import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Arrays;

public class MiniTester
{
	int scale = 1;
	String packageName;

	private String gradeString(int score, int maxScore, String comment)
	{
		return comment + "Score: " + Integer.toString(score * scale) + "/" + Integer.toString(maxScore * scale);
	}

	private void write(String string)
	{
		System.out.print(string + "\n");
	}

	public MiniTester()
	{
		Package p = MiniTester.class.getPackage();
		packageName = p != null ? p.getName() + "" : "";
	}


	//----------------------------------------------------------------------------------------------------------------------------------------
	//ADD CAT 12

	private int test_addCat_0(int testIdx)
	{
		String comment = "[" + testIdx + "]: Test addCat where cat to add is senior to c and c.senior == null.\n";
		int maxScore = 2;
		int grade = 0;

		try {

			CatInfo a = new CatInfo("A", 87, 50, 243, 40);
			CatInfo b = new CatInfo("B", 85, 60, 240, 30);
			CatTree t = new CatTree(a);
			t.addCat(b);

			if (t.root.data.equals(a) && t.root.senior.data.equals(b)) {
				grade += 2;
			} else {
				comment = comment + "Error: structure of tree is wrong when adding a more senior cat and c.senior == null.\n";
			}

			write(gradeString(grade, maxScore, comment));

		} catch (Exception e) {
			comment = comment + "Exception Found: " + e.toString() + "\n";
			e.printStackTrace();
			write(gradeString(0, maxScore, comment));
		}

		return grade;
	}


	private int test_addCat_1(int testIdx)
	{
		String comment = "[" + testIdx + "]:  Test addCat where cat to add is senior to c and c.senior != null.\n";
		int maxScore = 2;
		int grade = 0;

		try {

			CatInfo a = new CatInfo("A", 87, 50, 243, 40);
			CatInfo b = new CatInfo("B", 85, 60, 240, 30);
			CatInfo g = new CatInfo("G", 85, 50, 247, 27);
			CatTree t = new CatTree(a);
			t.addCat(b);
			t.addCat(g);

			if (t.root.data.equals(a) && t.root.senior.data.equals(b) && t.root.senior.same.data.equals(g)) {
				grade += 2;
			} else {
				comment = comment + "Error: structure of tree is wrong when adding a more senior cat and c.senior != null.\n";
			}

			write(gradeString(grade, maxScore, comment));

		} catch (Exception e) {
			comment = comment + "Exception Found: " + e.toString() + "\n";
			e.printStackTrace();
			write(gradeString(0, maxScore, comment));
		}

		return grade;
	}

	private int test_addCat_2(int testIdx)
	{
		String comment = "[" + testIdx + "]: Test addCat where cat to add is junior to c and c.junior == null.\n";
		int maxScore = 2;
		int grade = 0;

		try {
			CatInfo a = new CatInfo("A", 87, 50, 243, 40);
			CatInfo c = new CatInfo("C", 88, 70, 248, 10);
			CatTree t = new CatTree(a);
			t.addCat(c);

			if (t.root.data.equals(a) && t.root.junior.data.equals(c)) {
				grade += 2;
			} else {
				comment = comment + "Error: structure of tree is wrong when adding a more junior cat and c.junior == null.\n";
			}

			write(gradeString(grade, maxScore, comment));

		} catch (Exception e) {
			comment = comment + "Exception Found: " + e.toString() + "\n";
			e.printStackTrace();
			write(gradeString(0, maxScore, comment));
		}

		return grade;


	}

	private int test_addCat_3(int testIdx)
	{
		String comment = "[" + testIdx + "]: Test addCat where cat to add is junior to c and c.junior != null.\n";
		int maxScore = 2;
		int grade = 0;

		try {

			CatInfo a = new CatInfo("A", 87, 50, 243, 40);
			CatInfo c = new CatInfo("C", 88, 70, 248, 10);
			CatInfo d = new CatInfo("D", 95, 55, 245, 50);
			CatTree t = new CatTree(a);
			t.addCat(c);
			t.addCat(d);

			if (t.root.data.equals(a) && t.root.junior.data.equals(c) && t.root.junior.junior.data.equals(d)) {
				grade += 2;
			} else {
				comment = comment + "Error: structure of tree is wrong when adding a more junior cat and c.junior != null.\n";
			}

			write(gradeString(grade, maxScore, comment));

		} catch (Exception e) {
			comment = comment + "Exception Found: " + e.toString() + "\n";
			e.printStackTrace();
			write(gradeString(0, maxScore, comment));
		}

		return grade;
	}

