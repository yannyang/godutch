package yann.study.model;

import java.io.Serializable;
import java.util.Date;

public class ModelCategory implements Serializable {
	//��������ID
	private int m_CategoryID;
	//�������
	private String m_CategoryName;
	//���ͱ������
	private String m_TypeFlag;
	//������ID
	private int m_ParentID = 0;
	//·��
	private String m_Path;	
	//�������
	private Date m_CreateDate = new Date();
	//״̬ 0ʧЧ 1����
	private int m_State = 1;
	/**
	 * �˱�������ID
	 */
	public int GetCategoryID() {
		return m_CategoryID;
	}
	/**
	 * �˱�������ID
	 */
	public void SetCategoryID(int p_CategoryID) {
		this.m_CategoryID = p_CategoryID;
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
	 * ���ͱ������
	 */
	public String GetTypeFlag() {
		return m_TypeFlag;
	}
	/**
	 * ���ͱ������
	 */
	public void SetTypeFlag(String p_TypeFlag) {
		this.m_TypeFlag = p_TypeFlag;
	}
	/**
	 * ������ID
	 */
	public int GetParentID() {
		return m_ParentID;
	}
	/**
	 * ������ID
	 */
	public void SetParentID(int p_ParentID) {
		this.m_ParentID = p_ParentID;
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
	
	@Override
	public String toString() {
		return m_CategoryName;
	}
}
