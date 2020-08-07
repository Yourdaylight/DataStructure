package FinalProject_Template;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class HHH {


    public static void main(String[] args) {
        MyHashTable table = new MyHashTable(2);
        table.put("lzh1","111");

        table.put("lzh2","222");

        String[] ss={"1","2","3","111"};
        ArrayList list=new ArrayList();
        list.addAll(Arrays.asList(ss));


        table.put("lzh3","333");
        table.put("lzh4","444");
        table.put("lzh5","555");
        table.put("lzh6","666");
        table.put("lzh7","777");
        table.put("lzh8","888");
        table.put("lzh9","999");
        table.put("lzh10","1000");
        table.put("lzh11","1011");
        table.put("lzh7","込亜込込込");
        Iterator itr=table.iterator();
//        while (itr.hasNext())
//            System.out.println(itr.next());
        ArrayList a= MyHashTable.slowSort(table);
        System.out.println(a);
        Arrays.asList("111,222");
        String aaa="2020-01-023";
        List<String> sss=Arrays.asList(aaa.split(","));
//        System.out.println(table);
    }
}
