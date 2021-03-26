import java.time.*;

class SilverMember extends BonusMember
{
  public SilverMember(int medlemsnummer, Personals personals, LocalDate date, int points)
  {
    super(medlemsnummer, personals, date);
    super.registerPoints(points);
  }

  @Override
  public void registerPoints(int newPoints)
  {
    super.registerPoints((int)(newPoints * 1.2));
  }
}
