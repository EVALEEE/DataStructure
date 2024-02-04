package Sort;

/**
 * @author EVA LEE
 * @version 1.0
 * @time 3/2/2024 3:18 pm
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] array = {3, 9, -1, 10, -2};
        bubbleSort(array);
    }

    //冒泡排序演示：(时间复杂度：O(n^2))
    public static void bubbleSort(int[] array) {
        int temp = 0;
        boolean flag = false;

        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    flag = true;
                    temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
            //优化：如果发现在某趟排序中，没有发生一次交换，证明顺序已经正确，可以提前结束冒泡排序
            if (!flag) {
                break;
            } else {
                flag = false;//重置 为下一个循环做准备
            }
        }

        //print:
        for (int i = 0; i < 5; i++) {
            System.out.print(array[i] + " ");
        }
    }
}
