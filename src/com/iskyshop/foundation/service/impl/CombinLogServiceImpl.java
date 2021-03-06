/*** Eclipse Class Decompiler plugin, copyright (c) 2012 Chao Chen (cnfree2000@hotmail.com) ***/
package com.iskyshop.foundation.service.impl;

import com.iskyshop.core.dao.IGenericDAO;
import com.iskyshop.core.query.GenericPageList;
import com.iskyshop.core.query.PageObject;
import com.iskyshop.core.query.support.IPageList;
import com.iskyshop.core.query.support.IQueryObject;
import com.iskyshop.foundation.domain.CombinLog;
import com.iskyshop.foundation.service.ICombinLogService;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CombinLogServiceImpl implements ICombinLogService {

    @Resource(name = "combinLogDAO")
    private IGenericDAO<CombinLog> combinLogDao;

    public boolean save(CombinLog combinLog) {
        try {
            this.combinLogDao.save(combinLog);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public CombinLog getObjById(Long id) {
        CombinLog combinLog = (CombinLog) this.combinLogDao.get(id);
        if (combinLog != null) {
            return combinLog;
        }
        return null;
    }

    public boolean delete(Long id) {
        try {
            this.combinLogDao.remove(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean batchDelete(List<Serializable> combinLogIds) {
        for (Serializable id : combinLogIds) {
            delete((Long) id);
        }
        return true;
    }

    public IPageList list(IQueryObject properties) {
        if (properties == null) {
            return null;
        }
        String query = properties.getQuery();
        Map params = properties.getParameters();
        GenericPageList pList = new GenericPageList(CombinLog.class, query, params, this.combinLogDao);
        if (properties != null) {
            PageObject pageObj = properties.getPageObj();
            if (pageObj != null)
                pList.doList((pageObj.getCurrentPage() == null) ? 0 : pageObj.getCurrentPage().intValue(),
                        (pageObj.getPageSize() == null) ? 0 : pageObj.getPageSize().intValue());
        } else {
            pList.doList(0, -1);
        }
        return pList;
    }

    public boolean update(CombinLog combinLog) {
        try {
            this.combinLogDao.update(combinLog);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<CombinLog> query(String query, Map params, int begin, int max) {
        return this.combinLogDao.query(query, params, begin, max);
    }
}