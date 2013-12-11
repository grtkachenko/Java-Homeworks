import java.util.*;
import java.io.*;

public class Check {
    String START = "S";
    String ACCEPTED = "AC";
    String REJECTED = "RJ";
    String BLANK = "_";
    int countLines = 1;

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("Usage: java Check <machine> <test>");
            return;
        }
        new Check().run(args[0], args[1]);
    }

    void run(String machineFileName, String testFileName) throws IOException {
        Map<Cortege, Changes> rules = readMachine(machineFileName);
        Line[] lines = readTest(testFileName);

        runMachine(rules, lines);
    }

    Map<Cortege, Changes> readMachine(String fileName) throws IOException {
        BufferedReader ans = new BufferedReader(new FileReader(fileName));
        String s = ans.readLine();
        if (s.contains("start:")) {
            START = s.split(" ")[1];
            ACCEPTED = ans.readLine().split(" ")[1];
            REJECTED = ans.readLine().split(" ")[1];
            BLANK = ans.readLine().split(" ")[1];
        } else {
            countLines = Integer.parseInt(s);
        }

        Map<Cortege, Changes> rules = new HashMap<Cortege, Changes>();

        while ((s = ans.readLine()) != null) {
            StringTokenizer tokens = new StringTokenizer(s);
            String[] from = new String[countLines + 1];
            for (int i = 0; i <= countLines; i++) {
                from[i] = tokens.nextToken();
            }

            tokens.nextToken();

            String to = tokens.nextToken();
            String[] toLine = new String[countLines];
            String[] dirs = new String[countLines];
            for (int i = 0; i < countLines; i++) {
                toLine[i] = tokens.nextToken();
                dirs[i] = tokens.nextToken();
            }
            rules.put(new Cortege(from), new Changes(to, toLine, dirs));
        }
        ans.close();

        return rules;
    }

    Line[] readTest(String fileName) throws IOException {
        BufferedReader test = new BufferedReader(new FileReader(fileName));
        Line[] lines = new Line[countLines];
        lines[0] = new Line(test.readLine().replace(" ", ""));
        for (int i = 1; i < countLines; i++) {
            lines[i] = new Line();
        }
        test.close();
        return lines;
    }

    void runMachine(Map<Cortege, Changes> rules, Line[] lines) {
        String[] state = new String[countLines + 1];
        state[0] = START;
        int step;
        for (step = 0; step < 1e7 && !ACCEPTED.equals(state[0]) && !REJECTED.equals(state[0]); step++) {
            for (int i = 0; i < countLines; i++) {
                state[i + 1] = lines[i].get();
            }
            Cortege cur = new Cortege(state);
            Changes next = rules.get(cur);
            if (next == null) {
                state[0] = REJECTED;
                break;
            }

            for (int i = 0; i < countLines; i++) {
                String dir = next.dirs[i];
                String toLine = next.toLine[i];
                Line line = lines[i];
                if ("<".equals(dir)) {
                    line.left(toLine);
                } else if (">".equals(dir)) {
                    line.right(toLine);
                } else {
                    line.stay(toLine);
                }
            }
            state[0] = next.to;
        }

        System.out.println("Steps: " + step);
        if (ACCEPTED.equals(state[0])) {
            System.out.println(ACCEPTED);
        } else if (REJECTED.equals(state[0])) {
            System.out.println(REJECTED);
        } else {
            System.out.println("Time limit exceeded");
        }
        System.out.println(lines[0]);
    }

    class Cortege {
        String[] items;

        Cortege(String[] items) {
            this.items = items;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Cortege cortege = (Cortege) o;

            if (!Arrays.equals(items, cortege.items)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(items);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (String s : items) {
                sb.append(s);
                sb.append(" ");
            }
            return sb.toString();
        }
    }

    class Changes {
        String to;
        String[] toLine;
        String[] dirs;

        Changes(String to, String[] toLine, String[] dirs) {
            this.to = to;
            this.toLine = toLine;
            this.dirs = dirs;
        }
    }

    class Line {
        List<String> lineUp = new ArrayList<String>();
        List<String> lineDown = new ArrayList<String>();
        int pos = 0;

        Line(String s) {
            for (char c : s.toCharArray()) {
                lineUp.add(String.valueOf(c));
            }
        }

        Line() {
            lineUp.add(BLANK);
        }

        void left(String s) {
            if (pos >= 0) {
                lineUp.set(pos, s);
            } else {
                lineDown.set(-pos - 1, s);
            }
            pos--;
            if (pos < 0 && lineDown.size() == -pos - 1) {
                lineDown.add(BLANK);
            }
        }

        void right(String s) {
            if (pos >= 0) {
                lineUp.set(pos, s);
            } else {
                lineDown.set(-pos - 1, s);
            }
            pos++;
            if (pos >= 0 && lineUp.size() == pos) {
                lineUp.add(BLANK);
            }
        }

        void stay(String s) {
            if (pos >= 0) {
                lineUp.set(pos, s);
            } else {
                lineDown.set(-pos - 1, s);
            }
        }

        String get() {
            if (pos >= 0) {
                return lineUp.get(pos);
            }
            return lineDown.get(-pos - 1);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = lineDown.size() - 1; i >= 0; i--) {
                sb.append(lineDown.get(i));
            }
            for (String i : lineUp) {
                sb.append(i);
            }
            String s = sb.toString();
            int first = -1;
            int last = -1;
            for (int i = 0, len = s.length(); i < len; i++) {
                if (s.charAt(i) != BLANK.charAt(0)) {
                    if (first == -1) {
                        first = i;
                    }
                    last = i;
                }
            }
            if (first == -1) {
                return BLANK;
            }
            return s.substring(first, last + 1);
        }
    }
}