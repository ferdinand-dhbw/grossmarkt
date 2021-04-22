package grossmarkt.controller;

import static grossmarkt.controller.ControllerUtility.featureAlert;
import static grossmarkt.controller.ControllerUtility.switchScene;

import grossmarkt.controller.ControllerUtility.Views;
import grossmarkt.maps.MapReference;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class LagerController implements Controller {

  private MapReference reference;

  @FXML
  private Button nav_start;
  @FXML
  private Button nav_lieferant;
  @FXML
  private Button nav_kunde;




  @Override
  public void init(MapReference reference) {
    setReference(reference);
    initEvents();
  }

  private void initEvents() {
    EventHandler<ActionEvent> featureAlert = event -> featureAlert(nav_kunde.getScene().getWindow());
    nav_kunde.setOnAction(featureAlert);

    nav_start.setOnAction(
        event -> switchScene(Views.HOME, nav_start.getScene(), getClass(), reference));
    nav_lieferant.setOnAction(
        event -> switchScene(Views.LIEFERANT, nav_lieferant.getScene(), getClass(), reference));
  }

  public void setReference(MapReference reference) {
    this.reference = reference;
  }
}
