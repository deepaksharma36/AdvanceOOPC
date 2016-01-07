/**
 * this class creates hash table for storing values of integers and the keys of
 * string type. this stores object of the slot class which has three attributes
 * of type int,string and slot itself.
 * 
 * @author Sharma, Deepak DS5930
 * @author Kurra, Sree Lakshmi SK9040
 *
 */
public class MyHashMap {

	private final int SIZE;
	private Slot map[];

	/**
	 * this constructor is automatically called when we create an object of
	 * MyHashMap class. sets the default size as 256.
	 */
	public MyHashMap() {
		SIZE = 256;
		map = new Slot[SIZE];
	}

	/**
	 * this constructor is called when the size is set.
	 * 
	 * @param size
	 */
	public MyHashMap(int size) {
		SIZE = size;
		map = new Slot[SIZE];
	}

	/**
	 * this put method stores the key, value pair into the hash map.
	 * 
	 * @param key
	 *            index of the value
	 * @param value
	 *            information stored of integer type.
	 */
	public void put(String key, int value) {
		int hashCode = Math.abs(key.hashCode()) % SIZE;
		if (hashCode < 0)
			hashCode *= -1;
		Slot slot = map[hashCode];
		if (slot != null) {
			if (slot.getKey().equals(key)) {
				slot.setValue(value);
			}

			while (slot.nextSlot != null) {
				slot = slot.nextSlot;
			}
			Slot newslot = new Slot(key, value);
			slot.nextSlot = newslot;

		} else {
			Slot entryInNewBucket = new Slot(key, value);
			map[hashCode] = entryInNewBucket;
		}

	}

	/**
	 * this method returns the slot where the key and the value is stored for
	 * the given key.
	 * 
	 * @param findKey
	 *            index to be found
	 * @return the index where the value is present.
	 */
	public Slot get(String findKey) {
		int hashCode = findKey.hashCode() % SIZE;
		if (hashCode < 0)
			hashCode = hashCode * (-1);
		Slot slot = map[hashCode];
		if (slot.getKey().equals(findKey)) {
			return slot;
		}

		while (slot != null) {
			if (slot.getKey().equals(findKey)) {
				return slot;
			}
			slot = slot.nextSlot;
		}
		return null;
	}
}

/**
 * this class slot is a node which is stored in the hash map.
 * 
 *
 */
class Slot {
	private final String key;
	private int value;
	public Slot nextSlot;

	Slot(String k, int v) {
		key = k;
		value = v;

	}

	/**
	 * to get the value
	 * 
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * to set the value
	 * 
	 * @return the set value
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * to get the index at the particular value
	 * 
	 * @return the index of teh value.
	 */
	public String getKey() {
		return key;
	}
}
