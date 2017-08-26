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
		
		Node<T> newNode = new Node<T> (item);
		Node<T> cNode = mHead;
		
		while(cNode != null){
			if(item.equals(cNode.getValue())){
				cNode.setCount(cNode.getCount() + 1);
				return;
			}
			cNode = cNode.getNext();
		}
		
		if(mHead == null)	{
			mHead = newNode;
			mTail = newNode;
			mSize++;
			return;
		} else if (mHead.compareTo(item) > 0)	{
			newNode.setNext(mHead);
			mHead.setPrev(newNode);
			mHead = newNode;
			mSize++;
			return;
		} else	{
			Node<T> prevNode = mHead;
			Node<T> currentNode = mHead.getNext();
			while(currentNode != null && prevNode.compareTo(currentNode.getValue()) > prevNode.compareTo(item) )	{
				prevNode = currentNode;
				currentNode = currentNode.getNext();
			}
			if(currentNode == null) {
				prevNode.setNext(newNode);
				newNode.setPrev(prevNode);
			} else	{
				prevNode.setNext(newNode);
				currentNode.setPrev(newNode);
				newNode.setPrev(prevNode);
				newNode.setNext(currentNode);
			}
			mTail = newNode;
			mSize++;
			
			return;
		}
				
	} // end of add()
	
	public int search(T item) {		
		int count = 0;
        Node<T> currentNode = mHead;
        
        if(mSize == 0){
        	return count;
        }
        
        if(item.equals(currentNode.getValue()))	{
			count = currentNode.getCount();
		}
        
        while(currentNode.getNext() != null)	{
    		currentNode = currentNode.getNext();
    		if(item.equals(currentNode.getValue()))	{
    			count = currentNode.getCount();
			}
        }
    		return count;
    				
	} // end of add()
	
	
	public void removeOne(T item) {
		Node<T> cNode;
		Node<T> nextNode;
		Node<T> prevNode;
		
		//checking if the linked list is empty
		if(mHead == null){
			return;
		}
		//deletes the head value
		if(mHead.getValue().equals(item)){
			//checks if the size of linked list is 1
			if(mHead.getCount() > 1){
				mHead.setCount(mHead.getCount() - 1);
				return;
			}
			mHead = mHead.getNext();
			mHead.setPrev(null);
			mSize--;
			return;
		}
		
		cNode = mHead;
		for(int i = 0; i < mSize; i++){
			if(item.equals(cNode.getValue())){
				
				if(cNode.getCount() > 1){
					cNode.setCount(cNode.getCount() - 1);
					return;
				}
				
				//getting next and previous node
				nextNode = cNode.getNext();
        		prevNode = cNode.getPrev();
        		
        		//setting next and previous node
        		prevNode.setNext(nextNode);
        		
        		//checking if the current node is tail
        		if(cNode == mTail)	{
        			mTail = prevNode;
        		}
        		else {
        			nextNode.setPrev(prevNode);
        		}
        		mSize--;
        		return;
			}
			cNode = cNode.getNext();
		}		
	} // end of removeOne()
	
	
	public void removeAll(T item) {
		Node<T> cNode;
		Node<T> nextNode;
		Node<T> prevNode;
		
		if(mHead == null)	{
			return;
		}
		
		//Reset head
		if(mHead.getValue().equals(item))	{
			mHead = mHead.getNext();
			mSize--;
		}
		
		cNode = mHead;
		
		for(int i = 0; i < mSize; i++) {
			if(item.equals(cNode.getValue()))	{
				//Get nodes of either side
        		nextNode = cNode.getNext();
        		prevNode = cNode.getPrev();
        		//Set to each other
        		prevNode.setNext(nextNode);
        		//Set tail
        		if(cNode == mTail)	{
        			mTail = prevNode;
        		}
        		else	{
        			nextNode.setPrev(prevNode);
        		}
        		mSize--;
        	}
	   		cNode = cNode.getNext();
		}
	} // end of removeAll()
	
	
	public void print(PrintStream out) {
		Node<T> cNode = mHead;
		for(int i = 0; i < mSize; i++) {
			out.println(cNode.getValue() + " | " + cNode.getCount());
	   		cNode = cNode.getNext();
		}
	} // end of print()
	
	private class Node<T> implements Comparable<T>
	{
		/** Value of node. */
	    private T mValue;
	    private int count = 1;
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
	    
	    public int getCount() {
			return count;
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
	    
		public void setCount(int count) {
			this.count = count;
		}
		
		@Override
		public int compareTo(T item) {
			return getValue().toString().compareTo(item.toString());
		}
		
	} // end of inner class Node
	
} // end of class SortedLinkedListMultiset