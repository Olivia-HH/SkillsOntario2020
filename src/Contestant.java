
/*
Program Name: Contestant.java
Revision History: Olivia, Wed. June 17, 2020
Purpose: Objects that store contestant information.
*/

import java.util.Objects; //to create student objects.

class Contestant {
    private static int nextID = 1; //will increment per contestant object created.
    private String ID; //stores formatted ID
    private int tempID; //stores pre-formatted ID
    
    private String firstName; //stores first name
    private String lastName; //stores last name
    private String email; //stores email
    private String schoolDistrict; //stores school district
    private String birthday; //stores birthday
    private String competition; //stores category
    private double score; //stores score

    
    //below is the constructor. Makes a contestant with given info
    public Contestant(String firstName, String lastName, String email, String birthday, String schoolDistrict, String competition, double score) {
        this.firstName = firstName; //makes object's firstname equal input
        this.lastName = lastName; //makes object's lastname equal input
        this.email = email; //makes object's email equal input
        this.birthday = birthday; //makes object's birthday equal input
        this.schoolDistrict = schoolDistrict; //makes object's district equal input
        this.competition = competition; //makes object's category equal input
        this.score = score; //makes object's score equal input
        
        tempID = nextID; //sets a temp ID for the current value, will be formatted
        nextID++; //moves on to the next ID
        
        ID = String.format("%08d", tempID); //will format string to be 8 digits
        
    }

 
    /*******************************************************************
     * Below is IDE-generated code that will handle the comparison of 
     * student objects. This is to prevent identical objects from being
     * created. As long as there is one field that is different, then it
     * will be allowed to be created. However, if they are all the same,
     * the new duplicate student object will not be created.
     * Note that this ignores ID, so it will only look at user input info.
     ******************************************************************/
    
    
    @Override
    public int hashCode() { int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.firstName);
        hash = 89 * hash + Objects.hashCode(this.lastName);
        hash = 89 * hash + Objects.hashCode(this.email);
        hash = 89 * hash + Objects.hashCode(this.schoolDistrict);
        hash = 89 * hash + Objects.hashCode(this.birthday);
        hash = 89 * hash + Objects.hashCode(this.competition);
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.score) ^ (Double.doubleToLongBits(this.score) >>> 32));
        return hash;
    }

    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Contestant other = (Contestant) obj;
        if (Double.doubleToLongBits(this.score) != Double.doubleToLongBits(other.score)) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.schoolDistrict, other.schoolDistrict)) {
            return false;
        }
        if (!Objects.equals(this.birthday, other.birthday)) {
            return false;
        }
        if (!Objects.equals(this.competition, other.competition)) {
            return false;
        }
        return true;
    }

     /***************************
     * End of IDE-generated code.
     ***************************/
    
    public void fixNextID() {
        //if adding a duplicate student, this brings down the next ID.
        nextID--; //subtracts one from the current ID value
    }
    
    public String getCompetition() { //gets and returns category
        return competition; //returns category
    }

    public void setCompetition(String competition) { //sets category from userinput
        this.competition = competition; //sets category to userinput value
    }

    public double getScore() { //gets and returns score
        return score; //returns score
    }

    public void setScore(double score) { //sets score from userinput
        this.score = score; //sets score to userinput value
    }
    
    public String getID() { //gets and returns ID
        return ID; //returns ID
    }

    /**
     * Note that there is no "set" for ID.
     * it is automatically generated upon creation of
     * a student object by my constructor code.
    */
    
    public String getFirstName() { //gets and returns firstName
        return firstName; //returns firstName
    }

    public void setFirstName(String firstName) { //sets firstName from userinput
        this.firstName = firstName; //sets firstName to userinput value
    }
 
    public String getLastName() { //gets and returns ID
        return lastName; //returns ID
    }

    public void setLastName(String lastName) { //sets lastName from userinput
        this.lastName = lastName; //sets lastName to userinput value
    }

    public String getEmail() { //gets and returns email
        return email; //returns email
    }

    public void setEmail(String email) { //sets email from userinput
        this.email = email; //sets email to userinput value
    }

    public String getSchoolDistrict() { //gets and returns district
        return schoolDistrict; //returns district
    }

    public void setSchool(String school) { //sets district from userinput
        this.schoolDistrict = schoolDistrict; //sets district to userinput value
    }

    public String getBirthday() { //gets and returns birthday
        return birthday; //returns birthday
    }

    public void setBirthday(String birthday) { //sets birthday from userinput
        this.birthday = birthday; //sets birthday to userinput value
    }
     
    public String getLastFirst() { //gets and returns firstLast
        return (lastName + ", " + firstName); //returns firstName
    }
    
    public String viewStudent() { //outputs information on student object
        return ("Name: " + firstName + " " + lastName + "\n" //first & last name
            + "Email: " + email + "\n" //email
            + "School District: " + schoolDistrict + "\n" //district
            + "Birthday: " + birthday + "\n" //birthday
            + "ID: " + ID + "\n" //id 
            + "Competition: " + competition + "\n" //competition
            + "Score: " + score); //score
    }
    
}