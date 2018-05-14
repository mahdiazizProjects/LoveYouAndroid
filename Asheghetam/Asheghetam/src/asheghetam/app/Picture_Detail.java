package asheghetam.app;
import asheghetam.app.R;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.almeros.android.multitouch.MoveGestureDetector;
import com.almeros.android.multitouch.RotateGestureDetector;
import com.capricorn.ArcMenu;
import com.chiralcode.colorpicker.ColorPickerDialog;
import com.chiralcode.colorpicker.ColorPickerDialog.OnColorSelectedListener;
import com.github.amlcurran.showcaseview.OnShowcaseEventListener;
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ViewTarget;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.OnScanCompletedListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import asheghetam.others.FontPickerDialog;
import asheghetam.others.TypefaceSingelton;
import asheghetam.others.FontPickerDialog.OnFontSelectedListener;
import asheghetam.widgets.AspectImageView;


public class Picture_Detail extends android.app.Activity {
	private float mRotationDegrees = 0.f;
	private RotateGestureDetector mRotateDetector;
	private MoveGestureDetector mMoveDetector;
//	private boolean is_text_in_touch_mode=false;
//	private boolean is_zoom_mode=false;
	private static String dirname="/Pictures";
	final static float STEP = 200;
	float mRatio = 1.0f;
    private float mFocusX = 0.f;
    private float mFocusY = 0.f;  
	int mBaseDist;
	float mBaseRatio;
	float fontsize = 13;
	EditText tv;
	ImageButton ac_contactus;
	private View parentView;
//	private static final int[] ITEM_DRAWABLES = {R.drawable.select, R.drawable.dropper,R.drawable.preview ,
//		R.drawable.fontres,R.drawable.edit };
	private static final int[] ITEM_DRAWABLES = {R.drawable.dropper,R.drawable.edit,R.drawable.preview ,
		};
	
