package tv.codely.backoffice.courses.domain;

import lombok.Value;

import java.util.UUID;

@Value
public class BackofficeCourse {
    UUID id;
    String name;
    String duration;
}
