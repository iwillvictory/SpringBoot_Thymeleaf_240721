package nbq.springboot.thymeleaf.lesson3.models;

import javax.persistence.*;

@Entity
@Table(name = "ProductDetails")
public class ProductDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String information;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductDetail() {
    }

    public ProductDetail(String name, String information, Product product) {
        this.name = name;
        this.information = information;
        this.product = product;
    }

    public ProductDetail(Integer id, String name, String information, Product product) {
        this.id = id;
        this.name = name;
        this.information = information;
        this.product = product;
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

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    @Override
    public String toString() {
        return this.name + " : " + this.information;
    }
}
