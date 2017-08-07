import java.io.PrintStream;
import java.util.*;

public class SortedLinkedListMultiset<T> extends Multiset<T>
{
	protected Node<T> mHead;
	protected Node<T> mTail;
	int mSize;
	
	public SortedLinkedListMultiset() {
		mHead = null;
		mTail = null;
		mSize = 0;
	} // end of SortedLinkedListMultiset()
	
	
	public void add(T item) {
		// Implement me!
	} // end of add()
	
	
	public int search(T item) {		
		int count = 0;
        Node<T> currentNode = mHead;
        
        for(int i = 0; i < mSize; i++) {
        	if(item.equals(currentNode.getValue()))	{
        		count++;
        	}
        	currentNode = currentNode.getNext();
        }
		return count;
	} // end of add()
	
	
	public void removeOne(T item) {
		//checking if the linked list is empty
		if(mHead == null){
			return;
		}
		//deletes the head value
		if(mHead.getValue() == item){
			mHead = mHead.getNext();
		}
		
		Node<T> cNode = mHead;
		while(cNode.getNext() != null){
			if(cNode.getNext().getValue() == item){
				while (cNode != null) {
					if (cNode.getValue() == item) {
	                    Node<T> prevNode = cNode.getPrev();
	                    prevNode.setNext(cNode.getNext());
	                    
	                    if (cNode.getNext() != null) {
	                    	cNode.getNext().setPrev(prevNode);
	                    }
	                    else {
	                    	mTail = prevNode;
	                    }
	                    cNode = null;
	                    mSize--;
					}
					cNode = cNode.getNext();
				}
			}
		}
		
		
	} // end of removeOne()
	
	
	public void removeAll(T item) {
		Node<T> currentNode = mHead;
		Node<T> nextNode;
		Node<T> prevNode;
		
		for(int i = 0; i < mSize; i++) {
			if(item.equals(currentNode.getValue()))	{
				//Get nodes of either side
        		nextNode = currentNode.getNext();
        		prevNode = currentNode.getPrev();
        		//Set to each other
        		prevNode.setNext(nextNode);
        		nextNode.setPrev(prevNode);
        	}
	   		currentNode = mHead.getNext();
		}
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
	    
	    public Node(T value, Node<T> mNext, Node<T> mPrev)	{
	    	this.mValue = value;
	    	this.mNext = mNext;
	    	this.mPrev = mPrev;
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
	
} // end of class SortedLinkedListMultiset