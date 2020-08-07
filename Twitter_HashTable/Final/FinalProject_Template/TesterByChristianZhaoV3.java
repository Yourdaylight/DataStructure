package FinalProject_Template;

//						This is a tester for the COMP250 final project by Christian Zhao.
/* 
 * READ THIS ! ! !
 * Before you run the tester,
 * please install the e(fx)clipse plug-in from Help -> Eclipse Marketplace -> Find e(fx)clipse.
 * 
 * Tester includes: 
 * 	- 30 test cases for MyHashTable (on everything except the iterator, unless you use it in your methods)
 * 	- a line graph comparing the time performance by slowSort and fastSort
 * 	- 13 test cases for Twitter
 * 
 * This tester only tests on basic functionalities. Passing all tests does not guarantee a high grade!
 * You can add your own test cases by adding methods. See template
 * 
 */

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Random;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import java.util.concurrent.*;

public class TesterByChristianZhaoV3 extends Application
{
	public static void main(String[] args)
	{
		TesterByChristianZhaoV3 t = new TesterByChristianZhaoV3();
		t.test_put_get();
		t.test_remove();
		t.test_rehash();
		t.test_keys();
		t.test_values();
		t.test_fastSort();
		t.test_latestTweetByAuthor();
		t.test_tweetsByDate();
		t.test_trendingTopics();
		t.reportTotal();
		
		//delete the part below if you don't want the graph generated
		
		//change these two values to adjust the maximum number of entries you want to sort, and the step between each two trials
		setNumEntriesAndStep(1000,20);
		print("Please wait for the sorting performance result. A large number of entries and short step may cause prolonged processing time.\n");
		launch(args);
	}
	
	int total = 0;
	int score = 0;
	static int numEntries;
	static int step;
	
	private static void print(String str)
	{
		System.out.print(str);
	}
	
	public void reportTotal()
	{
		System.out.println("Total score: "+score+"/"+total+" ("+total+" test cases in total)");
		if (score == total) System.out.println("All tests passed");
	}

	public void test_put_get()
	{
		int s = 0;
		int subTotal = 11;
		this.total += subTotal;
		print("Testing for put() and get() ...\n");
		try
		{
			MyHashTable t = new MyHashTable(10);
			
			t.put(3, "Christian");
			if (t.get(3).equals("Christian")) s++;
			else print("Error: failed when key does not exist\n");
			
			if (t.size() == 1) s++;
			else print("Error: incorrect numEntries after put() when key does not exist\n");
			
			if (t.put(3, 1).equals("Christian")) s++;
			else print("Error: put() failed when key already exists\n");
			
			if (t.get(3).equals(1)) s++;
			else print("Error: get() failed when key already exists\n");
			
			if (t.size() == 1) s++;
			else print("Error: incorrect numEntries after put() when key already exists\n");
			
			t = new MyHashTable(2);
			t.put(0, "a");
			t.put(1, "b");
			if (t.size()/t.numBuckets() <= 0.75) s++;
			else print("Error: put() does not rehash when needed\n");
			
			ArrayList<Integer> arr1 = new ArrayList<Integer>();
			arr1.add(1); arr1.add(3);
			ArrayList<Integer> arr2 = new ArrayList<Integer>();
			arr2.add(1); arr2.add(3);
			t.put(arr1, 'a');
			if (t.put(arr2, 'b').equals('a')) s++;
			else print("Error: failed when key is not primitive type\n");
			
			t = new MyHashTable(2);
			for (int i=0; i<10; i++)
			{
				t.put(i,i);
			}
			if (t.numBuckets() == 16) s++;
			else print("Error: put() does not rehash correctly");
			if (t.size() == 10) s++;
			else print("Error: incorrect numEntries after multiple entries are added\n");
			
			try
			{
				t = new MyHashTable(0);
				t.put(1, 1);
				s++;
			}
			catch (ArithmeticException e)
			{
				System.out.println("Exception found: " + e.toString());
				e.printStackTrace();
				print("Error: failed to put when numBuckets is zero\n");
			}
			
			try
			{
				t = new MyHashTable(0);
				t.get(1);
				s++;
			}
			catch (ArithmeticException e)
			{
				System.out.println("Exception found: " + e.toString());
				e.printStackTrace();
				print("Error: failed to get when numBuckets is zero\n");
			}
		
		}
		catch (Exception e)
		{
			System.out.println("Exception found: " + e.toString());
			e.printStackTrace();
		}
		finally
		{
			this.score += s;
			print("Score for put() and get(): " + s + "/" + subTotal + "\n----------------------------------\n");
		}
		
	}
	
