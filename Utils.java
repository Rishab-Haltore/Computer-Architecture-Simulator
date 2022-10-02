
public class Utils {

    public static String generate_opcode(String instruction)
    {
        String operation;
        String Reg;
        String X;
        String I;
        String Address;
        String opcode;
        int Address_int;

        String operation_bin = "000000";
        String Reg_bin = "00";
        String X_bin = "00";
        String I_bin = "0";
        String Address_bin = "00000";

        instruction = instruction.replaceAll("\\s+","");

        operation = instruction.substring(0, 3);
        instruction = instruction.substring(3);

        I = instruction.substring(instruction.length() - 4);

        if (I.equals("[,I]"))
        {
            instruction = instruction.substring(0, instruction.length() - 4);
            I = "1";
        }
        else
        {
            I = "0";
        }

        String[] TempArray = instruction.split(",");

        Reg = TempArray[0];
        X = TempArray[1];
        Address = TempArray[2];

//        System.out.println(operation + "\t" + Reg + "\t" + X + "\t" + I + "\t" + Address);


        switch (operation)
        {
            case "LDR" : operation_bin = "000001";
            case "STR" : operation_bin = "000010";
            case "LDA" : operation_bin = "000011";
            case "LDX" : operation_bin = "101001";
            case "STX" : operation_bin = "101010";
        }

        switch (Reg)
        {
            case "R0" : Reg_bin = "00";
            case "R1" : Reg_bin = "01";
            case "R2" : Reg_bin = "10";
            case "R3" : Reg_bin = "11";
        }

        switch (X)
        {
            case "X1" : X_bin = "01";
            case "X2" : X_bin = "10";
            case "X3" : X_bin = "11";
            default : X_bin = "00";
        }

        I_bin = I;

        Address_int = Integer.parseInt(Address);

        Address_bin = String.format("%5s", Integer.toBinaryString(Address_int)).replace(' ', '0');

        opcode = operation_bin + Reg_bin + X_bin + I_bin + Address_bin;

        return opcode;
    }


    public static void execute(String OpCode)
    {
        String operation = OpCode.substring(0, 6);
        String Register = OpCode.substring(6, 8);
        String Index_Reg = OpCode.substring(8, 10);
        String I = OpCode.substring(10, 11);
        String Address = OpCode.substring(11);


        switch(operation)
        {
            case "000000" :ALU.HLT();
            case "000001" : ALU.LDR(Register, Index_Reg, I, Address);
            case "000010" : ALU.STR(Register, Index_Reg, I, Address);
            case "000011" : ALU.LDA(Register, Index_Reg, I, Address);

        }


    }


    public static int calculate_effective_address(String I, String X, String Address)
    {
        int EA = 0;

        if (I.equals("0"))
        {
            if (X.equals("00"))
            {
                EA = Integer.parseInt(Address, 2);
            }
            else if (X.equals("01"))
            {
                EA = Registers.get_register_value_int("X1") + Integer.parseInt(Address, 2);
            }
            else if (X.equals("10"))
            {
                EA = Registers.get_register_value_int("X2") + Integer.parseInt(Address, 2);
            }
            else if (X.equals("11"))
            {
                EA = Registers.get_register_value_int("X3") + Integer.parseInt(Address, 2);
            }
        }
        else if(I.equals("1"))
        {
            if (X.equals("00"))
            {
                EA = Memory.get_from_memory_int(Integer.parseInt(Address, 2));
            }
            else if (X.equals("01"))
            {
                EA = Memory.get_from_memory_int(Registers.get_register_value_int("X1")) + Integer.parseInt(Address, 2);
            }
            else if (X.equals("10"))
            {
                EA = Memory.get_from_memory_int(Registers.get_register_value_int("X2")) + Integer.parseInt(Address, 2);
            }
            else if (X.equals("11"))
            {
                EA = Memory.get_from_memory_int(Registers.get_register_value_int("X3")) + Integer.parseInt(Address, 2);
            }
        }
        return EA;
    }


    public static String toBinary(int x, int len)
    {
        if (len > 0)
        {
            return String.format("%" + len + "s",
                    Integer.toBinaryString(x)).replaceAll(" ", "0");
        }

        return null;
    }


}
