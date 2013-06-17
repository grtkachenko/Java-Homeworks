import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class TaskI {
    final int MAX_DEADLINE = 1031;
    final int INVALID_COLOR = Integer.MIN_VALUE;
    final int FILL_COLOR = -1;

    BufferedReader br;
    PrintWriter out;
    StringTokenizer stok;
    HashSet<Integer>[] jobsOnTime = new HashSet[MAX_DEADLINE];
    int n;
    int m;

    class Job implements Comparable<Job> {
        int d;
        int id;

        Job(int d, int id) {
            this.d = d;
            this.id = id;
        }

        @Override
        public int compareTo(Job job) {
            if (job.d == d) {
                return id - job.id;
            }
            return job.d - d;
        }
    }

    void moveToLeft(int position) {
        if (jobsOnTime[position].size() > m) {
            for (Integer id : jobsOnTime[position]) {
                if (!jobsOnTime[position - 1].contains(id)) {
                    jobsOnTime[position].remove(id);
                    jobsOnTime[position - 1].add(id);
                    moveToLeft(position - 1);
                    return;
                }

            }
            // unreachable point
            throw new RuntimeException();
        }
    }

    int[][] arrangeColors() {
        int[][] colors = new int[n][MAX_DEADLINE];
        int[][] rowColors = new int[n][m];
        int[][] columnColors = new int[MAX_DEADLINE][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(colors[i], INVALID_COLOR);
            Arrays.fill(rowColors[i], -1);
        }
        for (int i = 0; i < MAX_DEADLINE; i++) {
            Arrays.fill(columnColors[i], -1);
            for (int job : jobsOnTime[i]) {
                colors[job][i] = FILL_COLOR;
            }
        }


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < MAX_DEADLINE; j++) {
                if (colors[i][j] == FILL_COLOR) {
                    int columnColor = getNewColor(columnColors[j]);
                    rearrangeColors(colors, rowColors, columnColors, i, j, columnColor, -1, true);
                }
            }
        }
        return colors;
    }

    void rearrangeColors(int[][] colors, int[][] rowColors, int[][] columnColors, int i, int j, int color, int secondColor, boolean inRow) {
        if (inRow && rowColors[i][color] == -1 || !inRow && columnColors[j][color] == -1) {
            setColor(colors, rowColors, columnColors, i, j, color);
            return;
        }

        if (secondColor == -1) {
            secondColor = getNewColor(rowColors[i]);
        }
        int nextI = inRow ? i : columnColors[j][color];
        int nextJ = inRow ? rowColors[i][color] : j;
        setColor(colors, rowColors, columnColors, i, j, color);
        rearrangeColors(colors, rowColors, columnColors, nextI, nextJ, secondColor, color, !inRow);
    }

    void setColor(int[][] colors, int[][] rowColors, int[][] columnColors, int i, int j, int color) {
        if (colors[i][j] != FILL_COLOR) {
            if (rowColors[i][colors[i][j]] == j) {
                rowColors[i][colors[i][j]] = FILL_COLOR;
            }
            if (columnColors[j][colors[i][j]] == i) {
                columnColors[j][colors[i][j]] = FILL_COLOR;
            }
        }
        colors[i][j] = color;
        rowColors[i][color] = j;
        columnColors[j][color] = i;
    }



    int getNewColor(int[] colors) {
        for (int i = 0; i < colors.length; i++) {
            if (colors[i] == -1) {
                return i;
            }
        }
        return -1;
    }

    void printAns(Job[] jobs, int[][] colors) {
        int[][] ans = new int[n][m];

        for (Job job : jobs) {
            int i = job.id;
            Arrays.fill(ans[i], -1);
            for (int j = 0; j < MAX_DEADLINE; j++) {
                if (colors[i][j] != INVALID_COLOR) {
                    ans[i][colors[i][j]] = j + 1;
                }
            }
        }
        int curNotOkTime = MAX_DEADLINE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (ans[i][j] == -1) {
                    ans[i][j] = curNotOkTime++;
                }
                out.print(ans[i][j] + " ");
            }
            out.println();
        }
    }


    void solve() throws IOException {
        long start = System.currentTimeMillis();
        n = nextInt();
        m = nextInt();
        int penalty = nextInt();
        for (int i = 0; i < MAX_DEADLINE; i++) {
            jobsOnTime[i] = new HashSet<Integer>();
        }

        Job[] jobs = new Job[n];
        for (int i = 0; i < n; i++) {
            jobs[i] = new Job(nextInt(), i);
        }
        Arrays.sort(jobs);
        int numOkJobs = 0;
        loopByDeadlines : for (int i = 0; i < n; i++) {
            int firstOverflow = -1;
            if (jobs[i].d < m) {
                break;
            }
            int sizeSum = 0;
            for (int j = 0; j < jobs[i].d; j++) {
                sizeSum += jobsOnTime[j].size();
            }
            for (int j = jobs[i].d - m; j < jobs[i].d; j++) {
                if (jobsOnTime[j].size() == m && firstOverflow == -1) {
                    firstOverflow = j;
                }
                sizeSum++;
            }

            if (sizeSum > jobs[i].d * m) {
                break;
            } else {
                for (int j = jobs[i].d - m; j < jobs[i].d; j++) {
                    jobsOnTime[j].add(jobs[i].id);
                }
            }

            if (firstOverflow == -1 ) {
                numOkJobs++;
                continue;
            }

            for (int j = firstOverflow; j < jobs[i].d; j++) {
                if (jobsOnTime[j].size() == m + 1) {
                    moveToLeft(j);
                }
            }
            numOkJobs++;
        }
        out.println(penalty * (n - numOkJobs));
        arrangeColors();
        printAns(jobs, arrangeColors());
        System.out.println(System.currentTimeMillis() - start);
    }

    public static void main(String[] args) throws IOException {
        // Locale.setDefault(Locale.US);
        new TaskI().run();
    }

    void run() throws IOException {
//        br = new BufferedReader(new FileReader("furniture.in"));
//        out = new PrintWriter("furniture.out");
        br = new BufferedReader(new FileReader("input.txt"));
        out = new PrintWriter("output.txt");
//        br = new BufferedReader(new InputStreamReader(System.in));
//        out = new PrintWriter(System.out);
        solve();
        br.close();
        out.close();
    }

    String nextToken() throws IOException {
        while (stok == null || !stok.hasMoreTokens()) {
            String s = br.readLine();
            if (s == null) {
                return null;
            }
            stok = new StringTokenizer(s);
        }
        return stok.nextToken();
    }

    int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }

    long nextLong() throws IOException {
        return Long.parseLong(nextToken());
    }

    double nextDouble() throws IOException {
        return Double.parseDouble(nextToken());
    }

    char nextChar() throws IOException {
        return (char) (br.read());
    }

    String nextLine() throws IOException {
        return br.readLine();
    }


}
