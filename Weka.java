

public class Weka {

    public static void main(String[] args) throws Exception {
        //CrossValidationSingleRun SingleCrossRun = new CrossValidationSingleRun("breast-cancer.arff");
        //SingleCrossRun.SingleRun();

        String fileName = "request001.xml";
        RequestXMLParser requestXmlParser = new RequestXMLParser(fileName);
        String arffFileName = fileName.substring(0,fileName.length()-3) + "arff";

        Predict2 p = new Predict2("breast-cancer.arff", arffFileName);
        p.Run();
    }
}
