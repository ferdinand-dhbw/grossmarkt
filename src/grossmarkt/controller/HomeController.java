package grossmarkt.controller;

import grossmarkt.application.Lieferant;
import grossmarkt.maps.MapReference;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class HomeController implements Controller{

  private MapReference mapReference;

  public enum Views {
    LAGER("Lager.fxml"),
    HOME("Home.fxml"),
    LIEFERANT("Lieferant.fxml");

    public final String filename;

    Views(String filename) {
      this.filename = filename;
    }
  }

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

  public void init(MapReference reference) {
    setMapReference(reference);
    initClock();
    initEvents();
  }

  private void initEvents() {
    EventHandler<ActionEvent> featureAlert = event -> featureAlert();
    nav_kunde.setOnAction(featureAlert);
    home_aufträge.setOnAction(featureAlert);

    EventHandler<ActionEvent> switchScene = event -> switchScene(Views.LAGER);
    nav_lager.setOnAction(switchScene);

    nav_lieferant.setOnAction(event -> switchScene(Views.LIEFERANT));
  }

  private void switchScene(Views view) {
    System.out.println("Switching to " + view.filename);
    FXMLLoader loader = new FXMLLoader(getClass().getResource("../"+view.filename));
    Parent root = null;
    try {
      root = loader.load();
      ((Controller) loader.getController()).init(mapReference);
    } catch (IOException e) {
      e.printStackTrace();
    }
    Scene currentScene = nav_kunde.getScene();
    currentScene.setRoot(root);
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

  public void featureAlert() {
    var alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Funktion nicht verfügbar");
    alert.setContentText("Die ausgewählte Funktion ist in dieser Version des Programms nocht nicht verfügbar.");
    alert.setHeaderText(null);

    alert.showAndWait();
  }

}
