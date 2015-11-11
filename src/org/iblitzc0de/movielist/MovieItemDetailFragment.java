package org.iblitzc0de.movielist;

import org.iblitzc0de.movielist.movie.MovieContent;
import org.iblitzc0de.movielist.tasks.DownloadDetailsTask;
import org.iblitzc0de.movielist.tasks.DownloadImageTask;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.iblitzc0de.movielist.R;



public class MovieItemDetailFragment extends Fragment {

	public static final String ARG_ITEM_ID = "item_id";


	private MovieContent.MovieItem mItem;


	public MovieItemDetailFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getArguments().containsKey(ARG_ITEM_ID)) {

			mItem = MovieContent.ITEM_MAP.get(getArguments().getString(
					ARG_ITEM_ID));
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_movieitem_detail,
				container, false);
		
		
		
		if (mItem != null) {
			RelativeLayout layout = (RelativeLayout) rootView.findViewById(R.id.movieitem_detail);
			((TextView)layout.findViewById(R.id.movieName)).setText(mItem.title);
			((TextView)layout.findViewById(R.id.movieYear)).setText(mItem.releaseDate);
			((TextView)layout.findViewById(R.id.movieRating)).setText(mItem.rating);
			ImageView imageView = (ImageView)layout.findViewById(R.id.moviePoster);
			if(mItem.hugePosterURL != null)
				new DownloadImageTask(imageView).execute(mItem.hugePosterURL);
			new DownloadDetailsTask(layout, inflater, container).execute(mItem);
		}

		return rootView;
	}	
	
}
