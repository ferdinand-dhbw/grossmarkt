package grossmarkt.controller;

import grossmarkt.application.Lieferant;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class LieferantController{
  @FXML
  private TableView<Lieferant> lieferantenTableView;


  public void initialize(){
    TableColumn<Lieferant, String> lNummer = new TableColumn<>("Lieferantennummer"),
        lVorname = new TableColumn<>("Vorname"),
        lNachname = new TableColumn<>("Nachname"),
        lAdresse = new TableColumn<>("Geschäftsadresse"),
        lProduzent = new TableColumn<>("Produzenz"), //TODO implemnt this relationship
        lPreisliste = new TableColumn<>("Preisliste");
    lNummer.setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getId()));
    lVorname.setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getVorname()));
    lNachname.setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getNachname()));
    //lAdresse.setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getA));
    //lProduzent.setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().get));
    //lPreisliste.setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getId()));
    lieferantenTableView.getColumns().addAll(lNummer, lVorname, lNachname);
  }




}
