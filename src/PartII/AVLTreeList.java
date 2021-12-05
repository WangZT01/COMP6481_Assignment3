package PartII;

import java.util.ArrayList;
import java.util.List;
/**
 * 40171434 40186367
 * @author Zitao Wang & Jiaming Han
 *
 */
public class AVLTreeList{

    private TreeNode root;
    private long size = 0;

    public AVLTreeList() {
        root = null;
        size = 0;
    }
    public boolean add(AVLTreeList avl, long key, String value) {
        TreeNode cur = avl.root;
        TreeNode parent = null;
        int cmp = 0;
        while(cur != null) {

            cmp = cur.compareTo(key);
            if(cmp > 0) {
                parent = cur;
                cur = cur.right;
            }
            else if(cmp < 0) {
                parent = cur;
                cur = cur.left;
            }
            else {
                return false;
            }
        }
        TreeNode node = new TreeNode(key, value);
        if(parent == null) {
            avl.root = node;
        }
        else {
            if(cmp < 0) {
                parent.left = node;
            }
            else {
                parent.right = node;
            }
            node.parent = parent;
        }
        fixAfterUpdate(node);
        size++;
        return true;
    }

    private void fixAfterUpdate(TreeNode node) {
        TreeNode cur = node;
        while(cur != null) {
            updateHeight(cur);
            if( balanceFactor(cur) > 1) {
                if( balanceFactor(cur.left) < 0) {
                    rotateLeft(cur.left);
                }
                rotateRight(cur);
            }
            else if( balanceFactor(cur) < -1) {
                if( balanceFactor(cur.left) > 0) {
                    rotateRight(cur.left);
                }
                rotateLeft(cur);
            }
            else {
                cur = cur.parent;
            }
        }
    }
    private long height(TreeNode node) {
        if(node == null) {
            return 0;
        }
        return node.height;
    }
    private void updateHeight( TreeNode node ) {
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }

    private long balanceFactor(TreeNode node) {
        if( node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }


    //        y                              x
    //       / \                           /   \
    //      x   T4     right              z     y
    //     / \       - - - - - - - ->    / \   / \
    //    z   T3                       T1  T2 T3 T4
    //   / \
    // T1   T2
    private void rotateRight( TreeNode node) {
        TreeNode x = node.left;
        TreeNode t = x.right;
        TreeNode parent = node.parent;
        if(parent == null) {
            root = x;
        }
        else {
            if(node == parent.left) {
                parent.left = x;
            }
            else {
                parent.right = x;
            }
        }
        x.parent = parent;
        node.right = node;
        node.parent = x;
        node.left = t;
        if (t != null) {
            t.parent = node;
        }
        updateHeight(node);
        updateHeight(x);
    }
    //        y                               x
    //       / \                            /  \
    //      T4  x     left                 y    z
    //         / \   - - - - - - - ->    / \   / \
    //        T3 z                      T4 T3 T1 T2
    //          / \
    //         T1 T2
    private void rotateLeft(TreeNode node) {
        TreeNode x = node.right;
        TreeNode t = x.left;
        TreeNode parent = node.parent;
        if (parent == null) {
            root = x;
        } else {
            if (node == parent.left) {
                parent.left = x;
            } else {
                parent.right = x;
            }
        }
        x.parent = parent;
        node.parent = x;
        x.left = node;
        node.right = t;
        if (t != null) {
            t.parent = node;
        }
        updateHeight(node);
        updateHeight(x);
    }
    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        long balanceFactor = balanceFactor(root);
        if (Math.abs(balanceFactor) > 1) {
            return false;
        }
        return isBalanced(root.left) && isBalanced(root.right);
    }


