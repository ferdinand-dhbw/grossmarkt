package grossmarkt.application;

/**
 * Lieferant.java: Lieferant Class
 *
 * @author Gruppe2
 * @version 1.0
 * @since 20.04.2021
 *
 */

public class Lieferant extends Person {
  private String linkPreisliste;
  private String produzenten; //TODO check if sufficient

  /**
   * beschreibung
   *
   * @param id
   * @param vorname
   * @param nachname
   * @param land
   * @param stadt
   * @param strasse
   * @param hausNr
   * @param plz
   * @param linkPreisliste
   * @param produzenten
   */

  public Lieferant(int id, String vorname, String nachname, String land, String stadt,
      String strasse, String hausNr, int plz, String linkPreisliste, String produzenten) {
    super(id, vorname, nachname, land, stadt, strasse, hausNr, plz);
    this.linkPreisliste=linkPreisliste;
    this.produzenten=produzenten;
  }

  /**
   * beschreibung
   *
   * @param vorname
   * @param nachname
   * @param land
   * @param stadt
   * @param strasse
   * @param hausNr
   * @param plz
   * @param linkPreisliste
   * @param produzenten
   */

  public void updateAll(String vorname, String nachname, String land, String stadt, String strasse, String hausNr,
                        int plz, String linkPreisliste, String produzenten) {
    this.setVorname(vorname);
    this.setNachname(nachname);
    this.setLand(land);
    this.setStadt(stadt);
    this.setStrasse(strasse);
    this.setHausNr(hausNr);
    this.setPlz(plz);
    this.setLinkPreisliste(linkPreisliste);
    this.setProduzenten(produzenten);
  }

  /**
   * beschreibung
   *
   * @return
   */

  public String getLinkPreisliste() {
    return linkPreisliste;
  }

  /**
   * beschreibung
   *
   * @param linkPreisliste
   */

  public void setLinkPreisliste(String linkPreisliste) {
    this.linkPreisliste = linkPreisliste;
  }

  /**
   * beschreibung
   *
   * @return
   */

  public String getProduzenten() {
    return produzenten;
  }

  /**
   * beschreibung
   *
   * @param produzenten
   */

  public void setProduzenten(String produzenten) {
    this.produzenten = produzenten;
  }
}
