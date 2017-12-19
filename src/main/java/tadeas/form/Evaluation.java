package tadeas.form;


public class Evaluation {

    private String acceptanceMessage;
    private Boolean acceptance;

    public String getAcceptanceMessage() {
        return acceptanceMessage;
    }

    public void setAcceptanceMessage(String acceptanceMessage) {
        this.acceptanceMessage = acceptanceMessage;
    }

    public Boolean getAcceptance() {
        return acceptance;
    }

    public void setAcceptance(Boolean acceptance) {
        this.acceptance = acceptance;
    }

    @Override
    public String toString() {
        return "Evaluation{" +
                "acceptanceMessage='" + acceptanceMessage + '\'' +
                ", acceptance=" + acceptance +
                '}';
    }
}
