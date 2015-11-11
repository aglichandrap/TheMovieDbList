package org.iblitzc0de.movielist.tasks;

import org.iblitzc0de.movielist.MovieItemListActivity;
import org.iblitzc0de.movielist.movie.MovieContent;
import org.iblitzc0de.movielist.widget.ArrayAdapter;

import android.os.AsyncTask;

public class DownloadMovieTask extends AsyncTask<Integer, Void, Void> {
	ArrayAdapter arrayAdapter;
	MovieItemListActivity activity;
	public DownloadMovieTask(ArrayAdapter arrayAdapter, MovieItemListActivity activity) {
		this.arrayAdapter = arrayAdapter;
		this.activity = activity;
	}
	@Override
	protected Void doInBackground(Integer... params) {
		MovieContent.loadMovies(params[0]);
		return null;
	}
	@Override
	protected void onPostExecute(Void v) {
		arrayAdapter.refresh();
        arrayAdapter.notifyDataSetChanged();
        activity.finishedDownload();
    }
}
