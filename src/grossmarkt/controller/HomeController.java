package grossmarkt.controller;

import grossmarkt.maps.MapReference;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class HomeController {

  private MapReference mapReference;

  @FXML
  private Text timestamp;

  public void initializeUI() {
    initClock();
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

}
