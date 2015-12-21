package at.ac.univie.gradingapp.model;

import android.util.Log;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.io.Serializable;

/**
 * Created by Thomas on 05.11.2015.
 */
@Table(name = "Subject")
public class Subject extends Model {
    private static final String TAG = "Subject";

    @Column(onDelete = Column.ForeignKeyAction.CASCADE, name = "schoolClass")
    private SchoolClass schoolClass; //1 zu n Beziehung zur SchoolClass

    @Column(name ="subjectname")
    private String subjectname;

    public Subject(String subjectname, SchoolClass schoolclass) {
        super();
        this.subjectname = subjectname;
        this.schoolClass = schoolclass;
    }

    public Subject() { super(); }

    public String getSubjectname() {
        Log.d(TAG, "getsubjectname: ");
        return subjectname;
    }

    public void setSubjectname(String subjectname) {
        Log.d(TAG, "setsubjectname: " + subjectname);
        this.subjectname = subjectname;
    }

    public void setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }

    public SchoolClass getSchoolClass() { return schoolClass; }

    @Override
    public String toString() {
        return subjectname;
    }
}
