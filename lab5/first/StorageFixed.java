/**
 * 
 * @author Sharma, Deepak DS5930
 * @author Kurra, Sree Lakshmi SK9040
 *
 */
public class StorageFixed<E, V> implements Storage<E, V> {
	private Node<E, V> Start;
	private Node<E, V> End;
	private int size = 0;
	private final int storageSize = 100;

	StorageFixed() {
		this.Start = null;
		this.End = null;
	}

	/**
	 * this adds the element with the complexity of o(1) in the storage.
	 */
	public boolean add(E e) {
		if (size < storageSize) {
			Node<E, V> copyRef = this.Start;
			Node<E, V> newNode = new Node<E, V>(e, null);
			copyRef = End;
			if (End != null) {

				if (copyRef.getNext() == null) {
					copyRef.setNext(newNode);
					End = copyRef.getNext();
					size++;

				}
			} else if (End == null) {
				size++;
				Start = newNode;
				End = Start;
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	/**
	 * this adds the element with the complexity of o(1) in the storage.
	 */
	public void add(int index, E element) {
		if (size < storageSize) {

			Node<E, V> temp, temp1;
			temp = Start;
			if (index < size) {
				while (index > 1 && temp.getNext() != null) {
					temp = temp.getNext();
					index--;
				}
				Node<E, V> newNode = new Node<E, V>(element, null);
				temp1 = temp.getNext();
				temp.setNext(newNode);
				newNode.setNext(temp1);
			} else {
				System.out.println("Invalid Operation");
			}
		}

	}

	@Override
	/**
	 * this adds the element with the complexity of o(1) in the storage.
	 */
	public void addElement(E obj) {
		if (size < storageSize) {

			Node<E, V> copyRef = this.End;
			Node<E, V> newNode = new Node<E, V>(obj, null);

			if (End != null) {

				if (copyRef.getNext() == null) {
					copyRef.setNext(newNode);
					copyRef = copyRef.getNext();
					End = copyRef;
					size++;
					System.out.println("new nodes added from here");
				}
			} else if (End == null) {
				System.out.println("First node added");
				size++;
				Start = newNode;
				End = Start;
			}
		}
	}

	/**
	 * this adds the component with the complexity of o(1) in the storage.
	 */
	public void addElement(E obj, V elem) {
		if (size < storageSize) {

			Node<E, V> copyRef = this.End;
			Node<E, V> newNode = new Node<E, V>(obj, elem);

			if (End != null) {
				if (copyRef.getNext() == null) {
					size++;
					copyRef.setNext(newNode);
					End = copyRef.getNext();
					System.out.println("new nodes added >>>>");
				}
			} else if (End == null)

			{
				size++;
				System.out.println("First node added");
				Start = newNode;
				End = Start;
			}
		}
	}

	// returns the capacity of the storage.
	public int capacity() {

		return size;
	}

	// deletes all the elements of the storage
	public void clear() {
		Node<E, V> temp;
		temp = Start;
		while (Start.getNext() != null) {
			temp = Start;
			Start = Start.getNext();
			temp = null;
		}
		Start = null;

	}

	@Override
	// returns the first stored element from the storage.
	public E firstElement() {

		return Start.getDataOne();
	}

	@Override
	// returns the index of provided element.
	public E get(int index) {
		Node<E, V> temp;
		temp = Start;
		while (index > 0) {
			temp = temp.getNext();
			index--;
		}
		return temp.getDataOne();
	}

	@Override
	// returns the last element
	public E lastElement() {

		return End.getDataOne();
	}

	// makes the copy of storage and returns the reference of the same.
	public Object clone() {
		Node<E, V> temp = Start;
		Node<E, V> StartCopy;

		Node<E, V> copy = new Node<E, V>(Start.getDataOne(), Start.getDataTwo());
		StartCopy = copy;
		while (temp.getNext() != null) {
			temp = temp.getNext();
			Node<E, V> newNode = new Node<E, V>(temp.getDataOne(), temp.getDataTwo());
			copy.setNext(newNode);
			copy = copy.getNext();

		}
		return (Object) StartCopy;
	}

}
