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
import javafx.scene.text.Text;
import javafx.util.Duration;

public class HomeController implements Controller {

  private MapReference mapReference;


  @FXML
  private Text timestamp,changeOrt;
  @FXML
  private Button nav_kunde, nav_lager, home_aufträge, nav_lieferant, nav_produzent, home_lager;

  public void init(MapReference reference) {
    setMapReference(reference);
    initClock();
    initEvents();
  }

  private void initEvents() {
    EventHandler<ActionEvent> featureAlert = event -> featureAlert(home_lager.getScene().getWindow());
    nav_kunde.setOnAction(featureAlert);
    home_aufträge.setOnAction(featureAlert);

    nav_produzent.setOnAction(
        event -> switchScene(Views.PRODUZENT, nav_kunde.getScene(), getClass(), mapReference)
    );
    nav_lager.setOnAction(
        event -> switchScene(Views.LAGER, nav_kunde.getScene(), getClass(), mapReference));
    home_lager.setOnAction(
        event -> switchScene(Views.LAGER, home_lager.getScene(), getClass(), mapReference));
    nav_lieferant.setOnAction(
        event -> switchScene(Views.LIEFERANT, nav_kunde.getScene(), getClass(), mapReference));
    changeOrt.setOnMouseClicked(mouseEvent -> featureAlert(nav_kunde.getScene().getWindow()));
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
