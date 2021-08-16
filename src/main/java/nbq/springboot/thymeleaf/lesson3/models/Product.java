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

    @Column(name = "main_Image")
    private String mainImage;

    @Column(name = "extra_Image1")
    private String extraImage1;

    @Column(name = "extra_Image2")
    private String extraImage2;

    @Column(name = "extra_Image3")
    private String extraImage3;

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

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public String getExtraImage1() {
        return extraImage1;
    }

    public void setExtraImage1(String extraImage1) {
        this.extraImage1 = extraImage1;
    }

    public String getExtraImage2() {
        return extraImage2;
    }

    public void setExtraImage2(String extraImage2) {
        this.extraImage2 = extraImage2;
    }

    public String getExtraImage3() {
        return extraImage3;
    }

    public void setExtraImage3(String extraImage3) {
        this.extraImage3 = extraImage3;
    }

    public List<ProductDetail> addProductDetail(Integer id, String name, String info) {
        this.listDetails.add(new ProductDetail(id, name, info,this));
        return this.listDetails;
    }
    public List<ProductDetail> addProductDetail(String name, String info) {
        this.listDetails.add(new ProductDetail(name, info,this));
        return this.listDetails;
    }

    @Transient
    public String getMainImagePath() {
        return "/images/" + this.getCategory().getName() + "/"  + this.getId() + "/" + this.getMainImage();
    }

    @Transient
    public String getExtraImage1Path() {
        return "/images/" + this.getCategory().getName() + "/"  + this.getId() + "/" + this.getExtraImage1();
    }

    @Transient
    public String getExtraImage2Path() {
        return "/images/" + this.getCategory().getName() + "/"  + this.getId() + "/" + this.getExtraImage2();
    }

    @Transient
    public String getExtraImage3Path() {
        return "/images/" + this.getCategory().getName() + "/"  + this.getId() + "/" + this.getExtraImage3();
    }
}
