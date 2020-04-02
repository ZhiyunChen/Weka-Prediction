public class Patient {

    private String patientID = "unknown";
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


    @Override
    public String toString() {
        return "Patient ID: "        + patientID +
                "\n  age = "         + age +
                "\n  menopause = "   + menopause +
                "\n  tumor-size = "  + tumor_size +
                "\n  inv-nodes = "   + inv_nodes +
                "\n  node-caps = "   + node_caps +
                "\n  deg-malig = "   + deg_malig +
                "\n  breast = "      + breast +
                "\n  breast_quad = " + breast_quad +
                "\n  irradiat = "    + irradiat +
                "\n  Class = "       + Class ;
    }

    public String getPatientID(){
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMenopause() {
        return menopause;
    }

    public void setMenopause(String menopause) {
        this. menopause = menopause;
    }

    public String getBreast() {
        return breast;
    }

    public String getBreast_quad() {
        return breast_quad;
    }

    public String getDeg_malig() {
        return deg_malig;
    }

    public String getInv_nodes() {
        return inv_nodes;
    }

    public String getIrradiat() {
        return irradiat;
    }

    public String getNode_caps() {
        return node_caps;
    }

    public String getTumor_size() {
        return tumor_size;
    }

    public void setBreast(String breast) {
        this.breast = breast;
    }

    public void setBreast_quad(String breast_quad) {
        this.breast_quad = breast_quad;
    }

    public void setDeg_malig(String deg_malig) {
        this.deg_malig = deg_malig;
    }

    public void setInv_nodes(String inv_nodes) {
        this.inv_nodes = inv_nodes;
    }

    public void setIrradiat(String irradiat) {
        this.irradiat = irradiat;
    }

    public void setNode_caps(String node_caps) {
        this.node_caps = node_caps;
    }

    public void setTumor_size(String tumor_size) {
        this.tumor_size = tumor_size;
    }

    public void setClassi(String aClass) { Class = aClass; }

    public String getClassi() { return Class; }
}