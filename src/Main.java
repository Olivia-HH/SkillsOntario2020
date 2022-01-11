
/*
Program Name: Main.java
Revision History: Olivia, Wed. June 17, 2020
Purpose: CRUD database for managing Skills Ontario contestants.
 */

import java.io.File; //for the file storing the school districts
import java.io.IOException; //to handle IOExceptions
import java.util.ArrayList; //various arraylists for schools, contestants, etc
import java.util.Comparator; //used to sort alphabetically and by score.
import java.util.Scanner; //scanner to take in userinput and read from file

class Main {

    private static File districts = new File("./districts.txt"); //file storing schools districts
    private static File category = new File("./categories.txt"); //file storing competition categories
    private static Scanner input = new Scanner(System.in); //to take in userinput

    private static ArrayList<String> categories = new ArrayList<>(); //arraylist storing the competition categories
    private static ArrayList<Contestant> contestants = new ArrayList<>(); //arraylist storing contestant objects
    private static ArrayList<String> schools = new ArrayList<>(); //arraylist storing school districts
    private static ArrayList<Contestant> results = new ArrayList<>(); //arraylist yielding search results

    public static void main(String[] args) {

        readDistricts(); //will get all the schools stored in an arraylist.
        for (int i = 0; i < schools.size(); i++) { //reads through all arraylist indexes
            schools.set(i, schools.get(i).toUpperCase());
            //takes the "i"th index, sets it to its existing value, but forces uppercase
        }


        readCategories(); //will get all the categories stored in an arraylist.
        for (int i = 0; i < categories.size(); i++) { //reads through all arraylist indexes
            categories.set(i, categories.get(i).toUpperCase());
            //takes the "i"th index, sets it to its existing value, but forces uppercase
        }

        //below are the options to be shown to the user upon running the program.
        showOptions();

        while (true) { //program will run infinitely until quit. (0 is entered)
            try {
                int choice = input.nextInt(); //takes user's choice

                if (choice == 0) { //if they pick 0
                    System.exit(0); //will end program
                }

                input.nextLine(); //clears leftover newline

                switch (choice) { //compares the user's choice with options
                    case 1: { //if 1 is chosen, add contestant
                        addContestant(); //runs addContestant code
                        break; //exits the switch

                    }
                    case 2: { //if 2 is chosen, removes a contestant 

                        //below prompts the user asking how to remove the contestant.
                        System.out.print("How would you like to remove?\n"
                                + "1 :: Last Name\n"
                                + "2 :: ID\nChoice :: ");

                        try {
                            int removeMethod = input.nextInt(); //user chooses removal method
                            input.nextLine(); //clears newline leftover

                            switch (removeMethod) { //compares their choice to see how they will remove
                                case 1: { //if 1, then search & remove by last name
                                    System.out.print("Please enter contestant last name :: "); //prompts for last name
                                    String lastName = input.nextLine(); //takes in user's last name
                                    removeLastName(lastName); //begins removal process using lastname
                                    break; //breaks from the switch

                                }
                                case 2: { //if 2, then search & remove by ID
                                    System.out.print("Please enter full contestant ID :: "); //prompts for ID
                                    String ID = input.nextLine(); //takes in user's ID
                                    removeID(ID); //begins removal process using ID
                                    break; //breaks from switch
                                }
                            }
                        } catch (Exception search) { //if an error occurs
                            System.out.println("Something went wrong removing contestants!"); //notifies user
                        }

                        break; //exits from switch

                    }
                    case 3: { //if 3 is chosen, searches for contestant

                        //below prompts the user how they would like to search
                        System.out.print("How would you like to search?\n"
                                + "1 :: Last Name\n"
                                + "2 :: Birthday (DD/MM/YYYY)\n"
                                + "3 :: School District\nChoice :: ");

                        try {
                            int searchMethod = input.nextInt(); //takes in user's choice
                            input.nextLine(); //clears newline leftover

                            switch (searchMethod) { //comapres user's search choice
                                case 1: { //if 1, they want to search by last name
                                    System.out.print("Please enter contestant last name :: "); //prompts for last name
                                    String lastName = input.nextLine(); //takes in user's last name
                                    searchLastName(lastName); //begins search process using last name
                                    break; //exits from switch

                                }
                                case 2: { //if 2, they want to search by birthday
                                    System.out.print("Please enter contestant birthday (DD/MM/YYYY) :: "); //prompts for birthday
                                    String birthday = input.nextLine(); //takes in user's birthday 
                                    searchBirthday(birthday); //begins search process using birthday
                                    break; //exits from switch

                                }
                                case 3: //if 3, they want to search by school district
                                    System.out.print("Please enter contestant district :: "); //prompts for school district
                                    String district = input.nextLine(); //takes in user's school district
                                    searchDistrict(district); //begins search process using school district
                                    break; //exits from switch
                            }

                        } catch (Exception search) { //if an error occurs
                            System.out.println("Something went wrong searching for contestants!"); //notifies user
                        }

                        break; //exits from switch

                    }
                    case 4: { //if 4 is chosen, overview of all contestants alphabetically

                        if (contestants.size() != 0) { //if it's not 0 contestants

                            try { //will attempt to sort
                                /**
                                 * this comparator compares students by their
                                 * last name, followed by their first name. its
                                 * use is to get a list of students sorted by
                                 * first name, last name.
                                 */

                                Comparator<Contestant> comparator = new Comparator<Contestant>() {
                                    @Override
                                    public int compare(Contestant c1, Contestant c2) {
                                        return c1.getLastFirst().compareTo(c2.getLastFirst());
                                    }
                                };
                                contestants.sort(comparator); //sorts contestants by lastname, firstname
                                for (int i = 0; i < contestants.size(); i++) { //goes through all contestants
                                    System.out.println(contestants.get(i).getLastFirst()); //prints out in order alphabetically
                                }

                            } catch (Exception sorting) { //if unable to sort
                                System.out.println("There was an error sorting!"); //notifies user
                            }

                        } else { //if there are 0 contestants
                            System.out.println("No contestants to display!"); //notifies user of empty
                        }

                        System.out.println(""); //blank line for formatting

                        break; //exits from switch
                    }

                    case 5: {
                        System.out.println("Which category would you like to see?");
                        for (int i = 0; i < categories.size(); i++) { //prints out a number corresponding for viewing
                            //below displays the category identification
                            System.out.println((i + 1) + " :: " + categories.get(i));
                        }
                        
                        System.out.print("Choice :: ");
                        try {
                            int cChoice = input.nextInt(); //takes in user's choice
                            input.nextLine(); //clears newline leftover
                            while (cChoice > categories.size()) { //if larger than the category pool
                                System.out.print("Invalid choice!\n" //notifies if invalid
                                    + "Please enter again :: "); //re-prompts
                                cChoice = input.nextInt(); //user enters new choice
                                input.nextLine(); //clears newline leftover
                            }
                            
                            String competitionType = categories.get(cChoice-1); //sets string for competition type
                            
                            results.clear(); //clears result pool
                            for (int i = 0; i < contestants.size(); i++) { //goes through the contestant arraylist
                            if (contestants.get(i).getCompetition().toUpperCase().matches(competitionType)) { //looks for all competitions
                                    results.add(contestants.get(i)); //if matches competition criteria, adds to result pool
                                }
                            }
                            
                            Comparator<Contestant> comparator = new Comparator<Contestant>() {
                                    @Override
                                    public int compare(Contestant c1, Contestant c2) {
                                        return Double.valueOf(c1.getScore()).compareTo(Double.valueOf(c2.getScore()));
                                    }
                                };
                            
                            results.sort(comparator); //sorts contestants by score
                                
                            /**
                            * note that comparator works in ascending order. we want descending order.
                            * therefore, when printing, we iterate backwards instead of forward.
                            * because we display starting from 1, we must start at the end of the
                            * arraylist (using size-1 as the index) and subtract three times.
                            */
                                
                            
                            if (results.size() == 0) { //if no contestants
                                System.out.println("No contestants in this category!\n"); //notifies user
                            } else if(results.size() < 3) { //if less than 3
                                for (int i = 0; i < results.size(); i++) { //goes through all contestants
                                    System.out.println(results.get(results.size()-(i+1)).getLastFirst() 
                                        + " :: " + results.get(results.size()-(i+1)).getScore()); //prints out top scores
                                }
                            } else {
                                for (int i = 0; i < 3; i++) { //goes through all contestants
                                    System.out.println(results.get(results.size()-(i+1)).getLastFirst() 
                                        + " :: " + results.get(results.size()-(i+1)).getScore()); //prints out top scores
                                }
                            }
                                
                                
                        } catch (Exception top) { //if error occurs
                            System.out.println("Something went wrong showing top contestants!"); //notifies user
                            showOptions(); //shows main menu
                        }
                        
                        System.out.println(""); //spacing

                        break; //exits from switch

                    }

                    case 6: { //if 6 is chosen, gives a small help snippet.
                        displayHelp(); //displays help message.
                        break; //exits from switch

                    }
                    default: { //if no valid choice (0-6) is chosen, disallowed
                        System.out.println("You didn't enter a valid choice!\n"); //notifies user of disallowed
                    }

                }

                showOptions(); //shows the "main menu" for the user after every action.

            } catch (Exception in) { //if a non-number is entered (two, !, etc)
                System.out.println("You didn't enter a valid choice!\n"); //notifies user disallowed

                showOptions(); //shows the "main menu" for the user.

            }
        }
    }

