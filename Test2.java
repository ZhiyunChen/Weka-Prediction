import java.util.ArrayList;

public class Test2 {
    public static void main(String[] args) throws Exception {
        // Generate a response instance from the file "request001.xml"
        Response response;
        ResponseXMLParser responseXMLParser = new ResponseXMLParser("response001.xml");
        response = responseXMLParser.getResponse();
        System.out.println(response);
        System.out.println('\n');

        // Generate an XML file from response
        ResponseXMLGenerator responseXMLGenerator = new ResponseXMLGenerator(response);
        responseXMLGenerator.writeXML("newResponse.xml");
    }
}
