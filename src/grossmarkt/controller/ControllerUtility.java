package grossmarkt.controller;


import grossmarkt.maps.MapReference;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class ControllerUtility {

  public enum Views {
    LAGER("Lager.fxml"),
    HOME("Home.fxml"),
    LIEFERANT("Lieferant.fxml");

    public final String filename;

    Views(String filename) {
      this.filename = filename;
    }
  }

  public static void switchScene(Views view, Scene currentScene, Class currentClass,
      MapReference reference) {
    System.out.println("Switching to " + view.filename);
    FXMLLoader loader = new FXMLLoader(currentClass.getResource("../" + view.filename));
    Parent root = null;
    try {
      root = loader.load();
      ((Controller) loader.getController()).init(reference);
    } catch (IOException e) {
      e.printStackTrace();
    }
    currentScene.setRoot(root);
  }
}
