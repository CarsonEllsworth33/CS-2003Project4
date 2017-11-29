// This code will compile with 1 warning - for code
// that uses compareTo method for T - see compareItems

package heaps;
import java.util.ArrayList;
import java.util.Comparator;

public class Heap<T> {
  private ArrayList<T> items;    // array of heap items
  private Comparator<? super T> comparator;

  public Heap() { 
    items = new ArrayList<T>();
  }  // end default constructor

  public Heap(Comparator<? super T> comparator) {
    items = new ArrayList<T>();
    this.comparator = comparator;
  }  // end default constructor
   
// heap operations:
  public boolean heapIsEmpty() {
  // Determines whether a heap is empty.
  // Precondition: None.
  // Postcondition: Returns true if the heap is empty;
  // otherwise returns false.
    return items.size()==0;
  } // end heapIsEmpty

  public void heapInsert(T newItem) 
         throws HeapException, ClassCastException {
  // Inserts an item into a heap.
  // Precondition: newItem is the item to be inserted.
  // Postcondition: If the heap was not full, newItem is
  // in its proper position; otherwise HeapException is
  // thrown.
    if (!items.add(newItem)) {
      // problem adding element to ArrayList item for heap
      throw new HeapException("HeapException: heapInsert failed");
    } else {
      // trickle new item up to its proper position
      int place = items.size()-1;
      int parent = (place - 1)/2;
      while ((parent >= 0) &&
             (compareItems(items.get(place), items.get(parent))) < 0) {
        // swap items[place] and items[parent]
        T temp = items.get(parent);
        items.set(parent, items.get(place));
        items.set(place, temp);
        
        place = parent;
        parent = (place - 1)/2;
      }  // end while
    } // end else
  } // end heapInsert

  public T heapDelete() {
  // Retrieves and deletes the item in the root of a heap.
  // This item has the largest search key in the heap.
  // Precondition: None.
  // Postcondition: If the heap is not empty, returns the
  // item in the root of the heap and then deletes it. However,
  // if the heap is empty, removal is impossible and the
  // method returns null.
    T rootItem = null;
    int loc;
    if (!heapIsEmpty()) {
      rootItem = items.get(0);
      loc = items.size()-1;
      // if we remove the item first, it may make the ArrayList items
      // empty, then set() won't work
      items.set(0, items.get(loc));
      items.remove(loc);
      heapRebuild(0);
    }  // end if
    return rootItem;
  } // end heapDelete

  protected void heapRebuild(int root) {
  // if the root is not a leaf and the root's search key 
  // is less than the larger of the search keys in the
  // root's children
    int child = 2 * root + 1;  // index of root's left 
                               // child, if any
    if ( child < items.size() ) {
      // root is not a leaf, so it has a left child at child
      int rightChild = child + 1;  // index of right child, 
                                   // if any

      // if root has a right child, find larger child
      if ((rightChild < items.size()) &&
          (compareItems(items.get(rightChild),items.get(child))) < 0) {
        child = rightChild;    // index of larger child
      } // end if

      // if the root's value is smaller than the
      // value in the larger child, swap values
      if (compareItems(items.get(root), items.get(child)) > 0) {
        T temp = items.get(root);
        items.set(root, items.get(child));
        items.set(child, temp);
        // transform the new subtree into a heap
        heapRebuild(child);
      }  // end if
    }  // end if
    // if root is a leaf, do nothing
  } // end heapRebuild
  
  private int compareItems(T item1, T item2) {
    if (comparator == null) {
    return ((Comparable <T>)item1).compareTo(item2);
    } else {
      return comparator.compare(item1, item2);
    } // end if
  } // end compare
} // end Heap
