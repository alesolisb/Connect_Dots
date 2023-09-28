import java.util.ArrayList;

public class Lista<T> extends Nodo<T>{
    protected Nodo<T> head;
    protected Nodo<T> last;
    protected int size;

    public Lista(){
        this.head = null;
        this.last=null;
        this.size= 0;
    }

    public boolean isEmpty(){return this.head==null;}
    public int getSize(){return this.size;}
    public void insertFirst(T data){
        Nodo<T> nodo = new Nodo<>(data);
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
    public void insertLast(T data){
        Nodo<T> nodo = new Nodo<>(data);
        if (this.isEmpty()){
            this.head = this.last=nodo;
        }else{
            nodo.setPrev(this.last);
            this.last.setNext(nodo);
            this.last=nodo;
        }
        this.size++;
    }
    public void insertIndex(T data,int x){
        Nodo<T> current = this.head;
        Nodo<T> prev = this.head;
        Nodo<T> nodo = new Nodo<>(data);
        if (x==0){
            this.insertFirst(data);
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
    public Nodo<T> deleteFirst(){
        if (this.head !=null){
            Nodo<T> temp = this.head;
            this.head = this.head.next;
            this.head.setPrev(null);
            this.size--;
            return temp;
        } else{return null;}
    }
    public Nodo<T> deleteLast(){
        if (this.head!=null){
            Nodo<T> temp = this.last;
            this.last= this.last.getPrev();
            this.last.setNext(null);
            this.size--;
            return temp;
        }else{return null;}
    }


    public Nodo<T> deleteValue(Object x){
        Nodo<T> current = this.head;
        Nodo<T> prev= this.head;
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
    public Nodo<T> deleteIndex(int x){
        Nodo<T> current = this.head;
        Nodo<T> prev = this.head;
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

    public T getValue(int index){
        Nodo<T> current = this.head;
        for (int i =0; i<this.size; i++){
            if (i==index){
                return current.getData();
            }else{
                current=current.getNext();
            }
        }
        return null;
    }
    public Nodo<T> getNodo(int index){
        Nodo<T> current = this.head;
        for (int i =0; i<this.size; i++){
            if (i==index){
                return current;
            }else{
                current=current.getNext();
            }
        }
        return null;

    }
    public Nodo<T> getHead(){
        return this.head;
    }

    public int getIndex(Object value) {
        Nodo<T> current = this.head;
        for (int i =0; i<this.size; i++){
            if (current.getData().equals(value)){
                return i;
            }else{
                current=current.getNext();
            }
        }
        return -1;
    }

    public Nodo<T> getLast() {
        return this.last;
    }

    public Nodo<T> find(Object x){
        Nodo<T> current = this.head;
        while (current !=null){
            if (current.getData().equals(x)){
                return current;
            } else{current=current.getNext();}
        }
        return null;
    }
    public void displayList(){
        Nodo<T> current = this.head;
        while (current!=null){
            System.out.println(current.getData());
            current = current.getNext();
        }
    }
//    ArrayList<String> a = new ArrayList<>();
}
