import com.sun.source.tree.SynchronizedTree;

public class Utils {


    public static String[] InstructionSet1 = {"LDR", "STR", "LDA", "LDX", "STX", "JZ", "JNE", "JCC", "JMA", "JSR", "RFS", "SOB", "JGE", "AMR", "SMR", "AIR", "SIR"};
    public static String[] InstructionSet2 = {"MLT", "DVD", "TRR", "AND", "ORR", "NOT"};
    public static String[] InstructionSet3 = {"SRC", "RRC"};
    public static String[] InstructionSet4 = {"IN", "OUT", "CHK"};

    public static String generate_opcode(String instruction) throws Exception
    {
        String operation = "none";
        String operand1 = "None";
        String operand2 = "none";
        String operand3 = "None";
        String InstructionCode = "0000000000000000";
        int DevID_int;
        int Address_int;
        int Count_int;
        int x = 0;

        boolean R_update = false;
        boolean X_update = false;

        String operation_bin = "000000";
        String Reg_bin = "00";
        String Reg2_bin = "00";
        String X_bin = "00";
        String I_bin = "0";
        String Address_bin = "00000";
        String DevId_bin = "00000";
        String AL = "0";
        String LR = "0";
        String Count_bin = "00000";

        String[] TempOperands = instruction.split("\\s+");

        for (int i = 0; i < TempOperands.length; i++) {
            String TempString = TempOperands[i];

            TempString = TempString.replaceAll("\\s+", "");

            if (TempString.charAt(TempString.length() - 1) == ',') {
                TempOperands[i] = TempString.substring(0, TempString.length() - 1);
            } else {
                TempOperands[i] = TempString;
            }
        }

        operation = TempOperands[0];

        for (int temp_var = 0; temp_var < InstructionSet1.length; temp_var++) {
            if (InstructionSet1[temp_var].equals(operation)) {
                if (TempOperands[TempOperands.length - 1].equals("I")) {
                    I_bin = "1";

                    try {
                        Address_int = Integer.parseInt(TempOperands[TempOperands.length - 1]);
                        Address_bin = String.format("%5s", Integer.toBinaryString(Address_int)).replace(' ', '0');
                    } catch (Exception e) {
                        try {
                            Address_int = Integer.parseInt(TempOperands[TempOperands.length - 2]);
                            Address_bin = String.format("%5s", Integer.toBinaryString(Address_int)).replace(' ', '0');
                        } catch (Exception e1) {
                            Address_int = 0;
                            Address_bin = "00000";
                            x = 1;
                        }
                    }


                    int Tempcounter = 0;
                    for (int i = 1; i < TempOperands.length - 2 + x; i++) {
                        switch (Tempcounter) {
                            case 0:
                                operand1 = TempOperands[i];
                                Tempcounter++;
                                break;
                            case 1:
                                operand2 = TempOperands[i];
                                Tempcounter++;
                                break;
                        }
                    }
                } else {
                    I_bin = "0";

                    try {
                        Address_int = Integer.parseInt(TempOperands[TempOperands.length - 1]);
                        Address_bin = String.format("%5s", Integer.toBinaryString(Address_int)).replace(' ', '0');
                    } catch (Exception e) {
                        Address_int = 0;
                        Address_bin = "00000";
                        x = 1;
                    }

                    int Tempcounter = 0;
                    for (int i = 1; i < TempOperands.length - 1 + x; i++) {
                        switch (Tempcounter) {
                            case 0:
                                operand1 = TempOperands[i];
                                Tempcounter++;
                                break;
                            case 1:
                                operand2 = TempOperands[i];
                                Tempcounter++;
                                break;
                        }
                    }
                }

                if (operand1.charAt(0) == operand2.charAt(0)) {
                    throw new Exception("Two values of X or R were given");
                }


                if (operand1.charAt(0) == 'R') {
                    switch (operand1) {
                        case "R0":
                            Reg_bin = "00";
                            R_update = true;
                            break;
                        case "R1":
                            Reg_bin = "01";
                            R_update = true;
                            break;
                        case "R2":
                            Reg_bin = "10";
                            R_update = true;
                            break;
                        case "R3":
                            Reg_bin = "11";
                            R_update = true;
                            break;
                        default:
                            Reg_bin = "00";
                    }
                } else if (operand1.charAt(0) == 'X') {
                    switch (operand1) {
                        case "X1":
                            X_bin = "01";
                            X_update = true;
                            break;
                        case "X2":
                            X_bin = "10";
                            X_update = true;
                            break;
                        case "X3":
                            X_bin = "11";
                            X_update = true;
                            break;
                        default:
                            X_bin = "00";
                            X_update = true;
                            break;
                    }

                }


                if ((operand2.charAt(0) == 'R') & (!R_update)) {
                    switch (operand2) {
                        case "R0":
                            Reg_bin = "00";
                            R_update = true;
                            break;
                        case "R1":
                            Reg_bin = "01";
                            R_update = true;
                            break;
                        case "R2":
                            Reg_bin = "10";
                            R_update = true;
                            break;
                        case "R3":
                            Reg_bin = "11";
                            R_update = true;
                            break;
                    }
                } else if ((operand2.charAt(0) == 'X') & (!X_update)) {
                    switch (operand2) {
                        case "X1":
                            X_bin = "01";
                            X_update = true;
                            break;
                        case "X2":
                            X_bin = "10";
                            X_update = true;
                            break;
                        case "X3":
                            X_bin = "11";
                            X_update = true;
                            break;
                        default:
                            X_bin = "00";
                            X_update = true;
                            break;
                    }

                }


                switch (operation) {
                    case "LDR":
                        operation_bin = "000001";
                        break;
                    case "STR":
                        operation_bin = "000010";
                        break;
                    case "LDA":
                        operation_bin = "000011";
                        break;
                    case "LDX":
                        operation_bin = "101001";
                        break;
                    case "STX":
                        operation_bin = "101010";
                        break;
                    case "JZ":
                        operation_bin = "001010";
                        break;
                    case "JNE":
                        operation_bin = "001011";
                        break;
                    case "JCC":
                        operation_bin = "001100";
                        break;
                    case "JMA":
                        operation_bin = "001101";
                        break;
                    case "JSR":
                        operation_bin = "001110";
                        break;
                    case "RFS":
                        operation_bin = "001111";
                        break;
                    case "SOB":
                        operation_bin = "010000";
                        break;
                    case "JGE":
                        operation_bin = "010001";
                        break;
                    case "AMR":
                        operation_bin = "000100";
                        break;
                    case "SMR":
                        operation_bin = "000101";
                        break;
                    case "AIR":
                        operation_bin = "000110";
                        break;
                    case "SIR":
                        operation_bin = "000111";
                        break;
                }

                InstructionCode = operation_bin + Reg_bin + X_bin + I_bin + Address_bin;

                return InstructionCode;
            }
        }







        for (int temp_var = 0; temp_var < InstructionSet2.length; temp_var++) {
            if (InstructionSet2[temp_var].equals(operation)) {


                int Tempcounter = 0;
                for (int i = 1; i < TempOperands.length; i++) {
                    switch (Tempcounter) {
                        case 0:
                            operand1 = TempOperands[i];
                            Tempcounter++;
                            break;
                        case 1:
                            operand2 = TempOperands[i];
                            Tempcounter++;
                            break;
                    }
                }
            }

            if ((operand1.charAt(0) == 'X') | (operand2.charAt(0) == 'X')) {

                throw new Exception("Operation does not support X Registers");
            }

            switch (operand1) {
                case "R0":
                    Reg_bin = "00";
                    break;
                case "R1":
                    Reg_bin = "01";
                    break;
                case "R2":
                    Reg_bin = "10";
                    break;
                case "R3":
                    Reg_bin = "11";
                    break;
                default:
                    Reg_bin = "00";
            }

            switch (operand2) {
                case "R0":
                    Reg2_bin = "00";
                    break;
                case "R1":
                    Reg2_bin = "01";
                    break;
                case "R2":
                    Reg2_bin = "10";
                    break;
                case "R3":
                    Reg2_bin = "11";
                    break;
            }


            switch (operation) {
                case "MLT":
                    operation_bin = "010100";
                    break;
                case "DVD":
                    operation_bin = "010101";
                    break;
                case "TRR":
                    operation_bin = "010110";
                    break;
                case "AND":
                    operation_bin = "010111";
                    break;
                case "ORR":
                    operation_bin = "011000";
                    break;
                case "NOT":
                    operation_bin = "011001";
                    break;
            }

            InstructionCode = operation_bin + Reg_bin + Reg2_bin + "000000";
            }









        for (int temp_var = 0; temp_var < InstructionSet3.length; temp_var++) {
            if (InstructionSet3[temp_var].equals(operation)) {


                try {
                    Count_int = Integer.parseInt(TempOperands[TempOperands.length - 1]);
                    Count_bin = String.format("%4s", Integer.toBinaryString(Count_int)).replace(' ', '0');
                } catch (Exception e) {
                    throw new Exception("No Count given");
                }

                int Tempcounter = 0;

                try {

                    for (int i = 1; i < TempOperands.length; i++) {
                        switch (Tempcounter) {
                            case 0:
                                operand1 = TempOperands[i];
                                Tempcounter++;
                                break;
                            case 1:
                                operand2 = TempOperands[i];
                                Tempcounter++;
                                break;
                            case 2:
                                operand3 = TempOperands[i];
                                Tempcounter++;
                                break;
                        }
                    }
                }
                catch (Exception e){
                    throw new Exception("Not Enough Operands");
                }
            }

            switch (operand1) {
                case "R0":
                    Reg_bin = "00";
                    break;
                case "R1":
                    Reg_bin = "01";
                    break;
                case "R2":
                    Reg_bin = "10";
                    break;
                case "R3":
                    Reg_bin = "11";
                    break;
                default:
                    Reg_bin = "00";
            }

            AL = operand2;
            LR = operand3;


            switch (operation) {
                case "SRC":
                    operation_bin = "011111";
                    break;
                case "RRC":
                    operation_bin = "100000";
                    break;
            }

            InstructionCode = operation_bin + Reg_bin + AL + LR + "00" + Count_bin;
        }




        for (int temp_var = 0; temp_var < InstructionSet4.length; temp_var++) {
            if (InstructionSet4[temp_var].equals(operation)) {

                try {
                    DevID_int = Integer.parseInt(TempOperands[TempOperands.length - 1]);
                    DevId_bin = String.format("%5s", Integer.toBinaryString(DevID_int)).replace(' ', '0');
                } catch (Exception e) {
                    throw new Exception("No DevId Given");
                }


                operand1 = TempOperands[1];
            }

            if ((operand1.charAt(0) != 'R')) {

                throw new Exception("Wrong Register Given");
            }

            switch (operand1) {
                case "R0":
                    Reg_bin = "00";
                    break;
                case "R1":
                    Reg_bin = "01";
                    break;
                case "R2":
                    Reg_bin = "10";
                    break;
                case "R3":
                    Reg_bin = "11";
                    break;
                default:
                    Reg_bin = "00";
            }



            switch (operation) {
                case "IN":
                    operation_bin = "111101";
                    break;
                case "OUT":
                    operation_bin = "111110";
                    break;
                case "CHK":
                    operation_bin = "111111";
                    break;
            }

            InstructionCode = operation_bin + Reg_bin + "000" + DevId_bin;
        }

        return InstructionCode;
    }


