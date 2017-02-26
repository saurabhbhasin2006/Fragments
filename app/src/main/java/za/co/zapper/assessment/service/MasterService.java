package za.co.zapper.assessment.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import za.co.zapper.assessment.processor.MasterProcessor;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by SaurabhB on 2017/02/23.
 */
public class MasterService extends IntentService {
    private MasterProcessor masterProcessor = null;
    private Context context;

    public MasterService(){
        super(MasterService.class.getName());
        context = this;
        masterProcessor = MasterProcessor.getInstance(context);
    }
    public MasterService(String name) {
        super(name);
    }


    @Override
    protected void onHandleIntent(Intent intent) {

        int delay = 5000; // delay for 5 sec.
        int period = 10000; // repeat every 10 secs.

        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {
                masterProcessor = MasterProcessor.getInstance(context);
                Boolean status = MasterProcessor.getInstance(context).processMasterTransportData();
                Intent intent1 = new Intent();
                intent1.setAction("action");
                if(status) {
                    sendBroadcast(intent1);
                }
            }

        }, delay, period);
    }
}
