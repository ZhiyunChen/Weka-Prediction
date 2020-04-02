import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

public class ResponseXMLParser {

    private String fileName;
    private Response response;

    ResponseXMLParser(String fileName) throws Exception {
        this.fileName = fileName;
        parseResponseXML();
    }

    void parseResponseXML() throws Exception {

        //Create a Response instance
        response = new Response();

        //Get Docuemnt Builder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        //Build Document
        Document document = builder.parse(new File(fileName));

        //Normalize the XML Structure; It's just too important !!
        document.getDocumentElement().normalize();

        //Here comes the root node
        Element root = document.getDocumentElement();
        System.out.println(root.getNodeName());
        response.setResponseID(root.getAttribute("responseID"));
        response.setRequestID(root.getAttribute("requestID"));
        response.setSenderIPAddress(root.getAttribute("senderIPAddress"));
        response.setReceiverIPAddress(root.getAttribute("receiverIPAddress"));

        //Get all instances
        NodeList nList = document.getElementsByTagName("diagnosis");
        ArrayList<Diagnosis> diagnosisArrayList = new ArrayList<Diagnosis>();
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Diagnosis diagnosis = new Diagnosis();
            Node node = nList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                //Print each patient's detail
                Element eElement = (Element) node;
                diagnosis.setPatientID(eElement.getAttribute("patientID"));
                diagnosis.setOpinion(eElement.getElementsByTagName("opinion").item(0).getTextContent());
            }
            diagnosisArrayList.add(diagnosis);
        }
        response.setDiagnosisArrayList(diagnosisArrayList);
    }

    public Response getResponse() {
        return response;
    }
}