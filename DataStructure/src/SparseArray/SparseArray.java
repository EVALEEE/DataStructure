package SparseArray;

/**
 * @author EVA LEE
 * @version 1.0
 * @time 30/1/2024 9:52 pm
 */
public class SparseArray {
    public static void main(String[] args) {
        //创建一个原始的二维数组
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;

//        for (int[] row : chessArr1) {
//            for (int data : row) {
//                System.out.printf("%d\t", data);
//            }
//            System.out.println();
//        }

        //将二维数组 转成稀疏数组
        //1. 先遍历二维数组 得到非0的数据个数
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;//sum==2
                }
            }
        }

//        System.out.println(sum);

        //2. 创建一个对应的稀疏数组
        int sparseArray[][] = new int[sum + 1][3];

        //3. 给稀疏数组赋值
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = sum;

        //4. 遍历二维数组 将非零的值存放到稀疏数组中
        int sum_1 = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    sum_1++;
                    sparseArray[sum_1][0] = i;
                    sparseArray[sum_1][1] = j;
                    sparseArray[sum_1][2] = chessArr1[i][j];
                }
            }
        }

//        for (int[] a : sparseArray) {
//            for (int data_1 : a) {
//                System.out.printf("%d\t", data_1);
//            }
//            System.out.println();
//        }


        //稀疏数组 ==> 二维数组
        int recoverArray[][] = new int[sparseArray[0][0]][sparseArray[0][1]];

        for (int i = 1; i < sum + 1; i++) {
            recoverArray[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][sum];
        }

        for (int[] a : recoverArray) {
            for (int data_1 : a) {
                System.out.printf("%d\t", data_1);
            }
            System.out.println();
        }
    }
}
