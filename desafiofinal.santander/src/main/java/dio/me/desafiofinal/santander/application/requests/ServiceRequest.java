package dio.me.desafiofinal.santander.application.requests;

import dio.me.desafiofinal.santander.domain.model.Service;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record ServiceRequest(
        @NotNull
        @NotBlank
        String name,
        String description,
        @PositiveOrZero
        double price) {
    public static Service toService(ServiceRequest serviceRequest) {
        var service = new Service();
        service.setDescription(serviceRequest.description);
        service.setName(serviceRequest.name);
        service.setPrice(serviceRequest.price);
        return service;
    }
}
