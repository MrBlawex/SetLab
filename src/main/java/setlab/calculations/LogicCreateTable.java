package setlab.calculations;

import javafx.scene.control.TableView;
import setlab.cores.LogicCore.TableTruth;

import java.util.ArrayList;

public class LogicCreateTable {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("x");
        list.add("y");
        list.add("z");
        TableTruth t1 = new TableTruth(list, "(x∧y)~z");
        t1.printTable();
    }

    public static TableView<String> getTable() {
        TableView<String> table = new TableView<>();


        return table;
    }

}
