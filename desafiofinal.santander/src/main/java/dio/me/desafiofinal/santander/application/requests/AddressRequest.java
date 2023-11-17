package dio.me.desafiofinal.santander.application.requests;

import dio.me.desafiofinal.santander.domain.model.Address;
import dio.me.desafiofinal.santander.domain.model.Barber;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record AddressRequest(
        @Positive
        Long barberID,
        @NotBlank
        String street) {

    public static Address toAddress(Barber barber, AddressRequest addressRequest){
        var address = new Address();
        address.setStreet(addressRequest.street);
        address.setBarber(barber);
        return address;
    }
}
