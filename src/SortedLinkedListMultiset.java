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
		if(item.equals(cNode.getValue()))	{
			cNode.setCount(cNode.getCount() + 1);
			return;
		}
		
		while(cNode.getNext() != null){
			cNode = cNode.getNext();
			if(item.equals(cNode.getValue()))	{
				cNode.setCount(cNode.getCount() + 1);
				return;
			}
		}
		
		newNode = new Node<T>(item, cNode);	
		cNode.setNext(newNode);
		mTail = newNode;
		mSize++;
		
		sort();
		
	} // end of add()
	
	public void sort() {

		Node<T> cNode = mHead;
		
		while(cNode != null){
			Node <T> smallValue = cNode;
			
			for(Node<T> nextNode = cNode; nextNode != null; nextNode = nextNode.getNext()){
				if (smallValue.getValue().toString().compareTo(nextNode.getValue().toString()) > 0){
					smallValue = nextNode;
				}
				
				Node<T> temp = new Node<T>(cNode.getValue());
				int tempCount = cNode.getCount();
				
				cNode.setValue(smallValue.getValue());
				cNode.setCount(smallValue.getCount());
				
				smallValue.setValue(temp.getValue());
				smallValue.setCount(tempCount);
			}
			cNode = cNode.getNext();
		}
	}
	
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
	
	private class Node<T>
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
	} // end of inner class Node
	
} // end of class SortedLinkedListMultiset