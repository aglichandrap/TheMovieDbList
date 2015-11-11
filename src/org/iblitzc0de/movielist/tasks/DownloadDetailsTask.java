package org.iblitzc0de.movielist.tasks;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.iblitzc0de.movielist.movie.MovieContent;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.RelativeLayout;

import android.widget.TextView;

import org.iblitzc0de.movielist.R;

public class DownloadDetailsTask extends AsyncTask<MovieContent.MovieItem, Void, MovieContent.MovieDetails> {
	RelativeLayout relativeLayout;
	LayoutInflater inflater;
	ViewGroup container;

	public DownloadDetailsTask(RelativeLayout relativeLayout,
			LayoutInflater inflater, ViewGroup container) {
		this.relativeLayout = relativeLayout;
		this.inflater = inflater;
		this.container = container;
	}
	
	@Override
	public void onPreExecute() {
		relativeLayout.findViewById(R.id.movieDetailRelativeLayout).setVisibility(View.GONE);
	}

	protected MovieContent.MovieDetails doInBackground(MovieContent.MovieItem... movies) {
		MovieContent.MovieItem movie = movies[0];
		JSONObject movieDetails = null;
		try {
			URLConnection urlConn = new URL(movie.movieURL).openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
			JSONParser parser = new JSONParser();
			movieDetails = (JSONObject) parser.parse(in);

		} catch (Exception e) {
			Log.e("Error", e.getMessage());
			e.printStackTrace();
		}
		try {
			URLConnection urlConn = new URL(movie.castURL).openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject) parser.parse(in);

		} catch (Exception e) {
			Log.e("Error", e.getMessage());
			e.printStackTrace();
		}
		return new MovieContent.MovieDetails(movieDetails);
	}
	@Override
	protected void onPostExecute(MovieContent.MovieDetails movieDetails) {
		
		((TextView) relativeLayout.findViewById(R.id.movieOverview)).setText(movieDetails.overview);

	
		
			}
		

	}
