import java.util.Objects;
import java.util.NoSuchElementException;
public class TariffList implements TariffPolicy, Cloneable {
private double currentTradeValue;
private int lastFindIterations = 0;


public TariffList() {
	this.currentTradeValue = 0.0;
	this.head = null;
	this.size = 0;
}
	public double getCurrentTradeValue() {
	return currentTradeValue;
}

public void setCurrentTradeValue(double currentTradeValue) {
	this.currentTradeValue = currentTradeValue;
}
public int getLastFindIterations() {
    return lastFindIterations;
}


	public String evaluateTrade(double proposedTariff, double minimumTariff) {
	if (proposedTariff >= minimumTariff) {
		return "Trade Request Accepted";
	}
	else if (proposedTariff >= minimumTariff * 0.8) {
		double surcharge = currentTradeValue * ((minimumTariff - proposedTariff)/100.0);
		return "Trade Request Conditionally Accepted: Surcharge = " + String.format("%.2f", surcharge);
	}
	else 
		return "Trade Request Rejected";
	}

	public class TariffNode{
		private Tariff tariff;
		private TariffNode next;
		
		public TariffNode() {
			this.tariff = null;
			this.next = null;
		}

		public TariffNode(Tariff tariff) {
			this.tariff = tariff;
			this.next = null;
		}
		public TariffNode(TariffNode other) {
			this.tariff = other.tariff.clone();
			if (other.next != null) {
				this.next = new TariffNode(other.next);
			} else {
				this.next = null;
			}
		}
		public TariffNode clone() {
			try{
				return (TariffNode) super.clone();
			} catch (CloneNotSupportedException e) {
				return null;
			}
		}

		public Tariff getTariff() {
			return tariff;
		}
		
		public TariffNode getNext() {
			return next;
		}
		public void setTariff(Tariff tariff) {
			this.tariff = tariff;
		}
		public void setNext(TariffNode next) {
			this.next = next;
		}
		public boolean equals(Object obj) {
			if (this == obj) return true;
			if (obj == null || getClass() != obj.getClass()) return false;
			TariffNode that = (TariffNode) obj;
			return Objects.equals(tariff, that.tariff) && Objects.equals(next, that.next);
		}
	}
	private TariffNode head;
	private int size;
	public TariffList(TariffList other){
		this.head = null;
		this.size = 0;
		if (other.head != null) {
			this.head = new TariffNode(other.head);
			TariffNode thisCurrent = this.head;
			TariffNode otherCurrent = other.head.next;
			while (otherCurrent != null) {
				thisCurrent.next = new TariffNode(otherCurrent);
				thisCurrent = thisCurrent.next;
				otherCurrent = otherCurrent.next;
			}
		}
		this.size = other.size;
	}
	public void addToStart(Tariff tariff){
		TariffNode newNode = new TariffNode(tariff);
		newNode.next = this.head; 
		this.head = newNode;
		this.size++;
	}
	public void insertAtIndex(Tariff tariff, int index){
		if( index < 0 || index > size) {
			throw new NoSuchElementException();
		}
		TariffNode newNode = new TariffNode(tariff);
		this.size++;
		if(index ==0){
			newNode.next = head;
			this.head = newNode;
		}
		else{
			TariffNode current = head;
			for(int i=0; i<index-1; i++){
				current = current.next;
			}
			newNode.next = current.next;
			current.next = newNode;
		}
	}
	public void removeAtIndex(int index){
		if(index < 0 || index >= size) {
			throw new NoSuchElementException();
		}
		this.size--;
		if(index == 0){
			head = head.next;
		}
		else{
			TariffNode current = head;
			for(int i=0; i<index-1; i++){
				current = current.next;
			}
			current.next = current.next.next;
		}
	}
	public void deleteFromStart(){
		if(head == null) {
			throw new NoSuchElementException();
		}
		this.size--;
		head = head.next;
	}
	public void replaceAtIndex(Tariff tariff, int index){
		if(index < 0 || index >= size) {
			throw new NoSuchElementException();
		}
		TariffNode tariffNode = new TariffNode(tariff);
		if(index ==0){
			tariffNode.next = head.next;
			head = tariffNode;
		}
		else{
			TariffNode current = head;
			for(int i = 0; i<index-1; i++){
				current = current.next;
			}
			tariffNode.next = current.next.next;
			current.next = tariffNode;
		}
		}
		public TariffNode find(String destination, String origin, String category)
		{
			Tariff tariff = new Tariff(destination, origin, category, 0.0);
			TariffNode current = head;
			lastFindIterations = 0;
			while (current != null) {
				 lastFindIterations++;
				if (current.tariff.equals(tariff)) {
					return current;
				}
				current = current.next;
			}
			return null;
		}
		public boolean contains(String destination, String origin, String category){
			Tariff tariff = new Tariff(destination, origin, category, 0.0);
			TariffNode current = head;
			while(current!=null){
				if(current.tariff.equals(tariff)){
					return true;
				}
				current = current.next;
			}
			return false;
		}
		public boolean equals(TariffList other){
			if(this.size != other.size) return false;
			TariffNode current1 = this.head;
			TariffNode current2 = other.head;
			while(current1 != null && current2 != null){
				if(!current1.tariff.equals(current2.tariff)) return false;
				current1 = current1.next;
				current2 = current2.next;
			}
			return true;
		}
		public TariffNode getHead() {
			return head;
		}
		public void setHead(TariffNode head) {
			this.head = head;
		}
		public int getSize() {
			return size;
		}
		public void setSize(int size) {
			this.size = size;
		}
		
	}

