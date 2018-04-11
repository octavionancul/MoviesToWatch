package cl.octavionancul.moviestowatch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import cl.octavionancul.moviestowatch.models.Movie;

public class MainActivity extends AppCompatActivity {

    private List<Movie> movieList;
    public static final String MOVIE_ID = "cl.octavionancul.moviestowatch.KEY.MOVIE_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText nameEt = findViewById(R.id.nameEt);
        Button save = findViewById(R.id.saveBtn);
        Button showLast = findViewById(R.id.showLastBtn);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEt.getText().toString();

                if (name.trim().length() > 0) {
                    Movie movie = new Movie(name, false);
                    movie.save();
                    movieList = getMovies();
                    Log.d("movie", "Movie guardada:" + movieList.size());
                } else {
                    Toast.makeText(MainActivity.this, "Name vacio", Toast.LENGTH_SHORT).show();
                }

            }
        });

        showLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int lenght = movieList.size();
                if (lenght > 0) {
                    Movie movie = movieList.get(movieList.size() - 1);

                    Intent intent = new Intent(MainActivity.this, MovieActivity.class);
                    intent.putExtra(MOVIE_ID, movie.getId());
                    startActivity(intent);
                    Log.d("movie", String.valueOf(movie.getId()));
                } else {
                    Toast.makeText(MainActivity.this, "No existen peliculas", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        movieList = getMovies();
    }

    private List<Movie> getMovies() {
        List<Movie> movies = Movie.find(Movie.class, "watched= ?", "0");

        return movies;
    }
}
