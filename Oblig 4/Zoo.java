import java.util.ArrayList;
import java.util.Collection;

public class Zoo
{
  private final String _name;
  private Collection<Animal> _animals = new ArrayList<Animal>();

  public Zoo(String name)
  {
    this._name = name;
  }

  public String getName()
  {
    return this._name;
  }

  public Collection<Animal> getAnimals()
  {
    return this._animals;
  }

  public void setAnimals(Collection<Animal> newAnimals)
  {
    this._animals.addAll(newAnimals);
  }
}
