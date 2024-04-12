package com.example.webframeworkhw1.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubjectDetailResponse {
    int year;
    int semester;
    String name;
    String type;
    String professor;
    int credit;
}
