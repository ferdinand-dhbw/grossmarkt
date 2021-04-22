package grossmarkt.maps;

import grossmarkt.application.Produzent;
import java.util.Collections;
import java.util.HashMap;

public class ProduzentMap {
  private HashMap<Integer, Produzent> produzentHashMap = new HashMap<>();

  public HashMap<Integer, Produzent> getProduzentHashMap() {
    return produzentHashMap;
  }

  public void addProduzent(String vorname, String nachname, String land, String stadt,
      String strasse, String hausNr, int plz, String linkPreisliste){
    int nextFreeKey = produzentHashMap.isEmpty() ? 1 : Collections.max(produzentHashMap.keySet()) +1;

    produzentHashMap.put(nextFreeKey, new Produzent(nextFreeKey, vorname, nachname, land, stadt, strasse, hausNr, plz, linkPreisliste));
  }

  public void populateWithDemodata(){
    addProduzent("Bauer", "Müller", "DE", "Stuttgart", "Schulstrasse", "3a", 70174,
        "https://www.example.com");
    addProduzent("Jo", "Ker", "GB", "London", "Mainstreet", "420", 424242, "https://www.example.com");
  }

  public void deleteProduzent(int key){
    produzentHashMap.remove(key);
  }

  public void updateProduzent(Produzent produzent){
    produzentHashMap.replace(produzent.getId(), produzent);
  }
}
