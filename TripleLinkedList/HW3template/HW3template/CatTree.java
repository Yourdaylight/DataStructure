import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException; 


public class CatTree implements Iterable<CatInfo>{
    public CatNode root;
    
    public CatTree(CatInfo c) {
        this.root = new CatNode(c);
    }
    
    private CatTree(CatNode c) {
        this.root = c;
    }
    
    
    public void addCat(CatInfo c)
    {
        this.root = root.addCat(new CatNode(c));
    }
    
    public void removeCat(CatInfo c)
    {
        this.root = root.removeCat(c);
    }
    
    public int mostSenior()
    {
        return root.mostSenior();
    }
    
    public int fluffiest() {
        return root.fluffiest();
    }
    
    public CatInfo fluffiestFromMonth(int month) {
        return root.fluffiestFromMonth(month);
    }
    
    public int hiredFromMonths(int monthMin, int monthMax) {
        return root.hiredFromMonths(monthMin, monthMax);
    }
    
    public int[] costPlanning(int nbMonths) {
        return root.costPlanning(nbMonths);
    }
    
    
    
    public Iterator<CatInfo> iterator()
    {
        return new CatTreeIterator();
    }
    
    
    class CatNode {
        
        CatInfo data;
        CatNode senior;
        CatNode same;
        CatNode junior;
        
        public CatNode(CatInfo data) {
            this.data = data;
            this.senior = null;
            this.same = null;
            this.junior = null;
        }
        
        public String toString() {
            String result = this.data.toString() + "\n";
            if (this.senior != null) {
                result += "more senior " + this.data.toString() + " :\n";
                result += this.senior.toString();
            }
            if (this.same != null) {
                result += "same seniority " + this.data.toString() + " :\n";
                result += this.same.toString();
            }
            if (this.junior != null) {
                result += "more junior " + this.data.toString() + " :\n";
                result += this.junior.toString();
            }
            return result;
        }
        
        
        public CatNode addCat(CatNode c) {
            // ADD YOUR CODE HERE

            int compareResult=0;

            if(this==null) {
                root=c;
                return this;
            }
            CatNode current=this;
            CatNode parentNode=null;
            while(current!=null){
                parentNode=current;
                if(current.data.monthHired>c.data.monthHired) {
                    //添加senior
                    current = current.senior;
                    //若年龄小的节点为空，则返回
                    if(current==null){
                        parentNode.senior=c;
                        return this;
                    }
                } else if(current.data.monthHired<c.data.monthHired){
                    //添加junior
                    current=current.junior;
                    if(current==null){
                        parentNode.junior=c;
                        return this;
                    }
                } else{
                    //添加same
                    current=current.same;
                    if(current==null || c.data.furThickness>parentNode.data.furThickness)
                    //此时添加中间节点，判断待添加点的furthickness是否小于current
                    {
                        if(c.data.furThickness<=parentNode.data.furThickness)
                            //小于current则直接赋值
                            parentNode.same=c;
                        else{
                            //否则交换c与current的data
                            parentNode.same=c;
                            CatInfo temp=c.data;
                            c.data=parentNode.data;
                            parentNode.data=temp;
                        }
                        return this;
                    } else{
                        //对same分支的所有节点降序排序
                        while (true){
                            if(current==null) {
                                current = c;
                                parentNode.same=c;
                                return this;
                            }
//                            else if(c.data.furThickness>parentNode.data.furThickness)
//                            {
//                                parentNode.same=c;
//                                CatInfo temp=c.data;
//                                c.data=parentNode.data;
//                                parentNode.data=temp;
//                            }
                            else if((c.data.furThickness<=parentNode.data.furThickness) && (c.data.furThickness>=current.data.furThickness))
                            {
                                CatNode temp=parentNode;
                                parentNode.same=c;
                                c.same=current;
                                return this;
                            }
                            else {
                                parentNode=current;
                                current=current.same;
                            }

                        }

                    }
                }
            }
            return this; // DON'T FORGET TO MODIFY THE RETURN IF NEED BE
        }
        
        
        public CatNode removeCat(CatInfo c) {
            // ADD YOUR CODE HERE
            CatNode root=this;
            CatNode current=this;
            CatNode parentNode=this;

            boolean isSenior=false;
            boolean isJunior=false;


            //定位要删除的节点的位置，找到待删除的节点为current
            while (!current.data.equals(c)){
                parentNode=current;
                if(current.data.monthHired>c.monthHired) {
                    current = current.senior;
                    isSenior=true;
                }
                else if(current.data.monthHired<c.monthHired) {
                    current = current.junior;
                    isJunior=true;
                }
                else
                    current=current.same;
                if(current==null)
                    return this;
            }

            //1、该节点为叶子节点
            if(current.senior==null && current.junior==null && current.same==null) {
                if (current == root) {

                    return root;
                }
                else if(isJunior)
                    parentNode.junior=null;
                else if(isSenior)
                    parentNode.senior=null;
                else
                    parentNode.same=null;
                return this;
            }
            //2、该节点有1个节点
            if(current.junior==null && current.senior==null && current.same!=null){
                //same not null
                if(root==current){
                    root=current.same;
                    return root;
                }else if(isJunior){
                    parentNode.junior=current.same;
                } else if(isSenior){
                    parentNode.senior=current.same;
                }else {
                    parentNode.same=current.same;
                }
            } else if(current.junior==null && current.senior!=null && current.same==null) {
                //senior not null
                if (root == current) {
                    root = current.senior;
                    return root;
                } else if (isJunior) {
                    parentNode.junior = current.senior;
                } else if (isSenior) {
                    parentNode.senior = current.senior;
                } else {
                    parentNode.same = current.senior;
                }
            } else if(current.junior!=null && current.senior==null && current.same==null) {
                //junior not null
                if (root == current) {
                    root = current.same;
                    return root;
                } else if (isJunior) {
                    parentNode.junior = current.junior;
                } else if (isSenior) {
                    parentNode.senior = current.junior;
                } else {
                    parentNode.same = current.junior;
                }
            }

            //3、该节点有2个及以上的节点
            else{
                //首先获取当前节点的后继节点
                CatNode successorParent=current;
                CatNode successor=current;
                CatNode sCurrent=current;

                if(current.same!=null){
                    sCurrent=current.same;
                    current.data=sCurrent.data;

                    while (sCurrent.same!=null){
                        successorParent=successor;
                        successor=sCurrent;
                        sCurrent=sCurrent.same;
                    }
                    successor.same=null;
                    return this;
                }
                else if(current.senior!=null){
                    sCurrent=current.senior;
                    sCurrent.junior.junior=current.junior;
                    root=sCurrent;
                    this.data=root.data;
                    this.junior=root.junior;
                    this.same=root.same;
                    this.senior=root.senior;
                    return this;
                }
                else if(current.junior!=null) {
                    sCurrent = current.junior;
                    //遍历右子树找到最小值
                    while(sCurrent!=null){
                        successorParent=successor;
                        successor=sCurrent;
                        sCurrent=sCurrent.senior;
                    }
                    //判断待删除的节点是否与后继节点相邻
                    if(sCurrent!=current.junior){
                        //更新后继节点的右子节点
                        successorParent.senior=successor.junior;
                        //将后继节点与删除节点交换
                        successor.junior=current.junior;
                    }
                }


                //获取删除节点的后继结点
                if (root == current) {
                    root = successor;
                } else if (isSenior) {
                    parentNode.senior = successor;
                } else {
                    parentNode.junior = successor;
                }
                return this;
            }
            return this; // DON'T FORGET TO MODIFY THE RETURN IF NEED BE
        }
        
        
        public int mostSenior() {
            // ADD YOUR CODE HERE
            int min=this.data.monthHired;
            CatNode temp=this;
            while(temp.senior!=null){
                temp=temp.senior;
                if(temp.data.monthHired<min)
                    min=temp.data.monthHired;
            }
            return min; //CHANGE THIS
        }
        
