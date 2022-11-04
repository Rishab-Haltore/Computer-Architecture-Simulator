import java.lang.Math;

public class Registers {

    // 16 Bit General Purpose Registers 
    static String R0;
    static String R1;
    static String R2;
    static String R3;

    // 16 Bit Instruction Register: holds the instruction to be executed.
    static String idx;

    // 4 Bit Condition Code: set when arithmetic/logical operations are executed.
    static String CC;

    // 16 Bit Memory Buffer Register: holds the word just fetched from or the 
    // word to be last stored into memory.
    static String MBR;

    // 16 Bit Memory Address Register: holds the address of the word to be fetched from memory
    static String MAR;

    // 4 Bit Machine Fault Register: contains the ID code 0f a machine fault after it occurs.
    static String MFR;

    // 12 Bit Program Counter: holds address of next instruction to be executed.
    static String PC;

    // 16 Bit Index Registers
    static String X1;
    static String X2;
    static String X3;

    public static void create_reset_registers(){

        // Initializing all registers


        R0 = "0000000000000000";
        R1 = "0000000000000000";
        R2 = "0000000000000000";
        R3 = "0000000000000000";

        idx = "0000000000000000";

        CC = "0000";

        MBR = "0000000000000000";
        MAR = "000000000000";
        MFR = "0000";

        PC = "000000000000";

        X1 = "0000000000000011";
        X2 = "0000000000000000";
        X3 = "0000000000000000";
    }


    public static void update_registers(String reg_name, int val)
    {
        // Switch case to update registers as needed.
        switch (reg_name)
        {
            case "R0" : R0 = String.format("%16s", Integer.toBinaryString(val)).replace(' ', '0'); break;
            case "R1" : R1 = String.format("%16s", Integer.toBinaryString(val)).replace(' ', '0'); break;
            case "R2" : R2 = String.format("%16s", Integer.toBinaryString(val)).replace(' ', '0'); break;
            case "R3" : R3 = String.format("%16s", Integer.toBinaryString(val)).replace(' ', '0'); break;
            case "idx" : idx = String.format("%16s", Integer.toBinaryString(val)).replace(' ', '0'); break;
            case "MBR" : MBR = String.format("%16s", Integer.toBinaryString(val)).replace(' ', '0'); break;
            case "MAR" : MAR = String.format("%12s", Integer.toBinaryString(val)).replace(' ', '0'); break;
            case "MFR" : MFR = String.format("%4s", Integer.toBinaryString(val)).replace(' ', '0'); break;
            case "PC" : PC = String.format("%12s", Integer.toBinaryString(val)).replace(' ', '0'); break;
            case "X1" : X1 = String.format("%16s", Integer.toBinaryString(val)).replace(' ', '0'); break;
            case "X2" : X2 = String.format("%16s", Integer.toBinaryString(val)).replace(' ', '0'); break;
            case "X3" : X3 = String.format("%16s", Integer.toBinaryString(val)).replace(' ', '0'); break;
            case "CC" : CC = String.format("%4s", Integer.toBinaryString(val)).replace(' ', '0'); break;
        }
    }


    public static String get_register_value_string(String reg_name)
    {
        String result;
        // Switch case to fetch string values from Registers as needed.
        switch (reg_name) {
            case "R0" : result = R0; break;
            case "R1" :result  =  R1; break;
            case "R2" : result =  R2; break;
            case "R3" : result =  R3; break;
            case "idx" : result =  idx; break;
            case "MBR" : result = MBR; break;
            case "MAR" : result = MAR; break;
            case "MFR" : result = MFR; break;
            case "PC" : result = PC; break;
            case "X1" : result = X1; break;
            case "X2" : result = X2; break;
            case "X3" : result = X3; break;
            case "CC" : result =  CC; break;
            default : result = "Invalid input"; break;
        }
        return result;
    }


    public static int get_register_value_int(String reg_name)
    {
        // Switch case to fetch integer values from Registers as needed.
        int result_int;
        switch (reg_name) {
            case "R0" : result_int = Integer.parseInt(R0, 2); break;
            case "R1" : result_int = Integer.parseInt(R1, 2); break;
            case "R2" : result_int =  Integer.parseInt(R2, 2); break;
            case "R3" : result_int = Integer.parseInt(R3, 2); break;
            case "idx" : result_int = Integer.parseInt(idx, 2); break;
            case "MBR" : result_int = Integer.parseInt(MBR, 2); break;
            case "MAR" : result_int = Integer.parseInt(MAR, 2); break;
            case "MFR" : result_int = Integer.parseInt(MFR, 2); break;
            case "PC" : result_int = Integer.parseInt(PC, 2); break;
            case "X1" : result_int =  Integer.parseInt(X1, 2); break;
            case "X2" : result_int = Integer.parseInt(X2, 2); break;
            case "X3" : result_int =  Integer.parseInt(X3, 2); break;
            case "CC" : result_int =  Integer.parseInt(CC, 2); break;
            default : result_int = 0; break;
        }

        return result_int;
    }

}