	private int test_addCat_4(int testIdx)
	{
		String comment = "[" + testIdx + "]: Test addCat where cat to add has same seniority as c and is fluffier than c.\n";
		int maxScore = 2;
		int grade = 0;

		try {

			CatInfo a = new CatInfo("A", 87, 50, 243, 40);
			CatInfo e = new CatInfo("E", 87, 55, 245, 20);
			CatTree t = new CatTree(a);
			t.addCat(e);

			if (t.root.data.equals(e) && t.root.same.data.equals(a)) {
				grade += 2;
			} else {
				comment = comment + "Error: structure of tree is wrong when adding a cat with the same seniority that is fluffier.\n";
			}

			write(gradeString(grade, maxScore, comment));

		} catch (Exception e) {
			comment = comment + "Exception Found: " + e.toString() + "\n";
			e.printStackTrace();
			write(gradeString(0, maxScore, comment));
		}

		return grade;
	}

	private int test_addCat_5(int testIdx)
	{
		String comment = "[" + testIdx + "]: Test addCat where cat to add has same seniority to c and is less fluffy.\n";
		int maxScore = 2;
		int grade = 0;

		try {
			CatInfo a = new CatInfo("A", 87, 50, 243, 40);
			CatInfo i = new CatInfo("I", 87, 45, 245, 20);
			CatTree t = new CatTree(a);
			t.addCat(i);

			if (t.root.data.equals(a) && t.root.same.data.equals(i)) {
				grade += 2;
			} else {
				comment = comment + "Error: structure of tree is wrong when adding a cat with the same seniority that is less fluffy.\n";
			}

			write(gradeString(grade, maxScore, comment));


		} catch (Exception e) {
			comment = comment + "Exception Found: " + e.toString() + "\n";
			e.printStackTrace();
			write(gradeString(0, maxScore, comment));
		}

		return grade;
	}

	private int test_addCat_6(int testIdx)
	{
		String comment = "[" + testIdx + "]: Test addCat (using examples).\n";
		int maxScore = 4;
		int grade = 0;

		try {
			CatInfo a = new CatInfo("A", 87, 50, 42, 42);
			CatInfo b = new CatInfo("B", 88, 60, 42, 42);
			CatInfo h = new CatInfo("H", 95, 55, 42, 42);
			CatInfo f = new CatInfo("F", 85, 60, 42, 42);
			CatInfo d = new CatInfo("D", 85, 50, 42, 42);
			CatInfo e = new CatInfo("E", 85, 45, 42, 42);
			CatInfo c = new CatInfo("C", 87, 55, 42, 42);
			CatInfo g = new CatInfo("G", 86, 55, 42, 42);
			CatTree t = new CatTree(a);
			t.addCat(b);
			t.addCat(h);
			t.addCat(f);
			t.addCat(d);
			t.addCat(e);
			t.addCat(c);

			if (t.root.data.equals(c) && t.root.same.data.equals(a) && t.root.junior.data.equals(b) && t.root.junior.junior.data.equals(h) && t.root.senior.data.equals(f) && t.root.senior.same.data.equals(d) && t.root.senior.same.same.data.equals(e)) {
				grade += 2;
			}
			else {
				comment = comment + "Error: Example 1 failed\n";
			}

			t.addCat(g);

			if (t.root.data.equals(c) && t.root.same.data.equals(a) && t.root.junior.data.equals(b) && t.root.junior.junior.data.equals(h) && t.root.senior.data.equals(f) && t.root.senior.same.data.equals(d) && t.root.senior.same.same.data.equals(e) && t.root.senior.junior.data.equals(g)) {
				grade += 2;
			}
			else {
				comment = comment + "Error: Example 2 failed\n";
			}


			write(gradeString(grade, maxScore, comment));


		} catch (Exception e) {
			comment = comment + "Exception Found: " + e.toString() + "\n";
			e.printStackTrace();
			write(gradeString(0, maxScore, comment));
		}

		return grade;
	}

