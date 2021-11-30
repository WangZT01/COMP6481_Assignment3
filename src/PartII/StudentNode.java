package PartII;

/**
 * The signal student node.
 * @author WangZ
 *
 */
public class StudentNode {
	
	private long StudentIDentificationCode;
	private String studentName;
	StudentNode pre;
	StudentNode next;
	public StudentNode() {
		
		this.StudentIDentificationCode = 0;
		this.studentName = null;
		this.pre = null;
		this.next = null;
	}
	public StudentNode(long key, String value) {
		this.StudentIDentificationCode = key;
		this.studentName = value;
		this.pre = null;
		this.next = null;
		
	}
	public StudentNode(long key, String value, StudentNode pre, StudentNode next) {
		this.StudentIDentificationCode = key;
		this.studentName = value;
		this.pre = pre;
		this.next = next;
	}
	public String getValue() {
		return this.studentName;
	}
	public long getKey() {
		return this.StudentIDentificationCode;
	}
	public void printNode() {
		System.out.println( this.getKey() + " , " + this.getValue());
	}

}
