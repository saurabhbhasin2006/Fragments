package za.co.zapper.assessment.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by SaurabhB on 2017/02/23.
 */
public class ZapperDatabase {

    private static ZapperDatabase reference = null;

    private DaoMaster.DevOpenHelper devOpenHelper = null;
    private SQLiteDatabase sqliteDatabase = null;
    private ZapperDaoMaster daoMaster = null;
    private DaoSession daoSession = null;

    /**
     * Make default constructor private, so no one can instantiate it. This
     * ensures only one instance will exist.
     */
    private ZapperDatabase(Context context) {
        devOpenHelper = new ZapperDaoMaster.DevOpenHelper(context, "Zapperdb.sqlite", null);
        sqliteDatabase = devOpenHelper.getWritableDatabase();
        ZapperDaoMaster.createAllTables(sqliteDatabase,true);
        daoMaster = new ZapperDaoMaster(sqliteDatabase);
        daoSession = daoMaster.newSession();
    }

    public static void init(Context context) {
        if (reference == null) {
            // Will only ever be instantiated once (singleton).
            reference = new ZapperDatabase(context);
        }
    }

    /**
     * Returns the DatabaseManager singleton instance being used by the
     * application.
     *
     * @return DatabaseManager representing the singleton instance.
     */
    public static ZapperDatabase getInstance(Context context) {
        if (reference == null) {
            // Will only ever be instantiated once (singleton).
            reference = new ZapperDatabase(context);
        }
        return reference;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }


}
