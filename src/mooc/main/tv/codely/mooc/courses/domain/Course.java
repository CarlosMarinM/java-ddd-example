package tv.codely.mooc.courses.domain;

import lombok.Value;

@Value
public class Course {
    CourseId id;
    CourseName name;
    CourseDuration duration;
}
