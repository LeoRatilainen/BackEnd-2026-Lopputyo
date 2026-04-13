package fi.haagahelia.lopputyo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long  musclegroupid;
    private String muscle;
    @ManyToMany(mappedBy = "categories")
    @JsonIgnore
    private Set<Training> trainings = new HashSet<>();

    public Category() {
    }

    public Category(String muscle) {
        this.muscle = muscle;
    }

    public Category(Long musclegroupid, String muscle) {
        this.musclegroupid = musclegroupid;
        this.muscle = muscle;
    }

    public Long getMusclegroupid() { 
        return musclegroupid; 
    }
    public void setMusclegroupid(Long musclegroupid) { 
        this.musclegroupid = musclegroupid; 
    }

    public String getMuscle() {
        return muscle; 
    }
    public void setMuscle(String muscle) {
        this.muscle = muscle; 
    }

    public Set<Training> getTrainings() { 
        return trainings; 
    }
    public void setTrainings(Set<Training> trainings) { 
        this.trainings = trainings; 
    }

    @Override
    public String toString() {
        return "Category [musclegroupid=" + musclegroupid
        + ", muscle=" + muscle
        + "]";
    }
}