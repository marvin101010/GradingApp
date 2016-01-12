package at.ac.univie.gradingapp.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import at.ac.univie.gardingapp.R;
import at.ac.univie.gradingapp.model.BulkAddListAdapter;
import at.ac.univie.gradingapp.model.SchoolClass;
import at.ac.univie.gradingapp.model.Student;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BulkAddFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BulkAddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BulkAddFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private SchoolClass mSchoolClass;
    private BulkAddListAdapter mAdapter;
    private View mView;
    private OnFragmentInteractionListener mListener;
    private ListView mListView;
    private Button mBulkAddSaveButton;

    public BulkAddFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment BulkAddFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BulkAddFragment newInstance() {
        BulkAddFragment fragment = new BulkAddFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    public static BulkAddFragment newInstance(SchoolClass schoolClass) {
        BulkAddFragment fragment = new BulkAddFragment();
        Bundle args = new Bundle();
        args.putSerializable("schoolclasskey", schoolClass);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mSchoolClass = (SchoolClass) getArguments().getSerializable("schoolclasskey");
        }

        if (mSchoolClass != null && !mSchoolClass.getSubjects().isEmpty() && !mSchoolClass.getStudents().isEmpty()) {

            // Get all students of this school class for bulk add

            List<Student> students = mSchoolClass.getStudents();

            ArrayList<Map.Entry<Student, Double>> studentGradeKvp = new ArrayList<Map.Entry<Student, Double>>();
            for (Student student : students) {
                studentGradeKvp.add(new AbstractMap.SimpleEntry<Student, Double>(student, Double.NaN));
            }
            mAdapter = new BulkAddListAdapter(getActivity(), R.layout.bulk_add_list_item , studentGradeKvp);

        }
        else {
            // TODO: Hinweis ausgeben, dass noch keine Fächer angelegt sind
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_bulk_add2, container, false);

        mListView = (ListView)mView.findViewById(R.id.bulkAddListView);
        mListView.setAdapter(mAdapter);
        mListView.setItemsCanFocus(true);
        mBulkAddSaveButton = (Button) mView.findViewById(R.id.bulkAddSaveButton);
        mBulkAddSaveButton.setOnClickListener(bulkAddSaveButtonClickListener);

        return mView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
        void onFragmentInteraction_BulkAddSave(ArrayList<Map.Entry<Student, Double>> list);
    }

    private View.OnClickListener bulkAddSaveButtonClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(mListener != null && mSchoolClass != null && mListView != null && mAdapter != null) {
                mListener.onFragmentInteraction_BulkAddSave(mAdapter.getStudentGrades());
            }
        }
    };
}
