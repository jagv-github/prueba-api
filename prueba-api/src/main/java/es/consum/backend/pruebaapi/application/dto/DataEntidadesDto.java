package es.consum.backend.pruebaapi.application.dto;

import org.springframework.validation.annotation.Validated;

import es.consum.backend.application.applicationutils.dto.ApplicationDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Validated
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
public class DataEntidadesDto extends ApplicationDto {

    private static final long serialVersionUID = 1L;
    
    @NonNull
    private EntidadesDto data;

    public DataEntidadesDto(@NonNull EntidadesDto data) {
        super();
        this.data = data;
    }

}