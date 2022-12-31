import java.util.Scanner;


public class Driver {
    public static void main(String[] args) {

        MockSemaphore mutex = new MockSemaphore(1,"mutex");
        MockSemaphore full = new MockSemaphore(0,"full");
        MockSemaphore empty = new MockSemaphore(10, "empty"); //which is the max capacity of the buffer
    
        Scanner sc = new Scanner(System.in);    
        System.out.println("Enter a q value from 0 to 1 (probability that p will produce an item): ");
        double q = sc.nextDouble();
        sc.close();
        Buffer buffer = new Buffer();

        Producer producerTask = new Producer(buffer, mutex, empty, full, q);
        Consumer consumerTask = new Consumer(buffer, mutex, empty, full, 1-q);
        
        
        Thread consumerThread = new Thread(consumerTask);
        consumerThread.start();

        Thread producerThread = new Thread(producerTask);
        producerThread.start();
       

    }
}
