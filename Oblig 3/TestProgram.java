import java.util.*;

class TestProgram
{
  public static void main(String[] args)
  {
    String[] names = {"Hans", "Per", "Jesper"};
    String[] names2 = {"Hans", "Per", "Jesper", "Ola"};

    Tribune stand1 = new Stand("Olas", 64, 250);
    Tribune stand2 = new Stand("Olines", 16, 350);

    Tribune sit = new Sit("Christians", 81, 12, 15);

    Tribune vip = new VIP("Kong Olavs", 3500, 3, 2);

    Tribune[] tribuner = {stand1, stand2, sit, vip};

    System.out.println("---------------------------------------------");
    System.out.println("Før noe er solgt!");
    for (Tribune i : tribuner)
    {
      System.out.println(i.toString());
    }

    stand1.buyTicket(14);
    stand2.buyTicket(3);
    sit.buyTicket(8);
    vip.buyTicket(names);
    vip.buyTicket(names2);

    System.out.println("---------------------------------------------");
    System.out.println("Etter noe er solgt!");
    for (Tribune i : tribuner)
    {
      System.out.println(i.toString());
    }

    Arrays.sort(tribuner);

    System.out.println("---------------------------------------------");
    System.out.println("Etter sortering");
    for (Tribune i : tribuner)
    {
      System.out.println(i.toString());
    }

  }
}
