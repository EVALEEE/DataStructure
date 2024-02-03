package recursion;

/**
 * @author EVA LEE
 * @version 1.0
 * @time 2/2/2024 8:48 pm
 */

public class recursion {
    public static void main(String[] args) {
        //通过打印问题 了解递归调用机制
        test(4);
        //有else输出：2
        //没有else输出：234

        System.out.println(factorial(3));
        //factorial(1)*2*3 = 1*2*3
    }

    //打印问题：
    public static void test(int n) {
        if (n > 2) {
            test(n - 1);
        } else {
            System.out.println(n);
        }
    }

    //阶乘问题：
    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        } else {
            return factorial(n - 1) * n;
        }
    }
}
