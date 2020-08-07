package src;

interface Entry{
    public void setKey(Object k);
    public void setValue(Object v);
    public Object getKey();
    public Object getValue();
}
public class MyEntry implements Entry {
    private Object key;
    private Object value;

    public MyEntry(Object key, Object value) {
        this.key = key;
        this.value = value;
    }
    public MyEntry(){}

    @Override
    public String toString() {
        return "MyEntry{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }

    @Override
    public Object getKey() {
        return key;
    }

    @Override
    public void setKey(Object key) {
        this.key = key;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public void setValue(Object value) {
        this.value = value;
    }
}