	private static final int[]Gallery2= new int[] 
			{
		R.drawable.a_tiny,
		R.drawable.b_tiny,
		R.drawable.c_tiny,
		R.drawable.d_tiny,
		R.drawable.e_tiny,
		R.drawable.f_tiny,
		R.drawable.g_tiny,
		R.drawable.h_tiny,
		R.drawable.i_tiny,
		R.drawable.j_tiny,
		R.drawable.k_tiny,
		R.drawable.l_tiny,
		R.drawable.m_tiny,
		R.drawable.n_tiny,
		R.drawable.o_tiny,
		R.drawable.p_tiny,
		R.drawable.q_tiny,
		R.drawable.r_tiny,
		R.drawable.s_tiny,
		R.drawable.t_tiny,
		R.drawable.u_tiny,
		R.drawable.v_tiny,
		R.drawable.w_tiny,
		R.drawable.x_tiny,
		R.drawable.y_tiny,
		R.drawable.z_tiny,
		R.drawable.aa_tiny,
		R.drawable.bb_tiny,
		R.drawable.cc_tiny,
		R.drawable.dd_tiny,
		R.drawable.ee_tiny,
		R.drawable.ff_tiny,
		R.drawable.gg_tiny,
		R.drawable.hh_tiny,
		R.drawable.ii_tiny,
		R.drawable.jj_tiny,
		R.drawable.kk_tiny,
		R.drawable.ll_tiny,
		R.drawable.mm_tiny,
		R.drawable.nn_tiny,
		R.drawable.oo_tiny,
		R.drawable.pp_tiny,
		R.drawable.qq_tiny,
		R.drawable.rr_tiny,
		R.drawable.ss_tiny,
		R.drawable.tt_tiny
			};
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_list);
		AspectImageView img=(AspectImageView) findViewById(R.id.image_aks);
		ArcMenu arcMenu=(ArcMenu) findViewById(R.id.arc);
		getActionBar().hide();
		View top=findViewById(R.id.top);
		TextView title=(TextView) top.findViewById(R.id.title);
		title.setTypeface(TypefaceSingelton.getFontNumber(getApplicationContext()));
		ImageButton ac_back=(ImageButton) top.findViewById(R.id.ac_back);
		ac_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				onBackPressed();

			}
		});
		ac_contactus=(ImageButton) top.findViewById(R.id.ac_contactus);

		ac_contactus.setImageResource(R.drawable.education);
		ac_contactus.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showtutorial();

			}
		});
		initArcMenu(arcMenu, ITEM_DRAWABLES);
		String uri=getIntent().getExtras().getString("uri");
		String action = getIntent().getAction();
		String type = getIntent().getType();
		if(uri!=null)
		{
			Uri receivedUri= Uri.parse(uri);
			BitmapDrawable ob;
			try {
				ob = new BitmapDrawable(getResources(), getThumbnail(receivedUri));
				img.setBackground(ob);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			if (!Intent.ACTION_SEND.equals(action))
				img.setBackgroundResource(Gallery2[getIntent().getExtras().getInt("position")]);
		}
		tv=(EditText) findViewById(R.id.textdetail);
		tv.setFocusableInTouchMode(true);
//		tv.setOnTouchListener(new TouchListener());
		parentView=findViewById(R.id.screen);
		if (Intent.ACTION_SEND.equals(action) && type != null)
		{
			try
			{
				Uri receivedUri = (Uri)getIntent().getParcelableExtra(Intent.EXTRA_STREAM);
				BitmapDrawable ob = new BitmapDrawable(getResources(), getThumbnail(receivedUri));
				img.setBackground(ob);
			}
			catch(Exception e)
			{

			}
		}
		
		// Setup Gesture Detectors
		// View is scaled and translated by matrix, so scale and translate initially
		mRotateDetector = new RotateGestureDetector(getApplicationContext(), new RotateListener());
		mMoveDetector 	= new MoveGestureDetector(getApplicationContext(), new MoveListener());
		final SharedPreferences prefs=getSharedPreferences("norooz",MODE_PRIVATE);
		if(prefs.getBoolean("first_time_second", true))
		{
			new ShowcaseView.Builder(this)
			.setTarget(new ViewTarget(R.id.arc, this))
			.setContentTitle("منو تغییرات").setShowcaseEventListener(new OnShowcaseEventListener() {

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
//					showtutorial();
					final SharedPreferences prefs=getSharedPreferences("norooz",MODE_PRIVATE);
					if(prefs.getBoolean("ft", true))
					{
						new ShowcaseView.Builder(Picture_Detail.this)
						.setTarget(new ViewTarget(ac_contactus))
						.setContentTitle("آموزش").setContentText("برای یادگیری امکانات برنامه بر روی این دکمه کلیک کنید.").build();
						prefs.edit().putBoolean("ft", false).commit();
					}
				}
			})
			.setContentText("برای انجام تغییرات بر روی نوشته شامل رنگ فونت، فونت نوشته و انجام پیش نمایش نهایی بر روی این دکمه کلیک کنید.")
			.build();
			prefs.edit().putBoolean("first_time_second", false).commit();
		}
	}


	private void initArcMenu(ArcMenu menu, int[] itemDrawables) {
		final int itemCount = itemDrawables.length;
		for (int i = 0; i < itemCount; i++) {
			ImageView item = new ImageView(this);
			item.setImageResource(itemDrawables[i]);

			final int position = i;
			menu.addItem(item, new OnClickListener() {

				@Override
				public void onClick(View v) {
//					if(position==0)
//						is_text_in_touch_mode=(is_text_in_touch_mode==true)?false:true;
					if(position==0)
						showColorPickerDialog();
					else if(position==1)
					{
						showFontPickerDialog();
						
					}
//					else if(position==3)
//						is_zoom_mode=(is_zoom_mode==true)?false:true;
					else
						ShowDialog(getBitmapFromView(parentView));
				}
			});
		}
	}
	public boolean onTouchEvent(MotionEvent event) {

//		if(!is_zoom_mode)
//			return false;
		mRotateDetector.onTouchEvent(event);
		mMoveDetector.onTouchEvent(event);
		tv.setRotation(mRotationDegrees);

		if (event.getPointerCount() == 2) {
			int action = event.getAction();
			int pureaction = action & MotionEvent.ACTION_MASK;
			if (pureaction == MotionEvent.ACTION_POINTER_DOWN) {
				mBaseDist = getDistance(event);
				mBaseRatio = mRatio;
			} else {
				float delta = (getDistance(event) - mBaseDist) / STEP;
				float multi = (float) Math.pow(2, delta);
				mRatio = Math.min(1024.0f, Math.max(0.1f, mBaseRatio * multi));
				tv.setTextSize(mRatio + 13);
			}
		}
		else
		{
			tv.setX(mFocusX);
			tv.setY(mFocusY);
		}
		return true;
	}
	int getDistance(MotionEvent event) {
		int dx = (int) (event.getX(0) - event.getX(1));
		int dy = (int) (event.getY(0) - event.getY(1));
		return (int) (Math.sqrt(dx * dx + dy * dy));
	}

	public boolean onTouch(View v, MotionEvent event) {
		return false;
	}
	private void showColorPickerDialog() {

		int initialColor = Color.WHITE;

		ColorPickerDialog colorPickerDialog = new ColorPickerDialog(this, initialColor, new OnColorSelectedListener() {

			@Override
			public void onColorSelected(int color) {
				tv.setTextColor(color);
			}

		});
		colorPickerDialog.show();

	}
	private Bitmap getBitmapFromView(View view) {
		//Define a bitmap with the same size as the view
		tv.setBackgroundColor(Color.TRANSPARENT);
		tv.setFocusable(false);
		Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
		//Bind a canvas to it
		Canvas canvas = new Canvas(returnedBitmap);
		//Get the view's background
		Drawable bgDrawable =view.getBackground();

		if (bgDrawable!=null) 
			//has background drawable, then draw it on the canvas
			bgDrawable.draw(canvas);
		else 
			//does not have background drawable, then draw white background on the canvas
			canvas.drawColor(Color.WHITE);
		// draw the view on the canvas
		view.draw(canvas);
		//return the bitmap
		return returnedBitmap;
	}
	private void share_pic(Bitmap bitmap)
	{
		String preText = getString(R.string.app_name);
		Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
		emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, preText );
		emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,new String[]{});
		emailIntent.setType("text/plain");
		String content="میتوانید برنامه را از اینجا دانلود کنید";
		String url="http://cafebazaar.ir";
		emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,content+"\n"+url );
		emailIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
		emailIntent.setType("image/jpg");
		File fileOutput=null;
		try {

			String state = Environment.getExternalStorageState();
			if (Environment.MEDIA_MOUNTED.equals(state))
				fileOutput = new File(getExternalFilesDir(null), "temp.jpg");
			else
				fileOutput = new File(getFilesDir(), "temp.jpg");

			if (!fileOutput.exists())
				fileOutput.createNewFile();
			FileOutputStream fileOutPutStream = new FileOutputStream(fileOutput);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fileOutPutStream);
			fileOutPutStream.flush();
			fileOutPutStream.close();
