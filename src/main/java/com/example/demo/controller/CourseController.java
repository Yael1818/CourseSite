package com.example.demo.controller;
import com.example.demo.model.Course;
import com.example.demo.service.CourseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/course")
@CrossOrigin
public class CourseController {
    private CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
//    @GetMapping("/getCourseById/{id}")
//    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
//        Course c =courseRepository.findById(id).orElse(null);
//        if(c==null)
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        return new ResponseEntity<>(c, HttpStatus.OK);
//    }
@GetMapping("/getCourseByid/{id}")
public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
    Course c=courseRepository.findById(id).orElse(null);
    if(c==null) {
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(c, HttpStatus.OK);
}
    @GetMapping("/getAllCourse")
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    @PostMapping("/addCourse")
    public ResponseEntity addCourse(@RequestBody Course course) {
        courseRepository.save(course);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/updateCourse/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course course) {
        if(course.getId()!=id){
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
        Course newCourse=courseRepository.findById(id).orElse(null);
        if(newCourse==null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        newCourse=courseRepository.save(course);
        return new ResponseEntity<>(newCourse,HttpStatus.CREATED);
    }
    @DeleteMapping("/deleteCourse/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
