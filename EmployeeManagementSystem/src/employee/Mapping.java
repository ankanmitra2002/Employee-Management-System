package employee;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Mapping<K,V>{

    private ArrayList<Entry<K, V>>[] arr;
    private int storagecapacity;
    private int size;

    public Mapping(int storagecapacity) {
        this.storagecapacity = storagecapacity;
        arr = new ArrayList[storagecapacity];
        size = 0;
    }

    public void insert(K key, V value) {
        int index = getIndex(key);
        ArrayList<Entry<K, V>> bucketidx = arr[index];
        if (bucketidx == null) {
            bucketidx = new ArrayList<Entry<K, V>>();
            arr[index] = bucketidx;
        }
        for (Entry<K, V> i : bucketidx) {
            if (i.getKey().equals(key)) {
                i.setValue(value);
                return;
            }
        }
        bucketidx.add(new Entry<K, V>(key, value));
        size++;
    }

    public void update(K key,V value){
        int index = getIndex(key);
        ArrayList<Entry<K, V>> bucketidx = arr[index];
        for (Entry<K, V> i : bucketidx) {
            if (i.getKey().equals(key)) {
                i.setValue(value);
                return;
            }
        }
    }

    public V get(K key) {
        int index = getIndex(key);
        ArrayList<Entry<K, V>> bucketidx = arr[index];
        if (bucketidx == null) {
            return null;
        }
        for (Entry<K, V> i : bucketidx) {
            if (i.getKey().equals(key)) {
                return i.getValue();
            }
        }
        return null;
    }

    public void delete(K key) {
        int index = getIndex(key);
        ArrayList<Entry<K, V>> bucketidx = arr[index];
        if (bucketidx == null) {
            return;
        }
        for (Entry<K, V> i : bucketidx) {
            if (i.getKey().equals(key)) {
                bucketidx.remove(i);
                size--;
                return;
            }
        }
    }

    public int getSize() {
        return size;
    }

    private int getIndex(K key) {
        int index =  Math.abs(key.hashCode() % storagecapacity);
        return index;
    }

    public K[] getKeys(Class<K> keyClass){
        K[] keyset = (K[]) Array.newInstance(keyClass, size);
        for(int i = 0, j=0;j<storagecapacity;j++ ){
            if(arr[j]!=null){
                for(int k=0;k<arr[j].size();k++){
                    keyset[i] = arr[j].get(k).getKey();
                    i++;
                }
            }
        }
        return keyset;
    }

    public String toString(){
        String str ="";
        for(int i=0;i<storagecapacity;i++ ){
            if(arr[i]!=null){
                for(int j=0;j<arr[i].size();j++){
                    System.out.println(arr[i].get(j).getKey()+" "+arr[i].get(j).getValue());
                }
            }
        }
        return str;
    }

    public ArrayList<Entry<K, V>>[] getList(){
        return arr;
    }

    private static class Entry<K, V> {
        private final K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

    }
}



