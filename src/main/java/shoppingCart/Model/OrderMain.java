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
import javax.persistence.Table;

import member.Model.Member;

/*	
orderMain 欄位說明:
    orderNo      	 : 訂單編號			INT				主鍵, AUTO_INCREMENT
    orderMID         : 會員編號			INT				外來鍵
    orderDate        : 訂單日期			Date     		
    commit      	 : 交易狀態			VARCHAR(20)		
*/

@Entity
@Table(name="orderMain")
public class OrderMain {
	
	Integer orderNo;
	Member	orderMID;
	Date  orderDate;
	Integer commit;
	
	public OrderMain() {
		super();
	}
	
	public OrderMain(Member orderMID, Date orderDate, Integer commit) {
		super();
		this.orderMID = orderMID;
		this.orderDate = orderDate;
		this.commit = commit;
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
	@JoinColumn(name="orderMID")
	public Member getMemberId() {
		return orderMID;
	}

	public void setMemberId(Member orderMID) {
		this.orderMID = orderMID;
	}

	@Column(columnDefinition = "DATE")
	public Date getOrderDate() {
		return orderDate;
	}
	
	
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}


	@Column(columnDefinition = "VARCHAR(20)")
	public Integer isCommit() {
		return commit;
	}



	public void setCommit(Integer commit) {
		this.commit = commit;
	}
	
	
	
}
