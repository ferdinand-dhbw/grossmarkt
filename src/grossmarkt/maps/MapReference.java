package grossmarkt.maps;

import grossmarkt.application.DBHandler;

/**
 * Data structure to manage all Maps
 * Global variables of this project
 * @author Gruppe 2: Clara, Ferdinand, Florian, Jonas
 * @version 1.0
 * @since 27.04.2021
 */
public class MapReference {

  private final KundeMap kundeMap = new KundeMap();
  private final LieferantMap lieferantMap = new LieferantMap();
  private final ProduktMap produktMap = new ProduktMap();
  private final ProduzentMap produzentMap = new ProduzentMap();
  private DBHandler db;

  /**
   * new MapReference
   *
   * @param db reference to db to safe data to
   */
  public MapReference(DBHandler db) {
    setDBHandler(db);
  }

  public void setDBHandler(DBHandler db) {
    this.db = db;
  }

  public KundeMap getKundeMap() {
    return kundeMap;
  }

  public LieferantMap getLieferantMap() {
    return lieferantMap;
  }

  public ProduktMap getProduktMap() {
    return produktMap;
  }

  public ProduzentMap getProduzentMap() {
    return produzentMap;
  }

  /**
   * saves itself to linked db
   */
  public void saveToDB() {
    db.save(this);
  }
}
