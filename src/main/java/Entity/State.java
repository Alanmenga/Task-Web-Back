package Entity;

import jakarta.persistence.*;

@Entity
@Table
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String state_name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getState_name() {
        return state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    @Override
    public String toString() {
        return "State{" +
                "id=" + id +
                ", state_name='" + state_name + '\'' +
                '}';
    }
}
