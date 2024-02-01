package Stack;

/**
 * @author EVA LEE
 * @version 1.0
 * @time 1/2/2024 8:21 pm
 */
public class Calculator {
    public static void main(String[] args) {

        String expression = "13+2*6-2";
        ArrayStack_2 numStack = new ArrayStack_2(10);
        ArrayStack_2 operStack = new ArrayStack_2(10);

        int index = 0;//用于扫描
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';
        String keepNum = "";//用于拼接多位数

        while (true) {
            ch = expression.substring(index, index + 1).charAt(0);
            if (operStack.isOper(ch)) {//如果是运算符
                if (!operStack.isEmpty()) {
                    //当前符号栈有符号 需要比较符号之间的优先级
                    //如果优先级小于等于栈中的 操作符 就需要从栈中pop处两个数
                    //从栈中pop一个符号 进行运算 得到结果 入数栈 然后当前的运算符入符号栈
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        //结果入数栈
                        numStack.push(res);
                        //当前符号入符号栈
                        operStack.push(ch);
                    } else {
                        //如果当前的操作符的优先级 大于栈中的操作符 就直接入符号栈
                        operStack.push(ch);
                    }
                } else {
                    //为空 直接入栈
                    operStack.push(ch);
                }
            } else {
                //如果是数 则直接入 数栈(单位数)
                //多位数则不可行
//                numStack.push(ch - 48);//'1' -> 1的跨度

                keepNum += ch;
                //如果ch 已经是expression的最后一位 就直接入栈
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    //判断下一个字符是不是数字 如果是数字 就继续扫描 如果是运算符 则入栈
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        numStack.push(Integer.parseInt(keepNum));
                        //清空keepNum
                        keepNum = "";
                    }
                }
            }
            //让 index + 1  判断是否到结尾
            index++;
            if (index >= expression.length()) {
                break;
            }
        }
        //当表达式扫描完毕 就顺序的从 数栈和符号栈中 pop处相应的数和符号进行运算
        while (true) {
            //如果符号栈为空 数栈中只能有一个数字（结果）
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res);
        }
        System.out.println(numStack.pop());//最后结果！
    }
}

class ArrayStack_2 {
    private int maxSize;//栈的大小
    private int[] stack;
    private int top = -1;//栈顶

    public ArrayStack_2(int maxSize) {
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

    //返回运算符的优先级 优先级是程序员指定的 优先级使用数字表示
    //数字越大 优先级越高
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    //判断是否是一个运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算方法
    public int cal(int num1, int num2, int oper) {
        int res = 0;
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }

    //返回当前栈顶的值 但不是pop
    public int peek() {
        return stack[top];
    }
}