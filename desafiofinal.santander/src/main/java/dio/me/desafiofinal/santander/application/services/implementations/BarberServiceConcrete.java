package dio.me.desafiofinal.santander.application.services.implementations;

import dio.me.desafiofinal.santander.application.services.BarberService;
import dio.me.desafiofinal.santander.application.services.exceptions.AlreadyExistsException;
import dio.me.desafiofinal.santander.application.services.exceptions.NotFoundException;
import dio.me.desafiofinal.santander.domain.model.Appointment;
import dio.me.desafiofinal.santander.domain.model.Barber;
import dio.me.desafiofinal.santander.domain.dto.BarberWithAppointments;
import dio.me.desafiofinal.santander.domain.repository.BarberRepository;
import jakarta.transaction.Transactional;
import org.hibernate.engine.spi.SessionLazyDelegator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BarberServiceConcrete implements BarberService {
    private final BarberRepository barberRepository;

    public BarberServiceConcrete(BarberRepository barberRepository) {
        this.barberRepository = barberRepository;
    }

    @Override
    public Barber create(Barber barber) throws AlreadyExistsException {
        if( barber.getId() != null && barberRepository.existsById(barber.getId())){
            throw new AlreadyExistsException(Barber.class.getName(), "Já cadastrado.");
        }
        return this.barberRepository.save(barber);
    }

    @Override
    public Barber findById(Long id, boolean withAppointments) throws NotFoundException {

        var barber = this.barberRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(Barber.class.getName(), "Barbeiro não encontrado."));
        if(withAppointments){
            barber.setAppointments(barber.getAppointments());
            return barber;
        }
        return barber;
    }
    @Override
    public void deleteById(Long id) {
        this.barberRepository.deleteById(id);
    }

    @Override
    public List<BarberWithAppointments> getBarbers() {
        return  this.barberRepository.findBarberWithAppointmentsCount();
    }
}
