package nbq.springboot.thymeleaf.lesson3.models;

import javax.persistence.*;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 15, nullable = false, unique = true)
    private String name;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(obj == null) {
            return  false;
        }

        if(getClass() != obj.getClass()){
            return false;
        }

        Role other = (Role)obj;
        if(this.id == null) {
            if(other.getId() != null){
                return false;
            }
        } else if(!id.equals(other.id)){
            return false;
        }

        return true;

    }
}
