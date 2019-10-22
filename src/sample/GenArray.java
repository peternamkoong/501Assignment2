import java.lang.reflect.Array;

public class GenArray {

    int sizeOfArray;
    String classType;
    Object objArray;

    public GenArray(int sizeOfArray_, String classType_) {
        sizeOfArray = sizeOfArray_;
        classType = classType_;
        classType = "java.lang." + classType_;
        try {
            objArray = Array.newInstance(Class.forName(classType), sizeOfArray);
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public int getSizeOfArray() {
        return sizeOfArray;
    }

    public void setSizeOfArray(int sizeOfArray) {
        this.sizeOfArray = sizeOfArray;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public Object getObjArray() {
        return objArray;
    }

    public void setObjArray(Object objArray) {
        this.objArray = objArray;
    }

    public static void main(String args[]) throws ClassNotFoundException {
        int size = 5;
        String classType = "Integer";
        GenArray genArray = new GenArray(size, classType);

        for(int i=0;i<genArray.getSizeOfArray();i++)
            ArrayUtils.setElement(genArray.getObjArray(), i, i);

        System.out.println(ArrayUtils.getElement(genArray.getObjArray(), 0));
        System.out.println(ArrayUtils.getElement(genArray.getObjArray(), 4));
    }
}
