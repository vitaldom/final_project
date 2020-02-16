package ua.kpi.model.dao;

public interface GenericDao<T> extends AutoCloseable {

        boolean create (T entity);

        void close();
}
