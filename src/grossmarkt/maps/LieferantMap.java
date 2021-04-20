package grossmarkt.maps;

import grossmarkt.application.Lieferant;
import grossmarkt.application.Produzent;
import java.util.Collections;
import java.util.HashMap;

public class LieferantMap {
  private HashMap<Integer, Lieferant> lieferantHashMap = new HashMap<>();

  public HashMap<Integer, Lieferant> getLieferantHashMap() {
    return lieferantHashMap;
  }

  public void addLieferant(String vorname, String nachname, String land, String stadt,
      String strasse, String hausNr, int plz){
    int nextFreeKey = lieferantHashMap.isEmpty() ? 1 : Collections.max(lieferantHashMap.keySet()) +1;

    lieferantHashMap.put(nextFreeKey, new Lieferant(nextFreeKey, vorname, nachname, land, stadt, strasse, hausNr, plz)); // TODO nextFreeKey auf ID
  }

  public void populateWithDemodata(){
    addLieferant("Ann", "Geber", "DE", "Stuttgart", "Schulstrasse", "3a", 70174);
    addLieferant("Jo", "Ghurt", "GB", "London", "Mainstreet", "420", 424242);
    addLieferant("Knut", "Schfleck", "DE", "Muenchen", "Wilde Maus", "0", 80331);
  }
}
