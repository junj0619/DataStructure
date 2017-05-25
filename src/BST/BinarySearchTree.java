package BST;

/**
 * Created by junjin on 5/24/17.
 */
public class BinarySearchTree<T extends Comparable<T>> implements Tree<T> {


    private Node<T> root;

    private void insertData(T data, Node<T> node) {
        //New data smaller than current node data, then go left child. Otherwise go right child.
        //compareTo: smaller = -1, equal = 0, greater = 1
        if (data.compareTo(node.getData()) < 0) {
            Node<T> leftChild = node.getLeftChild();
            if (leftChild == null) {
                node.setLeftChild(new Node(data));
            } else {
                insertData(data, leftChild);
            }
        } else {
            Node<T> rightChild = node.getRightChild();
            if (rightChild == null) {
                node.setRightChild(new Node(data));
            } else {
                insertData(data, rightChild);
            }
        }
    }

    private T getMaxValue(Node<T> node) {
        //The max value of BST must be in the right most node.
        Node<T> rightChild = node.getRightChild();
        if (rightChild == null)
            return node.getData();
        else
            return getMaxValue(rightChild);
    }

    private T getMinValue(Node<T> node) {
        //The min value of BST must be in the left most node.
        Node<T> leftNode = node.getLeftChild();
        if (leftNode == null)
            return node.getData();
        else
            return getMinValue(leftNode);
    }

    private void inOrderTraversal(Node<T> node) {
        Node<T> leftChild = node.getLeftChild();
        if (leftChild != null)
            inOrderTraversal(leftChild);

        System.out.print(node.getData() + " => ");

        Node<T> rightChild = node.getRightChild();
        if (rightChild != null)
            inOrderTraversal(rightChild);
    }

    @Override
    public void insert(T data) {
        if (root == null) {
            root = new Node(data);
        } else {
            insertData(data, root);
        }
    }

    @Override
    public T getMax() {
        if (root == null) return null;
        return getMaxValue(root);
    }

    @Override
    public T getMin() {
        if (root == null) return null;
        return getMinValue(root);
    }

    @Override
    public void traversal() {
        if (root == null) return;
        inOrderTraversal(root);
    }

    @Override
    public void delete(T data) {
        deleteNode(root, data);
    }

    private Node<T> deleteNode(Node<T> node, T data) {
        if (node == null) return null;

        if (data.compareTo(node.getData()) < 0) {
            node.setLeftChild(deleteNode(node.getLeftChild(), data));
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRightChild(deleteNode(node.getRightChild(), data));
        } else {
            //found the delete node

            //case 1: delete leaf node
            if (node.getLeftChild() == null && node.getRightChild() == null) {
                System.out.println("Delete the leaf node.");
                return null;
            }
            //case 2: delete node has left child.
            if (node.getRightChild() == null) {
                System.out.println("Delete node has left child...");
                Node<T> nextLeftChild = node.getLeftChild();
                node = null;
                return nextLeftChild;
            }
            if (node.getLeftChild() == null) {
                System.out.println("Delete node has right child...");
                Node<T> nextRightChild = node.getRightChild();
                node = null;
                return nextRightChild;
            }

            //case 3: delete node with left and right child.
            //Idea  : find current node predecessor(left child right most) or successor(right tree left most) node
            //        then swap the value between current node and them
            //        remove the leaf node of swap.
            System.out.println("Delete node has left and right child...");
            Node<T> predecessor = getPredecessor(node.getLeftChild());

            node.setData(predecessor.getData());
            node.setLeftChild(deleteNode(node.getLeftChild(), predecessor.getData()));
        }

        return node;
    }

    private Node<T> getPredecessor(Node<T> node) {
        if (node.getRightChild() != null)
            return getPredecessor(node.getRightChild());

        return node;
    }


}