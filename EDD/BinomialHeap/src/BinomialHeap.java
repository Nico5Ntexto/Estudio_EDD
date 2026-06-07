
public class BinomialHeap {

    private class NodoCB {

        NodoCB padre;
        NodoCB hijo;
        NodoCB hermano;

        int clave;
        short grado;

        public NodoCB(
                NodoCB padre,
                NodoCB hijo,
                NodoCB hermano,
                int clave,
                short grado
        ) {
            this.padre = padre;
            this.hijo = hijo;
            this.hermano = hermano;
            this.clave = clave;
            this.grado = grado;
        }

        public void binomialLink(NodoCB z) {
            padre = z; //se vuelve el nuevo hijo de Z y Z aumenta 1 grado.
            hermano = z.hijo;
            z.hijo = this;
            z.grado++;
            /*
                     _-->[z]++
                    |
                    |
                    v       
                [this] --->[hermano]
             */
        }
    }

    private NodoCB cabeza;

    public BinomialHeap() {
        cabeza = null;
    }

    //{[head], [root2], [root3]}
    public int BHMinimum() {
        NodoCB actual = cabeza;
        int min = actual.clave;

        //Recorre las raices de todos los árboles.
        while (actual != null) {
            if (actual.clave < min) {
                min = actual.clave;
            }
            actual = actual.hermano;
        }

        return min;
    }

    public void insert(int clave) {
        BinomialHeap nuevo = new BinomialHeap();
        nuevo.cabeza = new NodoCB(null, null, null, clave, (short) 0);
        cabeza = BHUnion(nuevo);
    }

    //-------MÉTODOS AUXILIARES-------
    private NodoCB BHUnion(BinomialHeap otro) {
        BinomialHeap resultado = BHMerge(this, otro);
        if (resultado.cabeza == null) {
            return resultado.cabeza;
        }

        NodoCB previo = null;
        NodoCB actual = resultado.cabeza; //{[cabeza]}
        NodoCB siguiente = actual.hermano;//{[cabeza] -> [hermano]}

        while (siguiente != null) {
            //Cubre si el siguiente árbol es de distinto grado (esta bien) o si
            //está en cadena de 3 árboles del mismo grado (toca esperar a la siguiente iteración).
            if (actual.grado != siguiente.grado
                    || (siguiente.hermano != null
                    && siguiente.hermano.grado == actual.grado)) {
                previo = actual;
                actual = siguiente;
            } //si no es distinto el grado pero tampoco es cadena de 3, se fusionan,
            //caso si el actual es menor o igual que su hermano (fusión a la izquierda)
            else if (actual.clave <= siguiente.clave) {
                actual.hermano = siguiente.hermano; //se realiza el "salto"
                siguiente.binomialLink(actual); //se enlaza haciendo que el hermano ahora pase a ser el hijo del actual.
            } //Caso en donde el nodo actual es mayor que su hermano (fusión a la derecha)
            else {
                //se "elimina" al actual
                if (previo == null) {
                    resultado.cabeza = siguiente; //si no habia previo, el siguiente toma el nuevo puesto
                 }else {
                    previo.hermano = siguiente; //en caso de haber, entonces el previo debe "saltar" al actual
                }
                actual.binomialLink(siguiente); //actual debe convertirse en el hijo de siguiente
                actual = siguiente; //ahora actual es siguiente
            }

            siguiente = actual.hermano;
        }
        return resultado.cabeza;
    }

    private BinomialHeap BHMerge(BinomialHeap h1, BinomialHeap h2) {
        BinomialHeap resultado = new BinomialHeap();

        NodoCB actual1 = h1.cabeza;
        NodoCB actual2 = h2.cabeza;
        NodoCB colaResultado = null; // Puntero para ir construyendo la nueva lista

        // 1. Casos base: si una de las colas está vacía, retornamos la otra
        if (actual1 == null) {
            return h2;
        }
        if (actual2 == null) {
            return h1;
        }

        // 2. Determinar cuál será la nueva 'cabeza' (el nodo con menor grado)
        if (actual1.grado <= actual2.grado) {
            resultado.cabeza = actual1;
            actual1 = actual1.hermano;
        } else {
            resultado.cabeza = actual2;
            actual2 = actual2.hermano;
        }

        colaResultado = resultado.cabeza;

        // 3. Recorrer ambas listas y entrelazarlas ordenando por grado
        while (actual1 != null && actual2 != null) {
            if (actual1.grado <= actual2.grado) {
                colaResultado.hermano = actual1;
                actual1 = actual1.hermano;
            } else {
                colaResultado.hermano = actual2;
                actual2 = actual2.hermano;
            }
            colaResultado = colaResultado.hermano; // Avanzamos el puntero final
        }

        // 4. Si alguna lista se acabó primero, simplemente enganchamos lo que sobra de la otra
        if (actual1 != null) {
            colaResultado.hermano = actual1;
        } else {
            colaResultado.hermano = actual2;
        }

        return resultado;
    }
}
