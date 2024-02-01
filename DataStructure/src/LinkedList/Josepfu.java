package LinkedList;

/**
 * @author EVA LEE
 * @version 1.0
 * @time 1/2/2024 4:15 pm
 */
public class Josepfu {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();
        circleSingleLinkedList.countBoy(1, 2, 5);
        //2-4-1-5-3
    }
}

//创建一个环形的 单向链表
class CircleSingleLinkedList {
    //创建一个first节点 当前还没有编号
    private Boy first = null;

    //添加boy节点 构建一个环形的链表
    public void addBoy(int nums) {
        if (nums < 1) {
            System.out.println("wrong!");
            return;
        }

        Boy curBoy = null;//辅助变量 帮助构建环形链表

        //使用for循环 创建环形链表
        for (int i = 1; i <= nums; i++) {
            //根据编号 创建boy节点
            Boy boy = new Boy(i);

            //如果是第一个节点
            if (i == 1) {
                first = boy;
                first.setNext(first);//构成一个环
                curBoy = first;//因为first不能动 所有利用curBoy
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    //遍历当前的环形链表
    public void showBoy() {
        if (first.getNext() == null) {
            System.out.println("wrong!");
            return;
        }
        //任然使用辅助指针 curBoy 完成遍历
        //当 curBoy的next 指向first时 表明遍历完毕
        Boy curBoy = first;
        while (true) {
            System.out.println(curBoy.getNo());
            if (curBoy.getNext() == first) {
                break;
            }
            curBoy = curBoy.getNext();
        }
    }

    //根据用户的输入 计算出出圈的顺序

    /**
     * @param startNum 表示从第几个小孩开始
     * @param countNum 表示数几下
     * @param nums     表示最初有多少个小孩在圈
     */
    public void countBoy(int startNum, int countNum, int nums) {
        if (first == null || startNum < 1 || startNum > nums) {
            System.out.println("wrong!");
            return;
        }
        //创建辅助指针
        Boy helper = first;
        //辅助指针事先应指向 环形链表的最后这个节点
        while (true) {
            if (helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();
        }
        //报数时 先让 first和helper 移动 k-1次 (从当前第几个小孩开始重新报数)
        for (int j = 0; j < startNum - 1; j++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //定位出圈点 让first和helper 同时移动 m-1次
        while (true) {
            if (helper == first) {//圈中只剩一个节点
                break;
            }
            for (int j = 0; j < countNum - 1; j++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //此时 first指向的就是要出圈的节点
            System.out.println(first.getNo() + " out!");
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.println("last one: " + first.getNo());
    }
}

//创建一个boy类 表示一个节点
class Boy {
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
