/**
 * BLMDB
 * -----
 * The BLMDB is a database for Black Lives Matter (BLM) resources.
 * The main method contains a driver program that will open a menu
 * for the user to choose various options. 
 * The menu tree is this:
 *      1. display records --> 
 *              1. display all records
 *              2. display movies
 *              3. display books
 *              4. display online resources
 *              5. go back to previous menu
 *      2. sort records --> 
 *              1. sort by record type (i.e, Books, Movies, then Online)
 *              2. sort by name / title 
 *              3. sort by description
 *              4. sort by author*
 *              5. sort by director*
 *              6. sort by website URL*
 *              7. sort by year*
 *              8. Go back to previous menu
 *              (* Note: not all Records have these attributes, so sorting is somewhat unpredictable.
 *              Generally records lacking the sorting attribute will be put at the bottom of the list.)
 *              (Also note that you can sort by multiple criteria, simply by doing it in reverse order. For example,
 *              if you want to sort by year, but have same-year items then sorted by name, just first sort by name, then year.)
 *      3. import records -->
 *              1. import one record manually (by typing in the info for it)
 *              2. import records from BLM_resources.txt
 *              3. import records from a different file
 *              4. go back to previous menu
 *      4. export records to file
 *      5. quit
 * 
 * @author Karen 
 */

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.io.FileWriter;   
import java.io.IOException;  




/**
 * Write a description of class BLMDB here.
 *
 * @author David Nixon
 * @version Summer 2020
 */
public class BLMDB
{

    private RecordList recordList = new RecordList();

    /**
     * Constructor for objects of class BLMDB
     */
    public BLMDB()
    {
      // nothing needs to happen!
    }

    
    /**
     * main
     * ----
     * This is the driver. It simply creates an empty BLMDB object and starts the menu
     * PRE: none
     * POST: none
     */
    public static void main(String []args){
        // runAllTests();
        
        System.out.println("Welcome to the Black Lives Matter Resources Database.");
        System.out.println("-----------------------------------------------------");
        
        BLMDB theDatabase = new BLMDB();
        theDatabase.mainMenu();
        
    } // end main
    