	public void test_remove()
	{
		int s = 0;
		int subTotal = 7;
		this.total += subTotal;
		print("Testing for remove() ...\n");
		try
		{
			MyHashTable t = new MyHashTable(10);
			t.put("3", 3);
			t.put("Hi", 4);
			t.put(1, "Hi");
			
			if (t.remove(3) == null) s++;
			else print("Error: incorrect return value when key does not exist\n");
			
			if (t.size() == 3) s++;
			else print("Error: incorrect numEntries after removing a key that does not exist\n");
			
			if (t.remove("Hi").equals(4)) s++;
			else print("Error: incorrect return value when key exists\n");
			
			if (t.size() == 2) s++;
			else print("Error: incorrect numEntries after removing a existent key\n");
			
			if (t.get("Hi") == null) s++;
			else print("Error: the pair is still in the hash table after removing it\n");
			
			t = new MyHashTable(2);
			for (int i=0; i<10; i++)
			{
				t.put(i,i);
			}
			t.remove(1); t.remove(2); t.remove(100);
			if (t.size() == 8) s++;
			else print("Error: incorrect numEntries\n");
			
			try
			{
				t = new MyHashTable(0);
				t.put(1, 1);
				s++;
			}
			catch (ArithmeticException e)
			{
				System.out.println("Exception found: " + e.toString());
				e.printStackTrace();
				print("Error: failed to remove when numBuckets is zero\n");
			}
		}
		catch (Exception e)
		{
			System.out.println("Exception found: " + e.toString());
			e.printStackTrace();
		}
		finally
		{
			this.score += s;
			print("Score for remove(): " + s + "/" + subTotal + "\n----------------------------------\n");
		}
	}
	
	public void test_rehash()
	{
		int s = 0;
		int subTotal = 4;
		this.total += subTotal;
		print("Testing for rehash() ...\n");
		try
		{
			MyHashTable<Integer, String> t = new MyHashTable<Integer, String>(10);
			t.rehash();
			if (t.numBuckets() == 20) s++;
			else print("Error: failed when rehashing a table with no entry\n");
			
			t = new MyHashTable<Integer, String>(30);
			int[] keyList = new int[22];
			ArrayList<Integer> keyAL = new ArrayList<Integer>();
			int[] rehashed = new int[22];
			ArrayList<Integer> rehashedAL = new ArrayList<Integer>();
			int i = 0;
			for (i=0; i<22; i++)
			{
				t.put((int)(Math.pow(-i,3)+Math.pow(4*i,2)), "ho");
			}
			
			// retrieve keys from the hash table using the iterator
			i=0;
			for (HashPair<Integer, String> entry : t)
			{
				keyList[i] = entry.getKey();
				keyAL.add(entry.getKey());
				i++;
			}
				
			t.rehash();
			
			// retrieve keys from the hash table after rehashing
			i=0;
			for (HashPair<Integer, String> entry : t)
			{
				rehashed[i] = entry.getKey();
				rehashedAL.add(entry.getKey());
				i++;
			}
			
			if (t.numBuckets() == 60) s++;
			else print("Error: numBuckets remains unchanged after rehashing\n");
			
			if (!Arrays.equals(keyList, rehashed)) s++;
			else print("Error: rehashing unsuccessful - same item order after rehashing\n");
			
			if (keyAL.containsAll(rehashedAL) && rehashedAL.containsAll(keyAL)) s++;
			else print("Error: rehashing unsuccessful - wrong content after rehashing\n");
			
		}
		catch (Exception e)
		{
			System.out.println("Exception found: " + e.toString());
			e.printStackTrace();
			return;
		}
		finally
		{
			this.score += s;
			print("Score for rehash(): " + s + "/" + subTotal + "\n----------------------------------\n");
		}
	}
	
