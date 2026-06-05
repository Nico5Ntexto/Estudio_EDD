public class maxheapNB{
    int [] A;
    int heapSize;
    maxheapNB(int[] A){
        this.A=A;
        heapSize=0;
    }
    public void increaseKey(int i, int v) throws Exception{
        if(i>=A.length || i<0){
            throw new Exception ("Indice no valido");
        }
        heapSize++;
        A[heapSize-1]=Integer.MIN_VALUE;
        A[heapSize-1]=A[i];
        if(A[i]>A[padre(i)]){
            swap(i,padre(i));
        }
    }
    public void swap(int i, int j){
        int temp=A[i];
        A[i]=A[j];
        A[j]=temp;
    }
    public int padre(int i){
        return (i-1)/2;
    }
    public void imprimir(){
        for (int i=0;i<A.length;i++){
            System.out.print(A[i]);
        }
        System.out.println();
    }

    
    public static void main(String[] args){
        int[] A={1,3,6,4,2,3,1,5};
        maxheapNB h1=new maxheapNB(A);
    }
}