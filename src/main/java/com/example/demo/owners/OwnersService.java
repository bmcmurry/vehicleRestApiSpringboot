package com.example.demo.owners;

import com.example.demo.vehicle.Vehicle;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OwnersService {
    private final OwnersRepository ownersRepository;

    public OwnersService(OwnersRepository ownersRepository) {
        this.ownersRepository = ownersRepository;
    }

    public Optional<Owners> getOwnersByAddress(String address) {
            return ownersRepository.findOwnersByAddress(address);
    }

    public List<Owners> getOwners() { return ownersRepository.findAll();}

    public void addNewOwners(Owners owners) {
        Optional<Owners> ownersOptional = ownersRepository
                .findOwnersByAddress(owners.getAddress());
        if (ownersOptional.isPresent()) {
            throw new IllegalStateException("address taken");
        }
        ownersRepository.save(owners);
    }

    public void deleteOwners(Long ownersId) {
        boolean exists = ownersRepository.existsById(ownersId);
        if (!exists)  {
            throw new IllegalStateException("owner with id " + ownersId + " does not exist");
        }
        ownersRepository.deleteById(ownersId);
    }

    @Transactional
    public void updateOwners(Long ownersId, String name, String address) {
        Owners owners = ownersRepository.findById(ownersId)
                .orElseThrow(() -> new IllegalStateException(
                        "owner with id " + ownersId + " does not exist"));

        if (name != null && name.length() > 0 && !Objects.equals(owners.getName(), name)) {
            owners.setName(name);
        }

        if (address != null && address.length() > 0 && !Objects.equals(owners.getAddress(), address)) {
            Optional<Owners> ownersOptional = ownersRepository.findOwnersByAddress(address);
            if (ownersOptional.isPresent() && !ownersOptional.get().getId().equals(ownersId)) {
                throw new IllegalStateException("Address is already taken by another owner");
            }
            owners.setAddress(address);
        }
    }

    @Transactional
    public Optional<Owners> findOwners(Long ownersId) {
        Optional<Owners> owners = Optional.of(ownersRepository.findById(ownersId).get());
        if (owners == null)  {
            throw new IllegalStateException("owner with id " + ownersId + " does not exist");
        }
        return owners;
    }

    public Owners saveOwners(Owners owner) {
        return ownersRepository.save(owner);
    }

}