    public long size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }


    public boolean remove(AVLTreeList avl, long key) {
        TreeNode node = find(avl.root, key);
        if (node == null) {
            System.out.println("No target key.");
            return false;
        }
        fastRemove(node);
        size--;
        return true;
    }
    public boolean contains(long e) {
        TreeNode node = find(root, e);
        return node != null;
    }
    /**
     * ????root????????????????e????
     */
    private TreeNode find(TreeNode root, long key) {
        if (root != null) {
            if (root.compareTo(key) > 0) {
                return find(root.right, key);
            } else if (root.compareTo(key) < 0) {
                return find(root.left, key);
            } else {
                return root;
            }
        }
        return root;
    }
    private void fastRemove(TreeNode node) {

        if (node.left != null && node.right != null) {
            //??ú??????????????
            TreeNode successor = minimum(node.right);
            node.StudentIDentificationCode = successor.StudentIDentificationCode;
            node = successor;
        }
        TreeNode replacement = (node.left != null) ? node.left : node.right;
        if (replacement != null) {
            replacement.parent = node.parent;
        }
        if (node.parent == null) {
            //????????û??????
            root = replacement;
        } else {
            if (node == node.parent.left) {
                node.parent.left = replacement;
            } else {
                node.parent.right = replacement;
            }
        }
        fixAfterUpdate(node);
        node.left = node.right = node.parent = null;
    }

    private TreeNode minimum(TreeNode root) {
        TreeNode cur = root;
        while (cur.left != null) {
            cur = cur.left;
        }
        return cur;
    }
    private TreeNode maximum(TreeNode root) {
        TreeNode cur = root;
        while (cur.right != null) {
            cur = cur.right;
        }
        return cur;
    }


    public void Allkeys() {
        minOrder(root);

    }

    private void minOrder(TreeNode root) {
        if (root != null) {
            minOrder(root.left);
            root.printNode();
            minOrder(root.right);
        }
    }
    public void getValues(AVLTreeList avl, long key) {

        if(contains(key)) {
            TreeNode successor = find(avl.root, key);
            System.out.println(successor.StudentIDentificationCode);
        }
        else {
            System.out.println("This key: " + key + " is not insert.");
        }
    }
    public TreeNode nextKey(AVLTreeList avl, long key) {
        TreeNode tree = find(avl.root, key);
        TreeNode next = null;

        if (tree.right != null) {
            next = minimum(tree.right);
            next.printNode();
            return next;
        }
        TreeNode parent = tree.parent;
        while (parent != null && tree != parent.left) {
            tree = parent;
            parent = tree.parent;
        }
        if(parent != null) {
            parent.printNode();
        }
        else {
            System.out.println(" Key " + key + " has no nextkey.");
        }
        return parent;

    }
    public TreeNode prevKey(AVLTreeList avl, long key) {
        TreeNode tree = find(avl.root, key);
        TreeNode next = null;

        if (tree.left != null) {
            next = maximum(tree.left);
            next.printNode();
            return next;
        }
        TreeNode parent = tree.parent;
        while (parent != null && tree != parent.right) {
            tree = parent;
            parent = tree.parent;
        }
        if(parent != null) {
            parent.printNode();
        }
        else {
            System.out.println(" Key " + key + " has no prevkey.");
        }
        return parent;
    }
    public void rangeKey(long key1, long key2) {
        minOrder(this.root, key1, key2);
    }
    private void minOrder(TreeNode root, long key1, long key2) {

        if (root != null && root.getKey() < key2) {
            minOrder(root.left, key1, key2);
            if(root.getKey() > key1) {
                root.printNode();
            }
            minOrder(root.right, key1, key2);
        }
    }
    public static void main(String[] args){
        AVLTreeList avl = new AVLTreeList();
        long a[] = new long[10];
        long x = 10000000;
        for(int i = 0; i < 10; i++) {
            x++;
            avl.add(avl, x, "WZT" + i);
        }
        //avl.Allkeys();
        avl.prevKey(avl, 10000003);
        avl.nextKey(avl, 10000003);
        avl.rangeKey(10000003, 10000009);
    }
}

