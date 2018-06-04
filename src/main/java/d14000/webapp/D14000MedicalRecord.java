package d14000.webapp;

/**
 * Created by Hirokazu SUZUKI on 2018/06/04.
 * ダミーのクラス テスト問題によって適宜クラス名とか中身をリファクタリングする
 */
public class D14000MedicalRecord {
    protected String name; //名前
    protected double weight;   // 体重
    protected double height;   // 身長

    // コンストラクタ
    public D14000MedicalRecord(String name, double weight, double height) {
        this.name = name;
        this.weight = weight;
        this.height = height;
    }

    // BMIを計算する
    public double getBmi() {
        double height_m = getHeight() * 0.01;
        return getWeight() / (height_m * height_m);
    }

    // BMIによる分類（クラス）を返す
    public String getBmiClass() {
        double bmi = getBmi();
        if (bmi < 18.5) {
            return "痩せ";
        } else if (bmi < 25.0) {
            return "普通";
        } else {
            return "肥満";
        }
    }

    // 肥満かどうか判定
    public boolean isFat() {
        return getBmi() > 25;
    }

    // 指定したBMIとなる理想の体重を計算する
    public double calcIdealWeight(double bmi) {
        double height_m = height * 0.01;
        return bmi * height_m * height_m;
    }

    // METsと運動時間[h]から消費エネルギーを計算する
    public double calcEnergyExpenditure(double hours, double mets) {
        return mets * weight * hours * 1.05;
    }

    // 以下Getter

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }
}
