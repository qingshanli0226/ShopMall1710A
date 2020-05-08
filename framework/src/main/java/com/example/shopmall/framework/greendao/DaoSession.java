package com.example.shopmall.framework.greendao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.example.shopmall.framework.message.ShopMallMessage;

import com.example.shopmall.framework.greendao.ShopMallMessageDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig shopMallMessageDaoConfig;

    private final ShopMallMessageDao shopMallMessageDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        shopMallMessageDaoConfig = daoConfigMap.get(ShopMallMessageDao.class).clone();
        shopMallMessageDaoConfig.initIdentityScope(type);

        shopMallMessageDao = new ShopMallMessageDao(shopMallMessageDaoConfig, this);

        registerDao(ShopMallMessage.class, shopMallMessageDao);
    }
    
    public void clear() {
        shopMallMessageDaoConfig.clearIdentityScope();
    }

    public ShopMallMessageDao getShopMallMessageDao() {
        return shopMallMessageDao;
    }

}
