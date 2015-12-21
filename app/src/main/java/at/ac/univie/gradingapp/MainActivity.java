package at.ac.univie.gradingapp;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import at.ac.univie.gardingapp.R;
import at.ac.univie.gradingapp.fragment.SchoolClassFragment;
import at.ac.univie.gradingapp.fragment.SchoolclasslistFragment;
import at.ac.univie.gradingapp.fragment.StudentClassFragment;
import at.ac.univie.gradingapp.fragment.StudentListFragment;
import at.ac.univie.gradingapp.fragment.SubjectListFragment;
import at.ac.univie.gradingapp.model.Headmaster;
import at.ac.univie.gradingapp.model.SchoolClass;
import at.ac.univie.gradingapp.model.Student;
import at.ac.univie.gradingapp.model.Subject;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,SchoolclasslistFragment.OnFragmentInteractionListener, SubjectListFragment.OnFragmentInteractionListener, StudentListFragment.OnFragmentInteractionListener { //Drawer und Schoolclasslist als Starscreen
    //1
    ///2
    private static final String TAG = "main activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) { //Was passiert wenn die MainActivity aufgerufen wird
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportFragmentManager().beginTransaction().add(R.id.fragment_placeholder, SchoolclasslistFragment.newInstance()).addToBackStack("").commit();//immer Support nutzen, damit man die älteren Versionen unterstützt, Aktivity ist durch Fragments immer gleich
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_schoolclass) {
            Log.d(TAG, "irgendwas");
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_placeholder, SchoolClassFragment.newInstance()).addToBackStack("").commit();
            // Neue Klasse anlegen
        } else if (id == R.id.nav_schoolclasslist) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_placeholder, SchoolclasslistFragment.newInstance()).addToBackStack("").commit();
        } else if (id == R.id.nav_student) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_placeholder, StudentClassFragment.newInstance()).addToBackStack("").commit();
        } else if (id == R.id.nav_studentlist) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_placeholder, StudentListFragment.newInstance()).addToBackStack("").commit();
        }
//        else if (id == R.id.nav_subjectlist) {
//            // Fächerliste anzeigen
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_placeholder, SubjectListFragment.newInstance()).commit();
//        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void subjectClicked(Subject selectedSubject) {
        // TODO: check if getSchoolClass is working
        Log.d("MainActivity", "selected class: " + selectedSubject.getSchoolClass().getClassname());
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_placeholder, StudentListFragment.newInstance(selectedSubject.getSchoolClass())).addToBackStack("").commit();
    }

    @Override
    public void studentclicked(SchoolClass schoolClassPicker) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_placeholder, StudentListFragment.newInstance(schoolClassPicker)).addToBackStack("").commit();

    }

    @Override
    public void schoolClassClicked(SchoolClass selectedSchoolClass) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_placeholder, SubjectListFragment.newInstance(selectedSchoolClass)).addToBackStack("").commit();

    }

    @Override
    public void onAddNewGradeClicked(Student selectedStudent) {
        Log.d("MainActivity/NewGrade", "selected student: " + selectedStudent.getLastname());
        // TODO: ELISABETH: Fragment für "Noten anzeigen" öffnen!!
        // DES GEHT AN MARVIN AN SCHEISSDRECK AUN! IS NUR FIA DIE ELISABETH BESTIMMT!
        // FINGER WEG!
    }

    @Override
    public void onViewGradesClicked(Student selectedStudent) {
        Log.d("MainActivity/ViewGrade", "selected student: " + selectedStudent.getLastname());
        // TODO: ELISABETH: Fragment für "Noten eingeben" öffnen!!
        // DES GEHT AN MARVIN AN SCHEISSDRECK AUN! IS NUR FIA DIE ELISABETH BESTIMMT!
        // FINGER WEG!
    }
}

