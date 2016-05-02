package sample.mycustomlistview.utils;

/**
 * Created by shyam.yammanuru on 17/09/2015.
 */

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import sample.mycustomlistview.main.R;


public class CustomProgress extends Dialog {
	public CustomProgress(Context context) {
		super(context);
	}

	public CustomProgress(Context context, int theme) {
		super(context, theme);
	}

	public void onWindowFocusChanged(boolean hasFocus){
		ImageView imageView = (ImageView) findViewById(R.id.spinnerImageView);
        AnimationDrawable spinner = (AnimationDrawable) imageView.getBackground();
        spinner.start();
    }

	public static CustomProgress show(Context context, CharSequence message, boolean indeterminate, boolean cancelable,
			OnCancelListener cancelListener) {
		CustomProgress dialog = new CustomProgress(context,R.style.ProgressHUD);
		dialog.setTitle("");
		dialog.setContentView(R.layout.progress_hud);
		if(message == null || message.length() == 0) {
			dialog.findViewById(R.id.message).setVisibility(View.GONE);
		} else {
			TextView txt = (TextView)dialog.findViewById(R.id.message);
			txt.setText(message);
		}
		dialog.setCancelable(cancelable);
		dialog.setOnCancelListener(cancelListener);
		dialog.getWindow().getAttributes().gravity= Gravity.CENTER;
		WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
		lp.dimAmount=0.2f;
		dialog.getWindow().setAttributes(lp); 
		//dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
		dialog.show();
		return dialog;
	}	
}
