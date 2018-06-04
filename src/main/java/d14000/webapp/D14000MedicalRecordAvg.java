package d14000.webapp;

/**
 * Created by Hirokazu SUZUKI on 2018/06/04.
 * カルテの平均
 */
public class D14000MedicalRecordAvg extends D14000MedicalRecord {

    private int total;

    public D14000MedicalRecordAvg() {
        super("平均", 0, 0);
    }

    public void add(D14000MedicalRecord carte) {
        height += carte.height;
        weight += carte.weight;
        total++;
    }

    public void remove(D14000MedicalRecord carte) {
        height -= carte.height;
        weight -= carte.weight;
        total--;
    }

    @Override
    public boolean isFat() {
        return false;
    }

    @Override
    public double getWeight() {
        return weight / total;
    }

    @Override
    public double getHeight() {
        return height / total;
    }
}
