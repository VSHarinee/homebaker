package com.homebakertf.home_baker.controller;

import com.homebakertf.home_baker.model.Product;
import com.homebakertf.home_baker.model.User;
import com.homebakertf.home_baker.service.ProductService;
import com.homebakertf.home_baker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private UserService userService;  // inject UserService

    @Autowired
    private ProductService productService;

    @GetMapping("/vendoral/{vendorId}")
    public List<Object[]> getVendorDetails(@PathVariable Long vendorId) {
        return productService.getVendorUsernameAndPhone(vendorId);
    }
    // Get all products
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // Get product by ID
    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    // Get products by vendor
    @GetMapping("/vendor/{vendorId}")
    public List<Product> getProductsByVendor(@PathVariable Long vendorId) {
        return productService.getProductsByVendor(vendorId);
    }

    // Add new product
//    @PostMapping
//    public Product createProduct(@RequestBody Product product) {
//        return productService.saveProduct(product);
//    }

    @PostMapping
    public Product createProduct(
            @RequestParam String name,
            @RequestParam Double price,
            @RequestParam String vendorPhone,
            @RequestParam Long vendorId,
            @RequestParam("image") MultipartFile imageFile) throws IOException {

        Optional<User> optionalVendor = userService.getUserById(vendorId);
        if (!optionalVendor.isPresent()) {
            throw new RuntimeException("Vendor not found with ID: " + vendorId);
        }

        User vendor = optionalVendor.get();

        // Save the file to a folder, e.g., "uploads/"
        String uploadDir = "D:/home-baker/home-baker/uploads";
        String originalFilename = imageFile.getOriginalFilename();
        java.nio.file.Path filePath = java.nio.file.Paths.get(uploadDir , originalFilename);
        java.nio.file.Files.createDirectories(filePath.getParent()); // ensure folder exists
        imageFile.transferTo(filePath.toFile()); // save file

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setVendorPhone(vendorPhone);
        product.setVendor(vendor);
        product.setImageName(originalFilename); // store filename only

        return productService.saveProduct(product);
    }

    @GetMapping("/image/{filename}")
    public ResponseEntity<byte[]> getProductImage(@PathVariable String filename) throws IOException {
        String uploadDir = "D:/home-baker/home-baker/uploads/";
        java.nio.file.Path filePath = java.nio.file.Paths.get(uploadDir + filename);

        if (!java.nio.file.Files.exists(filePath)) {
            return ResponseEntity.notFound().build();
        }

        byte[] imageBytes = java.nio.file.Files.readAllBytes(filePath);
        String contentType = "image/jpeg"; // or detect dynamically
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, contentType)
                .body(imageBytes);
    }





    // Delete product
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "Product deleted successfully";
    }
}
