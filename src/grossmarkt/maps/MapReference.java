package grossmarkt.maps;

/**
 * MapReference.java: MapReference Class
 *
 * @author Gruppe2
 * @version 1.0
 * @since 20.04.2021
 *
 */

public class MapReference {

  private final KundeMap kundeMap = new KundeMap();
  private final LieferantMap lieferantMap = new LieferantMap();

  /**
   * beshreibung
   * @return
   */

  public KundeMap getKundeMap() {
    return kundeMap;
  }

  /**
   * beshreibung
   * @return
   */

  public LieferantMap getLieferantMap() {
    return lieferantMap;
  }
}
