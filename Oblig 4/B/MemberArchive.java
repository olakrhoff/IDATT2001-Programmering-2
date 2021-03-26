import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;


/**
 * Holds the archive of all bonus members.
 */
public class MemberArchive implements Iterable<BonusMember> {

  // Constants defining the bonus point limits for becoming silver- and gold members
  public static final int SILVER_LIMIT = 25000;
  public static final int GOLD_LIMIT = 75000;

  // Since members have a unique member number, HashMap is ideal for
  // storing the members. Use member number for key
  private HashMap<Integer, BonusMember> members;

  // Random number generator to be used for member numbers
  private static final Random randomGenerator = new Random();

  /**
   * Constructs a member archive with no members.
   */
  public MemberArchive() {
    this.members = new HashMap<>();
  }

  /**
   * Returns the number of bonus points earned by the member whos memebr number
   * and password is given by the parameters.
   * If no member is found matching the member number, or
   * the password is wrong, -1 is returned.
   *
   * @param memberNumber the member number of the member to look up
   * @param password     the password of the member to look up
   * @return earned bonus points of the member in question.
   *        -1 if no member found matching the membernumber or if wrong password.
   */
  public int findPoints(int memberNumber, String password) {
    int pointsFound;

    BonusMember foundMember = findMember(memberNumber);

    if ((foundMember == null) || !foundMember.okPassword(password)) {
      pointsFound = -1;
    } else {
      pointsFound = foundMember.getPoints();
    }
    return pointsFound;
  }

  /**
   * Register new bonus points on the member given by the parameter.
   * If the registering of bonus points was successfull, <code>true</code>
   * is returned. If not successfull, due to no members
   * matching the member number, <code>false</code> is returned.
   *
   * @param memberNumber the member number of the member ot register bonus points on
   * @param bonusPoints  bonus points to register
   * @return <code>true</code> if successfully registered, <code>false</code>
   *        not successful.
   */
  public boolean registerPoints(int memberNumber, int bonusPoints) {
    boolean success = false;
    BonusMember member = this.findMember(memberNumber);
    if (member != null) {
      member.registerPoints(bonusPoints);
      success = true;
    }

    return success;
  }

  /**
   * Adds a new member to the archive with the personals and enrollment date
   * given by the parameters. The new member is registered as a Basic Member.
   * The member is given a unique random member number.
   * The member number is returned.
   *
   * @param pers         personals of the memebr to add
   * @param dateEnrolled the enrollment date of the membership
   * @return the assigned membership number
   */
  public int addMember(Personals pers, LocalDate dateEnrolled) {
    int memberNo = this.findAvailableNo();
    BasicMember basicMember = new BasicMember(memberNo, pers, dateEnrolled);
    this.members.put(memberNo, basicMember);
    return memberNo;
  }

  /**
   * Iterates through all the members in the archive and upgrades the membership
   * if the member qualifies for upgrade.
   * A member qualifies for upgrade if he enrolled less than 365 days ago,
   * and have earned enough bonuspoints (25 000 for silver, 75 000 for gold)
   *
   * @param currentDate the date to check against
   */
  public void checkMembers(LocalDate currentDate) {

    for (BonusMember member : this.members.values()) {
      // Handle the basic members
      if (member instanceof BasicMember) {
        if (member.findQualificationPoints(currentDate) >= GOLD_LIMIT) {
          GoldMember goldMember = new GoldMember(
              member.getMemberNo(),
              member.getPersonals(),
              member.getEnrolledDate(),
              member.getPoints());
          this.members.replace(goldMember.getMemberNo(), goldMember);
        } else if (member.findQualificationPoints(currentDate) >= SILVER_LIMIT) {
          SilverMember silverMember = new SilverMember(
              member.getMemberNo(),
              member.getPersonals(),
              member.getEnrolledDate(),
              member.getPoints());
          this.members.replace(silverMember.getMemberNo(), silverMember);
        }
      }

      // Handel silver members
      if ((member instanceof SilverMember)
          && (member.findQualificationPoints(currentDate) >= GOLD_LIMIT)) {
        GoldMember goldMember = new GoldMember(
            member.getMemberNo(),
            member.getPersonals(),
            member.getEnrolledDate(),
            member.getPoints());
        this.members.replace(goldMember.getMemberNo(), goldMember);
      }

    }
  }

  /**
   * Returns the member matching the membernumber given by the parameter.
   * If no member found, <code>null</code> is returned.
   *
   * @param memberNumber the member number of the member to search for
   * @return the member matching the member number.
   *        If no member found, <code>null</code> is returned.
   */
  public BonusMember findMember(int memberNumber) {
    return this.members.get(memberNumber);
  }

  /**
   * Generates a new unique membership number. The number can be any number
   * between 1 and Interger.MAX_VALUE.
   *
   * @return a unique random membership number.
   */
  private int findAvailableNo() {
    int newMembershipNumber = 0;
    boolean foundAvailabelNumber = false;

    while (!foundAvailabelNumber || (newMembershipNumber == 0)) {
      newMembershipNumber = randomGenerator.nextInt(Integer.MAX_VALUE);
      if (!this.members.containsKey(newMembershipNumber)) {
        foundAvailabelNumber = true;
      }
    }
    return newMembershipNumber;
  }

  /**
   * Returns the member archive as a Collection.
   *
   * @return the member archive as a Collection.
   */
  public Collection<BonusMember> getArchive() {
    return this.members.values();
  }

  @Override
  public Iterator<BonusMember> iterator() {
    return this.members.values().iterator();
  }

  /**
   * Returns the number of members in the archive.
   *
   * @return the number of members in the archive
   */
  public int getNumberOfMembers() {
    return this.members.size();
  }


}
