package PartII;

/**
 * Key: StudentIDentificationCode,
 * Value: Name;
 * @author WangZ
 *
 */
public class LinkedList {

	ListNode head;
	ListNode tail;
	int size;
	public LinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	
	public void addInList(long SIDC, String Name) {
		
		ListNode node = new ListNode(SIDC, Name);
		if(this.head == null) {
			this.head = node;
			this.tail = node;
			this.head.next = null;
			this.head.pre = null;
		}
		else {
			ListNode previous = null;
			ListNode current = head;
			while(current != null && current.getKey() < node.getKey()) {
				
				previous = current;
				current = current.next;
				
			}
			node.next = current;
			node.pre = previous;
			
			if(previous != null) {
				previous.next = node;
			}
			else {
				head = node;
			}
			if(current != null) {
				current.pre = node;
			}
			else {
				tail = node;
			}
			
		}
		size++;
		
	}
	
	public void RemoveInList(long key) {
		
		ListNode previous = null;
		ListNode current = head;

        while(current != null && current.getKey() != key){
            previous = current;
            current = current.next;
        }
        if(current == null) {
        	System.out.println("key is not exist.");
        }
        else {
        	if(previous != null) {
        		previous.next = current.next;
        		if(current.next != null) {
        			current.next.pre = previous;
        		}
        	}
        	else {
        		head = head.next;
        	}
        	size--;
        }
		
	}
	
	public void displayContents() {
		ListNode node = head;
		if(head == null) {
			//System.out.println("empty");
		}
		else {
			
			while(node.next != null) {
				System.out.println( node.getKey() + " , " + node.getValue());
				node = node.next;
			}
			System.out.println(node.getKey() + " , " + node.getValue());
		}
		
	}

	public int size() {
		return this.size;
	}
	public void setsize(int size) {
		this.size = size;
	}

	public boolean contains(long SIDC) {
		
		//boolean result = false;
		ListNode node = head;
		if(node == null) {
			return false;
		}
		while(node.next != null) {
			if(node.getKey() == SIDC) {
				return true;	
			}
			node = node.next;
		}
		if(node.getKey() == SIDC) {
			return true;
		}
		return false;
	}
	
	public String getValueByKey(long key) {
		
		ListNode node = head;
		if(node == null) {
			return "key is invaild.";
		}
		while(node.next != null) {
			if(node.getKey() == key) {
				System.out.println("Value is: " + node.getValue());
				return node.getValue();
				
			}
			node = node.next;
		}
		if(node.getKey() == key) {
			System.out.println("Value is: " + node.getValue());
			return node.getValue();
		}
		return "key is invaild.";
	}
	
	public ListNode getTargetNode(long key) {
		
		ListNode node = head;
		if(node == null) {
			System.out.println("key is invaild.");
			return null;
		}
		while(node.next != null) {
			if(node.getKey() == key) {
				return node;
			}
			node = node.next;
		}
		if(node.getKey() == key) {
			return node;
		}
		System.out.println("key is invaild.");
		return null;
	}
	
	/*
	public static void main(String args[]) {
		LinkedList x = new LinkedList();
		x.addInList(4, "WZT");

		x.displayContents();
		x.RemoveInList(4);
		x.displayContents();
	}
	*/
}
