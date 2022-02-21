import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class main {

    // Method to add new contact
    public static void addContact() throws IOException {

        System.out.println("---------------------------------------------------");
        System.out.println("|                  ADD NEW CONTACT                |");
        System.out.println("---------------------------------------------------\n");

        // instantiates the scanner class to be used across the method
        Scanner scan = new Scanner(System.in);

        //Specify the file name
        File file = new File("phone.txt");

        //Creates file if it doesn't exist already
        if (!file.exists()) file.createNewFile();

        // instantiates the file writer class and pass in file as argument
        FileWriter fwrite = new FileWriter(file,true);
        BufferedWriter bw = new BufferedWriter( fwrite );

        // instantiates the file reader class and pass in file as argument
        FileReader fread = new FileReader(file);
        BufferedReader br = new BufferedReader( fread );

        // declare variables to be used for the operation
        String fname, lname, address, city , number, record;

        // boolean variable to be used to check if contact exists
        boolean contactExists = false;

        // take in contact details
        System.out.println("Enter First Name: ");
        fname = scan.nextLine();

        System.out.println("Enter Last Name: ");
        lname = scan.nextLine();

        System.out.println("Enter Address: ");
        address = scan.nextLine();

        System.out.println("Enter Phone Number: ");
        number = scan.nextLine();

        System.out.println("Enter City: ");
        city = scan.nextLine();

        // loop through the file to check if contact already exists
        while( ( record = br.readLine() ) != null ) {
            // instantiate the stringtokenizer class to be used to split details from the line been read from the file
            StringTokenizer st = new StringTokenizer(record,",");

            // convert boolean contactexists to true when a number in the file matches the number inputted
            if (st.nextToken().equals(number)) {
                contactExists = true;
            }
        }

        // add contact to file if it doesn't exist in the file
        if (!contactExists) {
            // write to file
            bw.write(number+","+fname+","+lname+","+address+","+city);
            bw.flush();
            bw.newLine();
            // close buffered writer
            bw.close();
            System.out.println("Contact saved successfully!");
        } else {
            System.out.println("Phone Number already exists!!!");
        }

        System.out.println("");
        System.out.println("Exiting Contact Save...");

    }

    // Method to delete contact
    public static void deleteContact() throws IOException {

        System.out.println("---------------------------------------------------");
        System.out.println("|                   DELETE CONTACT                |");
        System.out.println("---------------------------------------------------\n");

        // instantiates the scanner class to be used across the method
        Scanner scan = new Scanner(System.in);

        // declare variables to be used for operation
        String number, record;

        // boolean variable to be used to check if contact exists
        boolean found = false;

        // create new file for the operation
        File newFile = new File("new-phone.txt");

        // specify file name
        File file = new File("phone.txt");

        // instantiates the file reader class and pass in file as argument
        FileReader fread = new FileReader(file);
        BufferedReader br = new BufferedReader( fread );

        // instantiates the file writer class and pass in file as argument
        FileWriter fwrite = new FileWriter(newFile);
        BufferedWriter bw = new BufferedWriter( fwrite );

        System.out.println("Enter Contact Number: ");
        number = scan.nextLine();

        // loop through to search for number to be deleted
        while( ( record = br.readLine() ) != null ) {
            // instantiate the stringtokenizer class to be used to split details from the line been read from the file
            StringTokenizer st = new StringTokenizer(record,",");

            // check if inputted number matches number of current contact that's being looped through
            if (st.nextToken().equals(number)) {
                // convert boolean found to true when contact to be deleted is found
                found = true;

                // continue (skip the current loop) the loop when number is found, so it won't be written to the new file
                continue;
            }

            // write records that don't match the number to be deleted to the new file
            bw.write(record);
            bw.flush();
            bw.newLine();
        }

        // close buffered reader
        br.close();
        // close buffered writer
        bw.close();

        // delete the old file
        file.delete();
        // rename new file to old file
        newFile.renameTo(file);

        // print success or error message if number was found
        if (!found) System.out.println("No contact with number "+number+" found!!");
        else System.out.println(number+" deleted successfully!");

        System.out.println("");
        System.out.println("Exiting Contact Delete...");
    }

    // Method to modify number
    public static void modifyContact () throws IOException{

        System.out.println("---------------------------------------------------");
        System.out.println("|                   MODIFY CONTACT                |");
        System.out.println("---------------------------------------------------\n");

        // instantiates the scanner class to be used across the method
        Scanner scan = new Scanner(System.in);

        // declare variables to be used for operation
        String fname, lname, address, city, number, record;

        // boolean variable to be used to check if contact exists
        boolean found = false;

        // specify file name
        File file = new File("phone.txt");

        // create new file for the operation
        File newFile = new File("new_phone.txt");

        // instantiates the file reader class and pass in file as argument
        FileReader fread = new FileReader(file);
        BufferedReader br = new BufferedReader( fread );

        // instantiates the file writer class and pass in file as argument
        FileWriter fwrite = new FileWriter(newFile);
        BufferedWriter bw = new BufferedWriter( fwrite );

        // take in contact details
        System.out.println("Enter contact number to be modified: ");
        number = scan.nextLine();

        System.out.println("Enter new Firstname: ");
        fname = scan.nextLine();

        System.out.println("Enter new Lastname: ");
        lname = scan.nextLine();

        System.out.println("Enter new Address: ");
        address = scan.nextLine();

        System.out.println("Enter new City: ");
        city = scan.nextLine();

        // loop through to search for number to be modified
        while( ( record = br.readLine() ) != null ) {
            // instantiate the stringtokenizer class to be used to split details from the line been read from the file
            StringTokenizer st = new StringTokenizer(record,",");

            // check if inputted number matches number of current contact that's being looped through
            if (st.nextToken().equals(number)) {
                // convert boolean found to true when contact to be modified is found
                found = true;

                // write new contact details to the new file created
                bw.write(number+","+fname+","+lname+","+address+","+city);
                bw.flush();
                bw.newLine();

                // continue (skip the current loop) the loop when number is found, so it won't be written to the new file
                continue;
            }

            // write records that don't match the number to be modified to the new file
            bw.write(record);
            bw.flush();
            bw.newLine();
        }

        // close buffered reader
        br.close();
        // close buffered writer
        bw.close();

        // delete the old file
        file.delete();
        // rename new file to old file
        newFile.renameTo(file);

        // print success or error message if number was found
        if (!found) System.out.println("No contact with number "+number+" found!!");
        else System.out.println(number+" Modified successfully!");

        System.out.println("");
        System.out.println("Exiting Contact Update...");
    }

    // Method to search for number
    public static void searchContact() throws IOException {

        System.out.println("------------------------------------------------------");
        System.out.println("|                 SEARCH FOR CONTACTS                |");
        System.out.println("------------------------------------------------------\n");

        // instantiates the scanner class to be used across the method
        Scanner scan = new Scanner(System.in);

        // declare variables to be used for operation
        String term, record, choice;

        // boolean variable to be used to check if any contact exists
        boolean found = false;

        // instantiates the file reader class and pass in file as argument
        FileReader fread = new FileReader("phone.txt");
        BufferedReader br = new BufferedReader( fread );

        // take in search term
        System.out.println("Enter search term");
        term = scan.nextLine();
        System.out.println("");

        // loop through to searching for numbers to be displayed
        subLoop: while( ( record = br.readLine() ) != null) {

            // instantiate the stringtokenizer class to be used to split details from the line been read from the file
            StringTokenizer st = new StringTokenizer(record,",");

            // check if record contains the search term
            if( record.toLowerCase().contains(term.toLowerCase()) ) {

                // convert boolean found to true when term searched for is found
                found = true;

                // display current contact being looped through info
                System.out.println("--------------------");
                System.out.println("|   CONTACT INFO   |");
                System.out.println("--------------------\n");

                System.out.println("Number: "+st.nextToken());
                System.out.println("First name: "+st.nextToken());
                System.out.println("Last name: "+st.nextToken());
                System.out.println("Address: "+st.nextToken());
                System.out.println("City: "+st.nextToken()+"\n");


                // option to move to next record or quit
                System.out.println("Show next contact? (y/n)");
                choice = scan.nextLine();

                switch (choice) {
                    case "y" :
                    case "Y" :
                        continue subLoop;
                    case "n" :
                    case "N" :
                        break subLoop;
                    default:
                        System.out.println("Wrong Input entered!");
                }
            }

        }

        // print error message if no contact was found
        if (!found) System.out.println("No contact matches "+term);

        // close buffered reader
        br.close();

        System.out.println("");
        System.out.println("Exiting Contact Search...");
    }

    // Display all contacts
    public static void displayContacts () throws IOException {

        System.out.println("---------------------------------------------------");
        System.out.println("|                  DISPLAY CONTACTS               |");
        System.out.println("---------------------------------------------------\n");

        // instantiates the scanner class to be used across the method
        Scanner scan = new Scanner(System.in);

        // declare variables to be used for operation
        String record,choice;

        // boolean variable to be used to check if any contact exists
        boolean found = false;

        // instantiates the file reader class and pass in file as argument
        FileReader fread = new FileReader("phone.txt");
        BufferedReader br = new BufferedReader( fread );

        // loop through all numbers to be displayed
        subLoop: while( ( record = br.readLine() ) != null) {

            // instantiate the stringtokenizer class to be used to split details from the line been read from the file
            StringTokenizer st = new StringTokenizer(record,",");

            // convert boolean found to true when contacts exist
            found = true;

            // display current contact being looped through info
            System.out.println("--------------------");
            System.out.println("|   CONTACT INFO   |");
            System.out.println("--------------------\n");

            System.out.println("Number: "+st.nextToken());
            System.out.println("First name: "+st.nextToken());
            System.out.println("Last name: "+st.nextToken());
            System.out.println("Address: "+st.nextToken());
            System.out.println("City: "+st.nextToken()+"\n");

            // option to move to next record or quit
            System.out.println("Show next contact? (y/n)");
            choice = scan.nextLine();

            switch (choice) {
                case "y" :
                case "Y" :
                    continue subLoop;
                case "n" :
                case "N" :
                    break subLoop;
                default:
                    System.out.println("Wrong Input entered!");
                    continue subLoop;
            }

        }

        // print error message if no contact was found
        if (!found) System.out.println("Contact List empty");

        // close buffered reader
        br.close();

        System.out.println("");
        System.out.println("Exiting Contacts Display...");
    }

    // Method to count contact
    public static void contactCount () throws IOException {

        System.out.println("------------------------------------------------------");
        System.out.println("|                  NUMBER OF CONTACTS                |");
        System.out.println("------------------------------------------------------\n");

        // instantiates the scanner class to be used across the method
        Scanner scan = new Scanner(System.in);

        // declare a count variable to be increased when contacts are found
        int count = 0;

        // instantiates the file reader class and pass in file as argument
        FileReader fread = new FileReader("phone.txt");
        BufferedReader br = new BufferedReader( fread );

        // loop through all numbers for counting
        subLoop: while( br.readLine() != null) {

            // increment count whenever a number is found
            count++;

        }

        // display number of contacts found
        System.out.println(count+" Contacts exists");
        br.close();

        System.out.println("");
        System.out.println("Exiting Contact Count...");
    }

    public static void main(String[] args) throws IOException {

        // instantiates the scanner class to be used across the method
        Scanner scan = new Scanner(System.in);

        // while loop to display option for user to pick from
        mainLoop: while(true) {
            System.out.println("-----------------------------------------------------");
            System.out.println("|                  E-PROJECT PHONEBOOK               |");
            System.out.println("-----------------------------------------------------\n");

            System.out.println("1 ===> Add contact \n" +
                    "2 ===> Delete contact \n" +
                    "3 ===> Modify contact \n" +
                    "4 ===> Search for contact \n" +
                    "5 ===> Display contacts \n" +
                    "6 ===> Get number of contacts \n" +
                    "7 ===> Exit");


            System.out.println("-----------------------------------------------------\n");

            // take in selected option
            System.out.println("Enter selection: ");
            int option = scan.nextInt();
            System.out.println("");

            // run methods as per user selection
            switch (option) {
                case 1:
                    addContact();
                    break;
                case 2:
                    deleteContact();
                    break;
                case 3:
                    modifyContact();
                    break;
                case 4:
                    searchContact();
                    break;
                case 5:
                    displayContacts();
                    break;
                case 6:
                    contactCount();
                    break;
                case 7:
                    System.out.println("Exiting Program....");
                    break mainLoop;
                default:
                    System.out.println("Invalid Input");
            }

            System.out.println("");
        }

    }
}
