public class MockSemaphore {
    
    public int permits;
    public String semName;

    public MockSemaphore(int permits,String semName){
        this.permits = permits;
        this.semName = semName;
    }

    public void waits(String name){
        int count = 0;
        while(permits <= 0){
            if(count == 0){
                System.out.println(name + " is currently busy waiting");
                count++;
            }
            Thread.yield();
        }

        permits--;

    }

   

    public void signal(){
        permits++;
    }

    @Override
    public String toString() {
        return "MockSemaphore called \"" + semName + "\" has current value of " + permits;
    }
    
    

}
