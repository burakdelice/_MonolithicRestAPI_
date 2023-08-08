package com.burakdelice.service;

import com.burakdelice.exception.ResourcesNotFoundException;
import com.burakdelice.model.Category;
import com.burakdelice.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> findAll() {
        List<Category> listCategories = categoryRepository.findAll();
        if(listCategories.size()>0){
            return listCategories;
        }else{
            return new ArrayList<Category>();
        }
    }

    public ResponseEntity<Category> findById(Long id)  throws ResourcesNotFoundException{
        Category category = categoryRepository.findById(id)
                .orElseThrow( ()-> new ResourcesNotFoundException("Category not found ID: " + id));
        return ResponseEntity.ok().body(category);
    }
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public ResponseEntity<Category> updateOne(Category categoryInfo) throws ResourcesNotFoundException{
        Category category = categoryRepository.findById(categoryInfo.getId()).orElseThrow( ()-> new ResourcesNotFoundException("Category not found ID: " +categoryInfo.getId()));
        return ResponseEntity.ok(categoryRepository.save(category));
    }

    public Map<String,Boolean> deleteOne(Long id)  throws ResourcesNotFoundException{
        Category category = categoryRepository.findById(id).orElseThrow( ()-> new ResourcesNotFoundException("Category not found ID: " + id));
        categoryRepository.deleteById(id);
        Map<String,Boolean> response = new HashMap<>();
        response.put("Delete",Boolean.TRUE);
        return response;
    }
    public List<Category> getCategoriesByName(String name){
        return categoryRepository.getCategoriesByName(name);
    }

}