import java.util.Arrays;
import java.util.List;

public class Memory
{

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
        return ProcessorMemory[addr];
    }

    public static String get_from_memory_string(int addr)
    {
        return String.format("%16s", Integer.toBinaryString(ProcessorMemory[addr])).replace(' ', '0');
    }

    public static void store_to_memory(int addr, int val)
    {
        ProcessorMemory[addr] = val;
    }

    public static void clear_memory_address(int addr)
    {
        ProcessorMemory[addr] = 0;
    }

}
