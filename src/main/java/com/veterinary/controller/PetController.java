package com.veterinary.controller;

import com.veterinary.converter.PetConverter;
import com.veterinary.converter.PetDtoConverter;
import com.veterinary.dto.PetDto;
import com.veterinary.manager.PetManager;
import com.veterinary.model.Pet;
import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping("/api")
@Validated
@AllArgsConstructor
@Timed
public class PetController {

    private final PetManager petManager;


    @ApiOperation("get all pets")
    @Timed
    @GetMapping(produces = APPLICATION_JSON_VALUE,path="/v1/pets")
    ResponseEntity<List<PetDto>> getAllPets() {
        List<PetDto> pets = petManager.findAll();
        return ResponseEntity.ok(pets);
    }
    @ApiOperation("get pets with weight < 3")
    @Timed
    @GetMapping(produces = APPLICATION_JSON_VALUE,path="/v1/petsunderthreeweight")
    ResponseEntity<List<String>> getPetsWithWeight() {
        return ResponseEntity.ok(petManager.findPetsByWeight());
    }
    @ApiOperation("get one pet")
    @Timed
    @GetMapping(produces = APPLICATION_JSON_VALUE,path="/v1/pets/{id}")
    ResponseEntity<PetDto> getOnePet(@PathVariable long id) {
        PetDto pet = petManager.findOne(id);
        return ResponseEntity.ok(pet);
    }
    @ApiOperation("create pet")
    @Timed
    @PostMapping(produces = APPLICATION_JSON_VALUE,path="/v1/pets")
    ResponseEntity<PetDto> createPet(@RequestBody PetDto pet) {
        return ResponseEntity.ok(petManager.create(pet));
    }
    @ApiOperation("delete pet")
    @Timed
    @DeleteMapping(produces = APPLICATION_JSON_VALUE,path="/v1/pets/{id}")
    ResponseEntity<String> delete(@PathVariable long id) {
       return petManager.delete(id);

    }
    @ApiOperation("update pet")
    @Timed
    @PutMapping(produces = APPLICATION_JSON_VALUE,path="/v1/pets")
    ResponseEntity<String> delete(@RequestBody PetDto petdto) {
        return petManager.update(petdto);

    }
}
