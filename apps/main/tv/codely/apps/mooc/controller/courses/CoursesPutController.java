package tv.codely.apps.mooc.controller.courses;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tv.codely.apps.mooc.dto.CreateCourseDto;
import tv.codely.apps.mooc.mapper.CourseMapper;
import tv.codely.mooc.courses.application.create.CourseCreator;

@RestController
@RequiredArgsConstructor
public final class CoursesPutController {

	private final CourseMapper mapper;

	private final CourseCreator creator;

	@PutMapping(value = "/courses/{id}")
	public ResponseEntity index(@PathVariable final String id, @RequestBody final CreateCourseDto request) {
		this.creator.create(this.mapper.toDomain(id, request));

		return new ResponseEntity(HttpStatus.CREATED);
	}
}
