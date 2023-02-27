package com.veterinary.manager;

import com.veterinary.converter.PetConverter;
import com.veterinary.converter.PetDtoConverter;
import com.veterinary.dto.PetDto;
import com.veterinary.model.Pet;
import com.veterinary.repository.PetRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
@AllArgsConstructor
public class PetManager {

    private final PetRepository petRepository;

   public List<PetDto> findAll() {
        return petRepository.findAll().stream()
                .map(pet -> PetConverter.newInstance().convert(pet))
                .collect(Collectors.toList());
    }
    public PetDto findOne(long id) {
        return PetConverter.newInstance().convert(petRepository.findById(id).get());
    }
    public PetDto create(PetDto pet) {
        Pet pt=PetDtoConverter.newInstance().convert(pet);
        petRepository.save(pt);
        return PetConverter.newInstance().convert(pt);
    }
    public ResponseEntity<String> delete(long id) {
        Optional<Pet> p =  petRepository.findById(id);
        if (p==null){
            return ResponseEntity.ok("Echec Cet enregistrement n'existe pas !");
        }
        petRepository.deleteById(id);
        return ResponseEntity.ok("ok");
    }

    public ResponseEntity<String> update(PetDto petdto) {
        Pet pt = PetDtoConverter.newInstance().convert(petdto);
        boolean exist = petRepository.existsById(pt.getId());
        if (exist) {
            petRepository.save(pt);
            return ResponseEntity.ok("Succès Opération réalisée avec succès.");
        }
        else return ResponseEntity.ok("echec cet pet n'existe pas.");
    }
    public List<String> findPetsByWeight() {
        return petRepository.findAll().stream().filter(pet -> pet.getWeight() > 3)
                .map(pet -> pet.getName())
                .collect(Collectors.toList());
    }

}
