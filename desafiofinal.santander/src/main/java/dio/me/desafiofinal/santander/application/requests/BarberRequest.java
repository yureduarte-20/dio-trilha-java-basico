package dio.me.desafiofinal.santander.application.requests;


import dio.me.desafiofinal.santander.domain.model.Barber;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record BarberRequest(@NotBlank
                            @NotNull
                            String name, String email,
                            @NotBlank
                            @NotNull
                            String phone) {
    public static Barber toBarber(BarberRequest barberRequest) {
        var barber = new Barber();
        barber.setEmail(barberRequest.email);
        barber.setName(barberRequest.name);
        barber.setPhone(barberRequest.phone);
        return barber;
    }

}
