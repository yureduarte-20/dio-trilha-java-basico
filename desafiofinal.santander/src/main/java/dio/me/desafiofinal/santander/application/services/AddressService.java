package dio.me.desafiofinal.santander.application.services;

import dio.me.desafiofinal.santander.application.services.exceptions.AlreadyExistsException;
import dio.me.desafiofinal.santander.application.services.exceptions.NotFoundException;
import dio.me.desafiofinal.santander.domain.dto.BarberWithAppointments;
import dio.me.desafiofinal.santander.domain.model.Address;
import dio.me.desafiofinal.santander.domain.model.Barber;

import java.util.List;

public interface AddressRepository {
    Address create(Address  address) throws AlreadyExistsException;
    Address findById(Long id) throws NotFoundException;
    void deleteById(Long id);
    List<Address> getBarbers();

}
