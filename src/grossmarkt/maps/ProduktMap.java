package grossmarkt.maps;

import grossmarkt.application.Produkt;
import java.util.Collections;
import java.util.HashMap;

public class ProduktMap {

  private final HashMap<Integer, Produkt> produktHashMap = new HashMap<>();


  public HashMap<Integer, Produkt> getProduktHashMap() {
    return produktHashMap;
  }

  public void addProdukt(int menge, String bezeichnung, String herkunfstregion, String kategorie,
      String mhd) {
    int nextFreeKey = produktHashMap.isEmpty() ? 1 : Collections.max(produktHashMap.keySet()) + 1;

    produktHashMap.put(nextFreeKey,
        new Produkt(nextFreeKey, menge, bezeichnung, herkunfstregion, kategorie, mhd));
  }

  public void populateWithDemodata() {
    addProdukt(14, "Kiste Äpfel", "Bodenseeregion", "Obst", "25.05.2021");
    addProdukt(3, "Kiste Bananen", "Costa Rica", "Obst", "13.05.2021");
    addProdukt(8, "Kiste Gurken", "Stuttgart", "Gemüse", "08.05.2021");

  }

  public void deleteProdukt(int key) {
    produktHashMap.remove(key);
  }

  public void updateProdukt(Produkt produkt) {
    produktHashMap.replace(produkt.getProduktNr(), produkt);
  }
}
