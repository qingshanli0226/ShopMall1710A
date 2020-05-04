package com.example.shopmall.buy.manager;

import android.database.sqlite.SQLiteDatabase;

import com.dev.base.model.db.DaoMaster;
import com.dev.base.model.db.DaoSession;
import com.example.shopmall.buy.entity.CollectEntitiy;
import com.example.shopmall.framework.entity.ShortCarEntity;
import com.example.shopmall.framework.manager.AppCore;


import java.util.ArrayList;
import java.util.List;

public class CollectManager {
    private static CollectManager collectManager;
    private DaoMaster.DevOpenHelper devOpenHelper;
    private DaoSession daoSession;
    private CollectManager(){
        devOpenHelper = new DaoMaster.DevOpenHelper(AppCore.getInstance().getApp(), "collects.db", null);
        SQLiteDatabase sqLiteDatabase = devOpenHelper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(sqLiteDatabase);
        daoSession = daoMaster.newSession();
    }

    public static CollectManager getInstance(){
        if (collectManager == null){
            collectManager = new CollectManager();
        }
        return collectManager;
    }
    // 添加收藏数据
    public void collectShort(final List<ShortCarEntity.ResultBean> list){
        final List<CollectEntitiy> collectEntitiys = new ArrayList<>();
        for (ShortCarEntity.ResultBean resultBean : list) {
            collectEntitiys.add(new CollectEntitiy(resultBean.getProductPrice()+"",resultBean.getProductName(),resultBean.getUrl()));
        }
        daoSession.runInTx(new Runnable() {
            @Override
            public void run() {
                daoSession.insert(collectEntitiys.get(0));
            }
        });
    }
    // 查询收藏数据
    public List<CollectEntitiy>  queryShort(){
        List<CollectEntitiy> collectEntitiys = daoSession.loadAll(CollectEntitiy.class);
        return collectEntitiys;
    }

}
