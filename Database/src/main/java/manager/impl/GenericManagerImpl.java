package manager.impl;

import dao.GenericDAO;
import manager.GenericManager;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Andrii on 30/07/2015.
 */
@Service
public abstract class GenericManagerImpl<T> implements GenericManager<T> {
    protected GenericDAO<T> dao;

    protected Class clazz;

    public GenericManagerImpl(Class clazz, GenericDAO<T> dao) {
        this.clazz = clazz;
        this.dao = dao;
    }

    @Override
    @Transactional
    public Serializable save(T entity) {
        return dao.save(entity);
    }

    @Override
    @Transactional
    public void update(T entity) {
        dao.update(entity);
    }

    @Override
    @Transactional
    public void delete(T entity) {
        dao.delete(entity);
    }

    @Override
    @Transactional
    public T getById(Serializable id) {
        return dao.getByID(clazz, id);
    }

    @Override
    @Transactional
    public T getInitializedById(Serializable id) {
        return dao.getInitializedByID(clazz, id);
    }

    public abstract class GenericQueryImpl<T> implements GenericQuery<T> {
        protected GenericDAO<T> dao;
        protected DetachedCriteria criteria;

        public GenericQueryImpl(GenericDAO<T> dao) {
            this.dao = dao;
            criteria = dao.getDetachedCriteria(clazz);
        }

        protected void addCriterion(Criterion criterion) {
            if (criteria == null) {
                criteria = dao.getDetachedCriteria(clazz);
            }
            criteria.add(criterion);
        }

        @Override
        public List<T> find(int firstResult, int maxResults) {
            List<T> list = null;
            if (criteria != null) {
                list = dao.getMany(criteria, firstResult, maxResults);
            }
            return list;
        }

        @Override
        public List<T> findInitialized(int firstResult, int maxResults) {
            List<T> list = null;
            if (criteria != null) {
                list = dao.getInitializedMany(criteria, firstResult, maxResults);
            }
            return list;
        }
    }
}
