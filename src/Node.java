/**
 * 
 * A node class.
 * @author HPS251
 *
 * @param <E> Reference to the type of data stored in the node
 */
public class Node<E> {

	//Data used to create a node
	private E data;
	//The nodes next
	private Node next;
	
	/**
	 * The node Constructor
	 * @param data - data used to generate a node
	 */
	public Node(E data) {
		//Initiallize data and node.next
		this.data = data;
		this.next = null;
	}
	
	/**
	 * Return the next node.
	 */
	public Node getNext() {
		return this.next;
	}
	
	/**
	 * 
	 * @param node - Sets the current nodes next to the given node
	 */
	public void setNext(Node node) {
		this.next = node;
	}
	
	/**
	 * 
	 * @return return the data field of the node object
	 */
	public E getData() {
		return this.data;
	}
	
	/**
	 * Returns a string representation of the data.
	 */
	public String toString() {
		return this.data.toString();
	}
}
