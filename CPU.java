
public class CPU {



	private static Memory memory = new Memory();
	

	static String addBinary(String a, String b)
	    {   
	          //If the inputs are 0
	        if(a.charAt(0) == '0' && b.charAt(0) == '0'){
	             return "0";
	        }
	        // Initialize result
	        StringBuilder result = new StringBuilder("");
	         
	        // Initialize digit sum
	        int s = 0;        
	 
	        // Traverse both strings starting
	        // from last characters
	        int i = a.length() - 1, j = b.length() - 1;
	        while (i >= 0 || j >= 0 || s == 1)
	        {
	             
	            // Comput sum of last
	            // digits and carry
	            s += ((i >= 0)? a.charAt(i) - '0': 0);
	            s += ((j >= 0)? b.charAt(j) - '0': 0);
	 
	            // If current digit sum is
	            // 1 or 3, add 1 to result
	            result.append((char)(s % 2 + '0'));
	 
	            // Compute carry
	            s /= 2;
	 
	            // Move to next digits
	            i--; j--;
	        }
	       
	          // Remove leading zeros, if any
	          int start = result.length()-1;
	         
	        while(start >=0 && result.charAt(start) == '0') {
	            start--;
	        }
	         
	        if(start != result.length()-1) {
	            result.delete(start+1,result.length());
	        }
	         
	        return result.reverse().toString();
	    }
	 
	public static void instruction(String instruction) {

		//parse instruction
		String opcode=instruction.substring(0,6);
		String IX= instruction.substring(6,8);
		String GPR=instruction.substring(8,10);
		String I=instruction.substring(10,11);
		String Address=instruction.substring(11,16);
		if(opcode.equals("000001"))
		{
			//put codes here
		}
		//Calculate effective address, value=memory[effective_addr]
		int IX_array[]= {1,2,3,4};

		if (I.equals("0")) {
			System.out.println(IX);
			System.out.println(Address);

			if (IX.equals("00")) {
				int effective_addr=Integer.parseInt(Address); 
				System.out.println(effective_addr);

			}
			else if(IX.equals("01")) {
				String first_effective_addr=addBinary(Address,Integer.toBinaryString(IX_array[1])); 
				int effective_addr=Integer.parseInt(first_effective_addr);
				
			
			}
			else if(IX.equals("10")) {
				String first_effective_addr=addBinary(Address,Integer.toBinaryString(IX_array[2])); 
				int effective_addr=Integer.parseInt(first_effective_addr);

			}
			else if(IX.equals("11")) {
				String first_effective_addr=addBinary(Address,Integer.toBinaryString(IX_array[3])); 
				int effective_addr=Integer.parseInt(first_effective_addr);

			}
		}
		if(I.equals("1")) {
			//put codes here
			System.out.println(IX);

			if (IX.equals("00")) {
				int first_effective_addr=Integer.parseInt(Address,2);
				int effective_addr=memory.getValue(first_effective_addr);
				System.out.println(effective_addr);
					}
			else if(IX.equals("01")) {
				String first_effective_addr=addBinary(Address,Integer.toBinaryString(IX_array[1])); 
				System.out.println(first_effective_addr);

				int effective_addr=memory.getValue(Integer.parseInt(first_effective_addr,2));
				System.out.println(effective_addr);


				
					}
			else if(IX.equals("10")) {
				String first_effective_addr=addBinary(Address,Integer.toBinaryString(IX_array[2])); 
				int effective_addr=memory.getValue(Integer.parseInt(first_effective_addr,2));

					

					}
			else if(IX.equals("11")) {
				String first_effective_addr=addBinary(Address,Integer.toBinaryString(IX_array[3])); 
				int effective_addr=memory.getValue(Integer.parseInt(first_effective_addr,2));
					}
		}
			
	}

	private static int toBinaryString(int i) {
		// TODO Auto-generated method stub
		return 0;
	}

}