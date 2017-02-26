package za.co.zapper.assessment.service;

import android.app.IntentService;
import android.content.Intent;
import za.co.zapper.assessment.processor.DetailProcessor;

/**
 * Not Used right now.
 *
 * Created by SaurabhB on 2017/02/23.
 */
public class DetailService extends IntentService {

    private DetailProcessor detailProcessor = null;

    public DetailService(){
        super(DetailService.class.getName());
    }

    public DetailService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }
}
