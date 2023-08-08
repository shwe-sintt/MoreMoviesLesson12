package sg.edu.rp.c346.id22003619.movienight;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Arrays;

public class ThirdActivity extends AppCompatActivity {
EditText etID,etTitle,etYear, etGenre;
Button btnUpdate, btnDelete,btnCancel;
Spinner spinner;
Movie selectedMovie;
    String selectedRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        etID=findViewById(R.id.etID);
        etTitle=findViewById(R.id.etTitle);
        etYear=findViewById(R.id.etYear);
        etGenre=findViewById(R.id.etGenre);
        btnCancel=findViewById(R.id.btnCancel);
        btnDelete=findViewById(R.id.btnDelete);
        btnUpdate=findViewById(R.id.btnUpdate);
        spinner=findViewById(R.id.spinner);
        selectedMovie = (Movie) getIntent().getSerializableExtra("selectedMovie");

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        selectedRating = sharedPreferences.getString("selectedRating", "");
        String[] strRatings = {"G", "PG", "PG13", "NC16", "M18", "R21"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, strRatings);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        int ratingPosition = Arrays.asList(strRatings).indexOf(selectedRating);
        if (ratingPosition >= 0) {
            spinner.setSelection(ratingPosition);
        }
        etID.setText(String.valueOf(selectedMovie.getId()));
        etTitle.setText(selectedMovie.getTitle());
        etYear.setText(String.valueOf(selectedMovie.getYear()));
        etGenre.setText(selectedMovie.getGenre());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString();
                String genre = etGenre.getText().toString();
                int year = Integer.parseInt(etYear.getText().toString());
                String rating = spinner.getSelectedItem().toString();

                selectedMovie.setTitle(title);
                selectedMovie.setGenre(genre);
                selectedMovie.setYear(year);
                selectedMovie.setRating(rating);

                DBHelper dbHelper = new DBHelper(ThirdActivity.this);
                int rowsAffected = dbHelper.updateMovie(selectedMovie);
                if (rowsAffected > 0) {
                    Toast.makeText(ThirdActivity.this, "Movie updated successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ThirdActivity.this, "Error updating movie", Toast.LENGTH_SHORT).show();
                }

                dbHelper.close();
                Intent intent1= new Intent(ThirdActivity.this,SecondActivity.class);
                startActivity(intent1);
            }
        });
//        btnDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder mybuilder=new AlertDialog.Builder(ThirdActivity.this);
//                mybuilder.setTitle("Danger");
//                mybuilder.setMessage("Are you sure you want to delete the movie "+selectedMovie.getTitle());
//                mybuilder.setCancelable(false);
//                mybuilder.setPositiveButton("Cancel", null);
//                mybuilder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        DBHelper dbHelper = new DBHelper(ThirdActivity.this);
//                        int rowsAffected = dbHelper.deleteMovie(selectedMovie.getId());
//                        if (rowsAffected > 0) {
//                            Toast.makeText(ThirdActivity.this, "Movie deleted successfully", Toast.LENGTH_SHORT).show();
//                            finish();
//                        } else {
//                            Toast.makeText(ThirdActivity.this, "Error deleting movie", Toast.LENGTH_SHORT).show();
//                        }
//                        dbHelper.close();
//                        Intent intent1= new Intent(ThirdActivity.this,SecondActivity.class);
//                        startActivity(intent1);
//                    }
//                });
//                AlertDialog myDialog= mybuilder.create();
//                myDialog.show();
//            }
//        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mybuilder=new AlertDialog.Builder(ThirdActivity.this);
                mybuilder.setTitle("Danger");
                mybuilder.setMessage("Are you sure you want to delete the movie "+selectedMovie.getTitle());
                mybuilder.setCancelable(false);
                mybuilder.setPositiveButton("Cancel", null);
                mybuilder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBHelper dbHelper = new DBHelper(ThirdActivity.this);
                        int rowsAffected = dbHelper.deleteMovie(selectedMovie.getId());
                        if (rowsAffected > 0) {
                            Toast.makeText(ThirdActivity.this, "Movie deleted successfully", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(ThirdActivity.this, "Error deleting movie", Toast.LENGTH_SHORT).show();
                        }
                        dbHelper.close();
                        Intent intent1= new Intent(ThirdActivity.this,SecondActivity.class);
                        startActivity(intent1);
                    }
                });
                AlertDialog myDialog= mybuilder.create();
                myDialog.show();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mybuilder=new AlertDialog.Builder(ThirdActivity.this);
                mybuilder.setTitle("Danger");
                mybuilder.setMessage("Are you sure you want to discard the changes "+selectedMovie.getTitle());
                mybuilder.setCancelable(false);
                mybuilder.setPositiveButton("DO NOT DISCARD", null);
                mybuilder.setNegativeButton("DISCARD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(ThirdActivity.this, SecondActivity.class);
                        startActivity(intent);
                    }
                });
                AlertDialog myDialog= mybuilder.create();
                myDialog.show();

            }
        });
    }
}