    public static void execute(String OpCode) {
        String operation = OpCode.substring(0, 6);
        String operation_name = "None";

        switch (operation)
        {
            case "000001" : operation_name = "LDR"; break;
            case "000002" : operation_name = "STR"; break;
            // Complete This
        }





        for (int temp_var = 0; temp_var < InstructionSet1.length; temp_var++) {
            if (InstructionSet1[temp_var].equals(operation_name)) {
                String R = OpCode.substring(6, 8);
                String X = OpCode.substring(8, 10);
                String I = OpCode.substring(10, 11);
                String Address = OpCode.substring(11);

                switch (operation) {
                    case "000000":
                        ALU.HLT();
                        break;
                    case "000001":
                        ALU.LDR(R, X, I, Address);
                        break;
                    case "000010":
                        ALU.STR(R, X, I, Address);
                        break;
                    case "000011":
                        ALU.LDA(R, X, I, Address);
                        break;

                        // Complete this switch-case

                }
            }
        }



        for (int temp_var = 0; temp_var < InstructionSet2.length; temp_var++) {
            if (InstructionSet2[temp_var].equals(operation_name)) {
                String R1 = OpCode.substring(6, 8);
                String R2 = OpCode.substring(8, 10);

                switch (operation) {
                    case "000001":
                        ALU.MLT(R1, R2);
                        break;
                    case "000010":
                        ALU.DVD(R, X, I, Address);
                        break;
                //Complete this Switch Case
                }
            }
        }



        for (int temp_var = 0; temp_var < InstructionSet3.length; temp_var++) {
            if (InstructionSet3[temp_var].equals(operation_name)) {
                String R1 = OpCode.substring(6, 8);
                String AL = OpCode.substring(8, 9);
                String RL = OpCode.substring(9, 10);
                String Count = OpCode.substring(13);

                switch (operation) {
                    case "000001":
                        ALU.SRC(R1, AL, RL, Count);
                        break;
                    case "000010":
                        ALU.RRC(R1, AL, RL, Count);
                        break;
                    //Complete this Switch Case
                }
            }
        }



        for (int temp_var = 0; temp_var < InstructionSet3.length; temp_var++) {
            if (InstructionSet3[temp_var].equals(operation_name)) {
                String R1 = OpCode.substring(6, 8);
                String DevId = OpCode.substring(11);

                switch (operation) {
                    case "000001":
                        ALU.IN(R1, DevId);
                        break;
                    case "000010":
                        ALU.OUT(R1, DevId);
                        break;
                    //Complete this Switch Case
                }
            }
        }






    }


