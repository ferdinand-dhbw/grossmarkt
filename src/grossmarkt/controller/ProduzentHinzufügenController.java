package grossmarkt.controller;

import grossmarkt.application.Lieferant;
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
      lPopupProduzent, lPopupPreisliste;
  @FXML
  private Text lLiefernantennummerText;
  @FXML
  private Text lWarnung;

  private MapReference reference;
  private Lieferant currentLieferant;

  @Override
  public void init(MapReference reference) {
    this.reference = reference;
    lPopupNextBtn.setOnAction(event -> updateOrCreateLieferant());
  }

  /**
   * To be called right after init
   *
   * @param currentLieferant Can be null for new Lieferant
   */
  public void setUp(Lieferant currentLieferant) {
    this.currentLieferant = currentLieferant;
    if (currentLieferant != null) {
      populateFields();
      lLiefernantennummerText.setText(
          lLiefernantennummerText.getText().concat(Integer.toString(currentLieferant.getId())));
    } else {
      lLiefernantennummerText.setText("Neuer Lieferant");
      lPopupNextBtn.setText("ERSTELLEN");
    }
  }

  private void updateOrCreateLieferant() {
    String vorname = lPopupVorname.getText(),
        nachname = lPopupNachname.getText(),
        strasse = lPopupStrasse.getText(),
        hNr = lPopupHNr.getText(),
        stadt = lPopupOrt.getText(),
        produzent = lPopupProduzent.getText(),
        plzStr = lPopupPLZ.getText(),
        preisliste = lPopupPreisliste.getText();
    int plz;
    if (vorname.compareTo("") == 0 || nachname.compareTo("") == 0 || strasse.compareTo("") == 0
        || hNr.compareTo("") == 0 || stadt.compareTo("") == 0 || produzent.compareTo("") == 0
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

    if (currentLieferant != null) {
      currentLieferant
          .updateAll(vorname, nachname, currentLieferant.getLand(), stadt, strasse, hNr, plz,
              preisliste, produzent);
      reference.getLieferantMap().updateLieferant(currentLieferant);
    } else {
      reference.getLieferantMap()
          .addLieferant(vorname, nachname, "DE", stadt, strasse, hNr, plz, preisliste,
              produzent);
    }
    Stage stage = (Stage) lPopupNextBtn.getScene().getWindow();
    stage.close();
  }

  private void populateFields() {
    lPopupNachname.setText(currentLieferant.getNachname());
    lPopupVorname.setText(currentLieferant.getVorname());
    lPopupStrasse.setText(currentLieferant.getStrasse());
    lPopupHNr.setText(currentLieferant.getHausNr());
    lPopupPLZ.setText(Integer.toString(currentLieferant.getPlz()));
    lPopupOrt.setText(currentLieferant.getStadt());
    lPopupProduzent.setText(currentLieferant.getProduzenten());
    lPopupPreisliste.setText(currentLieferant.getLinkPreisliste());
  }

  private void invalidInput() {
    lWarnung.setText("Kein valider Input");
  }
}
