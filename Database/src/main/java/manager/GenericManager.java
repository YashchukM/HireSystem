package manager;

import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Andrii on 28/07/2015.
 */
@Repository
public interface GenericManager<T> {
    public Serializable save(T entity);
    public void update(T entity);
    public void delete(T entity);

    public T getById(Serializable id);
    public T getInitializedById(Serializable id);

    public GenericQuery<T> query();

    public interface GenericQuery<T> {
        public List<T> find(int firstResult, int maxResults);
        public List<T> findInitialized(int firstResult, int maxResults);
    }
}
