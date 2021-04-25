package grossmarkt.maps;

import grossmarkt.application.DBHandler;

public class MapReference {

  private final KundeMap kundeMap = new KundeMap();
  private final LieferantMap lieferantMap = new LieferantMap();
  private final ProduktMap produktMap = new ProduktMap();
  private final ProduzentMap produzentMap = new ProduzentMap();
  private DBHandler db;

  /**
   * new MapReference
   * @param db reference to db to safe data to
   */
  public MapReference(DBHandler db){
    setDBHandler(db);
  }

  public void setDBHandler(DBHandler db){
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
  public void saveToDB(){
    db.save(this);
  }
}
