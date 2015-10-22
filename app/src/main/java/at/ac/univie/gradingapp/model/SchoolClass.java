package at.ac.univie.gradingapp.model;

import android.util.Log;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marvin on 18.10.2015.
 */
@Table(name = "SchoolClass")
public class SchoolClass extends Model implements Serializable{ //extends Model --> Hier erbt die Klasse die möglichen Aktionen fürs SQL vom activeandroid // Serializable zum in Aktivities hin und her schieben
    private static final String TAG="SchoolClass"; //Fürs Logging - dann hat man den Tag drinnen
    @Column(name = "classname")
    private String classname;
    @Column(name = "headmaster")
    private Headmaster headmaster; //Klassenvorstand

    public SchoolClass(String classname, Headmaster headmaster) {
        super(); //ruft Konstruktor von Model auf
        this.classname = classname;
        this.headmaster = headmaster;
    }

    public SchoolClass() { //wird benötigt wenn man eine leere Klasse aufruft //wird fürs sqlite von activeandroid benötigt (Vorarbeit quasi)
        super();
    }

    public List<Student> getStudents(){ //Funktion returned alle Students
        return getMany(Student.class, "schoolClass");
    }

    public String getClassname() {
        return classname;
    }

    /**
     *     die Strings sind Private, deshalb get und set
     */
    public void setClassname(String classname) {
        Log.d(TAG,"Classname: "+classname);
        this.classname = classname;
    }

    public Headmaster getHeadmaster() {
        Log.d(TAG,"getHeadmaster: ");
        return headmaster;
    }

    public void setHeadmaster(Headmaster headmaster) {
        Log.d(TAG,"Headmaster: "+headmaster);
        this.headmaster = headmaster;
    }

    public static List<SchoolClass> getAllSchoolClasses() {
        return new Select().from (SchoolClass.class).execute();
    }

    @Override
    public String toString() {
        return classname;
    }

    public void deleteAllStudents() {
        //new Delete().from(Student.class).where("SchoolClass="+this.getId());
        for (Student student : this.getStudents()) {
            student.delete();
        }
    }
}