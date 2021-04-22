package grossmarkt.controller;

import static grossmarkt.controller.ControllerUtility.featureAlert;
import static grossmarkt.controller.ControllerUtility.switchScene;

import grossmarkt.application.Lieferant;
import grossmarkt.application.Produzent;
import grossmarkt.controller.ControllerUtility.Views;
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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class ProduzentController implements Controller {

  @FXML
  private TableView<Produzent> produzentenTableView;
  @FXML
  private TextField produzentenSearchTxtfield;
  @FXML
  private Button delBtn, addBtn;
  @FXML
  private Button nav_lager;
  @FXML
  private Button nav_start;
  @FXML
  private Button nav_kunde;


  private MapReference reference;

  public void setMapReference(MapReference reference) {
    this.reference = reference;
  }

  public void init(MapReference reference) {
    setMapReference(reference);
    initEvents();
    setUpTableView();

    delBtn.setOnAction(event ->
        deleteProduzenten(
            new ArrayList<>(produzentenTableView.getSelectionModel().getSelectedItems())));
    addBtn.setOnAction(event -> showProduzent(null));
  }

  public void showProduzent(Produzent produzent) {
    if (produzent != null) {
      System.out.println("clicked " + produzent.getNachname());
    }
    Parent root;
    ProduzentHinzufügenController produzentHinzufügenController;
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("../ProduzentHinzufügen.fxml"));
      root = loader.load();
      produzentHinzufügenController = loader.getController();
      produzentHinzufügenController.init(reference);
      produzentHinzufügenController.setUp(produzent);
      Stage addStage = new Stage();
      addStage.setScene(new Scene(root, 600, 470));
      addStage.setResizable(false);
      addStage.initModality(Modality.APPLICATION_MODAL);
      addStage.initOwner(nav_kunde.getScene().getWindow());
      addStage.getIcons().addAll(((Stage) nav_kunde.getScene().getWindow()).getIcons());
      addStage.showAndWait();

      FilteredList<Produzent> filteredProduzenten = filterProduzenten();
      setPredicate(filteredProduzenten, produzentenSearchTxtfield.getText());
      produzentenTableView.setItems(filteredProduzenten);
      produzentenTableView.refresh();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  private void setUpTableView() {
    TableColumn<Produzent, String> lNummer = new TableColumn<>("Produzentennummer"),
        lVorname = new TableColumn<>("Vorname"),
        lNachname = new TableColumn<>("Nachname"),
        lAdresse = new TableColumn<>("Geschäftsadresse"),
        lPreisliste = new TableColumn<>("Preisliste");
    lNummer.setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getId()));
    lVorname.setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getVorname()));
    lNachname
        .setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getNachname()));
    lAdresse
        .setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getAdressString()));

    lPreisliste.setCellValueFactory(
        param -> new SimpleObjectProperty(param.getValue().getLinkPreisliste()));

    produzentenTableView.getColumns()
        .addAll(lNummer, lVorname, lNachname, lAdresse, lPreisliste);
    produzentenTableView.getSelectionModel().setSelectionMode(
        SelectionMode.MULTIPLE
    );

    produzentenTableView.setItems(filterProduzentenAndSetUpSearch());

    produzentenTableView.setRowFactory(tv -> {
      TableRow<Produzent> row = new TableRow<>();
      row.setOnMouseClicked(event -> {
        if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
            && event.getClickCount() == 2) {

          Produzent clickedProduzent = row.getItem();
          showProduzent(clickedProduzent);
        }
      });
      return row;
    });
  }

  private FilteredList<Produzent> filterProduzentenAndSetUpSearch() {
    FilteredList<Produzent> filteredProduzenten = filterProduzenten();

    produzentenSearchTxtfield.textProperty()
        .addListener((observable, oldValue, newValue) -> {
          setPredicate(filteredProduzenten, newValue);
          produzentenTableView.setItems(filteredProduzenten);
        });

    return filteredProduzenten;
  }

  private FilteredList<Produzent> filterProduzenten() {
    ObservableList<Produzent> observableProduzentList = FXCollections.observableArrayList();
    observableProduzentList.addAll(reference.getProduzentMap().getProduzentHashMap().values());
    return new FilteredList<>(observableProduzentList, p -> true);
  }

  private void setPredicate(FilteredList<Produzent> filteredProduzenten, String newValue) {
    filteredProduzenten.setPredicate(produzent -> {
      if (newValue == null || newValue.isEmpty()) {
        return true;
      }
      if (produzent.getVorname().toLowerCase().contains(newValue.toLowerCase()) ||
          produzent.getNachname().toLowerCase().contains(newValue.toLowerCase()) ||
          produzent.getVorname().concat(" ").concat(produzent.getNachname()).toLowerCase()
              .contains(newValue.toLowerCase())) {
        return true;
      }
      return Integer.toString(produzent.getId()).contains(newValue);
    });
  }

  private void deleteProduzenten(ArrayList<Produzent> produzents) {
    if (produzents.size() == 0) {
      return;
    }

    Alert alert = new Alert(AlertType.NONE);
    alert.setTitle("Möchten Sie die Produzenten unwiderruflich löschen?");
    AtomicReference<String> content = new AtomicReference<>("Produzentennummern");
    produzents.forEach(produzent -> content
        .set(content.get().concat("\n").concat(Integer.toString(produzent.getId()))));
    alert.setContentText(content.get());
    alert.initOwner(delBtn.getScene().getWindow());

    alert.setHeaderText(null);
    ButtonType buttonTypeCancel = new ButtonType("Nein", ButtonData.CANCEL_CLOSE);
    ButtonType buttonTypeAgree = new ButtonType("Ja", ButtonData.NEXT_FORWARD);

    alert.getButtonTypes().setAll(buttonTypeCancel, buttonTypeAgree);

    if (alert.showAndWait().get().getButtonData() == ButtonData.NEXT_FORWARD) {
      produzents
          .forEach(produzent -> reference.getProduzentMap().deleteProduzent(produzent.getId()));
      produzentenTableView.setItems(filterProduzentenAndSetUpSearch());
      //produzentenTableView.refresh(); //implement in others aswell
    }
  }

  private void initEvents() {
    //TODO set all initEvents to new Btns
    EventHandler<ActionEvent> featureAlert = event -> featureAlert(nav_start.getScene().getWindow());
    nav_kunde.setOnAction(featureAlert);

    nav_start.setOnAction(
        event -> switchScene(Views.HOME, nav_start.getScene(), getClass(), reference));
    nav_lager.setOnAction(
        event -> switchScene(Views.LAGER, nav_lager.getScene(), getClass(), reference));
  }
}
