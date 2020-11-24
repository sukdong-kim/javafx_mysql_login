package application;

public class users {
	int seatno, price;
	String name, date;
	public users(String name,int seatno, int price, String date) {
		super();
		this.seatno = seatno;
		this.price = price;
		this.name = name;
		this.date = date;
	}
	public int getSeatno() {
		return seatno;
	}
	public void setSeatno(int seatno) {
		this.seatno = seatno;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
	
}