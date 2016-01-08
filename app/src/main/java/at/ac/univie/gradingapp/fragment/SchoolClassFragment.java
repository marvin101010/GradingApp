package at.ac.univie.gradingapp.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import java.util.List;
import at.ac.univie.gardingapp.R;
import at.ac.univie.gradingapp.model.SchoolClass;
import at.ac.univie.gradingapp.model.Student;

public class SchoolClassFragment extends Fragment {
    private static final String TAG = "SchoolClassFragment";
    private SchoolClass mSchoolClass;
    private View mRootView;

    private View.OnClickListener saveClickListener=new View.OnClickListener() { // Listener "hören" drauf wenn etwas in dem Fragment passiert. Dieser Listener heißt saveClickListener
        @Override
        public void onClick(View v) { //Der "saveClickListener" reagiert "onClick"
            EditText className = (EditText) mRootView.findViewById(R.id.classNameEditText);  //(EditText) --> man "casted" die view, damit wir bescheid wissen was gecasted wird gibt man die Art an (EditText)
            if (mSchoolClass == null) {
                mSchoolClass = new SchoolClass(); //Erstellt neue Klasse wenn ich auf speichern klicke
            }
            mSchoolClass.setClassname(className.getText().toString()); //füllt Namen in die neue Klasse
            mSchoolClass.save();
            /*Student teststudent = new Student(
                    "Foo","bar",new Date(),mSchoolClass
            );
            teststudent.save();
            */
            /*
            Debugg fürs Speichern der Schule
             */
            List<SchoolClass> bla = SchoolClass.getAllSchoolClasses();
            for (SchoolClass schoolClass:bla){
                Log.d(TAG, schoolClass.getClassname());
                for (Student student:schoolClass.getStudents()) {
                    Log.d(TAG,"Student: " + student);
                }
            }
        }
    };

    public static SchoolClassFragment newInstance() {
        SchoolClassFragment fragment = new SchoolClassFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public SchoolClassFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override //Wird von Android automatisch aufgerufen, deshalb override)
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRootView = view; //speichert die View ab
        Button classNameButton = (Button) view.findViewById(R.id.schoolClassSaveButton);
        classNameButton.setOnClickListener(saveClickListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_school_class, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mRootView = null; // Um Speicherlecks zu vermeiden muss man die Views destroyen wenn man sie speichert (Referenzen auf Aussen)
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
