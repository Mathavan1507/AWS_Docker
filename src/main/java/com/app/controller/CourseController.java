package com.app.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.app.entity.Course;
import com.app.service.CourseService;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5174")
public class CourseController {

	@Autowired
	private CourseService service;

	@PostMapping
	public Course create(@Valid @RequestBody Course course) {
		return service.createCourse(course);
	}

	@GetMapping
	public List<Course> getAll() {
		return service.getAllCourses();
	}

	@GetMapping("/{id}")
	public Course getById(@PathVariable Long id) {
		return service.getCourse(id);
	}

	@PutMapping("/{id}")
	public Course update(@PathVariable Long id, @Valid @RequestBody Course course) {
		return service.updateCourse(id, course);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		service.deleteCourse(id);
	}
}
