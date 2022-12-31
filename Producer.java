
public class Producer implements Runnable {
    
    private Buffer buffer;
    private MockSemaphore mutex;
    private MockSemaphore full;
    private MockSemaphore empty;
    private double probability;

    public Producer(Buffer buffer, MockSemaphore mutex, MockSemaphore empty,MockSemaphore full, double probability){
        this.buffer = buffer;
        this.mutex = mutex;
        this.empty = empty;
        this.full = full;
        this.probability = probability;
    }

    @Override
    public void run() {
       
        while(true){
            
          
            double P = Math.random();
            if(Double.compare(P, probability) < 0){
                //do work bc probability > P
            } else {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                continue;
            }

            //creating product

        
            System.out.println(empty + " from the perspective of the Producer");
            //this is to prevent producer from adding to buffer when it is full
            empty.waits("Producer"); // semaphore empty has intial value of n because if the value was 0, then that would mean the producer busy waits, but that does not make sense, since we start with empty buffer 
            System.out.println("Producer not busy waiting because buffer not full, but might wait because of mutex");
            System.out.println(empty + " from the perspective of the Producer");
        
        
            System.out.println(mutex + " from the perspective of the Producer");
            mutex.waits("Consumer"); // lock critical-section
            System.out.println(mutex + " from the perspective of the Producer  (acquiring mutex)");
            System.out.println("Producer NOT busy waiting and is holding mutex");
          

            buffer.addNewItem(new Item()); // ADD NEW ITEM
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            System.out.println("\n" + mutex + " from the perspective of the Producer");
            mutex.signal(); // unlock 
            System.out.println(mutex + " from the perspective of the Producer (releasing mutex)");
           
            System.out.println(full + " from the perspective of the Producer");
            full.signal(); // signals the consumer that it can start consuming
            System.out.println(full + " from the perspective of the Producer");
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

       }
        
    }

}
