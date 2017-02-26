package za.co.zapper.assessment.processor;

import android.content.Context;
import android.util.Log;
import za.co.zapper.assessment.dao.MasterDao;
import za.co.zapper.assessment.dao.ZapperDatabase;
import za.co.zapper.assessment.domain.Master;
import za.co.zapper.assessment.transport.MasterTransport;
import za.co.zapper.assessment.transport.bean.MasterTransportBean;

import java.util.List;

/**
 * Created by SaurabhB on 2017/02/23.
 */
public class MasterProcessor {
    private MasterDao masterDao = null;
    private static MasterProcessor masterProcessor = null;
    Context context;
    MasterTransport masterTransport = null;

    private MasterProcessor(Context context){
        this.context = context;
        masterDao = ZapperDatabase.getInstance(context).getDaoSession().getMasterDao();
        masterTransport = MasterTransport.getInstance(context);
    }
    /**
     *
     * @return singleton of detailProcessor
     */
    public static MasterProcessor getInstance(Context context){

        if(masterProcessor == null){
            masterProcessor = new MasterProcessor(context);
        }
        return masterProcessor;
    }

    /**
     * Inserting the master data from the server.
     */
    public Boolean processMasterTransportData() {
        Boolean downloadStatus = masterTransport.getMasterTransportData();
        return downloadStatus;

    }

    /**
     * @param id
     * @return An master entity by a unique id.
     */
    public Master getMaster(Long id){
        Master master = masterDao.load(id);
        return master;
    }

    /**
     *
     * @return list of Masters entities.
     */
    public List<Master> getListOfMaster(){
        return masterDao.loadAll();
    }

    /**
     * Deletes Masters from the database.
     */
    public void clearAllMasters() {
        masterDao.deleteAll();
    }

}