	public void test_keys()
	{
		int s = 0;
		int subTotal = 2;
		this.total += subTotal;
		print("Testing for keys() ...\n");
		try
		{
			int[] keyList = new int[22];
			int[] keysRetrieved = new int[22];
			Object[] tmp;
			MyHashTable t = new MyHashTable(30);
			if (t.keys() != null && t.keys().size()==0) s++;
			else print("Error: failed when MyHashTable is empty\n");
			
			for (int i=0; i<22; i++)
			{
				t.put((int)(Math.pow(-i,3)+Math.pow(4*i,2)), "Ho");
				keyList[i] = ((int)( Math.pow(-i,3)+Math.pow(4*i,2) ));
			}
			tmp = t.keys().toArray();
			for (int i=0; i < tmp.length; i++)
			{
				keysRetrieved[i] = (int) tmp[i];
			}
			Arrays.sort(keyList);
			Arrays.sort(keysRetrieved);
			if (Arrays.equals(keyList, keysRetrieved))
				s++;
			else print("Error: incorrect keys\n");
			
		}
		catch (Exception e)
			{
				System.out.println("Exception found: " + e.toString());
				e.printStackTrace();
			}
		finally
			{
				this.score += s;
				print("Score for keys(): " + s + "/" + subTotal + "\n----------------------------------\n");
			}
	}
	
	public void test_values()
	{
		int s = 0;
		int subTotal = 3;
		this.total += subTotal;
		print("Testing for values() ...\n");
		try
		{
			MyHashTable t = new MyHashTable(30);
			int[] values = new int[11];
			int[] valuesRetrieved;
			Object[] tmp;
			if (t.values()!=null && t.values().size()==0) s++;
			else print("Error: failed when MyHashTable is empty\n");
			
			for (int i=0; i<11; i++)
			{
				t.put(i, i);
				values[i] = i;
			}
			tmp = t.values().toArray();
			valuesRetrieved = new int[tmp.length];
			for (int i=0; i<tmp.length; i++)
			{
				valuesRetrieved[i] = (int)tmp[i];
			}
			Arrays.sort(values);
			Arrays.sort(valuesRetrieved);
			if (Arrays.equals(values, valuesRetrieved)) s++;
			else print("Error: incorrect values when all values are unique\n");
			
			for (int i=0; i<22; i++)
			{
				t.put(i, i/2);
				values[i/2] = i/2;
			}
			tmp = t.values().toArray();
			valuesRetrieved = new int[tmp.length];
			for (int i=0; i<tmp.length; i++)
			{
				valuesRetrieved[i] = (int)tmp[i];
			}
			Arrays.sort(values);
			Arrays.sort(valuesRetrieved);
			if (Arrays.equals(values, valuesRetrieved)) s++;
			else print("Error: incorrect values when identical values exist\n");
		}
		catch (Exception e)
			{
				System.out.println("Exception found: " + e.toString());
				e.printStackTrace();
			}
		finally
			{
				this.score += s;
				print("Score for values(): " + s + "/" + subTotal + "\n----------------------------------\n");
			}
	}
	
	public void test_fastSort()
	{
		int s = 0;
		int subTotal = 3;
		this.total += subTotal;
		print("Testing for fastSort() ...\n");
		try
		{
			MyHashTable<Integer, Integer> t = new MyHashTable<Integer, Integer>(1400);
			try {
				if (t.fastSort(t) != null && Arrays.equals(t.slowSort(t).toArray(), t.fastSort(t).toArray())) s++;
				else print("Error: failed when MyHashTable is empty\n");
			}
			catch (Exception e)
			{
				System.out.println("Exception found: " + e.toString());
				e.printStackTrace();
				print("Error: failed when MyHashTable is empty\n");
			}
			Random rand = new Random();
			for (int i=0; i<1500; i++)
			{
				t.put(i, rand.nextInt());
			}
			long a = System.nanoTime();
			Object[] slowSorted = t.slowSort(t).toArray();
			double b = System.nanoTime() - a;
			a = System.nanoTime();
			Object[] fastSorted = t.fastSort(t).toArray();
			double c = System.nanoTime() - a;
			if (b/c > 4) s++;
			else
			{
				print("Warning: your sorting might be too slow. Try again.\n");
				print("	- Tested for how many times faster fastSort sorts given 1500 random integers\n");
				print("	- fastSort sorts approximately "+ Double.toString(((int)(b/c*1000))/1000.0) + " times faster than slowSort\n");
				print("	- The target is 4 times faster\n");
				print("	- It is normal to get this warning occasionally.\n");
			}
			if (Arrays.equals(slowSorted, fastSorted)) s++;
			else 
				{
					print("Error: incorrect sorting\n");
					print("first 10 elements of the solution: "+ Arrays.toString(Arrays.copyOfRange(slowSorted, 0, 9)) +'\n');
					print("first 10 elements of the solution: "+ Arrays.toString(Arrays.copyOfRange(fastSorted, 0, 9)) +'\n');
				}
		}
		catch (Exception e)
			{
				System.out.println("Exception found: " + e.toString());
				e.printStackTrace();
			}
		finally
			{
				this.score += s;
				print("Score for fastSort(): " + s + "/" + subTotal + "\n----------------------------------\n");
			}
	}
	
