package a2template.src;

import java.util.Arrays;
import java.util.Comparator;


public class MiniTester {
    int scale = 1;

    private TrainLine l1, l2, l3, l4, l5, l6;
    private TrainStation[] a1, a2, a3, a4, a5, a6;

    public MiniTester() {
    }

    private String gradeString(int score, int maxScore, String comment) {
        return comment + "Passed: " + Integer.toString(score * scale) + "/" + Integer.toString(maxScore * scale);
    }

    public void write(String string) {
        System.out.print(string + "\n");
    }

    class SortByName implements Comparator<TrainStation> {
        // Used for sorting in ascending order of
        // string name
        public int compare(TrainStation a, TrainStation b) {
            return a.getName().compareTo(b.getName());
        }
    }

    // TEST MAPS

    private void generateLinesOnly0() {

        // creating line 1
        TrainStation s1 = new TrainStation("1.Little Whinging");
        TrainStation s5 = new TrainStation("5.St Mungo�s");

        s1.setRight(s5);
        s5.setLeft(s1);

        l1 = new TrainLine(s1, s5, "Scarlet", true);

        TrainStation s2 = new TrainStation("2.Wizard Hat");
        l1.addStation(s2);
        TrainStation s3 = new TrainStation("3.Hogsmeade");
        l1.addStation(s3);
        TrainStation s4 = new TrainStation("4.Diagon Alley- 1/3");
        l1.addStation(s4);

        TrainStation[] a1 = {s1, s2, s3, s4, s5};
        this.a1 = a1;

        // creating line 2
        TrainStation t1 = new TrainStation("1.Gringotts");
        TrainStation t5 = new TrainStation("5.Leaky Cauldron");

        t1.setRight(t5);
        t5.setLeft(t1);

        l2 = new TrainLine(t1, t5, "Grey", true);

        TrainStation t2 = new TrainStation("2.Diagon Alley - 2/3");
        l2.addStation(t2);
        TrainStation t3 = new TrainStation("3.Ollivanders");
        l2.addStation(t3);
        TrainStation t4 = new TrainStation("4.King's Cross - 3/5");
        l2.addStation(t4);

        TrainStation[] a2 = {t1, t2, t3, t4, t5};
        this.a2 = a2;

        s4.setConnection(l2, t2);
        t2.setConnection(l1, s4);

        // creating line 3
        TrainStation u1 = new TrainStation("1.King's Cross - 4/5");
        TrainStation u5 = new TrainStation("5.Hogwarts");

        u1.setRight(u5);
        u5.setLeft(u1);

        l3 = new TrainLine(u1, u5, "Purple", true);

        TrainStation u2 = new TrainStation("2.Ministry of Magic");
        l3.addStation(u2);
        TrainStation u3 = new TrainStation("3.Snowy Owl");
        l3.addStation(u3);
        TrainStation u4 = new TrainStation("4.Godric's Hollow");
        l3.addStation(u4);

        TrainStation[] a3 = {u1, u2, u3, u4, u5};
        this.a3 = a3;

        u1.setConnection(l2, t4);
        t4.setConnection(l3, u1);
    }

