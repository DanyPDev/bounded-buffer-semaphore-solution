public class Buffer {
    
    public Item[] buffer = new Item[10];
    public int index = 0;
    public int size = 0;




    public void addNewItem(Item newItem){
            buffer[index] = newItem;
            if(index != buffer.length-1){
                index++;
            }
            size++;
            System.out.println("A produced item has been added to the shared buffer. There are currently " + size + " items in buffer   <-----------------------------------------------------");
            if(size == 10){
                System.out.println("buffer full");
            }
            if(size == 0){
                System.out.println("buffer empty");
            }
           
    }

    public void consumeItem(){
            buffer[index] = null;
            if(index != 0){
                index--;
            }
            size--;
            System.out.println("An item has been consumed from the shared buffer. There are currently " + size + " items in buffer   <-----------------------------------------------------");
            if(size == 10){
                System.out.println("buffer full");
            }
            if(size == 0){
                System.out.println("buffer empty");
            }
    }
}


