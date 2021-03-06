package grossmarkt.application;

/**
 * Abstract class for common properties of a person
 *
 * @author Gruppe 2: Clara, Ferdinand, Florian, Jonas
 * @version 1.0
 * @since 27.04.2021
 */
public abstract class Person {

  private final int id;
  private String vorname, nachname, land, stadt, strasse, hausNr;
  private int plz;

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
   * Formats String for address.
   *
   * @return adress of Person
   */
  public String getAdressString() {
    return strasse.concat(" ").concat(hausNr).concat("\n").concat(Integer.toString(plz)).concat(" ").concat(stadt)
            .concat("\n").concat(land);
  }

  public int getId() {
    return id;
  }

  public String getVorname() {
    return vorname;
  }

  public void setVorname(String vorname) {
    this.vorname = vorname;
  }

  public String getNachname() {
    return nachname;
  }

  public void setNachname(String nachname) {
    this.nachname = nachname;
  }

  public String getLand() {
    return land;
  }

  public void setLand(String land) {
    this.land = land;
  }

  public String getStadt() {
    return stadt;
  }

  public void setStadt(String stadt) {
    this.stadt = stadt;
  }

  public String getStrasse() {
    return strasse;
  }

  public void setStrasse(String strasse) {
    this.strasse = strasse;
  }

  public String getHausNr() {
    return hausNr;
  }

  public void setHausNr(String hausNr) {
    this.hausNr = hausNr;
  }

  public int getPlz() {
    return plz;
  }

  public void setPlz(int plz) {
    this.plz = plz;
  }
}
