package grossmarkt.application;

import grossmarkt.maps.MapReference;

public class DBHandler {
  public DBHandler(){
    //establish connection
  }

  public void save(MapReference reference){
    //Preprocessing and saving to DB
    System.out.println("Saved on DB");
    return;
  }

  public MapReference read(){
    System.out.println("Read from DB");
    return new MapReference(this);
  }
}
