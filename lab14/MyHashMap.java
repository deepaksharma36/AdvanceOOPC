
//import statements are placed here
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This generic class offers constant time performance for the basic
 * operations(add, remove,contains,size).this hashMap implements hash map data
 * structure which allows the execution time of basic operations, such as get( )
 * and put( ), to remain constant even for large sets.
 * 
 * @author Deepak Sharma ds5930
 * @author Sree Lakshmi Kurra sk9040
 *
 * @param <T>
 *            implements generics
 * @param <E>
 */
public class MyHashMap<T, E> {
	private final int SIZE;
	private Slot<T, E> map[];
	private int hashMapSize = 0;
	private int hashModCount = 0;

	// this default constructor creates a map of the capacity 10000.
	public MyHashMap() {
		SIZE = 10000;
		map = new Slot[SIZE];
	}

	// this constructor creates a map of capacity mentioned by the user.
	public MyHashMap(int size) {
		SIZE = size;
		map = new Slot[SIZE];
	}

	// inserts any pair of key values in the hash map.
	public boolean put(T key, E value) {
		int hashCode = Math.abs(key.hashCode()) % SIZE;
		if (hashCode < 0)
			hashCode *= -1;
		Slot<T, E> slot = map[hashCode];
		Slot<T, E> pSlot = slot;
		if (slot != null) {
			while (slot != null) {
				if (slot.getKey().equals(key)) {
					return false;
				}
				pSlot = slot;
				slot = slot.nextSlot;
			}

			Slot<T, E> newslot = new Slot<T, E>(key, value);
			pSlot.nextSlot = newslot;
			hashMapSize++;
			hashModCount++;
			return true;
		} else {
			Slot<T, E> entryInNewBucket = new Slot<T, E>(key, value);
			map[hashCode] = entryInNewBucket;
			hashMapSize++;
			hashModCount++;
			return true;
		}
	}

	// this returns the size of the hash map
	public int getHashMapSize() {
		return hashMapSize;
	}

	// Returns the number of key-value mappings in this map.
	public void setHashMapSize(int hashMapSize) {
		this.hashMapSize = hashMapSize;
	}

	// Returns true if this map contains a mapping for the specified key.
	public boolean contains(T findKey) {
		int hashCode = findKey.hashCode() % SIZE;
		if (hashCode < 0)
			hashCode = hashCode * (-1);
		Slot<T, E> slot = map[hashCode];
		while (slot != null) {
			if (slot.getKey().equals(findKey)) {
				return true;
			}
			slot = slot.nextSlot;
		}
		return false;
	}

	// Removes the mapping for this key from this map if present.
	public boolean remove(T key) {
		int hashCode = Math.abs(key.hashCode()) % SIZE;
		if (hashCode < 0)
			hashCode *= -1;
		Slot<T, E> slot = map[hashCode];
		if (slot != null) {
			Slot<T, E> pSlot = slot;
			Slot<T, E> cSlot = slot.nextSlot;
			while (slot != null) {
				if (slot.getKey().equals(key)) {
					pSlot.nextSlot = cSlot;

					if (pSlot == slot)
						map[hashCode] = pSlot.nextSlot;

					hashMapSize--;
					return true;
				}
				pSlot = slot;
				slot = slot.nextSlot;
				cSlot = slot.nextSlot;
			}

		}
		return false;
	}

	// Removes all of the mapping from this map.
	public void clear() {
		for (int i = 0; i < SIZE; i++) {
			map[i] = null;
		}
		hashMapSize = 0;
	}

	// Returns an iterator over the elements in this hashMap.
	public Iterator iterator() {
		return new MyHashSetIterator();
	}

	/**
	 * 
	 * this generic class represents the data, where every data is stored in a
	 * hashMap.
	 * 
	 *
	 * @param <T>
	 * @param <E>
	 */
	class Slot<T, E> {
		private final T key;
		private E value;
		public Slot<T, E> nextSlot;

		Slot(T k, E v) {
			key = k;
			value = v;
		}

		public E getValue() {
			return value;
		}

		public void setValue(E value) {
			this.value = value;
		}

		public T getKey() {
			return key;
		}
	}

	/**
	 * this class implements iterator and gives definitions to the
	 * functions:hasNext, next,remove.
	 *
	 */
	class MyHashSetIterator implements Iterator {
		private int mapIndex;
		private int modCount = 0;
		private Slot<T, E> currentSlot;
		private Slot<T, E> nextSlot;

		public MyHashSetIterator() {
			nextSlot = null;
			currentSlot = null;
			mapIndex = -1;
			this.modCount = hashModCount;
		}

		// returns true when there is a element next to it in the map while
		// iterating in the map
		public final boolean hasNext() {
			if (currentSlot != null && currentSlot.nextSlot != null)
				return true;
			for (int b = mapIndex + 1; b < map.length; b++) {
				if (map[b] != null)
					return true;
			}
			return false;
		}

		// returns the next data element present in the map.
		public Object next() {
			if (this.modCount != hashModCount)
				throw new ConcurrentModificationException();
			if (currentSlot != null && currentSlot.nextSlot != null) {
				currentSlot = currentSlot.nextSlot;
				nextSlot = currentSlot.nextSlot;
			} else {
				do {
					mapIndex++;
					if (mapIndex >= map.length) {
						throw new NoSuchElementException();
					}

					currentSlot = map[mapIndex];
				} while (currentSlot == null);
			}
			if (currentSlot.nextSlot != null)
				nextSlot = currentSlot.nextSlot;
			else if (mapIndex + 1 < map.length)
				nextSlot = map[mapIndex + 1];
			else
				nextSlot = null;

			return currentSlot.key;
		}

		// while iterating remove method, it can remove the current object
		// present in the data.
		public void remove() {
			if (currentSlot.nextSlot == null)
				mapIndex += 1;
			if (MyHashMap.this.remove(currentSlot.key)) {
				currentSlot = nextSlot;
				this.modCount++;
			}
		}
	}
}