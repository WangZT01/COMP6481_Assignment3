package PartII;

import java.util.*;

public class CleverSIDC {

	private LinkedList[] studentList;
	private long size;
	
	public CleverSIDC() {
		studentList = null;
		size = 0;
	}
	/**
	 * Set the size.
	 * @param Size           
	 */
	public void SetSIDCThreshold(long Size) {
		
		if(Size < 100 || Size > 500000) {
			System.out.println("The Size is invalid.");
		}
		else {
			
			if(Size >= 100 && Size <= 500){
				studentList = new LinkedList[25];// 25 * (4-20);
				this.size = 25;
				for(int i = 0; i < 25; i++) {
					studentList[i] = new LinkedList();
				}
				
			}
			else if(Size > 500 && Size <= 5000){
				studentList = new LinkedList[50];// 50 * (10-100);
				this.size = 50;
				for(int i = 0; i < 50; i++) {
					studentList[i] = new LinkedList();
				}
				
			}
			else if(Size > 5000 && Size <= 50000){
				studentList = new LinkedList[500];// 500 * (10-100);
				this.size = 500;
				for(int i = 0; i < 500; i++) {
					studentList[i] = new LinkedList();
				}
				
			}
			else if(Size > 50000 && Size <= 500000){
				studentList = new LinkedList[5000]; // 5000 * (10-100);
				this.size = 5000;
				for(int i = 0; i < 5000; i++) {
					studentList[i] = new LinkedList();
				}
				
			}
		}
	}
	private int getSize() {
		return studentList.length;
	}
	
	private int getIndex(long key) {
		long linkMaxSize = 90000000 / size;
		//System.out.println("Max SIZE IS: " + linkMaxSize);
		int target = 0;
		if(key == 10000000) {
			return target;
		}
		for(long i = 10000000; i < 100000000; i += linkMaxSize) {
			if( key >= i+1 && key < i+linkMaxSize) {
				break;
			}
			else {
				target++;
			}
		}
		return target;
	}
	
	/**
	 * randomly generates new non-existing key of 8 digits;
	 * call the contains function in the LinkedList.
	 * if digits exist, continue random.
	 */
	public long generate() {
		long DISC = getRandom();
		while(checkSIDC(DISC)) {
			DISC = getRandom();
		}
		return DISC;
		
	}
	private long getRandom() {
		Random random = new Random();
		String str = "";
		str = String.valueOf( random.nextInt(9) + 1 );
		for(int i = 0; i < 7; i++) {
			str += String.valueOf(random.nextInt(10));
		}
		long sidc = Long.valueOf(str);
		System.out.println("Random id is: " + sidc);
		return sidc;
	}
	private boolean checkSIDC(long key) {
		
		int index = this.getIndex(key);
		return studentList[index].contains(key);
		
	}
	/**
	 * Print all keys
	 * 
	 */
	public void allKeys() {
		System.out.println();
		for(int i = 0; i < this.size; i++) {
			studentList[i].displayContents();
		}
		System.out.println();
	}

	/**
	 * add one student information
	 * @param key : SIDC
	 * @param value : Name
	 */
	public void add(long key, String value) {
		
		if(checkSIDC(key)) {
			System.out.println("This SIDC is exist, a new number is being randomized.");
			key = generate();
			System.out.println("New SIDC is: " + key);
		}
		int index = this.getIndex(key);
		studentList[index].addInList(key, value);
	}
	
	/**
	 * remove the key
	 * @param key
	 */
	public void remove(long key) {
		int index = this.getIndex(key);
		studentList[index].RemoveInList(key);
	}
	
	public void getValues(long key) {
		int index = this.getIndex(key);
		studentList[index].getValueByKey(key);
	}
	
	public StudentNode nextKey(long key) {
		int index = this.getIndex(key);
		StudentNode node = studentList[index].getTargetNode(key);
		if(node == null) {
			return null;
		}
		if(node.next == null) {
			
			for(int i = index+1; i < size; i++) {
				if( studentList[i].size() != 0 ) {
					studentList[i].head.printNode();
					return studentList[i].head;
				}
			}
			System.out.println("There is no next key.");
			return null;
			
		}
		else {
			node.pre.printNode();
			return node.pre;
		}
	}
	
	public StudentNode prevKey(long key) {
		int index = this.getIndex(key);
		StudentNode node = studentList[index].getTargetNode(key);
		if(node == null) {
			return null;
		}
		if(node.pre == null) {
			
			for(int i = index-1; i >= 0; i--) {
				if( studentList[i].size() != 0 ) {
					studentList[i].tail.printNode();
					return studentList[i].tail;
				}
			}
			System.out.println("There is no pre key.");
			return null;
			
		}
		else {
			node.pre.printNode();
			return node.pre;
		}
	}
	public void rangeKey(long key1, long key2) {
		
		int index1 = this.getIndex(key1);
		int index2 = this.getIndex(key2);
		StudentNode node1 = studentList[index1].getTargetNode(key1);
		StudentNode node2 = studentList[index2].getTargetNode(key2);
		if(node1 == null) {
			node1 = studentList[index1].head;
		}
		if(node2 == null) {
			node2 = studentList[index2].head;
		}
		
		if(index1 == index2) {
			
			while(node1.getKey() < key1) {
				node1 = node1.next;
			}
			while(node1.getKey() < key2) {
				node1.printNode();
				node1 = node1.next;
			}
		}
		else {
			
			for(int i = index1 ; i <= index2; i++) {
				if( studentList[i].size() != 0 ) {
					node1 = studentList[i].head;
					while(node1 != null && node1.getKey() < key1) {
						node1 = node1.next;
					}
					while(node1 != null && node1.getKey() < key2) {
						node1.printNode();
						node1 = node1.next;
					}
				}
			}
			
		}
	}
	
	public static void main(String args[]) {
		CleverSIDC x = new CleverSIDC();
		x.SetSIDCThreshold(6789);
		x.add(40171434, "Zitao Wang");
		x.add(99999999, "Zexin Peng");
		x.add(30000001, "Jiaming Han");
		x.allKeys();
		x.getValues(40171434);
		long r = x.generate();
		x.remove(r);
		x.add(r, "Renjie Peng");
		x.allKeys();
		x.prevKey(40171434);
		x.nextKey(40171434);
		x.rangeKey(20000000, 70000000);
	}
}
