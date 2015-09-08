package dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Andrii on 22/07/2015.
 */
public interface GenericDAO<T> {
    public Serializable save(T entity);
    public void update(T entity);
    public void delete(T entity);

    /*public List<T> getMany(Query query);
    public T getOne(Query query);*/

    public List<T> getMany(DetachedCriteria criteria, int firstResult, int maxResults);
    public List<T> getInitializedMany(DetachedCriteria criteria, int firstResult, int maxResults);
    public T getOne(DetachedCriteria criteria);
    public T getInitializedOne(DetachedCriteria criteria);

    public List<T> getAll(Class clazz);

    public T getByID(Class clazz, Serializable id);
    public T getInitializedByID(Class clazz, Serializable id);

    public DetachedCriteria getDetachedCriteria(Class clazz);
}
