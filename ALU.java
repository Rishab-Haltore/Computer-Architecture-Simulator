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

// Load/Store Instructions: 

// Addressing all memory requires indexing!
// 00   No Indexing  
// 01   Register 1  
// 10   Register 2  
// 11   Register 3 

// Load/Store instructions move data from/to memory and a register.

    public static void LDR(String R, String X, String I, String Address) {

        int val = Memory.get_from_memory_int(Utils.calculate_effective_address(I, X, Address));
        // LDR -> Load Register From Memory
        //  r = 0..3 
        // r <- c(EA)
        // c(EA) means “fetch the contents of the memory location specified by EA”

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
        // Store Register To Memory
        // r = 0..3 
        // Memory(EA) <- c(r)

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
        // Load Register with Address
        // r = 0..3
        // r <- EA
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

        int val = Memory.get_from_memory_int(Utils.calculate_effective_address(I, "00", Address));

        // Load Index Register from Memory
        // x = 1..3
        // Xx <- c(EA)
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
        // Store Index Register to Memory
        // X = 1..3
        // Memory(EA) <- c(Xx)

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
        // Transfer Instructions
        // Transfer instructions change control of program execution
        // c(r) means “the contents of register r”, r = 0..3

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
        // Jump If Not Equal
        // If c(r) != 0, then PC <- EA
        // Else PC <- PC + 1

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
        // Jump If Condition Code
        // cc replaces r for this instruction
        // cc takes values 0, 1, 2, 3 as above and specifies
        // the bit in the Condition Code Register to check;
        // If cc bit = 1, PC <- EA
        // Else PC <- PC + 1
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
        // Unconditional Jump To Address
        // PC <- EA,
        // Note: r is ignored in this instruction
        Registers.update_registers("PC", Utils.calculate_effective_address(I, X, Address));
    }


    public static void JSR(String X, String I, String Address) {
        // Jump and Save Return Address
        // R3 <- PC+1;
        // PC <- EA
        // R0 should contain pointer to arguments
        // Argument list should end with –1 (all 1s) value
        Registers.update_registers("R3", Registers.get_register_value_int("PC") + 1);
        Registers.update_registers("PC", Utils.calculate_effective_address(I, X, Address));
    }


    public static void RFS(String Address) {
        // Return From Subroutine w/ return code as Immed
        // portion (optional) stored in the instruction’s address field.
        // R0 <- Immed; PC <- c(R3) 
        // IX, I fields are ignored.
        Registers.update_registers("R0", Integer.parseInt(Address));
        Registers.update_registers("PC", Registers.get_register_value_int("R3"));
    }

    public static void SOB(String R, String X, String I, String Address) {
        int val = 0;
        // Subtract One and Branch. R = 0..3
        // r <- c(r) – 1
        // If c(r) > 0, PC <- EA;
        // Else PC <- PC + 1
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
        // Jump Greater Than or Equal To
        // If c(r) >= 0, then PC <- EA
        // Else PC <- PC + 1

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

// Arithmetic and Logical Instructions

// Arithmetical and Logical instructions perform most of the computational work in the machine.
// For immediate instructions, the Address portion is considered to be the Immediate value.
// The maximum absolute value of the Immediate value is 31 (5 bits).

    public static void AMR(String R, String X, String I, String Address) {
        int val = 0;

        // AMR -> Add Memory to Register
        // Add Memory To Register, r = 0..3 
		// r <- c(r) + c(EA)

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
        // Subtract Memory From Register
        // r = 0..3
        // r <- c(r) – c(EA)
            case "00":
                Registers.update_registers("R0", Registers.get_register_value_int("R0") - Memory.get_from_memory_int(Utils.calculate_effective_address(I, X, Address)));
                break;
            case "01":
                Registers.update_registers("R1", Registers.get_register_value_int("R1") - Memory.get_from_memory_int(Utils.calculate_effective_address(I, X, Address)));
                break;
            case "02":
                Registers.update_registers("R2", Registers.get_register_value_int("R2") - Memory.get_from_memory_int(Utils.calculate_effective_address(I, X, Address)));
                break;
            case "03":
                Registers.update_registers("R3", Registers.get_register_value_int("R3") - Memory.get_from_memory_int(Utils.calculate_effective_address(I, X, Address)));
                break;
        }
    }


    public static void AIR(String R, String Address) {
        int val = 0;

        // AIR -> Add Immediate to Register, r = 0..3
        // r <- c(r) + immed

        // 1. if immed = 0, does nothing
        // 2. if c(r) = 0, loads r with immed
        // IX and I are ignored in this instruction

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
        // Subtract Immediate from Register, r = 0..3
        // r -> c(r) - Immed

        // 1. if Immed = 0, does nothing
        // 2. if c(r) = 0, loads r1 with –(Immed)
        // IX and I are ignored in this instruction

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

        // Multiply Register by Register
        // rx, rx+1 <- c(rx) * c(ry)
        // rx must be 0 or 2
        // ry must be 0 or 2
        // rx contains the high order bits, rx+1 contains the low order bits of the result

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
        // Divide Register by Register
        // rx, rx+1 <- c(rx)/ c(ry)
        // rx must be 0 or 2
        // rx contains the quotient; rx+1 contains the remainder
        // ry must be 0 or 2
        // If c(ry) = 0, set cc(3) to 1 (set DIVZERO flag)
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
        // Test the Equality of Register and Register
        // If c(rx) = c(ry), set cc(4) <- 1; else, cc(4) <- 0

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
        // AND-> Logical And of Register and Register
        // c(rx) <- c(rx) AND c(ry)

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
        // Logical Or of Register and Register
        // c(rx) <- c(rx) OR c(ry)

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
        // Logical Not of Register To Register
        // c(rx) <- NOT c(rx)
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


    public static void ABS(String R1, String R2) {

        // Absolute Value

        int xxval = 0;
        int yyval = 0;
        int val;

        switch (R1) {
            case "00":
                xxval = Registers.get_register_value_int("R0");
                break;
            case "01":
                xxval = Registers.get_register_value_int("R1");
                break;
            case "10":
                xxval = Registers.get_register_value_int("R2");
                break;
            case "11":
                xxval = Registers.get_register_value_int("R3");
                break;
        }

        switch (R2) {
            case "00":
                yyval = Registers.get_register_value_int("R0");
                break;
            case "01":
                yyval = Registers.get_register_value_int("R1");
                break;
            case "10":
                yyval = Registers.get_register_value_int("R2");
                break;
            case "11":
                yyval = Registers.get_register_value_int("R3");
                break;
        }


        if (xxval >= yyval) {
            val = xxval - yyval;
        } else {
            val = yyval - xxval;
        }

        switch (R1) {
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


    public static void GMI(String R1, String R2) {
        int x = 0;
        int y = 0;

        switch (R1) {
            case "00":
                x = Registers.get_register_value_int("R0");
                break;
            case "01":
                x = Registers.get_register_value_int("R1");
                break;
            case "10":
                x = Registers.get_register_value_int("R2");
                break;
            case "11":
                x = Registers.get_register_value_int("R3");
                break;
        }

        switch (R2) {
            case "00":
                y = Registers.get_register_value_int("R0");
                break;
            case "01":
                y = Registers.get_register_value_int("R1");
                break;
            case "10":
                y = Registers.get_register_value_int("R2");
                break;
            case "11":
                y = Registers.get_register_value_int("R3");
                break;
        }


        int min = 100000;
        int index = 0;
        for (int i = x; i <= y; i++) {
            if (Memory.get_from_memory_int(i) <= min) {
                min = Memory.get_from_memory_int(i);
                index = i;
            }
        }

        Registers.update_registers("R3", index);

        // System.out.println(index);

    }
// Shift/Rotate Operations

// Shift and Rotate instructions manipulate a datum in a register.

// Arithmetic Shift (A/L = 0) instructions move a bit string to the right or left, with excess
// bits discarded (although one or more bits might be preserved in flags). The sign bit is not shifted in this instruction.
// Logical Shift (A/L = 1) instructions move a bit string left or right, with excess bits 
// discarded and zero(es) inserted at the opposite end.
// Logical Rotate (A/L = 1) instructions are similar to shift instructions, except that rotate
// instructions are circular, with the bits shifted out one end returning on the other end. Rotates can be to the left or right.

    public static void SRC(String R, String RL, String AL, String Count) {
       // Shift Register by Count
       // c(r) is shifted left (L/R =1) or right (L/R = 0) either
       // logically (A/L = 1) or arithmetically (A/L = 0)
       // Count = 0…15
       // If Count = 0, no shift occurs
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
        // Rotate Register by Count
        // c(r) is rotated left (L/R = 1) or right (L/R =0) either
        // logically (A/L =1)
        // Count = 0…15
        // If Count = 0, no rotate occurs
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

// I/O Operations  
// They communicate with the peripherals attached to the computer system.

    public static void takeInput() {


        String input = "";

        input = JOptionPane.showInputDialog("Please enter the input");

        System.out.println(input);
    }


    public static void IN(String R, String DevId) {
        // IN r, DevId
        // Input Character to Device from Register
        String x = "";

        for(int i = 500; i< 520; i++)
        {
            x = x + " " + String.valueOf(Memory.get_from_memory_int(i));
        }

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
        // OUT r, DevId
        // Output Character to Device from Register
        if (Integer.parseInt(DevId) == 1) {

            switch (R) {
                case "00":
                    DescText = String.valueOf(Registers.get_register_value_int("R0"));
                case "01":
                    DescText = String.valueOf(Registers.get_register_value_int("R1"));
                case "10":
                    DescText = String.valueOf(Registers.get_register_value_int("R2"));
                case "11":
                    DescText = String.valueOf(Registers.get_register_value_int("R3"));
            }
        }
    }


    public static void CHK(String R, String DevId) {
        // Checking  Device Status to Register 
        // r = 0..3 c(r) <- device status 

    }

}
