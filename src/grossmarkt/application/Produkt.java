package grossmarkt.application;

public class Produkt {
  private int produktNr, menge;
  private String bezeichnung, herkunftsregion, kategorie, mhd;

  public Produkt(int produktNr, int menge, String bezeichnung, String herkunftsregion,
      String kategorie, String mhd) {
    this.produktNr = produktNr;
    this.menge = menge;
    this.bezeichnung = bezeichnung;
    this.herkunftsregion = herkunftsregion;
    this.kategorie = kategorie;
    this.mhd = mhd;
  }

  public int getProduktNr() {
    return produktNr;
  }

  public void setProduktNr(int produktNr) {
    this.produktNr = produktNr;
  }

  public int getMenge() {
    return menge;
  }

  public void setMenge(int menge) {
    this.menge = menge;
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
}
