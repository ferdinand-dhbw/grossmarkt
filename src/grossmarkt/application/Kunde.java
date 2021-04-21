package grossmarkt.application;

public class Kunde extends Person{
  enum LieferscheinPreaferenz {
    MAIL,
    DRUCK,
    MAIL_UND_DRUCK;
  }

  private String email;
  private LieferscheinPreaferenz preaferenz;

  public Kunde(int id, String vorname, String nachname, String land, String stadt, String strasse,
      String hausNr, int plz, String email, LieferscheinPreaferenz preaferenz) {
    super(id, vorname, nachname, land, stadt, strasse, hausNr, plz);
    this.email=email;
    this.preaferenz=preaferenz;
  }
}
