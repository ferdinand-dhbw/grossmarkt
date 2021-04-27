package grossmarkt.application;

/**
 * Class to manage customers
 * @author Gruppe 2: Clara, Ferdinand, Florian, Jonas
 * @version 1.0
 * @since 27.04.2021
 */
public class Kunde extends Person {

  private final String email;
  private final LieferscheinPreaferenz preaferenz;
  public Kunde(int id, String vorname, String nachname, String land, String stadt, String strasse,
      String hausNr, int plz, String email, LieferscheinPreaferenz preaferenz) {
    super(id, vorname, nachname, land, stadt, strasse, hausNr, plz);
    this.email = email;
    this.preaferenz = preaferenz;
  }

  /**
   * enum for delivery note preference
   */
  enum LieferscheinPreaferenz {
    MAIL,
    DRUCK,
    MAIL_UND_DRUCK
  }
}
