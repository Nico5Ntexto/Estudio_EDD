public class HashingDoble{
    private class Nodo{
        int clave;
        Nodo siguiente;

    }

    int[] tabla;
    int m;

    public HashingDoble(int tamanio) {
        tabla = new int[tamanio];
        java.util.Arrays.fill(tabla, Integer.MIN_VALUE);
        m = tamanio;
    }

    //-------MÉTODOS PÚBLICOS--------
    public int insertar(int k){
        int i = 0;
        int j = 0;

        do { 
            j = h(k, i);

            if(tabla[j] == Integer.MIN_VALUE){
                tabla[j] = k;
                return j;
            }
            i++;
        } while (i < m);
        return -1;
    }

    public boolean delete(int k){
        int resultado = buscar(k);
        if(resultado == -1) return false;

        tabla[resultado] = Integer.MIN_VALUE;
        return true;
    }

    public int buscar(int k){
        int i = 0;
        int j = 0;
        
        do { 
            j = h(k, i);
            if(tabla[j] != Integer.MIN_VALUE && tabla[j] == k){
                return j;
            }

            i++;
        } while (i < m && tabla[j] != Integer.MAX_VALUE);
        return -1;
    }

    //-------MÉTODOS PRIVADOS--------
    private int h1(int k){return k % m;}
    private int h2(int k){return 1 + (k % (m-1));}
    private int h(int k, int i){
        return (h1(k) + i * h2(k)) % m;
    }
}