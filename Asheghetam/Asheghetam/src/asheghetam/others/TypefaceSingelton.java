package asheghetam.others;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;

public class TypefaceSingelton {

	private static TypefaceSingelton instance = new TypefaceSingelton();

	private TypefaceSingelton() {}

	public static TypefaceSingelton getInstance() {
		return instance;
	}
	public static  Typeface getFontNumber(Context context) {
		return Typeface.createFromAsset(context.getResources().getAssets(), "fonts/BYekan.otf");
	}
	public static  Typeface getFontBNazanin(Context context) {
		return Typeface.createFromAsset(context.getResources().getAssets(), "fonts/BNazanin.ttf");
	}
	public static  Typeface getFontAdobArabic(Context context) {
		String text=null;
		if(Build.VERSION.SDK_INT<21)
			text="arabic.ttf";
		else
			text="Adobe.Arabic-Regular.com.otf";
		return Typeface.createFromAsset(context.getResources().getAssets(), "fonts/"+text);
	}
	public static  Typeface getFontNastaligh(Context context) {
		return Typeface.createFromAsset(context.getResources().getAssets(), "fonts/IranNastaliq.ttf");
	}
	public static  Typeface getFontEntezar1(Context context) {
		return Typeface.createFromAsset(context.getResources().getAssets(), "fonts/entezar.ttf");
	}
	public static  Typeface getFontEntezar2(Context context) {
		return Typeface.createFromAsset(context.getResources().getAssets(), "fonts/entezard3.ttf");
	}
	public static  Typeface getFontEntezar3(Context context) {
		return Typeface.createFromAsset(context.getResources().getAssets(), "fonts/entezare2.ttf");
	}
}