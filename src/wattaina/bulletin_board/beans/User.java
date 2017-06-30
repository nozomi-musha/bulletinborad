package wattaina.bulletin_board.beans;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private int loginId;
	private String password;
	private String name;
	private int branchId;
	private int positionId;
	private int isStopped;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public int getLoginId() {
		return loginId;
	}

	public void setLogin_id(int loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranch_id(int branchId) {
		this.branchId = branchId;
	}
	public int getPositionId() {
		return positionId;
	}

	public void setPositionId(int positonId) {
		this.positionId = positonId;
	}

	public int getIsStopped() {
		return isStopped;
	}

	public void setIsStopped(int isStopped) {
		this.isStopped = isStopped;
	}
}