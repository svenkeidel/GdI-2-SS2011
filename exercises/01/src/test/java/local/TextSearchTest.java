package local;

import org.junit.Test;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.apache.log4j.*;


/**
 * Unit test for simple App.
 */
public class TextSearchTest
{
	private static Logger logger = Logger.getRootLogger();

	@BeforeClass 
	public static void ClassInit() {
		BasicConfigurator.configure();
		logger.setLevel(Level.OFF);
	}

    /**
     */
	@Test
    public void testTextMatch()
    {
		int result = TextSearch.textSearch("Sven", "Hello, my name is Sven Keidel");
		Assert.assertFalse("The string wasn't found, return value was -1", result == -1);
		Assert.assertEquals(
				"The string matched at the wrong position. "+
				"The return Value was "+result, 18, result);
    }

	@Test
	public void testStringNotMatched()
	{
		int result = TextSearch.textSearch("Bernd", "Hello, my name is Sven Keidel");
		Assert.assertEquals("The string was wrongly detected, return value was "+result, -1, result);
	}
}
