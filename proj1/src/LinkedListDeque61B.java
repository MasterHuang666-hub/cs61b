import java.util.List;
import java.util.ArrayList;
public class LinkedListDeque61B<T>implements Deque61B<T>
//Deque61B 规定“一个双端队列应该有哪些功能”，
//LinkedListDeque61B 负责“这些功能具体怎么用链表实现”。
{
    private Node sentinel;
    public int size;

    private class Node {
        // 外层类已经有 <T> 之后，非泛型内部类 IntNode 可以直接使用这个 T，所以不用再写 IntNode<T>
        T item;
        Node prev;
        Node next;

        private Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    public LinkedListDeque61B() {
        size = 0;
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    public void addFirst(T p) {//这里都是两步：第一步先让新的节点指向正确的位置，第二步再让原来的节点指向新创建的节点。
        size+=1;//利用cache进行工作。
        Node guodus=new Node(p,sentinel,sentinel.next);
        sentinel.next.prev=guodus;
        sentinel.next=guodus;
    }
    public void addLast(T h){
        size+=1;
        Node guodu=new Node(h,sentinel.prev,sentinel);
        sentinel.prev.next=guodu;
        sentinel.prev=guodu;
    }
    public List<T> toList(){
        List<T> returnList = new ArrayList<T>();
        Node current_Node=sentinel;//其实就是sentinel作为一个中介，将对于LinkedListDeque的操作转化到了对于Node的操作上来进行。
        for (int i=0;i<size;i++){//这里不能进行递归，因为方法针对的是LLD，而后面实际上操作的都是Node
            returnList.add(current_Node.next.item);
            current_Node=current_Node.next;
        }
        return returnList;

    }
    @Override
    public boolean isEmpty() {
        if (this.size==0){
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T getFirst() {
        if(this.isEmpty()) {
            return null;
        }
        return sentinel.next.item;
    }

    @Override
    public T getLast() {
        if(this.isEmpty()) {
            return null;
        }
        return sentinel.prev.item;
    }

    @Override
    public T removeFirst() {
        if(size==0){
            return null;
        }
        Node current_Node=sentinel.next;
        sentinel.next=current_Node.next;
        current_Node.next.prev=sentinel;
        return current_Node.item;

    }

    @Override
    public T removeLast() {
        if(size==0) {
            return null;
        }
        Node current_Node=sentinel.prev;
        sentinel.prev=current_Node.prev;
        current_Node.prev.next=sentinel;
        return current_Node.item;

    }

    @Override
    public T get(int index) {
        if(index>=size||index<0){
            return null;
        }
        Node current_Node=sentinel.next;
        for(int i=0;i<index;i++){
            current_Node=current_Node.next;
        }
        return current_Node.item;

    }

    @Override
    public T getRecursive(int index) {
        if(index>=size||index<0){
            return null;
        }
        Node current_Node=sentinel.next;//还是一样，想要进行循环的化，必须要进行一个转化，将SLList转化到Node的层面上才能进行操作。
        if(index==0){
            return current_Node.item;
        }
        LinkedListDeque61B<T> lld =this;
        lld.sentinel.next=lld.sentinel.next.next;//这里没办法，必须自己再创建一个LinkedListDeque，再将这个LLD修改后传入recursive进行操作
        return lld.getRecursive(index-1);
    }

    public static void main(String[] args) {
        Deque61B<Integer> lld = new LinkedListDeque61B<>();
        lld.addLast(0);   // [0]
        lld.addLast(1);   // [0, 1]
        lld.addFirst(-1); // [-1, 0, 1]
    }
}
