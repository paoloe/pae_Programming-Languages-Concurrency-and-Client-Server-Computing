# Programming Languages Concurrency and Client Server Computing

This is an academic repository containing all of my lab work for this module.

## Week 0 - Threads

This week consisted of reading the the information provided on threads on JavaDoc, and playing around with some of the functions of threads. Tasks involved:

- Identifying the name of the current thread.
- Writing a small program to print the name of thread that runs first.
- Creating two threads and printing the names of both the threads running at the same time and making use of Thread.Sleep() to be able to print both statements.
- Working out a program provided by Almas (https://github.com/AlmasB/FXTutorials/blob/master/src/com/almasb/dl/DownloaderApp.java):
    - Answering why the program needs more than one thread
    - Identifying where the new threads are made
    - Modifying the application to download multiple URL's at once.

I got a great idea of how running multiple threads causes issues such as 'Race Condition' where when multiple threads try to access a shared resource, it gets its name because the threads are racing each other to finish executing their methods.

## Week 1 - Threads Continued

This week's lab covers practice of handling 'Race Conditions', it covers the two commonly encountered race conditions: *Check-Then-Act* and *Read-Modify-Write*.

### Check-Then-Act

What is it? - "This occurs when several thread needs to check some condition, such as reading a numeric value and comparing it to another, then act according to the result of the check.

For instance, we may have a critical section in which each thread checks the number of widgets available in our online shop, numWidgets. If we have run out of widgets, numWidgets == 0, we should order some more from our supplier. A scenario where this could go wrong:

- Thread A reads the value and finds that numWidgets == 0.
- Thread A is interrupted and Thread B takes over.
- Thread B reads the value and finds that numWidgets == 0.
- Thread B orders more widgets from the supplier.
- Thread B is interrupted and Thread A takes over.
- Thread A orders more widgets from the supplier.

We've overspent our Widget budget and the shop is going bust :(" 

Src: https://github.com/jimburton/raceconditions

Tasks: 
- [X] Make the method changeNumber thread-safe and fix the race condition: this was achieved by using the "Synchronized" keyword.
- [X] Change the int to an AtomicInteger, remove the "Synchronized" keyword and let the AtomicInteger fix the Race Condition.

#### AtomicInteger

What is it?

### Read-Modify-Write

What is it?
