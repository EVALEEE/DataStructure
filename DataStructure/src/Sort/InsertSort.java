package Sort;

/**
 * @author EVA LEE
 * @version 1.0
 * @time 3/2/2024 8:02 pm
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1};
        insertSort(arr);
    }

    //插入排序：
    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            //定义待插入的数
            int insertVal = arr[i];
            int insertIndex = i - 1;

            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            arr[insertIndex + 1] = insertVal;
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