    private static void readDistricts() { //method to get districts from file to arraylist

        try { //will attempt to read file
            Scanner read = new Scanner(districts); //scanner reads from "districts" file.
            // System.out.println("Made it in!");
            while (read.hasNextLine()) { //while there is a next line to read from
                schools.add(read.nextLine()); //will add the schools to arraylist
            }
        } catch (IOException r) { //if an error occurs
            System.out.println("Something went wrong reading your districts file!\n"); //notifies user
        }
    }

    private static void readCategories() { //method to get categories from file to arraylist

        try { //will attempt to read file
            Scanner read = new Scanner(category); //scanner reads from "categories" file.
            while (read.hasNextLine()) { //while there is a next line to read from
                categories.add(read.nextLine()); //will add the categories to arraylist
            }
        } catch (IOException r) { //if an error occurs
            System.out.println("Something went wrong reading your categories file!\n"); //notifies user
        }
    }

    private static void addContestant() { //method to add contestant info
        System.out.print("Please input first name :: "); //prompt for first name
        String firstName = input.nextLine(); //user enters first name

        System.out.print("Please input last name :: "); //prompt for last name
        String lastName = input.nextLine(); //user enters last name

        System.out.print("Please input email :: "); //prompt for email
        String email = input.nextLine(); //user enters email

        System.out.print("Please input birthday (DD/MM/YYYY) :: "); //prompt for birthday
        String birthday = input.nextLine(); //user enters birthday

        System.out.print("Please input school district :: "); //prompt for school district
        String schoolDistrict = input.nextLine(); //user enters district

        while (!schools.contains(schoolDistrict.toUpperCase())) { //both forced to uppercase to compare
            System.out.print("Invalid school district!\nInput again :: "); //notifies and re-prompts
            schoolDistrict = input.nextLine(); //user enters new district
        }

        System.out.print("Please input competition category :: "); //prompt for category
        String competition = input.nextLine(); //user enters categoru

        while (!categories.contains(competition.toUpperCase())) { //both forced to uppercase to compare
            System.out.print("Invalid category!\nInput again :: "); //notifies and re-prompts
            competition = input.nextLine(); //user enters new category
        }

        System.out.print("Please input score :: "); //prompt for score
        double score = input.nextDouble(); //user enters score
        input.nextLine(); //clears newline leftover

        //makes a temporary contestant. is not added yet, must check for duplicate.
        Contestant temp = new Contestant(firstName, lastName, email, birthday, schoolDistrict, competition, score);

        boolean duplicate = false; //to compare if it's duplicate or not

        if (contestants.size() != 0) { // if there is not 0 contestants
            for (int i = 0; i < contestants.size(); i++) { //goes through contestant arraylist
                if (contestants.get(i).equals(temp)) { //checks for duplicate contestant. if found execute.
                    duplicate = true; //confirms duplicate contestant
                    break; //exits from switch
                }
            }

            if (duplicate == false) { // if not duplicate
                contestants.add(temp); //creates a contestant
                System.out.println("Success!\n"); //notifies user of success
            } else {
                System.out.println("Disallowed duplicate contestant.\n"); //ID's wont match but info will
                temp.fixNextID(); //nextID is static, so if we change it here, we change it everywhere.
            }

        } else { //if there are 0 contestants, don't check for duplicate
            contestants.add(temp); //creates a contestant
            System.out.println("Success!\n"); //notifies user of success
        }
    }

