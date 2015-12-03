package it.polimi.stopit.model;


import java.net.URL;

/**
 * Created by matteo on 03/12/15.
 */
public class User {

    private String ID;
    private String name,surname;
    private URL profilePic;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public URL getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(URL profilePic) {
        this.profilePic = profilePic;
    }
}
