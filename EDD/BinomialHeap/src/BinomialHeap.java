public class BinomialHeap{
    private class NodoBH{
        private NodoBH padre, hijo, hermano;
        private int clave;
        private short grado;

        NodoBH(int clave){
            padre = hijo = hermano = null;
            grado = 0;
            this.clave = clave;
        }

        void binomialLink(NodoBH link){
            this.padre = link;
            this.hermano = link.hijo;
            link.hijo = this;
            link.grado++;
        }
    }
    private NodoBH cabeza;
    BinomialHeap(){cabeza = null;}

    //-----------MÉTODOS PÚBLICOS--------------
    public void insertar(int valor){
        BinomialHeap nuevo = new BinomialHeap();
        nuevo.cabeza = new NodoBH(valor);
        cabeza = unirBH(nuevo);
    }

    public int obtenerMinimo(){
        if(isEmpty()) return Integer.MIN_VALUE;
        NodoBH actual = cabeza;
        int min = actual.clave;

        while(actual != null){
            if(actual.clave < min){
                min = actual.clave;
            }
            actual = actual.hermano;
        }
        return min;
    }
    


    public int extractMin(){
        if(isEmpty()) return Integer.MIN_VALUE;

        NodoBH minimo = cabeza;
        NodoBH previoMin = null;
        NodoBH actual = cabeza;
        NodoBH previo = null;

        while(actual != null){
            if(minimo.clave > actual.clave){
                previoMin = minimo;
                minimo = actual;
            }
            previo = actual;
            actual = actual.hermano;
        }

        if(previoMin == null) cabeza = minimo.hermano;
        else previoMin.hermano = minimo.hermano;

        NodoBH hijoActual = minimo.hijo;
        NodoBH nuevaCabezaHijos = null;

        //Algoritmo para invertir lista
        while(hijoActual != null){
            NodoBH siguiente = hijoActual.hermano;
            hijoActual.hermano = nuevaCabezaHijos;
            hijoActual.padre = null;
            nuevaCabezaHijos = hijoActual;
            hijoActual = siguiente;
        }

        BinomialHeap heapHijos = new BinomialHeap();
        heapHijos.cabeza = nuevaCabezaHijos;
        cabeza = unirBH(heapHijos);

        return minimo.clave;
    }

    public boolean buscar(int clave){
        return buscarNodo(cabeza, clave) != null;
    }

    //-----------MÉTODOS PRIVADOS--------------
    private boolean isEmpty(){
        return cabeza == null;
    }

    private int size(){
        int cantidadNodos = 0;
        NodoBH actual = cabeza;

        while(actual != null){
            cantidadNodos += (1 << actual.grado);
            actual = actual.hermano;
        }
        return cantidadNodos;
    }

    private NodoBH buscarNodo(NodoBH actual, int buscado){
        while(actual != null){
            if(actual.clave == buscado) return actual;
            if(actual.clave < buscado && actual.hijo != null){
                NodoBH resultado = buscarNodo(actual.hijo, buscado);
                if(resultado != null) return resultado;
            }
            actual = actual.hermano;
        }
        return null;
    }

    private void decreaseKey(NodoBH nodo, int nuevoValor){
        if(nuevoValor >= nodo.clave) return;

        nodo.clave = nuevoValor;

        NodoBH actual = nodo;
        NodoBH padre = nodo.padre;

        while(padre != null && actual.clave < padre.clave){

        }
    }

    private void swapKeys(NodoBH a, NodoBH b){
        int temp = a.clave;
        a.clave = b.clave;
        b.clave = temp;
    }

    private NodoBH unirBH(BinomialHeap aUnir){
        BinomialHeap resultado = fusionarBH(this, aUnir);
        return consolidarNodos(resultado.cabeza);
    }
//a
    private BinomialHeap fusionarBH(BinomialHeap bH1, BinomialHeap bH2){
        BinomialHeap BHResultante = new BinomialHeap();

        NodoBH colaResultante = null;
        NodoBH rec1 = bH1.cabeza;
        NodoBH rec2 = bH2.cabeza;

        if(rec1 == null) return bH2;
        if(rec2 == null) return bH1;

        if(rec1.grado <= rec2.grado){
            BHResultante.cabeza = rec1;
            rec1 = rec1.hermano;
        } else {
            BHResultante.cabeza = rec2;
            rec2 = rec2.hermano;
        }

        colaResultante = BHResultante.cabeza;

        while(rec1 != null && rec2 != null){
            if(rec1.grado <= rec2.grado){
                colaResultante.hermano = rec1;
                rec1 = rec1.hermano;
            } else {
                colaResultante.hermano = rec2;
                rec2 = rec2.hermano;
            }

            colaResultante = colaResultante.hermano;
        }

        colaResultante.hermano = (rec1 != null ? rec1 : rec2);

        return BHResultante;
    }

    private NodoBH consolidarNodos(NodoBH nodo){
        if(nodo == null) return null;

        NodoBH nuevaCabeza = nodo;
    
        NodoBH previo = null;
        NodoBH actual = nodo;
        NodoBH siguiente = nodo.hermano;

        while(siguiente != null){
            if(
                actual.grado != siguiente.grado ||
                (
                    siguiente.hermano != null &&
                    siguiente.hermano.grado == actual.grado
                )
            ){
                previo = actual;
                actual = siguiente;
            }
            else if(actual.clave <= siguiente.clave){
                actual.hermano = siguiente.hermano;
                siguiente.binomialLink(actual);
            }
            else {
                if(previo == null){
                    nuevaCabeza = siguiente;
                } else previo.hermano = siguiente;

                actual.binomialLink(siguiente);
                actual = siguiente;
            }
            siguiente = actual.hermano;
        }

        return nuevaCabeza;
    }
}