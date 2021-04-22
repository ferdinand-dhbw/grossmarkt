package grossmarkt.controller;

import grossmarkt.application.Produkt;
import grossmarkt.maps.MapReference;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ProduktHinzufügenController implements Controller {

  @FXML
  private Button NextBtn;
  @FXML
  private TextField pBezeichnung, pKategorie, pHerkunft;
  @FXML
  private Text produktNummer, pWarnung;
  @FXML
  private Spinner<Integer> pAnzahl;
  @FXML
  private DatePicker pMhd;

  private MapReference reference;
  private Produkt currentProdukt;

  @Override
  public void init(MapReference reference) {
    this.reference = reference;
    NextBtn.setOnAction(event -> updateOrCreateLieferant());

    pAnzahl.setValueFactory(new IntegerSpinnerValueFactory(1, 999, 1, 1));
  }


  public void setUp(Produkt currentProdukt) {
    this.currentProdukt = currentProdukt;
    if (currentProdukt != null) {
      populateFields();
      produktNummer.setText(
          produktNummer.getText().concat(Integer.toString(currentProdukt.getProduktNr())));
    } else {
      produktNummer.setText("Neues Produkt");
      NextBtn.setText("ERSTELLEN"); // TODO auch anderst im GUI Dokument
    }
  }

  private void updateOrCreateLieferant() {
    String bezeichnung = pBezeichnung.getText(),
        kategorie = pKategorie.getText(),
        herkunft = pHerkunft.getText();
    Integer anzahl = pAnzahl.getValue();
    String Mhd;
    try {
      Mhd = pMhd.getValue().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    } catch (NullPointerException nullPointerException) {
      invalidInput();
      return;
    }

    if (anzahl <= 0) {
      invalidInput();
      return;
    }
    if (bezeichnung.compareTo("") == 0 || kategorie.compareTo("") == 0
        || herkunft.compareTo("") == 0) {
      invalidInput();
      return;
    }

    if (currentProdukt != null) {
      currentProdukt.updateAll(anzahl, bezeichnung, herkunft, kategorie, Mhd);
      reference.getProduktMap().updateProdukt(currentProdukt);
    } else {
      reference.getProduktMap()
          .addProdukt(anzahl, bezeichnung, herkunft, kategorie, Mhd);
    }
    Stage stage = (Stage) NextBtn.getScene().getWindow();
    stage.close();
  }

  private void populateFields() {
    pBezeichnung.setText(currentProdukt.getBezeichnung());
    pKategorie.setText(currentProdukt.getKategorie());
    pHerkunft.setText(currentProdukt.getHerkunftsregion());
    pAnzahl.getValueFactory().setValue(currentProdukt.getMenge());
    pMhd.setValue(localDate(currentProdukt.getMhd()));
  }

  public static LocalDate localDate(String dateString) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    return LocalDate.parse(dateString, formatter);
  }

  private void invalidInput() {
    pWarnung.setText("Kein valider Input");
  }
}
