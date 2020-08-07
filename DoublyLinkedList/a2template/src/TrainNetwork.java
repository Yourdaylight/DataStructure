package a2template.src;

public class TrainNetwork {
	final int swapFreq = 2;
	TrainLine[] networkLines;

    public TrainNetwork(int nLines) {
    	this.networkLines = new TrainLine[nLines];
    }
    
    public void addLines(TrainLine[] lines) {
    	this.networkLines = lines;
    }
    
    public TrainLine[] getLines() {
    	return this.networkLines;
    }
    
    public void dance() {
    	System.out.println("The tracks are moving!");

    	//YOUR CODE GOES HERE
		for(int i=0;i<getLines().length;i++) {
			this.getLines()[i].shuffleLine();
		}

    }
    
    public void undance() {
    	//YOUR CODE GOES HERE
		for(int i=0;i<this.getLines().length;i++){
			this.getLines()[i].sortLine();
		}

    }
    
    public int travel(String startStation, String startLine, String endStation, String endLine) {
    	
    	TrainLine curLine = null; //use this variable to store the current line.
    	TrainStation curStation= null; //use this variable to store the current station. 
    	
    	
    	int hoursCount = 0;
    	System.out.println("Departing from "+startStation);
    	
    	//YOUR CODE GOES HERE
		TrainStation previous=null;
		TrainStation temp=null;
    	for (TrainLine tl:this.getLines()){
    		if (tl.getName().equals(startLine))
    			curLine=tl;
		}
    	String previousLine=curLine.getName();
    	curStation=curLine.findStation(startStation);
    	previous=curStation;

    	int transferTime=0;
    	while(curStation.getName().equals(endStation)==false /*you can change this*/) {
    		if(hoursCount == 168) {
    			System.out.println("Jumped off after spending a full week on the train. Might as well walk.");
    			return hoursCount;
    		}

			if (curStation.getName().equals(endStation))
				return hoursCount;
    		//prints an update on your current location in the network.
	    	System.out.println("Traveling on line "+curLine.getName()+":"+curLine.toString());
	    	System.out.println("Hour "+hoursCount+". Current station: "+curStation.getName()+" on line "+curLine.getName());
	    	System.out.println("=============================================");
	    	hoursCount+=1;
	    	temp=curStation;

			if(hoursCount>1&&hoursCount%2==1) {
				this.dance();
			}
			curStation = curLine.travelOneStation(curStation, previous);
			curLine=curStation.getLine();
			previous = temp;


    		}

	    	System.out.println("Arrived at destination after "+hoursCount+" hours!");
	    	return hoursCount;
    }
    
    
    //you can extend the method header if needed to include an exception. You cannot make any other change to the header.
    public TrainLine getLineByName(String lineName){
    	//YOUR CODE GOES HERE
    	for(TrainLine tl:this.getLines()){
    		if(tl.getName().equals(lineName))
    			return tl;
		}
    	throw new LineNotFoundException(lineName); //change this
    }
    
  //prints a plan of the network for you.
    public void printPlan() {
    	System.out.println("CURRENT TRAIN NETWORK PLAN");
    	System.out.println("------------------- --------");
    	for(int i=0;i<this.networkLines.length;i++) {
    		System.out.println(this.networkLines[i].getName()+":"+this.networkLines[i].toString());
    		}
    	System.out.println("----------------------------");
    }
}

//exception when searching a network for a LineName and not finding any matching Line object.
class LineNotFoundException extends RuntimeException {
	   String name;

	   public LineNotFoundException(String n) {
	      name = n;
	   }

	   public String toString() {
	      return "LineNotFoundException[" + name + "]";
	   }
	}