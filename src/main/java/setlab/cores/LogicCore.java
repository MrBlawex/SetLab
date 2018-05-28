package setlab.cores;

import setlab.calculations.ReversePolish_Bool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class LogicCore {

    private static int inv(int a) {
        return (a + 1) % 2;
    }

    public static int or(int a, int b) {
        boolean x = bin(a);
        boolean y = bin(b);
        return x || y ? 1 : 0;
    }

    public static int and(int a, int b) {
        boolean x = bin(a);
        boolean y = bin(b);
        return x && y ? 1 : 0;
    }

    public static int imp(int a, int b) {
        if (a == 1 && b == 0) {
            return 0;
        }
        return 1;
    }

    public static int eq(int a, int b) {
        boolean x = bin(a);
        boolean y = bin(b);
        return x == y ? 1 : 0;
    }

    public static int oppJ(int a, int b) {
        return inv(eq(a, b));
    }

    public static int sh(int a, int b) {
        return inv(and(a, b));
    }

    public static int arrP(int a, int b) {
        return inv(or(a, b));
    }

    private static boolean bin(int b) {
        return b != 0;
    }

    public static class TableTruth {

        private final static HashMap<String, Integer> mapOp = new HashMap<>();
        static HashSet<String> atoms = new HashSet<>();

        static {
            mapOp.put("∨", 1);
            mapOp.put("∧", 2);
            mapOp.put("→", 1);
            mapOp.put("~", 1);
            mapOp.put("⊕", 1);
            mapOp.put("↑", 1);
            mapOp.put("↓", 1);
            mapOp.put("¬", 3);
        }

        final HashMap<String, Integer[]> columns = new HashMap<>();
        int height;

        public TableTruth(ArrayList<String> atomsNameList, String form) {
            // creating colums for atoms
            atoms.addAll(atomsNameList);
            height = (int) Math.pow(2, atoms.size());
            int k = height / 2;
            for (String atomName : atoms) {
                Integer[] t = new Integer[height];

                for (int i = 0; i < height; i++) {
                    t[i] = (i / k) % 2;
                }
                k -= 2;
                k = k == 0 ? 1 : k;
                columns.put(atomName, t);
            }
            // creating colums for expressions
            String expession = ReversePolish_Bool.getStr(form);

        }

        private void addCol(String name, String op) {
            Integer[] t = new Integer[height];
            int id = 0;
            for (int i = 0; i < height; i++) {
                t[i] = calk(op);
            }
            columns.put(name, t);
        }

        private int calk(String ex) {

            return 0;
        }

        public void printTable() {
            for (int i = 0; i < height; i++) {
                for (String keys : columns.keySet()) {
                    Integer[] t = columns.get(keys);
                    System.out.print(t[i] + " ");
                }
                System.out.println();
            }
        }

        public static class Operation {
            String a, b;
            String op;

            public Operation(String a, String b, String op) {
                this.a = a;
                this.b = b;
                this.op = op;
            }
        }

    }
}