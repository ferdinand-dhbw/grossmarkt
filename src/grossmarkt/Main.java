package grossmarkt;

import grossmarkt.controller.HomeController;
import grossmarkt.maps.MapReference;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Main extends Application {

  private static final MapReference MAP_REFERENCE = new MapReference();

  @Override
  public void start(Stage homeStage) throws Exception{
    //Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));

    FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
    Parent root = loader.load();

    HomeController homeController = loader.getController();
    homeController.setListReference(MAP_REFERENCE);
    homeController.initializeUI();

    homeStage.setTitle("Obst & Gemüse Meier oHG Verwaltung");
    homeStage.setMinHeight(576);
    homeStage.setMinWidth(1024);
    homeStage.setScene(new Scene(root, homeStage.getMinWidth(), homeStage.getMinHeight()));
    homeStage.show();
  }


  public static void main(String[] args) {
    launch(args);
  }
}