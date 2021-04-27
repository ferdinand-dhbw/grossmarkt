package grossmarkt.controller;

import static grossmarkt.controller.ControllerUtility.featureAlert;
import static grossmarkt.controller.ControllerUtility.switchScene;

import grossmarkt.controller.ControllerUtility.View;
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

/**
 * Controller for Home View
 *
 * @author Gruppe 2: Clara, Ferdinand, Florian, Jonas
 * @version 1.0
 * @since 27.04.2021
 */
public class HomeController implements Controller {

  private MapReference mapReference;


  @FXML
  private Text timestamp, changeOrt;
  @FXML
  private Button navKunde, navLager, homeAuftrag, navLieferant, navProduzent, homeLager;

  /**
   * initializes HomeController sets MapReference, initializes clock, and initializes bar
   *
   * @param reference global references to HashMaps
   */
  public void init(MapReference reference) {
    setMapReference(reference);
    initClock();
    initEvents();
  }

  private void initEvents() {
    EventHandler<ActionEvent> featureAlert = event -> featureAlert(
        homeLager.getScene().getWindow());
    navKunde.setOnAction(featureAlert);
    homeAuftrag.setOnAction(featureAlert);

    navProduzent.setOnAction(
        event -> switchScene(View.PRODUZENT, navKunde.getScene(), getClass(), mapReference)
    );
    navLager.setOnAction(
        event -> switchScene(View.LAGER, navKunde.getScene(), getClass(), mapReference));
    homeLager.setOnAction(
        event -> switchScene(View.LAGER, homeLager.getScene(), getClass(), mapReference));
    navLieferant.setOnAction(
        event -> switchScene(View.LIEFERANT, navKunde.getScene(), getClass(), mapReference));
    changeOrt.setOnMouseClicked(mouseEvent -> featureAlert(navKunde.getScene().getWindow()));
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
