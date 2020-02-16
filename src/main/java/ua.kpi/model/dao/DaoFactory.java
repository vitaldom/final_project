package ua.kpi.model.dao;

import ua.kpi.model.dao.impl.JdbcDaoFactory;

public abstract class DaoFactory {

	public abstract UserDao createUserDao();
	public abstract DeclarationDao createDeclarationDao();
	public abstract InspectorChangeRequestDao createInspectorChangeRequestDao();

	private static class InstanceHolder {
		private static final DaoFactory INSTANCE = new JdbcDaoFactory();
	}

	public static DaoFactory getInstance() {
		return InstanceHolder.INSTANCE;
	}

	protected DaoFactory() {
	}
}
