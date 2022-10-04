


public class ALU
{

    public static void HLT()
    {
        Frame.run = false;
    }

    public static void LDR(String Register, String X, String I, String Address)
    {

        int val = Memory.get_from_memory_int(Utils.calculate_effective_address(I, X, Address));

        switch (Register)
        {
            case "00" : Registers.update_registers("R0", val); break;
            case "01" : Registers.update_registers("R1", val); break;
            case "10" : Registers.update_registers("R2", val); break;
            case "11" : Registers.update_registers("R3", val); break;
        }
    }

    public static void STR(String Register, String X, String I, String Address)
    {
        int val = 0;

        switch (Register)
        {
            case "00" : val = Registers.get_register_value_int("R0"); break;
            case "01" : val = Registers.get_register_value_int("R1"); break;
            case "10" : val = Registers.get_register_value_int("R2"); break;
            case "11" : val = Registers.get_register_value_int("R3"); break;
        }

        Memory.store_to_memory(Utils.calculate_effective_address(I, X, Address), val);

    }

    public static void LDA(String Register, String X, String I, String Address)
    {
        int val = Utils.calculate_effective_address(I, X, Address);

        switch (Register)
        {
            case "00" : Registers.update_registers("R0", val); break;
            case "01" : Registers.update_registers("R1", val); break;
            case "10" : Registers.update_registers("R2", val); break;
            case "11" : Registers.update_registers("R3", val); break;
        }

    }






}