	public void test_latestTweetByAuthor()
	{
		int s = 0;
		int subTotal = 3;
		this.total += subTotal;
		print("Testing for latestTweetByAuthor() ...\n");
		try
		{
			ArrayList<Tweet> tweets = initTweetList();
	        MyHashTable<String,Tweet> tweetTable = new MyHashTable<String,Tweet>(7);
	        for (Tweet t: tweets) {
	            tweetTable.put(t.getDateAndTime(), t);
	        }
			Twitter twitter = new Twitter(initTweetList(), new ArrayList<String>());
			Tweet testTweet0 = twitter.latestTweetByAuthor("USER_989b85bb");
			
			if (testTweet0 == null || !testTweet0.getAuthor().equals("USER_989b85bb") || !testTweet0.getMessage().equals("I can be MADE into a need.")) 
	        {
	            print("Error: wrong tweet retrieved when two tweets of a user are added\n");
	        }
	        else s++;
			
			twitter.addTweet(new Tweet("USER_989b85bb","2010-03-07 15:34:46","a"));
			twitter.addTweet(new Tweet("USER_989b85bb","2010-03-05 15:34:46","b"));
			
			testTweet0 = twitter.latestTweetByAuthor("USER_989b85bb");
			
	        if (testTweet0 == null || !testTweet0.getAuthor().equals("USER_989b85bb") || !testTweet0.getMessage().equals("a")) 
	        {
	            print("Error: wrong tweet retrieved when multiple (>2) tweets of a user are added\n");
	            print(testTweet0.toString()+'\n');
	        }
	        else s++;
	        
	        try {
	        	testTweet0 = twitter.latestTweetByAuthor("nosuchuser");
	        	if (testTweet0 == null) s++;
	        	else print("Error: incorrect return value when the author has not posted any tweet\n");
	        }
	        catch (Exception e)
	        {
	        	System.out.println("Exception found: " + e.toString());
				e.printStackTrace();
				print("Error: incorrect return value when the author has not posted any tweet\n");
	        }
	        
		}
		catch (Exception e)
			{
				System.out.println("Exception found: " + e.toString());
				e.printStackTrace();
			}
		finally
			{
				this.score += s;
				print("Score for latestTweetByAuthor(): " + s + "/" + subTotal + "\n----------------------------------\n");
			}
	}
	
