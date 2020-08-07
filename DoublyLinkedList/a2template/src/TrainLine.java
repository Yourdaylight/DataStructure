package a2template.src;

import java.util.Arrays;
import java.util.Random;

public class TrainLine {

	private TrainStation leftTerminus;
	private TrainStation rightTerminus;
	private String lineName;
	private boolean goingRight;
	public TrainStation[] lineMap;
	public static Random rand;

	public TrainLine(TrainStation leftTerminus, TrainStation rightTerminus, String name, boolean goingRight) {
		this.leftTerminus = leftTerminus;
		this.rightTerminus = rightTerminus;
		this.leftTerminus.setLeftTerminal();
		this.rightTerminus.setRightTerminal();
		this.leftTerminus.setTrainLine(this);
		this.rightTerminus.setTrainLine(this);
		this.lineName = name;
		this.goingRight = goingRight;

		this.lineMap = this.getLineArray();
	}

	public TrainLine(TrainStation[] stationList, String name, boolean goingRight)
	/*
	 * Constructor for TrainStation input: stationList - An array of TrainStation
	 * containing the stations to be placed in the line name - Name of the line
	 * goingRight - boolean indicating the direction of travel
	 */
	{
		TrainStation leftT = stationList[0];
		TrainStation rightT = stationList[stationList.length - 1];

		stationList[0].setRight(stationList[stationList.length - 1]);
		stationList[stationList.length - 1].setLeft(stationList[0]);

		this.leftTerminus = stationList[0];
		this.rightTerminus = stationList[stationList.length - 1];
		this.leftTerminus.setLeftTerminal();
		this.rightTerminus.setRightTerminal();
		this.leftTerminus.setTrainLine(this);
		this.rightTerminus.setTrainLine(this);
		this.lineName = name;
		this.goingRight = goingRight;

		for (int i = 1; i < stationList.length - 1; i++) {
			this.addStation(stationList[i]);
		}

		this.lineMap = this.getLineArray();
	}

	public TrainLine(String[] stationNames, String name,
			boolean goingRight) {/*
									 * Constructor for TrainStation. input: stationNames - An array of String
									 * containing the name of the stations to be placed in the line name - Name of
									 * the line goingRight - boolean indicating the direction of travel
									 */
		TrainStation leftTerminus = new TrainStation(stationNames[0]);
		TrainStation rightTerminus = new TrainStation(stationNames[stationNames.length - 1]);

		leftTerminus.setRight(rightTerminus);
		rightTerminus.setLeft(leftTerminus);

		this.leftTerminus = leftTerminus;
		this.rightTerminus = rightTerminus;
		this.leftTerminus.setLeftTerminal();
		this.rightTerminus.setRightTerminal();
		this.leftTerminus.setTrainLine(this);
		this.rightTerminus.setTrainLine(this);
		this.lineName = name;
		this.goingRight = goingRight;
		for (int i = 1; i < stationNames.length - 1; i++) {
			this.addStation(new TrainStation(stationNames[i]));
		}

		this.lineMap = this.getLineArray();

	}

	// adds a station at the last position before the right terminus
	public void addStation(TrainStation stationToAdd) {
		TrainStation rTer = this.rightTerminus;
		TrainStation beforeTer = rTer.getLeft();
		rTer.setLeft(stationToAdd);
		stationToAdd.setRight(rTer);
		beforeTer.setRight(stationToAdd);
		stationToAdd.setLeft(beforeTer);

		stationToAdd.setTrainLine(this);

		this.lineMap = this.getLineArray();
	}

	public String getName() {
		return this.lineName;
	}

	public int getSize() {

		// YOUR CODE GOES HERE
		int count=0;
//		System.out.println(this.leftTerminus.getName());
		if(this.rightTerminus!=null)
			count+=1;
		if(this.leftTerminus!=null)
			count+=1;
		TrainStation temp=this.rightTerminus.getLeft();

		while(!temp.isLeftTerminal())
		{
			if (temp.isLeftTerminal()==false){
				count+=1;
				temp=temp.getLeft();

			}
			else
				break;
		}

		return count; // change this!
	}

	public void reverseDirection() {
		this.goingRight = !this.goingRight;
	}

	// You can modify the header to this method to handle an exception. You cannot make any other change to the header.
	public TrainStation travelOneStation(TrainStation current, TrainStation previous) {

		// YOUR CODE GOES HERE

		if (this.equals(current.getLine())==false)
			throw new LineNotFoundException(current.getLine().getName());
		if (current.hasConnection==true)
		{
			if	( current.getLine().equals(previous.getLine())&&current.getTransferStation().equals(current)==false ){
					return current.getTransferStation();
			}
			else if(current.getLine().equals(previous.getLine())==false && current.getTransferStation().equals(previous)==false)
				return current.getTransferStation();
		}
		if((this.goingRight==true && current.isRightTerminal()==true) )
			this.reverseDirection();
		if((this.goingRight==false && current.isLeftTerminal()==true) )
			this.reverseDirection();

		return current.getLine().getNext(current); // change this!
	}

	// You can modify the header to this method to handle an exception. You cannot make any other change to the header.
	public TrainStation getNext(TrainStation station) {

		// YOUR CODE GOES HERE
		if(!station.getLine().lineName.equals(this.lineName)) 
			throw new StationNotFoundException(station.getName());
		if(this.goingRight==true)
			return station.getRight();
		else 
			return station.getLeft();
		

	}

