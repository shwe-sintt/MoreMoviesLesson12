package sg.edu.rp.c346.id22003619.movienight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnInsert;
    Button btnShow;
    EditText etTitle;
    EditText etGenre;
    EditText etYear;
    Spinner spinner;
    String selectedRating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnInsert=findViewById(R.id.btnInsert);
        btnShow=findViewById(R.id.btnShow);
        etTitle=findViewById(R.id.etTitle);
        etGenre=findViewById(R.id.etGenre);
        etYear=findViewById(R.id.etYear);
        spinner=findViewById(R.id.spinner);

        String[] strRatings = {"G", "PG", "PG13", "NC16", "M18", "R21"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, strRatings);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedRating = strRatings[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                }
        });
        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);
                String strTitle= etTitle.getText().toString();
                String strGenre= etGenre.getText().toString();
                int intYear= Integer.parseInt(etYear.getText().toString());
                String strRating= selectedRating;
                db.insertMovie(strTitle, strGenre,intYear,strRating);
                Toast.makeText(MainActivity.this, "Movie inserted", Toast.LENGTH_SHORT).show();

                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("title", strTitle);
                editor.putString("genre", strGenre);
                editor.putInt("year", intYear);
                editor.putString("selectedRating", selectedRating);
                editor.apply();

            }
        });
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,
                        SecondActivity.class);
                startActivity(i);
            }
        });
    }
    
}