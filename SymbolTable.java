import java.util.ArrayList;
import java.util.Objects;

public class SymbolTable<K,V> {
    private ArrayList<HashNode<K,V>> table;
    private int size;
    private int capacity;

    public SymbolTable() {
        table = new ArrayList<>();
        capacity = 10;
        size = 0;

        for(int i = 0; i < capacity; i++)
            table.add(null);
    }

    private int getHashCode (K key) {
        return Objects.hashCode(key);
    }

    private int getIndex(K key) {
        int hashCode = getHashCode(key);
        int index = hashCode % capacity;
        if (index < 0)
            index = -index;
        return index;
    }

    public V get(K key) {
        int index = getIndex(key);
        int hashCode = getHashCode(key);

        HashNode<K, V> head = table.get(index);

        while (head != null) {
            if (head.key.equals(key) && head.hashCode == hashCode)
                return head.value;
            head = head.next;
        }

        return null;
    }

    public void add(K key, V value)
    {
        int index = getIndex(key);
        int hashCode = getHashCode(key);
        HashNode<K, V> head = table.get(index);

        while (head != null) {
            if (head.key.equals(key) && head.hashCode == hashCode) {
                head.value = value;
                return;
            }
            head = head.next;
        }

        size++;
        head = table.get(index);
        HashNode<K, V> newNode
                = new HashNode<K, V>(key, value, hashCode);
        newNode.next = head;
        table.set(index, newNode);

        // Double the hash table size if load factor of 0.6 is exceeded
        if ((1.0 * size) / capacity >= 0.6) {
            ArrayList<HashNode<K, V> > temp = table;
            table = new ArrayList<>();
            capacity = 2 * capacity;
            size = 0;
            for (int i = 0; i < capacity; i++)
                table.add(null);

            for (HashNode<K, V> headNode : temp) {
                while (headNode != null) {
                    add(headNode.key, headNode.value);
                    headNode = headNode.next;
                }
            }
        }
    }
}
