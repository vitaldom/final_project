package ua.kpi.model.entities;

/**
 * Represents a client request for change of tax inspector. Can be submitted by client user in case a declaration was
 * declined by an inspector user.
 */
public class InspectorChangeRequest {

    private int id;
    private int declarationID;
    private String authorLogin;
    private String inspectorLogin;
    private String requestReason;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDeclarationID() {
        return declarationID;
    }

    public void setDeclarationID(int declarationID) {
        this.declarationID = declarationID;
    }

    public String getAuthorLogin() {
        return authorLogin;
    }

    public void setAuthorLogin(String authorLogin) {
        this.authorLogin = authorLogin;
    }

    public String getInspectorLogin() {
        return inspectorLogin;
    }

    public void setInspectorLogin(String inspectorLogin) {
        this.inspectorLogin = inspectorLogin;
    }

    public String getRequestReason() {
        return requestReason;
    }

    public void setRequestReason(String requestReason) {
        this.requestReason = requestReason;
    }

    public InspectorChangeRequest(Declaration declaration, String requestReason) {
        this.declarationID = declaration.getId();
        this.authorLogin = declaration.getAuthorLogin();
        this.inspectorLogin = declaration.getInspectorLogin();
        this.requestReason = requestReason;
    }

    @Override
    public String toString() {
        return " \nid: " + getId() +
                "\ndeclarationId: " + getDeclarationID() +
                "\nauthor login: " + getAuthorLogin() +
                "\nassigned to inspector: " + getInspectorLogin() +
                "\nrequestReason" + getRequestReason();
    }
}

