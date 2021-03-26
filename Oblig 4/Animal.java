public abstract class Animal
{
  public final String name;
  public final int animalID;

  public Animal(String name, int code) throws ZooException
  {
    if (name.equals("") || code < 0)
    {
      throw new ZooException("Ikke valide input i konstruktøren til Animal-klassen");
    }
    this.name = name;
    this.animalID = code;
  }

  @Override
  public String toString()
  {
    return "Animal [name=" + name + ", code=" + animalID + "]";
  }
}
