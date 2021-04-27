package grossmarkt;

import grossmarkt.application.DBHandler;
import grossmarkt.controller.HomeController;
import grossmarkt.maps.MapReference;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Main class Starts program
 *
 * @author Gruppe 2: Clara, Ferdinand, Florian, Jonas
 * @version 1.0
 * @since 27.04.2021
 */
public class Main extends Application {

  private static MapReference MAP_REFERENCE;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage homeStage) throws Exception {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
    Parent root = loader.load();

    DBHandler dbHandler = new DBHandler();
    MAP_REFERENCE = dbHandler.read();

    HomeController homeController = loader.getController();
    homeController.init(MAP_REFERENCE);
    MAP_REFERENCE.getLieferantMap().populateWithDemodata();
    MAP_REFERENCE.getProduktMap().populateWithDemodata();
    MAP_REFERENCE.getProduzentMap().populateWithDemodata();

    homeStage.setTitle("Obst & Gemüse Meier oHG Verwaltung");
    homeStage.setMinHeight(576);
    homeStage.setMinWidth(1024);
    homeStage.setScene(new Scene(root, homeStage.getMinWidth(), homeStage.getMinHeight()));
    homeStage.getIcons().add(new Image("https://img.icons8.com/cotton/344/vegetables-box--v1.png"));
    homeStage.show();
  }
}