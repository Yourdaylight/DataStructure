package FinalProject_Template;

import java.util.Hashtable;

/**
 * @Author lzh
 * @Date 2020/5/22 17:06
 */
public class test {
    public static void main(String[] args){
        MyHashTable2 table2=new MyHashTable2(13, (float) 0.9);
        table2.put("1","a");
        table2.put("2","b");
        table2.put("3","c");
        table2.put("4","d");
        table2.put("5","e");
        table2.put("6","f");
        table2.put("7","g");
        table2.put("8","h");
        table2.put("9","i");
        table2.put("10","j");
        table2.put("11","k");
        table2.put("12","l");
        table2.put("12","m");
        table2.put("14","n");
        table2.put("15","o");
        System.out.println(table2);
        System.out.println(table2.get("15"));
    }
}
