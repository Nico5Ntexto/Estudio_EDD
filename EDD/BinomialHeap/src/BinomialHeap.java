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

}