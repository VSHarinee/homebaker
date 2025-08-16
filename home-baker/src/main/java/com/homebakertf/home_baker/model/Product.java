package com.homebakertf.home_baker.model;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



//    @Lob
//    @Column(name = "image_data", columnDefinition = "LONGBLOB")
//    private byte[] imageData;

    @Column(name = "image_name")
    private String imageName;  // store image file name

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    @Column(name = "vendor_phone")
    private String vendorPhone;

    @ManyToOne
    @JoinColumn(name = "vendor_id", nullable = false)
    private User vendor; // reference to User entity

    public Product() {}

    public Product(String name, Double price, String vendorPhone, User vendor,String imageName) {
        this.name = name;
        this.price = price;
        this.vendorPhone = vendorPhone;
        this.vendor = vendor;
        this.imageName = imageName;

    }



//    public byte[] getImageData() {
//        return imageData;
//    }
//
//    public void setImageData(byte[] imageData) {
//        this.imageData = imageData;
//    }


    public String getImageName() { return imageName; }
    public void setImageName(String imageName) { this.imageName = imageName; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String getVendorPhone() { return vendorPhone; }
    public void setVendorPhone(String vendorPhone) { this.vendorPhone = vendorPhone; }

    public User getVendor() { return vendor; }
    public void setVendor(User vendor) { this.vendor = vendor; }
}
