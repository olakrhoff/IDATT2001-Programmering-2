class Whale extends Mammal implements Jumpable, Swimmable
{
  public Whale(String nameS, int codeS)
  {
    super(nameS, codeS);
  }

  @Override
  public boolean jump()
  {
    return true;
  }

  @Override
  public boolean swim()
  {
    return true;
  }
}
