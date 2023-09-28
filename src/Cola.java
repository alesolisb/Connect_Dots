public class Cola extends Lista<Player>{
    public void enqueue(Player x){insertLast(x);}
    public Player dequeue(){return deleteFirst().getData();}
    public Player peek(){return getHead().getData();}
    public void requeue(){
        enqueue(dequeue());
    }
}