package Java.Primer;

public class Counter {

    public Counter() {
    }
    
    public Counter(int initalValue) {
        this.count = initalValue;
    }

    private int count;


    public int getCount() {
        return count;
    }

    public void increment() {
        count++;
    }

    public void increment(int delta) {
        count += delta;
    }

    public void reset() {
        count = 0;
    }

}
