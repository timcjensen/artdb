package db;

public class Specs {
    private int Spec_id;
    private String Dimensions;

    public Specs(int spec_id, String dimensions) {
        Spec_id = spec_id;
        Dimensions = dimensions;
    }

    public Specs() {
    }

    public int getSpec_id() {
        return Spec_id;
    }

    public void setSpec_id(int spec_id) {
        Spec_id = spec_id;
    }

    public String getDimensions() {
        return Dimensions;
    }

    public void setDimensions(String dimensions) {
        Dimensions = dimensions;
    }
}
