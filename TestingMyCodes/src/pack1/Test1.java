package pack1;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

//	Test Link: https://mo7am0.github.io/

/*
 * This is the main class where the first GUI interface is launched
 * and the program starts where user enters link, depth and number of threads.
 * this class also contains readLinks which is responsible for the multi threading in the program. 
 */

public class Test1 {
	//User Inputs
	private static String url = "";
	private static int indepth = 0;
	private static int noOfThreads;
	
	//Variables used inside method and time variable
	private static int noOfAllLinks = 1;
	private static double time;

	public static void main(String[] args) throws InterruptedException {
		//setting max number of threads available for the user device
		MyThread.setMaxThreadCount(Runtime.getRuntime().availableProcessors());
		//Creating GUI objects
		Gui1 readWindow = new Gui1();
		LoadingScreen loadWindow = new LoadingScreen();

		//Launching first GUI until getClick returns true which means user entered valid inputs
		while (!readWindow.getClick()) {
			readWindow.setVisible(true);
		}
		
		//Closing first frame and opening second frame
		readWindow.setVisible(false);
		loadWindow.setVisible(true);
		
		//Get Start time
		long startTime = System.nanoTime();
		
		//Calling method to start validation process
		readLinks(indepth, url);
		MyThread.setThreadCount(noOfThreads);
		
		//extra console for data if needed
//		LinkValidator.printValid();
//		LinkValidator.printInvalid();
//		System.out.println("\n\nThreads: " + MyThread.getThreadCount() + "\nNumber of all links: " + noOfAllLinks);
//		System.out.println("MAX ALLOWED THREADS: " + MyThread.getMaxThreadCount());
		
		//Closing Loading frame
		loadWindow.setVisible(false);

		//Get End time
		long endTime = System.nanoTime();
		//Get time
		time = (double) (endTime - startTime) / 1_000_000_000;

		//Opening data window with all required data to user
		DataWindow DataWindow = new DataWindow();
		DataWindow.addLink(LinkValidator.getValidURLS(), LinkValidator.getInvalidURLS());
		DataWindow.setVisible(true);

	}

	public static void readLinks(int depth, String URL) throws InterruptedException {
		//Ending condtion for recursive function
		if (depth < 0)
			return;
		
		//Creating fixed threadpool with given number of threads
		ExecutorService es = Executors.newFixedThreadPool(noOfThreads);
		
		//if first time inside validate input URL
		if(depth == indepth)
			es.execute(new MyThread(URL,"Entered Link"));

		//Getting html document using jsoup
		Document doc = null;
		try {
			doc = Jsoup.connect(URL).get();
		} catch (IOException e) {
//			System.out.println("can not reach the URL!!");
			return;
		}
		
		//Storing links inside document in Elements array
		Elements links = doc.select("a[href]");

		//Increasing the total size by adding new links number
		noOfAllLinks += links.size();

		//for loop to validate all elements
		for (Element link : links) {
			es.execute(new MyThread(link.attr("abs:href"), link.text()));
		}
		//shutdown current pool
		es.shutdown();

		//wait for all threads to terminate
		while (true) {
			if (es.isTerminated())
				break;
		}

		//if depth not covered call function again for next generation links
		if (depth > 0) {
			depth--;
			for (Element link : links)
				readLinks(depth, link.attr("abs:href"));
		}
	}

	//depth setter
	public static void setDepth(int dep) {
		indepth = dep;
	}

	//URL setter
	public static void setUrl(String ur) {
		url = ur;
	}

	//Total number of links getter
	public static int noOfAllLinks() {
		return noOfAllLinks;
	}

	//time getter
	public static double time() {
		return time;
	}

	//No of threads getter
	public static int getNoOfThreads() {
		return noOfThreads;
	}

	//No of threads setter
	public static void setNoOfThreads(int noOfThread) {
		noOfThreads = noOfThread;
	}
}
