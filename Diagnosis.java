public class Diagnosis {
    private String patientID = "unknown";
    private String opinion = "no-recurrence-events";

    @Override
    public String toString() {
        return "Patient ID: " + patientID +
                ", Opinion: " + opinion + '\n';
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getOpinion() {
        return opinion;
    }
}
