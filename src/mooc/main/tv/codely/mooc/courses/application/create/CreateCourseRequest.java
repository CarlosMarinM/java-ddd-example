package tv.codely.mooc.courses.application.create;

import lombok.Data;

@Data
public class CreateCourseRequest {
	String id;
	String name;
	String duration;
}
