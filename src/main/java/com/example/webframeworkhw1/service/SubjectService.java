package com.example.webframeworkhw1.service;

import com.example.webframeworkhw1.model.dto.request.CourseRequest;
import com.example.webframeworkhw1.model.dto.response.SubjectDetailResponse;
import com.example.webframeworkhw1.model.dto.response.SubjectResponse;

import java.util.List;

public interface SubjectService {
    List<SubjectResponse> getCreditList();

    int getTotalCredits();

    List<SubjectDetailResponse> getSubjectList(int year, int semester);

    void saveCourse(CourseRequest courseRequest);
}
