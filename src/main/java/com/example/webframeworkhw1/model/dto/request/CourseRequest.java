package com.example.webframeworkhw1.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseRequest {
    @NotBlank
    @Length(max = 45)
    String subjectName;

    @NotBlank
    @Length(max = 45)
    String subjectType;

    @NotBlank
    @Length(max = 100)
    String professor;

    @NotNull
    int credit;
}
