package Messenger;

public class Profile {
    private String firstName, lastName, userName, password;

    public Profile(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public Profile(String firstName, String lastName, String userName, String password) {
        if(!firstName.equalsIgnoreCase("null")) {
            this.firstName = firstName;
        }
        if(!lastName.equalsIgnoreCase("null")) {
            this.lastName = lastName;
        }
        this.userName = userName;
        this.password = password;
    }

    public String firstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String lastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String userName() {
        return userName;
    }

    public void newUserName(String userName) {
        this.userName = userName;
    }

    public char[] password() {
        return toCharArray(password);
    }

    public void newPassword(String password) {
        this.password = password;
    }

    public String toString() {
        String str = userName + "/" + password + "/";
        if(firstName != null && firstName.length() > 0) {
            str += (firstName + "/");
        }
        if(lastName != null && lastName.length() > 0) {
            str += (lastName + "/");
        }
        return str;
    }

    private static char[] toCharArray(String str) {
        char[] c = new char[str.length()];
        for(int i = 0; i < c.length; i++) {
            c[i] = str.charAt(i);
        }
        return c;
    }
}