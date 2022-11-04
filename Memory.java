import java.util.Arrays;
import java.util.List;

// /**
// Memory Control Unit
// Reserved Locations Of Memory:
// 0 - Reserved for the Trap instruction for Part III.
// 1 - Reserved for a machine fault
// 2 - Store PC for Trap
// 3 - NU
// 4 - Store PC for Machine Fault
// 5 - NU

public class Memory
{
    // memory size 2048 initially
    // expandable memory size to 4096

    static int size = 2048;
    static int[] ProcessorMemory = new int[size];
    static int[] ProcessorMemoryTemp;

    public static void create_memory(int mem_size)
    {
        size = mem_size;
        ProcessorMemory = new int[size];
    }

    public static void reset_memory()
    {
        ProcessorMemory = new int[size];
    }

    public static void modify_memory(String modification)
    {
        if (modification.equals("expand") && ProcessorMemory.length == 2048)
        {
            ProcessorMemoryTemp = new int[4096];
            for(int i=0; i<ProcessorMemory.length; i++)
            {
                ProcessorMemoryTemp[i] = ProcessorMemory[i];
            }
            ProcessorMemory = ProcessorMemoryTemp;
            size = 4096;
        }
        else if (modification.equals("contract") && ProcessorMemory.length == 4096)
        {
            ProcessorMemoryTemp = new int[2048];
            for(int i=0; i<ProcessorMemoryTemp.length; i++)
            {
                ProcessorMemoryTemp[i] = ProcessorMemory[i];
            }
            ProcessorMemory = ProcessorMemoryTemp;
            size = 2048;
        }
    }

    public static int get_from_memory_int(int addr)
    {
        // Fetching memory as integer
        return ProcessorMemory[addr];
    }

    public static String get_from_memory_string(int addr)
    {
        // Fetching memory as string
        return String.format("%16s", Integer.toBinaryString(ProcessorMemory[addr])).replace(' ', '0');
    }

    public static void store_to_memory(int addr, int val)
    {
        //  Store directly into memory using address and value
        ProcessorMemory[addr] = val;
    }

    public static void clear_memory_address(int addr)
    {
        // Clearing Memory address
        ProcessorMemory[addr] = 0;
    }

}
