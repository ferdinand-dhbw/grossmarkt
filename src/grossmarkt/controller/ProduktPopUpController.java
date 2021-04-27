package grossmarkt.controller;

import grossmarkt.application.Produkt;
import grossmarkt.maps.MapReference;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory.DoubleSpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ProduktPopUpController implements Controller {

  @FXML
  private Button NextBtn;
  @FXML
  private TextField pBezeichnung, pKategorie, pHerkunft;
  @FXML
  private Text produktNummer, pWarnung;
  @FXML
  private Spinner<Integer> pAnzahl;
  @FXML
  private Spinner<Double> pPreis;
  @FXML
  private DatePicker pMhd, pEinkaufsdatum;

  private MapReference reference;
  private Produkt currentProdukt;

  public static LocalDate localDate(String dateString) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    return LocalDate.parse(dateString, formatter);
  }

  /**
   * initializes ProduktHinzufügenController sets MapReference, add handler to Button, init
   * Spinners, and default purchase date
   *
   * @param reference global references to HashMaps
   */
  @Override
  public void init(MapReference reference) {
    this.reference = reference;
    NextBtn.setOnAction(event -> updateOrCreateLieferant());

    initSpinners();

    pEinkaufsdatum.setValue(LocalDate.now());
  }

  /**
   * To be called right after init
   *
   * @param currentProdukt Can be null for new Produkt
   */
  public void setUp(Produkt currentProdukt) {
    this.currentProdukt = currentProdukt;
    if (currentProdukt != null) {
      populateFields();
      produktNummer.setText(
          produktNummer.getText().concat(Integer.toString(currentProdukt.getProduktNr())));
    } else {
      produktNummer.setText("Neues Produkt");
      NextBtn.setText("ERSTELLEN");
    }
  }

  private void updateOrCreateLieferant() {
    String bezeichnung = pBezeichnung.getText(),
        kategorie = pKategorie.getText(),
        herkunft = pHerkunft.getText();
    int anzahl = pAnzahl.getValue();
    double preis = pPreis.getValue();
    String mhd, einkaufsdatum;
    try {
      mhd = pMhd.getValue().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
      einkaufsdatum = pEinkaufsdatum.getValue().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
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
      currentProdukt.updateAll(anzahl, bezeichnung, herkunft, kategorie, einkaufsdatum, preis, mhd);
      reference.getProduktMap().updateProdukt(currentProdukt);
    } else {
      reference.getProduktMap()
          .addProdukt(anzahl, bezeichnung, herkunft, kategorie, einkaufsdatum, preis, mhd);
    }
    Stage stage = (Stage) NextBtn.getScene().getWindow();
    stage.close();
  }

  private void populateFields() {
    pBezeichnung.setText(currentProdukt.getBezeichnung());
    pKategorie.setText(currentProdukt.getKategorie());
    pHerkunft.setText(currentProdukt.getHerkunftsregion());
    pAnzahl.getValueFactory().setValue(currentProdukt.getAnzahl());
    pPreis.getValueFactory().setValue(currentProdukt.getPreis());
    pMhd.setValue(localDate(currentProdukt.getMhd()));
    pEinkaufsdatum.setValue(localDate(currentProdukt.getEinkaufsdatum()));
  }

  private void invalidInput() {
    pWarnung.setText("Kein valider Input");
  }

  private void initSpinners() {
    pAnzahl.setValueFactory(new IntegerSpinnerValueFactory(1, 999, 1, 1));
    pPreis.setValueFactory(new DoubleSpinnerValueFactory(0.00, 9999.99, 0.00, 0.01));

    pAnzahl.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
      if (!newValue.matches("\\d{1,3}")) {
        pAnzahl.getEditor().setText(oldValue);
      }
    });
    pPreis.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
      if (!newValue.matches("^[0-9]{1,4}(,[0-9]{0,2})?$")) {
        pPreis.getEditor().setText(oldValue);
      }
    });
  }
}
