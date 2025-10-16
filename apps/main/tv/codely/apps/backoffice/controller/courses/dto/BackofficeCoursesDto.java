package tv.codely.apps.backoffice.controller.courses.dto;

import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
public class BackofficeCoursesDto {

    List<BackofficeCourseDto> courses;

    @Value
    public static class BackofficeCourseDto {
        UUID id;
        String name;
        String duration;
    }
}
