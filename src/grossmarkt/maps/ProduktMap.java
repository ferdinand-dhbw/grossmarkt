package grossmarkt.maps;

import grossmarkt.application.Produkt;
import java.util.Collections;
import java.util.HashMap;

/**
 * Data structure to manage Produkt (product)
 *
 * @author Gruppe 2: Clara, Ferdinand, Florian, Jonas
 * @version 1.0
 * @since 27.04.2021
 */
public class ProduktMap {

  private final HashMap<Integer, Produkt> produktHashMap = new HashMap<>();


  public HashMap<Integer, Produkt> getProduktHashMap() {
    return produktHashMap;
  }

  /**
   * Adds produkt
   *
   * @param menge           quantity
   * @param bezeichnung     description
   * @param herkunftsregion region of origin
   * @param kategorie       category
   * @param einkaufsdatum   buy date
   * @param preis           purchase price
   * @param mhd             best-before-date
   */
  public void addProdukt(int menge, String bezeichnung, String herkunftsregion, String kategorie,
      String einkaufsdatum, double preis, String mhd) {
    int nextFreeKey = produktHashMap.isEmpty() ? 1 : Collections.max(produktHashMap.keySet()) + 1;

    produktHashMap.put(nextFreeKey,
        new Produkt(nextFreeKey, menge, bezeichnung, herkunftsregion, kategorie, einkaufsdatum,
            preis, mhd));
  }

  /**
   * adds demo data
   */
  public void populateWithDemodata() {
    addProdukt(14, "Kiste Äpfel", "Bodenseeregion", "Obst", "24.04.2021", 14.99, "24.05.2021");
    addProdukt(3, "Kiste Bananen", "Costa Rica", "Obst", "27.04.2021", 21.79, "27.05.2021");
    addProdukt(8, "Kiste Gurken", "Stuttgart", "Gemüse", "27.04.2021", 8.18, "27.05.2021");
  }

  /**
   * deletes produkt by key
   *
   * @param key id of produkt
   */
  public void deleteProdukt(int key) {
    produktHashMap.remove(key);
  }

  /**
   * updates produkt
   *
   * @param produkt replacement / updated version
   */
  public void updateProdukt(Produkt produkt) {
    produktHashMap.replace(produkt.getProduktNr(), produkt);
  }
}
