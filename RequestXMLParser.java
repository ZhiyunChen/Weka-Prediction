import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class RequestXMLParser {

    private String fileName;
    private Request request;

    RequestXMLParser(String fileName) throws Exception {
        this.fileName = fileName;
        parseRequestXML();
    }

    void parseRequestXML() throws Exception {

        //Create a Request instance
        request = new Request();

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
        request.setRequestID(root.getAttribute("requestID"));
        request.setSenderIPAddress(root.getAttribute("senderIPAddress"));
        request.setReceiverIPAddress(root.getAttribute("receiverIPAddress"));

        //Get all instances
        NodeList nList = document.getElementsByTagName("patient");
        ArrayList<Patient> patientArrayList = new ArrayList<Patient>();
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Patient patient = new Patient();
            Node node = nList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                //Print each patient's detail
                Element eElement = (Element) node;
                patient.setPatientID(eElement.getAttribute("patientID"));
                patient.setAge(eElement.getElementsByTagName("age").item(0).getTextContent());
                patient.setMenopause(eElement.getElementsByTagName("menopause").item(0).getTextContent());
                patient.setTumor_size(eElement.getElementsByTagName("tumor-size").item(0).getTextContent());
                patient.setInv_nodes(eElement.getElementsByTagName("inv-nodes").item(0).getTextContent());
                patient.setNode_caps(eElement.getElementsByTagName("node-caps").item(0).getTextContent());
                patient.setDeg_malig(eElement.getElementsByTagName("deg-malig").item(0).getTextContent());
                patient.setBreast(eElement.getElementsByTagName("breast").item(0).getTextContent());
                patient.setBreast_quad(eElement.getElementsByTagName("breast-quad").item(0).getTextContent());
                patient.setIrradiat(eElement.getElementsByTagName("irradiat").item(0).getTextContent());
                patient.setClassi(eElement.getElementsByTagName("Class").item(0).getTextContent());
            }
            patientArrayList.add(patient);
        }
        request.setPatientArrayList(patientArrayList);

    }

    public Request getRequest() {
        return request;
    }

}