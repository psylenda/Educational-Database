import java.util.Scanner;
/**
 * Movie
 * ----
 * This class holds a record of a movie.
 * 
 * @author 
 */
public class Movie extends Record
{
    private String name;
    private String description;
    private String director;
    private int year;

    /**
     * Constructors for objects of class Movie
     */
    public Movie()
    {
        // initialise instance variables
        super("Movie", "unNamed Movie","blank description" );
        this.director = "UnNamed Director";
        this.year = 2021;
    }
    public Movie(String name, String description, String director, int year)
    {
        // initialise instance variables
        super("Movie", name, description );
        this.director = director;
        this.name = name;
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
            else if (attributeToCompareBy.equalsIgnoreCase("description")) {
                cVal = this.getDescription().compareTo(other.getDescription());
            }
            else if (attributeToCompareBy.equalsIgnoreCase("identifier")) {
                cVal = this.getIdentifier().compareTo(other.getIdentifier());
            }
            else if (attributeToCompareBy.equalsIgnoreCase("Director")) {
                Movie comparer = (Movie) other;
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
     * getDirector
     * -----------
     */
    public String getDirector(){
        return new String(this.director);
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
        Scanner sc = new Scanner(this.director);
        String first = sc.next();
        String last = sc.next();
        return first;
    }
    /**
     * getLastName
     */
    public String getLastName() {
        Scanner sc = new Scanner(this.director);
        String first = sc.next();
        String last = sc.next();
        return last;
    }
    /**
     * toString
     * --------
     * This provides a nice looking String representation of this particular Record
     * PRE: none
     * POST: current object is unchanged. A String is returned.
     */
    public String toString(){
        return this.identifier + " (" + this.year + "), directed by " + this.director + ": \n" + niceLookingDescription()+ "\n";
        
    }
        /**
     * Overrides the parent method to include the special variables in this class.
     */
    public boolean hasAttribute(String attribute) {
        if (    attribute.equalsIgnoreCase("recordType") ||
        attribute.equalsIgnoreCase("identifier") ||
        attribute.equalsIgnoreCase("description") ||
        attribute.equalsIgnoreCase("director") ||
        attribute.equalsIgnoreCase("year") ) {
            return true;
        }
        
        else {
            return false;
        }
    }
}