	//----------------------------------------------------------------------------------------------------------------------
	//REMOVE CAT 22

	private int test_removeCat_0(int testIdx)
	{
		String comment = "[" + testIdx + "]: Test removeCat when Node to remove is at root.\n";
		int maxScore = 4;
		int grade = 0;

		try {

			/*
			1. Node not in the tree		1
			2. at root (simple case)	3
			*/
			CatInfo a = new CatInfo("A", 87, 50, 243, 40);
			CatInfo b = new CatInfo("B", 85, 60, 240, 30);
			CatInfo c = new CatInfo("C", 88, 70, 248, 10);

			CatTree t = new CatTree(a);
			t.addCat(b);

			t.removeCat(c);	//nothing should change since c not in tree

			if (t.root.data.equals(a) && t.root.senior.data.equals(b) && t.root.same == null && t.root.junior == null) {
				grade += 1;
			} else {
				comment = comment + "Error: removeCat changes the structure of the tree in some way when the cat to remove isn't in the tree.\n";
			}

			t.removeCat(a);

			if (t.root.data.equals(b) && t.root.same == null && t.root.senior == null && t.root.junior == null) {
				grade += 3;
			} else{
				comment = comment + "Error: unexpected tree structure when removing the root of a tree.\n";
			}

			write(gradeString(grade, maxScore, comment));

		} catch (Exception e) {
			comment = comment + "Exception Found: " + e.toString() + "\n";
			e.printStackTrace();
			write(gradeString(0, maxScore, comment));
		}

		return grade;
	}

	private int test_removeCat_1(int testIdx)
	{
		String comment = "[" + testIdx + "]: Test removeCat when same != null.\n";
		int maxScore = 6;
		int grade = 0;

		try {
			CatInfo a = new CatInfo("A", 87, 50, 243, 40);
			CatInfo b = new CatInfo("B", 85, 60, 240, 30);
			CatInfo c = new CatInfo("C", 88, 70, 248, 10);
			CatInfo e = new CatInfo("E", 88, 55, 245, 20);

			CatTree t = new CatTree(a);
			t.addCat(b);
			t.addCat(c);
			t.addCat(e);

			t.removeCat(c);

			if (t.root.data.equals(a) && t.root.senior.data.equals(b) && t.root.junior.data.equals(e)) {
				grade += 6;
			} else {
				comment = comment + "Error: unexpected tree structure when removing an internal node when same != null.\n";
			}

			write(gradeString(grade, maxScore, comment));

		} catch (Exception e) {
			comment = comment + "Exception Found: " + e.toString() + "\n";
			e.printStackTrace();
			write(gradeString(0, maxScore, comment));
		}

		return grade;
	}

