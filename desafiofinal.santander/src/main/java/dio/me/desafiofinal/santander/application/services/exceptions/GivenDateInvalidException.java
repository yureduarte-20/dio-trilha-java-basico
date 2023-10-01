package dio.me.desafiofinal.santander.application.services.exceptions;

public class GivenDateInvalidException extends Exception{
    private String model;
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }

    public GivenDateInvalidException(String model, String message) {
        super(message);
        this.model = model;
    }
}
