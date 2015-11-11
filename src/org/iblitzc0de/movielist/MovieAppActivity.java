package org.iblitzc0de.movielist;

import org.iblitzc0de.movielist.movie.MovieContent;

import org.iblitzc0de.movielist.R;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MovieAppActivity extends ListActivity {
	
	String[] options = {"Most Popular", "Highest Rated"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(this, R.layout.main_layout, options));
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent newActivity = new Intent(this, MovieItemListActivity.class);
		newActivity.putExtra(Intent.EXTRA_TITLE, options[position]);
		newActivity.putExtra("movie_type", position);
		MovieContent.reset();
        startActivity(newActivity);
	}
}
