import java.lang.reflect.InvocationTargetException;

public class Inspector {

    public void inspect(Object obj, boolean recursive) {
        Class c = obj.getClass();
        inspectClass(c, obj, recursive, 0);
    }

    private void inspectClass(Class c, Object obj, boolean recursive, int depth) {
        Object object = null;
        Class classObject = null;
        System.out.println("Class: " + c.getName());
        try {
            classObject = obj.getClass();
            for (int i = 0; i < c.getDeclaredConstructors().length; i++) {
                System.out.println(c.getConstructor(new Class[] {obj.getClass()}));
            }

        }
        catch (NoSuchMethodException e) {

        }


    }
}


