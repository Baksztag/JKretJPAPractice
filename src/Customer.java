import javax.persistence.Entity;

/**
 * Created by jkret on 25/12/2017.
 */
@Entity
public class Customer extends Company {
    private double discount;

    public Customer() {

    }

    public Customer(String companyName, String street, String city, String zipCode, double discount) {
        super(companyName, street, city, zipCode);
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
