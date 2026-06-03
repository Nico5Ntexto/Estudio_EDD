public class GrafoTabla {
    private int[][] tablaAd;

    GrafoTabla(int[][] tablaAd){
        this.tablaAd = tablaAd;
    }

    //--------MÉTODOS PRINCIPALES--------
    public boolean nuevaArista(int origen, int destino, int peso){
        if(!existen(origen, destino)) return false;

        tablaAd[origen][destino] = peso;
        return true;
    }

    public boolean existeCamino(int origen, int destino){
        
    }
    //--------MÉTODOS AUXILIARES---------
    private boolean existen(int i, int j){
        return i >= 0 &&
               i < tablaAd.length &&
               j >= 0 &&
               j < tablaAd[i].length;
    }

    private boolean recorrer(int origen, int destino){
        
    }
}