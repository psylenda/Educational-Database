import java.util.Scanner;




/**
 * Book
 * ----
 * This class holds a record of a book.
 * 
 * @author 
 */
public class Book extends Record
{
    private String name;
    private String description;
    private String author;
    private int year;

    /**
     * Constructors for objects of class Book
     */
    public Book()
    {
        // initialise instance variables
        super("Book", "unNamed Book","blank description" );
        this.author = "UnNamed author";
        this.year = 2021;
    }
    
    public Book(String title, String description, String author, int year)
    {
        // initialise instance variables
        super("Book", title, description );
        this.author = author;
        this.name = title;
        if (year < 1000 || year > 2021) year = 2021;
        else this.year = year;
    }
    
    /**
     * compareToBy
     * -----------
     * This method will compare this to other using the attribute specified by attributeToCompareBy.
     * A negative number is returned if this < other when comparing their respective attributeToCompareBy.
     * A 0 is returned if this = other with respect to attributeToCompareBy
     * a positive number is returned if this > other with respect to their attributeToCompareBy
     * So for example, if attributeToCompareBy is "Name", then 
     *              (assuming that both this and other have an attribute called 'Name')
     * if this.Name < other.Name, we'll return -1
     * if this.Name = other.Name, we'll return 0
     * if this.Name > other.Name, we'll return 1
     * PRECONDITION: Both this and other have the attribute specified by attributeToCompareBy.  
     *               If this has the attribute, but other does not, return a negative number.
     *               If this doesn't have the attribute, return a positive number.
     *               If neither has the attribute, return 0;
     *               This strategy will make it so that the items that DO have the attributeToCompareBy
     *               will end up at the beginning of a sorted list.
     *               other must be a Record. If not, we'll return 0;
     * POSTCONDITION: Neither this nor other is changed in any way. An integer is returned     
     */
    public int compareToBy(Record other, String attributeToCompareBy){
        // [OH NO! IT GOT ERASED!]
        int cVal = 0;
        
        if(!(other instanceof Record)) {
            return 0;
        }
        
        if(this.hasAttribute(attributeToCompareBy) && !(other.hasAttribute(attributeToCompareBy))) {
            cVal = -1;
        }
        else if (!(this.hasAttribute(attributeToCompareBy) && other.hasAttribute(attributeToCompareBy))) {
            cVal = 1;
        }

        else if (!(this.hasAttribute(attributeToCompareBy)) && !(other.hasAttribute(attributeToCompareBy))) {
            cVal = 0;
        }
        else {
            if (attributeToCompareBy.equalsIgnoreCase("recordType")) {
                cVal = this.getRecordType().compareTo(other.getRecordType());
            }
            else if (attributeToCompareBy.equalsIgnoreCase("identifier")) {
                cVal = this.getIdentifier().compareTo(other.getIdentifier());
            }
            else if (attributeToCompareBy.equalsIgnoreCase("description")) {
                cVal = this.getDescription().compareTo(other.getDescription());
            }
            else if (attributeToCompareBy.equalsIgnoreCase("Author")) {
                Book comparer = (Book) other;
                if (this.getLastName().equalsIgnoreCase(comparer.getLastName())) {
                    cVal = this.getFirstName().compareTo(comparer.getFirstName());
                }
                else {
                    cVal = this.getLastName().compareTo(comparer.getLastName());
                    
                }
                
            }
            else if (attributeToCompareBy.equalsIgnoreCase("Year")) {
                if (other instanceof Book) {
                    Book comparer = (Book) other;
                    if (this.year < comparer.getYear()) {
                        cVal = -1;
                    }
                    else if (this.year > comparer.getYear()) {
                        cVal = 1;
                    }
                    else if (this.year == comparer.getYear()) {
                        cVal = 0;
                    }
                    else {
                    }
                }
                else if (other instanceof Movie) {
                    Movie comparer = (Movie) other;
                    if (this.year < comparer.getYear()) {
                        cVal = -1;
                    }
                    else if (this.year > comparer.getYear()) {
                        cVal = 1;
                    }
                    else if (this.year == comparer.getYear()) {
                        cVal = 0;
                    }
                    else {
                    }
                }
            }
        }
        return cVal;
    }
    
    /**
     * getAuthor
     * ---------
     */
    public String getAuthor(){
        return new String(this.author);
    }

    /**
     * getYear
     * -------
     */
    public int getYear(){
        return this.year;
    }
    /**
     * getFirstName
     */
    public String getFirstName() {
        Scanner sc = new Scanner(this.author);
        String first = sc.next();
        String last = sc.next();
        return first;
    }
    /**
     * getLastName
     */
    public String getLastName() {
        Scanner sc = new Scanner(this.author);
        String first = sc.next();
        String last = sc.next();
        return last;
    }
    /**
     * Overrides the parent method to include the special variables in this class.
     */
        public boolean hasAttribute(String attribute) {
        if (    attribute.equalsIgnoreCase("recordType") ||
        attribute.equalsIgnoreCase("identifier") ||
        attribute.equalsIgnoreCase("description") ||
        attribute.equalsIgnoreCase("author") ||
        attribute.equalsIgnoreCase("year") ) {
            return true;
        }
        
        else {
            return false;
        }
    }
    
    /**
     * toString
     * --------
     * This provides a nice looking String representation of this particular Record
     * PRE: none
     * POST: current object is unchanged. A String is returned.
     */
    public String toString(){
        return this.identifier + " (" + this.year + "),  written by " + this.author + ": \n" + niceLookingDescription() + "\n";
        
    }
}
