package grossmarkt.controller;


import grossmarkt.maps.MapReference;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DialogPane;
import javafx.stage.Window;


/**
 * Different controller utilities used by several controller
 *
 * @author Gruppe 2: Clara, Ferdinand, Florian, Jonas
 * @version 1.0
 * @since 27.04.2021
 */
public class ControllerUtility {

  /**
   * switches the GUI JavaFX-Scenes
   *
   * @param view         FXML file to switch to
   * @param currentScene reference to current scene
   * @param currentClass reference to current class
   * @param reference    reference to maps
   */
  public static void switchScene(View view, Scene currentScene, Class currentClass, MapReference reference) {
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
   * Alert for missing functionality due to demo version
   *
   * @param parent parent window
   */
  public static void featureAlert(Window parent) {
    var alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Funktion nicht verfügbar");
    alert.setContentText("Die ausgewählte Funktion ist in dieser Version des Programms noch nicht verfügbar.");
    alert.setHeaderText(null);
    alert.initOwner(parent);

    DialogPane dialogPane = alert.getDialogPane();
    dialogPane.setStyle("-fx-background-color: #282c34;");
    dialogPane.lookup(".content.label").setStyle("-fx-text-fill: white");
    alert.showAndWait();
  }

  /**
   * enumeration for different possible views
   */
  public enum View {
    LAGER("Lager.fxml"),
    HOME("Home.fxml"),
    LIEFERANT("Lieferant.fxml"),
    PRODUZENT("Produzent.fxml");

    public final String filename;

    View(String filename) {
      this.filename = filename;
    }
  }
}
