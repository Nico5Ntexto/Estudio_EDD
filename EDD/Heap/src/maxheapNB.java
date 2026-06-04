public class maxheapNB {
    int[] A;
    int heapSize;

    // Cambiamos el constructor para recibir una capacidad inicial, no un arreglo lleno.
    public maxheapNB(int[] A) {
        this.A = A;
        this.heapSize = 0;
    }
//probando modificacion
    public int padre(int i) {
        return (i - 1) / 2;
    }

    public void swap(int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    public void insertar(int v) {
        if (heapSize == A.length) {
            //[1,2,3,v,x1,x2,x3] |size|
            //size == A.length
            A = java.util.Arrays.copyOf(A, A.length * 2);
        }
        //[1,2,3,size,0,0,0]
        int i = heapSize;
        heapSize++;
        //[1,2,3,i,size,0,0]
        A[i] = Integer.MIN_VALUE; //0x8000_0000
        //[1,2,3,0x8000_0000,size,0,0]
        increaseKey(i, v);
    }

    public boolean increaseKey(int i, int v) {
        if (A[i] > v) {
            return false;
        } else {
            A[i] = v;
            //[1,2,3,v,size,0,0]
            // CORRECCIÓN: Comparamos el valor de i (A[i]) con el valor del padre (A[padre(i)])
            while (i > 0 && A[i] > A[padre(i)]) {
                swap(i, padre(i));
                i = padre(i);
            }
            return true;
        }
    }

    public void imprimir() {
        // CORRECCIÓN: Imprimimos hasta heapSize, no hasta la longitud máxima del arreglo
        for (int i = 0; i < heapSize; i++) {
            System.out.print(A[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Creamos un heap con capacidad inicial de 15
        
        // Arreglo de prueba
        int[] elementos = {1, 4, 52, 32, 5, 3, 4, 6, 76, 64, 23, 43, 1};
        
        maxheapNB h1 = new maxheapNB(elementos);
        
        // Insertamos los elementos correctamente uno por uno para construir el Max Heap
        System.out.println("Insertando elementos iniciales...");
        for (int i = 0; i < elementos.length; i++) {
            h1.insertar(elementos[i]);
        }
        h1.imprimir();
        
        System.out.println("Insertando el número 100...");
        h1.insertar(100);
        h1.imprimir();
    }
}
