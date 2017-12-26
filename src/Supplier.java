import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jkret on 22/12/2017.
 */

@Entity
public class Supplier extends Company {
    private String bankAccountNumber;

    @OneToMany(mappedBy = "supplier")
    private Set<Product> suppliedProducts;

    public Supplier() {
    }

    public Supplier(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
        suppliedProducts = new HashSet<>();
    }

    public Supplier(String companyName, String street, String city, String zipCode, String bankAccountNumber) {
        super(companyName, street, city, zipCode);
        this.bankAccountNumber = bankAccountNumber;
    }

    public void addSuppliedProduct(Product product) {
        this.suppliedProducts.add(product);
        product.setSupplier(this);
    }
}
