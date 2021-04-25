package grossmarkt;

import grossmarkt.application.DBHandler;
import grossmarkt.controller.HomeController;
import grossmarkt.maps.MapReference;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;

public class Main extends Application {
  //TODO "Achten Sie darauf [...] den Source Code hinreichend [zu] kommentieren"
  //TODO "Achten Sie darauf [...] Debugging print Statements [vor Abgabe] zu entfernen"
  //TODO "Achten Sie darauf [...] noch einmal im Team zu besprechen, [ob klar ist,] wo das Suchfeld ist"
  //TODO "Achten Sie darauf [...] die Demodaten [für die Produkte] aufzuhübschen"
  //TODO "Achten Sie darauf [...] ein Interface für die Datenbank [zu] implementieren

  private static MapReference MAP_REFERENCE;

  @Override
  public void start(Stage homeStage) throws Exception{
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
    Parent root = loader.load();

    DBHandler dbHandler = new DBHandler();
    MAP_REFERENCE = dbHandler.read();

    HomeController homeController = loader.getController();
    homeController.init(MAP_REFERENCE);
    MAP_REFERENCE.getLieferantMap().populateWithDemodata(); //TODO remove
    MAP_REFERENCE.getProduktMap().populateWithDemodata(); //TODO remove
    MAP_REFERENCE.getProduzentMap().populateWithDemodata(); //TODO remove

    homeStage.setTitle("Obst & Gemüse Meier oHG Verwaltung");
    homeStage.setMinHeight(576);
    homeStage.setMinWidth(1024);
    homeStage.setScene(new Scene(root, homeStage.getMinWidth(), homeStage.getMinHeight()));
    homeStage.getIcons().add(new Image("https://img.icons8.com/cotton/344/vegetables-box--v1.png"));
    homeStage.show();
  }


  public static void main(String[] args) {
    launch(args);
  }
}