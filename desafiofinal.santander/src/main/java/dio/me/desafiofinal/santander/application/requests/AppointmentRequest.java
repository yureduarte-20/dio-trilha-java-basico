package dio.me.desafiofinal.santander.application.requests;

import dio.me.desafiofinal.santander.domain.model.Appointment;
import dio.me.desafiofinal.santander.domain.model.Barber;
import dio.me.desafiofinal.santander.domain.model.Customer;
import dio.me.desafiofinal.santander.domain.model.Service;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Timestamp;

public record AppointmentRequest(
        @Positive
        Long barberID,
        @Positive
        Long custumerID,
        @Positive
        Long serviceID,
        @NotNull
        @DateTimeFormat
        Timestamp date) {

    public static Appointment toAppointment(AppointmentRequest ap, Barber barber, Service service, Customer customer) {
        var appointment = new Appointment();
        appointment.setBarber(barber);
        appointment.setService(service);
        appointment.setDate(ap.date);
        appointment.setCustomer(customer);
        return appointment;
    }

}
