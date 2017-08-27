import java.io.PrintStream;

public class BstMultiset<T> extends Multiset<T>
{
	Node<T> mHead;
	
	public BstMultiset() {
		mHead = null;
	} // end of BstMultiset()

	public void add(T item) {
		
		Node<T> currentNode;
		Node<T> newNode;
		
		if(mHead == null)	{
			mHead = new Node<T>(item);
			return;
		}
		
		currentNode = mHead;
		
		while(currentNode != null)	{
			//If item the same, increase count
			if(currentNode.compareTo(item) == 0)	{
				currentNode.setCount(currentNode.getCount() +1);
				return;
			}
			
			if(currentNode.compareTo(item) < 0)	{
				if(currentNode.getLeft() == null)	{
					newNode = new Node<T>(item);
					currentNode.setLeft(newNode);
					return;
				} else	{
					currentNode = currentNode.getLeft();
					continue;
				}
			}
			
			if(currentNode.compareTo(item) > 0)	{
				if(currentNode.getRight() == null)	{
					newNode = new Node<T>(item);
					currentNode.setRight(newNode);
					return;
				} else	{
					currentNode = currentNode.getRight();
				}
			}
		}
		
	} // end of add()


	public int search(T item) {
		// Implement me!
		Node<T> node;
		
		if(mHead == null) {
			return 0;
		}
		
		node = mHead;
		
		while(node != null)	{
			if(item.equals(node.getValue()))	{
				return node.getCount();
			} else if(node.compareTo(item) < 0)	{
				if(node.getLeft() == null) {
					return 0;
				} else	{
					node = node.getLeft();
					continue;
				}
			} else if (node.compareTo(item) > 0)	{
				if(node.getRight() == null) {
					return 0;
				} else	{
					node = node.getRight();
				}
			}
		}
		
		return 0;
	} // end of add()

	public void removeOne(T item) {
		Node<T> node;
		
		if(mHead == null) {
			return;
		}
		
		node = mHead;
		
		while(node != null)	{
			if(item.equals(node.getValue()))	{
				if(node.getCount() > 1)	{
					node.setCount(node.getCount() - 1);
					return;
				}
				else	{
					removeAll(item);
					return;
				}
			} else if(node.compareTo(item) < 0)	{
				if(node.getLeft() == null) {
					return;
				} else	{
					node = node.getLeft();
					continue;
				}
			} else if (node.compareTo(item) > 0)	{
				if(node.getRight() == null) {
					return;
				} else	{
					node = node.getRight();
				}
			}
		}
		
		return;
	} // end of removeOne()
	
	
	public void removeAll(T item) {
		
		if(mHead == null)	{
			return;
		}
		
		mHead = removeNodes(mHead, item);
		
	} // end of removeAll()
	
	//REMOVE HELPER FUNCTION
	
	public Node<T> removeNodes(Node<T> node, T item) {
		Node<T> tempNode;
		
		if (node == null)	{
			return node;
		}
		
		if(node.compareTo(item) < 0)	{
			node.setLeft(removeNodes(node.getLeft(), item));
		}
		else if (node.compareTo(item) > 0)	{
			node.setRight(removeNodes(node.getRight(), item));
		}
		else	{
			if(node.getRight() == null)	{
				return node.getLeft();
			}
			else if (node.getLeft() == null)	{
				return node.getRight();
			}
			
			tempNode = minNode(node.getRight());
			node.setValue(tempNode.getValue());
			node.setCount(tempNode.getCount());
			
			node.setRight(removeNodes(node.getRight(), node.getValue()));
		}
		
		return node;
			
	}
	
	public Node<T> minNode(Node<T> node)	{
		while(node.getLeft() != null)	{
			node = node.getLeft();
		}
		return node;
	}


	public void print(PrintStream out) {
		if(mHead == null) {
			return;
		}
		
		printAllNodes(out, mHead);
		
	} // end of print()
	
	//PRINT HELPER FUNCTION
	
	public void printAllNodes(PrintStream out, Node<T> node)	{
		if(node == null) {
			return;
		}
		//Print all nodes recursively
		out.println(node.getValue() + printDelim + node.getCount());
		
		printAllNodes(out, node.getLeft());
		printAllNodes(out, node.getRight());
	}
	
	private class Node<T> implements Comparable<T>
	{
		/** Value of node. */
	    private T mValue;
	    private int count = 1;
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

		@Override
		public int compareTo(T item) {
			return getValue().toString().compareTo(item.toString());
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}
	} // end of inner class Node

} // end of class BstMultiset
