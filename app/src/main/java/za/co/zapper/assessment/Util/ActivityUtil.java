package za.co.zapper.assessment.Util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by SaurabhB on 2017/02/26.
 */
public class ActivityUtil {
    public ActivityUtil() {
    }

    public static void showInformationAlertDialog(final Context context, String title, String displayText, DialogInterface.OnClickListener okButtonOnClickListener) {
        LinearLayout titleTextLinearLayout = new LinearLayout(context);
        titleTextLinearLayout.setOrientation(1);
        TextView titleTextView = new TextView(context);
        titleTextView.setText(title);
        titleTextView.setTextSize(27.0F);
        titleTextView.setGravity(17);
        titleTextView.setTypeface(Typeface.SANS_SERIF, 1);
        titleTextView.setGravity(17);
        titleTextView.setTextColor(-16777216);
        titleTextView.setPadding(10, 10, 10, 10);
        TextView messageTextView = new TextView(context);
        messageTextView.setText(displayText);
        messageTextView.setTextSize(17.0F);
        messageTextView.setGravity(17);
        messageTextView.setTypeface(Typeface.SANS_SERIF, 1);
        messageTextView.setGravity(17);
        messageTextView.setTextColor(-16777216);
        messageTextView.setPadding(10, 10, 10, 10);
        titleTextLinearLayout.addView(titleTextView);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCustomTitle(titleTextLinearLayout).setNegativeButton("OK", okButtonOnClickListener).setView(messageTextView);
        final AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
        Button okButton = (Button)alertDialog.findViewById(16908314);
        okButton.setBackgroundColor(Color.GREEN);
        okButton.setTextColor(-1);
        okButton.setTextSize(20.0F);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.cancel();
                ((Activity)context).finish();
            }
        });
    }

}
