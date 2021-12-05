package PartII;

import java.util.*;

/**
 * 40171434 40186367
 * @author Zitao Wang & Jiaming Han
 *
 */
public class CleverSIDC {

	private LinkedList[] studentList;
	private long size;
	private AVLTreeList avltreelist;
	public CleverSIDC() {
		studentList = null;
		avltreelist = null;
		size = 0;
	}
	/**
	 * Set the size.
	 * @param Size           
	 */
	public void SetSIDCThreshold(long Size) {
		
		if(this.size == 0) {
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
					
					avltreelist = new AVLTreeList();
					this.size = Size;				
				}
			}

		}
		else {
			transfer(Size);
		}
	}
	
	
	
	public void transfer(long Size) {
		LinkedList[] l = null;
		if(Size <= 50000) {
			if(Size >= 100 && Size <= 500){
			
				l = new LinkedList[25];// 25 * (4-20);
				for(int i = 0; i < 25; i++) {
					l[i] = new LinkedList();
				}
				for(int i = 0; i < this.size; i++) {
					ListNode node = studentList[i].head;
					if(node == null) {
						continue;
					}
					else {
						while( node.next != null) {
						
							int x = getIndexT(node.getKey(), 25);
							l[x].addInList(node.getKey(), node.getValue());
							node = node.next;
						}
						int x = getIndexT(node.getKey(), 25);
						l[x].addInList(node.getKey(), node.getValue());
					}

				}
				studentList = l;
				this.size = 25;
				//this.allKeys();
			}
			else if(Size > 500 && Size <= 5000){
				
				l = new LinkedList[50];// 50 * (10-100);
				for(int i = 0; i < 50; i++) {
					l[i] = new LinkedList();
				}
				for(int i = 0; i < this.size; i++) {
					ListNode node = studentList[i].head;
					if(node == null) {
						continue;
					}
					else {
						while( node.next != null) {
						
							int x = getIndexT(node.getKey(), 50);

							l[x].addInList(node.getKey(), node.getValue());
							
							node = node.next;
						}
						int x = getIndexT(node.getKey(), 50);
						l[x].addInList(node.getKey(), node.getValue());
					}

				}
				studentList = l;
				this.size = 50;				
				//this.allKeys();

			}
			else if(Size > 5000 && Size <= 50000){
				
				l = new LinkedList[500];// 500 * (10-100);
				for(int i = 0; i <500; i++) {
					l[i] = new LinkedList();
				}
				for(int i = 0; i < this.size; i++) {
					ListNode node = studentList[i].head;
					if(node == null) {
						continue;
					}
					else {
						while( node.next != null) {
						
							int x = getIndexT(node.getKey(), 500);
							l[x].addInList(node.getKey(), node.getValue());
							node = node.next;
						}
						int x = getIndexT(node.getKey(), 500);
						l[x].addInList(node.getKey(), node.getValue());
					}

				}
				studentList = new LinkedList[500];
				studentList = l;
				this.size = 500;
				//this.allKeys();
				
			}

		}
		else {
				AVLTreeList avl = new AVLTreeList();
				
				for(int i = 0; i < this.size; i++) {
					ListNode node = studentList[i].head;
					if(node == null) {
						continue;
					}
					else {
						while( node.next != null) {
						
							avl.add(avl, node.getKey(), node.getValue());
							node = node.next;
						}
						int x = getIndexT(node.getKey(), 500);
						avl.add(avl, node.getKey(), node.getValue());
					}

				}
				
				
				this.avltreelist = avl;
				this.size = Size;
		}
		
		
	}
	
	private int getSize() {
		return studentList.length;
	}
	
	private int getIndexT(long key, long size) {
		long linkMaxSize = 90000000 / size;
		//System.out.println("Max SIZE IS: " + linkMaxSize);
		int target = 0;
		if(key <= 10000000) {
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
		//System.out.println("target IS: " + target);
		return target;
	}

	private int getIndex(long key) {
		long linkMaxSize = 90000000 / size;
		//System.out.println("Max SIZE IS: " + linkMaxSize);
		int target = 0;
		if(key <= 10000000) {
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
		//System.out.println("target IS: " + target);
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
		
		if(this.size <= 50000) {
			int index = this.getIndex(key);
			System.out.println(index);
			return studentList[index].contains(key);			
		}
		else {
			return this.avltreelist.contains(key);
		}
		
	}
	/**
	 * Print all keys
	 * 
	 */
	public void allKeys() {
		if(size <= 50000) {
			System.out.println();
			for(int i = 0; i < this.size; i++) {
				studentList[i].displayContents();
			}
			System.out.println();
		}
		else {
			avltreelist.Allkeys();
		}

	}

	/**
	 * add one student information
	 * @param key : SIDC
	 * @param value : Name
	 */
	public void add(long key, String value) {
		
		if(size <= 50000) {
			if(checkSIDC(key)) {
				System.out.println("This SIDC is exist, a new number is being randomized.");
				key = generate();
				System.out.println("New SIDC is: " + key);
			}
			int index = this.getIndex(key);
			studentList[index].addInList(key, value);
		}
		else {
			avltreelist.add(avltreelist, key, value);
		}

	}
	
	/**
	 * remove the key
	 * @param key
	 */
	public void remove(long key) {
		if(size <= 50000) {
			int index = this.getIndex(key);
			studentList[index].RemoveInList(key);			
		}
		else {
			avltreelist.remove(avltreelist, key);
		}

	}
	
	public void getValues(long key) {
		
		if(size <= 50000) {
			int index = this.getIndex(key);
			studentList[index].getValueByKey(key);		
		}
		else {
			avltreelist.getValues(avltreelist, key);
		}
		
	}
	
	public void nextKey(long key) {
		
		if(size <= 50000) {
			int index = this.getIndex(key);
			ListNode node = studentList[index].getTargetNode(key);
			if(node == null) {
				System.out.println("There is no next key.");
				return;
			}
			if(node.next == null) {
			
				for(int i = index+1; i < size; i++) {
					if( studentList[i].size() != 0 ) {
						studentList[i].head.printNode();
						return;
					}
				}
				System.out.println("There is no next key.");
				return;
			}
			else {
				node.pre.printNode();
			}
		}
		else {
			avltreelist.nextKey(avltreelist, key);
		}

	}
	
	public void prevKey(long key) {
		if(size <= 50000) {
			int index = this.getIndex(key);
			ListNode node = studentList[index].getTargetNode(key);
			if(node == null) {
				System.out.println("There is no pre key.");
				return;
			}
			if(node.pre == null) {
				
				for(int i = index-1; i >= 0; i--) {
					if( studentList[i].size() != 0 ) {
						studentList[i].tail.printNode();
						return;
					}
				}
				System.out.println("There is no pre key.");
				return;
				
			}
			else {
				node.pre.printNode();
				return;
			}
		}
		else {
			avltreelist.prevKey(avltreelist, key);
		}
	}
	public void rangeKey(long key1, long key2) {
		
		if(size <= 50000) {
			int index1 = this.getIndex(key1);
			int index2 = this.getIndex(key2);
			ListNode node1 = studentList[index1].getTargetNode(key1);
			ListNode node2 = studentList[index2].getTargetNode(key2);
			if(node1 == null) {
				node1 = studentList[index1].head;
			}
			if(node2 == null) {
				node2 = studentList[index2].head;
			}
			
			if(index1 == index2) {
				
				if(node1 == null) {
					System.out.println("No key in this range:" + key1 + "-" + key2);
					return;
				}
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
		else {
			avltreelist.rangeKey(key1, key2);
		}
	}
	
	
}
