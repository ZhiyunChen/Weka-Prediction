import java.io.*;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.apache.xerces.jaxp.DocumentBuilderFactoryImpl;
import org.apache.xml.serialize.XMLSerializer;
import org.apache.xml.serialize.OutputFormat;

public class ResponseXMLGenerator {

    // XML tag's
    private static final String TAG_RESPONSE     = "response";
    private static final String TAG_DIAGNOSIS     = "diagnosis";
    private static final String TAG_OPINION         = "opinion";

    // XML Settings
    private static final String XML_VERSION        = "1.0";
    private static final String XML_ENCODING       = "UTF-8";

    // Variables
    private Document xmlDoc        = null;
    private String   xmlStr        = null;

    private String opinion = "";

    private Response response;
    // Constructor
    public ResponseXMLGenerator(Response response ) {

        this.response = response;

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
        Element factor;

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
        root = xmlDoc.createElement(TAG_RESPONSE);
        root.setAttribute("requestID", response.getRequestID());
        root.setAttribute("responseID", response.getRequestID());
        root.setAttribute("senderIPAddress", response.getSenderIPAddress());
        root.setAttribute("receiverIPAddress", response.getReceiverIPAddress());


        // Add patient Element and its value
        for (int i = 0; i < response.getDiagnosisArrayList().size(); i++) {
            item = xmlDoc.createElement(TAG_DIAGNOSIS);
            item.setAttribute("patientID", response.getDiagnosisArrayList().get(i).getPatientID());

            factor = xmlDoc.createElement(TAG_OPINION);
            factor.appendChild(xmlDoc.createTextNode(response.getDiagnosisArrayList().get(i).getOpinion()));
            item.appendChild(factor);

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