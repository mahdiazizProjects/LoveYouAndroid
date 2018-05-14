package asheghetam.others;

import asheghetam.app.R;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

public class FontPickerDialog extends AlertDialog {

	private int SelectedFont=1;
	private CheckBox ch1;
	private CheckBox ch2;
	private CheckBox ch3;
	private CheckBox ch4;
	private CheckBox ch5;
	private CheckBox ch6;
	private CheckBox ch7;
    private final OnFontSelectedListener onFontSelectedListener;

    public FontPickerDialog(Context context, OnFontSelectedListener onFontSelectedListener) {
    	super(context);
    	LayoutInflater li = LayoutInflater.from(context);
        View content = li.inflate(R.layout.font_selector, null);
        this.onFontSelectedListener = onFontSelectedListener;
        setButton(BUTTON_POSITIVE, context.getString(android.R.string.ok), onClickListener);
        setButton(BUTTON_NEGATIVE, context.getString(android.R.string.cancel), onClickListener);
        View arabic=content.findViewById(R.id.arabic);
        TextView tv=(TextView) arabic.findViewById(R.id.text);
        tv.setText("فونت عربیک");
        ch1=(CheckBox) arabic.findViewById(R.id.ch);
        ch1.setTag(1);
        View bnaz=content.findViewById(R.id.bnazin);
        TextView tv2=(TextView) bnaz.findViewById(R.id.text);
        tv2.setText("فونت ب نازنین");
        ch2=(CheckBox) bnaz.findViewById(R.id.ch);
        ch2.setTag(2);
        View nastaligh=content.findViewById(R.id.nastaligh);
        TextView tv3=(TextView) nastaligh.findViewById(R.id.text);
        tv3.setText("فونت نستعلیق");
        ch3=(CheckBox) nastaligh.findViewById(R.id.ch);
        ch3.setTag(3);
        View yekan=content.findViewById(R.id.yekan);
        TextView tv4=(TextView) yekan.findViewById(R.id.text);
        tv4.setText("فونت یکان");
        ch4=(CheckBox) yekan.findViewById(R.id.ch);
        ch4.setTag(4);
        View ent1=content.findViewById(R.id.entezar1);
        TextView tv5=(TextView) ent1.findViewById(R.id.text);
        tv5.setText("فونت انتظار 1");
        ch5=(CheckBox) ent1.findViewById(R.id.ch);
        ch5.setTag(5);
        View ent2=content.findViewById(R.id.entezar2);
        TextView tv6=(TextView) ent2.findViewById(R.id.text);
        tv6.setText("فونت انتظار 2");
        ch6=(CheckBox) ent2.findViewById(R.id.ch);
        ch6.setTag(6);
        View ent3=content.findViewById(R.id.entezar3);
        TextView tv7=(TextView) ent3.findViewById(R.id.text);
        tv7.setText("فونت انتظار 3");
        ch7=(CheckBox) ent3.findViewById(R.id.ch);
        ch7.setTag(7);
        tv.setTypeface(TypefaceSingelton.getFontAdobArabic(context));
        tv2.setTypeface(TypefaceSingelton.getFontBNazanin(context));
        tv3.setTypeface(TypefaceSingelton.getFontNastaligh(context));
        tv4.setTypeface(TypefaceSingelton.getFontNumber(context));
        tv5.setTypeface(TypefaceSingelton.getFontEntezar1(context));
        tv6.setTypeface(TypefaceSingelton.getFontEntezar2(context));
        tv7.setTypeface(TypefaceSingelton.getFontEntezar3(context));
        ch1.setOnCheckedChangeListener(new CheckedChangedReset());
        ch2.setOnCheckedChangeListener(new CheckedChangedReset());
        ch3.setOnCheckedChangeListener(new CheckedChangedReset());
        ch4.setOnCheckedChangeListener(new CheckedChangedReset());
        ch5.setOnCheckedChangeListener(new CheckedChangedReset());
        ch6.setOnCheckedChangeListener(new CheckedChangedReset());
        ch7.setOnCheckedChangeListener(new CheckedChangedReset());
        setView(content);
    }

    private OnClickListener onClickListener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
            case BUTTON_POSITIVE:
            	switch (SelectedFont) {
				case 1:
	                Typeface selectedFont = TypefaceSingelton.getFontAdobArabic(getContext());
	                onFontSelectedListener.onFontSelected(selectedFont);
					break;
				case 2:
	                selectedFont = TypefaceSingelton.getFontBNazanin(getContext());
	                onFontSelectedListener.onFontSelected(selectedFont);
					break;
				case 3:
	                selectedFont = TypefaceSingelton.getFontNastaligh(getContext());
	                onFontSelectedListener.onFontSelected(selectedFont);
					break;
				case 4:
	                selectedFont = TypefaceSingelton.getFontNumber(getContext());
	                onFontSelectedListener.onFontSelected(selectedFont);
					break;
				case 5:
	                selectedFont = TypefaceSingelton.getFontEntezar1(getContext());
	                onFontSelectedListener.onFontSelected(selectedFont);
					break;
				case 6:
	                selectedFont = TypefaceSingelton.getFontEntezar2(getContext());
	                onFontSelectedListener.onFontSelected(selectedFont);
					break;
				case 7:
	                selectedFont = TypefaceSingelton.getFontEntezar3(getContext());
	                onFontSelectedListener.onFontSelected(selectedFont);
					break;
				}
                break;
            case BUTTON_NEGATIVE:
                dialog.dismiss();
                break;
            }
        }
    };

    public interface OnFontSelectedListener {
        public void onFontSelected(Typeface Font);
    }
    private class CheckedChangedReset implements OnCheckedChangeListener
    {

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			SelectedFont=(int) buttonView.getTag();
			ResetChecked();
			buttonView.setChecked(isChecked);
			
		}
    	
    }
    private void ResetChecked()
    {
    	ch1.setChecked(false);
    	ch2.setChecked(false);
    	ch3.setChecked(false);
    	ch4.setChecked(false);
    }
}