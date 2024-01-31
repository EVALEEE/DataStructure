package Queue;

import java.util.Scanner;

/**
 * @author EVA LEE
 * @version 1.0
 * @time 31/1/2024 8:13 pm
 */

public class CircleArrayQueue {
    public static void main(String[] args) {
        CircleArray circleArray = new CircleArray(4);
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
                    circleArray.showQueue();
                    break;

                case 'a':
                    System.out.println("input a num!");
                    int num = scanner.nextInt();
                    circleArray.addQueue(num);
                    break;

                case 'g':
                    try {
                        int res = circleArray.getQueue();
                        System.out.println(res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 'h':
                    try {
                        System.out.println(circleArray.headQueue());
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

class CircleArray {
    private int maxSize;//表示数组最大容量

    //front 变量的含义进行调整：front 就指向队列的第一个元素 也就是说arr[front]
    //front 初始值 = 0
    private int front;

    //rear 的含义进行调整： rear指向队列的最后一个元素的后一个位置 因为希望空出一个空间作为约定
    //rear的初始值 = 0
    private int rear;
    private int[] arr;//该数组用于存放数据 模拟队列

    public CircleArray(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    //判断队列是否满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;//这里为什么要加maxSize，是因为要补偿rear-front为负数的那部分
    }

    //判断队列是否为空
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
        arr[rear] = n;
        rear = (rear + 1) % maxSize;
        //将 rear后移 注意要考虑取模 不然会数组越界
    }

    //获取数据 出队列
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空！");
        }
        //这里需要分析出： front是指向队列的第一个元素
        //1. 先将front 对应的值 保留到一个临时变量
        //2. 将front 后移
        //3. 将临时保存的变量返回

        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    //显示队列的所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("没有数据！");
        }
        //从front开始遍历
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d] = %d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    //当前队列有效数据的个数
    public int size() {
        return (maxSize + rear - front) % maxSize;
    }

    //显示队列的头数据
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("-1");
        }
        return arr[front];
    }
}
