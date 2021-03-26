import java.time.LocalDate;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Makes up the text- and menu based user interface of the
 * BonusMemberApplication.
 */
public class BonusMemberUI {
  private static final String VERSION = "1.3-SNAPSHOT";

  // The Property register holding the properties
  private final MemberArchive members;

  // Constants representing the different menu choices
  private static final int ADD_MEMBER = 1;
  private static final int LIST_ALL_MEMBERS = 2;
  private static final int UPGRADE_MEMBERS = 3;
  private static final int REGISTER_BONUSPOINTS = 4;
  private static final int LIST_SOTRED_BY_POINTS = 5;
  private static final int EXIT = 9;

  /**
   * Creates an instance of the RealestateApp.
   */
  public BonusMemberUI() {
    this.members = new MemberArchive();
  }

  /**
   * Called to initialise the instance after having been created. Must be
   * called prior to calling <code>start()</code>
   */
  public void init() {
    this.fillRegisterWithMembers();
  }

  /**
   * Presents the menu for the user, and awaits input from the user. The menu
   * choice selected by the user is being returned.
   *
   * @return the menu choice by the user as a positive number starting from 1.
   *         If 0 is returned, the user has entered a wrong value
   */
  private int showMenu() {
    int menuChoice = 0;

    System.out.println("\n***** Bonus member archive Application v" + VERSION + " *****\n");
    System.out.println("1. Add basic member");
    System.out.println("2. List all members");
    System.out.println("3. Upgrade qualified members");
    System.out.println("4. Register bonus points");
    System.out.println("5. List members by bonus points");
    System.out.println("9. Quit");
    System.out.println("\nPlease enter a number between 1 and 9.\n");

    Scanner sc = new Scanner(System.in);

    if (sc.hasNextInt()) {
      menuChoice = sc.nextInt();
    } else {
      System.out.println("You must enter a number, not text");
    }
    return menuChoice;
  }

  /**
   * Starts the application. This is the main loop of the application,
   * presenting the menu, retrieving the selected menu choice from the user,
   * and executing the selected functionality.
   */
  public void start() {

    boolean finished = false;

    // The while-loop will run as long as the user has not selected
    // to quit the application
    while (!finished) {
      int menuChoice = this.showMenu();
      switch (menuChoice) {
        case ADD_MEMBER:
          this.addMember();
          break;

        case LIST_ALL_MEMBERS:
          this.listAllMembers();
          break;

        case UPGRADE_MEMBERS:
          this.upgradeMembers();
          break;

        case REGISTER_BONUSPOINTS:
          this.registerBonuspoints();
          break;

        case LIST_SOTRED_BY_POINTS:
          this.listSortedByPoints();
          break;

        case EXIT:
          System.out.println("Thank you for using the Bonus Member Archive app!\n");
          finished = true;
          break;

        default:
          System.out.println("Unrecognized menu selected..");
          break;
      }
    }
  }



  /**
   * Requests data about the member to register,
   * and add the new member to the register as a
   * Basic Member.
   * Most validation of input from the user has deliberately been omitted
   * for simplicity.
   */
  private void addMember() {


    System.out.println("\nAdd a new basic member");
    Scanner reader = new Scanner(System.in);

    System.out.println("Please enter...");
    System.out.println("First name : ");
    String firstName = reader.nextLine();

    System.out.println("Last name  : ");
    String lastName = reader.nextLine();

    System.out.println("E-mail address : ");
    String email = reader.nextLine();

    System.out.println("Password : ");
    String password = reader.nextLine();

    int year = 0;
    int month = 0;
    int day = 0;

    boolean dateValidSoFar = true;
    // Get the date enrolled.
    if (reader.hasNextInt()) {
      year = reader.nextInt();
      reader.nextLine();  //To get rid of the trailing carriage return
    } else {
      System.out.println("You have to enter a valid year. Please start over.");
      dateValidSoFar = false;
    }

    if (dateValidSoFar) {
      System.out.print("Month : ");
      if (reader.hasNextInt()) {
        month = reader.nextInt();
        reader.nextLine();  //To get rid of the trailing carriage return
      } else {
        System.out.println("You have to enter a valid month. Please start over.");
        dateValidSoFar = false;
      }
    }

    if (dateValidSoFar) {
      System.out.print("Day : ");
      if (reader.hasNextInt()) {
        day = reader.nextInt();
        reader.nextLine();  //To get rid of the trailing carriage return
      } else {
        System.out.println("You have to enter a valid day. Please start over.");
        dateValidSoFar = false;
      }
    }

    if (dateValidSoFar) {
      Personals personals = new Personals(firstName, lastName, email, password);

      this.members.addMember(personals, LocalDate.of(year, month, day));
    }
  }

