import java.util.Objects;

import javax.swing.JOptionPane;

import java.lang.Math;

public class ALU {

    public static int KeyboardInput = 0;
    public static String DescText = "";

    public static String Val_20_int = "";
    public static String Val_20_int2 = "";

    public static boolean halt = false;

    public static void HLT() {
        Frame.run = false;
        halt = true;
        for(int i = 0; i<=100; i++)
        {
            System.out.println("HALT");
        }
    }

    public static void LDR(String R, String X, String I, String Address) {

        int val = Memory.get_from_memory_int(Utils.calculate_effective_address(I, X, Address));

        switch (R) {
            case "00":
                Registers.update_registers("R0", val);
                break;
            case "01":
                Registers.update_registers("R1", val);
                break;
            case "10":
                Registers.update_registers("R2", val);
                break;
            case "11":
                Registers.update_registers("R3", val);
                break;
        }
    }

    public static void STR(String R, String X, String I, String Address) {
        int val = 0;

        switch (R) {
            case "00":
                val = Registers.get_register_value_int("R0");
                break;
            case "01":
                val = Registers.get_register_value_int("R1");
                break;
            case "10":
                val = Registers.get_register_value_int("R2");
                break;
            case "11":
                val = Registers.get_register_value_int("R3");
                break;
        }

        Memory.store_to_memory(Utils.calculate_effective_address(I, X, Address), val);

    }

    public static void LDA(String R, String X, String I, String Address) {
        int val = Utils.calculate_effective_address(I, X, Address);

        switch (R) {
            case "00":
                Registers.update_registers("R0", val);
                break;
            case "01":
                Registers.update_registers("R1", val);
                break;
            case "10":
                Registers.update_registers("R2", val);
                break;
            case "11":
                Registers.update_registers("R3", val);
                break;
        }

    }


    public static void LDX(String X, String I, String Address) {

        int val = Memory.get_from_memory_int(Integer.parseInt(Address, 2));

        switch (X) {
            case "01":
                Registers.update_registers("X1", val);
                break;
            case "10":
                Registers.update_registers("X2", val);
                break;
            case "11":
                Registers.update_registers("X3", val);
                break;
        }
    }

    public static void STX(String X, String I, String Address) {
        int val = 0;

        switch (X) {
            case "01":
                val = Registers.get_register_value_int("X1");
                break;
            case "10":
                val = Registers.get_register_value_int("X2");
                break;
            case "11":
                val = Registers.get_register_value_int("X3");
                break;
        }

        Memory.store_to_memory(Utils.calculate_effective_address(I, "00", Address), val);

    }


    public static void JZ(String R, String X, String I, String Address) {
        int val = 0;

        switch (R) {
            case "00":
                val = Registers.get_register_value_int("R0");
                break;
            case "01":
                val = Registers.get_register_value_int("R1");
                break;
            case "10":
                val = Registers.get_register_value_int("R2");
                break;
            case "11":
                val = Registers.get_register_value_int("R3");
                break;
        }

        if (val == 0) {
            Registers.update_registers("PC", Utils.calculate_effective_address(I, X, Address));
        } else {
            Registers.update_registers("PC", Registers.get_register_value_int("PC") + 1);
            // return;
        }

    }


    public static void JNE(String R, String X, String I, String Address) {
        int val = 0;

        switch (R) {
            case "00":
                val = Registers.get_register_value_int("R0");
                break;
            case "01":
                val = Registers.get_register_value_int("R1");
                break;
            case "10":
                val = Registers.get_register_value_int("R2");
                break;
            case "11":
                val = Registers.get_register_value_int("R3");
                break;
        }

        if (val != 0) {
            Registers.update_registers("PC", Utils.calculate_effective_address(I, X, Address));
        } else {
            Registers.update_registers("PC", Registers.get_register_value_int("PC") + 1);
            // return;
        }

    }

    public static void JCC(String R, String X, String I, String Address) {
        int val = 0;

        switch (R) {
            case "00":
                val = Registers.get_register_value_string("CC").charAt(0);
                break;
            case "01":
                val = Registers.get_register_value_string("CC").charAt(1);
                break;
            case "10":
                val = Registers.get_register_value_string("CC").charAt(2);
                break;
            case "11":
                val = Registers.get_register_value_string("CC").charAt(3);
                break;
        }

        if (val == 0) {
            Registers.update_registers("PC", Utils.calculate_effective_address(I, X, Address));
        } else {
            Registers.update_registers("PC", Registers.get_register_value_int("PC") + 1);
            // return;
        }

    }


