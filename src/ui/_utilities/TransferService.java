package ui._utilities;

public class TransferService {

    private static TransferService _instance;
    private Object transferObject;

    public static void storeForTransfer(Object obj){
        if(_instance == null){
            _instance = new TransferService();
        }
        _instance.transferObject = obj;
    }

    public static Object fetchStoredObject(){
        return _instance.transferObject;
    }
}
