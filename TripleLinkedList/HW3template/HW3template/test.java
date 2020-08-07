public class test {
    public static void main(String[] args)
    {
        CatInfo cat1=new CatInfo("Alice",87,50,100,12);
        CatInfo cat2=new CatInfo("Felix",85,60,120,12);
        CatInfo cat3=new CatInfo("Bob",88,60,100,12);
        CatInfo cat4=new CatInfo("Hilda",95,55,100,12);
        CatInfo cat5=new CatInfo("Eleamor",85,45,100,12);
        CatInfo cat6=new CatInfo("CoCo",87,55,100,12);
        CatInfo cat7=new CatInfo("Doughbut",85,50,100,12);

        CatTree Tree=new CatTree(cat1);
        Tree.addCat(cat2);
        Tree.addCat(cat3);
        Tree.addCat(cat4);
        Tree.addCat(cat5);
        Tree.addCat(cat6);
        Tree.addCat(cat7);
        String str=Tree.toString();
        System.out.println(str);
    }
}
