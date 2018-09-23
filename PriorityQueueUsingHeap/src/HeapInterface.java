public interface HeapInterface<T> {
    /*
        Interfata descrie metodele de baza ce trebuie implementata de un Heap Data Structure
     */
   void insert(T element);
   void heapify(int i);
   T extractMin();

}
