public class Nodo {
    private Object data;
    protected Nodo next;

    protected Nodo prev;

    public Nodo(Object data){
        this.next = null;
        this.prev =null;
        this.data=data;
    }
    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Nodo getNext() {
        return next;
    }

    public void setNext(Nodo nodo) {
        this.next = nodo;
    }

    public Nodo getPrev() {
        return prev;
    }

    public void setPrev(Nodo prev) {
        this.prev = prev;
    }
}
