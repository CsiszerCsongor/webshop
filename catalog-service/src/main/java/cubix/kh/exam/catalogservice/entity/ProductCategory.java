package cubix.kh.exam.catalogservice.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(schema = CommonDatabaseConstants.CATALOG_WEBSHOP_SCHEMA_NAME, name = "acts")
public class ProductCategory {
    private Long id;
    private String categoryName;
}
