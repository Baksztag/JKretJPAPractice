import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jkret on 22/12/2017.
 */

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ProductId;

    private String ProductName;
    private int UnitsOnStock;
    private int CategoryId;
    @ManyToOne
    @JoinColumn(name = "SUPPLIER_FK")
    private Supplier supplier;

    @ManyToMany
    private Set<Transaction_> soldIn;

    public Product() {
    }

    public Product(String productName, int unitsOnStock) {
        ProductName = productName;
        UnitsOnStock = unitsOnStock;
        soldIn = new HashSet<>();
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public int getUnitsOnStock() {
        return UnitsOnStock;
    }

    public void setUnitsOnStock(int unitsOnStock) {
        UnitsOnStock = unitsOnStock;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public void addTransaction(Transaction_ transaction) {
        this.soldIn.add(transaction);
    }

    public Set<Transaction_> getSoldIn() {
        return soldIn;
    }

    @Override
    public String toString() {
        return "Product{" +
                "ProductId=" + ProductId +
                ", ProductName='" + ProductName + '\'' +
                '}';
    }
}
