class Bat extends Mammal implements Flyable
{
  public Bat(String nameS, int codeS)
  {
    super(nameS, codeS);
  }

  @Override
  public boolean fly()
  {
    return true;
  }
}
