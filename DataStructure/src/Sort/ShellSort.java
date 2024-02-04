package Sort;

/**
 * @author EVA LEE
 * @version 1.0
 * @time 3/2/2024 11:10 pm
 */

public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        shellSort(arr);
    }

    //Shell排序：（交换法）--效率低(偏向冒泡)
    public static void shellSort(int[] arr) {
        int temp = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                //遍历各组中所有的元素（共gap组） 步长为gap
                for (int j = i - gap; j >= 0; j -= gap) {
                    //如果当前元素大于加上步长后的那个元素 说明要交换
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }
        for (int j : arr) {
            System.out.print(j + " ");
        }
    }

    //优化：移位法希尔排序 --效率高(增强版的插入法)
    public static void shellSort2(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //从第gap个元素开始 逐个对其所在的组进行直接的插入
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    //当while退出后 证明temp找到位置了 就给temp插入进去即可！
                    arr[j] = temp;
                }
            }
        }
        for (int j : arr) {
            System.out.print(j + " ");
        }
    }
}
