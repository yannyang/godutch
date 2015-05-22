package yann.study.model;

import java.util.Date;

public class ModelAccountBook {
	//�˱�������ID
	private int mAccountBookID;
	//�˱�����
	private String mAccountBookName;
	//�������
	private Date mCreateDate = new Date();
	//״̬ 0ʧЧ 1����
	private int mState = 1;
	//�Ƿ�Ĭ�˱� 0��1��
	private int mIsDefault;
	/**
	 * �˱�������ID
	 */
	public int GetAccountBookID() {
		return mAccountBookID;
	}
	/**
	 * �˱�������ID
	 */
	public void SetAccountBookID(int pAccountBookID) {
		this.mAccountBookID = pAccountBookID;
	}
	/**
	 * �˱�����
	 */
	public String GetAccountBookName() {
		return mAccountBookName;
	}
	/**
	 * �˱�����
	 */
	public void SetAccountBookName(String pAccountBookName) {
		this.mAccountBookName = pAccountBookName;
	}
	/**
	 * �������
	 */
	public Date GetCreateDate() {
		return mCreateDate;
	}
	/**
	 * �������
	 */
	public void SetCreateDate(Date pCreateDate) {
		this.mCreateDate = pCreateDate;
	}
	/**
	 * ״̬ 0ʧЧ 1����
	 */
	public int GetState() {
		return mState;
	}
	/**
	 * ״̬ 0ʧЧ 1����
	 */
	public void SetState(int pState) {
		this.mState = pState;
	}
	/**
	 * �Ƿ�Ĭ�˱� 0��1��
	 */
	public int GetIsDefault() {
		return mIsDefault;
	}
	/**
	 * �Ƿ�Ĭ�˱� 0��1��
	 */
	public void SetIsDefault(int pIsDefault) {
		this.mIsDefault = pIsDefault;
	}
}
