package BST;

/**
 * Created by junjin on 5/24/17.
 */
public class App {
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.insert(9);
        bst.insert(-10);
        bst.insert(1);
        bst.insert(20);
        bst.insert(5);
        bst.insert(100);
        bst.insert(2);
        bst.insert(15);
        bst.insert(13);

        bst.traversal();
        System.out.println();
        System.out.println("Max Value:" + bst.getMax());
        System.out.println("Min Value:" + bst.getMin());
        System.out.println();
//        bst.delete(2); //leaf node
//        bst.delete(-10); //has right child
//        bst.delete(15);//has left child
        bst.delete(9);//has left and right child
        bst.traversal();
    }
}
