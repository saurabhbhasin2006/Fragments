package za.co.zapper.assessment.dao;

import android.database.sqlite.SQLiteDatabase;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;
import za.co.zapper.assessment.domain.Detail;
import za.co.zapper.assessment.domain.Master;

import java.util.Map;

/**
 * Created by SaurabhB on 2017/02/24.
 */
public class ZapperDaoSession extends DaoSession {

    private final DaoConfig masterDaoConfig;
    private final DaoConfig detailDaoConfig;

    private final MasterDao masterDao;
    private final DetailDao detailDao;

    public ZapperDaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig> daoConfigMap) {
        super(db, type, daoConfigMap);

        masterDaoConfig = daoConfigMap.get(MasterDao.class).clone();
        masterDaoConfig.initIdentityScope(type);

        detailDaoConfig = daoConfigMap.get(DetailDao.class).clone();
        detailDaoConfig.initIdentityScope(type);

        masterDao = new MasterDao(masterDaoConfig, this);
        detailDao = new DetailDao(detailDaoConfig, this);

        registerDao(Master.class, masterDao);
        registerDao(Detail.class, detailDao);

    }

    public void clear() {
        masterDaoConfig.getIdentityScope().clear();
        detailDaoConfig.getIdentityScope().clear();
    }

    @Override
    public MasterDao getMasterDao() {
        return masterDao;
    }

    @Override
    public DetailDao getDetailDao() {
        return detailDao;
    }
}
