package edu.niu.cs.z1806979.assign5;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class InsertActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private DatabaseManager dbManager;
    private Spinner spinner;
    private String timeStr;
    private String dateStr;
    private DatePickerDialog picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new DatabaseManager(this);
        setContentView(R.layout.activity_insert);

        spinner = findViewById(R.id.spinnerTime);
        ArrayAdapter<CharSequence> timeAdapter = ArrayAdapter.createFromResource(
                this, R.array.time,
                android.R.layout.simple_spinner_item);
        spinner.setAdapter(timeAdapter);
        spinner.setOnItemSelectedListener(this);

        final EditText etDate = findViewById(R.id.editTextDate);
        etDate.setInputType(InputType.TYPE_NULL);
        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(InsertActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                dateStr = (monthOfYear + 1) + "/" + dayOfMonth + "/" + year;
                                etDate.setText((monthOfYear + 1) + "/" + dayOfMonth + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });
    }

    public void insert(View v) {
        EditText etName, etPrice, etWeather;
        String nameStr, priceStr, weatherStr;

        etName = findViewById(R.id.editTextType);
        etPrice = findViewById(R.id.editTextQuantity);
        etWeather = findViewById(R.id.editTextWeather);

        nameStr = etName.getText().toString();
        priceStr = etPrice.getText().toString();
        weatherStr = etWeather.getText().toString();

        // Insert into database here.
        try {
            double price = Double.parseDouble(priceStr);
            Bird bird = new Bird(0, nameStr, price, timeStr, dateStr, weatherStr);
            dbManager.insert(bird);
            Toast.makeText(this, "Bird inserted into the database." + nameStr, Toast.LENGTH_LONG).show();
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Bird not added, price error!", Toast.LENGTH_LONG).show();
        }
        etName.setText("");
        etPrice.setText("");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        timeStr = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void goBack(View v) {

        this.finish();
    }
}