	private int test_removeCat_2(int testIdx)
	{
		String comment = "[" + testIdx + "]: Test removeCat (using examples).\n";
		int maxScore = 6;
		int grade = 0;

		try {
			CatInfo a = new CatInfo("A", 87, 50, 42, 42);
			CatInfo b = new CatInfo("B", 88, 60, 42, 42);
			CatInfo h = new CatInfo("H", 95, 55, 42, 42);
			CatInfo f = new CatInfo("F", 85, 60, 42, 42);
			CatInfo d = new CatInfo("D", 85, 50, 42, 42);
			CatInfo e = new CatInfo("E", 85, 45, 42, 42);
			CatInfo c = new CatInfo("C", 87, 55, 42, 42);
			CatInfo g = new CatInfo("G", 86, 55, 42, 42);
			CatTree t = new CatTree(a);
			t.addCat(b);
			t.addCat(h);
			t.addCat(f);
			t.addCat(d);
			t.addCat(e);
			t.addCat(c);
			t.addCat(g);

			t.root.removeCat(t.root.data);

			if (t.root.data.equals(a) && t.root.junior.data.equals(b) && t.root.junior.junior.data.equals(h) && t.root.senior.data.equals(f) && t.root.senior.same.data.equals(d) && t.root.senior.same.same.data.equals(e) && t.root.senior.junior.data.equals(g) && t.root.same == null) {
				grade += 2;
			}
			else {
				comment = comment + "Error: Example 1 failed\n";
			}

			t.root.removeCat(t.root.data);

			if (t.root.data.equals(f) && t.root.same.data.equals(d) && t.root.same.same.data.equals(e) && t.root.junior.data.equals(g) && t.root.junior.junior.data.equals(b) && t.root.junior.junior.junior.data.equals(h) && t.root.senior == null) {
				grade += 2;
			}
			else {
				comment = comment + "Error: Example 2 failed\n";
			}

			t.root.removeCat(t.root.junior.junior.data);

			if (t.root.data.equals(f) && t.root.same.data.equals(d) && t.root.same.same.data.equals(e) && t.root.junior.data.equals(g) && t.root.junior.junior.data.equals(h) && t.root.senior == null) {
				grade += 2;
			}
			else {
				comment = comment + "Error: Example 3 failed\n";
			}

			write(gradeString(grade, maxScore, comment));


		} catch (Exception e) {
			comment = comment + "Exception Found: " + e.toString() + "\n";
			e.printStackTrace();
			write(gradeString(0, maxScore, comment));
		}

		return grade;
	}
	//----------------------------------------------------------------------------------------------------------------------
	//MOST SENIOR

	private int test_mostSenior_0(int testIdx)
	{
		String comment = "[" + testIdx + "]: Test mostSenior.\n";
		int maxScore = 1000;
		Random ranGen = new Random();
		int grade = 0;

		for (int i = 0; i < 1000; i++){
			try {
				CatInfo[] catArray = new CatInfo[26];
				String[] nameArray = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
				int minMonth = 300;

				for (int j = 0; j < 26; j++){
					catArray[j] = new CatInfo(nameArray[j], ranGen.ints(1, 217).findFirst().getAsInt(), ranGen.ints(1, 101).findFirst().getAsInt(), 42, 42);
					if (catArray[j].monthHired < minMonth){
						minMonth = catArray[j].monthHired;
					}
				}

				CatTree t = new CatTree(catArray[0]);

				for (int j = 1; j < 26; j++){
					t.addCat(catArray[j]);
				}

				if (t.mostSenior() == minMonth){
					grade += 1;
				}
				else{
					grade -= 10;
				}


			} catch (Exception e) {
				comment = comment + "Exception Found: " + e.toString() + "\n";
				e.printStackTrace();
				write(gradeString(0, maxScore, comment));
			}
		}
		write(gradeString(grade, maxScore, comment));
		return grade;
	}


	//----------------------------------------------------------------------------------------------------------------------
	//FLUFFIEST

	private int test_fluffiest_0(int testIdx)
	{
		String comment = "[" + testIdx + "]: Test fluffiest.\n";
		int maxScore = 1000;
		Random ranGen = new Random();
		int grade = 0;

		for (int i = 0; i < 1000; i++){
			try {
				CatInfo[] catArray = new CatInfo[26];
				String[] nameArray = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
				int maxFluff = 0;

				for (int j = 0; j < 26; j++){
					catArray[j] = new CatInfo(nameArray[j], ranGen.ints(1, 217).findFirst().getAsInt(), ranGen.ints(1, 101).findFirst().getAsInt(), 42, 42);
					if (catArray[j].furThickness > maxFluff){
						maxFluff = catArray[j].furThickness;
					}
				}

				CatTree t = new CatTree(catArray[0]);

				for (int j = 1; j < 26; j++){
					t.addCat(catArray[j]);
				}

				if (t.fluffiest() == maxFluff){
					grade += 1;
				}
				else{
					grade -= 10;
				}


			} catch (Exception e) {
				comment = comment + "Exception Found: " + e.toString() + "\n";
				e.printStackTrace();
				write(gradeString(0, maxScore, comment));
			}
		}
		write(gradeString(grade, maxScore, comment));
		return grade;
	}

