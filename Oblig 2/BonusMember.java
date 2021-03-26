import java.time.*;

class BonusMember
{
  private final int _memberNo;
  private final Personals _personals;
  private final LocalDate _enrolledDate;
  private int _point = 0;

  //Konstruktør
  public BonusMember(int medlemsnummer, Personals personals, LocalDate date)
  {
    this._memberNo = medlemsnummer;
    this._personals = personals;
    this._enrolledDate = date;
  }

  //getere
  public int getMemberNo()
  {
    return this._memberNo;
  }

  public Personals getPersonals()
  {
    return this._personals;
  }

  public LocalDate getEnrolledDate()
  {
    return this._enrolledDate;
  }

  public int getPoints()
  {
    return this._point;
  }

  //metoder
  public int findQualificationPoints(LocalDate innDato)
  {
    int aar = Period.between(this._enrolledDate, innDato).getYears(); //Skulle bruke 'getDays()', men denne returnererte bare degene, i perioden uten å konvertere år og måndeer til dager først, så resultatet ble veldig feil
    if (aar < 1) //Tar ikke skuddår med i betraktning
    {
      return this.getPoints();
    }
    return 0;
  }

  public boolean okPassword(String password)
  {
    return this._personals.okPassword(password);
  }

  public void registerPoints(int newPoints)
  {
    this._point += newPoints;
  }

  //toString-metode
  @Override
  public String toString()
  {
    return "\nMedlemsnummer: " + this._memberNo +
           "\nPersoninfo: " + this._personals +
           "\nDato: " + this._enrolledDate +
           "\nPoeng: " + this._point + "\n";
  }
}
