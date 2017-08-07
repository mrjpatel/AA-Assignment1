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
		Node<T> newNode;
		Node<T> cNode;
		
		//adding value at the start of linked list
		if(mHead == null){
			mHead = new Node<T>(item);
			mTail = mHead;
			mSize++;
			return;
		}
		
		cNode = mHead;
		while(cNode.getNext() != null){
			cNode = cNode.getNext();
		}
		
		newNode = new Node<T>(item, cNode);	
		cNode.setNext(newNode);
		mTail = newNode;
		mSize++;
		
		//needs sorting yet
		
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
		Node<T> cNode = mHead;
		Node<T> nextNode;
		Node<T> prevNode;
		
		//checking if the linked list is empty
		if(mHead == null){
			return;
		}
		//deletes the head value
		if(mHead.getValue() == item){
			//checks if the size of linked list is 1
			if(mSize == 1){
				mHead = mTail = null;
			}
			else{
				mHead = mHead.getNext();
				mHead.setPrev(null);
			}
			mSize--;
			return;
		}
		
		for(int i = 0; i < mSize; i++){
			if(item.equals(cNode.getValue())){
				//getting next and previous node
				nextNode = cNode.getNext();
        		prevNode = cNode.getPrev();
        		
        		//setting next and previous node
        		prevNode.setNext(nextNode);
        		nextNode.setPrev(prevNode);
        		
        		//checking if the current node is tail
        		if(cNode == mTail)	{
        			mTail = prevNode;
        		}
        		mSize--;
        		return;
			}
			cNode = cNode.getNext();
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
		Node<T> cNode = mHead;
		int count = 0;
		T item = cNode.getValue();
		
		for(int i = 0; i < mSize; i++) {
			if(item.equals(cNode.getValue())){
        		count++;
        	}
			out.println(cNode.getValue() + " | " + count);
	   		cNode = cNode.getNext();
		}
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
	
} // end of class SortedLinkedListMultiset