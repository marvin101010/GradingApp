package at.ac.univie.gradingapp.model;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Marvin on 18.10.2015.
 */
@Table(name= "Student")
public class Student extends Person {
    @Column(onDelete = Column.ForeignKeyAction.CASCADE, name = "schoolClass")
    private SchoolClass schoolClass; //1 zu n Beziehung zur SchoolClass
    private ArrayList<Achievement> achievements;

    public Student(String lastname, String firstname, Date birthdate, SchoolClass schoolClass) {
        super(lastname, firstname, birthdate);
        this.schoolClass = schoolClass;
        achievements = new ArrayList<Achievement>();
    }

    public Student(String lastname, String firstname, Date birthdate, SchoolClass schoolClass, ArrayList<Achievement> achs) {
        super(lastname, firstname, birthdate);
        this.schoolClass = schoolClass;
        achievements = new ArrayList<Achievement>(achs);
    }

    public Student() {
        super();
    }

    public void setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }

    public SchoolClass getSchoolClass() { return this.schoolClass; }

    public void setAchievements(ArrayList<Achievement> ach) { this.achievements = new ArrayList<Achievement>(ach); }
    public ArrayList<Achievement> getAchievements() { return this.achievements; }

    public void addAchievement(Achievement ach) {
        if(achievements != null)
            achievements.add(ach);
        else
        {
            achievements = new ArrayList<Achievement>();
            achievements.add(ach);
        }
    };

    // TODO: removeAchievment & removeAllAchievements?? --> noch nicht bei den Projektzielen dabei oder?
}



