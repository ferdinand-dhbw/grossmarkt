package grossmarkt.maps;

public class MapReference {

  private final KundeMap kundeMap = new KundeMap();
  private final LieferantMap lieferantMap = new LieferantMap();
  private final ProduzentMap produzentMap = new ProduzentMap();

  public KundeMap getKundeMap() {
    return kundeMap;
  }

  public LieferantMap getLieferantMap() {
    return lieferantMap;
  }

  public ProduzentMap getProduzentMap() {
    return produzentMap;
  }
}
