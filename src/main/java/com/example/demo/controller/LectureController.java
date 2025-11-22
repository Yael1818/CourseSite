package com.example.demo.controller;
import com.example.demo.model.Lecture;
import com.example.demo.service.LectureRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("api/lecture")
@RestController
@CrossOrigin
public class LectureController {

    private LectureRepository lectureRepository;

    public LectureController (LectureRepository lectureRepository){
        this.lectureRepository = lectureRepository;
    }

    @GetMapping("/getAllLectures/{id}")
    public ResponseEntity<Lecture> getLectureById(@PathVariable Long id) {
        Lecture l =lectureRepository.findById(id).orElse(null);
        if(l==null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(l, HttpStatus.OK);
    }

    @GetMapping("/getAllLectures")
    public List<Lecture> getAll() {
        return lectureRepository.findAll();
    }

    @PostMapping("/addLecture")
    public ResponseEntity<Lecture> addLecture(@RequestBody Lecture lecture) {
        lectureRepository.save(lecture);
        return new ResponseEntity<>(lecture, HttpStatus.OK);
    }

    @PutMapping("/updateLecture/{id}")
    public ResponseEntity updateLecture(@PathVariable Long id, @RequestBody Lecture lecture) {
        if(id!=lecture.getId()){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        lectureRepository.save(lecture);
        return new ResponseEntity<>(lecture, HttpStatus.OK);
    }
    @DeleteMapping("/deleteLecture")
    public ResponseEntity deleteLecture(@PathVariable Long id) {
        lectureRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
