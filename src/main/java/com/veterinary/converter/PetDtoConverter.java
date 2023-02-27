package com.veterinary.converter;

import com.veterinary.dto.CustomerDto;
import org.springframework.core.convert.converter.Converter;
import com.veterinary.dto.PetDto;
import com.veterinary.model.Pet;
import lombok.Data;

import java.util.Objects;
import java.util.Optional;

@Data(staticConstructor = "newInstance")
public class PetDtoConverter implements Converter<PetDto, Pet> {

    @Override
    public Pet convert(PetDto petDto) {
        if (Objects.isNull(petDto)) {
            return null;
        }
        return Pet.builder()
                .id(petDto.getId())
                .id(petDto.getId())
                .name(petDto.getName())
                .entryDate(petDto.getEntryDate())
                .category(petDto.getCategory())
                .weight(petDto.getWeight())
                .build();
    }
}
