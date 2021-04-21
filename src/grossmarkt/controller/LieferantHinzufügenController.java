package grossmarkt.controller;

import grossmarkt.application.Lieferant;
import grossmarkt.maps.MapReference;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LieferantHinzufügenController implements Controller {

  @FXML
  private Button lPopupNextBtn;
  @FXML
  private TextField lPopupNachname, lPopupVorname, lPopupStrasse, lPopupOrt, lPopupHNr, lPopupPLZ, lPopupProduzent;
  @FXML
  private Text lLiefernantennummerText;

  private MapReference reference;
  private Lieferant currentLieferant;

  @Override
  public void init(MapReference reference) {
    this.reference = reference;
    lPopupNextBtn.setOnAction(event -> updateOrCreateLieferant());
  }

  public void setCurrentLieferant(Lieferant currentLieferant) {
    this.currentLieferant = currentLieferant;
    if (currentLieferant != null) {
      populateFields();
      lLiefernantennummerText.setText(
          lLiefernantennummerText.getText().concat(Integer.toString(currentLieferant.getId())));
    } else {

    }
  }

  private void updateOrCreateLieferant() {
    String vorname = lPopupVorname.getText(),
        nachname = lPopupNachname.getText(),
        strasse = lPopupStrasse.getText(),
        hNr = lPopupHNr.getText(),
        stadt = lPopupOrt.getText(),
        produzent = lPopupProduzent.getText(),
        plzStr = lPopupPLZ.getText();
    int plz;
    if (vorname.compareTo("") == 0 || nachname.compareTo("") == 0 || strasse.compareTo("") == 0
        || hNr.compareTo("") == 0 || stadt.compareTo("") == 0 || produzent.compareTo("") == 0
        || plzStr.compareTo("") == 0) {
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
              currentLieferant.getLinkPreisliste(), produzent); //TODO land, Preisliste
      reference.getLieferantMap().updateLieferant(currentLieferant);
    } else {

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
    // TODO COUNTRY?
    // TODO Preisliste
  }

  private void invalidInput() {
    System.out.println("Nope");
  }
}
