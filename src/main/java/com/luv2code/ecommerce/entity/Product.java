package com.luv2code.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private ProductCategory category;

    @Column(name = "sku") private String sku;

    @Column(name = "name") private String name;

    @Column(name = "description") private String description;

    @Column(name = "unit_price") private BigDecimal unitPrice;

    @Column(name = "image_url") private String imageUrl;

    @Column(name = "active") private boolean active;

    @Column(name = "units_in_stock") private int unitsInStock;

    @Column(name = "date_created")
    @CreationTimestamp
    private LocalDate dateCreated;

    @Column(name = "last_updated")
    @UpdateTimestamp
    private LocalDate lastUpdated;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return active == product.active && unitsInStock == product.unitsInStock && Objects.equals(id, product.id) && Objects.equals(category, product.category) && Objects.equals(sku, product.sku) && Objects.equals(name, product.name) && Objects.equals(description, product.description) && Objects.equals(unitPrice, product.unitPrice) && Objects.equals(imageUrl, product.imageUrl) && Objects.equals(dateCreated, product.dateCreated) && Objects.equals(lastUpdated, product.lastUpdated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, sku, name, description, unitPrice, imageUrl, active, unitsInStock, dateCreated, lastUpdated);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", category=" + category +
                ", sku='" + sku + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", unitPrice=" + unitPrice +
                ", imageUrl='" + imageUrl + '\'' +
                ", active=" + active +
                ", unitsInStock=" + unitsInStock +
                ", dateCreated=" + dateCreated +
                ", lastUpdated=" + lastUpdated +
                '}';
    }
}
