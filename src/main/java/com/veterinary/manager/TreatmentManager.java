package com.veterinary.manager;

import com.veterinary.converter.PetConverter;
import com.veterinary.converter.PetDtoConverter;
import com.veterinary.converter.TreatmentConverter;
import com.veterinary.converter.TreatmentDtoConverter;
import com.veterinary.dto.PetDto;
import com.veterinary.dto.TreatmentDto;
import com.veterinary.model.Pet;
import com.veterinary.model.Treatment;
import com.veterinary.repository.PetRepository;
import com.veterinary.repository.TreatmentRepository;
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
public class TreatmentManager {

    private final TreatmentRepository repository;

    public List<TreatmentDto> findAll() {
        return repository.findAll().stream()
                .map(treat -> TreatmentConverter.newInstance().convert(treat))
                .collect(Collectors.toList());
    }
    public TreatmentDto findOne(long id) {
        return TreatmentConverter.newInstance().convert(repository.findById(id).get());
    }
    public TreatmentDto create(TreatmentDto treat) {
        Treatment tr= TreatmentDtoConverter.newInstance().convert(treat);
        repository.save(tr);
        return TreatmentConverter.newInstance().convert(tr);
    }
    public ResponseEntity<String> delete(long id) {
        Optional<Treatment> tr =  repository.findById(id);
        if (tr==null){
            return ResponseEntity.ok("Echec Cet enregistrement n'existe pas !");
        }
        repository.deleteById(id);
        return ResponseEntity.ok("Opération réalisée avec succès.");
    }

    public ResponseEntity<String> update(TreatmentDto treatmentDto) {
        Treatment pt = TreatmentDtoConverter.newInstance().convert(treatmentDto);
        boolean exist = repository.existsById(pt.getId());
        if (exist) {
            repository.save(pt);
            return ResponseEntity.ok("Succès Opération réalisée avec succès.");
        }
        else return ResponseEntity.ok("echec cet pet n'existe pas.");
    }


}
