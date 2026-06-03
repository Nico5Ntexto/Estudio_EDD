class MinHeap{
    private int[] heap;
    private int size;
    private int capacity;

    MinHeap(int[] heap){
        this.heap = heap;
        size = 0;
        capacity = heap.length;
    }

    private int padre(int i){return (i - 1)/2;}
    private int izquierda(int i){return 2 * i + 1;}
    private int derecha(int i){return 2 * i + 2;}

    
}