	//----------------------------------------------------------------------------------------------------------------------
	//HIREDFORMONTHS
	private int test_hired_0(int testIdx)
	{
		String comment = "[" + testIdx + "]: Test hiredFromMonths.\n";
		int maxScore = 1000;
		Random ranGen = new Random();
		int grade = 0;

		for (int i = 0; i < 1000; i++){
			try {
				CatInfo[] catArray = new CatInfo[26];
				String[] nameArray = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
				int counter = 0;
				int[] monthArray = new int[26];

				for (int j = 0; j < 26; j++){
					monthArray[j] = ranGen.ints(1, 217).findFirst().getAsInt();
				}

				int maxMonth = ranGen.ints(1,217).findFirst().getAsInt();
				int minMonth = maxMonth - ranGen.ints(0, 31).findFirst().getAsInt();

				for (int j = 0; j < 26; j++){
					catArray[j] = new CatInfo(nameArray[j], monthArray[j], ranGen.ints(1, 101).findFirst().getAsInt(), 42, 42);
					if (catArray[j].monthHired <= maxMonth && catArray[j].monthHired >= minMonth){
						counter++;
					}
				}

				CatTree t = new CatTree(catArray[0]);

				for (int j = 1; j < 26; j++){
					t.addCat(catArray[j]);
				}

				if (t.hiredFromMonths(minMonth, maxMonth) == counter){
					grade += 1;
				}
				else{
					t.hiredFromMonths(minMonth, maxMonth) ;
					grade -= 10;
				}


			} catch (Exception e) {
				comment = comment + "Exception Found: " + e.toString() + "\n";
				e.printStackTrace();
				write(gradeString(0, maxScore, comment));
	}
		}
		write(gradeString(grade, maxScore, comment));
		return grade;
	}

	//----------------------------------------------------------------------------------------------------------------------
	//FLUFFIESTFROMMONTH

	private int test_fluffyFrom_0(int testIdx)
	{
		String comment = "[" + testIdx + "]: Test fluffiestFromMonth.\n";
		int maxScore = 1000;
		Random ranGen = new Random();
		int grade = 0;

		for (int i = 0; i < 1000; i++){
			try {
				CatInfo[] catArray = new CatInfo[26];
				String[] nameArray = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
				CatInfo fluffiestFrom = null;
				int[] monthArray = new int[26];

				for (int j = 0; j < 26; j++){
					monthArray[j] = ranGen.ints(1, 217).findFirst().getAsInt();
				}

				int targetMonth = monthArray[ranGen.ints(0, 26).findFirst().getAsInt()];

				for (int j = 0; j < 26; j++){
					int fluff = ranGen.ints(1, 101).findFirst().getAsInt();
					if (monthArray[j] == targetMonth && fluffiestFrom != null && fluffiestFrom.furThickness == fluff){
						fluff += ranGen.ints(-10, 1).findFirst().getAsInt();
					}
					catArray[j] = new CatInfo(nameArray[j], monthArray[j], fluff, 42, 42);
					if (monthArray[j] == targetMonth && ((fluffiestFrom != null && fluffiestFrom.furThickness < fluff) || fluffiestFrom == null)){
						fluffiestFrom = catArray[j];
					}
				}

				CatTree t = new CatTree(catArray[0]);

				for (int j = 1; j < 26; j++){
					t.addCat(catArray[j]);
				}

				if (t.fluffiestFromMonth(targetMonth).equals(fluffiestFrom)){
					grade += 1;
				}
				else{
					grade -= 10;
				}


			} catch (Exception e) {
				comment = comment + "Exception Found: " + e.toString() + "\n";
				e.printStackTrace();
				write(gradeString(0, maxScore, comment));
			}
		}
		write(gradeString(grade, maxScore, comment));
		return grade;
	}

