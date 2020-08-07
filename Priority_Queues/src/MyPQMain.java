package src;

import java.awt.Point;
import java.util.Comparator;

public class MyPQMain {
    public static class intComparator implements Comparator<MyEntry>{

        @Override
        public int compare(MyEntry o1, MyEntry o2) {

            try {
                double comp;
                comp=(double)o1.getValue()-(double)o2.getValue();
                if (comp>0)
                    return 1;
                else if(comp==0)
                    return 0;
                else
                    return -1;
            }catch (Exception e){
                int comp;
                comp=(int)o1.getValue()-(int)o2.getValue();
                if (comp>0)
                    return 1;
                else if(comp==0)
                    return 0;
                else
                    return -1;
            }

        }
    }

    public static class intComparator2 implements Comparator<MyEntry>{

        @Override
        public int compare(MyEntry o1, MyEntry o2) {
            double comp=(double)o1.getValue()-(double)o2.getValue();
            if (comp<0)
                return 1;
            else if(comp==0)
                return 0;
            else
                return -1;
        }
    }

    public static double sqrt(int x,int y){
        return Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
    }
    public static void main(String[] args){
        Point a=new Point(5,4);
        Point b=new Point(2,7);
        Point c=new Point(9,5);
        Point d=new Point(3,1);
        Point e=new Point(7,2);
        Point f=new Point(9,7);
        Point g=new Point(1,4);
        Point h=new Point(4,3);
        Point i=new Point(8,2);
        Point j=new Point(4,8);
        Point[] points={a,b,c,d,e,f,g,h,i,j};
        intComparator c1=new intComparator();
        MyPQ pq=new MyPQ(c1);
        pq.insert(new Integer(30),null);
        pq.insert(new Integer(10),null);
        pq.insert(new Integer(20),null);
        System.out.println("Assignment1 output:");
        System.out.println(pq.removeMin().getKey());
        System.out.println(pq.removeMin().getKey());
        System.out.println(pq.removeMin().getKey());


        pq=new MyPQ(10,new intComparator());
        for (int n=0;n<points.length;n++){
            Point p=points[n];
            pq.insert(p,sqrt(p.x,p.y));
        }

        System.out.println("\nAssignment2 1):from near to far...");
        for (int n=0;n<points.length;n++){
            System.out.println(pq.removeMin().getKey());
        }

        pq=new MyPQ(10,new intComparator());
        for (int n=0;n<points.length;n++){
            Point p=points[n];
            pq.insert(p,sqrt(p.x,p.y));
        }

        System.out.println("\nAssignment2 2):from far to near...");
        for (int n=0;n<points.length;n++){
            System.out.println(pq.removeMin().getKey());
        }
    }
}