    private void generateLinesOnly0Unsorted() {

        // creating line 1
        TrainStation s1 = new TrainStation("5.St Mungo's");
        TrainStation s5 = new TrainStation("1.Little Whinging");

        s1.setRight(s5);
        s5.setLeft(s1);

        l4 = new TrainLine(s1, s5, "Scarlet", true);

        TrainStation s2 = new TrainStation("4.Diagon Alley- 1/3");
        l4.addStation(s2);
        TrainStation s3 = new TrainStation("3.Hogsmeade");
        l4.addStation(s3);
        TrainStation s4 = new TrainStation("2.Wizard Hat");
        l4.addStation(s4);

        TrainStation[] a4 = {s1, s2, s3, s4, s5};
        this.a4 = a4;

        // creating line 2
        TrainStation t1 = new TrainStation("3.Ollivanders");
        TrainStation t5 = new TrainStation("4.King's Cross - 3/5");

        t1.setRight(t5);
        t5.setLeft(t1);

        l5 = new TrainLine(t1, t5, "Grey", true);

        TrainStation t2 = new TrainStation("2.Diagon Alley - 2/3");
        l5.addStation(t2);
        TrainStation t3 = new TrainStation("1.Gringotts");
        l5.addStation(t3);
        TrainStation t4 = new TrainStation("5.Leaky Cauldron");
        l5.addStation(t4);

        TrainStation[] a5 = {t1, t2, t3, t4, t5};
        this.a5 = a5;

        s2.setConnection(l5, t2);
        t2.setConnection(l4, s2);

        // creating line 3
        TrainStation u1 = new TrainStation("5.Hogwarts");
        TrainStation u5 = new TrainStation("1.King's Cross - 4/5");

        u1.setRight(u5);
        u5.setLeft(u1);

        l6 = new TrainLine(u1, u5, "Purple", true);

        TrainStation u2 = new TrainStation("2.Ministry of Magic");
        l6.addStation(u2);
        TrainStation u3 = new TrainStation("3.Snowy Owl");
        l6.addStation(u3);
        TrainStation u4 = new TrainStation("4.Godric's Hollow");
        l6.addStation(u4);

        TrainStation[] a6 = {u1, u2, u3, u4, u5};
        this.a6 = a6;

        u5.setConnection(l5, t4);
        t4.setConnection(l6, u5);
    }

    // TEST CASES

    public int testGetSize0(int testIdx) {
        String comment = "[" + testIdx + "]: Test get size.\n";
        int maxScore = 1;
        int grade = 0;

        try {
            generateLinesOnly0();

            if (l1.getSize() == 5 && l2.getSize() == 5 && l3.getSize() == 5) {
                grade += 1;
            }

            write(gradeString(grade, maxScore, comment));
        } catch (Exception e) {
            comment = comment + "Exception Found: " + e.toString() + "\n";
            e.printStackTrace();
            write(gradeString(0, maxScore, comment));
        }

        return grade;
    }

    public int testTravelOneStation0(int testIdx) {
        String comment = "[" + testIdx + "]: Test travelOneStation. Regular case.\n";
        int maxScore = 2;
        int grade = 0;

        try {
            generateLinesOnly0();

            if (l1.travelOneStation(a1[1], a1[0]).equals(a1[2])) {
                grade += 1;
            }

            l1.reverseDirection();

            if (l1.travelOneStation(a1[1], a1[2]).equals(a1[0])) {
                grade += 1;
            }

            write(gradeString(grade, maxScore, comment));
        } catch (Exception e) {
            comment = comment + "Exception Found: " + e.toString() + "\n";
            e.printStackTrace();
            write(gradeString(0, maxScore, comment));
        }

        return grade;
    }

    public int testTravelOneStation1(int testIdx) {
        String comment = "[" + testIdx + "]: Test travelOneStation. Transfer.\n";
        int maxScore = 2;
        int grade = 0;

        try {
            generateLinesOnly0();

            if (l1.travelOneStation(a1[3], a1[2]).equals(a2[1])) {
                grade += 1;
            }

            l2.reverseDirection();

            if (l2.travelOneStation(a2[1], a2[0]).equals(a1[3])) {
                grade += 1;
            }

            write(gradeString(grade, maxScore, comment));
        } catch (Exception e) {
            comment = comment + "Exception Found: " + e.toString() + "\n";
            e.printStackTrace();
            write(gradeString(0, maxScore, comment));
        }

        return grade;
    }

