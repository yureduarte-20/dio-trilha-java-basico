package dio.me.desafiofinal.santander.application.services.implementations;

import dio.me.desafiofinal.santander.application.services.AppointmentsService;
import dio.me.desafiofinal.santander.application.services.exceptions.AlreadyExistsException;
import dio.me.desafiofinal.santander.application.services.exceptions.GivenDateInvalidException;
import dio.me.desafiofinal.santander.application.services.exceptions.NotFoundException;
import dio.me.desafiofinal.santander.domain.model.Appointment;
import dio.me.desafiofinal.santander.domain.repository.AppointmentRepository;
import dio.me.desafiofinal.santander.domain.repository.BarberRepository;
import dio.me.desafiofinal.santander.domain.repository.CustomerRepository;
import dio.me.desafiofinal.santander.domain.repository.ServiceRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
@Service
public class AppointmentServiceConcrete implements AppointmentsService {
    private AppointmentRepository  appointmentRepository;
    private ServiceRepository  serviceRepository;
    private BarberRepository  barberRepository;
    private CustomerRepository customerRepository;

    public AppointmentServiceConcrete(AppointmentRepository appointmentRepository, ServiceRepository serviceRepository, BarberRepository barberRepository, CustomerRepository customerRepository) {
        this.appointmentRepository = appointmentRepository;
        this.serviceRepository = serviceRepository;
        this.barberRepository = barberRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public Appointment create(Appointment appointment) throws AlreadyExistsException, GivenDateInvalidException {
        if(appointment.getId() != null && appointmentRepository.existsById(appointment.getId())){
            throw new AlreadyExistsException(Appointment.class.getName(),"Já existe um agendamento com este id");
        }
        if(appointment.getDate().before(Timestamp.from(Instant.now()))){
            throw new GivenDateInvalidException(Appointment.class.getName(), "A data de agendamento precisa ser maior que hoje");
        }
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment findById(Long id) throws NotFoundException {

        return this.appointmentRepository.findById(id).orElseThrow(() -> new NotFoundException(Appointment.class.getName(), "Agendamento não encontrado")) ;
    }

    @Override
    public void deleteById(Long id) {
        appointmentRepository.deleteById(id);
    }

    @Override
    public List<Appointment> getAppointments() {
        return this.appointmentRepository.findAll();
    }
}
