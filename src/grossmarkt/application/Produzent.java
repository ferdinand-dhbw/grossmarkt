package grossmarkt.application;

/**
 * Produzent.java: Produzent Class
 *
 * @author Gruppe2
 * @version 1.0
 * @since 20.04.2021
 *
 */

public class Produzent extends Person {
  
  /**
   * description
   *
   * @param id
   * @param vorname
   * @param nachname
   * @param land
   * @param stadt
   * @param strasse
   * @param hausNr
   * @param plz
   */

  public Produzent(int id, String vorname, String nachname, String land, String stadt, String strasse, String hausNr,
                   int plz) {
    super(id, vorname, nachname, land, stadt, strasse, hausNr, plz);
  }
}
