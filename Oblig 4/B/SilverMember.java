import java.time.LocalDate;

/**
* Represents a bonus member at silver level.
*/
public class SilverMember extends BonusMember {

  // Constant defining the bonus point factor
  public static final float FACTOR_SILVER = 1.2F;
  
  /**
  * Constructs an instance of SilverMember.
  *
  * @param memberNo     member number
  * @param personals    personals information
  * @param enrolledDate date enrolled as a member
  * @param points initial points at the start of the silver membership
  */
  public SilverMember(int memberNo, Personals personals, LocalDate enrolledDate, int points) {
    super(memberNo, personals, enrolledDate);
    // Call the super-version of register points, since the member should not
    // get the extra factor on already earned points.
    this.addPoints(points);
  }

  @Override
  public void registerPoints(int newPoints) {
    int pointsToAdd = Math.round(newPoints * FACTOR_SILVER);
    this.addPoints(pointsToAdd);
  }
}
