package recursion;

/**
 * @author EVA LEE
 * @version 1.0
 * @time 2/2/2024 11:55 pm
 */

public class Queen8 {

    //max表示 有多少个皇后
    int max = 8;
    int[] array = new int[max];
    static int count = 0;

    public static void main(String[] args) {
        Queen8 queen8 = new Queen8();
        queen8.check(0);
    }

    //编写一个方法 放置第n个皇后
    //特别注意：check 是每一次递归时 进入到check中都有 for (int i = 0; i < max; i++)
    //因此会有回溯
    private void check(int n) {
        if (n == max) {
            print();
            return;
        }
        for (int i = 0; i < max; i++) {
            //先把当前的这个皇后 放到当前行的第一列
            array[n] = i;
            if (judge(n)) {//不冲突
                //接着放皇后 即开始递归
                check(n + 1);
            }
            //如果冲突 就继续执行array[n] = i; 即将第n个皇后 下移一列
        }
    }

    //查看当我们放置第n个皇后 就去检测该皇后是否和前面已经摆放的皇后冲突

    /**
     * @param n 表示第n个皇后
     * @return
     */
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            if (array[i] == array[n]//同一列
                    || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                //行数的差值等于 列的差值
                return false;
                //不需要判断是否在同一行 因为每次都在递增
            }
        }
        return true;
    }

    //写一个方法 可以将皇后摆放的位置输出
    public void print() {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
        }
//        count++;
        System.out.println(" " + ++count);
    }
}
