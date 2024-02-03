package recursion;

/**
 * @author EVA LEE
 * @version 1.0
 * @time 2/2/2024 9:56 pm
 */
public class MIGONG {
    public static void main(String[] args) {
        //创建一个二维数组 作为迷宫
        int[][] map = new int[8][7];

        //使用1表示 墙
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }

        //设置挡板
        map[3][1] = 1;
        map[3][2] = 1;

//        //输出原始地图
//        for (int i = 0; i < 8; i++) {
//            for (int j = 0; j < 7; j++) {
//                System.out.print(map[i][j] + " ");
//            }
//        }

        setWay(map, 1, 1);//map是引用类型 所以所有的修改都在map上记录了下来
        //输出地图
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    //使用递归回溯 来给小球找路
    //说明：
    //如果小球能到 map[6][5] 则说明通路找到
    //约定： 当地图的 i j 为 0：表示该点还没有走过 1：表示为墙 2：表示通路可以走
    //3：表示该点已经走过 不通 无需回溯
    //在走迷宫时 需要确定一个策略：下=》右=》上=》左 如果走不通再回溯
    /**
     * @param map 表示地图
     * @param i   从哪个位置开始找
     * @param j
     * @return 如果找到通路 就返回ture
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            //通路已经找到
            return true;
        } else {
            if (map[i][j] == 0) {
                //表示这个点还没走过
                //按照策略走
                map[i][j] = 2;//假定该点可以走通
                if (setWay(map, i + 1, j)) {//向下走
                    return true;
                } else if (setWay(map, i, j + 1)) {//向右走
                    return true;
                } else if (setWay(map, i - 1, j)) {//向上走
                    return true;
                } else if (setWay(map, i, j - 1)) {//向左走
                    return true;
                } else {
                    //说明该点走不通 是死路
                    map[i][j] = 3;
                    return false;
                }
            } else {//map[i][j] 不等于0
                return false;
            }
        }
    }
}