  /**
   * Lists all members in the member archive.
   */
  private void listAllMembers() {
    System.out.println("\nList of members: \n");

    if (this.members.getNumberOfMembers() > 0) {

      for (BonusMember member : this.members) {
        this.displayBonusMemberDetails(member);
      }
    } else {
      System.out.println("There are no members in the member archive.");
    }

  }


  /**
   * Executes upgrading of members. This will go through all
   * members in the archive and upgrade members that qualify for
   * an upgrade to Silver- og Gold- member.
   * This method has a limited set ov validation of input from the
   * user. For a better user experience, a lot more effort should have
   * been made to ensure that wrong data cannot be entered.
   */
  private void upgradeMembers() {
    int year = 0;
    int month = 0;
    int day = 0;

    boolean inputValidSoFar = true;

    System.out.println("\nUpgrade members.");
    System.out.println("Please enter the date for upgrade:");
    System.out.print("Year : ");
    Scanner reader = new Scanner(System.in);
    if (reader.hasNextInt()) {
      year = reader.nextInt();
      reader.nextLine();  //To get rid of the trailing carriage return
    } else {
      System.out.println("You have to enter a valid year. Please start over.");
      inputValidSoFar = false;
    }

    if (inputValidSoFar) {
      System.out.print("Month : ");
      if (reader.hasNextInt()) {
        month = reader.nextInt();
        reader.nextLine();  //To get rid of the trailing carriage return
      } else {
        System.out.println("You have to enter a valid month. Please start over.");
        inputValidSoFar = false;
      }
    }

    if (inputValidSoFar) {
      System.out.print("Day : ");
      if (reader.hasNextInt()) {
        day = reader.nextInt();
        reader.nextLine();  //To get rid of the trailing carriage return
      } else {
        System.out.println("You have to enter a valid day. Please start over.");
        inputValidSoFar = false;
      }
    }

    if (inputValidSoFar) {
      // Run upgrade of members:
      this.members.checkMembers(LocalDate.of(year, month, day));
    }
  }

  /**
   * Registers bonus points on a member defined/selected
   * by the user. A list of all members is presented, and the
   * user is asked to provide the member number of the member
   * and the points to register.
   */
  private void registerBonuspoints() {
    System.out.println("\nRegister bonus points :");
    this.listAllMembers();
    System.out.print("Please enter member number: ");

    Scanner reader = new Scanner(System.in);
    if (reader.hasNextInt()) {
      int memberNo = reader.nextInt();
      reader.nextLine();

      System.out.print("Please enter bonus points : ");
      if (reader.hasNextInt()) {
        int bonusPoints = reader.nextInt();
        reader.nextLine();
        if (this.members.registerPoints(memberNo, bonusPoints)) {
          System.out.println("SUCCESS: " + bonusPoints + " points"
                + " registered on member number " +  memberNo);
        } else {
          System.out.println("No member found with member number " + memberNo);
        }
      } else {
        System.out.println("Bonus points must be a number...");
      }
    } else {
      System.out.println("The member number must be a number..Please start over.");
    }
  }

