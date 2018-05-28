package setlab.cores;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

public class SetCore {

    // --------------------------------------------------- OPERATIONS ---
    public static SetObj Union(SetObj a, SetObj b) {
        SetObj Res = new SetObj("Ans", a);
        Res.addAll(b);
        return Res;
    }

    public static SetObj Intersect(SetObj a, SetObj b) {
        SetObj Res = new SetObj("Ans", a);
        Res.retainAll(b);
        return Res;
    }

    public static SetObj Diff(SetObj a, SetObj b) {
        SetObj Res = new SetObj("Ans", a);
        Res.removeAll(b);
        return Res;
    }

    public static SetObj SymmetricDiff(SetObj a, SetObj b) {
        return Union(Diff(a, b), Diff(b, a));
    }

    // ---------------------------------------------- OPERATION EQUALS ---
    public static boolean isSubSet(SetObj a, SetObj b) {
        return b.containsAll(a);
    }

    public static class SetObj extends HashSet<String> {

        public String name;
        private String error;

        public SetObj(String name) {
            this.name = name;
        }

        public SetObj(String name, String line) {
            this.name = name;
            if (line.length() > 1) {
                this.addAll(Arrays.asList(line.split(",")));
            }
        }

        public SetObj(String name, String[] sequence) {
            this.name = name;
            this.addAll(Arrays.asList(sequence));
        }

        public SetObj(String name, SetObj set) {
            this.name = name;
            this.addAll(set);
        }

        public SetObj(SetObj set) {
            this.name = set.name;
            this.addAll(set);
        }

        public void changeName(String newName) {
            this.name = newName;
        }

        public boolean isError() {
            return this.error != null;
        }

        public void setError(String error) {
            this.error = error;
        }

        @Override
        public String toString() {
            if (error != null) {
                return "Error: " + error + "\n";
            }
            if (this.isEmpty()) {
                return this.name + " = Ø";
            }
            StringBuilder res = new StringBuilder(this.name + " = {");
            Iterator t = this.iterator();
            for (; ; ) {
                res.append(t.next());
                if (!t.hasNext()) {
                    return res.append("}").toString();
                }
                res.append(",").append(" ");
            }
        }

    }
}