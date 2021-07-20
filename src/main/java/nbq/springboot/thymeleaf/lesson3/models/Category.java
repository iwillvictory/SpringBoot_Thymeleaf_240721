package nbq.springboot.thymeleaf.lesson3.models;

import javax.persistence.*;

@Entity
@Table(name="Categories")
public class Category {
    /*Fields-Start*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 64, nullable = false, unique = true)
    private String name;

    /*Fields-End*/

    /*Accessor Methods-Start*/
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

    /*Accessor Methods-End*/

    /*Constructor-Start*/
    public Category() {
    }

    public Category(Integer id) {
        this.id = id;
    }

    public Category( String name) {
        this.name = name;
    }

    /*Constructor-End*/
}