	//----------------------------------------------------------------------------------------------------------------------
	//COSTPLANNING
	private int test_costPlan_0(int testIdx)
	{
		String comment = "[" + testIdx + "]: Test costPlanning (tests if costPlanning returns array of proper size).\n";
		int maxScore = 2;
		int grade = 0;

		try {
			CatInfo a = new CatInfo("A", 100, 40, 243, 20);
			CatInfo c = new CatInfo("C", 125, 75, 248, 20);
			CatInfo d = new CatInfo("D", 100, 15, 245, 20);

			CatTree t = new CatTree(a);
			t.addCat(c);
			t.addCat(d);

			int []plan = t.root.costPlanning(75);
			if (plan.length == 75) {
				grade += 2;
			} else {
				comment = comment + "Error: costPlanning did not return an array of required size.\n";
			}

			write(gradeString(grade, maxScore, comment));

		} catch (Exception e) {
			comment = comment + "Exception Found: " + e.toString() + "\n";
			e.printStackTrace();
			write(gradeString(0, maxScore, comment));
		}

		return grade;
	}

	private int test_costPlan_1(int testIdx)
	{
		String comment = "[" + testIdx + "]: Test costPlanning (using examples).\n";
		int maxScore = 2;
		int grade = 0;

		try {
			CatInfo a = new CatInfo("A", 87, 50, 250, 35);
			CatInfo b = new CatInfo("B", 88, 60, 248, 50);
			CatInfo h = new CatInfo("H", 95, 55, 244, 46);
			CatInfo f = new CatInfo("F", 85, 60, 249, 26);
			CatInfo d = new CatInfo("D", 85, 50, 247, 5);
			CatInfo e = new CatInfo("E", 85, 45, 246, 42);
			CatInfo c = new CatInfo("C", 87, 55, 249, 23);
			CatInfo g = new CatInfo("G", 86, 55, 249, 11);
			CatTree t = new CatTree(a);
			t.addCat(b);
			t.addCat(h);
			t.addCat(f);
			t.addCat(d);
			t.addCat(e);
			t.addCat(c);
			t.addCat(g);

			int[] plan = t.root.costPlanning(7);
			if (Arrays.equals(new int[] {0, 46, 0, 42, 5, 50, 60}, plan)) {
				grade += 1;
			} else {
				comment = comment + "Error: costPlanning did not return the proper array for example 1: returned " + Arrays.toString(plan) + " instead of [0, 46, 0, 42, 5, 50, 60]\n";
			}

			plan = t.root.junior.costPlanning(7);
			if (Arrays.equals(new int[] {0, 46, 0, 0, 0, 50, 0}, plan)) {
				grade += 1;
			} else {
				comment = comment + "Error: costPlanning did not return the proper array for example 2: returned " + Arrays.toString(plan) + " instead of [0, 46, 0, 0, 0, 50, 0]\n";
			}

			write(gradeString(grade, maxScore, comment));

		} catch (Exception e) {
			comment = comment + "Exception Found: " + e.toString() + "\n";
			e.printStackTrace();
			write(gradeString(0, maxScore, comment));
		}

		return grade;
	}

	//----------------------------------------------------------------------------------------------------------------------
	//ITERATOR
	private int test_iterator_0(int testIdx)
	{
		String comment = "[" + testIdx + "]: Iterator test (without any removals).\n";
		int maxScore = 1000;
		int grade = 0;
		Random ranGen = new Random();

		for (int i = 0; i < 1000; i++){
			try {
				CatInfo[] catArray = new CatInfo[26];
				String[] nameArray = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

				for (int j = 0; j < 26; j++){
					catArray[j] = new CatInfo(nameArray[j], ranGen.ints(1, 217).findFirst().getAsInt(), ranGen.ints(1, 101).findFirst().getAsInt(), 42, 42);
				}

				CatTree t = new CatTree(catArray[0]);

				for (int j = 1; j < 26; j++){
					t.addCat(catArray[j]);
				}

				int catCount = 0;
				CatInfo prevCat = null;
				for (CatInfo currCat : t){
					if (prevCat != null && prevCat.monthHired > currCat.monthHired){
						grade -= 10;
						comment = comment + "Error found: " + prevCat.toString() + " is after " + currCat.toString() + "\n";
					}
					if (prevCat != null && prevCat.monthHired == currCat.monthHired && prevCat.furThickness > currCat.furThickness){
						grade -= 10;
						comment = comment + "Error found: " + prevCat.toString() + " is after " + currCat.toString() + "\n";
					}
					catCount++;
					prevCat = currCat;
				}
				if (catCount != 26){
					grade -= 10;
					comment = comment + "Error found with amount of cats: found " + Integer.toString(catCount) + " cats (instead of 26)\n";
				}
				grade += 1;

			} catch (Exception e) {
				comment = comment + "Exception Found: " + e.toString() + "\n";
				e.printStackTrace();
			}
		}
		write(gradeString(grade, maxScore, comment));
		return grade;
	}

