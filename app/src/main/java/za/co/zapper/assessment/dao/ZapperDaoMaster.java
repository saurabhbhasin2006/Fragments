package za.co.zapper.assessment.dao;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by SaurabhB on 2017/02/24.
 */
public class ZapperDaoMaster extends DaoMaster {
    public ZapperDaoMaster(SQLiteDatabase db) {
        super(db);
    }
    /** Creates underlying database table using DAOs. */
    public static void createAllTables(SQLiteDatabase db, boolean ifNotExists) {
        MasterDao.createTable(db, ifNotExists);
        DetailDao.createTable(db, ifNotExists);
    }
}
