
public class TradeRequest {
private String requestID;
private String originCountry;
private String destinationCountry;
private String productCategory;
private double tradeValue;
private double proposedTariff;

public TradeRequest(String requestID, String originCountry, String destinationCountry, String productCategory,
		double tradeValue, double proposedTariff) {
	this.requestID = requestID;
	this.originCountry = originCountry;
	this.destinationCountry = destinationCountry;
	this.productCategory = productCategory;
	this.tradeValue = tradeValue;
	this.proposedTariff = proposedTariff;
}
public TradeRequest() {
	this.requestID = "";
	this.originCountry = "";
	this.destinationCountry = "";
	this.productCategory = "";
	this.tradeValue = 0.0;
	this.proposedTariff = 0.0;
}
public String getRequestID() {
	return requestID;
}
public void setRequestID(String requestID) {
	this.requestID = requestID;
}
public String getOriginCountry() {
	return originCountry;
}
public void setOriginCountry(String originCountry) {
	this.originCountry = originCountry;
}
public String getDestinationCountry() {
	return destinationCountry;
}
public void setDestinationCountry(String destinationCountry) {
	this.destinationCountry = destinationCountry;
}
public String getProductCategory() {
	return productCategory;
}
public void setProductCategory(String productCategory) {
	this.productCategory = productCategory;
}
public double getTradeValue() {
	return tradeValue;
}
public void setTradeValue(double tradeValue) {
	this.tradeValue = tradeValue;
}
public double getProposedTariff() {
	return proposedTariff;
}
public void setProposedTariff(double proposedTariff) {
	this.proposedTariff = proposedTariff;
}
@Override
public String toString() {
	return "TradeRequest: \nrequestID: " + requestID + ", originCountry: " + originCountry + ", destinationCountry: "
			+ destinationCountry + ", productCategory: " + productCategory + ", tradeValue: " + tradeValue
			+ ", proposedTariff: " + proposedTariff;
}

}
