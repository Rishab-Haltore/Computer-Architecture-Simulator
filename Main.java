import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.*;


public class Main
{
    // For Testing Memory
//    public static void main(String[] args)
//    {
//        Memory.create_memory(2048);
//
//        System.out.println(Memory.ProcessorMemory.length);
//
//        Memory.modify_memory("expand");
//
//        System.out.println(Memory.ProcessorMemory.length);
//
//        Memory.modify_memory("contract");
//
//        System.out.println(Memory.ProcessorMemory.length);
//
//        Memory.store_to_memory(30, 55);
//
//        System.out.println(Memory.get_from_memory_int(30));
//
//        Memory.clear_memory_address(30);
//
//        System.out.println(Memory.get_from_memory_int(30));
//    }






//    // For Testing Registers
//    public static void main(String[] args)
//    {
//        Registers.create_reset_registers();
//
//        System.out.println(Registers.get_register_value_int("R0"));
//        System.out.println(Registers.get_register_value_string("R0") + "\n");
//
//        Registers.update_registers("R0", 20);
//
//        System.out.println(Registers.get_register_value_int("R0"));
//        System.out.println(Registers.get_register_value_string("R0") + "\n");
//
//        String[] RegList = {"R0", "R1", "R2", "R3", "idx", "CC", "MBR", "MAB", "MAB", "PC", "X1", "X2", "X3"};
//
//        for(int i=0; i<RegList.length; i++)
//        {
//            Registers.update_registers(RegList[i], (i+1)*10);
//        }
//
//        for(int i=0; i<RegList.length; i++)
//        {
//            System.out.println(RegList[i]);
//            System.out.println(Registers.get_register_value_int(RegList[i]));
//            System.out.println(Registers.get_register_value_string(RegList[i]) + "\n");
//        }
//    }







    // For Testing Utils
//    public static void main(String[] args)
//    {
//
//        // Testing OpCode Generation
//        System.out.println(Utils.generate_opcode("LDR R0, X1, 22[,I]") + "\n");
//        System.out.println(Utils.generate_opcode("STR R3, X3, 22") + "\n");
//
//
//        System.out.println("I -> " + "0" + "\t" + "X -> " + "No X" + "\t" +
//                "Address -> " + String.format("%5s", Integer.toBinaryString(22)).replace(' ', '0'));
//        System.out.println(Utils.calculate_effective_address("0", "00",
//                String.format("%5s", Integer.toBinaryString(22)).replace(' ', '0')) + "\n");
//
//
//
//        // Testing EA Calculation
//        Registers.create_reset_registers();
//        Registers.update_registers("X1", 50);
//
//        System.out.println("I -> " + "0" + "\t" + "X -> " + "X1" + "\t" +
//                "Address -> " + String.format("%5s", Integer.toBinaryString(22)).replace(' ', '0'));
//        System.out.println(Utils.calculate_effective_address("0", "01",
//                String.format("%5s", Integer.toBinaryString(22)).replace(' ', '0')) + "\n");
//
//
//        Memory.create_memory(2048);
//        Memory.store_to_memory(22, 80);
//
//        System.out.println("I -> " + "1" + "\t" + "X -> " + "No X" + "\t" +
//                "Address -> " + String.format("%5s", Integer.toBinaryString(22)).replace(' ', '0'));
//        System.out.println(Utils.calculate_effective_address("1", "00",
//                String.format("%5s", Integer.toBinaryString(22)).replace(' ', '0')) + "\n");
//
//
//        Memory.store_to_memory(22, 80);
//
//        System.out.println("I -> " + "1" + "\t" + "X -> " + "X1" + "\t" +
//                "Address -> " + String.format("%5s", Integer.toBinaryString(22)).replace(' ', '0'));
//        System.out.println(Utils.calculate_effective_address("1", "01",
//                String.format("%5s", Integer.toBinaryString(22)).replace(' ', '0')) + "\n");
//
//    }








    // Testing ALU
//    public static void main(String[] args)
//    {
//        Memory.create_memory(2048);
//        Registers.create_reset_registers();
//
//
//        // Testing LDR
//        Memory.store_to_memory(22, 20);
//        Memory.store_to_memory(75, 63);
//
//        Registers.update_registers("X1", 25);
//
//        System.out.println("LDR R0, X0, 22");
//        Utils.execute(Utils.generate_opcode("LDR R0, X0, 22"));
//        System.out.println(Registers.get_register_value_int("R0") + "\n");
//
//
//        System.out.println("LDR R0, X1, 22");
//        Utils.execute(Utils.generate_opcode("LDR R0, X1, 22"));
//        System.out.println(Registers.get_register_value_int("R0" ) + "\n");
//
//        Memory.reset_memory();
//        Memory.store_to_memory(22, 75);
//        Memory.store_to_memory(75, 1193);
//        Memory.store_to_memory(1293, 5555);
//        Registers.update_registers("X1", 75);
//
//        System.out.println("LDR R0, X0, 50[,I]");
//        Utils.execute(Utils.generate_opcode("LDR R0, X0, 22[,I]"));
//        System.out.println(Registers.get_register_value_int("R0") + "\n");
//
//        System.out.println("LDR R0, X1, 22[,I]");
//        Utils.execute(Utils.generate_opcode("LDR R0, X1, 22[,I]"));
//        System.out.println(Registers.get_register_value_int("R0") + "\n");
//
//
//
//        //Testing STR
//        Memory.reset_memory();
//        Registers.update_registers("R1", 24);
//
//        System.out.println("STR R1, X0, 22");
//        Utils.execute(Utils.generate_opcode("STR R1, X0, 22"));
//        System.out.println(Memory.get_from_memory_int(95) + "\n");
//
//
//        Registers.update_registers("R1", 1150);
//        Registers.update_registers("X1", 22);
//
//
//        System.out.println("STR R1, X1, 22");
//        Utils.execute(Utils.generate_opcode("STR R1, X1, 22"));
//        System.out.println(Memory.get_from_memory_int(145) + "\n");
//
//        Memory.reset_memory();
//        Registers.update_registers("R0", 99);
//        Memory.store_to_memory(22, 1120);
//        Registers.update_registers("X1", 100);
//        Memory.store_to_memory(100, 150);
//
//        System.out.println("LDR R0, X0, 22[,I]");
//        Utils.execute(Utils.generate_opcode("STR R0, X0, 22[,I]"));
//        System.out.println(Memory.get_from_memory_int(1120) + "\n");
//
//        Registers.update_registers("R0", 1620);
//
//        System.out.println("LDR R0, X1, 22[,I]");
//        Utils.execute(Utils.generate_opcode("STR R0, X1, 22[,I]"));
//        System.out.println(Memory.get_from_memory_int(250) + "\n");
//    }




//    public static void main(String[] args) {
//        Memory.reset_memory();
//
//        {
//            String[] mem = new String[]{"0001", "0005", "0003", "0004", "0015", "000A",
//                    "000B", "0020", "0002", "0008", "0015",
//                    "0004", "0003", "0007", "0003", "0008",
//                    "0012", "00B1", "0009", "0021", "000F",
//                    "000E", "000D" };
//
//            for(int i=0; i<mem.length; i++){
//                int index = Integer.parseInt(mem[i], 16) + 8;
//                Memory.store_to_memory(i, index);
//            }
//
//            for(int i=0; i<mem.length; i++){
//                System.out.println(Memory.get_from_memory_int(i));
//            }
//
//
//        }
//    }
}