	public void test_tweetsByDate()
	{
		int s = 0;
		int subTotal = 3;
		this.total += subTotal;
		print("Testing for tweetsByDate() ...\n");
		try
		{
			ArrayList<Tweet> tweets = new ArrayList<Tweet>();
			tweets.add(new Tweet("USER_989b85bb","2010-04-04 15:34:46","@USER_6921e61d I can be made into one twitter superstar."));
	        tweets.add(new Tweet("USER_a75657c2","2010-04-04 00:02:54","@USER_13e8a102 They reached a compromise just on time"));
	        tweets.add(new Tweet("USER_989b85bb","2010-03-04 15:34:47","I can be MADE into a need."));
	        tweets.add(new Tweet("USER_a75657c2","2010-05-05 21:45:48","So SunChips made a bag that is 100% biodegradeable. It is about damn time somebody did."));
	        tweets.add(new Tweet("USER_ee551c6c","2010-05-05 15:40:27","drthema: Do something today that feeds your spirit and empowers you to start the week from a higher place."));
	        tweets.add(new Tweet("USER_6c78461b","2010-04-04 05:13:34","@USER_a3d59856 yes, i watched that foolery done disturbed my spirit. @USER_b1d28f26"));
	        tweets.add(new Tweet("USER_92b2293c","2010-05-05 14:00:11","@USER_5aac9e88: Let no one push u around today! Be at Peace! If u dont have restful spirit, u'll definitely have a stressful spirit"));
	        tweets.add(new Tweet("USER_75c62ed9","2010-03-07 03:35:38","@USER_cb237f7f Congrats on everything I am there in spirit my brother."));
	        tweets.add(new Tweet("USER_7f72a368","2010-05-05 07:18:22","Actions speak louder than words but feelings and spirits speak louder than anything #FACT"));
	        tweets.add(new Tweet("USER_b6cc1831","2010-03-07 04:04:37","@USER_be777094 urban spirit cafe. On Long st"));
	        ArrayList<Tweet> t1 = new ArrayList<Tweet>();
	        t1.add(tweets.get(0)); t1.add(tweets.get(1)); t1.add(tweets.get(5));
	        ArrayList<Tweet> t2 = new ArrayList<Tweet>();
	        t2.add(tweets.get(3)); t2.add(tweets.get(4)); t2.add(tweets.get(6)); t2.add(tweets.get(8));
	        
	        Twitter twitter = new Twitter(new ArrayList<Tweet>(), new ArrayList<String>());
	        for (Tweet t : tweets)
	        {
	        	twitter.addTweet(t);
	        }
	        ArrayList<Tweet> t1Result = twitter.tweetsByDate("2010-04-04");
	        if (t1.containsAll(t1Result) && t1Result.containsAll(t1)) s++;
	        else print("Error: incorrect tweets retrieved by date when tweets are added by addTweet()\n");
	        
	        twitter = new Twitter(tweets, new ArrayList<String>());
	        
	        ArrayList<Tweet> t2Result = twitter.tweetsByDate("2010-05-05");
	        if (t2.containsAll(t2Result) && t2Result.containsAll(t2)) s++;
	        else print("Error: incorrect tweets retrieved by date when tweets are added by constructor\n");
	        
	        try {
	        	t2Result = twitter.tweetsByDate("2010-06-05");
		        if (t2Result == null) s++;
		        else print("Error: incorrect return value when there are no tweets on the given date\n");
	        }
	        catch(Exception e)
	        {
	        	System.out.println("Exception found: " + e.toString());
				e.printStackTrace();
				print("Error: incorrect return value when there are no tweets on the given date\n");
	        }
	        
		}
		catch (Exception e)
			{
				System.out.println("Exception found: " + e.toString());
				e.printStackTrace();
			}
		finally
			{
				this.score += s;
				print("Score for tweetsByDate(): " + s + "/" + subTotal + "\n----------------------------------\n");
			}
	}
	
	public void test_trendingTopics()
	{
		int s = 0;
		int subTotal = 7;
		this.total += subTotal;
		print("Testing for trendingTopics() ...\n");
		try
		{
			MyHashTable t = new MyHashTable(10);
			ArrayList<String> stopWords = new ArrayList<String>();
			Twitter twitter = new Twitter(new ArrayList<Tweet>(), stopWords);
			ArrayList<String> tW = twitter.trendingTopics();
			if (tW != null && tW.size()==0) s++;
			else print("Error: wrong return value when no tweets are added");
			
			twitter = new Twitter(initTweetList(), stopWords);
			
			tW = twitter.trendingTopics();
			
			if (tW.size()==229) s++;
			else print("Error: wrong number of trending words collected\n");
			
			if (tW.get(0).equalsIgnoreCase("spirit") && tW.get(1).equalsIgnoreCase("a")
					&& tW.get(2).equalsIgnoreCase("that") && tW.get(3).equalsIgnoreCase("time"))
				s++;
			else print("Error: top 4 trending words are incorrect when no stopword is added\n");
			
			stopWords.add("that");
			twitter = new Twitter(initTweetList(), stopWords);
			tW = twitter.trendingTopics();
			if (tW.get(0).equalsIgnoreCase("spirit") && tW.get(1).equalsIgnoreCase("a")
					&& tW.get(2).equalsIgnoreCase("time") && tW.size() == 228)
				s++;
			else print("Error: incorrect trending words when a stopword is added\n");
			
			stopWords = new ArrayList<String>();
			stopWords.add("ThAt");
			twitter = new Twitter(initTweetList(), stopWords);
			tW = twitter.trendingTopics();
			if (tW.get(0).equalsIgnoreCase("spirit") && tW.get(1).equalsIgnoreCase("a")
					&& tW.get(2).equalsIgnoreCase("time") && tW.size() == 228)
				s++;
			else print("Error: incorrect trending words when a stopword with mixed case is added\n");
			
			stopWords.add("spiRit");
			stopWords.add("the");
			twitter = new Twitter(initTweetList(), stopWords);
			tW = twitter.trendingTopics();
			if (tW.get(0).equalsIgnoreCase("a") && tW.get(1).equalsIgnoreCase("time")
					&& tW.get(2).equalsIgnoreCase("i") && tW.size() == 226)
				s++;
			else print("Error: incorrect trending words when multiple stop words are added\n");
			
			
			ArrayList<String> manymanyWords = new ArrayList<String>();
			for (int i=0; i < 500000; i++)
			{
				manymanyWords.add(Integer.toString(i));
			}
			
			final Twitter toTest = new Twitter(initTweetList(), manymanyWords);
			ExecutorService executor = Executors.newCachedThreadPool();
			Callable<Object> task = new Callable<Object>() {
			   public Object call() {
			      return toTest.trendingTopics();
			   }
			};
			Future<Object> future = executor.submit(task);
			
			try
			{
				   Object result = future.get(100, TimeUnit.MILLISECONDS);
				   s++;
			} catch (TimeoutException e) 
			{
				print("Timed out after 100 milliseconds\n");
				print("Warning: Your trendingWords() is too slow when stopWords is large!\n");
			}
			
			
		}
		catch (Exception e)
			{
				System.out.println("Exception found: " + e.toString());
				e.printStackTrace();
			}
		finally
			{
				this.score += s;
				print("Score for trendingTopics(): " + s + "/" + subTotal + "\n----------------------------------\n");
			}
	}
	
