package org.example.orm_jpa;

import org.example.orm_jpa.Entities.Product;
import org.example.orm_jpa.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class OrmJpaApplication implements CommandLineRunner {

    @Autowired
    private ProductRepo productRepo;
    public static void main(String[] args) {
        SpringApplication.run(OrmJpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        productRepo.save(new Product(null, "Samsung Galaxy S22", 370, 45));
//        productRepo.save(new Product(null, "Samsung Galaxy S23", 4800, 13));
//        productRepo.save(new Product(null, "Samsung Galaxy S24", 9500, 8));
        System.out.println("________________________________");
        List<Product> productList1 = productRepo.findAll();
        System.out.println("*****Liste complète des produits :*****");
        productList1.forEach(product -> {
            System.out.println(product.toString());
        });
        System.out.println("________________________________");
        Product product=productRepo.findById(Long.valueOf(2)).get();
        System.out.println("***** Affichage d'un produit par son ID *****");
        System.out.println(product.getId());
        System.out.println(product.getName());
        System.out.println(product.getPrice());
        System.out.println(product.getQuantity());
        System.out.println("________________________________");
        System.out.println("***** Recherche par mot clé (méthode Contains) *****");
        List<Product> productList2=productRepo.findByNameContainsIgnoreCase("S23");
        productList2.forEach(product1 -> {
            System.out.println(product1);
        });
        System.out.println("________________________________");
        System.out.println("***** Recherche par mot clé (méthode Query) *****");
        List<Product> productList3=productRepo.search("%S24%");
        productList3.forEach(product2 -> {
            System.out.println(product2);
        });
        System.out.println("________________________________");
        System.out.println("***** Recherche par prix supérieur à un seuil (findByPrice) *****");
        List<Product> productList4=productRepo.findByPriceGreaterThan(5000);
        productList4.forEach(product3 -> {
            System.out.println(product3);
        });
        System.out.println("________________________________");
        System.out.println("***** Recherche par prix supérieur à un seuil (méthode Query) *****");
        List<Product> productList5=productRepo.searchByPrice(5000);
        productList5.forEach(product4 -> {
            System.out.println(product4);
        });
        System.out.println("________________________________");

    }
}
