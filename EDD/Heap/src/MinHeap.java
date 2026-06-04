
import java.util.NoSuchElementException;

class MinHeap{
    private int[] heap;
    private int size;
    private int capacity;

    MinHeap(int[] heap){
        this.heap = heap;
        size = 0;
        capacity = heap.length;
    }
    //----------------MÉTODOS PÚBLICOS----------------
    public void insert(int value){
        if(isFull()) duplicateCapacity();

        heap[size] = value;
        heapifyUp(size++);
    }

    public int find(int value){
        for(int i = 0; i < size; i++){
            if(heap[i] == value) return i;
        }
        return -1;
    }

    public boolean delete(int value){
        int i = find(value);
        if(i == -1) return false;

        if(i == 0){
            heap[i] = heap[--size];
            heapifyDown(i);
            return true;
        }

        int old = heap[i];
        heap[i] = heap[--size];

        if(heap[i] > old) heapifyDown(i);
        else heapifyUp(i);

        return true;
    }

    public int extractMin() throws  NoSuchElementException{
        if(isEmpty()) throw new NoSuchElementException("No hay elementos");

        int min = heap[0];
        delete(heap[0]);
        return min;
    }

    public void increaseKey(int i, int valor)throws IllegalArgumentException{
        if(i < 0) throw new IllegalArgumentException("Error: índice negativo");
        if(i >= size) throw new IllegalArgumentException("Error: índice fuera de rango");
        if(valor <= heap[i]) throw new IllegalArgumentException("Error: El nuevo valor debe ser mayor que el actual");

        heap[i] = valor;
        heapifyUp(i);
    }

    public void decreaseKey(int i, int valor)throws IllegalArgumentException{
        if(i < 0) throw new IllegalArgumentException("Error: índice negativo");
        if(i >= size) throw new IllegalArgumentException("Error: índice fuera de rango");
        if(valor >= heap[i]) throw new IllegalArgumentException("Error: El nuevo valor debe ser menor que el actual");

        heap[i] = valor;
        heapifyDown(i);
    }

    @Override
    public String toString(){
        StringBuilder cadena = new StringBuilder();
        cadena.append('[');
        for(int i = 0; i < size; i++){
            cadena.append(heap[i]);
            if(i != size - 1) cadena.append(',');
        }
        cadena.append(']');
        return cadena.toString();
    }
    //----------------MÉTODOS PRIVADOS----------------
    private int padre(int i){return (i - 1)/2;}
    private int izquierda(int i){return 2 * i + 1;}
    private int derecha(int i){return 2 * i + 2;}

    private void heapifyUp(int i){
        while(i > 0 && heap[i] < heap[padre(i)]){
            swap(i, padre(i));
            i = padre(i);
        }
    }

    private void heapifyDown(int i){
        int masChico = i;

        if(izquierda(i) < size && heap[i] > heap[izquierda(i)]){
            masChico = izquierda(i);
        }

        if(derecha(i) < size && heap[masChico] > heap[derecha(i)]){
            masChico = derecha(i);
        }

        if(masChico != i){
            swap(masChico, i);
            heapifyDown(masChico);
        }
    }

    private void swap(int i, int j){
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
    private boolean isFull(){return size == capacity;}
    private boolean isEmpty(){return size == 0;}
    private void duplicateCapacity(){
        capacity *= 2;
        heap = java.util.Arrays.copyOf(heap, capacity);
    }
    
}
