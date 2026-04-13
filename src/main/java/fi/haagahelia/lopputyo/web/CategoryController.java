package fi.haagahelia.lopputyo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import fi.haagahelia.lopputyo.domain.Category;
import fi.haagahelia.lopputyo.domain.CategoryRepository;
import fi.haagahelia.lopputyo.domain.Training;

@Controller
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/categorylist")
    public String showCategories(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "categorylist";
    }

    @GetMapping("/addcategory")
    public String showAddCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "addcategory";
    }

    @PostMapping("/addcategory")
    public String saveCategory(Category category) {
        categoryRepository.save(category);
        return "redirect:/categorylist";
    }

    @GetMapping("/deletecategory/{id}")
    public String deleteCategory(@PathVariable("id") Long id) {
        Category category = categoryRepository.findById(id).orElse(null);

        if (category != null) {

            for (Training training : category.getTrainings()) {
                training.getCategories().remove(category);
        }
        
            categoryRepository.delete(category);
    }
    
    return "redirect:/categorylist";
}
}
