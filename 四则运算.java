import java.util.ArrayList;
import java.util.Scanner;

public class assignment1 {
    public char[] op={'+','-','*','/','(',')'};

    public boolean isOp(char c){
        for(int i=0;i<op.length;i++){
            if(op[i]==c)
                return true;
        }
        return false;
    }

    /*
    中缀转后缀表达式
    * @para list :输入的表达式
    * */
    public ArrayList<Character> InfixToPostfix(char[] list){
        ArrayList<Character> Postfixlist = new ArrayList<Character>();//存放后缀表达式
        ArrayStack<Character> stack=new ArrayStack(list.length);
        //stack.push('#');
        for(int i=0;i<list.length;i++){
            char s = list[i];
            if(s=='('){
                stack.push(s);
            }else if(s==')'){
                while(!(stack.peek()=='(')){
                    Postfixlist.add(stack.pop());
                }
                stack.pop();
                if (stack.peek()=='/' || stack.peek()=='*')
                    Postfixlist.add(stack.pop());

            }else if(s=='*'||s=='/'){
                stack.push(s);
            }else if(s=='+'||s=='-'){
                if(!stack.isEmpty()){
                    try {
                        while(!stack.isEmpty() && !(stack.peek()=='(') ){
                            Postfixlist.add(stack.pop());
                        }
                        stack.push(s);
                    }catch (Exception e){
                        continue;
                    }

                }else{
                    stack.push(s);
                }
            }else{
                Postfixlist.add(s);
            }
            if(i==list.length-1){
                while(!stack.isEmpty()){
                    Postfixlist.add(stack.pop());
                }
            }
        }
        System.out.println(Postfixlist.size());
        return Postfixlist;
    }

    /**
     * 后缀表达式计算
     */
    public double doCal(ArrayList<Character> list){
        ArrayStack<Double> stack=new ArrayStack(list.size());

        for(int i=0;i<list.size();i++){
            char s=list.get(i);
            int t=0;
            if(!isOp(s)){
                stack.push((double)s-'0');
            }else{
                if(s=='+'){
                    double a1 = stack.pop();
                    double a2 = stack.pop();

                    double v = a2+a1;
                    System.out.println(a2+"+"+a1+"="+v);
                    stack.push(v);
                }else if(s=='-'){
                    double a1 = stack.pop();
                    double a2 = stack.pop();
                    double v = a2-a1;
                    System.out.println(a2+"-"+a1+"="+v);
                    stack.push(v);
                }else if(s=='*'){
                    double a1 = stack.pop();
                    double a2 = stack.pop();
                    double v = a2*a1;
                    System.out.println(a2+"*"+a1+"="+v);
                    stack.push(v);
                }else if(s=='/'){
                    try {
                        double a1 = stack.pop();
                        double a2 = stack.pop();
                        double v = a2 / a1;
                        System.out.println(a2 + "/" + a1 + "=" + v);
                        stack.push(v);
                    }catch (Exception e){
                        continue;
                    }
                }
            }
        }
        return stack.pop();
    }
    public static void main(String[] args){
        String input;
        assignment1 a1=new assignment1();
        Scanner sc=new Scanner(System.in);
        input=sc.nextLine();
        char[] l1=input.toCharArray();
        //反转输入
        int start=0;
        for(int i=input.length()-1;i>=0;i--) {
            if(input.charAt(i)=='(')
                l1[start]=')';
            else if(input.charAt(i)==')')
                l1[start]='(';
            else l1[start]=input.charAt(i);
            start+=1;
        }

        //转后缀表达式
        ArrayList<Character> l2=a1.InfixToPostfix(l1);
        System.out.println(l2);
        System.out.println(a1.doCal(l2));
    }
}
//1+3*(4+1)/4
//4/(1+4)*3+1
//4+2/(1+(1+3)*4)