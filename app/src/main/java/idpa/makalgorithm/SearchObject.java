package idpa.makalgorithm;

public class SearchObject {
    private String name;
    private double similarity;

    public SearchObject(String name, double similarity) {
        this.name = name;
        this.similarity = similarity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSimilarity() {
        return similarity;
    }

    public void setSimilarity(double similarity) {
        this.similarity = similarity;
    }
}