	private int test_iterator_1(int testIdx)
	{
		String comment = "[" + testIdx + "]: Iterator test (with removals).\n";
		int maxScore = 1000;
		int grade = 0;
		Random ranGen = new Random();

		for (int i = 0; i < 1000; i++){
			try {
				CatInfo[] catArray = new CatInfo[26];
				String[] nameArray = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

				for (int j = 0; j < 26; j++){
					catArray[j] = new CatInfo(nameArray[j], ranGen.ints(1, 217).findFirst().getAsInt(), ranGen.ints(1, 101).findFirst().getAsInt(), 42, 42);
				}

				CatTree t = new CatTree(catArray[0]);

				for (int j = 1; j < 26; j++){
					t.addCat(catArray[j]);
				}

				int startPoint = ranGen.ints(0, 22).findFirst().getAsInt();
				t.removeCat(catArray[startPoint]);
				t.removeCat(catArray[startPoint + 1]);
				t.removeCat(catArray[startPoint + 2]);
				t.removeCat(catArray[startPoint + 3]);
				t.removeCat(catArray[startPoint + 4]);


				int catCount = 0;
				CatInfo prevCat = null;
				for (CatInfo currCat : t){
					if (prevCat != null && prevCat.monthHired > currCat.monthHired){
						grade -= 10;
						comment = comment + "Error found: " + prevCat.toString() + " is after " + currCat.toString() + "\n";
					}
					if (prevCat != null && prevCat.monthHired == currCat.monthHired && prevCat.furThickness > currCat.furThickness){
						grade -= 10;
						comment = comment + "Error found: " + prevCat.toString() + " is after " + currCat.toString() + "\n";
					}
					catCount++;
					prevCat = currCat;
				}
				if (catCount != 21){
					grade -= 10;
					comment = comment + "Error found with amount of cats: found " + Integer.toString(catCount) + " cats (instead of 21)\n";
				}
				grade += 1;

			} catch (Exception e) {
				comment = comment + "Exception Found: " + e.toString() + "\n";
				e.printStackTrace();
			}
		}
		write(gradeString(grade, maxScore, comment));
		return grade;
	}
	public static void main(String[] args)
	{
		MiniTester m = new MiniTester();
		int total = 0;

		//ADD CAT
		total += m.test_addCat_0(0);
		total += m.test_addCat_1(1);
		total += m.test_addCat_2(2);
		total += m.test_addCat_3(3);
		total += m.test_addCat_4(4);
		total += m.test_addCat_5(5);
		total += m.test_addCat_6(6);

		//REMOVE CAT
		total += m.test_removeCat_0(7);
		total += m.test_removeCat_1(8);
		total += m.test_removeCat_2(9);

		//MOST SENIOR
		total += m.test_mostSenior_0(10);

		//FLUFFIEST
		total += m.test_fluffiest_0(11);

		//HIRED FROM MONTHS
		total += m.test_hired_0(12);

		//FLUFFIEST FROM MONTH
		total += m.test_fluffyFrom_0(13);

		//COST PLANNING
		total += m.test_costPlan_0(14);
		total += m.test_costPlan_1(15);

		//ITERATOR
//		total += m.test_iterator_0(16);
//		total += m.test_iterator_1(17);

		m.write(m.gradeString(total, 6036, "Your Final Tester "));
	}
}
