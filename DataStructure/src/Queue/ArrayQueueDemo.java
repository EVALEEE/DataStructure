package Queue;

import java.util.Scanner;

/**
 * @author EVA LEE
 * @version 1.0
 * @time 31/1/2024 12:29 am
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(3);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;

        //输出一个菜单
        while (loop) {
            System.out.println("s：显示队列");
            System.out.println("e；退出程序");
            System.out.println("a：添加数据到队列");
            System.out.println("g：从队列取出数据");
            System.out.println("h：查看队列头的数据");

            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    arrayQueue.showQueue();
                    break;

                case 'a':
                    System.out.println("input a num!");
                    int num = scanner.nextInt();
                    arrayQueue.addQueue(num);
                    break;

                case 'g':
                    try {
                        int res = arrayQueue.getQueue();
                        System.out.println(res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 'h':
                    try {
                        System.out.println(arrayQueue.headQueue());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 'e':
                    scanner.close();
                    loop = false;
                    break;
            }
        }

        System.out.println("程序退出！");
    }
}

//使用数组模拟队列 编写一个ArrayQueue类
class ArrayQueue {
    private int maxSize;//表示数组最大容量
    private int front;//队列头
    private int rear;//队列尾
    private int[] arr;//该数组用于存放数据 模拟队列


    //创建队列的构造器
    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = -1;//指向队列头部前一个位置
        rear = -1;//指向队尾 也就是队列最后一个数据
    }

    //判断队列是否是满的
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    ////判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    //添加数据到队列
    public void addQueue(int n) {
        //判断队列是否满
        if (isFull()) {
            System.out.println("队列满，不能加入新的数据！");
            return;
        }
        arr[++rear] = n;
    }

    //获取数据 出队列
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空！");
        }
        return arr[++front];
    }

    //显示队列的所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("没有数据！");
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }

    //显示队列的头数据 注意不是取出数据
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("-1");
        }
        return arr[front + 1];
    }

}