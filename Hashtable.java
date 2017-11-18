/**
 * Hashtable
 */
public class Hashtable {
	
	private int numOfChains = 100000;
	private Node[] arrayOfChains = new Node[numOfChains];
	
	/**
	 * Node
	 */
	private class Node {
        private String key;
        private String val;
        private Node next;

        public Node(String key, String val, Node next)  {
            this.key  = key;
            this.val  = val;
            this.next = next;
        }
    }
	
	/**
	 * hash
	 * 
	 * @param
	 * @return
	 */
	private int hash(String key) {
		return ((key.hashCode() & 0x7fffffff) % numOfChains);
	}
	
	/**
	 * Returns “true” if a key/value object pair
	 * 	(with the key matching the argument and any value).
	 * 
	 * @param
	 * @return 
	 */
	public boolean containsKey(String key) {
		if (key == null) {
			throw new IllegalArgumentException("no containsKey");
		}
		
		return (get(key) != null);
	}
	
	/**
	 * Returns the value associated with the key which is passed as an argument;
	 * 	returns null if no key/value pair is contained by the Hashtable instance.
	 * 
	 * @param
	 * @return 
	 */
	public String get(String key) {
		if (key == null) {
    			throw new IllegalArgumentException("null");
		}
		
		int i = hash(key);
		
		for (Node x = arrayOfChains[i]; x != null; x = x.next) {
			if (key.equals(x.key)) {
				return x.val;
			}
		}
		
		return null;
	}
	
	/**
	 * Adds the key/value pair into the Hashtable instance.
	 * If there is an existing key/value pair,
	 * 	the Hashtable instance replaces the stored value with the argument value.
	 * 
	 * @param
	 * @param
	 */
	public void put(String key, String val) {
		int i = hash(key);
		
		for (Node x = arrayOfChains[i]; x != null; x = x.next) {
			if (key.equals(x.key)) {
				x.val = val;
				return;
			}
		}
		
		arrayOfChains[i] = new Node(key, val, arrayOfChains[i]);
	}

	/**
	 * Removes the key/value pair from the Hashtable instance
	 * 	and returns the value associated with the key to the caller.
	 * Throws an Exception instance if the key is not present in the Hashtable instance.
	 * 
	 * @param
	 * @return
	 */
	public String remove(String key) {
		if (key == null) {
			throw new IllegalArgumentException("null");
		}
		
		int i = hash(key);
		return arrayOfChains[i].val = remove(arrayOfChains[i], key);
	}

	/**
	 * remove
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public String remove(Node x, String key) {
        if (key.equals(x.key)) {
        		numOfChains--;
        		
            return x.next.val;
        }
        
        x.next.val = remove(x.next, key);
        return x.val;
	}
	
}