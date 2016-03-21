package ZN_Chocolate;

/**
 * Created by aplastunov on 23.10.15.
 */
public class ZNExceptions extends Exception{

    public ZNExceptions() { super(); }
    public ZNExceptions(String message) { super(message); }
    public ZNExceptions(String message, Throwable cause) { super(message, cause); }
    public ZNExceptions(Throwable cause) { super(cause); }
}
