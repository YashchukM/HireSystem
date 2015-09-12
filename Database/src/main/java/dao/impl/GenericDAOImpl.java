package dao.impl;

import dao.GenericDAO;
import entity.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Andrii on 22/07/2015.
 */
@Repository
public abstract class GenericDAOImpl<T> implements GenericDAO<T> {
    @Autowired
    protected SessionFactory sessionFactory;

    @Override
    public Serializable save(T entity) {
        return sessionFactory.getCurrentSession().save(entity);
    }

    @Override
    public void update(T entity) {
        sessionFactory.getCurrentSession().update(entity);
    }

    @Override
    public void delete(T entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    /*@Override
    public List<T> getMany(Query query) {
        return query.list();
    }

    @Override
    public T getOne(Query query) {
        return (T) query.uniqueResult();
    }*/

    @Override
    @Transactional
    public List<T> getMany(DetachedCriteria criteria, int firstResult, int maxResults) {
        return criteria.
                getExecutableCriteria(sessionFactory.getCurrentSession()).
                setFirstResult(firstResult).setMaxResults(maxResults).
                list();
    }

    @Override
    @Transactional
    public List<T> getInitializedMany(DetachedCriteria criteria, int firstResult, int maxResults) {
        List<T> objects = getMany(criteria, firstResult, maxResults);
        for (T obj : objects) {
            initialize(obj);
        }
        return objects;
    }

    @Override
    @Deprecated
    public T getOne(DetachedCriteria criteria) {
        return (T) criteria.getExecutableCriteria(sessionFactory.getCurrentSession()).list();
    }

    @Override
    @Deprecated
    public T getInitializedOne(DetachedCriteria criteria) {
        T object = getOne(criteria);
        initialize(object);
        return object;
    }

    @Override
    @Deprecated
    public List<T> getAll(Class clazz) {
        Query query = sessionFactory.getCurrentSession().createQuery("from " + clazz.getName());
        return query.list();
    }

    @Override
    public T getByID(Class clazz, Serializable id) {
        return (T) sessionFactory.getCurrentSession().get(clazz, id);
    }

    @Override
    public T getInitializedByID(Class clazz, Serializable id) {
        T object = getByID(clazz, id);
        initialize(object);
        return object;
    }

    @Override
    public DetachedCriteria getDetachedCriteria(Class clazz) {
        return DetachedCriteria.forClass(clazz);
    }

    protected void initialize(T obj) {

    }
}
