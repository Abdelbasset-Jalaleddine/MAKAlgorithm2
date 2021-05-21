package idpa.makalgorithm;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name = "RNA Sequences")
public class MainObject {

    @ElementList(name = "RNA" , required = false)
    private ArrayList<String> updateList;

    private String RNASequence;

    public MainObject(String RNASequence) {
        this.RNASequence = RNASequence;
    }

    public String getRNASequence() {
        return RNASequence;
    }

    public void setRNASequence(String RNASequence) {
        this.RNASequence = RNASequence;
    }

    public void addUpdate(String RNASequence) {
        try {
            if (updateList == null) {
                updateList = new ArrayList<String>();
            }
            updateList.add(RNASequence);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String getUpdate(int i) {
            return updateList.get(i);
    }
    public int sizeUpdate() {
        return updateList.size();
    }
}
