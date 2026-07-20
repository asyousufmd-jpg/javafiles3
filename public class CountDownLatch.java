 public class CountDownLatch {

    private final CountDownLatch latch1;
    private final CountDownLatch latch2;

    public Foo() {
        // Initialize latch1 with a count of 1 to block second() until first() completes
        latch1 = new CountDownLatch(1);
        // Initialize latch2 with a count of 1 to block third() until second() completes
        latch2 = new CountDownLatch(1);
    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        // Decrement the count of the first latch, releasing the thread waiting on it
        latch1.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        // Wait until latch1 count reaches 0 (meaning first() has finished)
        latch1.await();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        // Decrement the count of the second latch, releasing the thread waiting on third()
        latch2.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {
        // Wait until latch2 count reaches 0 (meaning second() has finished)
        latch2.await();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}