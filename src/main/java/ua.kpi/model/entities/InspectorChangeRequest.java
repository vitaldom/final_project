package ua.kpi.model.entities;

public class InspectorChangeRequest {

    private int id;
    private int declarationID;
//    private ClientUser author;
    private String authorLogin;
//    private InspectorUser inspector;
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

    //                                                                            //Builder
//    private InspectorChangeRequest(Builder builder) {
//        author = builder.author;
//        authorLogin = builder.authorLogin;
//        declarationYear = builder.declarationYear;
//        taxCategory = builder.taxCategory;
//        income = builder.income;
//        taxSumDeclared = builder.taxSumDeclared;
//        status = builder.status;
//    }
//
//    public static class Builder {
//
//        private ClientUser author;
//        private String authorLogin;
//        private DeclarationYear declarationYear;
//        private TaxCategory taxCategory;
//        private long income;
//        private long taxSumDeclared;
//        private Status status;
//
//        public Builder() {}
//
//        public Builder author(ClientUser val) {
//            author = val;
//            return this;
//        }
//
//        public Builder authorLogin(String val) {
//            authorLogin = val;
//            return this;
//        }
//
//        public Builder declarationYear(DeclarationYear val) {
//            declarationYear = val;
//            return this;
//        }
//
//        public Builder taxCategory(TaxCategory val) {
//            taxCategory = val;
//            return this;
//        }
//
//        public Builder income(long val) {
//            income = val;
//            return this;
//        }
//
//        public Builder taxSumDeclared(long val) {
//            taxSumDeclared = val;
//            return this;
//        }
//
//        public Builder status(Status val) {
//            status = val;
//            return this;
//        }
//
//        public InspectorChangeRequest build() {
//            return new InspectorChangeRequest(this);
//        }
//    }

    @Override
    public String toString() {
        return " \nid: " + getId() +
                "\ndeclarationId: " + getDeclarationID() +
                "\nauthor login: " + getAuthorLogin() +
                "\nassigned to inspector: " + getInspectorLogin() +
                "\nrequestReason" + getRequestReason();
    }
}

