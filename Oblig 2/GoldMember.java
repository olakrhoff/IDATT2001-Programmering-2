import java.time.*;

class GoldMember extends BonusMember
{
  public GoldMember(int medlemsnummer, Personals personals, LocalDate date, int points)
  {
    super(medlemsnummer, personals, date);
    super.registerPoints(points);
  }

  @Override
  public void registerPoints(int newPoints)
  {
    super.registerPoints((int)(newPoints * 1.5));
  }
}
