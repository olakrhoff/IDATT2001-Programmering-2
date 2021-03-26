class BonusMemberTest
{

  public static void main(String[] args)
  {
    testStart();
    BonusMemberTest k = new BonusMemberTest();
    k.testInvalidParametersInConstructor();
    testEnd();
  }

  /**
   * prints "Test started", for orginazing sake
   * @return void
   */
  public static void testStart()
  {
    System.out.println("Test started");
  }

  /**
   * pints "Test ended", for orginazing sake
   * @return void
   */
  public static void testEnd()
  {
    System.out.println("Test ended");
  }

  /**
   * Creates a BonusMember and sends in parameters which should be illegal.
   * If the test cathces it it states "Test success", if the program overlooks it
   * then we print "Test failed".
   * @return void
   */
  public void testInvalidParametersInConstructor()
  {
    try
    {
      BonusMember bm = new BasicMember(12, null, null);
      System.out.println("Test failed");
    }
    catch (IllegalArgumentException e)
    {
      System.out.println("Test success");
      //Do not need to add anything here, since if the Exception is thrown, the //test is an success
    }
  }
}
