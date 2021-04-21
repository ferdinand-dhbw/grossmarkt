package grossmarkt.application;

public class Lieferant extends Person{
  private String linkPreisliste;
  private String produzenten; //TODO check if sufficient

  public Lieferant(int id, String vorname, String nachname, String land, String stadt,
      String strasse, String hausNr, int plz, String linkPreisliste, String produzenten) {
    super(id, vorname, nachname, land, stadt, strasse, hausNr, plz);
    this.linkPreisliste=linkPreisliste;
    this.produzenten=produzenten;
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
