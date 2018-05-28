package setlab.s;

import setlab.cores.BinRelCore.BinRel;

import java.util.HashMap;

public class BinRelTypesGraph {

    private final static HashMap<String, BinRel> MAP = new HashMap<>();

    static {
        MAP.put("penta", new BinRel("", "((1,4),(2,5),(3,1),(4,2),(5,3))"));
        MAP.put("line", new BinRel("", "{(1,2)}"));
        MAP.put("smile", new BinRel("", "{(1,1),(2,2),(3,4),(4,5),(5,3)}"));
        MAP.put("time", new BinRel("", "{(1,4),(4,5),(5,2),(2,1),(3,3),(6,6)}"));
        MAP.put("star", new BinRel("", "{(1,4),(5,2),(3,6)}"));
        MAP.put("madness", new BinRel("", getLine(10)));
        MAP.put("trash", new BinRel("", getLine(16)));
        MAP.put("madgraph", new BinRel("", getLine(20)));
    }

    private static String getLine(int n) {
        StringBuilder line = new StringBuilder("{");
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                line.append("(").append(i).append(",").append(j).append(")");
            }
            line.append(",");
        }
        line.deleteCharAt(line.length() - 1);
        line.append("}");
        return line.toString();
    }

    public static boolean contain(String key) {
        return MAP.containsKey(key);
    }

    public static BinRel get(String command) {
        return MAP.get(command);
    }
}
