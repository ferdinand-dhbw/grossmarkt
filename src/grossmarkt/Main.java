package grossmarkt;

import grossmarkt.controller.HomeController;
import grossmarkt.maps.MapReference;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

  private static final MapReference MAP_REFERENCE = new MapReference();

  @Override
  public void start(Stage primaryStage) throws Exception{
    //Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));

    FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
    Parent root = loader.load();

    HomeController homeController = loader.getController();
    homeController.setListReference(MAP_REFERENCE);
    homeController.initializeUI();

    primaryStage.setTitle("Hello World");
    primaryStage.setScene(new Scene(root, 1024, 576));
    primaryStage.show();
  }


  public static void main(String[] args) {
    launch(args);
  }
}