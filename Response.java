import java.util.ArrayList;

public class Response
{
    private static int idNumber = 0;
    private String responseID;
    private String requestID;
    private String senderIPAddress;
    private String receiverIPAddress;
    private ArrayList<Diagnosis> diagnosisArrayList;

    Response() {
        idNumber++;
        responseID = "response#" + Integer.toString(idNumber);
        requestID = "";
        senderIPAddress = "";
        receiverIPAddress = "";
        diagnosisArrayList = new ArrayList<Diagnosis>();
    }

    @Override
    public String toString() {
        String responseString ="[ responseID:            " + responseID
                           + "\n  requestID:             " + requestID
                           + "\n  sender's IP address:   " + senderIPAddress
                           + "\n  receiver's IP address: "   + receiverIPAddress
                           + '\n';
        for (int i = 0; i < diagnosisArrayList.size(); i++) {
            responseString += diagnosisArrayList.get(i);
        }
        responseString += "]";
        return responseString;
    }

    public String getResponseID() {
        return responseID;
    }

    public void setResponseID(String responseID) {
        this.responseID = responseID;
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

    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public ArrayList<Diagnosis> getDiagnosisArrayList() {
        return diagnosisArrayList;
    }

    public void setDiagnosisArrayList(ArrayList<Diagnosis> diagnosisArrayList) {
        this.diagnosisArrayList = diagnosisArrayList;
    }
}
