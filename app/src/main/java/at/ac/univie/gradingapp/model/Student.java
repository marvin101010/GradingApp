package at.ac.univie.gradingapp.model;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.Date;

/**
 * Created by Marvin on 18.10.2015.
 */
@Table(name= "Student")
public class Student extends Person {
    @Column(onDelete = Column.ForeignKeyAction.CASCADE, name = "schoolClass")
    private SchoolClass schoolClass; //1 zu n Beziehung zur SchoolClass

    public Student(String lastname, String firstname, Date birthdate, SchoolClass schoolClass) {
        super(lastname, firstname, birthdate);
        this.schoolClass = schoolClass;
    }

    public Student() {
        super();
    }

}
