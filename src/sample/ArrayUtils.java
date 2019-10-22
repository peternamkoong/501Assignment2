import java.lang.reflect.Array;

public class ArrayUtils {

    public static Object getElement(Object array_, int index){
        return Array.get(array_, index);
    }
    public static void setElement(Object array_, int index, Object element){Array.set(array_, index, element);}
}
