package ua.kpi.model.dao.Mapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.kpi.model.dao.impl.columns.DeclarationSqlColumns;
import ua.kpi.model.entities.Declaration;
import ua.kpi.model.services.UserService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DeclarationMapper implements Mapper<Declaration> {

    private static final Logger LOGGER = LogManager.getLogger(DeclarationMapper.class);
    private static final String YEAR_ = "YEAR_";

    @Override
    public Declaration extractFromResultSet(ResultSet rs) throws SQLException {

        if (rs.getString(DeclarationSqlColumns.ID) == null) { // TODO consider returning non-null value. Possible to avoid the check?
            return null;
        }

        Declaration declaration = new Declaration.Builder()                         //Setting fields, defined in Builder
                .authorLogin(rs.getString(DeclarationSqlColumns.AUTHOR_LOGIN))
                .declarationYear(Declaration.DeclarationYear.valueOf(YEAR_ +
                        (rs.getString(DeclarationSqlColumns.DECLARATION_YEAR))))
                .taxCategory(Declaration.TaxCategory.valueOf
                        (rs.getString(DeclarationSqlColumns.TAX_CATEGORY)))
                .income(rs.getLong(DeclarationSqlColumns.INCOME))
                .taxSumDeclared(rs.getLong(DeclarationSqlColumns.TAX_SUM_DECLARED))
                .status(Declaration.Status.valueOf(rs.getString(DeclarationSqlColumns.STATUS)))
                .build();

        UserService userService = new UserService();
                                                                                                //Setting rest of fields
        declaration.setAuthor(userService.findClientByLogin(declaration.getAuthorLogin())); //TODO check for universal solution
        declaration.setId(rs.getInt(DeclarationSqlColumns.ID));
        declaration.setInspectorLogin(rs.getString(DeclarationSqlColumns.INSPECTOR_LOGIN));
        declaration.setCorrectionMessage(rs.getString(DeclarationSqlColumns.CORRECTION_MESSAGE));
        //TODO set author and inspector object references?

        LOGGER.info("New declaration object recreated from database via Mapper (id, author login): {}{}",
                declaration.getId(), declaration.getAuthorLogin());

        return declaration;
    }
}