package dio.me.desafiofinal.santander.application.controllers;

import dio.me.desafiofinal.santander.application.requests.AppointmentRequest;
import dio.me.desafiofinal.santander.application.services.AppointmentsService;
import dio.me.desafiofinal.santander.application.services.BarberService;
import dio.me.desafiofinal.santander.application.services.CustomerService;
import dio.me.desafiofinal.santander.application.services.ServicesService;
import dio.me.desafiofinal.santander.application.services.exceptions.AlreadyExistsException;
import dio.me.desafiofinal.santander.application.services.exceptions.GivenDateInvalidException;
import dio.me.desafiofinal.santander.application.services.exceptions.NotFoundException;
import dio.me.desafiofinal.santander.domain.model.Appointment;
import dio.me.desafiofinal.santander.domain.model.Customer;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentControler {
    static final String PATH = "/appointment";
    private AppointmentsService appointmentsService;

    private CustomerService customerService;
    private BarberService barberService;
    private ServicesService servicesService;

    public AppointmentControler(AppointmentsService appointmentsService, CustomerService customerService, BarberService barberService, ServicesService servicesService) {
        this.appointmentsService = appointmentsService;
        this.customerService = customerService;
        this.barberService = barberService;
        this.servicesService = servicesService;
    }


    @GetMapping("")
    ResponseEntity<List<Appointment>> findAll() {
        return ResponseEntity.ok(this.appointmentsService.getAppointments());
    }

    @GetMapping("/{id}")
    ResponseEntity<Appointment> findById(@PathVariable Long id) throws NotFoundException {
        return ResponseEntity.ok(this.appointmentsService.findById(id));
    }

    @PostMapping("")
    ResponseEntity<Appointment> create(@RequestBody @Valid AppointmentRequest appointmentRequest) throws NotFoundException, AlreadyExistsException, GivenDateInvalidException {
        var barber = this.barberService.findById(appointmentRequest.barberID());
        var customer = this.customerService.findById(appointmentRequest.custumerID());
        var service = this.servicesService.findById(appointmentRequest.serviceID());
        var appointment = AppointmentRequest.toAppointment(appointmentRequest, barber, service, customer);
        appointment = this.appointmentsService.create(appointment);
        return ResponseEntity.created(URI.create(PATH + "/" + appointment.getId()))
                .body(appointment);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void deleteById( @PathVariable Long id ){
        this.appointmentsService.deleteById(id);
    }
}
