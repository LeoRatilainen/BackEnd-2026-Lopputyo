package fi.haagahelia.lopputyo.domain;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String workout;
    private int reps;
    private int sets;
    private int rest;

    @ManyToMany
    @JoinTable(
        name = "Training_category",
        joinColumns = @JoinColumn(name = "training_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();

    public Training() {
    }

    public Training(String workout, int reps, int sets, int rest, Set<Category> category) {
        this.workout = workout;
        this.reps = reps;
        this.sets = sets;
        this.rest = rest;
        this.categories = categories;
    }

    public Long getId() { 
        return id; 
    }
    public void setId(Long id) { 
        this.id = id; 
    }

    public String getWorkout() { 
        return workout; 
    }
    public void setWorkout(String workout) { 
        this.workout = workout; 
    }

    public int getReps() { 
        return reps; 
    }
    public void setReps(int reps) { 
        this.reps = reps; 
    }

    public int getSets() { 
        return sets;
    }
    public void setSets(int sets) { 
        this.sets = sets; 
    }

    public int getRest() { 
        return rest; 
    }
    public void setRest(int rest) { 
        this.rest = rest; 
    }

    public Set<Category> getCategories() {
        return categories;
    }
    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Training [id=" + id + ", workout=" + workout + ", reps=" + reps + ", sets=" + sets + ", rest=" + rest
                + ", categories=" + categories + "]";
    }
}
