package sem2.week0;

class week0 extends Thread{
    /**
     * Week 0 Lab Exercises
     */
    public static void main(String[] args){
        // running task three
        qThree();

        // running task four
        //qfour();
    }

    /**
     * 3. Referring back to javadoc, find a way to identify the name of the current 
     * thread. Guess the name of the thread that runs first. Write a small program 
     * to print the name of thread that runs first. Does it have the name you expected?
     */
    public static void qThree(){
        // creating a new thread
        Thread t1 = new Thread(() -> System.out.println("First Runnable Running"));

        // kicking off new thread
        t1.start();

        // printing out the thread name
        System.out.println("Thread Name One = " + t1.getName());
    }


    /**
     * 4. Spawn (create) a new thread in the program from (3) and print the names of 
     * both running threads. You might have to use Thread.sleep() on the first thread 
     * to be able to see both print statements.
     */
    public static void qfour(){
        // creating a new thread
        Thread t1 = new Thread(() -> System.out.println("First Runnable Running"));
        // second thread
        Thread t2 = new Thread(() -> System.out.println("Second Runnable Running"));
        // kicking off new thread
        t1.start();
        // starting second thread
        t2.start();
        // printing out the thread name
        System.out.println("Thread Name One = " + t1.getName());
        // printing out the second thread name
        System.out.println("Thread Name Two = " + t2.getName());
        // testing how many threads are active running
        System.out.println(activeCount());
    }
}