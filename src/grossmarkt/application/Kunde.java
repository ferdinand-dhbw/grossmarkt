package grossmarkt.application;

/**
 * Kunde.java: Kunde Class
 *
 * @author Gruppe2
 * @version 1.0
 * @since 20.04.2021
 *
 */

public class Kunde extends Person {
  enum LieferscheinPreaferenz {
    MAIL,
    DRUCK,
    MAIL_UND_DRUCK;
  }

  private String email;
  private LieferscheinPreaferenz preaferenz;

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
   * @param email
   * @param preaferenz
   */

  public Kunde(int id, String vorname, String nachname, String land, String stadt, String strasse, String hausNr,
               int plz, String email, LieferscheinPreaferenz preaferenz) {
    super(id, vorname, nachname, land, stadt, strasse, hausNr, plz);
    this.email=email;
    this.preaferenz=preaferenz;
  }
}
