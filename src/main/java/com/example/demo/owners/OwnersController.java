package com.example.demo.owners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/owners")
public class OwnersController {
    private final OwnersService ownersService;

    @Autowired
    public OwnersController(OwnersService ownersService) {
        this.ownersService = ownersService;
    }

    @GetMapping
    public List<Owners> getOwners() { return ownersService.getOwners(); }

    @GetMapping(path = "{address}")
    public Owners getOwnersByAddress(@PathVariable("address") String address) {
        Optional<Owners> owners = ownersService.getOwnersByAddress(address);
        if (owners.isPresent()) {
            return owners.get();
        } else {
            throw new IllegalStateException("Owners not found for address: " + address);
        }
    }


    @PostMapping
    public void registerNewOwners(@RequestBody Owners owners) {
        ownersService.addNewOwners(owners);

    }
    @DeleteMapping(path = "{ownersId}")
    public void deleteOwners(@PathVariable("ownersId") Long ownersId) {
        ownersService.deleteOwners(ownersId);
    }

    @PutMapping(path = "{ownersId}")
    public void updateOwners(
            @PathVariable("ownersId") Long ownersId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String address) {
        ownersService.updateOwners(ownersId, name, address);
    }
}
