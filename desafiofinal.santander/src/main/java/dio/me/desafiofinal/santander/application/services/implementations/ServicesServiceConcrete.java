package dio.me.desafiofinal.santander.application.services.implementations;

import dio.me.desafiofinal.santander.application.services.ServicesService;
import dio.me.desafiofinal.santander.application.services.exceptions.AlreadyExistsException;
import dio.me.desafiofinal.santander.application.services.exceptions.NotFoundException;
import dio.me.desafiofinal.santander.domain.model.Service;
import dio.me.desafiofinal.santander.domain.repository.ServiceRepository;

import java.util.List;
import java.util.NoSuchElementException;

@org.springframework.stereotype.Service
public class ServicesServiceConcrete implements ServicesService {
    private ServiceRepository serviceRepository;
    public ServicesServiceConcrete(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public Service create(Service service) throws AlreadyExistsException{
        if (service.getId() != null && serviceRepository.existsById(service.getId())) {
            throw new AlreadyExistsException(Service.class.getName(),"Servico Já cadastrado.");
        }
        return this.serviceRepository.save(service);
    }

    @Override
    public Service findById(Long id) throws NotFoundException{
        return this.serviceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Service.class.getName(), "Serviço não encontrado"));
    }

    @Override
    public void deleteById(Long id) {
        this.serviceRepository.deleteById(id);
    }

    @Override
    public List<Service> getServices() {
        return this.serviceRepository.findAll();
    }
}
