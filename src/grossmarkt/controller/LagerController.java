package grossmarkt.controller;

import static grossmarkt.controller.ControllerUtility.featureAlert;
import static grossmarkt.controller.ControllerUtility.switchScene;

import grossmarkt.application.Produkt;
import grossmarkt.controller.ControllerUtility.View;
import grossmarkt.maps.MapReference;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.stage.Modality;
import javafx.stage.Stage;

@SuppressWarnings({"DuplicatedCode", "unchecked"})
public class LagerController implements Controller {

  @FXML
  private TableView<Produkt> lagerTableView;
  @FXML
  private TextField lagerSearchTxtfield;
  @FXML
  private Button delBtn, addBtn, navStart, navKunde, navProduzent, navLieferant;


  private MapReference reference;

  public void setMapReference(MapReference reference) {
    this.reference = reference;
  }

  /**
   * initializes HomeController
   * sets MapReference, initializes bar, and sets up TableView as well as buttons
   * @param reference global references to HashMaps
   */
  public void init(MapReference reference) {
    setMapReference(reference);
    initEvents();
    setUpTableView();

    delBtn.setOnAction(event ->
        deleteProdukte(
            new ArrayList<>(lagerTableView.getSelectionModel().getSelectedItems())));

    Image image = new Image("https://img.icons8.com/metro/344/ffffff/delete.png", 16, 16, true, true);
    ImageView imageView = new ImageView(image);
    delBtn.setGraphic(imageView);

    addBtn.setOnAction(event -> showProdukt(null));
  }

  /**
   * Function to call dialog to manipulate or add instance
   * @param produkt Current instance or null
   */
  public void showProdukt(Produkt produkt) {
    Parent root;
    ProduktPopUpController produktPopUpController;
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("../ProduktPopUp.fxml"));
      root = loader.load();
      produktPopUpController = loader.getController();
      produktPopUpController.init(reference);
      produktPopUpController.setUp(produkt);
      Stage addStage = new Stage();
      addStage.setScene(new Scene(root, 600, 285));
      addStage.setResizable(false);
      addStage.initModality(Modality.APPLICATION_MODAL);
      addStage.initOwner(navKunde.getScene().getWindow());
      addStage.getIcons().addAll(((Stage) navKunde.getScene().getWindow()).getIcons());
      addStage.showAndWait();

      safeTableViewRefresh();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void setUpTableView() {
    TableColumn<Produkt, String> pNummer = new TableColumn<>("Produktnummer"),
        pBezeichnung = new TableColumn<>("Bezeichnung"),
        pAnzahl = new TableColumn<>("Anzahl"),
        pKategorie = new TableColumn<>("Kategorie"),
        pHerkunftsregion = new TableColumn<>("Herkunft"),
        pMhd = new TableColumn<>("MHD");
    pNummer.setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getProduktNr()));
    pBezeichnung
        .setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getBezeichnung()));
    pAnzahl.setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getMenge()));
    pKategorie
        .setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getKategorie()));
    pHerkunftsregion.setCellValueFactory(
        param -> new SimpleObjectProperty(param.getValue().getHerkunftsregion()));
    pMhd.setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getMhd()));

    lagerTableView.getColumns()
        .addAll(pNummer, pBezeichnung, pAnzahl, pKategorie, pHerkunftsregion, pMhd);
    lagerTableView.getSelectionModel().setSelectionMode(
        SelectionMode.MULTIPLE
    );

    lagerTableView.setItems(filterProdukteAndSetUpSearch());

    lagerTableView.setRowFactory(tv -> {
      TableRow<Produkt> row = new TableRow<>();
      row.setOnMouseClicked(event -> {
        if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
            && event.getClickCount() == 2) {

          Produkt clickedProdukt = row.getItem();
          showProdukt(clickedProdukt);
        }
      });
      return row;
    });
  }

  private FilteredList<Produkt> filterProdukteAndSetUpSearch() {
    FilteredList<Produkt> filteredProdukte = filterProdukte();

    lagerSearchTxtfield.textProperty()
        .addListener((observable, oldValue, newValue) -> {
          setPredicate(filteredProdukte, newValue);
          lagerTableView.setItems(filteredProdukte);
        });

    return filteredProdukte;
  }

  private FilteredList<Produkt> filterProdukte() {
    ObservableList<Produkt> observableProduktList = FXCollections.observableArrayList();
    observableProduktList.addAll(reference.getProduktMap().getProduktHashMap().values());
    return new FilteredList<>(observableProduktList, p -> true);
  }

  private void setPredicate(FilteredList<Produkt> filteredProdukte, String newValue) {
    filteredProdukte.setPredicate(produkt -> {
      if (newValue == null || newValue.isEmpty()) {
        return true;
      }
      if (produkt.getBezeichnung().toLowerCase().contains(newValue.toLowerCase()) ||
          produkt.getKategorie().toLowerCase().contains(newValue.toLowerCase()) ||
          produkt.getHerkunftsregion().toLowerCase().contains(newValue.toLowerCase())) {
        return true;
      }
      return Integer.toString(produkt.getProduktNr()).contains(newValue);
    });
  }

  private void deleteProdukte(ArrayList<Produkt> produkts) {
    if (produkts.size() == 0) {
      return;
    }

    Alert alert = new Alert(AlertType.NONE);
    alert.setTitle("Möchten Sie die Produkte unwiderruflich löschen?");
    AtomicReference<String> content = new AtomicReference<>("Ausgewählte Produkte:");
    produkts.forEach(produkt -> content.set(content.get().concat(String.format("\n\t• %d  %s (%s)", produkt.getProduktNr(), produkt.getBezeichnung(), produkt.getKategorie()))));
    alert.setContentText(content.get());
    alert.initOwner(delBtn.getScene().getWindow());

    alert.setHeaderText(null);
    ButtonType buttonTypeCancel = new ButtonType("Nein", ButtonData.CANCEL_CLOSE);
    ButtonType buttonTypeAgree = new ButtonType("Ja", ButtonData.NEXT_FORWARD);

    alert.getButtonTypes().setAll(buttonTypeCancel, buttonTypeAgree);

    DialogPane dialogPane = alert.getDialogPane();
    dialogPane.setStyle("-fx-background-color: #282c34;");
    dialogPane.lookup(".content.label").setStyle("-fx-text-fill: white");

    if (alert.showAndWait().get().getButtonData() == ButtonData.NEXT_FORWARD) {
      produkts
          .forEach(produkt -> reference.getProduktMap().deleteProdukt(produkt.getProduktNr()));
      safeTableViewRefresh();
    }
  }

  private void initEvents() {
    EventHandler<ActionEvent> featureAlert = event -> featureAlert(
        navStart.getScene().getWindow());
    navKunde.setOnAction(featureAlert);

    navStart.setOnAction(
        event -> switchScene(View.HOME, navStart.getScene(), getClass(), reference));
    navLieferant.setOnAction(
        event -> switchScene(View.LIEFERANT, navLieferant.getScene(), getClass(), reference));
    navProduzent.setOnAction(
        event -> switchScene(View.PRODUZENT, navProduzent.getScene(), getClass(), reference));
  }

  private void safeTableViewRefresh(){
    FilteredList<Produkt> filteredProdukte = filterProdukte();
    setPredicate(filteredProdukte, lagerSearchTxtfield.getText());

    lagerSearchTxtfield.textProperty()
        .addListener((observable, oldValue, newValue) -> {
          setPredicate(filteredProdukte, newValue);
          lagerTableView.setItems(filteredProdukte);
        });

    lagerTableView.setItems(filteredProdukte);
    lagerTableView.refresh();
  }
}
