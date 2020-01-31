package ua.kpi.model.dao.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Mapper<T> {

    T extractFromResultSet(ResultSet rs) throws SQLException;
}
