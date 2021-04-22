package grossmarkt.application;

public class Produzent extends Person{
  private String linkPreisliste;

  public Produzent(int id, String vorname, String nachname, String land, String stadt,
      String strasse, String hausNr, int plz, String linkPreisliste) {
    super(id, vorname, nachname, land, stadt, strasse, hausNr, plz);
    this.linkPreisliste = linkPreisliste;
  }

  public void updateAll(String vorname, String nachname, String land, String stadt,
      String strasse, String hausNr, int plz, String linkPreisliste){
    this.setVorname(vorname);
    this.setNachname(nachname);
    this.setLand(land);
    this.setStadt(stadt);
    this.setStrasse(strasse);
    this.setHausNr(hausNr);
    this.setPlz(plz);
    this.setLinkPreisliste(linkPreisliste);
  }

  public String getLinkPreisliste() {
    return linkPreisliste;
  }

  public void setLinkPreisliste(String linkPreisliste) {
    this.linkPreisliste = linkPreisliste;
  }
}