//			bitmap.recycle();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Log.i(getClass().getSimpleName(),"tmp.img1Url : "+ tmp.img1Url);
		//Log.i(getClass().getSimpleName(),"tmp.img1Url : "+ imgl.getFile(tmp.img1Url).getName() +".png");
		emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(fileOutput.getAbsoluteFile()));
		startActivity(Intent.createChooser(emailIntent, "انتقال فایل به دوستان"));
	}
	private void showFontPickerDialog() {
		FontPickerDialog fontPickerDialog = new FontPickerDialog(this, new OnFontSelectedListener() {
			@Override
			public void onFontSelected(Typeface Font) {
				tv.setTypeface(Font);

			}
		});
		fontPickerDialog.show();
	}
	private class RotateListener extends RotateGestureDetector.SimpleOnRotateGestureListener {
		@Override
		public boolean onRotate(RotateGestureDetector detector) {
			mRotationDegrees -= detector.getRotationDegreesDelta();
			return true;
		}
	}
	private String Save_im_cache(Bitmap bitmap,String directory,String id)
	{
		try
		{
			String CacheDir=Environment.getExternalStorageDirectory()+directory;
			File f=new File(CacheDir);
			if(!f.exists())
				f.mkdirs();
			String filename=CacheDir+"/"+id.hashCode()+".jpg";
			FileOutputStream fos = new FileOutputStream(filename);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
			MediaScannerConnection.scanFile(getApplicationContext(), new String[] {filename }, null, new OnScanCompletedListener() {

				@Override
				public void onScanCompleted(String path, Uri uri) {
					// TODO Auto-generated method stub

				}
			});
			fos.flush();
			fos.close();
			return filename;
		}
		catch(Exception e)
		{
			Toast.makeText(getApplicationContext(), "اشکال در ذخیره سازی", Toast.LENGTH_LONG).show();
			return null;
		}
	}
	@SuppressWarnings("deprecation")
	private void ShowDialog(final Bitmap bitmap)
	{
		final Dialog dialog = new Dialog(this,android.R.style.Theme_Translucent_NoTitleBar);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		Window window = dialog.getWindow();
		WindowManager.LayoutParams wlp = window.getAttributes();

		wlp.gravity = Gravity.CENTER;
		wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
		window.setAttributes(wlp);
		dialog.getWindow().setLayout(LayoutParams.FILL_PARENT, LayoutParams.MATCH_PARENT);
		dialog.setContentView(R.layout.preprint_dialog);
		ImageButton close=(ImageButton) dialog.findViewById(R.id.ic_close);
		close.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
				tv.setBackgroundResource(R.drawable.abc);
				tv.setFocusableInTouchMode(true);
			}
		});
		ImageView im=(ImageView) dialog.findViewById(R.id.pre_print_img);
		BitmapDrawable ob = new BitmapDrawable(getResources(), bitmap);
		im.setBackground(ob);
		dialog.show();
		dialog.setOnDismissListener(new OnDismissListener() {

			@Override
			public void onDismiss(DialogInterface dialog) {
				tv.setBackgroundResource(R.drawable.abc);
				tv.setFocusableInTouchMode(true);

			}
		});

		ImageButton share=(ImageButton) dialog.findViewById(R.id.ic_share);
		share.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				share_pic(bitmap);

			}
		});
		ImageButton save=(ImageButton) dialog.findViewById(R.id.ic_save);
		save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Save_im_cache(bitmap, dirname,String.valueOf(bitmap.hashCode()));
				Toast.makeText(getApplicationContext(), "تصویر با موفقیت در پوشه تصاویر ذخیره شد", Toast.LENGTH_LONG).show();

			}
		});

	}
	@SuppressWarnings("deprecation")
	private void showtutorial()
	{
		final Typeface yekan=TypefaceSingelton.getFontNumber(this);
		Dialog dialog=new Dialog(this,android.R.style.Theme_Translucent_NoTitleBar);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		Window window = dialog.getWindow();
		WindowManager.LayoutParams wlp = window.getAttributes();

		wlp.gravity = Gravity.CENTER;
		wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
		window.setAttributes(wlp);
		dialog.getWindow().setLayout(LayoutParams.FILL_PARENT, LayoutParams.MATCH_PARENT);
		dialog.setContentView(R.layout.tutorial);
		LinearLayout linearLayout  = (LinearLayout) dialog.findViewById(R.id.tutall);
		linearLayout.setClickable(true);
		linearLayout.setOnClickListener(new onclick(dialog));
		View v1=dialog.findViewById(R.id.ic1);
		TextView tv1=(TextView) v1.findViewById(R.id.title);
		ImageButton img=(ImageButton) v1.findViewById(R.id.icon);
		img.setImageResource(R.drawable.select);
		tv1.setTypeface(yekan);
		tv1.setText("با حرکت دست بر روی هر قسمت صفحه (غیر از متن نوشته شده) می توانید نسبت به جابه جایی متن اقدام کنید.");
		
		View v2=dialog.findViewById(R.id.ic2);
		TextView tv2=(TextView) v2.findViewById(R.id.title);
		ImageButton img2=(ImageButton) v2.findViewById(R.id.icon);
		img2.setImageResource(R.drawable.pinch);
		tv2.setTypeface(yekan);
		tv2.setText("با حرکت دو انگشت بر روی صفحه می توانید نسبت به چرخش و کوچک بزرگی متن نوشته شده تغییرات لازم را اعمال نمایید");
		
		View v3=dialog.findViewById(R.id.ic3);
		ImageButton img3=(ImageButton) v3.findViewById(R.id.icon);
		img3.setImageResource(ITEM_DRAWABLES[0]);
		TextView tv3=(TextView) v3.findViewById(R.id.title);
		tv3.setTypeface(yekan);
		tv3.setText(".برای تغییر رنگ نوشته استفاده می شود");
		View v4=dialog.findViewById(R.id.ic4);
		ImageButton img4=(ImageButton) v4.findViewById(R.id.icon);
		img4.setImageResource(ITEM_DRAWABLES[1]);
		TextView tv4=(TextView) v4.findViewById(R.id.title);
		tv4.setTypeface(yekan);
		tv4.setText("برای تغییر فونت نوشته استفاده می شود.");
		View v5=dialog.findViewById(R.id.ic5);
		ImageButton img5=(ImageButton) v5.findViewById(R.id.icon);
		img5.setImageResource(ITEM_DRAWABLES[2]);
		TextView tv5=(TextView) v5.findViewById(R.id.title);
		tv5.setTypeface(yekan);
		tv5.setText("زمانی که انجام تغییرات به پایان رسید برای پیش نمایش کار استفاده می شود.");
		dialog.show();

	}
	private class onclick implements View.OnClickListener
	{
		final Dialog dialog;
		public onclick(Dialog dialog)
		{
			this.dialog=dialog;
		}
		@Override
		public void onClick(View v) {

			dialog.dismiss();
		}

	}
	public Bitmap getThumbnail(Uri uri) throws FileNotFoundException, IOException{
        InputStream input =  getContentResolver().openInputStream(uri);

        BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
        onlyBoundsOptions.inJustDecodeBounds = true;
        onlyBoundsOptions.inDither=true;//optional
        onlyBoundsOptions.inPreferredConfig=Bitmap.Config.ARGB_8888;//optional
        BitmapFactory.decodeStream(input, null, onlyBoundsOptions);
        input.close();
        if ((onlyBoundsOptions.outWidth == -1) || (onlyBoundsOptions.outHeight == -1))
            return null;

        int IMAGE_MAX_SIZE=1024;
        int scale = 1;
		if (onlyBoundsOptions.outHeight > IMAGE_MAX_SIZE || onlyBoundsOptions.outWidth > IMAGE_MAX_SIZE) {
			scale = (int)Math.pow(2, (int) Math.round(Math.log(IMAGE_MAX_SIZE / 
					(double) Math.max(onlyBoundsOptions.outHeight, onlyBoundsOptions.outWidth)) / Math.log(0.5)));
		}
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inSampleSize = scale;
        bitmapOptions.inDither=true;//optional
        bitmapOptions.inPreferredConfig=Bitmap.Config.ARGB_8888;//optional
        input = this.getContentResolver().openInputStream(uri);
        Bitmap bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
        input.close();
        return bitmap;
    }
	private class MoveListener extends MoveGestureDetector.SimpleOnMoveGestureListener {
		@Override
		public boolean onMove(MoveGestureDetector detector) {
			PointF d = detector.getFocusDelta();
			mFocusX += d.x;
			mFocusY += d.y;		

			// mFocusX = detector.getFocusX();
			// mFocusY = detector.getFocusY();
			return true;
		}
	}
}