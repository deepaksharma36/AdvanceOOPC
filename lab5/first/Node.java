/**
 * 
 * @author Sharma, Deepak DS5930
 * @author Kurra, Sree Lakshmi SK9040
 */
public class Node<E, V> {
	private E dataOne;
	private V dataTwo;
	private Node<E, V> next;

	Node(E obj, V elm) {
		this.setDataOne(obj);
		this.setDataTwo(elm);
		this.next = null;
	}

	/**
	 * /** to get the next node of the storage
	 * 
	 * @return the next node
	 */
	public Node<E, V> getNext() {
		return this.next;
	}

	/**
	 * to set the next node of the storage
	 * 
	 * @param next
	 *            refers to the next node of the storage.
	 */

	public void setNext(Node<E, V> next) {
		this.next = next;
	}

	/**
	 * to get the data one element.
	 * 
	 * @param dataOne
	 *            refers to the dataone element.
	 */
	public E getDataOne() {
		return dataOne;
	}

	/**
	 * to set the data one element.
	 * 
	 * @param dataOne
	 *            refers to the dataone element.
	 */
	public void setDataOne(E dataOne) {
		this.dataOne = dataOne;
	}

	/**
	 * to get the data two element.
	 * 
	 * @param dataTwo
	 *            refers to the dataTwo element.
	 */
	public V getDataTwo() {
		return dataTwo;
	}

	/**
	 * to set the data two element.
	 * 
	 * @param dataTwo
	 *            refers to the datatwo element.
	 */
	public void setDataTwo(V dataTwo) {
		this.dataTwo = dataTwo;
	}

}