		/*
	 * template
		int s = 0;
		int subTotal = ;
		this.total += subTotal;
		print("Testing for () ...\n");
		try
		{
			MyHashTable t = new MyHashTable(10);
			
			Your test cases ...
		}
		catch (Exception e)
			{
				System.out.println("Exception found: " + e.toString());
				e.printStackTrace();
			}
		finally
			{
				this.score += s;
				print("Score for (): " + s + "/" + subTotal + "\n----------------------------------\n");
			}
	 */
		
	private static ArrayList<Tweet> initTweetList() {
        ArrayList<Tweet> tweets = new ArrayList<Tweet>();
        tweets.add(new Tweet("USER_989b85bb","2010-03-04 15:34:46","@USER_6921e61d I can be made into one twitter superstar."));
        tweets.add(new Tweet("USER_a75657c2","2010-03-03 00:02:54","@USER_13e8a102 They reached a compromise just on time"));
        tweets.add(new Tweet("USER_989b85bb","2010-03-04 15:34:47","I can be MADE into a need."));
        tweets.add(new Tweet("USER_a75657c2","2010-03-07 21:45:48","So SunChips made a bag that is 100% biodegradeable. It is about damn time somebody did."));
        tweets.add(new Tweet("USER_ee551c6c","2010-03-07 15:40:27","drthema: Do something today that feeds your spirit and empowers you to start the week from a higher place."));
        tweets.add(new Tweet("USER_6c78461b","2010-03-03 05:13:34","@USER_a3d59856 yes, i watched that foolery done disturbed my spirit. @USER_b1d28f26"));
        tweets.add(new Tweet("USER_92b2293c","2010-03-04 14:00:11","@USER_5aac9e88: Let no one push u around today! Be at Peace! If u dont have restful spirit, u'll definitely have a stressful spirit"));
        tweets.add(new Tweet("USER_75c62ed9","2010-03-07 03:35:38","@USER_cb237f7f Congrats on everything I am there in spirit my brother."));
        tweets.add(new Tweet("USER_7f72a368","2010-03-07 07:18:22","Actions speak louder than words but feelings and spirits speak louder than anything #FACT"));
        tweets.add(new Tweet("USER_b6cc1831","2010-03-07 04:04:37","@USER_be777094 urban spirit cafe. On Long st"));
        tweets.add(new Tweet("USER_65006b55","2010-03-05 00:58:28","RT @USER_86e8d97f: @USER_65006b55's spirit just took a turn for the worst. Lol please."));
        tweets.add(new Tweet("USER_60b9991b","2010-03-04 22:33:23","Who on my time ever flew on spirit airlines let me kno if there decent"));
        tweets.add(new Tweet("USER_36607a99","2010-03-03 02:06:01","@USER_561fe280: Nourish your spirit with your own achievement."));
        tweets.add(new Tweet("USER_9506fb5f","2010-03-04 01:16:34","Great spirits have often encountered violent opposition from weak minds"));
        tweets.add(new Tweet("USER_d3ca457f","2010-03-03 04:53:06","RT @USER_6d6bfb4d: The things that make a woman beautiful are her character, intellect, and spirituality."));
        tweets.add(new Tweet("USER_14f78255","2010-03-03 17:07:45","@USER_9afbc367 Oh in spirit. That's all that matters lol"));
        tweets.add(new Tweet("USER_3dfae4fe","2010-03-05 00:44:33","time for a spiritual cleansing of my facebook friend list"));
        tweets.add(new Tweet("USER_bd852fb7","2010-03-03 14:19:51","RT @USER_24bd1961:God's spirit is like a Radio station, broadcasting all the time. You just have to learn how to tune in and receive his signal"));
        tweets.add(new Tweet("USER_136c16da","2010-03-07 19:56:54","RT @USER_11d35e61: @USER_136c16da finally a kindred spirit. *daps* lol thanks"));
        tweets.add(new Tweet("USER_47063e51","2010-03-04 12:47:54","cathartic - noun - a purification or purgation that brings about spiritual renewal or release from tension"));
        tweets.add(new Tweet("USER_1e4eb302","2010-03-03 20:13:18","Anything worth having you have to contribute yourself heart, mind, soul and spirit to. It is so rewarding. Have u contributed lately?"));
        tweets.add(new Tweet("USER_5d246e83","2010-03-04 14:57:01","@USER_8e090edb That's always good to hear. Starting off to a good morning, always puts your spirit in a great place."));
        tweets.add(new Tweet("USER_b7117680","2010-03-03 06:55:17","I got a hustlas spirit, period!"));
        tweets.add(new Tweet("USER_25ecff25","2010-03-05 17:33:20","RT @USER_3a117437: The woman at the rental car spot tried 2 give us a Toyota! No ma'am lk the old spiritual says \"aint got time 2 die!\""));   
        tweets.add(new Tweet("USER_f91d8165","2010-03-03 22:33:24","#RandomThought why do people grab guns or knives when they think theres a ghost? DUMBASS! You can't shoot a spirit, grab some holy water! duh"));
        tweets.add(new Tweet("USER_86c542b8","2010-03-04 02:52:06","@USER_8cd1512d haha, maybe your right. I use to watch gymnastics all the time. I love the olympics. That's why I have so much spirit lol"));
        
        return tweets;
    }
	