    public static void JMA(String X, String I, String Address) {
        Registers.update_registers("PC", Utils.calculate_effective_address(I, X, Address));
    }


    public static void JSR(String X, String I, String Address) {
        Registers.update_registers("R3", Registers.get_register_value_int("PC") + 1);
        Registers.update_registers("PC", Utils.calculate_effective_address(I, X, Address));
    }


    public static void RFS(String Address) {
        Registers.update_registers("R0", Integer.parseInt(Address));
        Registers.update_registers("PC", Registers.get_register_value_int("R3"));
    }

    public static void SOB(String R, String X, String I, String Address) {
        int val = 0;

        switch (R) {
            case "00":
                Registers.update_registers("R0", Registers.get_register_value_int("R0") - 1);
                val = Registers.get_register_value_int("R0");
                break;
            case "01":
                Registers.update_registers("R1", Registers.get_register_value_int("R1") - 1);
                val = Registers.get_register_value_int("R1");
                break;
            case "10":
                Registers.update_registers("R2", Registers.get_register_value_int("R2") - 1);
                val = Registers.get_register_value_int("R2");
                break;
            case "11":
                Registers.update_registers("R3", Registers.get_register_value_int("R3") - 1);
                val = Registers.get_register_value_int("R3");
                break;
        }
        ;

        if (val > 0) {
            Registers.update_registers("PC", Utils.calculate_effective_address(I, X, Address));
        } else {
            Registers.update_registers("PC", Registers.get_register_value_int("PC") + 1);
            // return;
        }
    }


    public static void JGE(String R, String X, String I, String Address) {
        int val = 0;

        switch (R) {
            case "00":
                val = Registers.get_register_value_int("R0");
                break;
            case "01":
                val = Registers.get_register_value_int("R1");
                break;
            case "10":
                val = Registers.get_register_value_int("R2");
                break;
            case "11":
                val = Registers.get_register_value_int("R3");
                break;
        }

        if (val >= 0) {
            Registers.update_registers("PC", Utils.calculate_effective_address(I, X, Address));
        } else {
            Registers.update_registers("PC", Registers.get_register_value_int("PC") + 1);
            // return;
        }

    }


    public static void AMR(String R, String X, String I, String Address) {
        int val = 0;

        switch (R) {
            case "00":
                Registers.update_registers("R0", Registers.get_register_value_int("R0") + Memory.get_from_memory_int(Utils.calculate_effective_address(I, X, Address)));
                break;
            case "01":
                Registers.update_registers("R1", Registers.get_register_value_int("R1") + Memory.get_from_memory_int(Utils.calculate_effective_address(I, X, Address)));
                break;
            case "02":
                Registers.update_registers("R2", Registers.get_register_value_int("R2") + Memory.get_from_memory_int(Utils.calculate_effective_address(I, X, Address)));
                break;
            case "03":
                Registers.update_registers("R3", Registers.get_register_value_int("R3") + Memory.get_from_memory_int(Utils.calculate_effective_address(I, X, Address)));
                break;
        }
    }


    public static void SMR(String R, String X, String I, String Address) {
        switch (R) {
            case "00":
                if (Registers.get_register_value_int("R0") - Memory.get_from_memory_int(Utils.calculate_effective_address(I, X, Address)) >= 0)
                {
                    Registers.update_registers("R0", Registers.get_register_value_int("R0") - Memory.get_from_memory_int(Utils.calculate_effective_address(I, X, Address)));
                }
                else {
                    Registers.update_registers("R0", 0);
                }

                break;
            case "01":
                if (Registers.get_register_value_int("R1") - Memory.get_from_memory_int(Utils.calculate_effective_address(I, X, Address)) >= 0)
                {
                    Registers.update_registers("R1", Registers.get_register_value_int("R1") - Memory.get_from_memory_int(Utils.calculate_effective_address(I, X, Address)));
                }
                else {
                    Registers.update_registers("R1", 0);
                }
            case "02":
                if (Registers.get_register_value_int("R2") - Memory.get_from_memory_int(Utils.calculate_effective_address(I, X, Address)) >= 0)
                {
                    Registers.update_registers("R2", Registers.get_register_value_int("R2") - Memory.get_from_memory_int(Utils.calculate_effective_address(I, X, Address)));
                }
                else {
                    Registers.update_registers("R2", 0);
                }
            case "03":
                if (Registers.get_register_value_int("R3") - Memory.get_from_memory_int(Utils.calculate_effective_address(I, X, Address)) >= 0)
                {
                    Registers.update_registers("R3", Registers.get_register_value_int("R3") - Memory.get_from_memory_int(Utils.calculate_effective_address(I, X, Address)));
                }
                else {
                    Registers.update_registers("R3", 0);
                }
        }
    }


