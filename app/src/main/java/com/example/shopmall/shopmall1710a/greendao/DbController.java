package com.example.shopmall.shopmall1710a.greendao;

import android.content.Context;

import org.jetbrains.annotations.Contract;

import java.util.List;

public class DbController {
    private static DbController dbController;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private ProductDao contractDao;

    public DbController(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "MyContract.db");
        daoMaster = new DaoMaster(helper.getWritableDb());
        daoSession = daoMaster.newSession();
        contractDao = daoSession.getProductDao();
    }

    public static DbController getDbController(Context context){
        if (dbController==null){
            dbController = new DbController(context);
        }
        return  dbController;
    }

    public long insert(Product product){
        return contractDao.insertOrReplace(product);
    }
    public void deleteAll(){
        contractDao.deleteAll();
    }
    public List<Product> queryAll(){
        List<Product> list = contractDao.queryBuilder().list();
        return list;
    }
}
