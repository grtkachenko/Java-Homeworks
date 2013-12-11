import java.util.*;
import java.io.*;

public class TaskL {
    private PrintWriter out;

    public void solve() throws IOException {
        out.println("start: start");
        out.println("accept: y");
        out.println("reject: n");
        out.println("blank: _");
        out.println("start 0 -> start 0 >");
        out.println("start 1 -> start 1 >");
        out.println("start _ -> write_one # >");
        out.println("write_one _ -> write_zero 1 >");
        out.println("write_zero _ -> skip_begin 0 <");
        out.println("skip_begin 1 -> start_mul 1 <");
        out.println("");
        out.println("start_mul # -> check_fail # <");
        out.println("check_fail 0 -> check_fail 0 <");
        out.println("check_fail 1 -> back_to_start 1 ^");
        out.println("check_fail _ -> erase_and_exit _ >");
        out.println("erase_and_exit 0 -> erase_and_exit _ >");
        out.println("erase_and_exit 1 -> erase_and_exit _ >");
        out.println("erase_and_exit # -> build_ans_and_print _ >");
        out.println("");
        out.println("back_to_start 0 -> back_to_start 0 >");
        out.println("back_to_start 1 -> back_to_start 1 >");
        out.println("back_to_start # -> handle0 # <");
        out.println("");
        out.println("");
        out.println("handle0 0 -> handle1 0 <");
        out.println("handle0 1 -> mul0 1 ^");
        out.println("");
        out.println("handle1 0 -> back_to_start_and_shift1 0 ^");
        out.println("handle1 1 -> back_to_start_and_shift1 1 ^");
        out.println("handle1 _ -> back_to_start_and_sub_one _ >");
        out.println("handle1_cont 0 -> handle2 0 <");
        out.println("handle1_cont 1 -> mul1 1 ^");
        out.println("");
        out.println("handle2 0 -> back_to_start_and_shift2 0 ^");
        out.println("handle2 1 -> back_to_start_and_shift2 1 ^");
        out.println("handle2 _ -> back_to_start_and_sub_one _ >");
        out.println("handle2_cont 0 -> handle3 0 <");
        out.println("handle2_cont 1 -> mul2 1 ^");
        out.println("");
        out.println("handle3 0 -> back_to_start_and_shift3 0 ^");
        out.println("handle3 1 -> back_to_start_and_shift3 1 ^");
        out.println("handle3 _ -> back_to_start_and_sub_one _ >");
        out.println("handle3_cont 0 -> handle4 0 <");
        out.println("handle3_cont 1 -> mul3 1 ^");
        out.println("");
        out.println("handle4 0 -> back_to_start_and_shift4 0 ^");
        out.println("handle4 1 -> back_to_start_and_shift4 1 ^");
        out.println("handle4 _ -> back_to_start_and_sub_one _ >");
        out.println("handle4_cont 0 -> handle5 0 <");
        out.println("handle4_cont 1 -> mul4 1 ^");
        out.println("");
        out.println("handle5 _ -> back_to_start_and_sub_one _ >");
        out.println("");
        out.println("back_to_start_and_sub_one 0 -> back_to_start_and_sub_one 0 >");
        out.println("back_to_start_and_sub_one 1 -> back_to_start_and_sub_one 1 >");
        out.println("back_to_start_and_sub_one # -> sub_one # <");
        out.println("");
        out.println("sub_one 0 -> sub_one 1 <");
        out.println("sub_one 1 -> check_if_last 0 <");
        out.println("check_if_last _ -> is_last _ >");
        out.println("check_if_last 0 -> not_last 0 >");
        out.println("check_if_last 1 -> not_last 1 >");
        out.println("is_last 0 -> back_to_start_and_start_new_iteration _ >");
        out.println("not_last 0 -> back_to_start_and_start_new_iteration 0 >");
        out.println("");
        out.println("back_to_start_and_start_new_iteration 0 -> back_to_start_and_start_new_iteration 0 >");
        out.println("back_to_start_and_start_new_iteration 1 -> back_to_start_and_start_new_iteration 1 >");
        out.println("back_to_start_and_start_new_iteration # -> fix_ans # >");
        out.println("");
        out.println("");
        out.println("");
        out.println("back_to_start_and_shift1 0 -> back_to_start_and_shift1 0 >");
        out.println("back_to_start_and_shift1 1 -> back_to_start_and_shift1 1 >");
        out.println("back_to_start_and_shift1 # -> shift b >");
        out.println("back_to_start_and_shift2 0 -> back_to_start_and_shift2 0 >");
        out.println("back_to_start_and_shift2 1 -> back_to_start_and_shift2 1 >");
        out.println("back_to_start_and_shift2 # -> shift c >");
        out.println("back_to_start_and_shift3 0 -> back_to_start_and_shift3 0 >");
        out.println("back_to_start_and_shift3 1 -> back_to_start_and_shift3 1 >");
        out.println("back_to_start_and_shift3 # -> shift d >");
        out.println("back_to_start_and_shift4 0 -> back_to_start_and_shift4 0 >");
        out.println("back_to_start_and_shift4 1 -> back_to_start_and_shift4 1 >");
        out.println("back_to_start_and_shift4 # -> shift e >");
        out.println("");
        out.println("shift 0 -> shift0 0 >");
        out.println("shift 1 -> shift1 0 >");
        out.println("shift0 0 -> shift00 0 >");
        out.println("shift0 1 -> shift00 1 >");
        out.println("shift0 2 -> shift00 2 >");
        out.println("shift0 3 -> shift00 3 >");
        out.println("shift0 4 -> shift00 4 >");
        out.println("shift0 5 -> shift00 5 >");
        out.println("shift0 _ -> shift00 0 >");
        out.println("shift1 0 -> shift11 0 >");
        out.println("shift1 1 -> shift11 1 >");
        out.println("shift1 2 -> shift11 2 >");
        out.println("shift1 3 -> shift11 3 >");
        out.println("shift1 4 -> shift11 4 >");
        out.println("shift1 5 -> shift11 5 >");
        out.println("shift1 _ -> shift11 0 >");
        out.println("shift00 0 -> shift0 0 >");
        out.println("shift00 1 -> shift1 0 >");
        out.println("shift00 _ -> write_zero_and_back_start_from_shifting 0 >");
        out.println("shift11 0 -> shift0 1 >");
        out.println("shift11 1 -> shift1 1 >");
        out.println("shift11 _ -> write_zero_and_back_start_from_shifting 1 >");
        out.println("write_zero_and_back_start_from_shifting _ -> back_start_from_shifting 0 ^");
        out.println("");
        out.println("back_start_from_shifting 0 -> back_start_from_shifting 0 <");
        out.println("back_start_from_shifting 1 -> back_start_from_shifting 1 <");
        out.println("back_start_from_shifting 2 -> back_start_from_shifting 2 <");
        out.println("back_start_from_shifting 3 -> back_start_from_shifting 3 <");
        out.println("back_start_from_shifting 4 -> back_start_from_shifting 4 <");
        out.println("back_start_from_shifting 5 -> back_start_from_shifting 5 <");
        out.println("back_start_from_shifting b -> skip_after_shift1 # <");
        out.println("back_start_from_shifting c -> skip_after_shift2 # <");
        out.println("back_start_from_shifting d -> skip_after_shift3 # <");
        out.println("back_start_from_shifting e -> skip_after_shift4 # <");
        out.println("");
        out.println("skip_after_shift1 0 -> handle1_cont 0 <");
        out.println("skip_after_shift1 1 -> handle1_cont 1 <");
        out.println("");
        out.println("skip_after_shift2 0 -> skip_after_shift21 0 <");
        out.println("skip_after_shift2 1 -> skip_after_shift21 1 <");
        out.println("skip_after_shift21 0 -> handle2_cont 0 <");
        out.println("skip_after_shift21 1 -> handle2_cont 1 <");
        out.println("");
        out.println("skip_after_shift3 0 -> skip_after_shift31 0 <");
        out.println("skip_after_shift3 1 -> skip_after_shift31 1 <");
        out.println("skip_after_shift31 0 -> skip_after_shift32 0 <");
        out.println("skip_after_shift31 1 -> skip_after_shift32 1 <");
        out.println("skip_after_shift32 0 -> handle3_cont 0 <");
        out.println("skip_after_shift32 1 -> handle3_cont 1 <");
        out.println("");
        out.println("skip_after_shift4 0 -> skip_after_shift41 0 <");
        out.println("skip_after_shift4 1 -> skip_after_shift41 1 <");
        out.println("skip_after_shift41 0 -> skip_after_shift42 0 <");
        out.println("skip_after_shift41 1 -> skip_after_shift42 1 <");
        out.println("skip_after_shift42 0 -> skip_after_shift43 0 <");
        out.println("skip_after_shift42 1 -> skip_after_shift43 1 <");
        out.println("skip_after_shift43 0 -> handle4_cont 0 <");
        out.println("skip_after_shift43 1 -> handle4_cont 1 <");
        out.println("");
        out.println("mul0 1 -> back_start_from_mul0 1 ^");
        out.println("mul1 1 -> back_start_from_mul1 1 ^");
        out.println("mul2 1 -> back_start_from_mul2 1 ^");
        out.println("mul3 1 -> back_start_from_mul3 1 ^");
        out.println("mul4 1 -> back_start_from_mul4 1 ^");
        out.println("");
        out.println("back_start_from_mul0 0 -> back_start_from_mul0 0 >");
        out.println("back_start_from_mul0 1 -> back_start_from_mul0 1 >");
        out.println("back_start_from_mul0 # -> exec_mul_no a >");
        out.println("back_start_from_mul1 0 -> back_start_from_mul1 0 >");
        out.println("back_start_from_mul1 1 -> back_start_from_mul1 1 >");
        out.println("back_start_from_mul1 # -> exec_mul_no b >");
        out.println("back_start_from_mul2 0 -> back_start_from_mul2 0 >");
        out.println("back_start_from_mul2 1 -> back_start_from_mul2 1 >");
        out.println("back_start_from_mul2 # -> exec_mul_no c >");
        out.println("back_start_from_mul3 0 -> back_start_from_mul3 0 >");
        out.println("back_start_from_mul3 1 -> back_start_from_mul3 1 >");
        out.println("back_start_from_mul3 # -> exec_mul_no d >");
        out.println("back_start_from_mul4 0 -> back_start_from_mul4 0 >");
        out.println("back_start_from_mul4 1 -> back_start_from_mul4 1 >");
        out.println("back_start_from_mul4 # -> exec_mul_no e >");
        out.println("");
        out.println("exec_mul_no 0 -> exec_mul0_no 0 >");
        out.println("exec_mul_no 1 -> exec_mul1_no 1 >");
        out.println("exec_mul_no _ -> back_to_start_from_exec_mul _ <");
        out.println("exec_mul_yes 0 -> exec_mul0_yes 0 >");
        out.println("exec_mul_yes 1 -> exec_mul1_yes 1 >");
        out.println("exec_mul_yes _ -> exec_mul_yes_write_zero 0 >");
        out.println("exec_mul_yes_write_zero _ -> exec_mul_yes_write_one 1 <");
        out.println("exec_mul_yes_write_one 0 -> back_to_start_from_exec_mul 0 <");
        out.println("");
        out.println("");
        out.println("exec_mul0_no 0 -> exec_mul_no 0 >");
        out.println("exec_mul0_no 1 -> exec_mul_no 1 >");
        out.println("exec_mul0_yes 0 -> exec_mul_no 1 >");
        out.println("exec_mul0_yes 1 -> exec_mul_yes 0 >");
        out.println("");
        out.println("exec_mul1_no 0 -> exec_mul_no 1 >");
        out.println("exec_mul1_no 1 -> exec_mul_yes 0 >");
        out.println("exec_mul1_yes 0 -> exec_mul_yes 0 >");
        out.println("exec_mul1_yes 1 -> exec_mul_yes 1 >");
        out.println("");
        out.println("back_to_start_from_exec_mul 0 -> back_to_start_from_exec_mul 0 <");
        out.println("back_to_start_from_exec_mul 1 -> back_to_start_from_exec_mul 1 <");
        out.println("back_to_start_from_exec_mul a -> skip_after_mul0 # <");
        out.println("back_to_start_from_exec_mul b -> skip_after_mul1 # <");
        out.println("back_to_start_from_exec_mul c -> skip_after_mul2 # <");
        out.println("back_to_start_from_exec_mul d -> skip_after_mul3 # <");
        out.println("back_to_start_from_exec_mul e -> skip_after_mul4 # <");
        out.println("");
        out.println("skip_after_mul0 0 -> handle1 0 <");
        out.println("skip_after_mul0 1 -> handle1 1 <");
        out.println("");
        out.println("skip_after_mul1 0 -> skip_after_mul11 0 <");
        out.println("skip_after_mul1 1 -> skip_after_mul11 1 <");
        out.println("skip_after_mul11 0 -> handle2 0 <");
        out.println("skip_after_mul11 1 -> handle2 1 <");
        out.println("");
        out.println("skip_after_mul2 0 -> skip_after_mul21 0 <");
        out.println("skip_after_mul2 1 -> skip_after_mul21 1 <");
        out.println("skip_after_mul21 0 -> skip_after_mul22 0 <");
        out.println("skip_after_mul21 1 -> skip_after_mul22 1 <");
        out.println("skip_after_mul22 0 -> handle3 0 <");
        out.println("skip_after_mul22 1 -> handle3 1 <");
        out.println("");
        out.println("skip_after_mul3 0 -> skip_after_mul31 0 <");
        out.println("skip_after_mul3 1 -> skip_after_mul31 1 <");
        out.println("skip_after_mul31 0 -> skip_after_mul32 0 <");
        out.println("skip_after_mul31 1 -> skip_after_mul32 1 <");
        out.println("skip_after_mul32 0 -> skip_after_mul33 0 <");
        out.println("skip_after_mul32 1 -> skip_after_mul33 1 <");
        out.println("skip_after_mul33 0 -> handle4 0 <");
        out.println("skip_after_mul33 1 -> handle4 1 <");
        out.println("");
        out.println("skip_after_mul4 0 -> skip_after_mul41 0 <");
        out.println("skip_after_mul4 1 -> skip_after_mul41 1 <");
        out.println("skip_after_mul41 0 -> skip_after_mul42 0 <");
        out.println("skip_after_mul41 1 -> skip_after_mul42 1 <");
        out.println("skip_after_mul42 0 -> skip_after_mul43 0 <");
        out.println("skip_after_mul42 1 -> skip_after_mul43 1 <");
        out.println("skip_after_mul43 0 -> skip_after_mul44 0 <");
        out.println("skip_after_mul43 1 -> skip_after_mul44 1 <");
        out.println("skip_after_mul44 0 -> handle5 0 <");
        out.println("skip_after_mul44 1 -> handle5 1 <");
        out.println("");
        out.println("fix_ans 0 -> fix_ans_right 0 >");
        out.println("fix_ans 1 -> fix_ans_right 1 >");
        out.println("fix_ans _ -> back_to_start_fix _ <");
        out.println("");
        out.println("fix_ans_right 0 -> fix_ans_left0 0 <");
        out.println("fix_ans_right 1 -> fix_ans_left1 0 <");
        out.println("fix_ans_left0 0 -> skip_fix 0 >");
        out.println("fix_ans_left0 1 -> skip_fix 0 >");
        out.println("fix_ans_left1 0 -> skip_fix 1 >");
        out.println("fix_ans_left1 1 -> skip_fix 1 >");
        out.println("skip_fix 0 -> fix_ans 0 >");
        out.println("");
        out.println("back_to_start_fix 0 -> back_to_start_fix 0 <");
        out.println("back_to_start_fix 1 -> back_to_start_fix 1 <");
        out.println("back_to_start_fix # -> start_mul # ^");
        out.println("");
        out.println("build_ans_and_print 0 -> skip_build_ans_and_print 0 >");
        out.println("build_ans_and_print 1 -> skip_build_ans_and_print 1 >");
        out.println("build_ans_and_print _ -> move _ <");
        out.println("skip_build_ans_and_print 0 -> build_ans_and_print = >");
        out.println("skip_build_ans_and_print 1 -> build_ans_and_print = >");
        out.println("");
        out.println("move 0 -> move0 _ <");
        out.println("move 1 -> move1 _ <");
        out.println("move = -> move _ <");
        out.println("move _ -> move_to_first _ <");
        out.println("");
        out.println("move0 0 -> move0 0 <");
        out.println("move0 1 -> move0 1 <");
        out.println("move0 = -> move0 = <");
        out.println("move0 _ -> move0_second _ <");
        out.println("move0_second 0 -> move0_second 0 <");
        out.println("move0_second 1 -> move1_second 0 <");
        out.println("move0_second _ -> return_to_right 0 ^");
        out.println("move1 0 -> move1 0 <");
        out.println("move1 1 -> move1 1 <");
        out.println("move1 = -> move1 = <");
        out.println("move1 _ -> move1_second _ <");
        out.println("move1_second 0 -> move0_second 1 <");
        out.println("move1_second 1 -> move1_second 1 <");
        out.println("move1_second _ -> return_to_right 1 ^");
        out.println("");
        out.println("return_to_right 0 -> return_to_right 0 >");
        out.println("return_to_right 1 -> return_to_right 1 >");
        out.println("return_to_right _ -> go_to_next _ >");
        out.println("");
        out.println("go_to_next 0 -> go_to_next 0 >");
        out.println("go_to_next 1 -> go_to_next 1 >");
        out.println("go_to_next = -> go_to_next = >");
        out.println("go_to_next _ -> move _ <");
        out.println("");
        out.println("move_to_first 0 -> move_to_first 0 <");
        out.println("move_to_first 1 -> move_to_first 1 <");
        out.println("move_to_first _ -> y _ >");
    }

    public void run() {
        try {
            out = new PrintWriter(new File("factorial.out"));
            solve();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class FastScanner {
        private BufferedReader br;
        private StringTokenizer st;

        public FastScanner(File f) {
            try {
                br = new BufferedReader(new FileReader(f));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        public String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static void main(String[] arg) {
        new TaskL().run();
    }
}