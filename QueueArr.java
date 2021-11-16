// Tan De Shao
// A0218351L
class QueueArr {
    public int[] arr;
    public int front, back;
    public int capacity;
    public final int INITSIZE = 1000;
    public int queueSize;

    public QueueArr() {
        arr = new int[INITSIZE]; // create array of integers
        front = 0; // the queue is empty
        back = 0;
        capacity = INITSIZE;
    }

    public boolean empty() {
        return (front == back);
    }

    public int size() {
        return queueSize;
    }

    public int get(int i) {
        return arr[(front + i) % capacity];
    }

    public Integer removeFirst() {
        if (empty())
            return null;
        Integer item = arr[front];
        front = (front + 1) % capacity;
        queueSize--;
        return item;
    }

    public Integer removeLast() {
        if (empty())
            return null;
        Integer item = arr[(capacity + back - 1) % capacity];
        arr[(capacity + back - 1) % capacity] = 0;
        back = (capacity + back - 1) % capacity;
        queueSize--;
        return item;
    }

    public void addFront(Integer item) {
        int newFront = arr.length - 1;
        queueSize++;
        if (front - 1 < 0) {
            if (newFront == back)
                enlargeArr();
            arr[capacity - 1] = item;
            front = capacity - 1;
        } else {
            if ((front - 1 == back)) {
                enlargeArr();
                arr[capacity - 1] = item;
                front = capacity - 1;
            } else {
                arr[front - 1] = item;
                front = front - 1;
            }
        }
    }

    public void addBack(Integer item) {
        queueSize++;
        if (((back + 1) % capacity) == front) // array is full
            enlargeArr();
        arr[back] = item;
        back = (back + 1) % capacity;
    }

    public boolean enlargeArr() {
        int newSize = capacity * 2;
        int[] temp = new int[newSize];
        for (int j = 0; j < capacity; j++) {
            // copy the front (1st) element, 2nd element, ..., in the
            // original array to the 1st (index 0), 2nd (index 1), ...,
            // position in the enlarged array
            temp[j] = arr[(front + j) % capacity];
        }
        front = 0;
        back = capacity - 1;
        arr = temp;
        capacity = newSize;
        return true;
    }
}
