package local;

import org.apache.log4j.*;

public class TextSearch {
	private static Logger logger = Logger.getRootLogger();

	private static boolean matchAtStart(String searchee, String page) {
		for (int i=0; i<searchee.length(); i++)
			if(searchee.charAt(i) != page.charAt(i))
				return false;
		return true;
	}

	/**
	 * A method to match string in a text.
	 *
	 * The function described in pseudocode:
	 * <pre>{@code
	 * function textSearch(str, cont):
	 * 	if [ string matches at the start of the context ]
	 * 		return 0
	 * 	else if [ string length is longer then the context length ]
	 * 		return -1
	 * 	else
	 * 		shorten one char at end ( cont )
	 * 		ret := textSearch(str, cont)
	 * 		return -1 if ret = -1, else ret + 1
	 * }</pre>
	 * 
	 * @param searchee the string to search
	 * @param page the context in wich the string is searched
	 * @return the position at wich the string matches. For Example, if
	 * the searched string is "foo" and the context is "barfoobar" the
	 * function returns 3
	 */
	public static int textSearch(String searchee, String page) {
		if (matchAtStart(searchee, page)) {
			logger.debug("textSearch( \""+searchee+"\", \""+page+"\" ) => 0");
			return 0;
		} else if (searchee.length() > page.length()){
			logger.debug("textSearch( \""+searchee+"\", \""+page+"\" ) => -1");
			return -1;
		} else {
			logger.debug("textSearch( \""+searchee+"\", \""+page+"\" ) => ");
			int retval = textSearch(searchee, page.substring(1,page.length()));
			logger.debug("textSearch( \""+searchee+"\", \""+page+"\" ) => "+(retval==-1?-1:retval+1));
			return retval == -1 ? -1 : retval + 1;
		}
	}
}
