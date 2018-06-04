package d14000.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hirokazu SUZUKI on 2018/06/04.
 * コントローラ
 */

@Controller // フレームワークにこのクラスがコントローラであることを伝えるアノテーション
@RequestMapping("d14000/") // URLを指定する
public class D14000Controller {
    // データを持っとくリスト Objectを適当なクラスに、hogeを適当な名前に変えて使う。
    private List<D14000MedicalRecord> carteList;
    private D14000MedicalRecordAvg avg; // 平均

    // コンストラクタ
    public D14000Controller() {
        this.carteList = new ArrayList<>(); // リストの初期化
        this.avg = new D14000MedicalRecordAvg(); // 平均の初期化
    }

    // 一覧表示
    @RequestMapping("list/")
    public String list(ModelMap modelMap) {
        modelMap.addAttribute("carteList", carteList); // モデルマップにリストを渡す
        return "d14000/list"; // テンプレートの場所
    }

    // 探索
    @RequestMapping("search/") // searchよりかはcalc
    public String search(
            @RequestParam("number") String num,
            @RequestParam("bmi") String bmi,
            @RequestParam("mets") String mets,
            @RequestParam("time") String time,
            ModelMap modelMap
    ) {
        // どんな探索が要求されるのかわからんので適当に実装
        if (num.isEmpty()) num = "0";

        if (carteList.size() <= Integer.parseInt(num)) {
            return "d14000/error";
        }

        D14000MedicalRecord carte = carteList.get(Integer.parseInt(num));

        modelMap.addAttribute("name", carte.name);

        if (bmi.isEmpty()) modelMap.addAttribute("weight", "-");
        else modelMap.addAttribute("weight", carte.calcIdealWeight(Double.parseDouble(bmi)));

        if (mets.isEmpty() || time.isEmpty()) modelMap.addAttribute("calorie", "-");
        else modelMap.addAttribute("calorie",
                carte.calcEnergyExpenditure(Double.parseDouble(time), Double.parseDouble(mets)));

        return "d14000/search";
    }

    // 追加
    @PostMapping("add")
    public String add(
            @RequestParam("name") String name, // name属性をnameにしたinputタグの内容が来る
            @RequestParam("height") double height, // name属性をheightにしたinputタグの内容が来る
            @RequestParam("weight") double weight // name属性をweightにしたinputタグの内容が来る
    ) {
        D14000MedicalRecord carte = new D14000MedicalRecord(name, weight, height);
        carteList.add(carte);
        if (carteList.size() > 0) {
            carteList.remove(avg);
            avg.add(carte);
            carteList.add(avg);
        }
        return "d14000/add";
    }

    // 削除
    @GetMapping("remove")
    public String remove(
            @RequestParam("num") int num // name属性をnumにしたinputタグの内容が来る
    ) {
        D14000MedicalRecord carte = carteList.get(num);
        avg.remove(carte);
        carteList.remove(num);
        if (carteList.size() == 1) {
            carteList.remove(avg);
        }
        return "d14000/remove";
    }
}
