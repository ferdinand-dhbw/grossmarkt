package grossmarkt.maps;

import grossmarkt.application.Lieferant;
import java.util.Collections;
import java.util.HashMap;

/**
 * LieferantMap.java: LieferantMap Class
 *
 * @author Gruppe2
 * @version 1.0
 * @since 20.04.2021
 *
 */

public class LieferantMap {
  private HashMap<Integer, Lieferant> lieferantHashMap = new HashMap<>();

  public HashMap<Integer, Lieferant> getLieferantHashMap() {
    return lieferantHashMap;
  }

  /**
   * beschreibung
   */

  public void addLieferant(String vorname, String nachname, String land, String stadt, String strasse, String hausNr,
                           int plz, String linkPreisliste, String produzenten) {
    int nextFreeKey = lieferantHashMap.isEmpty() ? 1 : Collections.max(lieferantHashMap.keySet()) +1;

    lieferantHashMap.put(nextFreeKey, new Lieferant(nextFreeKey, vorname, nachname, land, stadt, strasse, hausNr, plz,
            linkPreisliste, produzenten));
  }

  /**
   * beschreibung
   */

  public void populateWithDemodata() {
    addLieferant("Ann", "Geber", "DE", "Stuttgart", "Schulstrasse", "3a",
            70174, "https://www.example.com", "Bauer Mueller");
    addLieferant("Jo", "Ghurt", "GB", "London", "Mainstreet", "420",
            424242, "https://www.example.com", "Bauer Mueller");
    addLieferant("Knut", "Schfleck", "DE", "Muenchen", "Wilde Maus", "0",
            80331, "https://www.example.com", "Bauer Mueller");
  }

  /**
   * beschreibung
   *
   * @param  key
   *
   */

  public void deleteLieferant(int key) {
    lieferantHashMap.remove(key);
  }

  /**
   * beschreibung
   *
   * @param  lieferant
   *
   */

  public void updateLieferant(Lieferant lieferant) {
    lieferantHashMap.replace(lieferant.getId(), lieferant);
  }
}
