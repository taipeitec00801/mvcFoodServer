package shoppingCart.Model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*	
orderItem 欄位說明:
    orderItemNo      : 明細編號			INT				主鍵, AUTO_INCREMENT
    quantity         : 商品數量			VARCHAR(40) 	NOT NULL
    orderItemGID     : 商品編號			INT     		
    oiNo      	     : 訂單編號			INT		
*/


@Entity
@Table(name="orderItem")
public class OrderItem {
	OrderMain oiNo;
	Gift orderItemGID;
	Integer quantity;
	Integer orderItemNo;
	
	public OrderItem() {
		super();
	}
	public OrderItem(OrderMain oiNo, Gift orderItemGID, Integer quantity) {
		super();
		this.oiNo = oiNo;
		this.orderItemGID = orderItemGID;
		this.quantity = quantity;
	}
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="oiNo")
	public OrderMain getOiNo() {
		return oiNo;
	}
	public void setOiNo(OrderMain oiNo) {
		this.oiNo = oiNo;
	}
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="orderItemGID")
	public Gift getGiftId() {
		return orderItemGID;
	}

	public void setGiftId(Gift orderItemGID) {
		this.orderItemGID = orderItemGID;
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
