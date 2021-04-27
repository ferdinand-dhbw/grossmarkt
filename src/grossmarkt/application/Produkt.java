package grossmarkt.application;

/**
 * Class to manage products
 *
 * @author Gruppe 2: Clara, Ferdinand, Florian, Jonas
 * @version 1.0
 * @since 27.04.2021
 */
public class Produkt {

  private int produktNr, anzahl;
  private double preis;
  private String bezeichnung, herkunftsregion, kategorie, einkaufsdatum, mhd;

  public Produkt(int produktNr, int anzahl, String bezeichnung, String herkunftsregion,
      String kategorie, String einkaufsdatum, double preis, String mhd) {
    this.produktNr = produktNr;
    this.anzahl = anzahl;
    this.bezeichnung = bezeichnung;
    this.herkunftsregion = herkunftsregion;
    this.kategorie = kategorie;
    this.einkaufsdatum = einkaufsdatum;
    this.preis = preis;
    this.mhd = mhd;
  }

  /**
   * Updates every field of the Lieferant-object
   *
   * @param anzahl          quantity
   * @param bezeichnung     description
   * @param herkunftsregion region of origin
   * @param kategorie       category
   * @param einkaufsdatum   buy date
   * @param preis           purchase price
   * @param mhd             best-before-date
   */
  public void updateAll(int anzahl, String bezeichnung, String herkunftsregion,
      String kategorie, String einkaufsdatum, double preis, String mhd) {
    this.anzahl = anzahl;
    this.bezeichnung = bezeichnung;
    this.herkunftsregion = herkunftsregion;
    this.kategorie = kategorie;
    this.einkaufsdatum = einkaufsdatum;
    this.preis = preis;
    this.mhd = mhd;
  }

  public int getProduktNr() {
    return produktNr;
  }

  public void setProduktNr(int produktNr) {
    this.produktNr = produktNr;
  }

  public int getAnzahl() {
    return anzahl;
  }

  public void setAnzahl(int anzahl) {
    this.anzahl = anzahl;
  }

  public String getBezeichnung() {
    return bezeichnung;
  }

  public void setBezeichnung(String bezeichnung) {
    this.bezeichnung = bezeichnung;
  }

  public String getHerkunftsregion() {
    return herkunftsregion;
  }

  public void setHerkunftsregion(String herkunftsregion) {
    this.herkunftsregion = herkunftsregion;
  }

  public String getKategorie() {
    return kategorie;
  }

  public void setKategorie(String kategorie) {
    this.kategorie = kategorie;
  }

  public String getMhd() {
    return mhd;
  }

  public void setMhd(String mhd) {
    this.mhd = mhd;
  }

  public String getEinkaufsdatum() {
    return einkaufsdatum;
  }

  public void setEinkaufsdatum(String einkaufsdatum) {
    this.einkaufsdatum = einkaufsdatum;
  }

  public double getPreis() {
    return preis;
  }

  public void setPreis(double preis) {
    this.preis = preis;
  }
}
