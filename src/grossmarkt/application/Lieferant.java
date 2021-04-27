package grossmarkt.application;

/**
 * Class to manage supplier
 * @author Gruppe 2: Clara, Ferdinand, Florian, Jonas
 * @version 1.0
 * @since 27.04.2021
 */
@SuppressWarnings("DuplicatedCode")
public class Lieferant extends Person {

  private String linkPreisliste;
  private String produzenten;

  public Lieferant(int id, String vorname, String nachname, String land, String stadt,
      String strasse, String hausNr, int plz, String linkPreisliste, String produzenten) {
    super(id, vorname, nachname, land, stadt, strasse, hausNr, plz);
    this.linkPreisliste = linkPreisliste;
    this.produzenten = produzenten;
  }

  /**
   * Updates every field of the Lieferant-object
   *
   * @param vorname        first name
   * @param nachname       last name
   * @param land           country
   * @param stadt          city
   * @param strasse        street
   * @param hausNr         house number
   * @param plz            post code
   * @param linkPreisliste link (String) to pricing
   * @param produzenten    link (String) to producer
   */
  public void updateAll(String vorname, String nachname, String land, String stadt,
      String strasse, String hausNr, int plz, String linkPreisliste, String produzenten) {
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

  public String getLinkPreisliste() {
    return linkPreisliste;
  }

  public void setLinkPreisliste(String linkPreisliste) {
    this.linkPreisliste = linkPreisliste;
  }

  public String getProduzenten() {
    return produzenten;
  }

  public void setProduzenten(String produzenten) {
    this.produzenten = produzenten;
  }
}
