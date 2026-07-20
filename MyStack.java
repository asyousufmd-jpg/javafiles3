import java.util.LinkedList;
import java.util.Queue;

public class MyStack {
    // We use two standard queues
    private Queue<Integer> q1 = new LinkedList<>();
    private Queue<Integer> q2 = new LinkedList<>();

    public MyStack() {
        
    }
    
    // Non-optimized approach: shift elements back and forth on every push
    public void push(int x) {
        // 1. Put the new element into the empty queue q2
        q2.add(x);
        
        // 2. Move everything from q1 to q2 so the new element stays at the front
        while (!q1.isEmpty()) {
            q2.add(q1.remove());
        }
        
        // 3. Swap the names of q1 and q2 so q1 always holds the main stack elements
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;
    }
    
    // O(1) time - Pop from the front of q1
    public int pop() {
        return q1.remove();
    }
    
    // O(1) time - Peek at the front of q1
    public int top() {
        return q1.peek();
    }
    
    // O(1) time - Check if q1 is empty
    public boolean empty() {
        return q1.isEmpty();
    }
}