        public int fluffiest() {
            // ADD YOUR CODE HERE
            int max=this.data.furThickness;
            CatNode temp=this;

            //列表模拟栈遍历所有节点
            ArrayList<CatNode> s=new ArrayList<CatNode>();

            while(temp !=null || s.size()!=0){
                //先遍历左节点
                while(temp!=null){
                    if(temp.data.furThickness>max)
                        max=temp.data.furThickness;
                    s.add(temp);
                    temp = temp.senior;
                }
                if(s.size()!=0){
                    temp = s.get(s.size()-1);
                    s.remove(s.size()-1);
                    if(temp.data.furThickness>max)
                        max=temp.data.furThickness;
                    if(temp.same!=null)
                        if(temp.same.data.furThickness>max)
                            max=temp.same.data.furThickness;
                    temp = temp.junior;
                }


            }


            return max; // DON'T FORGET TO MODIFY THE RETURN IF NEED BE
        }
        
        
        public int hiredFromMonths(int monthMin, int monthMax) {
            // ADD YOUR CODE HERE
            if(monthMin>monthMax)
                return 0;
            int max=0;
            CatNode temp=this;

            //列表模拟栈遍历所有节点
            ArrayList<CatNode> s=new ArrayList<CatNode>();

            while(temp !=null || s.size()!=0){
                //先遍历左节点
                while(temp!=null){
                    if(temp.data.monthHired>=monthMin && temp.data.monthHired<=monthMax)
                        max+=1;
                    s.add(temp);
                    temp = temp.senior;

                }

                //左节点遍历完毕，遍历右节点
                if(s.size()!=0){
                    temp = s.get(s.size()-1);
                    s.remove(s.size()-1);

//                    若中间节点不为空，计算中间节点个数
                    if(temp.same!=null) {
                        CatNode Same=temp.same;
                        while (Same != null) {
                            if (Same.data.monthHired >= monthMin && Same.data.monthHired <= monthMax)
                                max += 1;
                            Same=Same.same;
                        }
                    }
                    temp = temp.junior;
                }

            }
            return max; // DON'T FORGET TO MODIFY THE RETURN IF NEED BE
            
        }
        
