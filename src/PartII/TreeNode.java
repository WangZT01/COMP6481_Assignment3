package PartII;

/**
 * 40171434 40186367
 * @author Zitao Wang & Jiaming Han
 *
 */

public class TreeNode extends StudentNode{

    TreeNode parent;
    TreeNode left;
    TreeNode right;
    long height;
    public TreeNode() {

        super();
        this.height = 0;
        this.parent = null;
        this.left = null;
        this.right = null;
    }
    public TreeNode(long key, String value) {
        super(key, value);
        this.parent = null;
        this.left = null;
        this.right = null;
        this.height = 0;

    }
    public TreeNode(long key, String value, TreeNode left, TreeNode right) {
        super(key, value);
        this.parent = null;
        this.left = left;
        this.right = right;
    }

    /*
     *  -1 left
     *  1 right
     */
    public int compareTo(long key) {


        if (key > this.StudentIDentificationCode){
            return 1;
        }else if( key < this.StudentIDentificationCode ){
            return -1;
        }else {
            return 0;
        }
    }
    public void printNode() {
        System.out.println( "{ " + this.getKey() + " , " + this.getValue() + "}");
        if(this.parent == null) {
            System.out.println("I'm root.");
        }
        else {
            System.out.println( "My parent is: " + this.parent.StudentIDentificationCode);
        }
        if(this.left == null) {
            System.out.println("No Left.");
        }
        else {
            System.out.println( "My Left child is: " + this.left.StudentIDentificationCode);
        }
        if(this.right == null) {
            System.out.println("No Right.");
        }
        else {
            System.out.println( "My Right child is: " + this.right.StudentIDentificationCode);
        }
        System.out.println();
    }

}
