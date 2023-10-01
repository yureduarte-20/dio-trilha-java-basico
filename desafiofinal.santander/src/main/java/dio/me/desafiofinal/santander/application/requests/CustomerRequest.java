package dio.me.desafiofinal.santander.application.requests;

import dio.me.desafiofinal.santander.domain.model.Barber;
import dio.me.desafiofinal.santander.domain.model.Customer;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(@NotNull
                              @NotBlank
                              String name,
                              String phone,
                              @NotNull
                              @NotBlank
                              @Email
                              String email) {

    public static Customer toCustumer(CustomerRequest customerRequest) {
        var customer = new Customer();
        customer.setEmail(customerRequest.email);
        customer.setName(customerRequest.name);
        customer.setPhone(customerRequest.phone);
        return customer;
    }
}