    /**
     * mainMenu
     * --------
     * This displays the main menu and allows the user to choose among various options:
     *      1. display records
     *      2. sort records
     *      3. add records
     *      4. export records
     *      5. quit
     * PRE: none
     * POST: various different things will happen based on what the user chooses. See above menu.
     */
    public void mainMenu(){
        boolean theUserHasntYetChosenToQuit = true;
        String mainMessagePrompt = 
            "MAIN MENU\nPlease choose from the following options: \n" +
            "1. display records\n" +
            "2. sort records\n" +
            "3. import records\n" +
            "4. export records to file\n" +
            "5. quit\n";
        
        while (theUserHasntYetChosenToQuit){
            int selection = getIntFromUser(mainMessagePrompt, 1, 5);
            System.out.println(); // extra line
            switch(selection){
                case 1:  // display records
                    displayRecordsMenu();
                    break;
                    
                case 2: // sort records
                    sortRecordsMenu();
                    break;
                
                case 3: // add records
                    addRecordsMenu();
                    break;
                
                case 4: // export records
                    Scanner keys = new Scanner(System.in);
                    System.out.print("Enter the name of the file you want to export the records to > ");
                    String fileName = keys.nextLine();
                    this.exportDBtoFile(fileName);
                    System.out.println();
                    break;
                
                case 5: // quit
                    System.out.println("Okay, bye!");
                    return;
                    
                default:
                    break;
            }
        }
    } // end mainMenu()
    
    
    /**
     * displayRecordsMenu
     * ------------------
     * This displays a menu which allows the user to choose different ways to display the records:
     *      1. display all records
     *      2. display Movies
     *      3. display Books
     *      4. display online resouces
     *      5. go back to main menu
     * PRE: none
     * POST: various different things will happen based on what the user chooses. See above menu.
     */
    public void displayRecordsMenu(){
        boolean theUserHasntYetChosenToQuit = true;
        String mainMessagePrompt = 
            "DISPLAY MENU\n Please choose from the following options: \n" +
            "1. display all records\n" +
            "2. display movies\n" +
            "3. display books\n" +
            "4. display online resources\n" +
            "5. go back to previous menu\n";
        
        while (theUserHasntYetChosenToQuit){
            int selection = getIntFromUser(mainMessagePrompt, 1, 5);
            System.out.println(); // extra line
            switch(selection){
                case 1:  // display all records
                    this.recordList.displayAllRecords();
                    break;
                    
                case 2: // display Movies
                    System.out.println("Movies:");
                    this.recordList.displayRecordsOfRecordType("Movie");
                    break;
                
                case 3: // display Books
                    System.out.println("Books:");
                    this.recordList.displayRecordsOfRecordType("Book");
                    break;
                
                case 4: // display online resources
                    System.out.println("Online resources:");
                    this.recordList.displayRecordsOfRecordType("Online");
                    break;
                
                case 5: // go back
                    System.out.println(); // extra line
                    return;
                    
                default:
                    break;
            }
            System.out.println(); // extra line
        }
    } // end displayRecordsMenu
    
    
    /**
     * sortRecordsMenu
     * ---------------
     * This displays a menu which allows the user to choose different ways to sort the records:
     *      1. record type (i.e, Books, Movies, then Online)
     *      2. name / title 
     *      3. description
     *      4. author*
     *      5. director*
     *      6. website URL*
     *      7. year*
     *      8. Go back to previous menu
     *      (* Note: not all Records have these attributes, so sorting is somewhat unpredictable.
     *         Generally records lacking the sorting attribute will be put at the bottom of the list.)
     * PRE: none
     * POST: Will sort the list of records based on what they choose.
     */
    public void sortRecordsMenu(){
        boolean theUserHasntYetChosenToQuit = true;
        String mainMessagePrompt = 
            "SORTING MENU\n Please choose from the following options: \n" +
            "1. sort by record type (i.e, Books, Movies, then Online)\n" +
            "2. sort by name / title \n" +
            "3. sort by description\n" +
            "4. sort by author*\n" +
            "5. sort by director*\n" +
            "6. sort by website URL*\n" +
            "7. sort by year*\n" +
            "8. Go back to previous menu\n" +
            "(* Note: not all Records have these attributes, so sorting is somewhat unpredictable.\n" +
            "   Generally records lacking the sorting attribute will be put at the bottom of the list.)\n" +
            "(Also note that you can sort by multiple criteria, simply by doing it in reverse order. For example,\n" +
            " if you want to sort by year, but have same-year items then sorted by name, just first sort by name, then year.)\n";
        
        while (theUserHasntYetChosenToQuit){
            int selection = getIntFromUser(mainMessagePrompt, 1, 8);
            System.out.println(); // extra line
            switch(selection){
                case 1:  // sort by recordType:
                    this.recordList.sortBy("recordType");
                    System.out.println("The database has now been sorted by record type.");
                    break;
                    
                case 2: // sort by identifier (name / title)
                    this.recordList.sortBy("identifier");
                    System.out.println("The database has now been sorted by name/title.");
                    break;
                
                case 3: // sort by desription
                    this.recordList.sortBy("description");
                    System.out.println("The database has now been sorted by description.");
                    break;
                
                case 4: /// sort by author
                    this.recordList.sortBy("author");
                    System.out.println("The database has now been sorted by author.");
                    break;
                
                case 5: /// sort by director
                    this.recordList.sortBy("director");
                    System.out.println("The database has now been sorted by director.");
                    break;
                
                case 6: /// sort by website URL
                    this.recordList.sortBy("websiteURL");
                    System.out.println("The database has now been sorted by webiste URL.");
                    break;
                
                case 7: /// sort by year
                    this.recordList.sortBy("year");
                    System.out.println("The database has now been sorted by year.");
                    break;
                
                case 8: // go back
                    return;
                    
                default:
                    break;
            }
    
        }
    } // end sortRecordsMenu()
    
    
    /**
     * addRecordsMenu()
     * ----------------
     * This displays a menu for adding new records:
     * 
     * PRE: none
     * POST: new records will be added to the recordList.
     */
    public void addRecordsMenu(){
        boolean theUserHasntYetChosenToQuit = true;
        String mainMessagePrompt = 
            "IMPORT MENU\n Please choose from the following options: \n" +
            "1. import one record manually (by typing in the info for it)\n" +
            "2. import records from BLM_resources.txt\n" +
            "3. import records from a different file\n" +
            "4. go back to previous menu\n";
        
        while (theUserHasntYetChosenToQuit){
            int selection = getIntFromUser(mainMessagePrompt, 1, 4);
            System.out.println(); // extra line
            switch(selection){
                case 1:  // import record manually
                    this.importOneRecordFromConsole();
                    System.out.println("Record added.\n");
                    break;
                    
                case 2: // import from BLM_resources.txt 
                    this.importRecordsFromFile("BLM_resources.txt");
                    System.out.println("Records added.\n");
                    break;
                    
                case 3: // import from file:
                    Scanner keys = new Scanner(System.in);
                    System.out.print("Enter the name of the file you want to import the records from > ");
                    String fileName = keys.nextLine();
                    this.importRecordsFromFile(fileName);
                    System.out.println("Records added.\n");
                    break;
                
                case 4: // go back
                    return;
                    
                default:
                    break;
            }
        }
    } // end recordsMenu()
    
    
    /**
     * importRecordsFromFile
     * ---------------------
     * PRE: the file called filename must exist and be in the same directory as this java file.
     *      the file called filename must have all of its records formated in the proper way 
     *      (each of these must be on its own line):
     *          recordType
     *          identifier
     *          description
     *          websiteURL  (if recordType == "Online")
     *          author (if recordType == "Book")
     *          director (if recordType == "Movie")
     *          year (if recordType == "Book" or "Movie")
     *          (Optional: an empty line between records)
     */
    public boolean importRecordsFromFile(String filename){
        boolean hasAtLeastOneFile = false;
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNext()) {
                //System.out.println(scanner.nextLine());
                String currentLine = scanner.nextLine();
                // eat any blank lines:
                while (currentLine.trim().equals("") && scanner.hasNext()) currentLine = scanner.nextLine();
                // if we have a blank line and there's nothing after it, end the method:
                if (currentLine.trim().equals("") && !(scanner.hasNext())) return hasAtLeastOneFile;
                if (currentLine.equalsIgnoreCase("Online")){
                    try {
                        String name = scanner.nextLine().trim();
                        String description = scanner.nextLine().trim();
                        String URL = scanner.nextLine().trim();
                        OnlineRec newOnline = new OnlineRec(name, description, URL);
                        this.recordList.append(newOnline);
                    }
                    catch(Exception e){
                        // Probably we got here because we tried to do nextLine and it didn't find anything.
                        System.out.println("Error: " + e + "<" + filename + "> not properly formatted");
                        return false;
                    }
                }
                else if (currentLine.equalsIgnoreCase("Book")){
                    try {
                        String title = scanner.nextLine().trim();
                        String description = scanner.nextLine().trim();
                        String author = scanner.nextLine().trim();
                        String yearAsString = scanner.nextLine().trim();
                        Book newBook = new Book(title, description, author, Integer.parseInt(yearAsString));
                        this.recordList.append(newBook);
                    }
                    catch(Exception e){
                        // Probably we got here because we tried to do nextLine and it didn't find anything.
                        System.out.println("Error: " + e + "<" + filename + "> not properly formatted");
                        return false;
                    }
                }
                else if (currentLine.equalsIgnoreCase("Movie")){
                    try {
                        String title = scanner.nextLine().trim();
                        String description = scanner.nextLine().trim();
                        String director = scanner.nextLine().trim();
                        String yearAsString = scanner.nextLine().trim();
                        Movie newMovie = new Movie(title, description, director, Integer.parseInt(yearAsString));
                        this.recordList.append(newMovie);
                    }
                    catch(Exception e){
                        // Probably we got here because we tried to do nextLine and it didn't find anything.
                        System.out.println("Error: " + e + "<" + filename + "> not properly formatted");
                        return false;
                    }
                }
                else {
                    System.out.println("Error: '" + currentLine + "' is not a recognized record type.");
                    return false;
                }
                
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: We could not find <" + filename + ">.");
            return false;
        }

