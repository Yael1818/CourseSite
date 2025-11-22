package com.example.demo.controller;
import com.example.demo.model.Category;
import com.example.demo.service.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.xml.xpath.XPathVariableResolver;
import java.util.List;

@RequestMapping("api/category")
@RestController
@CrossOrigin
public class CategoryController {

    private CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/getAllCategory1/{id}")
    public ResponseEntity <Category> getCategoryById(@PathVariable Long id) {
        Category c =categoryRepository.findById(id).orElse(null);
        if(c==null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    @GetMapping("/getAllCategory")
    public List<Category> getAll() {

        return categoryRepository.findAll();
    }

    @PostMapping("/addCategory")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        Category c =categoryRepository.save(category);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    @PutMapping("/updateCategory/{id}")
    public ResponseEntity updateCategory(@PathVariable Long id, @RequestBody Category category) {
        if(id!=category.getId()){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        categoryRepository.save(category);
        return new ResponseEntity(HttpStatus.OK);
    }
    @DeleteMapping("/deleteCategory")
    public ResponseEntity deleteCategory(@PathVariable Long id) {
        categoryRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
