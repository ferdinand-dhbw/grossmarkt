package grossmarkt.application;

import grossmarkt.maps.MapReference;

/**
 * Class to handle the database connection
 *
 * @author Gruppe 2: Clara, Ferdinand, Florian, Jonas
 * @version 1.0
 * @since 27.04.2021
 */
public class DBHandler {

  /**
   * Constructor: sets up and establish connection to DB
   */
  public DBHandler() {
    //establish connection
  }

  /**
   * Demo of Saving data to DB
   *
   * @param reference MapReference to save
   */
  public void save(MapReference reference) {
    //Preprocessing and saving to DB
    System.out.println("Saved on DB");
  }

  /**
   * Reads data stored in DB
   *
   * @return MapReference with data and links to itself.
   */
  public MapReference read() {
    System.out.println("Read from DB");
    return new MapReference(this);
  }
}
