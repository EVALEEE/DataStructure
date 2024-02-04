package Sort;

/**
 * @author EVA LEE
 * @version 1.0
 * @time 3/2/2024 5:36 pm
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] array = {101, 34, 119, 1};
        selectSort(array);
//        selection_sort(array);
    }

    //选择排序：(自己写的)
    public static void selectSort(int[] array) {
        int minIndex = 0, point = 0, temp, min;
        for (int j = 0; j < array.length - 1; j++) {
            min = array[j];
            for (int i = j; i < array.length; i++) {
                if (array[i] <= min) {
                    min = array[i];
                    point = i;
                }
            }
            temp = array[minIndex];
            array[minIndex++] = min;
            array[point] = temp;
        }
        for (int ele : array) {
            System.out.print(ele + " ");
        }
    }

/*
    // 选择排序（Java）网上找到的方法 =====================================
    public static void selection_sort(int[] arr) {
        int i, j, min, temp, len = arr.length;
        for (i = 0; i < len - 1; i++) {
            min = i;
            for (j = i + 1; j < len; j++)
                if (arr[min] > arr[j])
                    min = j;
            temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp;
        }
        for (int a : arr) {
            System.out.print(a + " ");
        }
    }*/
}
