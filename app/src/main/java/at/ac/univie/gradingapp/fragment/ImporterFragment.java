package at.ac.univie.gradingapp.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import at.ac.univie.gardingapp.R;
import at.ac.univie.gradingapp.MainActivity;
import at.ac.univie.gradingapp.model.SchoolClass;
import at.ac.univie.gradingapp.model.Student;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ImporterFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ImporterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ImporterFragment extends Fragment {


    private OnFragmentInteractionListener mListener;
    private View mRootView;
    private Student mStudent;
    private static final String TAG = "StudentClassFragment";
    private SchoolClass mSchoolclass;
    Button btn;
    int year_x, month_x, day_x;
    static final int DIALOG_ID = 0;
    String firstName, lastName, birthDate;



    public static ImporterFragment newInstance() {
        ImporterFragment fragment = new ImporterFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public ImporterFragment() {
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
        return inflater.inflate(R.layout.fragment_importer, container, false);
    }

    @Override //Wird von Android automatisch aufgerufen, deshalb override)
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRootView = view; //speichert die View ab
        TextView tv = (TextView) view.findViewById(R.id.text_view);
        String state = Environment.getExternalStorageState();


//Schulklasse Auswählen mit einem Spinner

        Spinner schoolClassSpinner = (Spinner) view.findViewById(R.id.schooClassSpinner); //Java sagen was er sich holen soll
        ArrayAdapter<SchoolClass> mAdapter = new ArrayAdapter<SchoolClass>(getActivity(), // den Adapter definieren
                android.R.layout.simple_list_item_1, android.R.id.text1, SchoolClass.getAllSchoolClasses());
        schoolClassSpinner.setAdapter(mAdapter); //den Spinner mit Adapter füllen

//File Einlesen

            BufferedReader reader = null;
            try {
                File file = Environment.getExternalStorageDirectory();
                File textFile = new File(file.getAbsolutePath()+File.separator + "file.txt");
                reader = new BufferedReader(new FileReader(textFile));
                StringBuilder textBuilder = new StringBuilder();
                String line;
                while((line = reader.readLine()) != null) {
                    textBuilder.append(line);
                    textBuilder.append("\n");
                }

                tv.setText(textBuilder);

            } catch (FileNotFoundException e) {
                // TODO: handle exception
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            finally{
                if(reader != null){
                    try {
                        reader.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }

        }

    // TODO: Rename method, update argument and hook method into UI event

    private View.OnClickListener saveClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Spinner schoolClass = (Spinner) mRootView.findViewById(R.id.schooClassSpinner);
            if (mStudent == null) {
                mStudent = new Student(); //Erstellt neuen Student wenn ich auf speichern klicke
            }
            BufferedReader reader = null;
            try {
                File file = Environment.getExternalStorageDirectory();
                File textFile = new File(file.getAbsolutePath()+File.separator + "file.txt");
                Scanner read = new Scanner(new FileReader(textFile));
                read.useDelimiter("");
                StringBuilder textBuilder = new StringBuilder();
                while(read.hasNext()) {
                    firstName = read.nextLine();
                    lastName = read.nextLine();
                    birthDate = read.nextLine();
                    mStudent.setLastname(lastName.toString());
                    mStudent.setFirstname(firstName.toString());
                    mStudent.setBirthdate(birthDate.toString());
                }

            } catch (FileNotFoundException e) {
                // TODO: handle exception
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            mStudent.setSchoolClass((SchoolClass) schoolClass.getItemAtPosition(schoolClass.getSelectedItemPosition())); //(SchoolClass) in Klammer zum Casten - Damit java weiß was ich übergebe
            mStudent.save();

        }
    };
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
