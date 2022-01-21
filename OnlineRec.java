
/**
 * OnlineRec
 * ---------
 * This class holds a record of some online resource like a website or an online article.
 * 
 * @author 
 */
public class OnlineRec extends Record
{
    private String name;
    private String websiteURL;
    private int year;

    /**
     * Constructors for objects of class OnlineRec
     */
    public OnlineRec()
    {
        // initialise instance variables
        super("Online", "unNamed OnlineRec","blank description" );
        this.websiteURL = "https://www.youtube.com/watch?v=dQw4w9WgXcQ";
    }
    public OnlineRec(String name, String description, String websiteURL)
    {
        // initialise instance variables
        super("Online", name, description );
        this.websiteURL = websiteURL;
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
        if (!(other instanceof Record)) {
            return 0;
        }
         
        if (this.hasAttribute(attributeToCompareBy) && !(other.hasAttribute(attributeToCompareBy))) {
             cVal = -1;
         }
        else if (!(this.hasAttribute(attributeToCompareBy))) {
             cVal = 1;
         }
        else if (attributeToCompareBy.equalsIgnoreCase("description")) {
            cVal = this.getDescription().compareTo(other.getDescription());
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
             else if (attributeToCompareBy.equalsIgnoreCase("URL")) {
                 OnlineRec comparer = (OnlineRec) other;
                 cVal = this.getWebsiteURL().compareTo(comparer.getWebsiteURL());
             }
             else {
             }
         }
         return cVal;
    }
    
    /**
     * getWebsiteURL
     * -------------
     */
    public String getWebsiteURL(){
        return new String(this.websiteURL);
    }
        /**
     * Overrides the parent method to include the special variables in this class.
     */
    public boolean hasAttribute(String attribute) {
        if (    attribute.equalsIgnoreCase("recordType") ||
        attribute.equalsIgnoreCase("identifier") ||
        attribute.equalsIgnoreCase("description") ||
        attribute.equalsIgnoreCase("websiteURL") ) {
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
        return this.identifier + " (" + this.websiteURL + "): \n" + niceLookingDescription() + "\n";
        
    }
}
