package grossmarkt.maps;

import grossmarkt.application.Produkt;
import java.util.Collections;
import java.util.HashMap;

public class ProduktMap {

  private final HashMap<Integer, Produkt> produktHashMap = new HashMap<>();


  public HashMap<Integer, Produkt> getProduktHashMap() {
    return produktHashMap;
  }

  /**
   * Adds produkt
   * @param menge quantity
   * @param bezeichnung description
   * @param herkunfstregion region of origin
   * @param kategorie category
   * @param mhd best-before-date
   */
  public void addProdukt(int menge, String bezeichnung, String herkunfstregion, String kategorie,
      String mhd) {
    int nextFreeKey = produktHashMap.isEmpty() ? 1 : Collections.max(produktHashMap.keySet()) + 1;

    produktHashMap.put(nextFreeKey,
        new Produkt(nextFreeKey, menge, bezeichnung, herkunfstregion, kategorie, mhd));
  }

  /**
   * adds demo data
   */
  public void populateWithDemodata() {
    addProdukt(1, "Bezeichnung", "Herkunftsregion", "Kategorie A", "22.04.2021");
    addProdukt(15, "asdasdasdasda", "Herkunftsregion", "Kategorie A", "22.04.2021");
    addProdukt(11, "Bezeicsdasdasdashnung", "asdasda sdasd ", "Kategorie B", "22.04.2021");
    addProdukt(42, "Bezeicdasdasdasdhnung", "Herkunftsregion", "Kategorie B", "22.04.2021");

  }

  /**
   * deletes produkt by key
   * @param key id of produkt
   */
  public void deleteProdukt(int key) {
    produktHashMap.remove(key);
  }

  /**
   * updates produkt
   * @param produkt replacement / updated version
   */
  public void updateProdukt(Produkt produkt) {
    produktHashMap.replace(produkt.getProduktNr(), produkt);
  }
}
