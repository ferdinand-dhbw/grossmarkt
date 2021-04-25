package grossmarkt.controller;

import static grossmarkt.controller.ControllerUtility.featureAlert;
import static grossmarkt.controller.ControllerUtility.switchScene;

import grossmarkt.application.Lieferant;
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


public class LieferantController implements Controller {

  @FXML
  private TableView<Lieferant> lieferantenTableView;
  @FXML
  private TextField lieferantSearchTxtfield;
  @FXML
  private Button delBtn, addBtn, nav_lager, nav_start, nav_kunde, nav_produzent;

  private MapReference reference;

  public void setMapReference(MapReference reference) {
    this.reference = reference;
  }

  /**
   * initializes LieferantController
   * sets MapReference, initializes bar, and sets up TableView as well as buttons
   * @param reference global references to HashMaps
   */
  public void init(MapReference reference) {
    setMapReference(reference);
    initEvents();
    setUpTableView();

    delBtn.setOnAction(event ->
        deleteLieferanten(
            new ArrayList<>(lieferantenTableView.getSelectionModel().getSelectedItems())));

    Image image = new Image("https://img.icons8.com/metro/344/ffffff/delete.png", 16, 16, true, true);
    ImageView imageView = new ImageView(image);
    delBtn.setGraphic(imageView);

    addBtn.setOnAction(event -> showLieferant(null));
  }

  /**
   * Function to call dialog to manipulate or add instance
   * @param lieferant Current instance or null
   */
  public void showLieferant(Lieferant lieferant) {
    Parent root;
    LieferantHinzufügenController lieferantHinzufügenController;
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("../LieferantHinzufügen.fxml"));
      root = loader.load();
      lieferantHinzufügenController = loader.getController();
      lieferantHinzufügenController.init(reference);
      lieferantHinzufügenController.setUp(lieferant);
      Stage addStage = new Stage();
      addStage.setScene(new Scene(root, 600, 470));
      addStage.setResizable(false);
      addStage.initModality(Modality.APPLICATION_MODAL);
      addStage.initOwner(nav_kunde.getScene().getWindow());
      addStage.getIcons().addAll(((Stage) nav_kunde.getScene().getWindow()).getIcons());
      addStage.showAndWait();

      safeTableViewRefresh();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  private void setUpTableView() {
    TableColumn<Lieferant, String> lNummer = new TableColumn<>("Lieferantennummer"),
        lVorname = new TableColumn<>("Vorname"),
        lNachname = new TableColumn<>("Nachname"),
        lAdresse = new TableColumn<>("Geschäftsadresse"),
        lProduzent = new TableColumn<>("Produzent"),
        lPreisliste = new TableColumn<>("Preisliste");
    lNummer.setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getId()));
    lVorname.setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getVorname()));
    lNachname
        .setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getNachname()));
    lAdresse
        .setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getAdressString()));
    lProduzent
        .setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getProduzenten()));
    lPreisliste.setCellValueFactory(
        param -> new SimpleObjectProperty(param.getValue().getLinkPreisliste()));

    lieferantenTableView.getColumns()
        .addAll(lNummer, lVorname, lNachname, lAdresse, lProduzent, lPreisliste);
    lieferantenTableView.getSelectionModel().setSelectionMode(
        SelectionMode.MULTIPLE
    );

    lieferantenTableView.setItems(filterLieferantenAndSetUpSearch());

    lieferantenTableView.setRowFactory(tv -> {
      TableRow<Lieferant> row = new TableRow<>();
      row.setOnMouseClicked(event -> {
        if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
            && event.getClickCount() == 2) {

          Lieferant clickedLieferant = row.getItem();
          showLieferant(clickedLieferant);
        }
      });
      return row;
    });
  }

  private FilteredList<Lieferant> filterLieferantenAndSetUpSearch() {
    FilteredList<Lieferant> filteredLieferanten = filterLieferanten();

    lieferantSearchTxtfield.textProperty()
        .addListener((observable, oldValue, newValue) -> {
          setPredicate(filteredLieferanten, newValue);
          lieferantenTableView.setItems(filteredLieferanten);
        });

    return filteredLieferanten;
  }

  private FilteredList<Lieferant> filterLieferanten() {
    ObservableList<Lieferant> observableLieferantList = FXCollections.observableArrayList();
    observableLieferantList.addAll(reference.getLieferantMap().getLieferantHashMap().values());
    return new FilteredList<>(observableLieferantList, p -> true);
  }

  private void setPredicate(FilteredList<Lieferant> filteredLieferanten, String newValue) {
    filteredLieferanten.setPredicate(lieferant -> {
      if (newValue == null || newValue.isEmpty()) {
        return true;
      }
      if (lieferant.getVorname().toLowerCase().contains(newValue.toLowerCase()) ||
          lieferant.getNachname().toLowerCase().contains(newValue.toLowerCase()) ||
          lieferant.getVorname().concat(" ").concat(lieferant.getNachname()).toLowerCase()
              .contains(newValue.toLowerCase())) {
        return true;
      }
      return Integer.toString(lieferant.getId()).contains(newValue);
    });
  }

  private void deleteLieferanten(ArrayList<Lieferant> lieferants) {
    if (lieferants.size() == 0) {
      return;
    }

    Alert alert = new Alert(AlertType.NONE);
    alert.setTitle("Möchten Sie die Lieferanten unwiderruflich löschen?");
    AtomicReference<String> content = new AtomicReference<>("Ausgewählte Lieferanten:");
    lieferants.forEach(lieferant -> content.set(content.get().concat(String
        .format("\n\t• %d  %s %s", lieferant.getId(), lieferant.getVorname(),
            lieferant.getNachname()))));
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
      lieferants
          .forEach(lieferant -> reference.getLieferantMap().deleteLieferant(lieferant.getId()));
      safeTableViewRefresh();
    }
  }

  private void initEvents() {
    EventHandler<ActionEvent> featureAlert = event -> featureAlert(
        nav_start.getScene().getWindow());
    nav_kunde.setOnAction(featureAlert);

    nav_start.setOnAction(
        event -> switchScene(View.HOME, nav_start.getScene(), getClass(), reference));
    nav_lager.setOnAction(
        event -> switchScene(View.LAGER, nav_lager.getScene(), getClass(), reference));
    nav_produzent.setOnAction(
        event -> switchScene(View.PRODUZENT, nav_produzent.getScene(), getClass(), reference));
  }

  private void safeTableViewRefresh() {
    FilteredList<Lieferant> filteredLieferanten = filterLieferanten();
    setPredicate(filteredLieferanten, lieferantSearchTxtfield.getText());
    lieferantenTableView.setItems(filteredLieferanten);
    lieferantenTableView.refresh();
  }
}
