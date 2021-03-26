import java.time.LocalDate;

/**
 * Represents a bonus member at basic level.
 */
public class BasicMember extends BonusMember {
  /**
   * Constructs an instance of BasicMember.
   *
   * @param memberNo     member number
   * @param personals    personals information
   * @param enrolledDate date enrolled as a member
   */
  public BasicMember(int memberNo, Personals personals, LocalDate enrolledDate) {
    super(memberNo, personals, enrolledDate);
  }

  @Override
  public void registerPoints(int newPoints) {
    this.addPoints(newPoints);
  }

}
