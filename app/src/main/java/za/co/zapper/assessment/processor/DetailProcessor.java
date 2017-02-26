package za.co.zapper.assessment.processor;

import android.content.Context;
import android.util.Log;
import za.co.zapper.assessment.dao.DetailDao;
import za.co.zapper.assessment.dao.MasterDao;
import za.co.zapper.assessment.dao.ZapperDatabase;
import za.co.zapper.assessment.domain.Detail;
import za.co.zapper.assessment.domain.Master;
import za.co.zapper.assessment.transport.DetailTransport;
import za.co.zapper.assessment.transport.bean.DetailTransportBean;

import java.util.List;

/**
 * Created by SaurabhB on 2017/02/23.
 */
public class DetailProcessor {
    private DetailDao detailDao = null;
    private MasterDao masterDao = null;
    private static DetailProcessor detailProcessor = null;
    Context context;

    private DetailProcessor(Context context){
        this.context = context;
        detailDao = ZapperDatabase.getInstance(context).getDaoSession().getDetailDao();
        masterDao = ZapperDatabase.getInstance(context).getDaoSession().getMasterDao();
    }
    /**
     *
     * @return singleton of detailProcessor
     */
    public static DetailProcessor getInstance(Context context){
        if(detailProcessor == null){
            detailProcessor = new DetailProcessor(context);
        }
        return detailProcessor;
    }

/*    public void processDetailTransportData() {
        List<Master> masterList = masterDao.loadAll();

        for (Master master : masterList) {
            DetailTransportBean detailTransportBean = DetailTransport.getInstance(context).getDetailTransportData(master.getId());
            if(detailTransportBean != null ) {
                Detail detail = new Detail();
                detail.setId(detailTransportBean.getId());
                detail.setFirstName(detailTransportBean.getFirstName());
                detail.setLastName(detailTransportBean.getLastName());
                detail.setAge(detailTransportBean.getAge());
                detail.setLastName(detailTransportBean.getLastName());
                detailDao.insertOrReplace(detail);
            }

        }

    }*/

    /**
     * Sending the id, getting the detail data and save into the database
     * @param id
     */
     public void processDetailData(Long id) {
         DetailTransport.getInstance(context).setDetailTransportData(id);
        }

    /**
     * @param id
     * @return An detail entity by a unique id.
     */
    public Detail getDetail(Long id){
        Detail detail = detailDao.load(id);
        List<Detail> detailList = getDetail();
        return detail;
    }

    /**
     *
     * @return list of Details entities.
     */
    public List<Detail> getDetail(){
        return detailDao.loadAll();
    }

    /**
     * Deletes Masters from the database.
     */
    public void clearAllDetails() {
        detailDao.deleteAll();
    }
}
