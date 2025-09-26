package tv.codely.apps.mooc.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class CreateCourseDto {
    private String name;
    private String duration;
}
