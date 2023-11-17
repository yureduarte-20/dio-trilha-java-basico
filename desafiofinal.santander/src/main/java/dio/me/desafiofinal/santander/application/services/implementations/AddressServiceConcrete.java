package dio.me.desafiofinal.santander.application.services.implementations;

import dio.me.desafiofinal.santander.application.services.AddressService;
import dio.me.desafiofinal.santander.application.services.exceptions.AlreadyExistsException;
import dio.me.desafiofinal.santander.application.services.exceptions.NotFoundException;
import dio.me.desafiofinal.santander.domain.model.Address;
import dio.me.desafiofinal.santander.domain.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AddressServiceConcrete implements AddressService {
    private AddressRepository addressRepository;

    public AddressServiceConcrete(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address create(Address address) throws AlreadyExistsException {
        if(address.getId() != null && this.addressRepository.existsById(address.getId())){
            throw new AlreadyExistsException(Address.class.getName(), "Já existe");
        }
        return this.addressRepository.save(address);
    }

    @Override
    public Address findById(Long id) throws NotFoundException {
        return this.addressRepository.findById(id).orElseThrow(() -> new NotFoundException(Address.class.getName(), "Não encontrado"));
    }

    @Override
    public void deleteById(Long id) {
        this.addressRepository.deleteById(id);
    }

    @Override
    public List<Address> getBarbers() {
        return this.addressRepository.findAll();
    }
}
