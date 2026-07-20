import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

class FizzBuzz {
    private int n;
    private int current = 1;

    private Semaphore fizz = new Semaphore(0);
    private Semaphore buzz = new Semaphore(0);
    private Semaphore fizzbuzz = new Semaphore(0);
    private Semaphore number = new Semaphore(1);

    public FizzBuzz(int n) {
        this.n = n;
    }

    private void next() {
        current++;

        if (current > n) {
            fizz.release();
            buzz.release();
            fizzbuzz.release();
            number.release();
            return;
        }

        if (current % 3 == 0 && current % 5 == 0) {
            fizzbuzz.release();
        } else if (current % 3 == 0) {
            fizz.release();
        } else if (current % 5 == 0) {
            buzz.release();
        } else {
            number.release();
        }
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        while (true) {
            fizz.acquire();

            if (current > n) return;

            printFizz.run();
            next();
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        while (true) {
            buzz.acquire();

            if (current > n) return;

            printBuzz.run();
            next();
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (true) {
            fizzbuzz.acquire();

            if (current > n) return;

            printFizzBuzz.run();
            next();
        }
    }

    // printNumber.accept(x) outputs "x".
    public void number(IntConsumer printNumber) throws InterruptedException {
        while (true) {
            number.acquire();

            if (current > n) return;

            printNumber.accept(current);
            next();
        }
    }
}
