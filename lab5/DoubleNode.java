/**
 * @author Sharma, Deepak DS5930
 * @author Kurra, Sree Lakshmi SK9040
 *
 * @param <E>
 */
public class DoubleNode<E> {

	private E dataOne;

	private DoubleNode<E> left;
	private DoubleNode<E> right;
	public int leftChild = 0;
	public int rightChild = 0;

	DoubleNode(E obj) {
		this.setDataOne(obj);

		this.right = null;
		this.left = null;
	}

	/**
	 * /** to get the next left node of the storage
	 * 
	 * @return the next left node
	 */

	public DoubleNode<E> getLeft() {
		return this.left;
	}

	/**
	 * to get the next right node from the storage
	 * 
	 * @return the next right node.
	 */
	public DoubleNode<E> getRight() {
		return this.right;
	}

	/**
	 * to set the next left node of the storage
	 * 
	 * @param left
	 *            refers to the next left node of the storage.
	 */
	public void setLeft(DoubleNode<E> left) {
		this.left = left;

	}

	/**
	 * to set the next right node of the storage
	 * 
	 * @param right
	 *            refers to the next right node of the storage.
	 */
	public void setRight(DoubleNode<E> right) {
		this.right = right;

	}

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

}
