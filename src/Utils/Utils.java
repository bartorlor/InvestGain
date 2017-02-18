package Utils;

public class Utils {
 public final static boolean DEBUG = true;    
public static void println(String str){
  System.out.println(str);
}

public static void println(String str ,Exception e){
  println(str + eToStr(e));
}

public static String eToStr(Exception e){
    StackTraceElement[] array= e.getStackTrace();
    String ret ="" ;
    ret += e.getMessage();
    for(StackTraceElement stack : array){
      ret += stack.toString() + "\n";
    }

//    String fullClassName = Thread.currentThread().getStackTrace()[2].getClassName();
//    String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
//    String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
//    int lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
//
//    String str = className + "." + methodName + "():" + lineNumber + ","+  e.getMessage();
    return ret;

}
}
