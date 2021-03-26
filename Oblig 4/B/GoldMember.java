import java.time.LocalDate;

/**
 * Represents a bonus member at gold level.
 */
public class GoldMember extends BonusMember {

  // Constant defining the bonus point factor
  public static final float FACTOR_GOLD = 1.5F;

  /**
   * Constructs an instance of GoldMember.
   *
   * @param memberNo     member number
   * @param personals    personals information
   * @param enrolledDate date enrolled as a member
   * @param points initial points at the start of the gold membership
   */
  public GoldMember(int memberNo, Personals personals, LocalDate enrolledDate, int points) {
    super(memberNo, personals, enrolledDate);
    // Call the super-version of register points, since the member should not
    // get the extra factor on already earned points.
    this.addPoints(points);
  }

  @Override
  public void registerPoints(int newPoints) {
    int pointsToAdd = Math.round(newPoints * FACTOR_GOLD);
    this.addPoints(pointsToAdd);
  }
}
