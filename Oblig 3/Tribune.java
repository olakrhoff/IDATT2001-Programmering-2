class Tribune implements Comparable
{
  private final String _TRIBUNE_NAME;
  private final int _CAPACITY;
  private final int _PRICE;

  public Tribune(String name, int capacity, int price)
  {
    if (name.trim().equals("") || capacity == 0 || price < 0)
    {
      throw new IllegalArgumentException("Noen av argumentene i Tribune konstruktøren er ikke valide");
    }

    this._TRIBUNE_NAME = name.trim();
    this._CAPACITY = capacity;
    this._PRICE = price;
  }

  //getere
  public String getName()
  {
    return this._TRIBUNE_NAME;
  }

  public int getPrice()
  {
    return this._PRICE;
  }

  public int getCapacity()
  {
    return this._CAPACITY;
  }

  //metoder
  public int findNumberOfSoldTickets()
  {
    return -1;
  }

  public int findIncome()
  {
    return -1;
  }

  public Ticket[] buyTicket(int wantedTickets)
  {
    return null;
  }

  public Ticket[] buyTicket(String[] namesOfBuyers)
  {
    return null;
  }

  //CompareTo
  public int compareTo(Object obj)
  {
    try
    {
      Tribune newObj = (Tribune)obj;
      if (this.findIncome() > newObj.findIncome())
      {
        return 1;
      }
      else if (this.findIncome() < newObj.findIncome())
      {
        return -1;
      }
      return 0;
    }
    catch (Exception e)
    {
      throw new IllegalArgumentException("Tribune objekter må sammen lignes meg tilsvarende eller subklasser");
    }
  }

  //toString-metode
  public String toString()
  {
    return "\nName: " + this._TRIBUNE_NAME + "\nCapacity: " + this._CAPACITY + "\nPrice: " + this._PRICE + "\nSold: " + findNumberOfSoldTickets() + "\nIncome: " + findIncome();
  }
}
