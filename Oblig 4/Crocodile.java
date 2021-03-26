class Crocodile extends Oviparous implements Swimmable, Walkable
{
  public Crocodile(String nameS, int codeS)
  {
    super(nameS, codeS);
  }

  @Override
  public boolean swim()
  {
    return true;
  }

  @Override
  public boolean walk()
  {
    return true;
  }
}
