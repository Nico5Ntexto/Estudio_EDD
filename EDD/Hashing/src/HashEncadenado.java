public class HashEncadenado {
    private class Nodo{
        int valor;
        Nodo next;

        Nodo(int valor, Nodo next){
            this.valor = valor;
            this.next = next;
        }
    }

    private Nodo[] tabla;
    private int size;

    public HashEncadenado(int size){
        tabla = new Nodo[size];
        this.size = size;
    }

    //----------MÉTODOS PÚBLICOS---------
    public void insert(int valor){
        int i = h(valor);

        Nodo nuevo = new Nodo(valor, null);

        if(tabla[i] == null){
            tabla[i] = nuevo;
        }else{
            nuevo.next = tabla[i];
            tabla[i] = nuevo;
        }
    }

    public boolean buscar(int valor){
        int i = h(valor);

        Nodo current = tabla[i];

        while(current != null && current.valor != valor){
            current = current.next;
        }

        if(current == null) return false;
        else return true;
    }

public boolean eliminar2(int clave) {
        int indice = funcionHash(clave);
        Nodo actual = tabla[indice];
        Nodo previo = null;
        
        while (actual != null) {
            if (actual.clave == clave) {
                // Si es el primer elemento de la lista (la cabeza)
                if (previo == null) {
                    tabla[indice] = actual.siguiente;
                } 
                // Si está en el medio o al final de la lista
                else {
                    previo.siguiente = actual.siguiente;
                }
                return true; // Eliminado con éxito
            }
            
            // Avanzamos los punteros para seguir iterando
            previo = actual;
            actual = actual.siguiente;
        }
        
        return false; // No se encontró el objeto
    }


    public boolean eliminar(int valor){
        int i = h(valor);

        Nodo prev = null;
        Nodo current = tabla[i];

        while(current != null && current.valor != valor){
            prev = current;
            current = current.next;
        }

        if(current == null) return false;

        else {
            if(prev != null){
                prev.next = current.next;
                return true;
            }

            tabla[i] = current.next;
            return true;
        }
    }
    //----------MÉTODOS AUXILIARES-------
    private int h(int k){
        return k % size;
        }
}
