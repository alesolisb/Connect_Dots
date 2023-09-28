import java.io.Serializable;

public class Nodo <T> implements Serializable{
    private T data;
    protected Nodo<T> next;

    protected Nodo<T> prev;

    public Nodo(T data){
        this.next = null;
        this.prev =null;
        this.data=data;
    }

    public Nodo() {

    }
    public T getData() {
        return data;
    }

    public void setData(T data) {this.data = data;}

    public Nodo<T> getNext() {return next;}

    public void setNext(Nodo<T> nodo) {this.next = nodo;}

    public Nodo<T> getPrev() {return prev;}

    public void setPrev(Nodo<T> prev) {this.prev = prev;}
}
