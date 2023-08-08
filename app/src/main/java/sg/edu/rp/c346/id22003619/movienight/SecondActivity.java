package sg.edu.rp.c346.id22003619.movienight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
ListView lv;
Button btn;
CustomAdapter adapter;
Spinner spinnerRatings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        lv=findViewById(R.id.lv);
        btn=findViewById(R.id.btnPG13);
        spinnerRatings=findViewById(R.id.spinnerRatings);

        Spinner spinnerRatings = findViewById(R.id.spinnerRatings);
        ArrayAdapter<CharSequence> ratingsAdapter = ArrayAdapter.createFromResource(this, R.array.movie_ratings, android.R.layout.simple_spinner_item);
        ratingsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRatings.setAdapter(ratingsAdapter);

        adapter=new CustomAdapter(this,R.layout.row,new ArrayList<Movie>());
        lv.setAdapter(adapter);

        DBHelper dbHelper=new DBHelper(this);
        ArrayList<Movie> movies=dbHelper.getMovies();
        adapter.clear();
        adapter.addAll(movies);
        adapter.notifyDataSetChanged();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper = new DBHelper(SecondActivity.this);
                ArrayList<Movie> pg13Movies = dbHelper.getPG13("PG13");
                adapter.clear();
                adapter.addAll(pg13Movies);
                adapter.notifyDataSetChanged();
            }
        });
        spinnerRatings.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedRating = parent.getItemAtPosition(position).toString();

                // Get the movies based on the selected rating from the DBHelper
                if ("ALL".equals(selectedRating)) {
                    // Show all movies in the list view
                    DBHelper dbHelper = new DBHelper(SecondActivity.this);
                    ArrayList<Movie> allMovies = dbHelper.getMovies();
                    adapter.clear();
                    adapter.addAll(allMovies);
                    adapter.notifyDataSetChanged();
                } else {
                    // Get the movies based on the selected rating from the DBHelper
                    DBHelper dbHelper = new DBHelper(SecondActivity.this);
                    ArrayList<Movie> filteredMovies = dbHelper.getMoviesByRating(selectedRating);
                    adapter.clear();
                    adapter.addAll(filteredMovies);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie selectedMovie = adapter.getItem(position);
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                intent.putExtra("selectedMovie", selectedMovie);
                startActivity(intent);
            }
        });

    }
}