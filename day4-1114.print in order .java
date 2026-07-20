import java.util.concurrent.Semaphore;

class Foo {

    private Semaphore second = new Semaphore(0);
    private Semaphore third = new Semaphore(0);

    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first".
        printFirst.run();

        // Allow second() to execute
        second.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {

        // Wait until first() finishes
        second.acquire();

        // printSecond.run() outputs "second".
        printSecond.run();

        // Allow third() to execute
        third.release();
    }

    public void third(Runnable printThird) throws InterruptedException {

        // Wait until second() finishes
        third.acquire();

        // printThird.run() outputs "third".
        printThird.run();
    }
}
