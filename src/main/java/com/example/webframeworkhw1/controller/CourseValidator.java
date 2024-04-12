package com.example.webframeworkhw1.controller;

import com.example.webframeworkhw1.model.dto.request.CourseRequest;
import com.example.webframeworkhw1.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class CourseValidator implements Validator { // (1)

    private final CourseRepository courseRepository; // (2)

    @Override
    public boolean supports(Class<?> clazz) { // (3)
        return clazz.isAssignableFrom(CourseRequest.class);
    }

    @Override
    public void validate(Object target, Errors errors) { // (4)
        CourseRequest courseRequest = (CourseRequest) target;
        if (courseRepository.existsBySubjectName(courseRequest.getSubjectName())) {
            errors.rejectValue("subjectName", "invalid.subject", new Object[]{courseRequest.getSubjectName()},
                    "이미 신청한 과목입니다.");
        }
    }
}
