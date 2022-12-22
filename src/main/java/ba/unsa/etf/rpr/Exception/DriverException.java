package ba.unsa.etf.rpr.Exception;

public class DriverException extends Exception{
    public DriverException(String msg, Exception reason){
        super(msg, reason);
    }
    public DriverException(String msg){
        super(msg);
    }
}
