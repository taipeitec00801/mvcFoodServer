package other.Model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import member.Model.Member;

/*	
StoreRecommend 欄位說明:
    stRecomId      : 推薦編號			INT			主鍵, AUTO_INCREMENT
    stRecomMId     : 會員編號			INT 	
    stRecomSId     : 店家編號			INT     		
    stRecomYN      : 有無推薦			TINYINT		NOT NULL, 預設 0
*/

@Entity
public class StoreRecommend {
	private Integer stRecomId;
	private Member stRecomMId;
	private Store stRecomSId;
	private Integer stRecomYN;
	
	
	public StoreRecommend(Member stRecomMId, Store stRecomSId) {
		super();
		this.stRecomMId = stRecomMId;
		this.stRecomSId = stRecomSId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getStRecomId() {
		return stRecomId;
	}

	public void setStRecomId(Integer stRecomId) {
		this.stRecomId = stRecomId;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "stRecomMId")
	public Member getStRecomMId() {
		return stRecomMId;
	}

	public void setStRecomMId(Member stRecomMId) {
		this.stRecomMId = stRecomMId;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "stRecomSId")
	public Store getStRecomSId() {
		return stRecomSId;
	}

	public void setStRecomSId(Store stRecomSId) {
		this.stRecomSId = stRecomSId;
	}

	@Column(columnDefinition = "TINYINT DEFAULT '0'")
	public Integer getStRecomYN() {
		return stRecomYN;
	}

	public void setStRecomYN(Integer stRecomYN) {
		this.stRecomYN = stRecomYN;
	}

}
