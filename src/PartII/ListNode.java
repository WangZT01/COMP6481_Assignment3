package PartII;

/**
 * 40171434 40186367
 * @author Zitao Wang & Jiaming Han
 *
 */
public class ListNode extends StudentNode{
    long StudentIDentificationCode;
    String studentName;
    ListNode pre;
    ListNode next;
    public ListNode() {

        super();
        this.pre = null;
        this.next = null;
    }
    public ListNode(long key, String value) {
        super(key, value);
        this.pre = null;
        this.next = null;

    }
    public ListNode(long key, String value, ListNode pre, ListNode next) {
        super(key, value);
        this.pre = pre;
        this.next = next;
    }


}
