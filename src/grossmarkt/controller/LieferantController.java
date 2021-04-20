package grossmarkt.controller;

import grossmarkt.application.Lieferant;
import grossmarkt.maps.MapReference;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;


public class LieferantController implements Controller{
  @FXML
  private TableView<Lieferant> lieferantenTableView;
  @FXML
  private TextField lieferantSearchTxtfield;

  private MapReference reference;

  public void setMapReference(MapReference reference){ this.reference = reference; }

  public void init(MapReference reference) {
    setMapReference(reference);

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

  public void showLieferant(Lieferant lieferant){
    System.out.println("clicked " + lieferant.getNachname());
  }

  private FilteredList<Lieferant> filterLieferantenAndSetUpSearch() {
    ObservableList<Lieferant> observableLieferantList = FXCollections.observableArrayList();
    observableLieferantList.addAll(reference.getLieferantMap().getLieferantHashMap().values());
    FilteredList<Lieferant> filteredLieferanten = new FilteredList<>(observableLieferantList, p -> true);

    lieferantSearchTxtfield.textProperty()
        .addListener((observable, oldValue, newValue) -> {
          setPredicate(filteredLieferanten, newValue);
          lieferantenTableView.setItems(filteredLieferanten);
        });

    return filteredLieferanten;
  }

  private void setPredicate(FilteredList<Lieferant> filteredLieferanten, String newValue) {
    filteredLieferanten.setPredicate(lieferant -> {
      if (newValue == null || newValue.isEmpty())
        return true;
      if (lieferant.getVorname().toLowerCase().contains(newValue.toLowerCase()) || lieferant.getNachname().toLowerCase().contains(newValue.toLowerCase()))
        return true;
      return Integer.toString(lieferant.getId()).contains(newValue);
    });
  }
}