        return true;
    } // end importRecordsFromFile(String filename)
    
    
    /**
     * importOneRecordFromConsole
     * --------------------------
     * This method asks the user if they want to add an OnlineRec, Book, or Movie 
     * (or if they changed their mind and don't want to add a new record)
     * and then, based on which they chose, asks the user to input more information for that record
     * and then creates the appropriate record and adds it to the database.
     * The method checks for incorrect input when making the choice and prompts the user again if
     * the input was not in the correct range or was not the correct type.
     * PRE: In the case of a Movie or a Book, when inputing the year, the user must type an int.
     * POST: a new Record has been added to this.recordList 
     *      (unless the user chose "never mind, go back", in which case, nothing happens.)
     */
    public void importOneRecordFromConsole(){ 
        Scanner keys = new Scanner(System.in);
        
        boolean successfullyGotMyInt = false;
        int recordTypeInt = -1;
        String messagePromptForRecordType = "What kind of record would you like to import? type a number:\n" +
                            "1. OnlineRec \n" +
                            "2. Book \n" +
                            "3. Movie \n" +
                            "4. (Never mind, go back)\n";
        recordTypeInt = getIntFromUser(messagePromptForRecordType, 1, 4);
                            
        //System.out.println("you typed " + recordTypeInt);
        String name;
        String description;
        String URL;
        String director;
        String author;
        int year;
        switch (recordTypeInt){
            case 1: // Online thing:
                System.out.print("Please type the name of the OnlineRec.  > ");
                name = keys.nextLine();
                System.out.print("Please type a description of the OnlineRec.  > ");
                description = keys.nextLine();
                System.out.print("Please type the URL of the OnlineRec.  > ");
                URL = keys.nextLine();
                // FOR TESTING: System.out.println("<" + name + ">, [" + description + "], {" + URL + "}");
                OnlineRec newOnline = new OnlineRec(name, description, URL);
                this.recordList.append(newOnline);
                break;
            case 2: // BOOK:
                System.out.print("Please type the title of the book.  > ");
                name = keys.nextLine();
                System.out.print("Please type a description of the book.  > ");
                description = keys.nextLine();
                System.out.print("Please type the author of the book.  > ");
                author = keys.nextLine();
                // we'll make sure that the book's publication year is between 1473
                // (the year of the first book published in English), and 2021:
                year = getIntFromUser("Please type the publication year of the book.", 1473, 2021);
                // FOR TESTING: System.out.println("<" + name + ">, [" + description + "], {" +author + "}, *" + year + "*");
                Book newBook = new Book(name, description, author, year);
                this.recordList.append(newBook);
                break;
            case 3: // MOVIE:
                System.out.print("Please type the name of the movie.  > ");
                name = keys.nextLine();
                System.out.print("Please type a description of the movie.  > ");
                description = keys.nextLine();
                System.out.print("Please type the director of the movie.  > ");
                director = keys.nextLine();
                // we'll make sure that the movie's year is between 1888
                // (the year of the first created movie), and 2021:
                year = getIntFromUser("Please type the year the movie came out.", 1888, 2021);
                // FOR TESTING: System.out.println("<" + name + ">, [" + description + "], {" + director + "}, *" + year + "*");
                Movie newMovie = new Movie(name, description, director, year);
                this.recordList.append(newMovie);
                break;
            default:
                // it should not be able to get here.
        }

    } // end importOneRecordFromConsole()
    
    
    /**
     * getIntFromUser
     * ---------------
     * This method gets an int from the user after displaying the message given in the parameter.
     * If the user doesn't enter an int, 
     * or if the int that the user enters is less than lowestAcceptableInt,
     * or if the int that the user enters is greater than highestAcceptableInt,
     * the method will print an error message to the console and re-display the message,
     * prompting the user to enter a number again.
     * This will also put a " > " after the prompt message.
     * PRE: lowestAcceptableInt <= highestAcceptableInt
     * POST: the method returns an int, but does not change the state of the current BLMDB object
     */
    private int getIntFromUser(String message, int lowestAcceptableInt, int highestAcceptableInt){
        Scanner keys = new Scanner(System.in);
        boolean successfullyGotMyInt = false;
        int intToReturn = 0;
        while (!successfullyGotMyInt){
            try {
                System.out.print(message);
                System.out.print(" > ");
                intToReturn = keys.nextInt();
                if (lowestAcceptableInt <= intToReturn && intToReturn <= highestAcceptableInt) 
                    successfullyGotMyInt = true;
                else 
                    throw new InputMismatchException(); 
            }
            catch (InputMismatchException e){
                System.out.println("Oops! Please type an int no lower than " + lowestAcceptableInt +
                                       " and no higher than " + highestAcceptableInt + ".");
            }
            keys.nextLine(); // to eat the newline
        }
        
        return intToReturn;
    } // end getIntFromUser
    
    
    
    /**
     * exportDBtoFile
     * --------------
     * This will export all the records to a text file, 
     * and they'll be in the same format as they need to be to later import them.
     * They'll be in whatever order they were when the method was called.
     * If the filename already exists, it will be overwritten.
     * If it doesn't exist, it will be created.
     * PRE: none.  (Though if the database is empty, then nothing will be written to the file.)
     * POST: a file will be created with the db in it. The current object will be unchanged.
     */
    public boolean exportDBtoFile(String filename){
        try {
            FileWriter f = new FileWriter(filename);
            int size = this.recordList.size();
            for (int i = 0 ; i < size; i++){
                Record currentRecord = this.recordList.get(i);
                if (currentRecord instanceof OnlineRec){
                    OnlineRec o = (OnlineRec)currentRecord;
                    f.write("Online\n" + 
                            o.getIdentifier() + " \n" + 
                            o.getDescription() + "\n" +
                            o.getWebsiteURL() + "\n\n");
                }
                else if (currentRecord instanceof Book){
                    Book o = (Book)currentRecord;
                    f.write("Book\n" + 
                            o.getIdentifier() + " \n" + 
                            o.getDescription() + "\n" + 
                            o.getAuthor() + "\n" +
                            o.getYear() + "\n\n");
                }
                else if (currentRecord instanceof Movie){
                    Movie o = (Movie)currentRecord;
                    f.write("Movie\n" + 
                            o.getIdentifier() + " \n" + 
                            o.getDescription() + "\n" +
                            o.getDirector() + "\n" +
                            o.getYear() + "\n\n");
                }                
            }
            
            f.close();
            System.out.println("We have sucecessfully exported the BLM database to " + filename);
        } catch (IOException e) {
            System.out.println("An error occurred tying to write to <" + filename + ">");
            return false;
            // FOR TESTING: e.printStackTrace();
        }
    
        return true;
    } // end exportDBtoFile()
    
    
    /**
     * runAllTests()
     * -------------
     * Hope you like tests. This has about every single one I could think to add. I didn't include any of the methods that came with this assignment
     * as I didn't rewrite them in any way.
     */
    public static void runAllTests(){
        BLMDB testDatabase = new BLMDB();
          Book redwall = new Book("Redwall", "Mice fighting rats", "Brian Jacques", 1977);
          Book shades = new Book("Shade's Children", "Evil Dystopian Future", "Garth Nix", 1989);
          Book bloodmeridian = new Book("Blood Meridian", "Cowboys in Mexico", "Cormac McCarthy", 1972);
          Movie dawnDead = new Movie("Dawn of the Dead", "Zombie movie", "George Romero", 1978);
          Movie titanic = new Movie("Titanic", "Romance movie on a boat", "James Cameron", 1990);
          OnlineRec facebook = new OnlineRec("Facebook", "social media platform", "www.facebook.com");
          OnlineRec wikipedia = new OnlineRec("Wikipedia", "online encyclopedia", "www.wikipedia.org");
          
        // put tests here.
        
        System.out.println("TESTING BOOK");
        System.out.println("--------------");
        System.out.println("TESTING TOSTRING: ");
        System.out.println(redwall.toString());
        System.out.println("TESTING GETTERS: ");
        System.out.println(redwall.getIdentifier());
        System.out.println(redwall.getRecordType());
        System.out.println(redwall.getDescription());
        System.out.println(redwall.getAuthor());
        System.out.println(redwall.getYear());
        System.out.println(redwall.getFirstName());
        System.out.println(redwall.getLastName());
        System.out.println("TESTING COMPARETOBY()");
        //comparing it to other books
        System.out.println("Redwall compared to Shade's Children by identifier:" + redwall.compareToBy(shades, "identifier")); //less than zero
        System.out.println("Redwall compared to Blood Meridian by identifier: " + redwall.compareToBy(bloodmeridian, "identifier")); // should be above 0
        System.out.println("Redwall compared to Shade's Children by year:" + redwall.compareToBy(shades, "year")); //less than zero
        System.out.println("Redwall compared to Shade's Children by author:" + redwall.compareToBy(shades, "author")); //less than zero
        System.out.println("Redwall compared to Shade's Children by type:" + redwall.compareToBy(shades, "recordType")); // 0
        //comparing it to a movie
        //Comparing it using an attribute that both have:
        System.out.println("Redwall compared to Titanic by identifier:" + redwall.compareToBy(titanic, "identifier")); // < 0
        //comparing it using an attribute redwall has but titanic doesn't
        System.out.println("Redwall compared to Titanic by author:" + redwall.compareToBy(titanic, "identifier")); // > 0
        //comparing it using an attribute that neither has
        System.out.println("Redwall compared to Titanic by URL:" + redwall.compareToBy(titanic, "websiteURL")); // 0
        //comparing it using an attribute redwall doesn't have but titanic does
        System.out.println("Redwall compared to Titanic by director:" + redwall.compareToBy(titanic, "director")); // < 0
        
        //All of these should print true except for websiteURL.
        System.out.println("TESTING HAS ATTRIBUTE():");
        System.out.println("recordtype: " + redwall.hasAttribute("recordType"));
        System.out.println("identifier: " + redwall.hasAttribute("identifier"));
        System.out.println("author: " + redwall.hasAttribute("author"));
        System.out.println("year: " + redwall.hasAttribute("year"));
        System.out.println("websiteURL : " + redwall.hasAttribute("websiteURL") + "\n");
        
        System.out.println("TESTING MOVIE");
        System.out.println("--------------");
        //All of these should print true except for websiteURL and author
        System.out.println("TESTING TOSTRING: ");
        System.out.println(dawnDead.toString());
        System.out.println("TESTING GETTERS: ");
        System.out.println(dawnDead.getIdentifier());
        System.out.println(dawnDead.getRecordType());
        System.out.println(dawnDead.getDescription());
        System.out.println(dawnDead.getDirector());
        System.out.println(dawnDead.getYear());
        System.out.println(dawnDead.getFirstName());
        System.out.println(dawnDead.getLastName());
        System.out.println("TESTING COMPARETOBY()");
        //comparing it to other books and movies
        System.out.println("Dawn of the Dead compared to Shade's Children by identifier:" + dawnDead.compareToBy(titanic, "identifier")); //less than zero
        System.out.println("Dawn of the Dead compared to Blood Meridian by identifier: " + dawnDead.compareToBy(bloodmeridian, "identifier")); // should be above 0
        System.out.println("Dawn of the Dead compared to Titanic by year:" + dawnDead.compareToBy(titanic, "year")); //less than zero
        System.out.println("Dawn of the Dead compared to Titanic by director:" + dawnDead.compareToBy(titanic, "director")); //less than zero
        System.out.println("Dawn of the Dead compared to Titanic by type:" + dawnDead.compareToBy(titanic, "recordType")); // 0
        //comparing it to a book
        //Comparing it using an attribute that both have:
        System.out.println("Dawn of the Dead compared to Redwall by identifier:" + redwall.compareToBy(titanic, "identifier")); // < 0
        //comparing it using an attribute Dawn of the Dead has but Redwall doesn't
        System.out.println("Dawn of the Dead compared to Redwall by director:" + dawnDead.compareToBy(titanic, "director")); // > 0
        //comparing it using an attribute that neither has
        System.out.println("Dawn of the Dead compared to Redwall by URL:" + dawnDead.compareToBy(titanic, "websiteURL")); // 0
        //comparing it using an attribute Dawn of the Dead doesn't have but Redwall does
        System.out.println("Dawn of the Dead compared to Redwall by director:" + dawnDead.compareToBy(redwall, "author")); // < 0
        
        //All of these should print true except for websiteURL.
        System.out.println("TESTING HAS ATTRIBUTE():");
        System.out.println("recordtype: " + dawnDead.hasAttribute("recordType"));
        System.out.println("identifier: " + dawnDead.hasAttribute("identifier"));
        System.out.println("author: " + dawnDead.hasAttribute("director"));
        System.out.println("year: " + dawnDead.hasAttribute("year"));
        System.out.println("websiteURL : " + dawnDead.hasAttribute("websiteURL") + "\n");
        
        System.out.println("TESTING ONLINEREC:");
        System.out.println("------------------");
        System.out.println("TESTING TOSTRING: ");
        System.out.println(redwall.toString());
        System.out.println("TESTING GETTERS: ");
        System.out.println(facebook.getIdentifier());
        System.out.println(facebook.getRecordType());
        System.out.println(facebook.getDescription());

        System.out.println("TESTING COMPARETOBY()");
        //comparing it to another website and books
        System.out.println("Facebook compared to Wikipedia by identifier:" + facebook.compareToBy(wikipedia, "identifier")); //less than zero
        System.out.println("Facebook compared to Blood Meridian by identifier: " + facebook.compareToBy(bloodmeridian, "identifier")); // should be above 0
        System.out.println("Facebook compared to Wikipedia by type:" + facebook.compareToBy(wikipedia, "recordType")); // 0
        //comparing it to a movie
        //Comparing it using an attribute that both have:
        System.out.println("Facebook compared to Titanic by identifier:" + facebook.compareToBy(titanic, "identifier")); // < 0
        //comparing it using an attribute Facebook has but Titanic doesn't
        System.out.println("Facebook compared to Titanic by author:" + facebook.compareToBy(titanic, "websiteURL")); // > 0
        //comparing it using an attribute that neither has
        System.out.println("Facebook compared to Titanic by URL:" + facebook.compareToBy(titanic, "author")); // 0
        //comparing it using an attribute Facebook doesn't have but titanic does
        System.out.println("Facebook compared to Titanic by director:" + facebook.compareToBy(titanic, "director")); // < 0
        
      
        System.out.println("TESTING HAS ATTRIBUTE():");
        System.out.println("recordtype: " + facebook.hasAttribute("recordType")); //true
        System.out.println("identifier: " + facebook.hasAttribute("identifier")); //true
        System.out.println("author: " + facebook.hasAttribute("author")); //false
        System.out.println("year: " + facebook.hasAttribute("year")); //false
        System.out.println("websiteURL : " + facebook.hasAttribute("websiteURL") + "\n"); //true
        
        
        
        //Testing append
        testDatabase.recordList.append(redwall);
        testDatabase.recordList.append(shades);
        testDatabase.recordList.append(bloodmeridian);
        testDatabase.recordList.append(dawnDead);
        testDatabase.recordList.append(titanic);
        
        //Testing display
        testDatabase.recordList.displayAllRecords();
        
        //In order to test swap I had to turn it into a public method.
        //testDatabase.recordList.swap(1,3);
        //If testing this,, shades and dawn of the dead should switch places when all records are displayed.
        testDatabase.recordList.displayAllRecords();
        
        testDatabase.recordList.append(facebook);
        testDatabase.recordList.append(wikipedia);
        
        //Testing sort by year
        testDatabase.recordList.sortBy("year");
        testDatabase.recordList.displayAllRecords();
        //Sort by identifier
        testDatabase.recordList.sortBy("identifier");
        testDatabase.recordList.displayAllRecords();
        //sort by record type
        testDatabase.recordList.sortBy("recordType");
        testDatabase.recordList.displayAllRecords();
        //sort by author. books up top the rest below
        testDatabase.recordList.sortBy("author");
        testDatabase.recordList.displayAllRecords();
        //sort by director
        testDatabase.recordList.sortBy("director");
        testDatabase.recordList.displayAllRecords();
        //sort by websiteURL
        testDatabase.recordList.sortBy("websiteURL");
        testDatabase.recordList.displayAllRecords();
        
        //Testing all the specific displays.
        testDatabase.recordList.displayRecordsOfRecordType("Book");
        testDatabase.recordList.displayRecordsOfRecordType("Movie");
        testDatabase.recordList.displayRecordsOfRecordType("Online");
    }// end runAllTests()
    
} // end BLMDB class