        public CatInfo fluffiestFromMonth(int month) {
            // ADD YOUR CODE HERE

            CatNode temp=this;
            CatInfo res=null;
            int fur=0;
            //列表模拟栈遍历所有节点
            ArrayList<CatNode> s=new ArrayList<CatNode>();

            while(temp !=null || s.size()!=0){
                //先遍历左节点
                while(temp!=null){
                    if(temp.data.monthHired==month && temp.data.furThickness>fur) {
                        res = temp.data;
                        fur=res.furThickness;
                    }
                    s.add(temp);
                    temp = temp.senior;
                }
                if(s.size()!=0){
                    temp = s.get(s.size()-1);
                    s.remove(s.size()-1);

                }

                    temp = temp.junior;

            }

            return res;// DON'T FORGET TO MODIFY THE RETURN IF NEED BE
        }
        
        public int[] costPlanning(int nbMonths) {
            // ADD YOUR CODE HERE

            int[] cost=new int[nbMonths];
            int catNum=0;
            CatTreeIterator iter=new CatTreeIterator();
            ArrayList<CatInfo> cats=new ArrayList<CatInfo>();
            cats=iter.list;
            for(int i=0;i<nbMonths;i++){
                int months=243+i;
                for(CatInfo cat:cats){
                    if(cat.nextGroomingAppointment==months)
                        cost[i]+=cat.expectedGroomingCost;
                }
            }

            return cost; // DON'T FORGET TO MODIFY THE RETURN IF NEED BE
        }
        
    }
    
    private class CatTreeIterator implements Iterator<CatInfo> {
        // HERE YOU CAN ADD THE FIELDS YOU NEED
        public ArrayList<CatInfo> list=new ArrayList<CatInfo>();
        public CatInfo nextCat=root.data;
        private CatNode Root=root;

        public CatTreeIterator() {
            //YOUR CODE GOES HERE
            CatNode temp=Root;
            nextCat=Root.data;
            //列表模拟栈遍历所有节点
            ArrayList<CatNode> s=new ArrayList<CatNode>();
            while(temp !=null || s.size()!=0){
                //先遍历左节点
                while(temp!=null){
                    s.add(temp);
                    list.add(temp.data);
                    temp = temp.senior;
                }

                //左节点遍历完毕，遍历右节点
                if(s.size()!=0){
                    temp = s.get(s.size()-1);
                    s.remove(s.size()-1);

//                    若中间节点不为空，计算中间节点个数
                    if(temp.same!=null) {
                        CatNode Same=temp.same;
                        while (Same != null) {
                            list.add(Same.data);
                            Same=Same.same;
                        }
                    }
                    temp = temp.junior;
                }

            }

            for(int i=0;i<list.size()-1;i++){
                if(list.get(i).monthHired==list.get(i+1).monthHired)
                {

                    if(list.get(i).furThickness<list.get(i+1).furThickness)
                    {
                        //deep copy the cats we want to swap
                        CatInfo exchange1=new CatInfo(list.get(i).name,list.get(i).monthHired,list.get(i).furThickness,list.get(i).nextGroomingAppointment,list.get(i).expectedGroomingCost);
                        CatInfo exchange2=new CatInfo(list.get(i+1).name,list.get(i+1).monthHired,list.get(i+1).furThickness,list.get(i+1).nextGroomingAppointment,list.get(i+1).expectedGroomingCost);

                       list.set(list.indexOf(list.get(i)),exchange2);
                       list.set(list.indexOf(list.get(i+1)),exchange1);

                    }

                }
            }

            for(int i=0;i<list.size()-1;i++){
                for(int j=i;j<list.size()-1;j++){
                    if(list.get(i).monthHired>list.get(j).monthHired){
                        CatInfo exchange1=new CatInfo(list.get(i).name,list.get(i).monthHired,list.get(i).furThickness,list.get(i).nextGroomingAppointment,list.get(i).expectedGroomingCost);
                        CatInfo exchange2=new CatInfo(list.get(j).name,list.get(j).monthHired,list.get(j).furThickness,list.get(j).nextGroomingAppointment,list.get(j).expectedGroomingCost);

                        list.set(i,exchange2);
                        list.set(j,exchange1);


                    }
                }

            }


        }
        
        public CatInfo next(){
            //YOUR CODE GOES HERE
            if(!hasNext())
                return null;

            CatInfo cat=nextCat;
            if(cat==list.get(list.size()-1))
                nextCat=null;
//            else if(nextCat==null)
//                nextCat=root.data;
            else
                nextCat=list.get(list.indexOf(cat)+1);
            return nextCat; // DON'T FORGET TO MODIFY THE RETURN IF NEED BE
        }
        
        public boolean hasNext() {
            //YOUR CODE GOES HERE
            return (nextCat!=null); // DON'T FORGET TO MODIFY THE RETURN IF NEED BE
        }
    }
    
}

