package grossmarkt.maps;

import grossmarkt.application.DBHandler;

public class MapReference {

  private final KundeMap kundeMap = new KundeMap();
  private final LieferantMap lieferantMap = new LieferantMap();
  private final ProduktMap produktMap = new ProduktMap();
  private final ProduzentMap produzentMap = new ProduzentMap();
  private DBHandler db;

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

  public void saveToDB(){
    db.save(this);
  }
}
