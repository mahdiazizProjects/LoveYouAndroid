package asheghetam.app;

import com.etsy.android.grid.StaggeredGridView;
import com.github.amlcurran.showcaseview.OnShowcaseEventListener;
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ViewTarget;
import asheghetam.app.R;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import asheghetam.adapters.Main_Adapter;
import asheghetam.others.TypefaceSingelton;

public class main_list extends Activity {


	public static final int RESULT_LOAD_IMAGE=100;

	//	// (arbitrary) request code for the purchase flow
	//	static final int RC_REQUEST = ;

	// The helper object
	private Main_Adapter adapter;
	private StaggeredGridView gridview;
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_grid);
		getActionBar().hide();
		gridview=(StaggeredGridView) findViewById(R.id.grid_main);
		ImageButton contact_us=(ImageButton) findViewById(R.id.ac_contactus);
		View view=findViewById(R.id.cir_btn);
		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				{
					Intent intent = new Intent();
					intent.setType("image/*");
					intent.setAction(Intent.ACTION_GET_CONTENT);
					intent.addCategory(Intent.CATEGORY_OPENABLE);
					startActivityForResult(intent, RESULT_LOAD_IMAGE);
				}


			}
		});
		TextView title=(TextView) findViewById(R.id.title);
		title.setTypeface(TypefaceSingelton.getFontNumber(getApplicationContext()));
		ImageButton ac_back=(ImageButton) findViewById(R.id.ac_back);
		contact_us.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
						"mailto","Robinappgroup@gmail.com", null));
				i.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.sendusemail));
				startActivity(Intent.createChooser(i, getString(R.string.sendusemail)));

			}
		});
		ac_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				onBackPressed();

			}
		});
		adapter=new Main_Adapter(this);
		gridview.setAdapter(adapter);
		gridview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				goto_detail(position);

			}
		});
		final SharedPreferences prefs=getSharedPreferences("norooz",MODE_PRIVATE);
		if(prefs.getBoolean("first_time", true))
		{
			new ShowcaseView.Builder(this)
			.setTarget(new ViewTarget(R.id.ac_contactus, this)).setShowcaseEventListener(new OnShowcaseEventListener() {

				@Override
				public void onShowcaseViewShow(ShowcaseView showcaseView) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onShowcaseViewHide(ShowcaseView showcaseView) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onShowcaseViewDidHide(ShowcaseView showcaseView) {
					new ShowcaseView.Builder(main_list.this)
					.setTarget(new ViewTarget(R.id.cir_btn, main_list.this)).setContentTitle(getString(R.string.galtitle))
					.setContentText(getString(R.string.gall)).build();

				}
			})
			.setContentTitle(getString(R.string.contitle))
			.setContentText(getString(R.string.contactus))
			.build();
			prefs.edit().putBoolean("first_time", false).commit();
		}
	}

	private void goto_detail(int position)
	{
		Bundle bu=new Bundle();
		bu.putInt("position", position);
		Intent myIntent = new Intent(main_list.this,Picture_Detail.class);
		myIntent.putExtras(bu);
		startActivity(myIntent);
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode==RESULT_LOAD_IMAGE&&resultCode==RESULT_OK)
		{
			Uri uri = data.getData();
			if(uri!=null)
			{
				Bundle bu=new Bundle();
				bu.putString("uri", uri.toString());
				Intent myIntent = new Intent(main_list.this,Picture_Detail.class);
				myIntent.putExtras(bu);
				startActivity(myIntent);
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
}
