package grossmarkt.application;

/**
 * Produkt.java: Produkt Class
 *
 * @author Gruppe2
 * @version 1.0
 * @since 20.04.2021
 *
 */

public class Produkt {
  private int produktNr, menge;
  private String bezeichnung, herkunftsregion, kategorie, mhd;

  /**
   * description
   *
   * @param produktNr
   * @param menge
   * @param bezeichnung
   * @param herkunftsregion
   * @param kategorie
   * @param mhd
   */

  public Produkt(int produktNr, int menge, String bezeichnung, String herkunftsregion, String kategorie, String mhd) {
    this.produktNr = produktNr;
    this.menge = menge;
    this.bezeichnung = bezeichnung;
    this.herkunftsregion = herkunftsregion;
    this.kategorie = kategorie;
    this.mhd = mhd;
  }

  /**
   * description
   *
   * @return
   */

  public int getProduktNr() {
    return produktNr;
  }

  /**
   * description
   *
   * @param produktNr
   */

  public void setProduktNr(int produktNr) {
    this.produktNr = produktNr;
  }

  /**
   * description
   *
   * @return
   */

  public int getMenge() {
    return menge;
  }

  /**
   * description
   *
   * @param menge
   */

  public void setMenge(int menge) {
    this.menge = menge;
  }

  /**
   * description
   *
   * @return
   */

  public String getBezeichnung() {
    return bezeichnung;
  }

  /**
   * description
   *
   * @param bezeichnung
   */

  public void setBezeichnung(String bezeichnung) {
    this.bezeichnung = bezeichnung;
  }

  /**
   * description
   *
   * @return
   */

  public String getHerkunftsregion() {
    return herkunftsregion;
  }

  /**
   * description
   *
   * @param herkunftsregion
   */

  public void setHerkunftsregion(String herkunftsregion) {
    this.herkunftsregion = herkunftsregion;
  }

  /**
   * description
   *
   * @return
   */

  public String getKategorie() {
    return kategorie;
  }

  /**
   * description
   *
   * @param kategorie
   */

  public void setKategorie(String kategorie) {
    this.kategorie = kategorie;
  }

  /**
   * description
   *
   * @return
   */

  public String getMhd() {
    return mhd;
  }

  /**
   * description
   *
   * @param mhd
   */

  public void setMhd(String mhd) {
    this.mhd = mhd;
  }
}
