package grossmarkt.controller;

import grossmarkt.maps.MapReference;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class HomeController {

  private MapReference mapReference;

  @FXML
  private Text timestamp;
  @FXML
  private Button nav_kunde;
  @FXML
  private Button home_aufträge;

  public void initializeUI() {
    initClock();
    initEvents();
  }

  private void initEvents() {
    EventHandler<ActionEvent> featureAlert = event -> featureAlert();
    nav_kunde.setOnAction(featureAlert);
    home_aufträge.setOnAction(featureAlert);
  }


  private void initClock() {
    Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy\nHH:mm:ss");
      timestamp.setText(LocalDateTime.now().format(formatter));
    }), new KeyFrame(Duration.seconds(1)));
    clock.setCycleCount(Animation.INDEFINITE);
    clock.play();
  }

  public void setListReference(MapReference mapReference) {
    this.mapReference = mapReference;
  }

  public void featureAlert() {
    var alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Funktion nicht verfügbar");
    alert.setContentText("Die ausgewählte Funktion ist in dieser Version des Programms nocht nicht verfügbar.");
    alert.setHeaderText(null);

    alert.showAndWait();
  }

}
