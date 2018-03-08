package core.domain.model.entities._utilities;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class OptionalParamsFactory {

    private static OptionalParamsFactory _instance;

    public static OptionalParamsFactory getInstance(){
        if(OptionalParamsFactory._instance == null) OptionalParamsFactory._instance = new OptionalParamsFactory();

        return _instance;
    }

    private OptionalParamsFactory(){
    }

    @NotNull
    public  String ProcessString(Optional<String> string){
        if(string.isPresent()) return string.get();
        else return "";
    }

    public  Integer ProcessInteger(Optional<Integer> value){
        if(value.isPresent()) return value.get();
        else return 0;
    }

    public  Double ProcessDouble(Optional<Double> value){
        if(value.isPresent()) return value.get();
        else return 0.0;
    }


}
