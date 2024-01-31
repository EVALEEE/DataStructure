package LinkedList;

/**
 * @author EVA LEE
 * @version 1.0
 * @time 31/1/2024 10:34 pm
 */

public class SingleLinkedList {
    public static void main(String[] args) {
        SingleLinkedList_ singleLinkedList = new SingleLinkedList_();

        singleLinkedList.add(new HeroNode(1, "eva", "007"));
        singleLinkedList.add(new HeroNode(2, "kevin", "008"));
        singleLinkedList.add(new HeroNode(3, "kk", "006"));

        singleLinkedList.list();
    }
}

@SuppressWarnings({"all"})
//定义 SingleLinkedList 管理我们的英雄
class SingleLinkedList_ {

    //初始化一个 头节点（不要乱动） 不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "");

    //添加节点到单向链表
    public void add(HeroNode heroNode) {

        //思路：当不考虑编号顺序时
        //1. 找到当前链表的最后节点
        //2. 将最后这个节点的next 指向新的节点

        //因为head节点不能动 所以需要一个辅助变量 temp
        HeroNode temp = head;

        //遍历链表 找到最后的节点
        while (true) {
            if (temp.next == null) {
                break;
            }
            //如果没有找到 就将temp 后移
            temp = temp.next;
        }

        //当while循环退出时 证明找到了尾部节点 可以开始添加操作了
        temp.next = heroNode;
    }

    //第二种添加英雄的方式： 根据排名 将英雄插入到指定位置（如果有这个排名 则添加失败 并给出提示）
    public void addByOrder(HeroNode heroNode) {

        //依旧需要temp
        //因为是单链表 因此 temp必须是在添加位置的前一个节点 否则添加不了
        HeroNode temp = head;
        boolean flag = false;//标识编号是否存在
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no > heroNode.no) {
                //位置找到 在temp后插入
                break;
            } else if (temp.next.no == heroNode.no) {
                //编号存在 添加失败
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //判断 flag
        if (flag) {
            System.out.println("wrong!");
        } else {
            //加入新数据到链表中 temp的后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //修改操作 no不能修改
    public void update(HeroNode newheroNode) {
        //判断是否为 null
        if (head.next == null) {
            System.out.println("wrong!");
            return;
        }

        //不为null 根据no 找到需要修改的节点
        HeroNode temp = head.next;
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

    //删除操作
    public void delete(int no) {
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.println("wrong! Not Found!");
        }
    }

    //遍历显示链表
    //通过一个辅助变量 来帮助遍历整个链表
    public void list() {
        //判断链表是否为 null
        if (head.next == null) {
            System.out.println("wrong!");
            return;
        }

        //因为 头节点不能动 通过一个辅助变量 来帮助遍历整个链表
        HeroNode temp = head.next;
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
}

//================================================================
//定义 HeroNode 每个HeroNode对象 就是一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;//指向下一个节点

    public HeroNode(int no, String name, String nickname) {
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