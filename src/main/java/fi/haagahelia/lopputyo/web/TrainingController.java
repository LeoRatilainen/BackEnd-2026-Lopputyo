package fi.haagahelia.lopputyo.web;

//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.ResponseBody;

import fi.haagahelia.lopputyo.domain.Training;
import fi.haagahelia.lopputyo.domain.TrainingRepository;
import fi.haagahelia.lopputyo.domain.CategoryRepository;

@Controller
public class TrainingController {

    private final TrainingRepository trainingRepository;
    private final CategoryRepository categoryRepository;

    public TrainingController(TrainingRepository trainingRepository, CategoryRepository categoryRepository){
        this.trainingRepository = trainingRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("title", "Training App");
        return "index";
    }

    // @GetMapping("/login")

    @GetMapping("/traininglist")
    public String trainingList(Model model) {
        model.addAttribute("trainings", trainingRepository.findAll());
        return "traininglist";
    }

    @GetMapping("/addtraining")
    public String showAddTrainingForm(Model model) {
        model.addAttribute("training", new Training());
        model.addAttribute("categories", categoryRepository.findAll());
        return "addtraining";
    }
    
    @PostMapping("/addtraining")
    public String addTraining(Training training) {
        trainingRepository.save(training);
        return "redirect:/traininglist";
    }

    @GetMapping("/deletetraining/{id}")
    public String deleteTraining(@PathVariable("id") Long id) {
        trainingRepository.deleteById(id);
        return "redirect:/traininglist";
    }

    @GetMapping("/confirmdelete/{id}")
    public String confirmDelete(@PathVariable("id") Long id, Model model) {
        model.addAttribute("id", id);
        return "deletetraining";
    }

    @GetMapping("/edittraining/{id}")
    public String editTraining(@PathVariable("id") Long id, Model model) {
        model.addAttribute("training", trainingRepository.findById(id).get());
        model.addAttribute("categories", categoryRepository.findAll()); 
        return "edittraining";
    }
}
