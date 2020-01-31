package ua.kpi.model.dao;

public interface GenericDao<T> extends AutoCloseable {

        boolean create (T entity);

//        T get(int id);
//
//        void update(T entity);
//
//        void delete(int id);

        void close();
}
