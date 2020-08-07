package a2template.src;

public class tester {

    public static void main(String[] args) {
        System.out.println("Welcome to the Confusing Railroad!");

        TrainNetwork tNet = generateTrainNetwork1();

        TrainNetwork refNetwork = generateTrainNetwork1();
        tNet.printPlan();
        //Starting tests for TrainLine
        System.out.println("***************************************************\n" +
                "*********Testing for TrainLine methods**********\n" +
                "***************************************************");

        int score = 0;
        int testIdx = 0;
        // TODO Auto-generated method stub
        MiniTester m = new MiniTester();
        score += m.testGetSize0(testIdx ++);
        score += m.testFindStation0(testIdx ++);
        score += m.testGetNext0(testIdx ++);
        score += m.testGetNext1(testIdx ++);
        score += m.testTravelOneStation0(testIdx ++);
        score += m.testTravelOneStation1(testIdx ++);
        score += m.testGetLineArray(testIdx ++);
        score += m.testSortLine(testIdx ++);
        score += m.testShuffleLine(testIdx ++);

        m.write("Tests passed is " + score * m.scale + "/15.");
        m.write("Remember that the minitester does not account for all possible points. Only up to a maximum of 40%-50%.");


        //starting the tests for TrainNetwork class
        System.out.println("Starting the tests for the class TrainNetwork:");


        //*************** testing getLineByName() **********************
        System.out.println("***************************************************\n" +
                "*********Testing for TrainNetwork.getLineByName()**********\n" +
                "***************************************************");

        int getLineScore = 0;
        System.out.println("Testing :");

        TrainLine ref = tNet.networkLines[1];
        TrainLine ref2 = tNet.networkLines[0];

        //test to fetch a line that exists in the network
        try {
            if (ref.equals(tNet.getLineByName("Grey"))) {
                System.out.println("Test 1 (Fetching an existing line from the network): Passed");
                getLineScore += 1;
            } else
                System.out.println("Test 1 (Fetching an existing line from the network): Failed");
        }
        catch (Exception e)
        {
            System.out.println("Test 1 (Fetching an existing line from the network): Failed. Encountered an error.");
            e.printStackTrace();
        }

        //test to fetch a line that does not exist in the network
        try {
            TrainLine temp = tNet.getLineByName("Blue");
        }
        catch(LineNotFoundException e)
        {
            getLineScore += 1;
            System.out.println("Test 2 (Fetching a line that does not exist in the network): Passed");
        }
        catch(Exception e)
        {
            System.out.println("Test 2 (Fetching a line that does not exist in the network): Failed");
            e.printStackTrace();

        }

        //*********************************************************
        //***************** testing for dance() *******************

        System.out.println("***************************************************\n" +
                "*********Testing for TrainNetwork.dance()**********\n" +
                "***************************************************");

        //compare the lines in the network with the trainStationArray/lineMap variable

        tNet.dance();

        int danceScore = 0;
        boolean flag = true;
        TrainLine [] lineArray = tNet.getLines();
        for( int i = 0; i < lineArray.length ;i++) {
            TrainLine temp = lineArray[i];
            TrainStation[] refMap = temp.lineMap; //get access of the map updated by built in methods
            TrainLine refLine = createMakeshiftLines(refMap, lineArray[i].getName(), true);

            if (refLine.equals(temp)) {
                danceScore += 1;
            }
        }
        if (danceScore==lineArray.length)
        {
            System.out.println(" Test Passed. All the lines were successfully shuffled.");
        }
        else if(danceScore > 0 && danceScore < lineArray.length)
        {
            int diff = lineArray.length-danceScore;
            System.out.println(" Test failed."+  diff + " lines out of "+lineArray.length + " were not shuffled.");

        }
        else
            System.out.println("Test failed. Not a single line was shuffled.");





        //*********************************************************
        //***************** testing for undance() *****************

        System.out.println("***************************************************\n" +
                "*********Testing for TrainNetwork.undance()**********\n" +
                "***************************************************");
        //shuffling the lines before sorting.
        tNet.dance();
        tNet.dance();

        tNet.undance();
        int undanceScore = 0;
        for( int i = 0; i < lineArray.length ;i++) {
            TrainLine temp = lineArray[i];
            TrainStation[] refMap = temp.lineMap; //get access of the map updated by built in methods
            TrainLine refLine = createMakeshiftLines(refMap, lineArray[i].getName(), true);

            if (refLine.equals(temp)) {
                undanceScore += 1;
            }
        }
        if (undanceScore==lineArray.length)
        {
            System.out.println(" Test Passed. All the lines were successfully sorted.");
        }
        else if(undanceScore > 0 && undanceScore < lineArray.length)
        {
            int diff = lineArray.length-undanceScore;
            System.out.println(" Test failed."+  diff + " lines out of "+lineArray.length + " were not sorted.");

        }
        else
            System.out.println("Test failed. Not a single line was sorted.");





        //Issue arising from checking of hasConnections() in the equals method.



        //*********************************************************
        //***************** testing for travel() ******************


        System.out.println("***************************************************\n" +
                "*********Testing for TrainNetwork.travel()**********\n" +
                "***************************************************");

        int travelScore=0;
        //reset the network
        tNet = generateTrainNetwork1();
        System.out.println("Resetting the network.");
        tNet.printPlan();
        //travelling in the same line less than 2 jumps
        if (tNet.travel("2.Wizard Hat", "Scarlet", "4.Diagon Alley- 1/3", "Scarlet")==2) {
            System.out.println("Test Passed!");
            travelScore += 1;
        }
        else
            System.out.println("Test Failed!");
        //correct answer - 2 hours


        //travelling in the same line more than 2 jumps

        //reset the network
        tNet = generateTrainNetwork1();
        System.out.println("Resetting the network.");
        tNet.printPlan();
        if(tNet.travel("2.Wizard Hat", "Scarlet", "1.Little Whinging", "Scarlet")==37) {
            System.out.println("Test Passed!");
            travelScore += 1;
        }
        else
            System.out.println("Test Failed!");
        //correct answer -  37 hours



        //travelling from a junction

        //reset the network
        tNet = generateTrainNetwork1();
        System.out.println("Resetting the network.");
        tNet.printPlan();
        if (tNet.travel("1.King's Cross - 4/5", "Purple", "5.Leaky Cauldron", "Grey")==2) {
            System.out.println("Test Passed!");
            travelScore += 1;
        }
        else
            System.out.println("Test Failed!");

        //correct answer - 2 hours.


        //reset the network
        tNet = generateTrainNetwork1();
        System.out.println("Resetting the network.");
        tNet.printPlan();
        if (tNet.travel("1.King's Cross - 4/5", "Purple", "1.Little Whinging", "Scarlet")==52) {
            System.out.println("Test Passed!");
            travelScore += 1;
        }
        else
            System.out.println("Test Failed!");


        //correct answer - 52 hours.

        String testNameArray[] = new String[]{"TrainNetwork.getLineByName", "TrainNetwork.dance", "TrainNetwork.undance","TrainNetwork.travel"};
        int scoreArray [] = new int[]{getLineScore, danceScore, undanceScore, travelScore};
        int totalScoreArray[] = new int[]{2,3,3,4};
        printSummary(testNameArray, scoreArray, totalScoreArray);


    }

