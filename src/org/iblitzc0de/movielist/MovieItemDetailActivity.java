package org.iblitzc0de.movielist;

import org.iblitzc0de.movielist.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;


public class MovieItemDetailActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_movieitem_detail);

		
		getActionBar().setDisplayHomeAsUpEnabled(true);


		if (savedInstanceState == null) {
	
			Bundle arguments = new Bundle();
			arguments.putString(
					MovieItemDetailFragment.ARG_ITEM_ID,
					getIntent().getStringExtra(
							MovieItemDetailFragment.ARG_ITEM_ID));
			MovieItemDetailFragment fragment = new MovieItemDetailFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
					.add(R.id.movieitem_detail_container, fragment).commit();
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:

			NavUtils.navigateUpTo(this, new Intent(this,
					MovieItemListActivity.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
