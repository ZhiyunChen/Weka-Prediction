import org.jetbrains.annotations.NotNull;
import weka.classifiers.bayes.NaiveBayesUpdateable;
import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.evaluation.Prediction;
import weka.classifiers.trees.J48;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.ConverterUtils.DataSource;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class Predict {

    private static String trainLocation;
    private static String testLocation;

    public Predict(String train, String test){
        trainLocation = train;
        testLocation = test;
    }

    public void Run() throws Exception {
        // load data
        DataSource s = new DataSource(trainLocation);
        Instances train = s.getDataSet();
        train.setClassIndex(train.numAttributes() - 1);

        J48 model = new J48();
        model.buildClassifier(train);

        DataSource t = new DataSource(testLocation);
        Instances test = t.getDataSet();
        test.setClassIndex(test.numAttributes() - 1);


        // output generated model
        System.out.println(model);
        System.out.println(train.numInstances());

   //    predictions.forEach((v) -> System.out.println(v.actual()));

        for (int i = 0; i < test.numInstances()/10; i++) {
            double ac = test.instance(i).classValue();
            String a = test.classAttribute().value((int) ac);
            Instance I = test.instance(i);
            double p = model.classifyInstance(I);

            String pre = test.classAttribute().value((int) p);
            System.out.println(a + " , " + pre);
        }
    }
}