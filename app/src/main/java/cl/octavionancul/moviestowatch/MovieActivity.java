package cl.octavionancul.moviestowatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.TextView;

import cl.octavionancul.moviestowatch.models.Movie;

import static cl.octavionancul.moviestowatch.MainActivity.MOVIE_ID;

public class MovieActivity extends AppCompatActivity {
    CheckBox watched ;

    private Movie movie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        watched= findViewById(R.id.watchedCb);
        long idMovie = getIntent().getLongExtra(MOVIE_ID, 0);

        movie = Movie.findById(Movie.class,idMovie);
        getSupportActionBar().setTitle(movie.getName());

        Log.d("moviefragid", movie.getName());

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(watched.isChecked()){
         movie.setWatched(watched.isChecked());
         movie.save();
        }

    }
}
