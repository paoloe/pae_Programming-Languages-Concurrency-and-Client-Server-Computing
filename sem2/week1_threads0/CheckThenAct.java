package sem2.week1_threads0;

import java.util.concurrent.atomic.AtomicInteger;

// Example adapted from https://www.javacodegeeks.com

public class CheckThenAct {
    /**
     * Task 2: Replace the int with an AtomicInt which is initialised with with a
     *  value 0, remove the synchronised keyword and let AtomicInteger take care
     *  of the problem.
     */

    // private int number;
    private AtomicInteger number = new AtomicInteger(0);

    /**
     * Task 1: make the method changeNumber() thread-safe and fix the race condition
     *  - do this by adding the synchronized keyword
     */
    /* public synchronized void changeNumber() {
        if (number == 0) {
            Utils.simulateInterrupt(5);
            System.out.println(Thread.currentThread().getName() + " | Changed");
            number = -1;
        } else {
            System.out.println(Thread.currentThread().getName() + " | Not changed");
        }
    } */

    public void changeNumber(){
        // using the method compareAndSet to set expected and update value
        if (number.intValue() == 0){
            number.compareAndSet(0, -1);
            System.out.println(Thread.currentThread().getName() + " | Changed");
        } else {
            System.out.println(Thread.currentThread().getName() + " | Not changed");
        }
    }

    public static void main(String[] args) {
        final CheckThenAct checkAct = new CheckThenAct();

        for (int i = 0; i < 50; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    checkAct.changeNumber();
                }
            }, "T" + i).start();
        }
    }
}