package inzent.pjt.vo;

public class CardVo {
	String card_number;
	String expiry;
	String birth;
	String pwd_2digit;
	String customer_uid;
	
	public String getCard_number() {
		return card_number;
	}
	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}
	public String getExpiry() {
		return expiry;
	}
	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getPwd_2digit() {
		return pwd_2digit;
	}
	public void setPwd_2digit(String pwd_2digit) {
		this.pwd_2digit = pwd_2digit;
	}
	public String getCustomer_uid() {
		return customer_uid;
	}
	public void setCustomer_uid(String customer_uid) {
		this.customer_uid = customer_uid;
	}
	
	@Override
	public String toString() {
		return "CardVo [card_number=" + card_number + ", expiry=" + expiry + ", birth=" + birth + ", pwd_2digit="
				+ pwd_2digit + ", customer_uid=" + customer_uid + ", getCard_number()=" + getCard_number()
				+ ", getExpiry()=" + getExpiry() + ", getBirth()=" + getBirth() + ", getPwd_2digit()=" + getPwd_2digit()
				+ ", getCustomer_uid()=" + getCustomer_uid() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
	
}
