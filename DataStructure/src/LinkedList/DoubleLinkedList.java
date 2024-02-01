package LinkedList;

/**
 * @author EVA LEE
 * @version 1.0
 * @time 1/2/2024 11:37 am
 */
public class DoubleLinkedList {
    public static void main(String[] args) {
        System.out.println("test here ~");
    }
}

class DoubleLinkedList_ {
    private HeroNode_1 head = new HeroNode_1(0, "", "");

    public HeroNode_1 getHead() {//返回头节点
        return head;
    }

    //遍历双向链表的方法
    public void list() {
        //判断链表是否为 null
        if (head.next == null) {
            System.out.println("wrong!");
            return;
        }

        //因为 头节点不能动 通过一个辅助变量 来帮助遍历整个链表
        HeroNode_1 temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            //输出当前节点的信息
            System.out.println(temp);
            //temp后移
            temp = temp.next;
        }
    }

    public void add(HeroNode_1 heroNode) {

        //思路：当不考虑编号顺序时
        //1. 找到当前链表的最后节点
        //2. 将最后这个节点的next 指向新的节点

        //因为head节点不能动 所以需要一个辅助变量 temp
        HeroNode_1 temp = head;

        //遍历链表 找到最后的节点
        while (true) {
            if (temp.next == null) {
                break;
            }
            //如果没有找到 就将temp 后移
            temp = temp.next;
        }
        //当while循环退出时 证明找到了尾部节点 可以开始添加操作了
        //形成一个双向链表
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    public void update(HeroNode_1 newheroNode) {
        //判断是否为 null
        if (head.next == null) {
            System.out.println("wrong!");
            return;
        }

        //不为null 根据no 找到需要修改的节点
        HeroNode_1 temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                //已经遍历完
                break;
            }
            if (temp.no == newheroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据 flag 判断是否找到修改点
        if (flag) {
            temp.name = newheroNode.name;
            temp.nickname = newheroNode.nickname;
        } else {
            System.out.println("wrong! Not Found!");
        }
    }

    //对于双向链表 我们可以直接找到要删除的这个节点
    //找到后 自我删除即可
    public void delete(int no) {

        //判断当前链表是否为 null
        if (head.next == null) {
            System.out.println("wrong!");
            return;
        }

        HeroNode_1 temp = head.next;
        boolean flag = false;

        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.pre.next = temp.next;
            //如果是最后一个节点 就不需要执行下面这句话 否则会空指针异常
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.println("wrong! Not Found!");
        }
    }
}

class HeroNode_1 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode_1 next;//指向下一个节点
    public HeroNode_1 pre;//指向前一个节点

    public HeroNode_1(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode: " +
                "no= " + no +
                ", name= '" + name + '\'' +
                ", nickname= " + nickname;
    }
}