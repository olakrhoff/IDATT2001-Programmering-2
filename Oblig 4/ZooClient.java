import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ZooClient
{
  public static void main(String[] args) throws ZooException
  {
    Zoo zoo = new Zoo("Kristiansand Dyrepark");
    Collection<Animal> animals = new ArrayList<Animal>();

    animals.add(new Crocodile("Crocodylus niloticus", 1001));
    animals.add(new Crocodile("Crocodylus niloticus", 1002));
    animals.add(new Crocodile("Crocodylus porosus", 1101));
    animals.add(new Crocodile("Crocodylus porosus", 1102));

    animals.add(new Pelican("Brown Pelican ", 4001));
    animals.add(new Pelican("Dalmatian Pelican ", 4101));

    animals.add(new Whale("Blue whale", 2001));
    animals.add(new Whale("Blue whale", 2002));
    animals.add(new Whale("Minke whale", 2101));
    animals.add(new Whale("Minke whale", 2102));

    animals.add(new Bat("Acerodon ", 3001));
    animals.add(new Bat("Cistugo ", 3002));

    //Tvinger fram ZooException
    animals.add(new Bat("", -1));

    zoo.setAnimals(animals);

    //Oppgave 2a)
    zoo.getAnimals().stream().filter(s -> s instanceof Flyable == true).forEach(s -> System.out.println(((Flyable)s).fly()));

    //Oppgave 2b)
    zoo.getAnimals().stream().filter(s -> s instanceof Mammal == true && s instanceof Jumpable == true).forEach(s -> System.out.println(((Jumpable)s).jump()));


    List<Object> walker = zoo.getAnimals().stream().filter(p -> p instanceof Walkable).collect(Collectors.toList());

    try
    {
      walker.stream().forEach(p ->((Flyable)p).fly());
    }
    catch (ClassCastException e)
    {
      System.out.println("ClassCastException: Klassen må implementere interfacet 'Flyable'!");
      System.exit(0);
    }
    System.out.println("Kake");
  }
}
