package za.co.zapper.assessment.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import za.co.zapper.assessment.activity.StartUpActivity;

/**
 * Created by SaurabhB on 2017/02/24.
 */
public class DataReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
             Bundle bundle = intent.getExtras();
            Intent activityIntent = new Intent(context, StartUpActivity.class);
            activityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(activityIntent);


    }
}
