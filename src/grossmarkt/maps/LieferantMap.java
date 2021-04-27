package grossmarkt.maps;

import grossmarkt.application.Lieferant;
import java.util.Collections;
import java.util.HashMap;

/**
 * Data structure to manage Lieferant (supplier)
 *
 * @author Gruppe 2: Clara, Ferdinand, Florian, Jonas
 * @version 1.0
 * @since 27.04.2021
 */
public class LieferantMap {

  private final HashMap<Integer, Lieferant> lieferantHashMap = new HashMap<>();

  public HashMap<Integer, Lieferant> getLieferantHashMap() {
    return lieferantHashMap;
  }

  /**
   * Adds a new lieferant
   *
   * @param vorname        first name
   * @param nachname       last name
   * @param land           country
   * @param stadt          city
   * @param strasse        street
   * @param hausNr         house number
   * @param plz            post code
   * @param linkPreisliste link (String) to pricing
   * @param produzenten    link (String) to produzenten
   */
  public void addLieferant(String vorname, String nachname, String land, String stadt,
      String strasse, String hausNr, int plz, String linkPreisliste, String produzenten) {
    int nextFreeKey =
        lieferantHashMap.isEmpty() ? 1 : Collections.max(lieferantHashMap.keySet()) + 1;

    lieferantHashMap.put(nextFreeKey,
        new Lieferant(nextFreeKey, vorname, nachname, land, stadt, strasse, hausNr, plz,
            linkPreisliste, produzenten));
  }

  /**
   * adds demo data
   */
  public void populateWithDemodata() {
    addLieferant("Ann", "Geber", "DE", "Stuttgart",
        "Schulstrasse", "3a", 70174, "https://www.example.com", "Bauer Mueller");
    addLieferant("Jo", "Ghurt", "GB", "London", "Mainstreet", "420", 424242,
        "https://www.example.com", "Bauer Mueller");
    addLieferant("Knut", "Schfleck", "DE", "Muenchen", "Wilde Maus", "0", 80331,
        "https://www.example.com", "Bauer Mueller");
  }

  /**
   * deletes lieferant by key
   *
   * @param key id of lieferant to be deleted
   */
  public void deleteLieferant(int key) {
    lieferantHashMap.remove(key);
  }

  /**
   * updates lieferant
   *
   * @param lieferant replacement / updated version
   */
  public void updateLieferant(Lieferant lieferant) {
    lieferantHashMap.replace(lieferant.getId(), lieferant);
  }
}
