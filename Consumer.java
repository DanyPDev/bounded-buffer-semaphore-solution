
public class Consumer implements Runnable{
    
    private Buffer buffer;
    private MockSemaphore mutex;
    private MockSemaphore empty;
    private MockSemaphore full;
    private double probability;

    public Consumer(Buffer buffer, MockSemaphore mutex, MockSemaphore empty,MockSemaphore full, double probability){
        this.buffer = buffer;
        this.mutex = mutex;
        this.empty = empty;
        this.full = full;
        this.probability = probability;
    }

    @Override
    public void run() {
        
       
        while(true){
            
            double C = Math.random();
            if(Double.compare(C, probability) < 0){
                //do work bc probability (1-q) > C
            } else {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                continue;
            }
            
                
            System.out.println(full + " from the perspective of the Consumer");
            full.waits("Consumer"); // busy waits until producer does full.signals() to increment the "full" semaphore value
            System.out.println(full + " from the perspective of the Consumer");
            System.out.println("Consumer Busy not waiting because buffer is not empty, but might wait for mutex");
            
            
            System.out.println(mutex + " from the perspective of the Consumer");
            mutex.waits("Consumer"); // lock critical-section
            System.out.println(mutex + " from the perspective of the Consumer (acquiring mutex)");
            System.out.println("Consumer NOT busy waiting and is holding mutex");
        

            buffer.consumeItem();   //CONSUME ITEM
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            System.out.println("\n" + mutex + " from the perspective of the Consumer");
            mutex.signal(); // unlock 
            System.out.println(mutex + " from the perspective of the Consumer (releasing mutex)");
            System.out.println(empty + " from the perspective of the Consumer");
            empty.signal(); // signals the producer that it can produce since empty is no longer <= 0
            System.out.println(empty + " from the perspective of the Consumer");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

       }
        
    }
    
}