    public int testGetNext0(int testIdx) {
        String comment = "[" + testIdx + "]: Test getNext. Station not found\n";
        int maxScore = 1;
        int grade = 0;

        try {
            generateLinesOnly0();

            l1.getNext(a2[1]);

        } catch (StationNotFoundException e) {
            grade += 1;
            write(gradeString(grade, maxScore, comment));
        } catch (Exception e) {
            comment = comment + "Exception Found: " + e.toString() + "\n";
            e.printStackTrace();
            write(gradeString(0, maxScore, comment));
        }

        return grade;
    }

    public int testGetNext1(int testIdx) {
        String comment = "[" + testIdx + "]: Test getNext. Typical case\n";
        int maxScore = 2;
        int grade = 0;

        try {
            generateLinesOnly0();

            if (l1.getNext(a1[1]).equals(a1[2])) {
                grade += 1;
            }

            l1.reverseDirection();

            if (l1.getNext(a1[1]).equals(a1[0])) {
                grade += 1;
            }

            write(gradeString(grade, maxScore, comment));
        } catch (Exception e) {
            comment = comment + "Exception Found: " + e.toString() + "\n";
            e.printStackTrace();
            write(gradeString(0, maxScore, comment));
        }

        return grade;
    }


    public int testFindStation0(int testIdx) {
        String comment = "[" + testIdx + "]: Test findStation. Station exists.\n";
        int maxScore = 1;
        int grade = 0;

        try {
            generateLinesOnly0();

            if (l3.findStation(a3[3].getName()).equals(a3[3])) {
                grade += 1;
            }

            write(gradeString(grade, maxScore, comment));
        } catch (Exception e) {
            comment = comment + "Exception Found: " + e.toString() + "\n";
            e.printStackTrace();
            write(gradeString(0, maxScore, comment));
        }

        return grade;
    }

    public int testGetLineArray(int testIdx) {
        String comment = "[" + testIdx + "]: Test getLineArray.\n";
        int maxScore = 1;
        int grade = 0;

        try {
            generateLinesOnly0();

            TrainStation[] array = l1.getLineArray();

            assessLine(testIdx, l1);

            boolean arrayEqual = true;
            if (array.length == a1.length) {

                for (int i = 0; i < array.length; i++) {
                    if (!array[i].equals(a1[i])) {
                        arrayEqual = false;
                        break;
                    }
                }
            }
            if (arrayEqual) {
                grade += 1;
            }

            write(gradeString(grade, maxScore, comment));
        } catch (Exception e) {
            comment = comment + "Exception Found: " + e.toString() + "\n";
            e.printStackTrace();
            write(gradeString(0, maxScore, comment));
        }

        return grade;
    }

    public int testSortLine(int testIdx) {
        String comment = "[" + testIdx + "]: Test sortLine.\n";
        int maxScore = 3;
        int grade = 0;

        try {
            generateLinesOnly0();
            generateLinesOnly0Unsorted();

            l4.sortLine();
            l5.sortLine();
            l6.sortLine();

            assessLine(testIdx, l4);
            assessLine(testIdx, l5);
            assessLine(testIdx, l6);

            TrainStation[] array1 = l4.lineMap;
            Arrays.sort(array1, this.new SortByName());

            boolean arrayEqual = true;
            if (array1.length == a1.length) {

                TrainStation currentStation = array1[0];
                int index = 0;
                while (!currentStation.isRightTerminal()) {

                    if (!array1[index].equals(currentStation)) {
                        arrayEqual = false;
                        break;
                    }
                    currentStation = currentStation.getRight();

                    index++;
                }
            }

            if (arrayEqual) {
                grade += 1;
            }

            TrainStation[] array2 = l5.lineMap;
            Arrays.sort(array2, this.new SortByName());

            arrayEqual = true;
            if (array2.length == a1.length) {

                TrainStation currentStation = array2[0];
                int index = 0;
                while (!currentStation.isRightTerminal()) {

                    if (!array2[index].equals(currentStation)) {
                        arrayEqual = false;
                        break;
                    }
                    currentStation = currentStation.getRight();

                    index++;
                }
            }

            if (arrayEqual) {
                grade += 1;
            }

            TrainStation[] array3 = l6.lineMap;
            Arrays.sort(array3, this.new SortByName());

            arrayEqual = true;
            if (array3.length == a1.length) {

                TrainStation currentStation = array3[0];
                int index = 0;
                while (!currentStation.isRightTerminal()) {

                    if (!array3[index].equals(currentStation)) {
                        arrayEqual = false;
                        break;
                    }
                    currentStation = currentStation.getRight();

                    index++;
                }
            }

            if (arrayEqual) {
                grade += 1;
            }

            write(gradeString(grade, maxScore, comment));
        } catch (Exception e) {
            comment = comment + "Exception Found: " + e.toString() + "\n";
            e.printStackTrace();
            write(gradeString(0, maxScore, comment));
        }

        return grade;
    }

