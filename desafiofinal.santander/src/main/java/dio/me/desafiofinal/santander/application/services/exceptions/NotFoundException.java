package dio.me.desafiofinal.santander.application.services.exceptions;

public class NotFoundException extends Exception {
    private String model;
    private String message;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NotFoundException(String model, String message) {
        this.model = model;
        this.message = message;
    }
    @Override
    public String toString() {
        return String.format("{\n\t\"message\":\"%s\",\n\t\"model\": \"%s\"\n}", this.message, this.model);
    }
}