    private static final void displayHelp() { //will show a small help message.
        System.out.println("A number will appear beside each corresponding option.\n"
                + "Enter that number to continue to that menu, or perform that action.\n"
                + "Dulicate contestants are NOT allowed.\n"
                + "Files must be the same format as the provided text files.\n"
                + "Enter 0 to exit.\n");
    }

    private static void searchLastName(String lastName) { //method to search by last name
        if (contestants.size() != 0) { //if there are not 0 contestants
            results.clear(); //clears the current result arraylist

            for (int i = 0; i < contestants.size(); i++) { //goes through the contestant arraylist
                if (contestants.get(i).getLastName().matches(lastName)) { //looks for all last names
                    results.add(contestants.get(i)); //if matches criteria, adds to result pool
                }
            }

            if (results.size() == 0) { //if no contestants match criteria, then no results
                System.out.println("No contestants with this last name!\n"); //notifies user
            } else { //if a result is found
                for (int i = 0; i < results.size(); i++) { //prints out a number corresponding for viewing
                    //below displays the contestant first&last name for identification
                    System.out.println((i + 1) + " :: " + results.get(i).getLastName()
                            + ", " + results.get(i).getFirstName());
                }

                System.out.print("View which contestant? :: "); //prompts for which contestant #
                int sChoice = input.nextInt(); //takes in user's choice
                input.nextLine(); //clears newline leftover
                while (sChoice > results.size()) { //if larger than the result pool
                    System.out.print("Invalid choice!\n" //notifies if invalid
                            + "Please enter again :: "); //re-prompts
                    sChoice = input.nextInt(); //user enters new choice
                    input.nextLine(); //clears newline leftover
                }
                System.out.println(results.get(sChoice - 1).viewStudent() + "\n"); //prints out contestant info
            }

        } else { //if there are 0 contestants
            System.out.println("No contestants to display!\n"); //notifies user of empty
        }
    }

