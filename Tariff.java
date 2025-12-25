import java.util.Objects;

public class Tariff implements Cloneable {
protected String destinationCountry;
protected String originCountry;
protected String productCategory;
protected double minimumTariff;

public String getDestinationCountry() {
	return destinationCountry;
}
public void setDestinationCountry(String destinationCountry) {
	this.destinationCountry = destinationCountry;
}
public String getOriginCountry() {
	return originCountry;
}
public void setOriginCountry(String originCountry) {
	this.originCountry = originCountry;
}
public String getProductCategory() {
	return productCategory;
}
public void setProductCategory(String productCategory) {
	this.productCategory = productCategory;
}
public double getMinimumTariff() {
	return minimumTariff;
}
public void setMinimumTariff(double minimumTariff) {
	this.minimumTariff = minimumTariff;
}
public Tariff(String destinationCountry, String originCountry, String productCategory, double minimumTariff) {
	this.destinationCountry = destinationCountry;
	this.originCountry = originCountry;
	this.productCategory = productCategory;
	this.minimumTariff = minimumTariff;
}
public Tariff() {
	this.destinationCountry = "";
	this.productCategory = "";
	this.originCountry = "";
	this.minimumTariff = 0.0;
	
	}
public Tariff(Tariff other) {
	this.destinationCountry = other.destinationCountry;
	this.minimumTariff = other.minimumTariff;
	this.originCountry = other.originCountry;
	this.productCategory = other.productCategory;
}
public Tariff clone() {
	try{
		return (Tariff) super.clone();
	}
	catch(CloneNotSupportedException e) {
		return null;
	}
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Tariff other = (Tariff) obj;
	return destinationCountry != null && originCountry != null && productCategory != null
		    && other.destinationCountry != null && other.originCountry != null && other.productCategory != null
		    && destinationCountry.equalsIgnoreCase(other.destinationCountry)
		    && originCountry.equalsIgnoreCase(other.originCountry)
		    && productCategory.equalsIgnoreCase(other.productCategory);

}
@Override
public String toString() {
	return "Tariff destinationCountry: " + destinationCountry + "\noriginCountry: " + originCountry
			+ "\nproductCategory: " + productCategory + "\nminimumTariff: " + minimumTariff;
}

}
