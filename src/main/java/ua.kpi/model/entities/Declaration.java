package ua.kpi.model.entities;

public class Declaration {
                                                //Parameters assigned at construction
    private ClientUser author;
    private String authorLogin;
    private DeclarationYear declarationYear;
    private TaxCategory taxCategory;
    private long income;
    private long taxSumDeclared;
    private Status status;
                                                //Parameters set after construction
    private int id;
    private InspectorUser inspector;
    private String inspectorLogin;

                                                //Optional parameter
    private String correctionMessage;

                                                //Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public InspectorUser getInspector() {
        return inspector;
    }

    public void setInspector(InspectorUser inspector) {
        this.inspector = inspector;
    }

    public String getInspectorLogin() {
        return inspectorLogin;
    }

    public void setInspectorLogin(String inspectorLogin) {
        this.inspectorLogin = inspectorLogin;
    }

    public String getCorrectionMessage() {
        if(correctionMessage == null) {
            return ("Correction message not set.");
        }
        return correctionMessage;
    }

    public void setCorrectionMessage(String correctionMessage) {
        this.correctionMessage = correctionMessage;
    }

    public ClientUser getAuthor() {
        return author;
    }

    public void setAuthor(ClientUser author) {
        this.author = author;
    }

    public String getAuthorLogin() {
        return authorLogin;
    }

    public void setAuthorLogin(String authorLogin) {
        this.authorLogin = authorLogin;
    }

    public DeclarationYear getDeclarationYear() {
        return declarationYear;
    }

    public void setDeclarationYear(DeclarationYear declarationYear) {
        this.declarationYear = declarationYear;
    }

    public TaxCategory getTaxCategory() {
        return taxCategory;
    }

    public void setTaxCategory(TaxCategory taxCategory) {
        this.taxCategory = taxCategory;
    }

    public long getIncome() {
        return income;
    }

    public void setIncome(long income) {
        this.income = income;
    }

    public long getTaxSumDeclared() {
        return taxSumDeclared;
    }

    public void setTaxSumDeclared(long taxSumDeclared) {
        this.taxSumDeclared = taxSumDeclared;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
                                                                            //Member enums
    public enum DeclarationYear { //TODO consider changing to a period
        YEAR_2015("2015"), YEAR_2016("2016"), YEAR_2017("2017"), YEAR_2018("2018"), YEAR_2019("2019"), YEAR_2020("2020");
        //TODO check for more efficient solution

        private final String year;

        DeclarationYear(String year) {
            this.year = year;
        }

        public String getYear() {
            return year;
        }
    }

    public enum TaxCategory {
        PREFERENTIAL("new.declaration.preferential.tax"),
        EMPLOYEE("new.declaration.employee.tax"), ENTREPRENEUR(("new.declaration.entrepreneur.tax"));

        private final String resourceBundleKey;

        TaxCategory(String resourceBundleKey) {
            this.resourceBundleKey = resourceBundleKey;
        }

        public String getResourceBundleKey() {
            return resourceBundleKey;
        }
    }

    public enum Status {
        SUBMITTED("status.submitted"), UNDER_CORRECTION("status.under.correction"),
        APPROVED("status.approved"), APPEALED("status.appealed");

        private final String resourceBundleKey;

        Status(String resourceBundleKey) {
            this.resourceBundleKey = resourceBundleKey;
        }

        public String getResourceBundleKey() {
            return resourceBundleKey;
        }
    }
                                                                            //Builder
    private Declaration(Builder builder) {
        author = builder.author;
        authorLogin = builder.authorLogin;
        declarationYear = builder.declarationYear;
        taxCategory = builder.taxCategory;
        income = builder.income;
        taxSumDeclared = builder.taxSumDeclared;
        status = builder.status;
    }

    public static class Builder {

        private ClientUser author;
        private String authorLogin;
        private DeclarationYear declarationYear;
        private TaxCategory taxCategory;
        private long income;
        private long taxSumDeclared;
        private Status status;

        public Builder() {}

        public Builder author(ClientUser val) {
            author = val;
            return this;
        }

        public Builder authorLogin(String val) {
            authorLogin = val;
            return this;
        }

        public Builder declarationYear(DeclarationYear val) {
            declarationYear = val;
            return this;
        }

        public Builder taxCategory(TaxCategory val) {
            taxCategory = val;
            return this;
        }

        public Builder income(long val) {
            income = val;
            return this;
        }

        public Builder taxSumDeclared(long val) {
            taxSumDeclared = val;
            return this;
        }

        public Builder status(Status val) {
            status = val;
            return this;
        }

        public Declaration build() {
            return new Declaration(this);
        }
    }

    @Override
    public String toString() {
        return " \nid: " + getId() +
                "\nauthor: " + getAuthor().getFirstName() + " " + getAuthor().getSecondName() + " " + getAuthor().getLogin() +
                "\nassigned to inspector: " + getInspectorLogin() +
                "\ndeclarationYear: " + getDeclarationYear() +
                "\ntaxCategory: " + getTaxCategory() +
                "\nincome: " + getIncome() +
                "\ntaxSumDeclared: " + getTaxSumDeclared() +
                "\nstatus: " + getStatus().toString() +
                "\ncorrectionMessage" + getCorrectionMessage(); //TODO consider returning inspector
    }
}

