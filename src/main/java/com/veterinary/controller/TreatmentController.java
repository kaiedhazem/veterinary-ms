package com.veterinary.controller;

import com.veterinary.dto.PetDto;
import com.veterinary.dto.TreatmentDto;
import com.veterinary.manager.TreatmentManager;
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
@RequestMapping("/api/v1/treatments")
@Validated
@AllArgsConstructor
@Timed
public class TreatmentController {

    private final TreatmentManager service;


    @ApiOperation("get all pets")
    @Timed
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    ResponseEntity<List<TreatmentDto>> getAll() {
        List<TreatmentDto> treatments = service.findAll();
        return ResponseEntity.ok(treatments);
    }

    @ApiOperation("get one pet")
    @Timed
    @GetMapping(produces = APPLICATION_JSON_VALUE,path="{id}")
    ResponseEntity<TreatmentDto> getOne(@PathVariable long id) {
        TreatmentDto tr = service.findOne(id);
        return ResponseEntity.ok(tr);
    }
    @ApiOperation("create pet")
    @Timed
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    ResponseEntity<TreatmentDto> create(@RequestBody TreatmentDto tr) {
        return ResponseEntity.ok(service.create(tr));
    }
    @ApiOperation("delete pet")
    @Timed
    @DeleteMapping(produces = APPLICATION_JSON_VALUE,path="/{id}")
    ResponseEntity<String> delete(@PathVariable long id) {
        return service.delete(id);

    }
    @ApiOperation("update pet")
    @Timed
    @PutMapping(produces = APPLICATION_JSON_VALUE)
    ResponseEntity<String> delete(@RequestBody TreatmentDto treatmentDto) {
        return service.update(treatmentDto);

    }

}
