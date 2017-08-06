import java.io.PrintStream;
import java.util.*;
			

public class LinkedListMultiset<T> extends Multiset<T>
{
	protected Node<T> mHead;
	protected Node<T> mTail;
	int mSize;
	
	public LinkedListMultiset() {
		// Implement me!
		mHead = null;
		mTail = null;
		mSize = 0;
	} // end of LinkedListMultiset()
	
	
	public void add(T item) {
		// Implement me!
		Node<T> currentNode;
		Node<T> newNode;
		
		if(mHead == null)	{
			mHead = new Node<T>(item);
			mSize++;
			return;
		}
		
		currentNode = mHead;
		
		while(currentNode.getNext() != null)	{
    		currentNode = mHead.getNext();
    	}
	
		newNode = new Node<T>(item, currentNode);	
		currentNode.setNext(newNode);
		mTail = newNode;
		mSize++;
		
	} // end of add()
	
	
	public int search(T item) {
		// Implement me!		
		int count = 0;
        Node<T> currentNode = mHead;
        
        for(int i = 0; i < mSize; i++) {
        	if(item.equals(currentNode.getValue()))	{
        		count++;
        	}
        	currentNode = mHead.getNext();
        }
		// default return, please override when you implement this method
		return count;
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
	    private Node<T> mNext;
	    /** Reference to previous node. */
	    private Node<T> mPrev;
	    
	    /**Constructors for head, middle, tail**/
	    public Node(T value) {
	        this.mValue = value;
	        this.mNext = null;
	        this.mPrev = null;
	    }
	    
	    public Node(T value, Node<T> mPrev) {
	    	this.mValue = value;
	    	this.mPrev = mPrev;
	    	this.mNext = null;
	    }
	    
	    /**Accessors**/
	    
	    public T getValue() {
	        return mValue;
	    }
	
	    public Node<T> getNext() {
	        return mNext;
	    }
	        
	    public Node<T> getPrev() {
	        return mPrev;
	    }
	
	    public void setValue(T value) {
	        this.mValue = value;
	    }
	
	    public void setNext(Node<T> next) {
	        this.mNext = next;
	    }
	    
	    public void setPrev(Node<T> prev) {
	        this.mPrev = prev;
	    }
	} // end of inner class Node
	
} // end of class LinkedListMultiset