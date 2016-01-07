
//import statements are placed here
import java.util.*;

/**
 * This class extends the Hash Set class and overrides the method:add, remove,
 * contains and size, backed by a hash map. It makes no guarantees as to the
 * iteration order of the set. In particular, it does not guarantee that the
 * order will remain constant over time. This class offers constant time
 * performance for the basic operations:add,remove,contains,size.
 * 
 * @author Deepak Sharma ds5930
 * @author Sree Lakshmi Kurra sk9040
 *
 */
public class HashSetNew extends HashSet {

	MyHashMap<Object, Object> hashMap = new MyHashMap<Object, Object>(20000);

	/**
	 * Adds the specified element to this set if it is not already present.
	 */
	public boolean add(Object obj) {
		return hashMap.put(obj, 1);
	}

	// Removes the specified element from this set if it is present.
	public boolean remove(Object obj) {
		return hashMap.remove(obj);
	}

	// Returns the number of elements in this set (its cardinality).
	public int size() {
		// System.out.println(hashMap.getHashMapSize());
		return hashMap.getHashMapSize();
	}

	// Returns true if this set contains no elements
	public boolean isEmpty() {
		return hashMap.getHashMapSize() == 0 ? true : false;
	}

	// Returns true if this set contains the specified element.
	public boolean contains(Object obj) {
		return hashMap.contains(obj);
	}

	// Returns an iterator over the elements in this set.
	public Iterator iterator() {
		return hashMap.iterator();
	}

	/**
	 * Removes all of the elements from this set
	 */
	public void clear() {

		hashMap.clear();

	}
}
