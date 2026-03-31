package com.app.service;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.Course;
import com.app.repository.CourseRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

	@Autowired
	private CourseRepository repository;

	public Course createCourse(Course course) {
		if(course.getLessons()!=null) {
		course.getLessons().forEach(lesson -> lesson.setCourse(course));
		}
		return repository.save(course);
	}

	public List<Course> getAllCourses() {
		return repository.findAll();
	}

	public Course getCourse(Long id) {
		return repository.findById(id).orElseThrow();
	}

	public Course updateCourse(Long id, Course updated) {
		Course course = getCourse(id);
		course.setTitle(updated.getTitle());
		course.setDescription(updated.getDescription());

		course.getLessons().clear();
		updated.getLessons().forEach(lesson -> {
			lesson.setCourse(course);
			course.getLessons().add(lesson);
		});

		return repository.save(course);
	}

	public void deleteCourse(Long id) {
		repository.deleteById(id);
	}
}
