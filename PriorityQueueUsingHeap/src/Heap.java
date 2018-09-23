import java.lang.reflect.Array;
    /*
        Description :
        Implementarea efectiva a heap-ului folosit un Array generic si un contor pentru size
        Parametrul generic trebuie sa extinda comparable ( ca sa putem compara prioritatea obiectelor din array )
     */
public class Heap<T extends Comparable> implements HeapInterface {
    private T[] heap;
    private int size;

    @SuppressWarnings("unchecked")
    public Heap(Class<T> clasa,int capacity) {
        heap = (T[]) Array.newInstance(clasa,capacity); // pentru a instantia un array de parametrii generici trebuie sa stim metadata continutului
                                                        // vom folosi Array.newInstance(tipParametruGeneric,capacitateArray)
    }

        /*
            Description :
            Heap gol -> adaugam elementul / crestem size-ul
            Altfel -> adaugam elementul la final / crestem size-ul
                   -> daca parintele e mai mare -> swap
         */
    @Override
    public void insert(Object element) {
        if(size==0){
            heap[size] = (T)element;
            size++;
        }else{
            heap[size] = (T)element;
            int i = size;
            size++;
            while(i!=0&&heap[i/2].compareTo(heap[i])>0){
                swap(i/2,i);
                i = i/2;
            }
        }
    }
        /*
            Description :
            Basic heapify . Folosim pentru a verifica proprietatea de heap
         */
    @Override
    public void heapify(int i){
        int smallest = i;
        int right = 2*i+1;
        int left = 2*i;
        int size = this.size;

        if(left<size&&heap[left].compareTo(heap[i])<0){
            smallest = left;
        }
        if(right<size&&heap[right].compareTo(heap[smallest])<0){
            smallest = right;
        }
        if(i!=smallest){
            swap(i,smallest);
            heapify(smallest);
        }
    }
        /*
            Description :
            Extragem varful heapului si refacem proprietatea de heap .
         */
    @Override
    public T extractMin() {
        T rezultat = null;
        if(size==0){
            throw new NullPointerException("heap e null");
        }else if(size==1){
            rezultat = heap[0];
            size--;
        }else{
            rezultat = heap[0];
            heap[0] = heap[size-1];
            size--;
            heapify(0);
        }
        return rezultat;
    }

    private void swap(int index1,int index2){
        T auxiliar = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = auxiliar;
    }

    public T[] getHeap(){
        return this.heap;
    }

    public int getSize() {
        return size;
    }
}