    public static void AIR(String R, String Address) {
        int val = 0;

        switch (R) {
            case "00":
                val = Registers.get_register_value_int("R0");
                break;
            case "01":
                val = Registers.get_register_value_int("R1");
                break;
            case "10":
                val = Registers.get_register_value_int("R2");
                break;
            case "11":
                val = Registers.get_register_value_int("R3");
                break;
        }

        System.out.println(val);
        System.out.println(Integer.parseInt(Address));

        val = val + Integer.parseInt(Address, 2);

        switch (R) {
            case "00":
                Registers.update_registers("R0", val);
                break;
            case "01":
                Registers.update_registers("R1", val);
                break;
            case "10":
                Registers.update_registers("R2", val);
                break;
            case "11":
                Registers.update_registers("R3", val);
                break;
        }
    }


    public static void SIR(String R, String Address) {
        int val = 0;

        switch (R) {
            case "00":
                val = Registers.get_register_value_int("R0");
                break;
            case "01":
                val = Registers.get_register_value_int("R1");
                break;
            case "10":
                val = Registers.get_register_value_int("R2");
                break;
            case "11":
                val = Registers.get_register_value_int("R3");
                break;
        }

        System.out.println(val);
        System.out.println(Integer.parseInt(Address, 2));
        val = val - Integer.parseInt(Address, 2);
        System.out.println(val);

        switch (R) {
            case "00":
                Registers.update_registers("R0", val);
                break;
            case "01":
                Registers.update_registers("R1", val);
                break;
            case "10":
                Registers.update_registers("R2", val);
                break;
            case "11":
                Registers.update_registers("R3", val);
                break;
        }
    }


    public static void MLT(String R1, String R2) {
        if ((Objects.equals(R1, "01")) || (Objects.equals(R1, "11"))) {
            throw new RuntimeException("Use Registers 0 or 2. 1 or 3 must have been used");
        }

        if ((Objects.equals(R1, "00") && Objects.equals(R2, "10")) || (Objects.equals(R1, "00") && Objects.equals(R2, "11"))) {
            throw new RuntimeException("Ry is not Rx+1");
        }

        if ((Objects.equals(R1, "10") && Objects.equals(R2, "00")) || (Objects.equals(R1, "10") && Objects.equals(R2, "01"))) {
            throw new RuntimeException("Ry is not Rx+1");
        }

        int val = 0;
        String val_string;
        String cc_reg;


        if (Objects.equals(R1, "00")) {
            val = Registers.get_register_value_int("R0") * Registers.get_register_value_int("R1");
        }

        if (Objects.equals(R1, "10")) {
            val = Registers.get_register_value_int("R2") * Registers.get_register_value_int("R3");
        }

        val_string = String.format("%33s", Integer.toBinaryString(val)).replace(' ', '0');

        if (val_string.charAt(0) == '1') {
            cc_reg = "1" + Registers.get_register_value_string("CC").substring(1);
            Registers.CC = cc_reg;
        } else {
            cc_reg = "0" + Registers.get_register_value_string("CC").substring(1);
            Registers.CC = cc_reg;
        }

        if (Objects.equals(R1, "00")) {
            Registers.R0 = val_string.substring(1, 17);
            Registers.R1 = val_string.substring(17);
        }

        if (Objects.equals(R1, "10")) {
            Registers.R2 = val_string.substring(1, 17);
            Registers.R3 = val_string.substring(17);
        }

    }


