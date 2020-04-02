import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.Utils;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;

import java.io.File;
import java.util.Random;

public class CrossValidationSingleRun {

    String Location;
    public CrossValidationSingleRun(String file){
        Location = file;
    }

    public void SingleRun() throws Exception {
        // loads data and set class index
        DataSource s = new DataSource(Location);
        Instances data = s.getDataSet();
        data.setClassIndex(data.numAttributes() - 1);

        // classifier
        String[] tmpOptions = new String[3];
        String classname;
        tmpOptions[0] = "weka.classifiers.trees.J48";
        tmpOptions[1] = "-C";
        tmpOptions[2] = "0.25";
        classname      = tmpOptions[0];
        tmpOptions[0]  = "";
        Classifier cls = (Classifier) Utils.forName(Classifier.class, classname, tmpOptions);

        // other options
        int seed = 1;
        int folds = 10;


        // randomize data
        Random rand = new Random(seed);
        Instances randData = new Instances(data);
        randData.randomize(rand);
        if (randData.classAttribute().isNominal())
            randData.stratify(folds);

        // perform cross-validation
        System.out.println();
        System.out.println("=== Setup ===");
        System.out.println("Classifier: " + cls.getClass().getName() + " " );
        System.out.println("Dataset: " + data.relationName());
        System.out.println("Folds: " + folds);
        System.out.println("Seed: " + seed);
        System.out.println();
        Evaluation evalAll = new Evaluation(randData);
        for (int n = 0; n < folds; n++) {
            Evaluation eval = new Evaluation(randData);
            Instances train = randData.trainCV(folds, n, rand);
            Instances test = randData.testCV(folds, n);

            // build and evaluate classifier
            Classifier clsCopy = weka.classifiers.AbstractClassifier.makeCopy(cls);
            clsCopy.buildClassifier(train);
            eval.evaluateModel(clsCopy, test);
            evalAll.evaluateModel(clsCopy, test);

            // output evaluation
            System.out.println();
            System.out.println(eval.toMatrixString("=== Confusion matrix for fold " + (n + 1) + "/" + folds + " ===\n"));
        }

        // output evaluation
        System.out.println();
        System.out.println(evalAll.toSummaryString("=== " + folds + "-fold Cross-validation ===", false));
    }
}