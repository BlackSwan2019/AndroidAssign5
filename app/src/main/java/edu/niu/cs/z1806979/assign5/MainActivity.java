/**
 *    Program:        Bird sighting tracker.
 *
 *    Purpose:        Records bird sightings, keeping track of bird type, quantity seen, time of day,
 *                    date, time, weather, and temperature.
 *
 *    Programmers:    Ben Lane    z1806979
 *                    Jinhong Yao z1785700
 *
 *    Due Date:       December 5, 2018
 */

package edu.niu.cs.z1806979.assign5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


/**
 *    Class:  MainActivity
 *
 *    @author Ben Lane
 *    @author Jinhong Yao
 *
 *    Activity that allows user to click a button to go to a specific task concerning the bird sightings.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Method that initiates the activity to add a bird sighting.
     *
     * @param v The view of the current activity.
     *
     */
    public void goAdd(View v) {
        Intent i = new Intent(this, InsertActivity.class);
        startActivity(i);
    }

    /**
     * Method that initiates the activity to delete a sighting..
     *
     * @param v The view of the current activity.
     *
     */
    public void goDelete(View v) {
        Intent i = new Intent(this, DeleteActivity.class);
        startActivity(i);
    }

    /**
     * Method that initiates the activity to update a sighting..
     *
     * @param v The view of the current activity.
     *
     */
    public void goUpdate(View v) {
        Intent i = new Intent(this, UpdateActivity.class);
        startActivity(i);
    }
}
