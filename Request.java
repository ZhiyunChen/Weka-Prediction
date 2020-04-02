import java.io.IOException;
import java.util.ArrayList;

public class Request
{
    private static int idNumber = 0;
    private String requestID;
    private String senderIPAddress;
    private String receiverIPAddress;
    private ArrayList<Patient> patientArrayList;
    private String arffFileName;

    public Request() {
        idNumber++;
        requestID = "request#" + Integer.toString(idNumber);
        senderIPAddress = "";
        receiverIPAddress = "";
        patientArrayList = new ArrayList<Patient>();
        arffFileName = requestID + ".arff";
    }

    @Override
    public String toString() {
        String requestString ="[requestID: " + requestID
                + "\n  sender's IP address: " + senderIPAddress
                + "\n  receiver's IP address: "   + receiverIPAddress + ", \n";
        for (int i = 0; i < patientArrayList.size(); i++) {
            requestString += patientArrayList.get(i);
        }
        requestString += "]";
        return requestString;
    }

    public void writeARFF() throws IOException {
        PatientARFF patientARFF = new PatientARFF();
        for (int i = 0; i < patientArrayList.size(); i++) {
            patientARFF.addPatient(patientArrayList.get(i));
        }
        patientARFF.writeARFF(arffFileName);
    }

    public String getArffFileName() {
        return arffFileName;
    }

    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public String getSenderIPAddress() {
        return senderIPAddress;
    }

    public void setSenderIPAddress(String senderIPAddress) {
        this.senderIPAddress = senderIPAddress;
    }

    public String getReceiverIPAddress() {
        return receiverIPAddress;
    }

    public void setReceiverIPAddress(String receiverIPAddress) {
        this.receiverIPAddress = receiverIPAddress;
    }

    public ArrayList<Patient> getPatientArrayList() {
        return patientArrayList;
    }

    public void setPatientArrayList(ArrayList<Patient> patientArrayList) {
        this.patientArrayList = patientArrayList;
    }

}