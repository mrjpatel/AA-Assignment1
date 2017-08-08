import java.io.PrintStream;
			
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
		
		//First node
		if(mHead == null)	{
			mHead = new Node<T>(item);
			mSize++;
			return;
		}
		
		currentNode = mHead;
		
		//Check item/head if value is same
		if(item.equals(currentNode.getValue()))	{
			currentNode.setCount(currentNode.getCount() + 1);
			return;
		}
		
		//Check rest of list
		while(currentNode.getNext() != null)	{
			if(item.equals(currentNode.getValue()))	{
				currentNode.setCount(currentNode.getCount() + 1);
				return;
			}
    		currentNode = currentNode.getNext();
    	}
		
		//Add new node
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
        	currentNode = currentNode.getNext();
        }
		// default return, please override when you implement this method
		return count;
	} // end of add()
	
	
	public void removeOne(T item) {
		// Implement me!
		Node<T> currentNode;
		Node<T> nextNode;
		Node<T> prevNode;
		
		if(mHead == null)	{
			return;
		}
		
		//Reset head
		if(mHead.getValue().equals(item))	{
			mHead = mHead.getNext();
			mSize--;
			return;
		}
		
		currentNode = mHead;
		
		for(int i = 0; i < mSize; i++) {
			if(item.equals(currentNode.getValue()))	{
				//Get nodes of either side
        		nextNode = currentNode.getNext();
        		prevNode = currentNode.getPrev();
        		//Set to each other
        		prevNode.setNext(nextNode);
        		//Set tail
        		if(currentNode == mTail)	{
        			mTail = prevNode;
        		}
        		else	{
        			nextNode.setPrev(prevNode);
        		}
        		mSize--;
        		return;
        	}
	   		currentNode = currentNode.getNext();
		}
	} // end of removeOne()
	
	
	public void removeAll(T item) {
		// Implement me!
		
		// Errors still come up when removing everything from list
		
		Node<T> currentNode;
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
		
		currentNode = mHead;
		
		for(int i = 0; i < mSize; i++) {
			if(item.equals(currentNode.getValue()))	{
				//Get nodes of either side
        		nextNode = currentNode.getNext();
        		prevNode = currentNode.getPrev();
        		//Set to each other
        		prevNode.setNext(nextNode);
        		//Set tail
        		if(currentNode == mTail)	{
        			mTail = prevNode;
        		}
        		else	{
        			nextNode.setPrev(prevNode);
        		}
        		mSize--;
        	}
	   		currentNode = currentNode.getNext();
		}
	} // end of removeAll()
	
	
	public void print(PrintStream out) {
		// Implement me!
		Node<T> currentNode = mHead;
		for(int i = 0; i < mSize; i++) {
			out.println(currentNode.getValue() + printDelim + currentNode.getCount());
	   		currentNode = currentNode.getNext();
		}
	} // end of print()
	
	private class Node<T>
	{
		/** Values of node. */
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
	
	    public void setValue(T value) {
	        this.mValue = value;
	    }
	
	    public void setNext(Node<T> next) {
	        this.mNext = next;
	    }
	    
	    public void setPrev(Node<T> prev) {
	        this.mPrev = prev;
	    }

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}
	} // end of inner class Node
	
} // end of class LinkedListMultiset