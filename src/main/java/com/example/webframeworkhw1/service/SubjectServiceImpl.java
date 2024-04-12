package com.example.webframeworkhw1.service;

import com.example.webframeworkhw1.model.dto.request.CourseRequest;
import com.example.webframeworkhw1.model.dto.response.SubjectDetailResponse;
import com.example.webframeworkhw1.model.dto.response.SubjectResponse;
import com.example.webframeworkhw1.model.entity.Course;
import com.example.webframeworkhw1.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final CourseRepository courseRepository;

    @Override
    public List<SubjectResponse> getCreditList() {
        List<SubjectResponse> subjectResponses = new ArrayList<>();
        List<Course> courses = courseRepository.findAllByOrderByYear();
        int year = courses.get(0).getYear();

        while (year <= 2023) {
            for (int i = 1; i < 3; i++) {
                List<Course> c = courseRepository.findAllByYearAndSemester(year, i);
                int s = 0;

                for (Course t : c) {
                    s += t.getCredit();
                }

                if (s == 0) {
                    break;
                }

                subjectResponses.add(SubjectResponse.builder()
                        .year(year)
                        .semester(i)
                        .sum(s)
                        .build());
            }

            year++;
        }

        return subjectResponses;
    }

    @Override
    public int getTotalCredits() {
        int result = 0;
        List<Course> courses = courseRepository.findAllByOrderByYear();

        for (Course c : courses) {
            if (c.getYear() < 2024) {
                result += c.getCredit();
            }
        }

        return result;
    }

    @Override
    public List<SubjectDetailResponse> getSubjectList(int year, int semester) {
        List<SubjectDetailResponse> subjectDetailResponses = new ArrayList<>();
        List<Course> courses = courseRepository.findAllByYearAndSemester(year, semester);

        for (Course c : courses) {
            subjectDetailResponses.add(SubjectDetailResponse.builder()
                            .year(c.getYear())
                            .semester(c.getSemester())
                            .name(c.getSubjectName())
                            .type(c.getSubjectType())
                            .professor(c.getProfessor())
                            .credit(c.getCredit())
                            .build());
        }

        return subjectDetailResponses;
    }

    @Override
    public void saveCourse(CourseRequest courseRequest) {
        courseRepository.save(Course.builder()
                        .year(2024)
                        .semester(2)
                        .subjectName(courseRequest.getSubjectName())
                        .subjectCode(LocalDateTime.now().toString())
                        .subjectType(courseRequest.getSubjectType())
                        .professor(courseRequest.getProfessor())
                        .credit(courseRequest.getCredit())
                        .build());
    }
}
