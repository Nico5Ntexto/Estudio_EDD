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

    public boolean find(int value){
        for(int n: heap) if(n == value) return true;
        return false;
    }

    public boolean delete(int value){
        return true;
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
