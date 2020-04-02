import java.io.File;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) throws Exception {
        // Generate an ARFF file from the file "request001.xml"
        Request request;
        String fileName = "request001.xml";
        RequestXMLParser requestXmlParser = new RequestXMLParser(fileName);

        // Generate a request instance from the file "request001.xml"
        request = requestXmlParser.getRequest();
        System.out.println(request);
        System.out.println('\n');

        // Generate a response instance from the request instance
        RequestToResponse requestToResponse = new RequestToResponse(request);
        requestToResponse.run();
        Response response = requestToResponse.getResponse();
        System.out.println(response);
        System.out.println('\n');

        // Generate an XML file from response instance
        ResponseXMLGenerator responseXMLGenerator = new ResponseXMLGenerator(response);
        responseXMLGenerator.writeXML("newResponse.xml");

    }
}