	// You can modify the header to this method to handle an exception. You cannot make any other change to the header.
	public TrainStation findStation(String name) {

		// YOUR CODE GOES HERE
		for(TrainStation station:this.getLineArray())
		{
			if(station.getName().equals(name))
				return station; // change this!
		}
		throw new StationNotFoundException(name);
	
	}

	private TrainStation[] BubbleSort(TrainStation[] array) {
		TrainStation temp=array[0];
		for(int i=0;i<array.length-1;i++)
			for(int j=0;j<array.length-1-i;j++){
				int num1=Integer.valueOf(array[j].getName().substring(0,1));
				int num2=Integer.valueOf(array[j+1].getName().substring(0,1));

				if(num1>num2){
					temp=array[j];
					array[j]=array[j+1];
					array[j+1]=temp;
				}
			}
		this.lineMap = array;

		return array;
	}
	public void sortLine() {

		// YOUR CODE GOES HERE
		TrainStation [] map=this.lineMap;
		TrainStation temp=map[0];
		map=BubbleSort(map);
		for(int i=0;i<map.length;i++)
		{
			if(i==0){
				map[i].setNonTerminal();
				map[i].setLeft(null);
				map[i].setLeftTerminal();
				map[i].setRight(map[i+1]);
				this.leftTerminus=map[i];
			}

			else  if(i==map.length-1){
				map[i].setNonTerminal();
				map[i].setRight(null);
				map[i].setRightTerminal();
				map[i].setLeft(map[i-1]);
				this.rightTerminus=map[i];
			}
			else{
				if (map[i].isRightTerminal()==true || map[i].isLeftTerminal())
					map[i].setNonTerminal();
				map[i].setLeft(map[i-1]);
				map[i].setRight(map[i+1]);
			}
		}
		this.goingRight=true;

		this.lineMap=map;

	}

	public TrainStation[] getLineArray() {

		// YOUR CODE GOES HERE

		int size=this.getSize();
		TrainStation[] LineArray=new TrainStation[size];
		for(int i=0;i<size;i++){
			if (i== 0)
				LineArray[i] = this.leftTerminus;

			else {
				if (i== size - 1)
				{
					LineArray[i] = this.rightTerminus;
					break;
				}
				else {
					LineArray[i] = LineArray[i - 1].getRight();
				}
			}
		}
		return LineArray; // change this
	}

	private TrainStation[] shuffleArray(TrainStation[] array) {
		Random rand = new Random();
		rand.setSeed(11);
		for (int i = 0; i < array.length; i++) {
			int randomIndexToSwap = rand.nextInt(array.length);
			TrainStation temp = array[randomIndexToSwap];
			array[randomIndexToSwap] = array[i];
			array[i] = temp;
		}
		this.lineMap = array;
		return array;
	}

	public void shuffleLine() {

		// you are given a shuffled array of trainStations to start with
		TrainStation[] lineArray = this.getLineArray();
		TrainStation[] shuffledArray = shuffleArray(lineArray);
		// YOUR CODE GOES HERE


		for(int i=0;i<shuffledArray.length;i++)
		{

			if(i==0) {
				shuffledArray[i].setNonTerminal();
				shuffledArray[i].setLeftTerminal();
				shuffledArray[i].setRight(shuffledArray[i+1]);
				shuffledArray[i].setLeft(null);
			}
			else  if(i==shuffledArray.length-1) {
				shuffledArray[i].setNonTerminal();
				shuffledArray[i].setRightTerminal();
				shuffledArray[i].setLeft(shuffledArray[i-1]);
				shuffledArray[i].setRight(null);
			}
			else{
				if(shuffledArray[i].isRightTerminal()==true || shuffledArray[i].isLeftTerminal()==true)
					shuffledArray[i].setNonTerminal();
				shuffledArray[i].setLeft(shuffledArray[i-1]);
				shuffledArray[i].setRight(shuffledArray[i+1]);
			}
		}
		this.leftTerminus=shuffledArray[0];
		this.rightTerminus=shuffledArray[shuffledArray.length-1];


	}

	public String toString() {

		TrainStation[] lineArr = this.getLineArray();

		String[] nameArr = new String[lineArr.length];
		for (int i = 0; i < lineArr.length; i++) {
			nameArr[i] = lineArr[i].getName();

		}
		return Arrays.deepToString(nameArr);
	}

	public boolean equals(TrainLine line2) {

		// check for equality of each station
		TrainStation current = this.leftTerminus;
		TrainStation curr2 = line2.leftTerminus;

		try {
			while (current != null) {
				if (!current.equals(curr2))
					return false;
				else {
					current = current.getRight();
					curr2 = curr2.getRight();
				}

			}

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public TrainStation getLeftTerminus() {
		return this.leftTerminus;
	}

	public TrainStation getRightTerminus() {
		return this.rightTerminus;
	}
}

//Exception for when searching a line for a station and not finding any station of the right name.
class StationNotFoundException extends RuntimeException {
	String name;

	public StationNotFoundException(String n) {
		name = n;
	}

	public String toString() {
		return "StationNotFoundException[" + name + "]";
	}
}
