package wattaina.bulletin_board.beans;

import java.io.Serializable;
import java.util.Date;

public class  Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String text;
	private int branchId;
	private int positionId;
	private Date createdAt;
	private int userId;

	public int getId() {
		return id;
	}

	public void setLogin_id(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public int getPositionId() {
		return positionId;
	}

	public void setPosionId(int posionId) {
		this.positionId = posionId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
