package com.ctbu.latte.ec.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * Created by CaiPengFei on 2018/1/25.
 */

public class DatabaseManager {
    private DaoSession mDaoSession = null;
    private UserDao mDao = null;

    private DatabaseManager(){

    }

    public DatabaseManager init(Context context){
        initDao(context);
        return this;
    }
    private static final class Holder{
        private static final DatabaseManager INSTANCE = new DatabaseManager();
    }

    public static  DatabaseManager getInstance(){
        return Holder.INSTANCE;
    }

    private void initDao(Context context) {
        final ReleaseOpenHelper helper = new ReleaseOpenHelper(context, "fastec_app.db");
        final Database db =helper.getWritableDb();
        mDaoSession =new DaoMaster(db).newSession();
        mDao=mDaoSession.getUserDao();
    }

    public final UserDao getDao(){
        return mDao;
    }
}
