public class Cola {
    private Lista cola;
    public void enqueue(Object x){this.cola.insertLast(x);}
    public Object dequeue(){return this.cola.deleteFirst();}
    public Object peek(){return this.cola.getHead();}


}