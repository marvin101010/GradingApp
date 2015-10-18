package at.ac.univie.gradingapp.model;

import android.util.Log;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.Date;

/**
 * Created by Marvin on 18.10.2015.
 */
@Table(name = "Person")
public class Person extends Model{
    private static final String TAG = "Person";
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "birthdate")
    private Date birthdate;

    public Person(String lastname, String firstname, Date birthdate) {
        super();
        this.lastname = lastname;
        this.firstname = firstname;
        this.birthdate = birthdate;
    }

    public Person() {
        super();
    }

    public String getLastname() {
        Log.d(TAG, "getLastname: ");
        return lastname;
    }

    public void setLastname(String lastname) {
        Log.d(TAG, "setLastname: "+lastname);
        this.lastname = lastname;
    }

    public String getFirstname() {
        Log.d(TAG, "getFirstname: ");
        return firstname;
    }

    public void setFirstname(String firstname) {
        Log.d(TAG, "setFirstname: "+firstname);
        this.firstname = firstname;
    }

    public Date getBirthdate() {
        Log.d(TAG, "getBirthdate: ");
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        Log.d(TAG, "setBirthdate: "+birthdate);
        this.birthdate = birthdate;
    }

    @Override //Override für die Headmaster "ID" von der Klasse, bei + wird immer toString aufgerufen, deshalb der Override
    public String toString() {
        return firstname+" "+lastname;
    }
}
