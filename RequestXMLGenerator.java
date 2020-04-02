import java.io.*;
import java.util.*;
import java.text.*;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.apache.xerces.jaxp.DocumentBuilderFactoryImpl;
import org.apache.xerces.jaxp.DocumentBuilderImpl;
import org.apache.xml.serialize.XMLSerializer;
import org.apache.xml.serialize.OutputFormat;
import org.w3c.dom.ls.*;

public class RequestXMLGenerator {

    // XML tag's
    private static final String TAG_REQUEST     = "request";
    private static final String TAG_PATIENT     = "patient";
    private static final String TAG_AGE         = "age";
    private static final String TAG_MENOPAUSE   = "menopause";
    private static final String TAG_TUMOR_SIZE  = "tumor-size";
    private static final String TAG_INV_NODES   = "inv-nodes";
    private static final String TAG_NODE_CAPS   = "node-caps";
    private static final String TAG_DEG_MALIG   = "deg-malig";
    private static final String TAG_BREAST      = "breast";
    private static final String TAG_BREAST_QUAD = "breast-quad";
    private static final String TAG_IRRADIAT    = "irradiat";
    private static final String TAG_CLASS       = "Class";


    // XML Settings
    private static final String XML_VERSION        = "1.0";
    private static final String XML_ENCODING       = "UTF-8";

    // Variables
    private Document xmlDoc        = null;
    private String   xmlStr        = null;

    private String patient = "";
    private String age = "";
    private String menopause = "";
    private String tumor_size = "";
    private String inv_nodes = "";
    private String node_caps = "";
    private String deg_malig = "";
    private String breast = "";
    private String breast_quad = "";
    private String irradiat = "";
    private String Class = "";

    private Request request;
    // Constructor
    public RequestXMLGenerator(Request request ) {

        this.request = request;

        // Generate the XML Document using DOM
        this.generateXMLDocument();

        // Generate a XML String
        this.generateXMLString();
    }

    // Retrive probe message as XML string
    public String getXMLString() {
        return xmlStr;
    }

    // Generate a DOM XML document
    private void generateXMLDocument()
    {
        Element main;
        Element root;
        Element item;
        Element factor1;
        Element factor2;
        Element factor3;
        Element factor4;
        Element factor5;
        Element factor6;
        Element factor7;
        Element factor8;
        Element factor9;
        Element factor10;

        try {

            //Create a XML Document
            DocumentBuilderFactory dbFactory =
                    DocumentBuilderFactoryImpl.newInstance();
            DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();
            xmlDoc = docBuilder.newDocument();
        } catch(Exception e) {
            System.out.println("Error " + e);
        }

        // Create the root element
        root = xmlDoc.createElement(TAG_REQUEST);
        root.setAttribute("requestID", request.getRequestID());
        root.setAttribute("senderIPAddress", request.getSenderIPAddress());
        root.setAttribute("receiverIPAddress", request.getReceiverIPAddress());


        // Add patient Element and its value
        for (int i = 0; i < request.getPatientArrayList().size(); i++) {
            item = xmlDoc.createElement(TAG_PATIENT);
            item.setAttribute("patientID", request.getPatientArrayList().get(i).getPatientID());

            factor1 = xmlDoc.createElement(TAG_AGE);
            factor1.appendChild(xmlDoc.createTextNode(request.getPatientArrayList().get(i).getAge()));
            item.appendChild(factor1);

            factor2 = xmlDoc.createElement(TAG_MENOPAUSE);
            factor2.appendChild(xmlDoc.createTextNode(request.getPatientArrayList().get(i).getMenopause()));
            item.appendChild(factor2);

            factor3 = xmlDoc.createElement(TAG_TUMOR_SIZE);
            factor3.appendChild(xmlDoc.createTextNode(request.getPatientArrayList().get(i).getTumor_size()));
            item.appendChild(factor3);

            factor4 = xmlDoc.createElement(TAG_INV_NODES);
            factor4.appendChild(xmlDoc.createTextNode(request.getPatientArrayList().get(i).getInv_nodes()));
            item.appendChild(factor4);

            factor5 = xmlDoc.createElement(TAG_NODE_CAPS);
            factor5.appendChild(xmlDoc.createTextNode(request.getPatientArrayList().get(i).getNode_caps()));
            item.appendChild(factor5);

            factor6 = xmlDoc.createElement(TAG_DEG_MALIG);
            factor6.appendChild(xmlDoc.createTextNode(request.getPatientArrayList().get(i).getDeg_malig()));
            item.appendChild(factor6);

            factor7 = xmlDoc.createElement(TAG_BREAST);
            factor7.appendChild(xmlDoc.createTextNode(request.getPatientArrayList().get(i).getBreast()));
            item.appendChild(factor7);

            factor8 = xmlDoc.createElement(TAG_BREAST_QUAD);
            factor8.appendChild(xmlDoc.createTextNode(request.getPatientArrayList().get(i).getBreast_quad()));
            item.appendChild(factor8);

            factor9 = xmlDoc.createElement(TAG_IRRADIAT);
            factor9.appendChild(xmlDoc.createTextNode(request.getPatientArrayList().get(i).getIrradiat()));
            item.appendChild(factor9);

            factor10 = xmlDoc.createElement(TAG_CLASS);
            factor10.appendChild(xmlDoc.createTextNode(request.getPatientArrayList().get(i).getClassi()));
            item.appendChild(factor10);

            root.appendChild(item);
        }
        // Add to the root Element
        xmlDoc.appendChild(root);
    }

    // Generate String out of the XML document object
    private void generateXMLString() {

        StringWriter  strWriter    = null;
        XMLSerializer probeMsgSerializer   = null;
        OutputFormat  outFormat    = null;

        try {
            probeMsgSerializer = new XMLSerializer();
            strWriter = new StringWriter();
            outFormat = new OutputFormat();

            // Setup format settings
            outFormat.setEncoding(XML_ENCODING);
            outFormat.setVersion(XML_VERSION);
            outFormat.setIndenting(true);
            outFormat.setIndent(4);

            // Define a Writer
            probeMsgSerializer.setOutputCharStream(strWriter);

            // Apply the format settings
            probeMsgSerializer.setOutputFormat(outFormat);

            // Serialize XML Document
            probeMsgSerializer.serialize(xmlDoc);
            this.xmlStr = strWriter.toString();
            strWriter.close();

        } catch (IOException ioEx) {
            System.out.println("Error " + ioEx);
        }
    }
    public void writeXML(String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(getXMLString());
        writer.close();
    }

}