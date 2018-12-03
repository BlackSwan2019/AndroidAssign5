package edu.niu.cs.z1806979.assign5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;

public class DeleteActivity extends AppCompatActivity {
    private DatabaseManager databaseManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseManager = new DatabaseManager(this);
        updateView();
    }

    public void updateView() {
        ArrayList<Bird> birdies = databaseManager.selectAll();
        RelativeLayout layout = new RelativeLayout(this);
        ScrollView scrollView = new ScrollView(this);
        RadioGroup group = new RadioGroup(this);

        for (Bird bird : birdies) {
            RadioButton rb = new RadioButton(this);
            rb.setText(bird.toString());
            group.addView(rb);
        } // End for bird.

        // Event handler
        RadioButtonHandler rbh = new RadioButtonHandler();
        group.setOnCheckedChangeListener(rbh);
        Button backBtn = new Button(this);
        backBtn.setText("Back");
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteActivity.this.finish();
            }
        });

        scrollView.addView(group);
        layout.addView(scrollView);
        // Add back button at the bottom.
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        // Keep button at bottom of screen.
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.setMargins(0, 0, 0, 50);
        layout.addView(backBtn,  params);
        setContentView(layout);

    } // End updateView.

    private class  RadioButtonHandler implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            databaseManager.deleteById(checkedId);
            Toast.makeText(DeleteActivity.this, "Bird deleted", Toast.LENGTH_LONG).show();
            updateView();
        }
    }
}
