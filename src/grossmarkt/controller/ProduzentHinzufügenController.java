package grossmarkt.controller;

import grossmarkt.application.Produzent;
import grossmarkt.maps.MapReference;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ProduzentHinzufügenController implements Controller {

  @FXML
  private Button lPopupNextBtn;
  @FXML
  private TextField lPopupNachname, lPopupVorname, lPopupStrasse, lPopupOrt, lPopupHNr, lPopupPLZ,
      lPopupPreisliste;
  @FXML
  private Text pProduzentennummerText, lWarnung;

  private MapReference reference;
  private Produzent currentProduzent;

  @Override
  public void init(MapReference reference) {
    this.reference = reference;
    lPopupNextBtn.setOnAction(event -> updateOrCreateProduzent());
  }

  /**
   * To be called right after init
   *
   * @param currentProduzent Can be null for new Produzent
   */
  public void setUp(Produzent currentProduzent) {
    this.currentProduzent = currentProduzent;
    if (currentProduzent != null) {
      populateFields();
      pProduzentennummerText.setText(
          pProduzentennummerText.getText().concat(Integer.toString(currentProduzent.getId())));
    } else {
      pProduzentennummerText.setText("Neuer Produzent");
      lPopupNextBtn.setText("ERSTELLEN");
    }
  }

  private void updateOrCreateProduzent() {
    String vorname = lPopupVorname.getText(),
        nachname = lPopupNachname.getText(),
        strasse = lPopupStrasse.getText(),
        hNr = lPopupHNr.getText(),
        stadt = lPopupOrt.getText(),
        plzStr = lPopupPLZ.getText(),
        preisliste = lPopupPreisliste.getText();
    int plz;
    if (vorname.compareTo("") == 0 || nachname.compareTo("") == 0 || strasse.compareTo("") == 0
        || hNr.compareTo("") == 0 || stadt.compareTo("") == 0
        || plzStr.compareTo("") == 0 || preisliste.compareTo("") == 0) {
      invalidInput();
      return;
    }
    try {
      plz = Integer.parseInt(plzStr);
    } catch (Exception e) {
      invalidInput();
      return;
    }

    if (currentProduzent != null) {
      currentProduzent
          .updateAll(vorname, nachname, currentProduzent.getLand(), stadt, strasse, hNr, plz,
              preisliste);
      reference.getProduzentMap().updateProduzent(currentProduzent);
    } else {
      reference.getProduzentMap()
          .addProduzent(vorname, nachname, "DE", stadt, strasse, hNr, plz, preisliste);
    }
    Stage stage = (Stage) lPopupNextBtn.getScene().getWindow();
    stage.close();
  }

  private void populateFields() {
    lPopupNachname.setText(currentProduzent.getNachname());
    lPopupVorname.setText(currentProduzent.getVorname());
    lPopupStrasse.setText(currentProduzent.getStrasse());
    lPopupHNr.setText(currentProduzent.getHausNr());
    lPopupPLZ.setText(Integer.toString(currentProduzent.getPlz()));
    lPopupOrt.setText(currentProduzent.getStadt());
    lPopupPreisliste.setText(currentProduzent.getLinkPreisliste());
  }

  private void invalidInput() {
    lWarnung.setText("Kein valider Input");
  }
}
