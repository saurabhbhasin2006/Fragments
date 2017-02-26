package za.co.zapper.assessment.activity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;
import za.co.zapper.assessment.R;
import za.co.zapper.assessment.dao.MasterDao;
import za.co.zapper.assessment.dao.ZapperDatabase;
import za.co.zapper.assessment.processor.MasterProcessor;
import za.co.zapper.assessment.receiver.AlarmReceiver;
import za.co.zapper.assessment.service.MasterService;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by SaurabhB on 2017/02/25.
 */
public class StartUpActivity extends FragmentActivity {
    Context context;
    Boolean status = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;

        Toast.makeText(this, "StartUpActivity ", Toast.LENGTH_SHORT).show();
        setTitle("ZapperActivity");

        ZapperDatabase.init(this);
        MasterDao masterDao = ZapperDatabase.getInstance(this).getDaoSession().getMasterDao();

        Intent masterService = new Intent(StartUpActivity.this, MasterService.class);
        startService(masterService);
        if (masterDao.loadAll().size() > 0) {

            setContentView(R.layout.activity_fragment_layout);
            Fragment masterFragment = new MasterFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(android.R.id.content, masterFragment);
            fragmentTransaction.commit();
        } else {
            recreateActivityIfNoData();
        }
    }

    void recreateActivityIfNoData(){
        int delay = 5000; // delay for 5 sec.
        int period = 10000; // repeat every 10 secs.

        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {
                MasterProcessor masterProcessor = MasterProcessor.getInstance(context);
                status = MasterProcessor.getInstance(context).processMasterTransportData();
                if(status) {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(context, StartUpActivity.class);
                            finish();
                            startActivity(intent);
                        }
                    });
                }

            }
        }, delay, period);

    }
}
