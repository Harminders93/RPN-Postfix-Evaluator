import java.util.LinkedList;

/**
 * 
 * @author HPS251
 *
 * @param <E> Type of objects (For this case, we're only focusing on Strings and Integers in the other classes.
 * This is the stack class. It uses a single linked list to replicate a stack.
 */
public class MyStack<E> {

	//A reference to the head
	private Node head;
	//A reference to the size.
	private int size;
	
	/**
	 * Constructor for the stack class. 
	 * 
	 */
	public MyStack() {
		//Head initiallized as null
		this.head = null;
		//Size initialized as 0
		size = 0;
	}
	
	/**
	 * 
	 * @return Boolean value indicating if stack is empty or not
	 */
	public boolean isEmpty() {
		//If head points to null, the stack is empty
		if(head == null) {
			return true;
		}
		//Otherwise, return true
		else {
			return false;
		}
	}
	
	/**
	 * Adds an item to the stack.
	 * @param obj - Data used to create a new node to be pushed onto a stack
	 */
	public void push(E obj) {
		//If the head is pointing to null. 
		//Create a stack with 1 object, the head object.
		if(this.head == null) {
			Node temp = new Node(obj);
			head = temp;
			size++;
		}
		//If the stack has objects in it. 
		//Add the new object to the head of the stack.
		else {
			Node temp = new Node(obj);
			temp.setNext(head);
			head = temp;
			size++;
		}
	}
	
	/**
	 * Removes an item from the stack.
	 * @return A string value representing the head data
	 */
	public String pop() {
		//If the head is null, return null
		if(head == null) {
			return null;
		}
		//Otherwise pop the head, and return the data of the node popped.
		else {
			Node temp = head;
			head = head.getNext();
			size--;
			return temp.toString();
		}
	}
	
	/**
	 * Look at the top of the stack.
	 * @return The data for the top of the stack.
	 */
	public E peek() {
		//If the head points to null, return null
		if(head == null) {
			return null;
		}
		//Return the head's data.
		else {
			return (E) head.getData();
		}
	}
	
	/**
	 * This is a string representation of the stack.
	 */
	public String toString() {
		//If the stack isn't true, generate a string representation of the stack. Labeling the top and bottom.
		if(isEmpty() != true) {
			StringBuilder result = new StringBuilder();
			result.append("[Top] ");
			Node temp = head;
			while(temp != null) {
				result.append(temp.toString()).append(" ");
				temp = temp.getNext();
			}
			result.append("[Bottom]");
			return result.toString();
		}
		else {
			return "Empty Stack";
		}
	}
	
	/**
	 * Obtain the size of the stack.
	 * @return the size of the stack.
	 */
	public int getSize() {
		return size;
	}
}
