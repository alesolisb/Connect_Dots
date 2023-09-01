import javax.management.StandardEmitterMBean;

public class Lista {
    private Nodo head;
    private Nodo last;
    private int size;

    public Lista(){
        this.head = null;
        this.size= 0;
    }
    public boolean isEmpty(){
        return this.head==null;
    }
    public int getSize(){
        return this.size;
    }
    public void insetFirst(Object data){
        Nodo nodo = new Nodo(data);
        if (this.isEmpty()){
            this.head = this.last = nodo;
            nodo.setPrev(null);
        }else{
            this.head.setPrev(nodo);
            nodo.setNext(this.head);
            nodo.setPrev(null);
            this.head=nodo;
        }
        this.size++;
    }
    public Nodo deleteFirst(){
        if (this.head !=null){
            Nodo temp = this.head;
            this.head = this.head.next;
            this.head.setPrev(null);
            this.size--;
            return temp;
        } else{return null;}
    }
    public Nodo deleteLast(){
        if (this.head!=null){
            Nodo temp = this.last;
            this.last= this.last.getPrev();
            this.last.setNext(null);
            this.size--;
            return temp;
        }else{return null;}
    }
    public void displayList(){
        Nodo current = this.head;
        while (current!=null){
            System.out.println(current.getData());
            current = current.getNext();
        }
    }
    public Nodo find(Object x){
        Nodo current = this.head;
        while (current !=null){
            if (current.getData().equals(x)){
                return current;
            } else{current=current.getNext();}
        }
        return null;
    }
    public Nodo deleteValue(Object x){
        Nodo current = this.head;
        Nodo prev= this.head;
        while(current !=null){
            if (current.getData().equals(x)){
                if (current==this.head){
                    this.head=this.head.getNext();
                }else{prev.setNext(current.getNext());}
                return current;
            }else{
                prev = current;
                current=current.getNext();
            }
        }
        return null;
    }
    public Nodo deleteIndex(int x){
        Nodo current = this.head;
        Nodo prev = this.head;
        if (x==0){
            return this.deleteFirst();
        } else if (x>this.size-1) {
            return null;
        } else if (x==this.size-1){
            return this.deleteLast();
        } else{
            for (int i = 0; i<x; i++){
                prev=current;
                current=current.getNext();
            }
            current.prev.setNext(current.getNext());
            current.next.setPrev(current.getPrev());
            return current;
        }
    }
    public void insertLast(Object data){
        Nodo nodo = new Nodo(data);
        if (this.isEmpty()){
            this.head = this.last=nodo;
        }else{
            nodo.setPrev(this.last);
            this.last.setNext(nodo);
            this.last=nodo;
        }
        this.size++;
    }
    public void insertIndex(Object data,int x){
        Nodo current = this.head;
        Nodo prev = this.head;
        Nodo nodo = new Nodo(data);
        if (x==0){
            this.insetFirst(data);
        } else if (x>=this.size-1) {
            this.insertLast(data);
        } else {
            for (int i = 0; i < x; i++) {
                prev = current;
                current = current.getNext();
            }
            nodo.setNext(current.next);
            nodo.next.setPrev(nodo);
            nodo.setPrev(current);
            current.setNext(nodo);
        }
        this.size++;
    }
}