    public static void DVD(String R1, String R2) {
        if ((Objects.equals(R1, "01")) || (Objects.equals(R1, "11"))) {
            throw new RuntimeException("Use Registers 0 or 2. 1 or 3 must have been used");
        }

        if ((Objects.equals(R1, "00") && Objects.equals(R2, "10")) || (Objects.equals(R1, "00") && Objects.equals(R2, "11"))) {
            throw new RuntimeException("Ry is not Rx+1");
        }

        if ((Objects.equals(R1, "10") && Objects.equals(R2, "00")) || (Objects.equals(R1, "10") && Objects.equals(R2, "01"))) {
            throw new RuntimeException("Ry is not Rx+1");
        }

        int quotient = 0;
        int rem = 0;
        String cc_reg;

        System.out.println(Registers.get_register_value_string("CC"));


        if (Objects.equals(R1, "00")) {
            if (Registers.get_register_value_int("R1") == 0) {
                cc_reg = Registers.get_register_value_string("CC").substring(0, 1) + Registers.get_register_value_string("CC").substring(1, 2) + "1" + Registers.get_register_value_string("CC").substring(3);
                Registers.CC = cc_reg;
                return;
            } else {
                cc_reg = Registers.get_register_value_string("CC").substring(0, 1) + Registers.get_register_value_string("CC").substring(1, 2) + "0" + Registers.get_register_value_string("CC").substring(3);
                Registers.CC = cc_reg;
            }

            quotient = Registers.get_register_value_int("R0") / Registers.get_register_value_int("R1");
            rem = Registers.get_register_value_int("R0") % Registers.get_register_value_int("R1");
        }

        if (Objects.equals(R1, "10")) {
            if (Registers.get_register_value_int("R3") == 0) {
                cc_reg = Registers.get_register_value_string("CC").substring(0, 1) + Registers.get_register_value_string("CC").substring(1, 2) + "1" + Registers.get_register_value_string("CC").substring(3);
                Registers.CC = cc_reg;
                return;
            } else {
                cc_reg = Registers.get_register_value_string("CC").substring(0, 1) + Registers.get_register_value_string("CC").substring(1, 2) + "0" + Registers.get_register_value_string("CC").substring(3);
                Registers.CC = cc_reg;
            }

            quotient = Registers.get_register_value_int("R2") / Registers.get_register_value_int("R3");
            rem = Registers.get_register_value_int("R2") % Registers.get_register_value_int("R3");
        }

        if (Objects.equals(R1, "00")) {
            Registers.update_registers("R0", quotient);
            Registers.update_registers("R1", rem);
        }

        if (Objects.equals(R1, "10")) {
            Registers.update_registers("R2", quotient);
            Registers.update_registers("R3", rem);
        }

    }


    public static void TRR(String R1, String R2) {
        int rx = 0, ry = 0;


        switch (R1) {
            case "00":
                rx = Registers.get_register_value_int("R0");
                break;
            case "01":
                rx = Registers.get_register_value_int("R1");
                break;
            case "10":
                rx = Registers.get_register_value_int("R2");
                break;
            case "11":
                rx = Registers.get_register_value_int("R3");
                break;
        }

        switch (R2) {
            case "00":
                ry = Registers.get_register_value_int("R0");
                break;
            case "01":
                ry = Registers.get_register_value_int("R1");
                break;
            case "10":
                ry = Registers.get_register_value_int("R2");
                break;
            case "11":
                ry = Registers.get_register_value_int("R3");
                break;
        }

        String cc_string;

        if (rx == ry) {
            cc_string = Registers.get_register_value_string("CC").substring(0, 3) + "1";
        } else {
            cc_string = Registers.get_register_value_string("CC").substring(0, 3) + "0";
        }

        Registers.update_registers("CC", Integer.parseInt(cc_string));
    }


    public static void AND(String R1, String R2) {
        int rx = 0, ry = 0;
        String sol_reg = "R0";


        switch (R1) {
            case "00":
                rx = Registers.get_register_value_int("R0");
                sol_reg = "R0";
                break;
            case "01":
                rx = Registers.get_register_value_int("R1");
                sol_reg = "R1";
                break;
            case "10":
                rx = Registers.get_register_value_int("R2");
                sol_reg = "R2";
                break;
            case "11":
                rx = Registers.get_register_value_int("R3");
                sol_reg = "R3";
                break;
        }

        switch (R2) {
            case "00":
                ry = Registers.get_register_value_int("R0");
                break;
            case "01":
                ry = Registers.get_register_value_int("R1");
                break;
            case "10":
                ry = Registers.get_register_value_int("R2");
                break;
            case "11":
                ry = Registers.get_register_value_int("R3");
                break;
        }

        Registers.update_registers(sol_reg, rx & ry);
    }


    public static void ORR(String R1, String R2) {
        int rx = 0, ry = 0;
        String sol_reg = "R0";


        switch (R1) {
            case "00":
                rx = Registers.get_register_value_int("R0");
                sol_reg = "R0";
                break;
            case "01":
                rx = Registers.get_register_value_int("R1");
                sol_reg = "R1";
                break;
            case "10":
                rx = Registers.get_register_value_int("R2");
                sol_reg = "R2";
                break;
            case "11":
                rx = Registers.get_register_value_int("R3");
                sol_reg = "R3";
                break;
        }

        switch (R2) {
            case "00":
                ry = Registers.get_register_value_int("R0");
                break;
            case "01":
                ry = Registers.get_register_value_int("R1");
                break;
            case "10":
                ry = Registers.get_register_value_int("R2");
                break;
            case "11":
                ry = Registers.get_register_value_int("R3");
                break;
        }

        Registers.update_registers(sol_reg, rx | ry);
    }

