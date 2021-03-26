class VIP extends Sit
{
  private String[][] _spectator; //tilskuere: antall rader * antall plasser pr rad

  public VIP(String name, int price, int seatRows, int seats)
  {
    super(name, price, seatRows, seats);
    this._spectator = new String[seatRows][seats]; //Tar ikke hensyn til at resultatet ikke går opp i et heltall og kapasiteten da vil synke
  }

  //setere
  public void setSeat(int row, int seat, String name)
  {
    this._spectator[seat][row] = name;
  }

  //metoder
  @Override
  public Ticket[] buyTicket(int wantedTickets)
  {
    return null;
  }

  @Override
  public Ticket[] buyTicket(String[] namesOfBuyers)
  {
    if (namesOfBuyers.length > this._spectator.length) //Sjekker om raden er lang nok til antallet folk forespurt
    {
      return null;
    }
    int rowCount = 0;

    for (int i = 0; i < this._spectator[0].length; i++) //går gjennom radene i VIP tribunen
    {

      for (int j = 0; j < this._spectator.length; j++) //går gjennom setene i raden
      {
        if (this._spectator[j][i] == null) //sjekker om sete på raden er ledig
        {
          rowCount++;
          if (rowCount == namesOfBuyers.length)
          {
            Ticket[] tickets = new SittingTicket[namesOfBuyers.length];
            for (int p = 0; p < namesOfBuyers.length; p++)
            {
              tickets[p] = new SittingTicket(super.getName(), super.getPrice(), i, j - namesOfBuyers.length + 1 + p);
              this.setSeat(i, j - namesOfBuyers.length + 1 + p, namesOfBuyers[p]);
            }
            return tickets;
          }
        }
      }
    }
    return null;
  }

  @Override
  public String toString()
  {
    String result = super.toString();
    for (int i = 0; i < this._spectator[0].length; i++)
    {
      for (int j = 0; j < this._spectator.length; j++)
      {
        result += "\nRow: " + i +  " Seat: " + j + " Spectator: " + this._spectator[j][i];
      }
    }
    return result;
  }
}