	public static void setNumEntriesAndStep(int n, int s)
	{
		numEntries = n;
		step = s;
	}
	
	@Override public void start(Stage stage)
	{
		stage.setTitle("Time Performance");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
         xAxis.setLabel("Number of Entries to Be Sorted");
         yAxis.setLabel("Microseconds (us)");
        final LineChart<String,Number> lineChart = 
                new LineChart<String,Number>(xAxis,yAxis);
       
        lineChart.setTitle("Time Performance of fastSort and slowSort");
                          
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("slowSort");
        
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("fastSort");
        
        MyHashTable<Integer, Double> t = new MyHashTable<Integer, Double>(numEntries);
        int key = 0;
        Random rand = new Random();
        double value = rand.nextDouble();
        
        long before;
        double diff;
        print("--------------------------------------------------Finish\n");
        double timeStep = numEntries/50;
        double timePassed = 0;
        int tmp = 0;
        int toPrint = 0;
        int printed = 0;
        for (int i=step; i <= numEntries; i+=step)
        {
        	for (int j=0; j < step; j++)
        	{
        		t.put(key, value);
        		value = rand.nextDouble();
        		key++;
        	}
        	before = System.nanoTime();
        	t.slowSort(t);
        	diff = (double) (System.nanoTime()-before)/1000;
        	series1.getData().add(new XYChart.Data(Integer.toString(i), diff));
        	
        	before = System.nanoTime();
        	t.fastSort(t);
        	diff = (double) (System.nanoTime()-before)/1000;
        	series2.getData().add(new XYChart.Data(Integer.toString(i), diff));
        	
        	toPrint = (int) (i/timeStep);
        	while (toPrint - printed > 0)
        	{
        		print(">");
        		printed++;
        	}
        }
        print("Finished\n");
        
        Scene scene  = new Scene(lineChart,1400,800);       
        lineChart.getData().addAll(series1, series2);
       
        stage.setScene(scene);
        stage.show();
	}
	
}
