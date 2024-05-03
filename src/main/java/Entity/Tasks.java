package Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Entity
@Table
public class Tasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @CreationTimestamp
    private LocalDate create_date;

    @UpdateTimestamp
    private LocalDate update_date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreateDate() {
        return create_date;
    }

    public void setCreateDate(LocalDate createDate) {
        this.create_date = createDate;
    }

    public LocalDate getUpdateDate() {
        return update_date;
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.update_date = updateDate;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", createDate=" + create_date +
                ", updateDate=" + update_date +
                '}';
    }
}
