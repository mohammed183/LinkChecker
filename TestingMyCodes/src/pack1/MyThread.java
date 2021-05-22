package pack1;


/*
 * Thread class extension to implement runnable and add new thread constructor
 * that takes URL and its text to validate it using validateUrl from LinkValidator class
 */
public class MyThread extends Thread{
	private String URL;
	private String urlText;
	
	private static int maxThreadCount ;		//max number of threads can be used in the device
	private static int threadCount = 0;		//number of threads the user wants to use
	
	LinkValidator check = new LinkValidator(); //LinkValidator object to be used inside the thread
	
	//Constructor
	MyThread( String URL, String urlText){
			this.URL = URL;
			this.urlText = urlText;
		}
	
	//Implementing run to validate URL given
	public void run() {
		try {
			check.validateUrl(URL, urlText);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Number of threads getter
	public static int getThreadCount() {
		return threadCount;
	}

	//Number of threads setter
	public static void setThreadCount(int threadCount) {
		MyThread.threadCount = threadCount;
	}
	
	//Max number of threads setter
	public static void setMaxThreadCount(int maxThreadCount) {
		MyThread.maxThreadCount = maxThreadCount;
	}
	
	//Max number of threads getter
	public static int getMaxThreadCount() {
		return maxThreadCount;
	}
}
