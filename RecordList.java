
/**
 * RecordList
 * ----------
 * RecordList is essentially an ArrayList of Records with a few sorting methods and display methods added.
 * 
 *
 * @author 
 */
public class RecordList extends ArrayList<Record>
{
    

    /**
     * Constructor for objects of class RecordList
     */
    public RecordList()
    {
        super();
    }

    
    /**
     * sortBy
     * ------
     * This sorts the Records by featureToSortBy.
     * This uses the Insertion Sort algorithm, since that one is stable
     * PRE: featureToSortBy describes one of the instance variables of at least one of the Records in the database,
     *      such that it is taken into account by that Record's compareToBy method.
     *      (If not, correct sorting is not guaranteed, but the program should not crash.)
     * POST: the records in the database are sorted in ascending order by the sorting criteria specified.
     */
    public void sortBy(String featureToSortBy){
        boolean contains = true;
        for (int j = 1; j < countNodes() ; j++) {
            Record current = this.get(j);
            int i = j -1 ;
            //System.out.println("Sort" + j + "passed");
            while ((i > -1) && (this.get(i).compareToBy(current, featureToSortBy) > 0)) {
                //System.out.println("Inner sort" + i + "passed");
                this.swap(i, i+1);
                i--;
            }
            this.overwrite(i+1, current);
        }
     }
    
     /*
      * // NOTE FROM KAREN:
      * 
      * I got this Insertion Sort code from 
      * https://stackabuse.com/insertion-sort-in-java/
      * (I'll use it as a template for making my own insertSort -- sortBy -- that works with my own ArrayList/RecordList)
      * 
      * public static void insertionSort(int array[]) {
      *     for (int j = 1; j < array.length; j++) {
      *         int current = array[j];
      *         int i = j-1;
      *         while ((i > -1) && (array[i] > current)) {
      *             array[i+1] = array[i];
      *             i--;
      *         }
      *         array[i+1] = current;
      *     }
      * }
      */
    
    
    
    /**
     * swap
     * ----
     * If you're gonna sort an ArrayList, 
     * you might need a way to swap the elements of our ArrayList by index.
     * PRE: indices refer to elements in the arrayList.
     * POST: elements at indices a and b will have swapped places (the memory locations)
     */
    private void swap(int a, int b){
         if (isEmpty() || super.get(a) == null || super.get(b) == null) {
             
            }
         else { 
             Record pl1 = (Record) super.get(a);
             Record pl2 = (Record) super.get(b);
             
             super.remove(a);
             super.insert(pl2, a);
             
             super.remove(b);
             super.insert(pl1, b);
         }
         
    }
    
    /**
     * overwrite
     * ---------
     * This method overwrites the elements at index i with Record r,
     * so long as there is an element at index i.
     * PRE: there's an element at i
     * POST: overwrites element at i with r.
     */
    private void overwrite(int i,Record r){
         if (isEmpty() || super.get(i) == null) {
            }
         else {
             Record pl1 = (Record) super.get(i);
             
             super.remove(i);
             super.insert(r, i);
         }
    }
    
    /**
     * displayAllRecords
     * -----------------
     * This prints to the screen a nice representation of all the records in their current order
     * Each record is on its own line.
     * PRE: none
     * POST: none.
     */
    public void displayAllRecords(){
         // [OH NO! IT GOT ERASED!]
         System.out.println(super.toString());
    }
    
    /**
     * displayRecordsOfRecordType()
     * ----------------------------
     * This prints to the screen a nice representation of all the records in the RecdordList 
     * whose recordType matches the parameter recType.  It displays them in their current order.
     * Each record is on its own line.
     * PRE: r must be a recordType of at least some records, or else it will display no records.
     * POST: none
     */
    public void displayRecordsOfRecordType(String recType){
        String r = "";
        Node currentNode = this.head;
        while (currentNode != null) {
            if (currentNode.getItem().getRecordType().equalsIgnoreCase(recType)) {
                r+= currentNode.getItem() + " \n";
            }
            
            currentNode = currentNode.getLink();
            
        }
        
        System.out.println(r);
    }
    /**
     * This returns the number of items in the arraylist. It just calls the parent method of countnodes.
     */
    public int size() {
        return this.countNodes();
    }
    /**
     * This method is a getter for recordlist, it returns an item at the given index.
     */
    public Record get(int i ) {
        
        Record gettie = super.get(i);
        return gettie;
    }

}
