import java.io.PrintStream;

public class BstMultiset<T> extends Multiset<T>
{
	Node<T> mHead;
	
	public BstMultiset() {
		mHead = null;
		// Implement me!
	} // end of BstMultiset()

	public void add(T item) {
		// Implement me!
	} // end of add()


	public int search(T item) {
		// Implement me!
		
		// default return, please override when you implement this method
		return 0;
	} // end of add()


	public void removeOne(T item) {
		// Implement me!
	} // end of removeOne()
	
	
	public void removeAll(T item) {
		// Implement me!
	} // end of removeAll()


	public void print(PrintStream out) {
		// Implement me!
	} // end of print()
	
	private class Node<T>
	{
		/** Value of node. */
	    private T mValue;
	    /** Reference to next node. */
	    private Node<T> mLeft;
	    /** Reference to previous node. */
	    private Node<T> mRight;
	    
	    /**Constructors for head, middle, tail**/
	    public Node(T value) {
	        this.mValue = value;
	        this.mLeft = null;
	        this.mRight = null;
	    }
	    
	    /**Accessors**/
	    
	    public T getValue() {
	        return mValue;
	    }
	
	    public Node<T> getLeft() {
	        return mLeft;
	    }
	        
	    public Node<T> getRight() {
	        return mRight;
	    }
	
	    public void setValue(T value) {
	        this.mValue = value;
	    }
	
	    public void setLeft(Node<T> left) {
	        this.mLeft = left;
	    }
	    
	    public void setRight(Node<T> right) {
	        this.mRight = right;
	    }
	} // end of inner class Node

} // end of class BstMultiset