    public static int calculate_effective_address(String I, String X, String Address) {
        int EA = 0;

        if (I.equals("0")) {
            switch (X) {
                case "00":
                    EA = Integer.parseInt(Address, 2);
                    break;
                case "01":
                    EA = Registers.get_register_value_int("X1") + Integer.parseInt(Address, 2);
                    break;
                case "10":
                    EA = Registers.get_register_value_int("X2") + Integer.parseInt(Address, 2);
                    break;
                case "11":
                    EA = Registers.get_register_value_int("X3") + Integer.parseInt(Address, 2);
                    break;
            }
        } else if (I.equals("1")) {
            switch (X) {
                case "00":
                    EA = Memory.get_from_memory_int(Integer.parseInt(Address, 2));
                    break;
                case "01":
                    EA = Memory.get_from_memory_int(Registers.get_register_value_int("X1")) + Integer.parseInt(Address, 2);
                    break;
                case "10":
                    EA = Memory.get_from_memory_int(Registers.get_register_value_int("X2")) + Integer.parseInt(Address, 2);
                    break;
                case "11":
                    EA = Memory.get_from_memory_int(Registers.get_register_value_int("X3")) + Integer.parseInt(Address, 2);
                    break;
            }
        }
        return EA;
    }


    public static void main(String[] args) throws Exception {
        System.out.println(Utils.generate_opcode("IN R3, 22"));
    }

}
