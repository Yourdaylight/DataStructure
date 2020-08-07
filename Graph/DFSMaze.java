public class DFSMaze {
    static final int[][] maze = {
            {0,0,0,0,0,1,1,1,1,0},
            {0,1,1,1,0,1,1,1,1,0},
            {0,1,1,1,0,0,0,0,0,0},
            {0,0,0,1,1,0,1,1,1,0},
            {0,1,0,0,1,0,1,0,1,0},
            {0,1,1,1,1,0,1,0,0,0},
            {0,0,0,1,1,1,1,1,1,0},
            {1,1,0,0,0,0,0,0,0,0},
            {1,1,1,1,0,1,0,1,1,0},
            {1,1,1,1,0,1,0,0,1,0}
    };
    static int[][] maze2;
    static int[][] result;
    public static void dfsMaze(int x, int y, int[][] maze) {
        /*
         * ��þ���Ĵ�С
         * */
        int m=maze.length;
        int n=maze[0].length;
        //���ý�������
        if (x < 0 || y < 0)
            return;

        //�����յ�
        if (x == m - 1 && y == n - 1) {
            for(int i=0;i<10;i++){
                for(int j=0;j<10;j++){
                    if(result[i][j]==6 ||(i==0 && j==0) ||(i==9 && j==9))
                        System.out.print("*");
                    else
                        System.out.print(result[i][j]);
                }
                System.out.println();
            }
            System.out.println();
            return;
        }

        // �������Խ�磬���� maze[x][y]==1 ��ʾ�����ϰ�
        if (x > m - 1 || y > n - 1 || maze[x][y] ==1)
            return;

        //��ʾ�����ϰ�
        if (maze[x][y] == 1) {
            return; // �ж��Ƿ�ͨ·��Խ��
        }

        result[x][y]=6;
        maze[x][y] = 1; // ���߹���·���
        // ���ĸ���������
        dfsMaze(x + 1, y, maze);  //��������
        dfsMaze(x, y + 1, maze);  //��������
        dfsMaze(x, y - 1, maze);  //��������
        dfsMaze(x - 1, y, maze);  //��������

        // ��·�ߺͱ�ǻָ�����һ�ε�״̬
        result[x][y]=2;
        maze[x][y] = 0;

    }

    public static  void init(){

        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                maze2[i][j]= maze[i][j];
                result[i][j]=maze[i][j];
            }
        }
        maze2[0][0]=1;
    }
    public static void main(String[] args) {
        maze2=new int[10][10];
        result=new int[10][10];

        System.out.println("Solution 1");
        init();
        dfsMaze(1, 0, maze2);


        System.out.println("Solution 2");
        init();
        dfsMaze(0, 1, maze2);
        System.out.println();

    }
}

