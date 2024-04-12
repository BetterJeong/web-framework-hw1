package com.example.webframeworkhw1.controller;

import com.example.webframeworkhw1.model.dto.request.CourseRequest;
import com.example.webframeworkhw1.repository.CourseRepository;
import com.example.webframeworkhw1.service.SubjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

@Controller
public class MenuController {

    private final SubjectService subjectService;
    private final CourseValidator courseValidator;

    public MenuController(SubjectService subjectService, CourseValidator courseValidator) {
        this.subjectService = subjectService;
        this.courseValidator = courseValidator;
    }

    @RequestMapping("/creditsEarned")
    public String accessCreditsEarnedPage(Model model) {
        model.addAttribute("credits", subjectService.getCreditList());
        model.addAttribute("totalCredits", subjectService.getTotalCredits());
        return "creditsEarned";
    }

    @RequestMapping("/creditsEarned/detail")
    public String accessCreditsEarnedDetailPage(Model model, @RequestParam int year, @RequestParam int semester) {
        model.addAttribute("subjects", subjectService.getSubjectList(year, semester));
        model.addAttribute("year", year);
        model.addAttribute("semester", semester);
        return "creditsEarnedDetail";
    }

    @RequestMapping("/course/registration")
    public String accessCourseRegistrationPage() {
        return "courseRegistration";
    }

    @PostMapping("/course/application")
    public @ResponseBody String createCourse(@Valid @ModelAttribute CourseRequest courseRequest, Errors errors) {
        if (errors.hasErrors()) {
            return "<script>"
                + "alert(\"올바른 정보를 입력해주세요.\");"
                    + "location.href=\"/course/registration\";"
                + "</script>";
        }
        courseValidator.validate(courseRequest, errors);
        if (errors.hasErrors()) {
            return "<script>"
                    + "alert(\"올바른 정보를 입력해주세요.\");"
                    + "location.href=\"/course/registration\";"
                    + "</script>";
        }

        subjectService.saveCourse(courseRequest);

        return "<script>"
                + "alert(\"수강 신청 완료\");"
                + "location.href=\"/course/registration\";"
                + "</script>";
    }

    @RequestMapping("/course/registration-confirmation")
    public String accessCourseRegistrationConfirmationPage(Model model) {
        model.addAttribute("subjects", subjectService.getSubjectList(2024, 2));
        return "courseRegistrationConfirmation";
    }

}

