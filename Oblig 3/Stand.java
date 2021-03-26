class Stand extends Tribune
{
  private int _soldTickets;

  public Stand(String name, int capacity, int price)
  {
    super(name, capacity, price);
    this._soldTickets = 0;
  }

  //setere
  public void setSoldTickets(int antall)
  {
    this._soldTickets += antall;
  }

  //metoder
  @Override
  public int findNumberOfSoldTickets()
  {
    return this._soldTickets;
  }

  @Override
  public int findIncome()
  {
    return super.getPrice() * this._soldTickets;
  }

  @Override
  public Ticket[] buyTicket(int wantedTickets)
  {
    if (wantedTickets > this.getCapacity() - this._soldTickets)
    {
      return null;
    }
    Ticket[] tickets = new StandingTicket[wantedTickets];
    for (int i = 0; i < wantedTickets; i++)
    {
      tickets[i] = new StandingTicket(this.getName(), this.getPrice());
    }
    this.setSoldTickets(wantedTickets);
    return tickets;
  }

  @Override
  public Ticket[] buyTicket(String[] namesOfBuyers)
  {
    if (namesOfBuyers.length > this.getCapacity() - this._soldTickets)
    {
      return null;
    }
    Ticket[] tickets = new StandingTicket[namesOfBuyers.length];
    for (int i = 0; i < namesOfBuyers.length; i++)
    {
      tickets[i] = new StandingTicket(this.getName(), this.getPrice());
    }
    this.setSoldTickets(namesOfBuyers.length);
    return tickets;
  }
}
