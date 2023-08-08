package com.burakdelice.controller;

import com.burakdelice.model.Category;
import com.burakdelice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import static com.burakdelice.constant.RestApiUrl.*;

@RestController
@RequestMapping(CATEGORY)
@RequiredArgsConstructor
public class CategoryRestController {

    private final CategoryService categoryService;

    //http://localhost:8090/categories/
    @GetMapping("/")
    public List<Category> findAll() {
        return categoryService.findAll();
    }

    //http://localhost:8090/categories/id
    @GetMapping( "/{id}")
    public ResponseEntity<Category> findById(@PathVariable("id") Long id)  {
        return categoryService.findById(id);
    }

    //POST - https://localhost:8090/categories
    @PostMapping("/")
    public Category createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    //UPDATE - https://localhost:8090/categories/1
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateOne(@PathVariable("id") Long id,
                                              @RequestBody Category category) {

        Category categoryInfo = categoryService.findById(id).getBody();
        if (categoryInfo != null) {
            categoryInfo.setId(id);
            categoryInfo.setName(category.getName());
            categoryInfo.setDescription(category.getDescription());
            return categoryService.updateOne(categoryInfo);
        }
        return null;
    }

    //DELETE - https://localhost:8090/categories/1
    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteOne(@PathVariable("id") Long id) {
        return categoryService.deleteOne(id);
    }

    // https://localhost:8090/categories/name
    @GetMapping("/name")
    public ResponseEntity<List<Category>> getCategoriesByName(String name){
        return ResponseEntity.ok(categoryService.getCategoriesByName(name));
    }
}