    private static void searchBirthday(String birthday) { //searches by birthday
        if (contestants.size() != 0) { //if there are not 0 contestants
            results.clear(); //clears result pool

            for (int i = 0; i < contestants.size(); i++) { //goes through the contestant arraylist
                if (contestants.get(i).getBirthday().matches(birthday)) { //looks for all birthdays
                    results.add(contestants.get(i)); //if matches criteria, adds to result pool
                }
            }

            if (results.size() == 0) { //if there are no results found
                System.out.println("No contestants with this birthday!\n"); //notifies user
            } else { //if a result is found
                for (int i = 0; i < results.size(); i++) { //prints out a number corresponding for viewing
                    //below displays the contestant first&last name for identification
                    System.out.println((i + 1) + " :: " + results.get(i).getLastName()
                            + ", " + results.get(i).getFirstName());
                }

                System.out.print("View which contestant? :: "); //prompts for which contestant #
                int sChoice = input.nextInt(); //takes in user's choice
                input.nextLine(); //clears newline leftover
                while (sChoice > results.size()) { //if larger than the result pool
                    System.out.print("Invalid choice!\n" //notifies if invalid
                            + "Please enter again :: "); //re-prompts
                    sChoice = input.nextInt(); //user enters new choice
                    input.nextLine(); //clears newline leftover
                }
                System.out.println("\n" + results.get(sChoice - 1).viewStudent() + "\n"); //user enters new choice
            }

        } else {  //user enters new choice
            System.out.println("No contestants to display!\n"); //notifies user of empty
        }

    }