  /**
   * Lists the members sorted by the bonus points, listing the
   * member with least points first.
   */
  private void listSortedByPoints() {
    this.members.getArchive()
        .stream()
        .sorted()
        .forEach(this::displayBonusMemberDetails);
    // Alternative lambda:
    // .forEach((BonusMember bm) -> this.displayBonusMemberDetails(bm));
  }

  /**
   * Displays details of the bonus member provided in the parameter.
   *
   * @param member the member to display
   */
  private void displayBonusMemberDetails(BonusMember member) {
    if (member instanceof BasicMember) {
      this.displayBasicMemberDetails((BasicMember) member);
    }

    if (member instanceof SilverMember) {
      this.displaySilverMemberDetails((SilverMember) member);
    }

    if (member instanceof GoldMember) {
      this.displayGoldMemberDetails((GoldMember) member);
    }
  }


  /**
   * Displays details of the basic member provided in the parameter.
   *
   * @param member the member to display
   */
  private void displayBasicMemberDetails(BasicMember member) {
    System.out.println("Member No     : " + member.getMemberNo());
    System.out.println("Name          : " + member.getPersonals().getSurname()
        + ", " + member.getPersonals().getFirstname());
    System.out.println("Points        : " + member.getPoints());
    System.out.println("Date enrolled : " + member.getEnrolledDate());
    System.out.println("Membership    : BASIC");
    System.out.println();
  }

  /**
   * Displays details of the silver member provided in the parameter.
   *
   * @param member the member to display
   */
  private void displaySilverMemberDetails(SilverMember member) {
    System.out.println("Member No     : " + member.getMemberNo());
    System.out.println("Name          : " + member.getPersonals().getSurname()
        + ", " + member.getPersonals().getFirstname());
    System.out.println("Points        : " + member.getPoints());
    System.out.println("Date enrolled : " + member.getEnrolledDate());
    System.out.println("Membership    : SILVER");
    System.out.println();
  }

  /**
   * Displays details of the member provided in the parameter.
   *
   * @param member the member to display
   */
  private void displayGoldMemberDetails(GoldMember member) {
    System.out.println("Member No     : " + member.getMemberNo());
    System.out.println("Name          : " + member.getPersonals().getSurname()
        + ", " + member.getPersonals().getFirstname());
    System.out.println("Points        : " + member.getPoints());
    System.out.println("Date enrolled : " + member.getEnrolledDate());
    System.out.println("Membership    : GOLD");
    System.out.println();
  }

  /**
   * Fills the member archive with a set of test-members,
   * for testing and debugging purposes.
   */
  private void fillRegisterWithMembers() {
    // Add some Basic Members, and register points. Some of the members
    // will be registered with points that qualify them for Silver og Gold
    Personals pers1 = new Personals("Arne", "Styve", "asdf@gmail.no", "qwerty");
    Personals pers2 = new Personals("Ole", "Jensen", "ole@gmail.no", "ytrewq");
    Personals pers3 = new Personals("Lise", "Pedersen", "lise@gmail.no", "zaq123");
    Personals pers4 = new Personals("Kari", "Lid", "kari@gmail.no", "123qaz");
    Personals pers5 = new Personals("Leif", "Øvrebust", "leif@gmail.no", "jadda");

    // Add all persons as members in the archive
    int memberNo1 = this.members.addMember(pers1, LocalDate.of(2019, 3, 10));
    this.members.registerPoints(memberNo1, 10000);

    int memberNo2 = this.members.addMember(pers2, LocalDate.of(2019, 3, 10));
    this.members.registerPoints(memberNo2, 30000);

    int memberNo3 = this.members.addMember(pers3, LocalDate.of(2019, 3, 10));
    this.members.registerPoints(memberNo3, 80000);

    int memberNo4 = this.members.addMember(pers4, LocalDate.of(2019, 3, 10));
    this.members.registerPoints(memberNo4, 77000);

    int memberNo5 = this.members.addMember(pers5, LocalDate.of(2019, 3, 10));
    this.members.registerPoints(memberNo5, 35000);
  }

}
