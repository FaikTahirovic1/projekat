package ba.unsa.etf.rpr.Exception;

public class F1Exception extends Exception{
    public F1Exception(String msg, Exception reason){
        super(msg, reason);
    }
    public F1Exception(String msg){
        super(msg);
    }
}
