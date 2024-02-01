package Stack;

/**
 * @author EVA LEE
 * @version 1.0
 * @time 1/2/2024 7:26 pm
 */
public class ArrayStack {
    public static void main(String[] args) {

    }
}

//用数组 模拟栈结构
class ArrayStack_ {
    private int maxSize;//栈的大小
    private int[] stack;
    private int top = -1;//栈顶

    public ArrayStack_(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    //判断栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //判断栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈
    public void push(int value) {
        //先判断栈是否满
        if (isFull()) {
            System.out.println("wrong!");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈 (将栈顶的数据返回)
    public int pop() {
        if (isEmpty()) {
            System.out.println("wrong!");
            throw new RuntimeException("empty!");
        }
        return stack[top--];
    }

    //遍历栈
    public void list() {
        if (isEmpty()) {
            System.out.println("wrong!");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.println(stack[i]);
        }
    }
}
