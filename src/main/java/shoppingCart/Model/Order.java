package shoppingCart.Model;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import member.Model.Member;

@Entity
public class Order {
	
	Integer orderNo;
	Member	memberId;
	Date  orderDate;
	
	public Order() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="memberId")
	public Member getMemberId() {
		return memberId;
	}

	public void setMemberId(Member memberId) {
		this.memberId = memberId;
	}

	@Column(columnDefinition = "DATE")
	public Date getOrderDate() {
		return orderDate;
	}
	
	
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	
	
}
