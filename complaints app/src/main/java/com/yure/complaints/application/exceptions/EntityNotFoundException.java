package com.yure.complaints.application.exceptions;

public class EntityNotFoundException extends Exception{
    private String entityName;

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public EntityNotFoundException(String message, String entityName) {
        super(message);
        this.entityName = entityName;
    }
    public  EntityNotFoundException(Long ID, Class<?> entity){
        super("Entidade com ID "+ ID + " não encontrada");
        this.entityName = entity.getSimpleName();
    }
}
