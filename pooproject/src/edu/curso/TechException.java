package src.edu.curso;

public class TechException extends Exception {

    public TechException(String message){
        super(message);
    }

    public TechException(){
        super();
    }

    public TechException(Throwable t){
        super(t);
    }
    
}
