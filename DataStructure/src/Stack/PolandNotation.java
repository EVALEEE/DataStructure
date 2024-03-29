package Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author EVA LEE
 * @version 1.0
 * @time 1/2/2024 11:37 pm
 */
public class PolandNotation {
    public static void main(String[] args) {

        //完成将一个中缀表达式 转成后缀表达式的功能
        //1. 1+((2+3)*4)-5 => 1 2 3 + 4 * + 5 -
        //2. 先将字符串 转成一个中缀表达式对应的List
        //即 "1+((2+3)*4)-5" = ArrayList [1, +, (, (, 2, .....]
        //3. 将上面的中缀List 转成后缀表达式对应的List
        //ArrayList[1,2,3,+,4,*,+,5,-]
        String expression = "1+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);
        List<String> suffixExpressionList = parseSuffixExpressionList(infixExpressionList);
        System.out.println(suffixExpressionList);

        System.out.println(calculate(suffixExpressionList));

//        //先定义个逆波兰表达式
//        String suffixExpression = "30 4 + 5 * 6 -";//(30+4)*5-6
//        //1. 将 "3 4 + 5 x 6 - " 放到ArrayList中
//        //2. 将ArrayList 传递给一个方法 遍历ArrayList 配合栈 完成计算
//        List<String> list = getListString(suffixExpression);
//        System.out.println(calculate(list));
    }

    //将得到的中缀表达式的List 转成后缀表达式的List
    public static List<String> parseSuffixExpressionList(List<String> ls) {
        //定义两个栈
        Stack<String> s1 = new Stack<>();//符号栈
        //说明： 因为s2这个栈 在整个转换过程中 没有pop操作 而且后面我们还需要逆序输出
        //因为比较麻烦 这里我们就不同 Stack 直接使用List
//        Stack<String> s2 = new Stack<>();//存储中间结果的栈
        List<String> s2 = new ArrayList<>();

        //遍历ls
        for (String item : ls) {
            //如果是一个数 入s2
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                //右括号 则依次弹出s1栈顶的运算符 并压入s2 直到遇到左括号为止 这是将这一对括号丢弃
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();//将（ 弹出s1栈！！！！！
            } else {
                //当item的优先级小于等于s1栈顶运算符 将s1栈顶的运算符弹出并加入到s2中 再次转到（4-1）步骤
                //与s1中新的栈顶运算符相比较
                while (s1.size() != 0
                        && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                //还需要将 item压入栈中
                s1.push(item);
            }
        }
        //将s1中剩余的运算符 依次弹出并加入 s2
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;
        //因为是存放到List中 因此按顺序输出就是对应的后缀表达式
    }


    //将中缀 转成 对应的List
    public static List<String> toInfixExpressionList(String s) {
        //定义一个List 存放中缀表达式对应的内容
        List<String> ls = new ArrayList<>();
        int i = 0;//一个指针 用于遍历 s
        String str;//对多位数的拼接
        char c;//每遍历一个字符 就放入c中

        do {
            //如果c是一个非数字 需要加入到ls中
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                ls.add(c + "");
                i++;
            } else {
                //为数字 需要拼接 要考虑多位数
                str = "";
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c;
                    i++;
                }
                ls.add(str);
            }
        } while (i < s.length());
        return ls;
    }


    //将一个逆波兰表达式 依次将数据和运算符 放入到ArrayList中 返还一个ArrayList
    public static List<String> getListString(String suffixExpression) {
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<String>();
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }

    /*
    1. 从左到右扫描 将3 4压入堆栈
    2. 遇到 + 运算符 因此弹出4 3（4为栈顶元素 3为次顶元素） 计算出3+4 结果再次放入栈
    3. 5入栈
    4. 接下来是 x运算符 因此弹出5 7 计算出结果 35入栈
    5. 将6入栈
    6. 最后是 -运算符 计算出35-6的值 即29 得出最终结果
     */
    public static int calculate(List<String> ls) {
        //创建一个栈
        Stack<String> stack = new Stack<String>();
        //遍历 ls
        for (String item : ls) {
            //用正则取出数
            if (item.matches("\\d+")) {//匹配的是多位数
                //入栈
                stack.push(item);
            } else {
                //pop出两个数 并运算
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());

                int res = 0;
                if (item.equals("+")) {
                    res = num2 + num1;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("wrong!");
                }
                //把res 入栈
                stack.push(res + "");//把int转成字符串的一个快速操作
            }
        }
        //最后留在stack 里的就是最终结果
        return Integer.parseInt(stack.pop());
    }

}

//编写一个类 可以返回一个运算符对应的优先级
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //写一个方法 返回对应的优先级数字
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("wrong!");
                break;
        }
        return result;
    }
}