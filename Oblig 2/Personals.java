/**
* Personalia.java 2010‐01‐18
* Klasse med personopplysninger: fornavn, etternavn, epostadresse og passord.
* Passordet kan endres, men da må det nye være forskjellig fra det gamle.
* Passordkontrollen skiller ikke mellom store og små bokstaver.
*/
class Personals
{
  private final String _surname;
  private final String _firstname;
  private final String _ePostadr;
  private String _password;

  /**
  * Konstruktør:
  * Alle data må oppgis: fornavn, etternavn, ePostadr, passord
  * Ingen av dataene kan være null eller blanke strenger.
  */
  public Personals(String firstname, String surname, String ePostadr, String password)
  {
    if (firstname == null || surname == null || ePostadr == null || password == null || firstname.trim().equals("") || surname.trim().equals("") || ePostadr.trim().equals("") || password.trim().equals(""))
    {
      throw new IllegalArgumentException("Et eller flere konstruktrør argumenter er null og/eller blanke.");
    }
    this._firstname = firstname.trim();
    this._surname = surname.trim();
    this._ePostadr = ePostadr.trim();
    this._password = password.trim();
  }

  //getere
  public String getFirstname()
  {
    return this._firstname;
  }

  public String getSurname()
  {
    return this._surname;
  }

  public String getEPostadr()
  {
    return this._ePostadr;
  }

  /**
  * Metoden returnerer true dersom passordet er korrekt.
  * Passordkontrollen skiller ikke mellom store og små bokstaver.
  */
  public boolean okPassword(String password)
  {
    return this._password.equalsIgnoreCase(password);
  }

  /**
  * Metoden setter nytt passord, dersom det er forskjellig fra
  * det gamle. To passord betraktes som like dersom det kun er
  * forskjeller i store/små bokstaver.
  * Metoden returnerer true dersom passordet ble endret, ellers false.
  */
  public boolean changePassword(String oldPassword, String newPassword)
  {
    if (oldPassword == null || newPassword == null)
    {
      return false;
    }
    if (!this._password.equalsIgnoreCase(oldPassword.trim()))
    {
      return false;
    }
    else
    {
      this._password = newPassword.trim();
      return true;
    }
  }

  @Override
  public String toString()
  {
    return "\n" + this._surname + ", " + this._firstname + "\nmail: " + this._ePostadr + "\npassord: " + this._password;
  }
}
