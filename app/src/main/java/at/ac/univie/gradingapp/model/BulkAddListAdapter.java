package at.ac.univie.gradingapp.model;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.text.Format;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import at.ac.univie.gardingapp.R;

/**
 * Created by Thomas on 28.12.2015.
 */
public class BulkAddListAdapter extends ArrayAdapter<Map.Entry<Student, Double>>
{
    private Context context;
    private int layoutResourceId;
    //private List<Student> items;
    //private ArrayList<Map.Entry<Student, Double>> studentGradeKvp;
    private ArrayList<Map.Entry<Student, Double>> items;

    //public BulkAddListAdapter(Context context, int layoutResourceId, List<Student> items ) {
    public BulkAddListAdapter(Context context, int layoutResourceId, ArrayList<Map.Entry<Student, Double>> items ) {
        super(context, layoutResourceId, items);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.items = items;

        //studentGradeKvp = new ArrayList<Map.Entry<Student, Double>>();
        //for (Student student : items) {
        //    studentGradeKvp.add(new AbstractMap.SimpleEntry<Student, Double>(student, Double.NaN));
        //}
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent ) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View row = convertView;
        row = inflater.inflate(layoutResourceId, parent, false);

        final long idPointVerification = getItemId(position);

            final StudentNameGrade student;
            student = new StudentNameGrade();
            student.student = items.get(position);
            student.name = (TextView) row.findViewById(R.id.student_name);
            student.value = (EditText) row.findViewById(R.id.student_grade);
            //student.name.setTag(student.student.getKey().getLastname() + " " + student.student.getKey().getFirstname());
            student.name.setText(student.student.getKey().getLastname() + " " + student.student.getKey().getFirstname());

            if(student.student.getValue().isNaN())
                student.value.setText("");
            else
                student.value.setText(student.student.getValue().toString());

            //row.setTag(student);

        EditText grade = (EditText)row.findViewById(R.id.student_grade);
        grade.addTextChangedListener(new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {      }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {     }

        @Override
        public void afterTextChanged(Editable s) {

            // This is the worst workaround i've ever seen
            // but it seems to work. So fuck it!
            Double d = Double.valueOf(s.toString());
            Map.Entry<Student, Double> temp = items.get((int)idPointVerification);
            temp.setValue(d);
            items.set((int)idPointVerification, temp);
        }
    });

            return row;
    }

    private void setupItem(StudentNameGrade student) {
        //student.name.setText(student.student.getLastname() + " " + student.student.getFirstname());
        student.name.setText(student.student.getKey().getLastname() + " " + student.student.getKey().getFirstname());
        student.value.setText(""); // this field is empty for entering the student's new grade
    }

    public static class StudentNameGrade {
        //Student student;
        Map.Entry<Student, Double> student;
        TextView name;
        EditText value;
    }

    public ArrayList<Map.Entry<Student, Double>> getStudentGrades(){
        return items;
    }

}