    public int testShuffleLine(int testIdx) {
        String comment = "[" + testIdx + "]: Test shuffle line.\n";
        int maxScore = 2;
        int grade = 0;

        try {
            generateLinesOnly0();

            l1.shuffleLine();
            l2.shuffleLine();
            l3.shuffleLine();

            assessLine(testIdx, l1);
            assessLine(testIdx, l2);
            assessLine(testIdx, l3);

            TrainStation[] lineMap1 = l1.lineMap;

            boolean arrayEqual = true;

            TrainStation currentStation = l1.getLeftTerminus();
            ;
            int index = 0;
            while (!currentStation.isRightTerminal()) {

                if (!lineMap1[index].equals(currentStation)) {
                    arrayEqual = false;
                    break;
                }
                currentStation = currentStation.getRight();

                index++;
            }

            if (arrayEqual) {
                grade += 1;
            }

            TrainStation[] lineMap2 = l2.lineMap;

            arrayEqual = true;

            currentStation = l2.getRightTerminus();
            index = lineMap2.length - 1;
            while (!currentStation.isLeftTerminal()) {

                if (!lineMap2[index].equals(currentStation)) {
                    arrayEqual = false;
                    break;
                }
                currentStation = currentStation.getLeft();

                index--;
            }

            if (arrayEqual) {
                grade += 1;
            }

            write(gradeString(grade, maxScore, comment));
        } catch (Exception e) {
            comment = comment + "Exception Found: " + e.toString() + "\n";
            e.printStackTrace();
            write(gradeString(0, maxScore, comment));
        }

        return grade;
    }

    public void assessLine(int testIdx, TrainLine l1) {
        String comment = "[" + testIdx + "]: Test null pointers and circular line.\n";
        try {

            TrainStation currentStation = l1.getLeftTerminus();
            TrainStation currentStation1 = l1.getLeftTerminus().getRight();
            while (!currentStation.isRightTerminal()) {

                if (currentStation.equals(currentStation1)) {
                    throw new CircularLineException();
                }

                currentStation = currentStation.getRight();
                if (currentStation1 != null && currentStation1.getRight() != null) {
                    currentStation1 = currentStation.getRight().getRight();
                }
            }

            currentStation = l1.getRightTerminus();
            currentStation1 = l1.getRightTerminus().getLeft();
            while (!currentStation.isLeftTerminal()) {

                if (currentStation.equals(currentStation1)) {
                    throw new CircularLineException();
                }

                currentStation = currentStation.getLeft();
                if (currentStation1 != null && currentStation1.getLeft() != null) {
                    currentStation1 = currentStation.getLeft().getLeft();
                }
            }
        } catch (Exception e) {
            comment = comment + "Exception Found: " + e.toString() + "\n";
            e.printStackTrace();
            write(comment);
        }
    }
}

//Exception for when searching a line for a station and not finding any station of the right name.
class CircularLineException extends RuntimeException {

    public CircularLineException() {
    }

    public String toString() {
        return "CircularLineException";
    }
}
