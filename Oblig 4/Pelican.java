class Pelican extends Oviparous implements Walkable, Flyable
{
  public Pelican(String nameS, int codeS)
  {
    super(nameS, codeS);
  }

  @Override
  public boolean walk()
  {
    return true;
  }

  @Override
  public boolean fly()
  {
      return true;
  }
}
