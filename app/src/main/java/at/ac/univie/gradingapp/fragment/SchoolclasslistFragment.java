package at.ac.univie.gradingapp.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import at.ac.univie.gardingapp.R;
import at.ac.univie.gradingapp.MainActivity;
import at.ac.univie.gradingapp.model.SchoolClass;
import at.ac.univie.gradingapp.model.Student;
import at.ac.univie.gradingapp.model.Subject;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class SchoolclasslistFragment extends Fragment implements AbsListView.OnItemClickListener, AdapterView.OnItemLongClickListener {


    private static final String TAG = "SchoolclasslistFragment";
    private OnFragmentInteractionListener mListener;
    private View mRootView;
    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private ListAdapter mAdapter;

    // TODO: Rename and change types of parameters
    public static SchoolclasslistFragment newInstance() {
        SchoolclasslistFragment fragment = new SchoolclasslistFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SchoolclasslistFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO: Change Adapter to display your content
        mAdapter = new ArrayAdapter<SchoolClass>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, SchoolClass.getAllSchoolClasses());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schoolclasslist, container, false);

        // Set the adapter
        mListView = (AbsListView) view.findViewById(android.R.id.list);
        ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);
        mListView.setOnItemLongClickListener(this);

        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRootView = view; //Verweis auf die MainView
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        // (eigentlich müssen hier die Fächer der Schulklasse anzeigen, dann kommt man erst zu den Schülern)
        // hier muss der wechsel zu den subjects stattfinden
        SchoolClass selectedSchoolClass = (SchoolClass) parent.getItemAtPosition(position);
        Log.d(TAG, "selected SchoolClass: " + selectedSchoolClass.getClassname());

        //switch to subjectlist fragment
        mListener.schoolClassClicked(selectedSchoolClass);
    }

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        SchoolClass schoolClassPicker = (SchoolClass) parent.getItemAtPosition(position);

        schoolClassPicker.deleteAllStudents(); // schüler und fächer werden gelöscht
        schoolClassPicker.delete();
        mAdapter = new ArrayAdapter<SchoolClass>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, SchoolClass.getAllSchoolClasses());
        mListView.setAdapter(mAdapter);
        return false;
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
        void studentclicked(SchoolClass schoolClassToDelete);
        void schoolClassClicked(SchoolClass selectedSchoolClass);
    }



}
