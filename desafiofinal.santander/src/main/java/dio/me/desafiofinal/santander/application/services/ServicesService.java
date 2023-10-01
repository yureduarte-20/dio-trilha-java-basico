package dio.me.desafiofinal.santander.application.services;

import dio.me.desafiofinal.santander.application.services.exceptions.AlreadyExistsException;
import dio.me.desafiofinal.santander.application.services.exceptions.NotFoundException;
import dio.me.desafiofinal.santander.domain.model.Service;

import java.util.List;

public interface ServicesService {
    Service create(Service service) throws AlreadyExistsException;
    Service findById(Long id) throws NotFoundException;
    void deleteById(Long id);
    List<Service> getServices();
}
