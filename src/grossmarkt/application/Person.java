package grossmarkt.application;

/**
 * Person.java: Person Class
 *
 * @author Gruppe2
 * @version 1.0
 * @since 20.04.2021
 *
 */

public abstract class Person {
  private int id;
  private String vorname, nachname, land, stadt, strasse, hausNr;
  private int plz;

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
   */

  public Person(int id, String vorname, String nachname, String land, String stadt, String strasse, String hausNr,
                int plz) {
    this.vorname = vorname;
    this.nachname = nachname;
    this.land = land;
    this.stadt = stadt;
    this.strasse = strasse;
    this.hausNr = hausNr;
    this.plz = plz;
    this.id = id;
  }


  /**
   * description
   *
   * @return
   */

  public String getAdressString() {
    return strasse.concat(" ").concat(hausNr).concat("\n").concat(Integer.toString(plz)).concat(" ").concat(stadt)
            .concat("\n").concat(land);
  }

  /**
   * description
   *
   * @return
   */

  public int getId() {
    return id;
  }

  /**
   * description
   *
   * @return
   */

  public String getVorname() {
    return vorname;
  }


  /**
   * description
   *
   * @param vorname
   */

  public void setVorname(String vorname) {
    this.vorname = vorname;
  }

  /**
   * description
   *
   * @return
   */

  public String getNachname() {
    return nachname;
  }

  /**
   * description
   *
   * @param nachname
   */

  public void setNachname(String nachname) {
    this.nachname = nachname;
  }

  /**
   * description
   *
   * @return
   */

  public String getLand() {
    return land;
  }


  /**
   * description
   *
   * @param land
   */

  public void setLand(String land) {
    this.land = land;
  }

  /**
   * description
   *
   * @return
   */

  public String getStadt() {
    return stadt;
  }

  /**
   * description
   *
   * @param stadt
   */

  public void setStadt(String stadt) {
    this.stadt = stadt;
  }

  /**
   * description
   *
   * @return
   */

  public String getStrasse() {
    return strasse;
  }

  /**
   * description
   *
   * @param strasse
   */

  public void setStrasse(String strasse) {
    this.strasse = strasse;
  }

  /**
   * description
   *
   * @return
   */

  public String getHausNr() {
    return hausNr;
  }

  /**
   * description
   *
   * @param hausNr
   */

  public void setHausNr(String hausNr) {
    this.hausNr = hausNr;
  }

  /**
   * description
   *
   * @return
   */

  public int getPlz() {
    return plz;
  }

  /**
   * description
   *
   * @param plz
   */

  public void setPlz(int plz) {
    this.plz = plz;
  }
}
