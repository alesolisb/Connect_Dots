import java.io.Serializable;

/**
 * Clase Nodo que representa un nodo en una estructura de datos enlazada.
 * Puede contener datos de tipo genérico y referencias a nodos previos y siguientes.
 * @author Alejandro Solis Bolanos
 * @author Fabian Gutierrez Jimenez
 * @author Adrian Muñoz Alvarado
 * @param <T> El tipo de datos que se almacena en el nodo.
 */


public class Nodo <T> implements Serializable{
    private T data;
    protected Nodo<T> next;
    protected Nodo<T> prev;

    /**
     * Constructor de la clase Nodo que inicializa un nodo con datos.
     * @param data Los datos que se almacenarán en el nodo.
     */

    public Nodo(T data){
        this.next = null;
        this.prev =null;
        this.data=data;
    }

    /**
     * Constructor de la clase Nodo sin argumentos.
     */

    public Nodo() {

    }

    /**
     * Obtiene los datos almacenados en el nodo.
     * @return Los datos almacenados en el nodo.
     */

    public T getData() {
        return data;
    }

    /**
     * Establece los datos almacenados en el nodo.
     * @param data Los nuevos datos a almacenar en el nodo.
     */

    public void setData(T data) {this.data = data;}

    /**
     * Obtiene una referencia al siguiente nodo en la lista enlazada.
     * @return El siguiente nodo en la lista enlazada.
     */

    public Nodo<T> getNext() {return next;}

    /**
     * Establece una referencia al siguiente nodo en la lista enlazada.
     * @param nodo El siguiente nodo en la lista enlazada.
     */

    public void setNext(Nodo<T> nodo) {this.next = nodo;}

    /**
     * Obtiene una referencia al nodo previo en la lista enlazada.
     * @return El nodo previo en la lista enlazada.
     */

    public Nodo<T> getPrev() {return prev;}

    /**
     * Establece una referencia al nodo previo en la lista enlazada.
     * @param prev El nodo previo en la lista enlazada.
     */

    public void setPrev(Nodo<T> prev) {this.prev = prev;}
}
