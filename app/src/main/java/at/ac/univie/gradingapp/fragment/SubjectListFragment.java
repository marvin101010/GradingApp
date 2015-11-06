package at.ac.univie.gradingapp.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import at.ac.univie.gardingapp.R;
import at.ac.univie.gradingapp.model.SchoolClass;
import at.ac.univie.gradingapp.model.Subject;
/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SubjectListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SubjectListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SubjectListFragment extends Fragment implements AbsListView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static final String TAG = "SubjectListFragment";
    private View mRootView;
    private View mSubjectView;
    private AbsListView mListView;
    private ListAdapter mAdapter;
    private SchoolClass mSchoolClass;
    private ArrayList<Subject> mSubjects = new ArrayList<Subject>();

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SubjectListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SubjectListFragment newInstance() {
        SubjectListFragment fragment = new SubjectListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public static SubjectListFragment newInstance(SchoolClass schoolClass) {
        SubjectListFragment fragment = new SubjectListFragment();
        Bundle args = new Bundle();
        args.putSerializable("schoolclasskey", schoolClass);
        fragment.setArguments(args);
        return fragment;
    }

    public SubjectListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mSchoolClass = (SchoolClass) getArguments().getSerializable("schoolclasskey");
        }

        if (mSchoolClass != null && !mSchoolClass.getSubjects().isEmpty()) {
            mAdapter = new ArrayAdapter<Subject>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, mSchoolClass.getSubjects());
        }
        else {
            // TODO: Hinweis ausgeben, dass noch keine Fächer angelegt sind
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mSubjectView = inflater.inflate(R.layout.fragment_subject_list, container, false);

        // Set the adapter
        mListView = (ListView) mSubjectView.findViewById(R.id.subjectListView);
        ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);

        final Button addSubjectButton = (Button) mSubjectView.findViewById(R.id.subjectAddButton);
        final LinearLayout saveLayout = (LinearLayout) mSubjectView.findViewById(R.id.saveLayout);
        addSubjectButton.setOnClickListener(new View.OnClickListener()
        {
            //@Override
            public void onClick(View v)
            {
                Log.d(TAG, "Fach hinzufügen Button click");
                saveLayout.setVisibility(View.VISIBLE); // make textfield and save button visible
                addSubjectButton.setVisibility(View.GONE); // hide "add new subject" button
            }
        });

        Button saveSubjectButton = (Button) mSubjectView.findViewById(R.id.saveSubjectButton);
        saveSubjectButton.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {
                Log.d(TAG, "Fach speichern Button click");
                EditText subjectName = (EditText) mRootView.findViewById(R.id.newSubjectText);
                Subject newSubject = new Subject(subjectName.getText().toString(), mSchoolClass);
                newSubject.save();
                subjectName.setText("");

                // make savelayout invisible and make add new subject button visible
                saveLayout.setVisibility(View.GONE); // make textfield and save button visible
                addSubjectButton.setVisibility(View.VISIBLE); // hide "add new subject" button

                // Fächerliste nach Eintragung aktualisieren
                if (mSchoolClass != null && !mSchoolClass.getSubjects().isEmpty()) {
                    mAdapter = new ArrayAdapter<Subject>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, mSchoolClass.getSubjects());

                    if (mListView != null) {
                        ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);
                    }
                }
            }
        });

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);
        mListView.setOnItemLongClickListener(this);

        return mSubjectView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRootView = view; //speichert die View ab

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
    public void onDestroyView() {
        super.onDestroyView();
        mRootView = null;
        mSubjectView = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Subject selectedSubject = (Subject) parent.getItemAtPosition(position);
        Log.d(TAG, "selected subject: " + selectedSubject.getSubjectname());
        mListener.subjectClicked(selectedSubject);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        Subject subjectToRemove = (Subject) parent.getItemAtPosition(position);
        subjectToRemove.delete();

        mAdapter = new ArrayAdapter<Subject>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, subjectToRemove.getSchoolClass().getSubjects());
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
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);

        public void subjectClicked(Subject selectedClass);
    }

}