    private static void searchDistrict(String district) { //searches by district
        if (contestants.size() != 0) { //if there are not 0 contestants
            results.clear(); //clears result pool

            for (int i = 0; i < contestants.size(); i++) { //goes through the contestant arraylist
                if (contestants.get(i).getSchoolDistrict().matches(district)) { //looks for all districts
                    results.add(contestants.get(i)); //if matches criteria, adds to result pool
                }
            }

            if (results.size() == 0) { //if there are no results found
                System.out.println("No contestants in this district!\n"); //notifies user
            } else { //if a result is found
                for (int i = 0; i < results.size(); i++) { //prints out a number corresponding for viewing

                    //below displays the contestant first&last name for identification
                    System.out.println((i + 1) + " :: " + results.get(i).getLastName()
                            + ", " + results.get(i).getFirstName());
                }

                System.out.print("View which contestant? :: "); //prompts for which contestant #
                int sChoice = input.nextInt(); //takes in user's choice
                input.nextLine(); //clears newline leftover
                while (sChoice > results.size()) { //if larger than the result pool
                    System.out.print("Invalid choice!\n" //notifies if invalid
                            + "Please enter again :: "); //re-prompts
                    sChoice = input.nextInt(); //user enters new choice
                    input.nextLine(); //clears newline leftover
                }
                System.out.println("\n" + results.get(sChoice - 1).viewStudent() + "\n");  //user enters new choice
            }

        } else {  //user enters new choice
            System.out.println("No contestants to display!\n"); //notifies user of empty
        }
    }

    private static void removeLastName(String lastName) { //removes contestant via last name
        if (contestants.size() != 0) { //if there are not 0 contestants

            for (int i = 0; i < contestants.size(); i++) { //searches for lastname
                if (contestants.get(i).getLastName().matches(lastName)) { //if that last name is found
                    contestants.remove(i); //removed the contestant with that last name
                    System.out.println("Success!\n"); //notifies user of success
                    break; //will exit loop upon finding matching last name.

                } else if (i == contestants.size() - 1) { //if a last name is not found
                    System.out.println("No contestants matching this last name!\n"); //notifies user of nonexistant surname
                }
            }

        } else { //if there are 0 existing contestants
            System.out.println("No contestants to display!\n"); //notifies user of 0 contestants
        }
    }

    private static void removeID(String ID) { //removes contestant via ID
        if (contestants.size() != 0) { //if there are not 0 contestants

            for (int i = 0; i < contestants.size(); i++) { //searches for ID
                if (contestants.get(i).getID().matches(ID)) { //if that ID is found
                    contestants.remove(i); //removed the contestant with that ID
                    System.out.println("Success!\n"); //notifies user of success
                    break; //will exit loop upon finding matching ID.

                } else if (i == contestants.size() - 1) { //if an ID is not found
                    System.out.println("No contestants matching this ID!\n"); //notifies user of nonexistant ID
                }
            }

        } else { //if there are 0 existing contestants
            System.out.println("No contestants to display!\n"); //notifies user of 0 contestants
        }
    }

    private static void showOptions() { //this is the "main menu" for the user.
        System.out.print("1 :: Add contestant\n"
                + "2 :: Remove contestant\n"
                + "3 :: Search for contestant\n"
                + "4 :: View all contestants alphabetically\n"
                + "5 :: Display top 3\n"
                + "6 :: Help and instructions\n"
                + "0 :: Quit\nChoice :: ");
    }
}
