import weka.core.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PatientARFF {

    private ArrayList<Attribute> atts;
    private ArrayList<String> ageVals;
    private ArrayList<String> menopauseVals;
    private ArrayList<String> tumor_sizeVals;
    private ArrayList<String> inv_nodesVals;
    private ArrayList<String> node_capsVals;
    private ArrayList<String> deg_maligVals;
    private ArrayList<String> breastVals;
    private ArrayList<String> breast_quadVals;
    private ArrayList<String> irradiatVals;
    private ArrayList<String> ClassVals;
    Instances       data;
    double[]        vals;
    int             i;


    PatientARFF() { // Constructor without data input
        // 1. set up attributes
        atts = new ArrayList<Attribute>();

        ageVals = new ArrayList<String>();
        String[] ageEnum = {"10-19", "20-29", "30-39", "40-49", "50-59", "60-69", "70-79", "80-89", "90-99"};
        for (i = 0; i < ageEnum.length; i++) {
            ageVals.add(ageEnum[i]);
        }
        atts.add(new Attribute("age", ageVals));

        menopauseVals = new ArrayList<String>();
        String[] menopauseEnum = {"lt40", "ge40", "premeno"};
        for (i = 0; i < menopauseEnum.length; i++) {
            menopauseVals.add(menopauseEnum[i]);
        }
        atts.add(new Attribute("menopause", menopauseVals));

        tumor_sizeVals = new ArrayList<String>();
        String[] tumor_sizeEnum = {"0-4", "5-9", "10-14", "15-19", "20-24", "25-29", "30-34", "35-39", "40-44", "45-49", "50-54", "55-59"};
        for (i = 0; i < tumor_sizeEnum.length; i++) {
            tumor_sizeVals.add(tumor_sizeEnum[i]);
        }
        atts.add(new Attribute("tumor-size", tumor_sizeVals));

        inv_nodesVals = new ArrayList<String>();
        String[] inv_nodesEnum = {"0-2", "3-5", "6-8", "9-11", "12-14", "15-17", "18-20", "21-23", "24-26", "27-29", "30-32", "33-35", "36-39"};
        for (i = 0; i < inv_nodesEnum.length; i++) {
            inv_nodesVals.add(inv_nodesEnum[i]);
        }
        atts.add(new Attribute("inv-nodes", inv_nodesVals));

        node_capsVals = new ArrayList<String>();
        String[] node_capsEnum = {"yes", "no"};
        for (i = 0; i < node_capsEnum.length; i++) {
            node_capsVals.add(node_capsEnum[i]);
        }
        atts.add(new Attribute("node-caps", node_capsVals));

        deg_maligVals = new ArrayList<String>();
        String[] deg_maligEnum = {"1", "2", "3"};
        for (i = 0; i < deg_maligEnum.length; i++) {
            deg_maligVals.add(deg_maligEnum[i]);
        }
        atts.add(new Attribute("deg-malig", deg_maligVals));

        breastVals = new ArrayList<String>();
        String[] breastEnum = {"left", "right"};
        for (i = 0; i < breastEnum.length; i++) {
            breastVals.add(breastEnum[i]);
        }
        atts.add(new Attribute("breast", breastVals));

        breast_quadVals = new ArrayList<String>();
        String[] breast_quadEnum = {"left_up", "left_low", "right_up", "right_low", "central"};
        for (i = 0; i < breast_quadEnum.length; i++) {
            breast_quadVals.add(breast_quadEnum[i]);
        }
        atts.add(new Attribute("breast-quad", breast_quadVals));

        irradiatVals = new ArrayList<String>();
        String[] irradiatEnum = {"yes", "no"};
        for (i = 0; i < irradiatEnum.length; i++) {
            irradiatVals.add(irradiatEnum[i]);
        }
        atts.add(new Attribute("irradiat", irradiatVals));


        ClassVals = new ArrayList<String>();
        String[] ClassEnum = {"no-recurrence-events","recurrence-events"};
        for (i = 0; i < ClassEnum.length; i++) {
            ClassVals.add(ClassEnum[i]);
        }
        atts.add(new Attribute("Class", ClassVals));

        // 2. create Instances object
        data = new Instances("breast-cancer", atts, 0);
    }

    PatientARFF(Patient patient) {
        new PatientARFF();
        addPatient(patient);
    }
    void addPatient(Patient patient) {
        vals = new double[data.numAttributes()];
        vals[0] = ageVals.indexOf(patient.getAge());
        vals[1] = menopauseVals.indexOf(patient.getMenopause());
        vals[2] = tumor_sizeVals.indexOf(patient.getTumor_size());
        vals[3] = inv_nodesVals.indexOf(patient.getInv_nodes());
        vals[4] = node_capsVals.indexOf(patient.getNode_caps());
        vals[5] = deg_maligVals.indexOf(patient.getDeg_malig());
        vals[6] = breastVals.indexOf(patient.getBreast());
        vals[7] = breast_quadVals.indexOf(patient.getBreast_quad());
        vals[8] = irradiatVals.indexOf(patient.getIrradiat());
        vals[9] = ClassVals.indexOf(patient.getClassi());

        data.add(new DenseInstance(1.0, vals));
    }
    @Override
    public String toString() {
        return data.toString();
    }

    public void writeARFF(String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(toString());
        writer.close();
    }
}
