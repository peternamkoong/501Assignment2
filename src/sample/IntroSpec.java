import javax.lang.model.type.NullType;
import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.lang.reflect.*;
import java.security.InvalidParameterException;
import java.util.Scanner;

public class IntroSpec {



    public static void fieldSpec() {

        Object object = null;
        Class classObject = null;

        try{
            System.out.print("Enter a class name: ");
            Scanner scanner = new Scanner(System.in);
            String inputString = scanner. nextLine();
            classObject = Class.forName(inputString);
            object = classObject.getConstructor().newInstance();
            Field[] fields = classObject.getDeclaredFields();
            for(Field field : fields){
                if(!Modifier.isPublic(field.getModifiers())){
                    field.setAccessible(true);
                }
                System.out.println(field.getType()+" "+field.getName()+" : "+field.get(object));
            }
        }
        catch(InstantiationException e){
            e.printStackTrace();
        }
        catch(IllegalAccessException e){
            e.printStackTrace();
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        try{
            System.out.print("Enter a field name: \n");
            Scanner scanner = new Scanner(System. in);
            String inputString = scanner. nextLine();
            Field f = classObject.getDeclaredField(inputString);
            if(!Modifier.isPublic(f.getModifiers())){
                f.setAccessible(true);
            }
            Object value = f.get(object);
            Class type = f.getType();
            System.out.println("The type is: "+type);
            System.out.println("The previous value was: "+value);
            System.out.print("Enter a new value: \n");
            scanner = new Scanner(System. in);
            inputString = scanner. nextLine();

            PropertyEditor editor = PropertyEditorManager.findEditor(type);
            editor.setAsText(inputString);
            f.set(object, editor.getValue());

            System.out.println("The new value is: "+f.get(object));

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public static String recIntoSpecArray(Object object, int depth){
        depth++;
        StringBuffer tabs = new StringBuffer();
        for(int j=0;j<depth;j++)
            tabs.append("\t");
        StringBuffer buffer = new StringBuffer();
        Class objectClass = object.getClass();
        try {
            if (objectClass.isArray()){
                buffer.append("\n");
                buffer.append(tabs.toString());
                buffer.append("[");
                for(int i=0;i<Array.getLength(object);i++){
                    if(i>0)
                        buffer.append(", ");
                    Object value = Array.get(object, i);
                    if (value.getClass().isPrimitive() ||
                        value.getClass() == java.lang.String.class ||
                        value.getClass() == java.lang.Long.class ||
                        value.getClass() == java.lang.Integer.class ||
                        value.getClass() == java.lang.Boolean.class){

                        buffer.append(value);

                    }
                    else{
                        buffer.append(recIntoSpecArray(value, depth));
                    }

                }
                buffer.append(tabs.toString());
                buffer.append("]\n");
            }


        }catch (InvalidParameterException e){
            e.printStackTrace();
        }
        return buffer.toString();
    }

    public static  void main(String args[]){
        IntroSpec.fieldSpec();
        String[][] someNames = {
                {"Frodo", "Sam", "Aragorn", "Gandalf", "Boromir", "Legolas"},
                {"Saruman", "Sauron", "Smaug"}
        };

        int[][] someNumbers = {
                {1, 2, 3},
                {4, 5, 6}
        };

        System.out.println(IntroSpec.recIntoSpecArray(someNames,0));
        System.out.println(IntroSpec.recIntoSpecArray(someNumbers,0));
    }


}

