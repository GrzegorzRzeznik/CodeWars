package kyu4.Escaping_The_Matrix;
/*
Help Neo to escape the matrix by throwing himself out!

Neo is an exception, which is defined already:

public final class Neo extends Exception {}
The example test is the same one as the real test case, so if your code pass the example test case, you are good to go!

Hint: although Neo is an exception, there is nothing special about it, the solution is pretty generic.
 */
public class Matrix {

    public static void enter() {
        escapeMatrix(new Neo());
    }
    public static void escapeMatrix(Exception e){
        Matrix.escapeMatrix(e, 0);
    }
    private static <T extends Exception> void escapeMatrix(Exception e, int i) throws T{
        throw (T) e;
    }
}