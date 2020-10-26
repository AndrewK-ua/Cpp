import java.util.Date;

public class Product {
    private String name;

    private double price;

    private Date dateOfConsumption;

    private Date dateOfManufacture;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDateOfConsumption() {
        return dateOfConsumption;
    }

    public void setDateOfConsumption(Date dateOfConsumption) {
        this.dateOfConsumption = dateOfConsumption;
    }

    public Date getDateOfManufacture() {
        return dateOfManufacture;
    }

    public void setDateOfManufacture(Date dateOfManufacture) {
        this.dateOfManufacture = dateOfManufacture;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", dateOfConsumption=" + dateOfConsumption +
                ", dateOfManufacture=" + dateOfManufacture +
                '}';
    }
}
