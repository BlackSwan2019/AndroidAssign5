package edu.niu.cs.z1806979.assign5;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {
    private DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_update);
        databaseManager = new DatabaseManager(this);
        updateView();
    }

    public void updateView() {
        ArrayList<Bird> birds = databaseManager.selectAll();
        ScrollView scrollView = new ScrollView(this);
        GridLayout gridLayout = new GridLayout(this);

        gridLayout.setRowCount(birds.size());
        gridLayout.setColumnCount(4);

        // Create the arrays of components.
        TextView[] ids = new TextView[birds.size()];
        EditText[][] namesAndPrices = new EditText[birds.size()][3];
        Button[] btns = new Button[birds.size()];
        ButtonHandler bh = new ButtonHandler();
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);

        int width = size.x;
        int i = 0;

        for (Bird bird : birds) {
            ids[i] = new TextView(this);
            ids[i].setGravity(Gravity.CENTER);
            ids[i].setText("" + bird.getId());
            // Create EditText for name and price.
            namesAndPrices[i][0] = new EditText(this);
            namesAndPrices[i][1] = new EditText(this);
            namesAndPrices[i][2] = new EditText(this);
            namesAndPrices[i][0].setText(bird.getType());
            namesAndPrices[i][1].setText("" + bird.getQuantity());
            namesAndPrices[i][1].setInputType(InputType.TYPE_CLASS_NUMBER);
            namesAndPrices[i][2].setText("" + bird.getDate());
            namesAndPrices[i][0].setId(10 * bird.getId());
            namesAndPrices[i][1].setId(10 * bird.getId() + 1);
            namesAndPrices[i][2].setId(10 * bird.getId() + 2);
            btns[i] = new Button(this);
            btns[i].setText("Update");
            btns[i].setId(bird.getId());
            btns[i].setOnClickListener(bh);
            // Add each element to the grid.
            // 10 % for ID, 40% for the name, 15% for the price, and 35% for the button.
            gridLayout.addView(namesAndPrices[i][0], (int) (width * .30), ViewGroup.LayoutParams.WRAP_CONTENT);
            gridLayout.addView(namesAndPrices[i][1], (int) (width * .20), ViewGroup.LayoutParams.WRAP_CONTENT);
            gridLayout.addView(namesAndPrices[i][2], (int) (width * .30), ViewGroup.LayoutParams.WRAP_CONTENT);
            gridLayout.addView(btns[i], (int) (width * .20), ViewGroup.LayoutParams.WRAP_CONTENT);
            i++;
        } // End for bird.

        scrollView.addView(gridLayout);
        setContentView(scrollView);
    }

    private class ButtonHandler implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            int birdId = v.getId();

            EditText nameET = findViewById(10 * birdId);
            EditText priceET = findViewById(10 * birdId + 1);
            EditText weatherET = findViewById(10 * birdId + 2);

            String name = nameET.getText().toString();
            String priceStr = priceET.getText().toString();
            String weatherStr = weatherET.getText().toString();
            // Update the database.
            try {
                double price = Double.parseDouble(priceStr);
                databaseManager.update(birdId, name, price, weatherStr);
                Toast.makeText(UpdateActivity.this, "Bird updated", Toast.LENGTH_LONG).show();
                updateView();
            } catch (NumberFormatException e) {
                Toast.makeText(UpdateActivity.this, "Price is not valid.", Toast.LENGTH_LONG).show();
            }
        }
    }
}
