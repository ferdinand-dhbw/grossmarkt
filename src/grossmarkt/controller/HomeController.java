package grossmarkt.controller;

import static grossmarkt.controller.ControllerUtility.featureAlert;
import static grossmarkt.controller.ControllerUtility.switchScene;

import grossmarkt.controller.ControllerUtility.Views;
import grossmarkt.maps.MapReference;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class HomeController implements Controller {

  private MapReference mapReference;


  @FXML
  private Text timestamp;
  @FXML
  private Button nav_kunde;
  @FXML
  private Button nav_lager;
  @FXML
  private Button home_aufträge;
  @FXML
  private Button nav_lieferant;
  @FXML
  private Button home_lager;
  @FXML
  private ComboBox nav_aufträge;

  public void init(MapReference reference) {
    setMapReference(reference);
    initClock();
    initEvents();

    nav_aufträge.getItems().addAll("VERKAUF", "EINKAUF");

  }

  private void initEvents() {
    EventHandler<ActionEvent> featureAlert = event -> featureAlert();
    nav_kunde.setOnAction(featureAlert);
    home_aufträge.setOnAction(featureAlert);

    nav_lager.setOnAction(
        event -> switchScene(Views.LAGER, nav_kunde.getScene(), getClass(), mapReference));
    home_lager.setOnAction(
        event -> switchScene(Views.LAGER, home_lager.getScene(), getClass(), mapReference));
    nav_lieferant.setOnAction(
        event -> switchScene(Views.LIEFERANT, nav_kunde.getScene(), getClass(), mapReference));
  }


  private void initClock() {
    Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy\nHH:mm:ss");
      timestamp.setText(LocalDateTime.now().format(formatter));
    }), new KeyFrame(Duration.seconds(1)));
    clock.setCycleCount(Animation.INDEFINITE);
    clock.play();
  }

  public void setMapReference(MapReference mapReference) {
    this.mapReference = mapReference;
  }

}