    private static void printSummary(String [] testNames, int[] scores, int [] totalScores)
    {
        System.out.println("**********************************************\n" +
                "****** Test Summary for TrainNetwork *****\n" +
                "**********************************************");

        for (int i = 0; i< scores.length; i++)
        {
            System.out.println(testNames[i] + " : Test passed - "+scores[i]+"/"+totalScores[i]);
        }

    }
    //function to create makeshift lines to test
    public static TrainLine createMakeshiftLines(TrainStation [] stationArray, String name, boolean goingRight)
    {
        String [] stationNamearray = new String[stationArray.length];

        for (int i=0; i < stationArray.length ; i++)
        {
            stationNamearray[i] = stationArray[i].getName();
        }
        TrainLine newLine = new TrainLine(stationNamearray, name, goingRight);
        TrainStation current = newLine.getLeftTerminus();
        for (int i = 0 ; i < stationArray.length; i++)
        {
            if (stationArray[i].hasConnection)
            {
                current.hasConnection = true;
                TrainStation dummyLeft = new TrainStation("dummy1");
                dummyLeft.setLeftTerminal();
                TrainStation dummyRight = new TrainStation("dummy2");
                dummyRight.setRightTerminal();
                dummyLeft.setRight(dummyRight);
                dummyRight.setLeft(dummyLeft);
                current.setConnection(new TrainLine(dummyLeft, dummyRight, stationArray[i].getTransferLine().getName(), true), new TrainStation(stationArray[i].getTransferStation().getName()));
            }
            current = current.getRight();
        }

        return newLine;
    }

    public static TrainNetwork generateTrainNetwork1() {
        //creating line 1

        TrainStation s1 = new TrainStation("1.Little Whinging");
        TrainStation s5 = new TrainStation("5.St-Mungos");

        s1.setRight(s5);
        s5.setLeft(s1);

        TrainLine l1 = new TrainLine(s1, s5, "Scarlet", true);

        TrainStation s2 = new TrainStation("2.Wizard Hat");
        l1.addStation(s2);
        TrainStation s3 = new TrainStation("3.Hogsmeade");
        l1.addStation(s3);
        TrainStation s4 = new TrainStation("4.Diagon Alley- 1/3");
        l1.addStation(s4);

        /*
        TrainStation s1 = new TrainStation("1.Little Whinging");
        TrainStation s5 = new TrainStation("5.St-Mungos");

        TrainStation s2 = new TrainStation("2.Wizard Hat");
        TrainStation s3 = new TrainStation("3.Hogsmeade");
        TrainStation s4 = new TrainStation("4.Diagon Alley- 1/3");

        TrainLine l1 = new TrainLine(new TrainStation[]{s1, s2, s3, s4, s5}, "Scarlet", true);
*/

        //creating line 2
        TrainStation t1 = new TrainStation("1.Gringott's");
        TrainStation t5 = new TrainStation("5.Leaky Cauldron");


        TrainStation t2 = new TrainStation("2.Diagon Alley - 2/3");
        TrainStation t3 = new TrainStation("3.Ollivander's");
        TrainStation t4 = new TrainStation("4.King's Cross - 3/5");

        TrainLine l2 = new TrainLine(new TrainStation[]{t1, t2, t3, t4, t5}, "Grey", true);


        s4.setConnection(l2, t2);
        t2.setConnection(l1, s4);

        //creating line 3
        TrainStation u1 = new TrainStation("1.King's Cross - 4/5");
        TrainStation u5 = new TrainStation("5.Hogwarts");
        TrainStation u2 = new TrainStation("2.Ministry of Magic");
        TrainStation u3 = new TrainStation("3.Snowy Owl");
        TrainStation u4 = new TrainStation("4.Godric's Hollow");


        TrainLine l3 = new TrainLine(new TrainStation[]{u1, u2, u3, u4, u5}, "Purple", true);

        u1.setConnection(l2, t4);
        t4.setConnection(l3, u1);


        TrainNetwork tNet = new TrainNetwork(1);
        TrainLine[] lines = {l1, l2, l3};
        tNet.addLines(lines);



        return tNet;

    }

}
