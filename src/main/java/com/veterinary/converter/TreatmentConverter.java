package com.veterinary.converter;

import com.veterinary.dto.CustomerDto;
import com.veterinary.dto.TreatmentDto;
import com.veterinary.model.Treatment;
import lombok.Data;

import java.util.Objects;
import java.util.Optional;
import org.springframework.core.convert.converter.Converter;



@Data(staticConstructor = "newInstance")
public class TreatmentConverter implements Converter<Treatment, TreatmentDto> {

    @Override
    public TreatmentDto convert(Treatment treatment) {
        if (Objects.isNull(treatment)) {
            return null;
        }
        return TreatmentDto.builder()
                .id(treatment.getId())
                .price(treatment.getPrice())
                .date(treatment.getDate())
                .status(treatment.getStatus())
                .type(treatment.getType())
                .build();
    }
}
