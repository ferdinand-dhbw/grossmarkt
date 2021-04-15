package grossmarkt.application;

public class Kunde extends Person{
  private String email;

  public Kunde(String vorname, String nachname, String land, String stadt, String strasse,
      String hausNr, int plz, String email) {
    super(vorname, nachname, land, stadt, strasse, hausNr, plz);
    this.email=email;
  }
}
