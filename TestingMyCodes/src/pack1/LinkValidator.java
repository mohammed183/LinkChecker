package pack1;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
 * This class is used to validate the given URL by establishing an HTTP connection
 * and getting a status response code by which we can know
 * the status of this page
*/

public class LinkValidator {
	
	//	Static variables to store data that will be printed for the user 
	private static String invalidURLS = "";
	private static String validURLS = "";
	private static int numOfValid = 0;
	private static int numOfInvalid = 0;

	public boolean validateUrl(String url, String urlText) throws Exception {	
			//Adding code in try catch to prevent MalformedURLException if URL protocol is Invalid
			try {
				URL myURL = new URL(url);		//creating URL object to be sent HTTP object to establish connection
				HttpURLConnection myConnection = (HttpURLConnection) myURL.openConnection();		//Connecting URL with protocol used by javax.net
				
				//if HTTP response is 200 "OK", then URL1  is valid
				if (myConnection.getResponseCode() == 200) {
					addToValid(url, urlText);
					return true;
				} else {
					addToInvalid(url, urlText);
					return false;
				}
			} catch (Exception e) {
				//if something wrong with URL add it to invalid
				addToInvalid(url,urlText);
				return false;
			}		
	}
	
	//method to verify if URL is written correctly
	public static boolean verifyUrl(String url, String text) {
		//using regular expression to validate it
		String urlRegex = "^(http|https)://[-a-zA-Z0-9+&@#/%?=~_|,!:.;]*[-a-zA-Z0-9+@#/%=&_|]";
		Pattern pattern = Pattern.compile(urlRegex);
		Matcher m = pattern.matcher(url);
		if (m.matches()) {
			return true;
		} else {
			return false;
		}
	}

	//Method to concatenate Valid string with valid URLs and their text
	private void addToValid(String validURL, String text) {
		validURLS = validURLS + validURL + "\n" + text + "\n";
		numOfValid++;
	}
	
	//Method to concatenate invalid string with valid URLs and their text
	private static void addToInvalid(String invalidURL, String text) {
		invalidURLS = invalidURLS + invalidURL + "\n" + text + "\n";
		numOfInvalid++;
	}
	
	//extra method to print data in console (Invalid)
	public static void printInvalid() {
		System.out.println("*****************Invalid***********\n\n" + invalidURLS + "\n\n********Number: " + numOfInvalid + "*********");
	}
	
	//extra method to print data in console (Valid)
	public static void printValid() {
		System.out.println("*****************Valid***********\n\n" + validURLS + "\n\n********Number: " + numOfValid + "*********");
	}

	//Number of valid links getter
	public static int getValidNum() {
		return numOfValid;
	}

	//Number of invalid links getter
	public static int getInvalidNum() {
		return numOfInvalid;
	}
	
	//Valid URLs String getter
	public static String getValidURLS() {
		return validURLS;
	}
	
	//Invalid URLs String getter
	public static String getInvalidURLS() {
		return invalidURLS;
	}
}
