package grossmarkt.controller;

import grossmarkt.application.Lieferant;
import grossmarkt.maps.LieferantMap;
import java.util.HashMap;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;


public class LieferantController{
  @FXML
  private TableView<Lieferant> lieferantenTableView;


  public void initialize() {
    TableColumn<Lieferant, String> lNummer = new TableColumn<>("Lieferantennummer"),
        lVorname = new TableColumn<>("Vorname"),
        lNachname = new TableColumn<>("Nachname"),
        lAdresse = new TableColumn<>("Geschäftsadresse"),
        lProduzent = new TableColumn<>(
            "Produzent"), //TODO implement this relationship (maybe as String)
        lPreisliste = new TableColumn<>("Preisliste");
    lNummer.setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getId()));
    lVorname.setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getVorname()));
    lNachname
        .setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getNachname()));
    lAdresse
        .setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getAdressString()));
    //lProduzent.setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().get));
    //lPreisliste.setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getId())); // TODO implement that
    lieferantenTableView.getColumns().addAll(lNummer, lVorname, lNachname, lAdresse);

    ObservableList<Lieferant> observableLieferantenList = FXCollections.observableArrayList();
    LieferantMap demo = new LieferantMap();

    lieferantenTableView.getItems().add(new Lieferant("Ann", "Geber", "DE", "Stuttgart", "Schulstrasse", "3a", 70174));
    //demo.populateWithDemodata();
    //demo.getLieferantHashMap().forEach((key, lieferant) -> observableLieferantenList.add(lieferant));
    //demo.getLieferantHashMap().forEach((key, lieferant) -> lieferantenTableView.getItems().add(lieferant)); //TODO FIX BUG

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

  public void showLieferant(Lieferant lieferant){
    System.out.println("clicked " + lieferant.getNachname());
  }

}
