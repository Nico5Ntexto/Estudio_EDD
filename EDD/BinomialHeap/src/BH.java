class BH{
    private class NodoBH{
        NodoBH padre;
        NodoBH hijo;
        NodoBH hermano;

        int valor;
        int grado;

        NodoBH(NodoBH padre, NodoBH hijo, 
            NodoBH hermano, int valor, int grado){
                this.padre = padre;
                this.hijo = hijo;
                this.hermano = hermano;
                this.valor = valor;
                this.grado = 0;
        }

        public void binomialLink(NodoBH root){
            this.padre = root;
            this.hermano = root.hijo;
            root.hijo = this;
        }
    }

    private NodoBH cabeza;

    public BH(){cabeza = null;}

    public int BHMinimum(){
        NodoBH actual = cabeza;
        int minimo = cabeza.valor;

        while(actual != null){
            if(actual.valor < minimo) minimo = actual.valor;
            actual = actual.hermano;
        }

        return minimo;
    }

    //------------MÉTODOS PRIVADOS--------------
    private NodoBH BHUnion(BH aUnir){

    }

    private BH BHMerge(BH h1, BH h2){
        BH resultado = new BH();

        NodoBH colaResultante = null;
        NodoBH recorrido1 = h1.cabeza;
        NodoBH recorrido2 = h2.cabeza;

        if(recorrido1 == null) return h2;
        if(recorrido2 == null) return h1;

        if(recorrido1.grado <= recorrido2.grado){
            resultado.cabeza = recorrido1;
            recorrido1 = recorrido1.hermano;
        } else {
            resultado.cabeza = recorrido2;
            recorrido2 = recorrido2.hermano;
        }

        colaResultante = resultado.cabeza;

        while(recorrido1 != null && recorrido2 != null){
            if(recorrido1.grado <= recorrido2.grado){
                colaResultante.hermano = recorrido1;
                recorrido1 = recorrido1.hermano;
            } else {
                colaResultante.hermano = recorrido2;
                recorrido2 = recorrido2.hermano;
            }

            colaResultante = colaResultante.hermano;
        }

        recorrido1 == null ? 
        colaResultante.hermano = recorrido2 : 
        colaResultante.hermano = recorrido1;

        return resultado;
    }
}