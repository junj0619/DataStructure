package BST;

/**
 * Created by junjin on 5/24/17.
 */
public interface Tree<T> {
    void traversal();

    void insert(T data);

    void delete(T data);

    T getMax();

    T getMin();

}