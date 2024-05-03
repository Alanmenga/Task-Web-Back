package Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Entity
@Table
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String pass;

    private Long phone;

    private Long age;

    private String gender;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", pass='" + pass + '\'' +
                ", phone=" + phone +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", create_date=" + create_date +
                ", update_date=" + update_date +
                '}';
    }
}
