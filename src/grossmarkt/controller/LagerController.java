package grossmarkt.controller;

import static grossmarkt.controller.ControllerUtility.switchScene;

import grossmarkt.controller.ControllerUtility.Views;
import grossmarkt.maps.MapReference;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class LagerController implements Controller {

  private MapReference reference;

  @FXML
  private Button nav_start;
  @FXML
  private Button nav_lieferant;



  @Override
  public void init(MapReference reference) {
    setReference(reference);
    initEvents();
  }

  private void initEvents() {
    nav_start.setOnAction(
        event -> switchScene(Views.HOME, nav_start.getScene(), getClass(), reference));
    nav_lieferant.setOnAction(
        event -> switchScene(Views.LIEFERANT, nav_lieferant.getScene(), getClass(), reference));
  }

  public void setReference(MapReference reference) {
    this.reference = reference;
  }
}
