package nbq.springboot.thymeleaf.lesson3.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 128, nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private float price;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductDetail> listDetails = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Product() { }

    public Product(Integer id) {
        this.id = id;
    }

    public Product(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public List<ProductDetail> getListDetails() {
        return listDetails;
    }

    public void setListDetails(List<ProductDetail> listDetails) {
        this.listDetails = listDetails;
    }

    public List<ProductDetail> addProductDetail(Integer id, String name, String info) {
        this.listDetails.add(new ProductDetail(id, name, info,this));
        return this.listDetails;
    }
    public List<ProductDetail> addProductDetail(String name, String info) {
        this.listDetails.add(new ProductDetail(name, info,this));
        return this.listDetails;
    }
}
