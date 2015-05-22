package yann.study.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import android.R.integer;

public class ModelPayout implements Serializable {
	//֧��������ID
	private int m_PayoutID;
	//�˱�ID���
	private int m_AccountBookID;
	//�˱�����
	private String m_AccountBookName;
	//֧�����ID���
	private int m_CategoryID;
	//�������
	private String m_CategoryName;
	//·��
	private String m_Path;
	//���ʽID���
	private int m_PayWayID;
	//���ѵص�ID���
	private int m_PlaceID;
	//���ѽ��
	private BigDecimal m_Amount;
	//��������
	private Date m_PayoutDate;
	//���㷽ʽ
	private String m_PayoutType;
	//������ID���
	private String m_PayoutUserID;
	//��ע
	private String m_Comment;
	//�������
	private Date m_CreateDate = new Date();
	//״̬ 0ʧЧ 1����
	private int m_State = 1;
	/**
	 * ֧��������ID
	 */
	public int GetPayoutID() {
		return m_PayoutID;
	}
	/**
	 * ֧��������ID
	 */
	public void SetPayoutID(int p_PayoutID) {
		this.m_PayoutID = p_PayoutID;
	}
	/**
	 * �˱�����ID���
	 */
	public int GetAccountBookID() {
		return m_AccountBookID;
	}
	/**
	 * �˱�ID���
	 */
	public void SetAccountBookID(int p_AccountBookID) {
		this.m_AccountBookID = p_AccountBookID;
	}
	/**
	 * �˱�����
	 */
	public String GetAccountBookName() {
		return m_AccountBookName;
	}
	/**
	 * �˱�����
	 */
	public void SetAccountBookName(String p_AccountBookName) {
		this.m_AccountBookName = p_AccountBookName;
	}
	/**
	 * ֧�����ID���
	 */
	public int GetCategoryID() {
		return m_CategoryID;
	}
	/**
	 * ֧�����ID���
	 */
	public void SetCategoryID(int p_CategoryID) {
		this.m_CategoryID = p_CategoryID;
	}
	/**
	 * ·��
	 */
	public String GetPath() {
		return m_Path;
	}
	/**
	 * ·��
	 */
	public void SetPath(String p_Path) {
		this.m_Path = p_Path;
	}
	/**
	 * �˱�����
	 */
	public String GetCategoryName() {
		return m_CategoryName;
	}
	/**
	 * �˱�����
	 */
	public void SetCategoryName(String p_CategoryName) {
		this.m_CategoryName = p_CategoryName;
	}
	/**
	 * 	���ʽID���
	 */
	public int GetPayWayID() {
		return m_PayWayID;
	}
	/**
	 * 	���ʽID���
	 */
	public void SetPayWayID(int p_PayWayID) {
		this.m_PayWayID = p_PayWayID;
	}
	/**
	 * ���ѵص�ID���
	 */
	public int GetPlaceID() {
		return m_PlaceID;
	}
	/**
	 * ���ѵص�ID���
	 */
	public void SetPlaceID(int p_PlaceID) {
		this.m_PlaceID = p_PlaceID;
	}
	/**
	 * ���ѽ��
	 */
	public BigDecimal GetAmount() {
		return m_Amount;
	}
	/**
	 * ���ѽ��
	 */
	public void SetAmount(BigDecimal p_Amount) {
		this.m_Amount = p_Amount;
	}
	/**
	 * ��������
	 */
	public Date GetPayoutDate() {
		return m_PayoutDate;
	}
	/**
	 * ��������
	 */
	public void SetPayoutDate(Date p_PayoutDate) {
		this.m_PayoutDate = p_PayoutDate;
	}
	/**
	 * ���㷽ʽ
	 */
	public String GetPayoutType() {
		return m_PayoutType;
	}
	/**
	 * ���㷽ʽ
	 */
	public void SetPayoutType(String p_PayoutType) {
		this.m_PayoutType = p_PayoutType;
	}
	/**
	 * ������ID���
	 */
	public String GetPayoutUserID() {
		return m_PayoutUserID;
	}
	/**
	 * ������ID���
	 */
	public void SetPayoutUserID(String p_PayoutUserID) {
		this.m_PayoutUserID = p_PayoutUserID;
	}	
	/**
	 * ��ע
	 */
	public String GetComment() {
		return m_Comment;
	}
	/**
	 * ��ע
	 */
	public void SetComment(String p_Comment) {
		this.m_Comment = p_Comment;
	}
	/**
	 * �������
	 */
	public Date GetCreateDate() {
		return m_CreateDate;
	}
	/**
	 * �������
	 */
	public void SetCreateDate(Date p_CreateDate) {
		this.m_CreateDate = p_CreateDate;
	}
	/**
	 * ״̬ 0ʧЧ 1����
	 */
	public int GetState() {
		return m_State;
	}
	/**
	 * ״̬ 0ʧЧ 1����
	 */
	public void SetState(int p_State) {
		this.m_State = p_State;
	}
}
