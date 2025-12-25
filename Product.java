import java.util.Objects;

public class Product implements Comparable<Product> {
private String productName;
private String country;
private String category;
private double initialPrice;

public Product(String productName, String country, String category, double initialPrice) {
	this.productName = productName;
	this.country = country;
	this.category = category;
	this.initialPrice = initialPrice;
}
public Product() {
	this.productName= "";
	this.country="";
	this.category="";
	this.initialPrice=0.0;
}
public String getProductName() {
	return productName;
}
public void setProductName(String productName) {
	this.productName = productName;
}
public String getCountry() {
	return country;
}
public void setCountry(String country) {
	this.country = country;
}
public String getCategory() {
	return category;
}
public void setCategory(String category) {
	this.category = category;
}
public double getInitialPrice() {
	return initialPrice;
}
public void setInitialPrice(double initialPrice) {
	this.initialPrice = initialPrice;
}
public int compareTo(Product other) {
	return this.productName.compareTo(other.productName);
}
@Override
public String toString() {
	return "TariffInformation: \nProduct name " + productName + ", country " + country + ", category " + category
			+ ", initial price " + initialPrice;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Product other = (Product) obj;
	return Objects.equals(category, other.category) && Objects.equals(country, other.country)
			&& Double.doubleToLongBits(initialPrice) == Double.doubleToLongBits(other.initialPrice)
			&& Objects.equals(productName, other.productName);
}


}
