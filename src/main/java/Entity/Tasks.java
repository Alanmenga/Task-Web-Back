package Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
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

    private String state;

    private Long user_id;

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

    public String getState() { return state; }

    public void setState(String state) { this.state = state; }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
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
        return "Tasks{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", state=" + state +
                ", create_date=" + create_date +
                ", update_date=" + update_date +
                '}';
    }
}
