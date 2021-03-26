/**
 * This main-class is used to make a simple test of the functionality of the MemberArchive class
 * and the classes BasicMember, SilverMember and GoldMember.
 */
public class Main {
  /**
   * The main- and only method of the class. Tests the MemberArchive functionality.
   * @param args Commandline arguments - not used by this application
   */
  public static void main(String[] args) {
    // Start the user interface
    BonusMemberUI bonusMemberUI = new BonusMemberUI();

    bonusMemberUI.init();
    bonusMemberUI.start();
  }
}
