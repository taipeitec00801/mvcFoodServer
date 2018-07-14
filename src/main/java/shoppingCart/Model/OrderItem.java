package shoppingCart.Model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class OrderItem {
	Order orderNo;
	Gift giftId;
	Integer quantity;
	Integer orderItemNo;
	
	public OrderItem() {
		super();
	}

	public OrderItem(Order orderNo, Gift giftId, Integer quantity) {
		super();
		this.orderNo = orderNo;
		this.giftId = giftId;
		this.quantity = quantity;
	}
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="orderNo")
	public Order getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Order orderNo) {
		this.orderNo = orderNo;
	}
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="giftId")
	public Gift getGiftId() {
		return giftId;
	}

	public void setGiftId(Gift giftId) {
		this.giftId = giftId;
	}
	
	@Column(columnDefinition = "VARCHAR(40) NOT NULL")
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getOrderItemNo() {
		return orderItemNo;
	}

	public void setOrderItemNo(Integer orderItemNo) {
		this.orderItemNo = orderItemNo;
	}
	
	
	
}
