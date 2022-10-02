//// Online Java Compiler
//// Use this editor to write, compile and run your Java code online
//
//import java.util.Arrays;
//import java.util.List;
//
//class HelloWorld {
//    public static void main(String[] args) {
//        String instruction = "LDR R0, X1, address[,I]";
//
//        String operation;
//        String Reg;
//        String X;
//        String I;
//        String Address;
//
//        instruction = instruction.replaceAll("\\s+","");
//        System.out.println(instruction + '\n');
//
//        operation = instruction.substring(0, 3);
//        instruction = instruction.substring(3);
//
//        I = instruction.substring(instruction.length() - 4);
//
//        if (I.equals("[,I]"))
//        {
//            instruction = instruction.substring(0, instruction.length() - 4);
//        }
//
//        String[] TempArray = instruction.split(",");
//
//        Reg = TempArray[0];
//        X = TempArray[1];
//        Address = TempArray[2];
//
//        System.out.println(operation + '\n');
//        System.out.println(Reg + '\n');
//        System.out.println(X + '\n');
//        System.out.println(I + '\n');
//        System.out.println(Address + '\n');
//    }
//
//    public static void modify_memory_Ex(String modification)
//    {
//        if (modification.equals("expand") && ProcessorMemory.size() == 2048)
//        {
//            List<Integer> ProcessorMemoryTemp = Arrays.asList(new Integer[4096]);
//            for(int i=0; i<ProcessorMemory.size(); i++)
//            {
//                ProcessorMemoryTemp.set(i, ProcessorMemory.get(i));
//            }
//            ProcessorMemory = ProcessorMemoryTemp;
//        }
//        else if (modification.equals("contract") && ProcessorMemory.size() == 4096)
//        {
//            List<Integer> ProcessorMemoryTemp = Arrays.asList(new Integer[2048]);
//            for(int i=0; i<ProcessorMemory.size(); i++)
//            {
//                ProcessorMemoryTemp.set(i, ProcessorMemory.get(i));
//            }
//            ProcessorMemory = ProcessorMemoryTemp;
//        }
//    }
//}
//
//
