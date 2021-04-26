package grossmarkt.controller;

import grossmarkt.maps.MapReference;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Window;

/**
 * ControllerUtility.java: ControllerUtility Class
 *
 * @author Gruppe2
 * @version 1.0
 * @since 20.04.2021
 *
 */

public class ControllerUtility {

  public enum Views {
    LAGER("Lager.fxml"),
    HOME("Home.fxml"),
    LIEFERANT("Lieferant.fxml");

    public final String filename;

    /**
     * description
     *
     * @param filename
     */

    Views(String filename) {
      this.filename = filename;
    }
  }

  /**
   * description
   *
   * @param view
   * @param currentScene
   * @param currentClass
   * @param reference
   */

  public static void switchScene(Views view, Scene currentScene, Class currentClass, MapReference reference) {
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

  /**
   * description
   *
   * @param parent
   */

  public static void featureAlert(Window parent) {
    var alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Funktion nicht verfügbar");
    alert.setContentText("Die ausgewählte Funktion ist in dieser Version des Programms noch nicht verfügbar.");
    alert.setHeaderText(null);
    alert.initOwner(parent);
    alert.showAndWait();
  }
}
