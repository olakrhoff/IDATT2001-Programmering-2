import java.time.LocalDate;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

class MemberArchive
{
  private final int _SILVER_LIMIT = 25000;
  private final int _GOLD_LIMIT = 75000;
  private final int _MAKS_TRY = 1; //Hva fader er dette for noe?
  private final Random _RANDOM_NUMBER = new Random();

  private List<BonusMember> _archive = new ArrayList<BonusMember>();

  //metoder
  public int newMember(Personals personals, LocalDate date)
  {
    BonusMember newMember = new BasicMember(findAvailableNo(), personals, date);
    this._archive.add(newMember);
    return newMember.getMemberNo();
  }

  public int findPoint(int medlemsnummer, String password)
  {
    for(BonusMember i : this._archive)
    {
      if (i.getMemberNo() == medlemsnummer)
      {
        if (i.okPassword(password))
        {
          return i.getPoints();
        }
      }
    }
    return -1;
  }

  public boolean registerPoints(int medlemsnummer, int newPoints)
  {
    for(BonusMember i : this._archive)
    {
      if (i.getMemberNo() == medlemsnummer)
      {
        i.registerPoints(newPoints);
        return true;
      }
    }
    return false;
  }

  public void checkMembers(LocalDate date)
  {
    this._archive.forEach(s ->
    {
      if (!(s instanceof GoldMember) && s.findQualificationPoints(date) >= this._GOLD_LIMIT)
      {
        BonusMember newGold = new GoldMember(s.getMemberNo(), s.getPersonals(), s.getEnrolledDate(), s.getPoints());
        this._archive.set(this._archive.indexOf(s), newGold);
      }
      else if (s instanceof BasicMember && s.findQualificationPoints(date) >= this._SILVER_LIMIT)
      {
        BonusMember newSilver = new SilverMember(s.getMemberNo(), s.getPersonals(), s.getEnrolledDate(), s.getPoints());
        this._archive.set(this._archive.indexOf(s), newSilver);
      }
    });
  }

  private BonusMember checkSilverLimit(int todo, LocalDate date)
  {
    Personals p = new Personals("firstname", "surname", "ePostadr", "password");
    BonusMember k = new BonusMember(0, p, LocalDate.of(2006, 2, 15));
    return k;
  }

  private BonusMember checkGoldLimit(int todo, LocalDate date)
  {
    Personals p = new Personals("firstname", "surname", "ePostadr", "password");
    BonusMember k = new BonusMember(0, p, LocalDate.of(2006, 2, 15));
    return k;
  }

  private BonusMember findMember(int todo)
  {
    Personals p = new Personals("firstname", "surname", "ePostadr", "password");
    BonusMember k = new BonusMember(0, p, LocalDate.of(2006, 2, 15));
    return k;
  }

  private int findAvailableNo()
  {
    boolean newRandom = true;
    int newMemberNo = 0;
    while(newRandom)
    {
      newRandom = false;
      newMemberNo = this._RANDOM_NUMBER.nextInt(10000000); //Maks kapasitet er på 10.000.000 medlemmer
      for(BonusMember i : this._archive)
      {
        if (i.getMemberNo() == newMemberNo)
        {
          newRandom = true;
        }
      }
    }
    return newMemberNo;
  }

  //Hva er dette for noe??
  public String createOutput(LocalDate date)
  {
    return "no";
  }

  //toStirng-metode
  @Override
  public String toString()
  {
    return this._archive.toString();
  }
}
