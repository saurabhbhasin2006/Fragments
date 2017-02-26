package za.co.zapper.assessment.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import za.co.zapper.assessment.service.MasterService;

/**
 * Alarm Not used but can be used with Alarm Manager.
 *
 * Created by SaurabhB on 2017/02/26.
 */
public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent newintent = new Intent(context, MasterService.class);
        context.startService(newintent);
    }
}