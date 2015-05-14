import junit.framework.TestCase;


public class ItemsTest extends TestCase {

	
	public void testEnumSize_Values() throws Exception {
		assertEquals(3,Items.values().length);
		assertEquals("CANDY",Items.CANDY.toString());
		assertEquals("SODA",Items.SODA.toString());
		assertEquals("CHIPS",Items.CHIPS.toString());
	}
}
