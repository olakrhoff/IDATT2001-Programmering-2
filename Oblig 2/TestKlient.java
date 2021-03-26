import java.io.*;
import java.time.LocalDate;
import java.util.*;

class TestKlient
{

  MemberArchive memberArchive = new MemberArchive();

  private final int NEW_MEMBER = 1;
  private final int FIND_POINTS = 2;
  private final int REGISTER_POINTS = 3;
  private final int CHECK_MEMBERS = 4;
  private final int ALL_INFO = 5;
  private final int EXIT = 6;

  BufferedReader br = null;

  public static void main(String[] args)
  {
    TestKlient main = new TestKlient();
    main.start();
  }

  private int showMenu()
  {
    int menuChoice = 0;
    System.out.println("");
    System.out.println("1. Registrer nytt medlem");
    System.out.println("2. Finn antall oppsparte poeng");
    System.out.println("3. Registrer poeng");
    System.out.println("4. Sjekk medlemmer for oppgraderinger");
    System.out.println("5. Se all informasjon om medlemmer");
    System.out.println("6. Avslutt");
    System.out.println("\nVennligst velg fra menyen.\n");
    br = new BufferedReader(new InputStreamReader(System.in));

    try
    {
      String input = br.readLine();
      menuChoice = Integer.parseInt(input);
    }
    catch (Exception e)
    {
      System.out.println("Verdien må være et tall..." + "\n");
    }
    return menuChoice;
  }

  public void start()
  {
    br = new BufferedReader(new InputStreamReader(System.in), 8);
    boolean finished = true;
    while (finished) {
        int menuChoice = this.showMenu();
        switch (menuChoice)
        {
            case NEW_MEMBER:
                try
                {
                  //Henter ut informasjon til å lage Personals
                  System.out.println("Hva er fornavnet ditt?\n");
                  String fornavn = br.readLine();
                  System.out.println("Hva er etternavnet ditt?\n");
                  String etternavn = br.readLine();
                  System.out.println("Hva er epostadresseen din?\n");
                  String epost = br.readLine();
                  System.out.println("Lag et passord!\n");
                  String passord = br.readLine();
                  Personals newPersonals = new Personals(fornavn, etternavn, epost, passord);

                  //Henter ut informasjon til å lage LocalDate, ville i et faktisk program bare hentet ut datoen som var når medlemmet blir registrert
                  //men for testing og demonstrering så ber jeg brukeren skrive det inn
                  System.out.println("Hvilket år er det?");
                  String year = br.readLine();
                  System.out.println("Hvilken måned er det?");
                  String month = br.readLine();
                  System.out.println("Hvilken dag er det?");
                  String day = br.readLine();
                  LocalDate enrolled = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));

                  //Lager nytt BasicMember, og legger det til i arkivet
                  System.out.println("Medlemsnummer: " + memberArchive.newMember(newPersonals, enrolled));
                }
                catch (Exception e)
                {
                  System.out.println("Det skjedde en feil!");
                }
                break;

            case FIND_POINTS:
              try
              {
                System.out.println("Hva er medlemsnummeret ditt?");
                String medlemsnummer = br.readLine();
                System.out.println("Hva er passordet ditt?");
                String passord = br.readLine();

                if (memberArchive.findPoint(Integer.parseInt(medlemsnummer), passord) == -1)
                {
                  System.out.println("Medlemsnummer eller passord er feil!");
                }
                else
                {
                  System.out.println("Poeng: " + memberArchive.findPoint(Integer.parseInt(medlemsnummer), passord));
                }
              }
              catch (Exception e)
              {
                System.out.println("Noen av variabelene var oppgitt feil");
              }
              break;

            case REGISTER_POINTS:
              try
              {
                System.out.println("Hva er medlemsnummeret ditt?");
                String medlemsnummer = br.readLine();
                System.out.println("Hvor mange poeng skal registreres?");
                String nyePoeng = br.readLine();

                if (memberArchive.registerPoints(Integer.parseInt(medlemsnummer), Integer.parseInt(nyePoeng)))
                {
                  System.out.println("Poeng registrert!");
                }
                else
                {
                  System.out.println("Poengene ble ikke registrert!");
                }
              }
              catch (Exception e)
              {
                System.out.println("Noen av variabelene var oppgitt feil");
              }
              break;

            case CHECK_MEMBERS:
              try
              {
                System.out.println("Hvilket år er det?");
                String year = br.readLine();
                System.out.println("Hvilken måned er det?");
                String month = br.readLine();
                System.out.println("Hvilken dag er det?");
                String day = br.readLine();
                LocalDate enrolled = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));

                memberArchive.checkMembers(enrolled);
                System.out.println(memberArchive.toString());
              }
              catch (Exception e)
              {

              }
              break;

            case ALL_INFO:
              try
              {
                System.out.println(memberArchive.toString());
              }
              catch (Exception e)
              {

              }
              break;

            case EXIT:
              try
              {
                finished = false;
                System.out.println("\nAvsluttet");
              }
              catch (Exception e)
              {

              }
              break;

            default:
                System.out.println("Ukjent menyvalg..." + "\n");
                break;
        }
      }
  }
}
