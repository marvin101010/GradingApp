package at.ac.univie.gradingapp.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;
import at.ac.univie.gardingapp.R;
import at.ac.univie.gradingapp.model.SchoolClass;
import at.ac.univie.gradingapp.model.Student;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StudentClassFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StudentClassFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StudentClassFragment extends Fragment {


    private OnFragmentInteractionListener mListener;
    private View mRootView;
    private Student mStudent;
    private static final String TAG = "StudentClassFragment";
    private SchoolClass mSchoolclass;

    private View.OnClickListener saveClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText lastName = (EditText) mRootView.findViewById(R.id.studentLastnameEdit);  //(EditText) --> man "casted" die view, damit wir bescheid wissen was gecasted wird gibt man die Art an (EditText)
            if (mStudent == null) {
                mStudent = new Student(); //Erstellt neuen Student wenn ich auf speichern klicke
            }
            EditText firstName = (EditText) mRootView.findViewById(R.id.studentFirstnameEdit);
            EditText birthDate = (EditText) mRootView.findViewById(R.id.studentBirthdateEdit);
            Spinner schoolClass = (Spinner) mRootView.findViewById(R.id.schooClassSpinner);
            mStudent.setLastname(lastName.getText().toString());
            mStudent.setFirstname(firstName.getText().toString());
            mStudent.setSchoolClass((SchoolClass) schoolClass.getItemAtPosition(schoolClass.getSelectedItemPosition())); //(SchoolClass) in Klammer zum Casten - Damit java weiß was ich übergebe
            //mStudent.setBirthdate(birthDate.get
            mStudent.save();
            /*Student teststudent = new Student(
                    "Foo","bar",new Date(),mSchoolClass
            );
            teststudent.save();
            */
            /*
            Debugg fürs Speichern der Schule
             */
            List<Student> bla = Student.getAllStudents();
            for (Student Student:bla){
                Log.d(TAG, Student.getLastname());
                // for (Student student:schoolClass.getStudents()) {
                //   Log.d(TAG,"Student: " + student);
                // }
            }
        }
    };

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment StudentClassFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StudentClassFragment newInstance() {
        StudentClassFragment fragment = new StudentClassFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public StudentClassFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_class, container, false);
    }

    @Override //Wird von Android automatisch aufgerufen, deshalb override)
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRootView = view; //speichert die View ab
        Button studentClassSaveButton = (Button) view.findViewById(R.id.studentClassSaveButton);
        studentClassSaveButton.setOnClickListener(saveClickListener);
        Spinner schoolClassSpinner = (Spinner) view.findViewById(R.id.schooClassSpinner); //Java sagen was er sich holen soll
        ArrayAdapter<SchoolClass> mAdapter = new ArrayAdapter<SchoolClass>(getActivity(), // den Adapter definieren
                android.R.layout.simple_list_item_1, android.R.id.text1, SchoolClass.getAllSchoolClasses());
        schoolClassSpinner.setAdapter(mAdapter); //den Spinner mit Adapter füllen

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mRootView = null; // Um Speicherlecks zu vermeiden muss man die Views destroyen wenn man sie speichert (Referenzen auf Aussen)
    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
