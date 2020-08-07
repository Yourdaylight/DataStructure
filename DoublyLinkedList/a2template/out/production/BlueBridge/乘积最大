import java.util.*;
public class 乘积最大 {
	public static String str;
	
	//分解字符串为数字
	public static int parse(int begin,int end)
	{
		int sum=0;
		for(int i=begin-1;i<=end-1;i++)
			sum=sum*10+(str.charAt(i)-'0');
		return sum;
	}
	
	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		int N=sc.nextInt();//数字长度
		int K=sc.nextInt();//*号使用个数
		str=sc.next();
		int[][] dp=new int[N+1][K+1];//n个数包含k个*所能达到的最大乘积。
		
		for(int i=1;i<=N;i++) {
			dp[i][0]=parse(1,i);
			System.out.println(i+" "+dp[i][0]);
		}
		
		for(int i=2;i<=N;i++)
		{//从第2个字符串前开始插入,i为字符串长度
			System.out.println("字符串长度为"+i);
			for(int j=1;j<=Math.min(i-1, K);j++)
			{//插入*号,*号个数最多是字符串数-1
				for(int k=j;k<N;k++)
				{
					int multipile=parse(k+1,i);//*后面的数
			
					System.out.println("即将乘的值是："+multipile);
					dp[i][j]=Math.max(dp[i][j],dp[k][j-1]* multipile);
				}
			}
		}
		
		System.out.println(dp[N][K]);
	}
}
