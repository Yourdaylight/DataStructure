package FinalProject_Template;

//						This is a tester for the COMP250 final project by Christian Zhao.
/* 
 * 
 * Tester includes: 
 * 	- 21 test cases for MyHashTable (test on everything except the iterator, unless you use foreach loop in your methods
 * 	- a line graph comparing the time performance by slowSort and fastSort
 * 
 * This tester only tests on basic functionalities. Passing all tests does not guarantee a high grade!!
 * You can add your own test cases by adding methods. See template
 * 
 * READ THIS ! ! !
 * Before you run the tester,
 * please install the e(fx)clipse plug-in from Help -> Eclipse Marketplace -> Find e(fx)clipse.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class TesterByChristianZhaoV2 extends Application
{
	public static void main(String[] args)
	{
		TesterByChristianZhaoV2 t = new TesterByChristianZhaoV2();
		t.test_put_get();
		t.test_remove();
		t.test_rehash();
		t.test_keys();
		t.test_values();
		t.test_fastSort();
		t.reportTotal();
		//change these two values to adjust the maximum number of entries you want to sort, and the step of increment for the number of entries
		setNumEntriesAndStep(2000,20);
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
	}

	public void test_put_get()
	{
		int s = 0;
		int subTotal = 7;
		this.total += subTotal;
		print("Testing for put() and get() ...\n");
		try
		{
			MyHashTable t = new MyHashTable(10);
			
			t.put(3, "Christian");
			if (t.get(3).equals("Christian")) s++;
			else print("Error: failed when key does not exist\n");
			
			if (t.size() == 1) s++;
			else print("Error: incorrect numEntries\n");
			
			if (t.put(3, 1).equals("Christian")) s++;
			else print("Error: put() failed when key already exists\n");
			
			if (t.get(3).equals(1)) s++;
			else print("Error: get() failed when key already exists\n");
			
			if (t.size() == 1) s++;
			else print("Error: incorrect numEntries\n");
			
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
		int subTotal = 5;
		this.total += subTotal;
		print("Testing for remove() ...\n");
		try
		{
			MyHashTable t = new MyHashTable(10);
			t.put("3", 3);
			t.put("Hi", 4);
			t.put(1, "Hi");
			
			if (t.remove(3) == null) s++;
			else print("Error: unexpected return value\n");
			
			if (t.size() == 3) s++;
			else print("Error: incorrect numEntries\n");
			
			if (t.remove("Hi").equals(4)) s++;
			else print("Error: unexpected return value\n");
			
			if (t.size() == 2) s++;
			else print("Error: incorrect numEntries\n");
			
			if (t.get("Hi") == null) s++;
			else print("Error: failed to remove the pair\n");
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
		int subTotal = 3;
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
			int[] rehashed = new int[22];
			int i = 0;
			for (i=0; i<22; i++)
			{
				t.put((int)(Math.pow(-i,3)+Math.pow(4*i,2)), "ho");
			}
			
			// retrieve a list of keys from the hash table using the iterator
			i=0;
			for (HashPair<Integer, String> entry : t)
			{
				keyList[i] = entry.getKey();
				i++;
			}
				
			t.rehash();
			
			// retrieve a list of keys from the hash table after rehashing
			i=0;
			for (HashPair<Integer, String> entry : t)
			{
				rehashed[i] = entry.getKey();
				i++;
			}
			
			if (t.numBuckets() == 60) s++;
			else print("Error: numBuckets remains unchanged after rehashing\n");
			
			if (!Arrays.equals(keyList, rehashed)) s++;
			else print("Error: rehashing unsuccessful - same item order before and after rehashing\n");
			
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
			t.keys();
			tmp = t.keys().toArray();
			for (int i=0; i < tmp.length; i++)
			{
				keysRetrieved[i] = (int) tmp[i];
			}
			Arrays.sort(keyList);
			Arrays.sort(keysRetrieved);
			if (Arrays.equals(keyList, keysRetrieved))
				s++;
			else print("Error: incorrect ArrayList<K> content\n");
			
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
		int subTotal = 2;
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
			else print("Error: incorrect ArrayList<K> content\n");
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
		int subTotal = 2;
		this.total += subTotal;
		print("Testing for fastSort() ...\n");
		try
		{
			MyHashTable<Integer, Integer> t = new MyHashTable<Integer, Integer>(1000);
			if (t.fastSort(t) != null && Arrays.equals(t.slowSort(t).toArray(), t.fastSort(t).toArray())) s++;
			else print("Error: failed when MyHashTable is empty\n");
			
			for (int i=0; i<749; i++)
			{
				//t.put(i, (int)(Math.pow(-i,3)+Math.pow(4*i,2)));
				t.put(i, (int)(Math.cos(i)*100));
			}
			Object[] slowSorted = t.slowSort(t).toArray();
			Object[] fastSorted = t.fastSort(t).toArray();
			if (Arrays.equals(slowSorted, fastSorted)) s++;
			else print("Error: incorrect sorting\n");

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
