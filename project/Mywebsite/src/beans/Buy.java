package beans;

import java.io.Serializable;
import java.util.Date;

public class Buy implements Serializable{

	private int Id;
	private int UserId;
	private int TotalPrice;
	private int DeliveryMethodId;
	private Date CreateDate;

	private String DeliveryMethodName;
	private int DeliveryMethodPrice;




	public Buy() {
//		this.id = id;
//		this.userId = userId;
//		this.totalPrice = totalPrice;
//		this.deliveryMethodId = deliveryMethodId;
//		this.buyDate = buyDate;
//		this.deliveryMethodName = deliveryMethodName;
//		this.deliveryMethodPrice = deliveryMethodPrice;
	}

	public int getId() {
		return Id;
	}
	public void setId(int Id) {
		this.Id = Id;
	}
	public int getUserId() {
		return UserId;
	}
	public void setUserId(int UserId) {
		this.UserId = UserId;
	}
	public int getTotalPrice() {
		return TotalPrice;
	}
	public void setTotalPrice(int TotalPrice) {
		this.TotalPrice = TotalPrice;
	}

	public Date getCreateDate() {
		return CreateDate;
	}
	public void setCreateDate(Date CreateDate) {
		this.CreateDate = CreateDate;
	}

	public int getDeliveryMethodId() {
		return DeliveryMethodId;
	}

	public void setDeliveryMethodId(int DeliveryMethodId) {
		this.DeliveryMethodId = DeliveryMethodId;
	}

	public String getDeliveryMethodName() {
		return DeliveryMethodName;
	}

	public void setDeliveryMethodName(String DeliveryMethodName) {
		this.DeliveryMethodName = DeliveryMethodName;
	}

	public int getDeliveryMethodPrice() {
		return DeliveryMethodPrice;
	}

	public void setDeliveryMethodPrice(int DeliveryMethodPrice) {
		this.DeliveryMethodPrice = DeliveryMethodPrice;
	}

}
