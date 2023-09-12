public class Cola {
    private Lista<Player> cola;
    public void enqueue(Player x){this.cola.insertLast(x);}
    public Player dequeue(){return this.cola.deleteFirst().getData();}
    public Player peek(){return this.cola.getHead().getData();}


}