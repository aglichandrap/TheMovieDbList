package org.iblitzc0de.movielist.movie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import android.util.Log;


public class MovieContent {

	final private static String API_KEY = "?api_key=1b2f29d43bf2e4f3142530bc6929d341";
													
	final private static String BASE_URL = "http://api.themoviedb.org/3/discover/movie";
	final private static String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/";
	final private static String SMALL= "w92";
	final private static String LARGE ="w342";
	final private static String PROFILE = "w45";
	final private static String[] LIST_TYPE = {"popularity.desc", "vote_average.desc"};
	

	public static List<MovieItem> ITEMS = new ArrayList<MovieItem>();


	public static Map<String, MovieItem> ITEM_MAP = new HashMap<String, MovieItem>();
	
	



	public static void loadMovies(int type) {
		Log.e("constructor", "loading");
        try {
        	URL url = new URL(BASE_URL  + API_KEY + "&sort_by="+ LIST_TYPE[type]);
        	URLConnection urlConn = url.openConnection();
        	BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
        	JSONParser parser = new JSONParser();
        	JSONObject obj = (JSONObject) parser.parse(in);
        	JSONArray array = (JSONArray) obj.get("results");
        	if(array!=null)
        		for(int i=0; i<array.size(); i++) {
        			MovieItem movie = new MovieItem( (JSONObject) array.get(i) );        		
        			addItem(movie);
        		}
		} catch (ParseException e) {
			Log.e("ParseException", e.getMessage());
			e.printStackTrace();
		} catch (MalformedURLException e) {
			Log.e("MalformedURLException", e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			Log.e("IOException", e.getMessage());
			e.printStackTrace();
		}
	}

	public static void addItem(MovieItem item) {
		ITEMS.add(item);
		ITEM_MAP.put(item.id, item);
	}


	public static class MovieItem {
		public String id;
		public String title;
		public String rating;
		public Date date;
		public String releaseDate;
		public String posterURL;
		public String hugePosterURL;
		public String castURL;
		public String movieURL;
		public String posterPath;
		
		public JSONObject getObject() {
			JSONObject object = new JSONObject();
			object.put("id", id);
			object.put("title", title);
			object.put("vote_average", rating);
			object.put("release_date", releaseDate);
			object.put("poster_path", posterPath);
			
			return object;
		}

		public MovieItem(JSONObject movie) throws IOException, ParseException {
			this.id = String.valueOf(movie.get("id"));
			this.title = String.valueOf(movie.get("title"));
			this.rating = String.valueOf(movie.get("vote_average"));
			this.releaseDate = (String) movie.get("release_date");
			try {
				this.date = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(releaseDate);
			} catch (java.text.ParseException e) {
				Log.e("ParseException", e.getMessage());
			}
			this.posterPath = String.valueOf(movie.get("poster_path"));
			if(posterPath==null || posterPath.equals("null")) {
				this.posterURL = null;
				this.hugePosterURL = null;
			}
			else {
				this.posterURL = IMAGE_BASE_URL + SMALL + movie.get("poster_path") + API_KEY;
				this.hugePosterURL = IMAGE_BASE_URL + LARGE + movie.get("poster_path") + API_KEY;
			}
			this.movieURL = BASE_URL + API_KEY+ this.id;
	
		}

		@Override
		public String toString() {
			return title + " " + releaseDate.substring(0,4);
		}
	}
	
	public static class Cast {
		public String name;
		public String character;
		public String profileURL;
		public Cast(JSONObject cast) {
			this.name = String.valueOf(cast.get("name"));
			this.character = String.valueOf(cast.get("character"));
			String path = String.valueOf(cast.get("profile_path"));
			if(path == null || path.equals("null"))
				this.profileURL = null;
			else
				this.profileURL = IMAGE_BASE_URL + PROFILE + path + API_KEY;
			
		}
	}
	
	public static class MovieDetails {
		public String overview;

		public List<String> languages;

		
		public String getLanguages() {
			return languages.toString().substring(1, languages.toString().length()-1);
		}
		public MovieDetails(JSONObject movieDetails) {
			this.overview = String.valueOf(movieDetails.get("overview"));

			
			      		
			
			
		}
	

	
	}

	public static void reset() {
		// TODO Auto-generated method stub
		ITEMS.clear();
		ITEM_MAP.clear();
	}
}
