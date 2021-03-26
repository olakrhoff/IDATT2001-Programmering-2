import java.util.*;

class Sit extends Tribune
{
  private int[] _totalBusySeats; //antall opptatte seter per rad antar jeg at det er ment

  public Sit(String name, int price, int seatRows, int seats)
  {
    super(name, seatRows * seats, price);
    this._totalBusySeats = new int[seatRows];
  }

  //getere & setere
  public int getSeatRows()
  {
    return this._totalBusySeats.length;
  }

  public int getSeats()
  {
    return super.getCapacity() / this._totalBusySeats.length;
  }

  public void setBusySeats(int antall, int row)
  {
    if (antall <= this.getSeats() - this._totalBusySeats[row])
    {
      this._totalBusySeats[row] += antall;
    }
  }

  //metoder
  @Override
  public int findNumberOfSoldTickets()
  {
    int temp = 0;
    for(int i : this._totalBusySeats)
    {
      temp += i;
    }
    return temp;
  }

  @Override
  public int findIncome()
  {
    return super.getPrice() * this.findNumberOfSoldTickets();
  }

  @Override
  public Ticket[] buyTicket(int wantedTickets)
  {
    for (int i = 0; i < this._totalBusySeats.length; i++)
    {
      if (wantedTickets <= this.getSeats() - this._totalBusySeats[i])
      {
        Ticket[] tickets = new SittingTicket[wantedTickets];
        for (int j = 0; j < wantedTickets; j++)
        {
          tickets[j] = new SittingTicket(this.getName(), this.getPrice(), i, this._totalBusySeats[i] + j);
        }
        this.setBusySeats(wantedTickets, i);
        return tickets;
      }
    }
    return null;
  }

  @Override
  public Ticket[] buyTicket(String[] namesOfBuyers)
  {
    for (int i = 0; i < this._totalBusySeats.length; i++)
    {
      if (namesOfBuyers.length <= this.getSeats() - this._totalBusySeats[i])
      {
        Ticket[] tickets = new SittingTicket[namesOfBuyers.length];
        for (int j = 0; j < namesOfBuyers.length; j++)
        {
          tickets[j] = new SittingTicket(this.getName(), this.getPrice(), i, this._totalBusySeats[i] + j);
        }
        this.setBusySeats(namesOfBuyers.length, i);
        return tickets;
      }
    }
    return null;
  }
}
