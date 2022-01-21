
/**
 * This class holds a class inside, the public node class. It contains getter and setter methods,
 * and methods for traversal and manipulation of the arraylist, including a removal and an insert
 * method. It implements a singly linked list.
 */
public class ArrayList<T>
{
    Node head = null;
    
    /**
     * the private class definition for the node.
     */
    public class Node {
        private T item;
        private Node link;
        
        public Node() {
            item = null;
            link = null;
        }
        //constructor which sets the new item and the linkvalue.
        public Node(T newItem, Node linkValue) {
            item = newItem;
            link = linkValue;
        }
        /**
         * returns the value contained in the node
         */
        public T getItem() {
            return this.item;
            
        }
        /**
         * returns the address of the node's link
         */
        public Node getLink() {
            return this.link;
        }
        /**
         * sets the value of the node container
         */
        public void setItem(T newItem) {
            this.item = newItem;
        }
        /**
         * sets the address of the node's link.
         */
        public void setLink(Node newNode) {
            this.link = newNode;
        }
    }
    /**
     * This simple method is for use within this class only. It returns the address
     * of the head, which becomes useful for comparing to quack objects.
     */
    private Node getHead() {
        return this.head;
    }
    /**
     * The protected insert takes an object and an index value as parameters,
     * and adds the object to the index in the list. It has different cases, depending
     * on whether the list is empty, the index is higher than the number of actual things in
     * the list, and if the index is 0 (adding to the beginning).
     */
    protected void insert(T newData, int index) {
        if (head == null) {
            head = new Node(newData, null);
        }
        else if (head.getLink() == null && index != 0) {
            head.setLink(new Node(newData, null));
        }
        
        else {
            int count = this.countNodes();
            if (index > count +1) {
                System.out.println("You are trying to add an object outside of the bounds of the list. Item not added.");
            }
            else if (index == 0) {
                Node currentNode = this.head;
                Node tempNode = currentNode.getLink();
                
                this.head = new Node(newData, currentNode);
            }
            else {
                Node currentNode = this.head;
                
                for (int i = 0; i < index-1 ; i++) {
                    currentNode = currentNode.getLink();
                }
                
                
                Node tempNode = currentNode.getLink();
                currentNode.setLink(new Node(newData, tempNode));
                
                
                
            }

        }
    }
    /**
     * This method removes an object at certain index and mends the list so that the gap is
     * "closed". After removing that object, it returns it.
     */
    protected Object remove(int index) {
        Object toRemove = null;
        
        if (this.isEmpty()) {
            System.out.println("The list is empty.");
        }
        else if (this.head.getLink() == null) {
            toRemove = head.getItem();
            this.head = null;
        }
        else if (this.countNodes() < index) {
            System.out.println("There is no node at that location.");
        }
        else if (index == 0) {
            toRemove = this.head.getItem();
            this.head = this.head.getLink();
        }
        else {
            Node currentNode = this.head;
            
            for (int i = 0; i < index-1 ; i++) {
                currentNode = currentNode.getLink();
            }
            
            
            Node tempNode = currentNode.getLink();
            toRemove = tempNode.getItem();
            Node tempNode2 = tempNode.getLink();
            
            currentNode.setLink(tempNode2);
            
        }
        return toRemove;
    }
    /**
     * This method automatically finds the end of the list and adds a node to it, containing
     * the parameter newData.
     */
    protected void append(T newData) {
        if (this.head == null) {
            this.head = new Node(newData, null);
        }
        else {
            Node currentNode = this.head;
            while (currentNode.getLink() != null) {
                currentNode = currentNode.getLink();
                
            }
            currentNode.setLink(new Node(newData, null));
            
        }
    }
    
    /**
     * This method is identical to remove except that it does not return
     * anything. It ony deletes the node at the index provided.
     */
    protected void delete ( int index) {
        
        
        if (this.isEmpty()) {
            System.out.println("Cannot remove from empty list!");
        }
        else if (this.head.getLink() == null) {
            
            this.head = null;
        }
        else if (this.countNodes() < index) {
            System.out.println("There is no node at that location.");
        }
        else if (index == 0) {
            this.head = this.head.getLink();
        }
        
        else {
            Node currentNode = this.head;
            
            for (int i = 0; i < index-1 ; i++) {
                currentNode = currentNode.getLink();
            }
            
            
            Node tempNode = currentNode.getLink();
            
            Node tempNode2 = tempNode.getLink();
            
            currentNode.setLink(tempNode2);
            
        }
        
    }
    /**
     * This method returns the object contained at the given index in the parameter.
     * It does not alter the list in any way, it only allows the user to read the 
     * item.
     */
    protected T get(int index) {
        T returnItem = null;
        if(this.isEmpty()) {
            System.out.println("The list is empty.");
        }
        else if (this.head.getLink() == null) {
            returnItem = this.head.getItem();
        }
        else if (index == 0) {
            returnItem =  this.head.getItem();
        }
        else if (this.countNodes() < index) {
            System.out.println("There is no node at that location.");
        }
        else {
            Node currentNode = this.head;
            
            for (int i = 0; i < index - 1 ; i++) {
                currentNode = currentNode.getLink();
            }
            
            Node tempNode = currentNode.getLink();
            returnItem = tempNode.getItem();
        }
        return returnItem;
    }
    /**
     * This method does what size was asking for in the assignment.
     * It traverses the list and counts each item until it reaches a null. Then it 
     * returns the value.
     */
    public int countNodes() {
        int count = 0;
        Node currentNode = this.head;
        while (currentNode != null) {
            count++;
            currentNode = currentNode.getLink();
        }
        return count;
    }
    /**
     * This method traverses the list, adding each item from it to a string 
     * and returning that string.
     */
    public String toString() {
        String r = "";
        Node currentNode = this.head;
        while (currentNode != null) {
            r+= currentNode.getItem() + " \n";
            
            currentNode = currentNode.getLink();
            
        }
        
        return r;
    }
    /**
     * checks if head is null. If so, the list is empty and it returns true. If not, it returns false.
     */
    public boolean isEmpty() {
        if (this.head == null) {
            return true;
        }
        else {
            return false;
        }
    }
    /**
     * Traverses the list to find a targeted object. Upon finding the objet, it returns the index where it was found.
     * If it isn't found or the list is empty, the method returns -1.
     */
    protected int indexOf(Object target) {
        int indexOf = -1;
        int count = 0;
        if (isEmpty()) {
        }
        else {
            Node currentNode = this.head;
            while (currentNode != null) {
                if (currentNode.getItem().equals(target)) {
                    indexOf = count;
                }
                count++;
                currentNode = currentNode.getLink();
            }
        }
        return indexOf;
    }
    /**
     * compares two Quacks to see if they are deep copies of one another. If they are both quack, their lengths
     * are the same and each item in them is idential then they are considered copies, and the method returns true.
     */
    public boolean equals(Object other) {
        boolean r = true;
        if (!(other instanceof ArrayList)) {
            r = false;
        }
        else {
            ArrayList c = (ArrayList)other;
            
            if(c.countNodes() != this.countNodes()) {
                r = false;
            }
            else {
                Node currentNode1 = this.head;
                Node currentNode2 = c.getHead();
                
                while (currentNode1 != null) {
                    if (!(currentNode1.getItem().equals(currentNode2.getItem()))) {
                        r = false;
                    }
                    currentNode1 = currentNode1.getLink();
                    currentNode2 = currentNode2.getLink();
                }
            }
        }
        return r;
    }

}