    public static void NOT(String R1) {
        switch (R1) {
            case "00":
                Registers.update_registers("R0", ~Registers.get_register_value_int("R0"));
                break;
            case "01":
                Registers.update_registers("R1", ~Registers.get_register_value_int("R1"));
                break;
            case "10":
                Registers.update_registers("R2", ~Registers.get_register_value_int("R2"));
                break;
            case "11":
                Registers.update_registers("R3", ~Registers.get_register_value_int("R3"));
                break;
        }
    }


    public static void SRC(String R, String RL, String AL, String Count) {
        int val = 0;
        String sol_reg = "R0";

        switch (R) {
            case "00":
                val = Registers.get_register_value_int("R0");
                sol_reg = "R0";
                break;
            case "01":
                val = Registers.get_register_value_int("R1");
                sol_reg = "R1";
                break;
            case "10":
                val = Registers.get_register_value_int("R2");
                sol_reg = "R2";
                break;
            case "11":
                val = Registers.get_register_value_int("R3");
                sol_reg = "R3";
                break;
        }

        int count = Integer.parseInt(Count);

        if (count == 0) {
            return;
        }

        if (Objects.equals(AL, "0")) {
            if (Objects.equals(RL, "0")) {
                val = val >> count;
            } else if (Objects.equals(AL, "1")) {
                val = val << count;
            }
        } else if (Objects.equals(AL, "1")) {
            if (Objects.equals(RL, "0")) {
                val = val >>> count;
            } else if (Objects.equals(AL, "1")) {
                val = val << count;
            }
        }

        String val_string = String.format("%16s", Integer.toBinaryString(val)).replace(' ', '0').substring(0, 16);

        switch (sol_reg) {
            case "R0":
                Registers.R0 = val_string;
                break;
            case "R1":
                Registers.R1 = val_string;
                break;
            case "R2":
                Registers.R2 = val_string;
                break;
            case "R3":
                Registers.R3 = val_string;
                break;
        }

    }


    public static void RRC(String R, String AL, String RL, String Count) {
        int val = 0;
        String sol_reg = "R0";

        switch (R) {
            case "00":
                val = Registers.get_register_value_int("R0");
                sol_reg = "R0";
                break;
            case "01":
                val = Registers.get_register_value_int("R1");
                sol_reg = "R1";
                break;
            case "10":
                val = Registers.get_register_value_int("R2");
                sol_reg = "R2";
                break;
            case "11":
                val = Registers.get_register_value_int("R3");
                sol_reg = "R3";
                break;
        }

        int count = Integer.parseInt(Count);

        if (count == 0) {
            return;
        }

        if (Objects.equals(RL, "0")) {
            val = Integer.rotateRight(val, count);
        } else if (Objects.equals(AL, "1")) {
            val = Integer.rotateLeft(val, count);
        }

        String val_string = String.format("%16s", Integer.toBinaryString(val)).replace(' ', '0').substring(0, 16);

        switch (sol_reg) {
            case "R0":
                Registers.R0 = val_string;
                break;
            case "R1":
                Registers.R1 = val_string;
                break;
            case "R2":
                Registers.R2 = val_string;
                break;
            case "R3":
                Registers.R3 = val_string;
                break;
        }
    }


    public static void takeInput() {


        String input = "";

        input = JOptionPane.showInputDialog("Please enter the input");

        System.out.println(input);
    }


    public static void IN(String R, String DevId) {

        if (Integer.parseInt(DevId) == 0) {

            String input = "";
            input = JOptionPane.showInputDialog("Please enter the input");
            System.out.println(input);

            KeyboardInput = Integer.parseInt(input);

            switch (R) {
                case "00":
                    Registers.update_registers("R0", KeyboardInput);
                    break;
                case "01":
                    Registers.update_registers("R1", KeyboardInput);
                    break;
                case "10":
                    Registers.update_registers("R2", KeyboardInput);
                    break;
                case "11":
                    Registers.update_registers("R3", KeyboardInput);
                    break;
            }
        }
    }

    public static void OUT(String R, String DevId) {
        if (Integer.parseInt(DevId) == 1) {

            switch (R) {
                case "00":
                    DescText = String.valueOf(Registers.get_register_value_int("R0")); break;
                case "01":
                    DescText = String.valueOf(Registers.get_register_value_int("R1")); break;
                case "10":
                    DescText = String.valueOf(Registers.get_register_value_int("R2")); break;
                case "11":
                    DescText = String.valueOf(Registers.get_register_value_int("R3")); break;
            }
        }
    }


    public static void CHK(String R, String DevId) {

    }

}
