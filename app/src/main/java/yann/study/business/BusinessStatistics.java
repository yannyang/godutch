package yann.study.business;

import android.content.Context;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import yann.study.R;
import yann.study.business.base.BaseBusiness;
import yann.study.model.ModelPayout;

public class BusinessStatistics extends BaseBusiness {
	
	private BusinessPayout m_BusinessPayout;
	private BusinessUser m_BusinessUser;
	private BusinessAccountBook m_BusinessAccountBook;
	
	public BusinessStatistics(Context p_Context)
	{
		super(p_Context);
		m_BusinessPayout = new BusinessPayout(p_Context);
		m_BusinessUser = new BusinessUser(p_Context);
		m_BusinessAccountBook = new BusinessAccountBook(p_Context);
	}
	
	public String GetPayoutUserIDByAccountBookID(int p_AccountBookID)
	{
		String _Result = "";
		List<ModelStatistics> _ListModelStatisticsTotal =  GetPayoutUserID(" And AccountBookID = " + p_AccountBookID);
		
		//���õ�����Ϣ����ת�����Է���ۿ�
		for (int i = 0; i < _ListModelStatisticsTotal.size(); i++) {
			ModelStatistics _ModelStatistics = _ListModelStatisticsTotal.get(i);
			if(_ModelStatistics.getPayoutType().equals("����")) {
				_Result += _ModelStatistics.PayerUserID + "�������� " + _ModelStatistics.Cost.toString() + "Ԫ\r\n";
			} else if(_ModelStatistics.getPayoutType().equals("����")) {
				if(_ModelStatistics.PayerUserID.equals(_ModelStatistics.ConsumerUserID)) {
					_Result += _ModelStatistics.PayerUserID + "�������� " + _ModelStatistics.Cost.toString() + "Ԫ\r\n";
				} else {
					_Result += _ModelStatistics.ConsumerUserID + "Ӧ֧����" + _ModelStatistics.PayerUserID + _ModelStatistics.Cost + "Ԫ\r\n";
				}
			}
			//_Result += _ModelStatistics.PayerUserID + "#" + _ModelStatistics.ConsumerUserID + "#" + _ModelStatistics.Cost + "(" + _ModelStatistics.getPayoutType() + ")" + "\r\n";
		}
		
		return _Result;
	}
	
	public List<ModelStatistics> GetPayoutUserID(String p_Condition)
	{
		//�õ���ֺõ�ͳ����Ϣ
		List<ModelStatistics> _ListModelStatistics = GetModelStatisticsList(p_Condition);
		//��Ű������˷������ʱͳ����Ϣ
		List<ModelStatistics> _ListModelStatisticsTemp = new ArrayList<ModelStatistics>();
		//���ͳ�ƺõĻ���
		List<ModelStatistics> _ListModelStatisticsTotal = new ArrayList<ModelStatistics>();
		String _Result = "";
		//������ֺõ�ͳ����Ϣ
		for (int i = 0; i < _ListModelStatistics.size(); i++) {
			//�õ���ֺõ�һ����Ϣ
			ModelStatistics _ModelStatistics = _ListModelStatistics.get(i);
			_Result += _ModelStatistics.PayerUserID + "#" + _ModelStatistics.ConsumerUserID + "#" + _ModelStatistics.Cost + "\r\n";
			//���浱ǰ�ĸ�����ID
			String _CurrentPayerUserID = _ModelStatistics.PayerUserID;
			
			//�ѵ�ǰ��Ϣ�������˷������ʱ����
			ModelStatistics _ModelStatisticsTemp = new ModelStatistics();
			_ModelStatisticsTemp.PayerUserID = _ModelStatistics.PayerUserID;
			_ModelStatisticsTemp.ConsumerUserID = _ModelStatistics.ConsumerUserID;
			_ModelStatisticsTemp.Cost = _ModelStatistics.Cost;
			_ModelStatisticsTemp.setPayoutType(_ModelStatistics.getPayoutType());
			_ListModelStatisticsTemp.add(_ModelStatisticsTemp);
			
			//������һ�е�����
			int _NextIndex;
			//�����һ������С��ͳ����Ϣ����������Լ�1
			if((i+1) < _ListModelStatistics.size())
			{
				_NextIndex = i+1;
			}
			else {
				//����֤���Ѿ���β����������ֵΪ��ǰ��
				_NextIndex = i;
			}
			
			//�����ǰ����������һλ�����˲�ͬ����֤������ͳ���Ѿ���β�������Ѿ�ѭ����ͳ���������һλ����Ϳ�ʼ�������ͳ��
			if (!_CurrentPayerUserID.equals(_ListModelStatistics.get(_NextIndex).PayerUserID) || _NextIndex == i) {
				
				//��ʼ���б������е�ǰ����ͳ�������ͳ��
				for (int j = 0; j < _ListModelStatisticsTemp.size(); j++) {
					//ȡ����һ��
					ModelStatistics _ModelStatisticsTotal = _ListModelStatisticsTemp.get(j);
					//�ж�����ͳ�����鵱���Ƿ��Ѿ����ڸø����˺������˵���Ϣ
					int _Index = GetPostionByConsumerUserID(_ListModelStatisticsTotal,_ModelStatisticsTotal.PayerUserID, _ModelStatisticsTotal.ConsumerUserID);
					//����Ѿ����ڣ���ʼ��ԭ���������Ͻ����ۼ�
					if(_Index != -1)
					{
						_ListModelStatisticsTotal.get(_Index).Cost = _ListModelStatisticsTotal.get(_Index).Cost.add(_ModelStatisticsTotal.Cost);
					}
					else {
						//�������һ������Ϣ����ӵ�ͳ�����鵱��
						_ListModelStatisticsTotal.add(_ModelStatisticsTotal);
					}
				}
				//ȫ�����������յ�ǰ����ͳ�����飬������һ������ͳ������ļ��㣬ֱ��β
				_ListModelStatisticsTemp.clear();
			}
			
		}
		
		return _ListModelStatisticsTotal;
	}
	
	private int GetPostionByConsumerUserID(List<ModelStatistics> p_ListModelStatisticsTotal,String p_PayerUserID,String p_ConsumerUserID)
	{
		int _Index = -1;
		for (int i = 0; i < p_ListModelStatisticsTotal.size(); i++) {
			if (p_ListModelStatisticsTotal.get(i).PayerUserID.equals(p_PayerUserID) && p_ListModelStatisticsTotal.get(i).ConsumerUserID.equals(p_ConsumerUserID)) {
				_Index = i;
			}
		}
		
		return _Index;
	}

	private List<ModelStatistics> GetModelStatisticsList(String p_Condition) {
		//��֧����ID����ȡ�����Ѽ�¼
		List<ModelPayout> _ListPayout = m_BusinessPayout.GetPayoutOrderByPayoutUserID(p_Condition);
		
		//��ȡ���㷽ʽ����
		String _PayoutTypeArr[] = GetContext().getResources().getStringArray(R.array.PayoutType);
		
		List<ModelStatistics> _ListModelStatistics = new ArrayList<ModelStatistics>();
		
		if(_ListPayout != null)
		{
			//�������Ѽ�¼�б�
			for (int i = 0; i < _ListPayout.size(); i++) {
				//ȡ��һ�����Ѽ�¼
				ModelPayout _ModelPayout = _ListPayout.get(i);
				//��������IDת��Ϊ��ʵ����
				String _PayoutUserName[] = m_BusinessUser.getUserNameByUserID(_ModelPayout.GetPayoutUserID()).split(",");
				String _PayoutUserID[] = _ModelPayout.GetPayoutUserID().split(",");
				
				//ȡ�����㷽ʽ
				String _PayoutType = _ModelPayout.GetPayoutType();
				
				//��ż��������ѽ��
				BigDecimal _Cost;
				
				//�жϱ������Ѽ�¼���������ͣ�����Ǿ��֣�����Ա��������˵ĸ��������ƽ�����ѽ��
				if(_PayoutType.equals(_PayoutTypeArr[0]))
				{
					//�õ���������
					int _PayoutTotal = _PayoutUserName.length;
					
					/*��������������BigDecimal 
					ͨ��BigDecimal��divide�������г���ʱ������������������ѭ��С��ʱ���ͻ����쳣�ģ��쳣���£�java.lang.ArithmeticException: Non-terminating decimal expansion; no exact representable decimal result. at java.math.BigDecimal.divide(Unknown Source) 

					Ӧ�ó�����һ���й��ͻ��ĵ�����1000Ԫ/�꣬������¼���Ļ�1000/12=83.3333333333.... 

					���֮�������Ǹ�divide���þ�ȷ��С����divide(xxxxx,2, BigDecimal.ROUND_HALF_EVEN) */
					//�õ�������ƽ�����ѽ��
					_Cost = _ModelPayout.GetAmount().divide(new BigDecimal(_PayoutTotal),2, BigDecimal.ROUND_HALF_EVEN);
				}
				//����ǽ�����Ǹ������ѣ���ֱ��ȡ�����ѽ��
				else {
					_Cost = _ModelPayout.GetAmount();
				}
				
				//��������������
				for (int j = 0; j < _PayoutUserID.length; j++) {
					
					//����ǽ����������һ����������Ϊ��һ�����ǽ�����Լ�
					if (_PayoutType.equals(_PayoutTypeArr[1]) && j == 0) {
						continue;
					}
					
					//����һ��ͳ����
					ModelStatistics _ModelStatistics = new ModelStatistics();
					//��ͳ�����֧��������Ϊ����������ĵ�һ����
					_ModelStatistics.PayerUserID = _PayoutUserName[0];
					//����������
					_ModelStatistics.ConsumerUserID = _PayoutUserName[j];
					//������������
					_ModelStatistics.setPayoutType(_PayoutType);
					//������õ����ѽ��
					_ModelStatistics.Cost = _Cost;
					
					_ListModelStatistics.add(_ModelStatistics);
				}
			}
		}
		
		return _ListModelStatistics;
	}
	
	public String ExportStatistics(int p_AccountBookID) throws Exception {
		String _Result = "";
		String _AccountBookName = m_BusinessAccountBook.GetAccountBookNameByAccountId(p_AccountBookID);
		Date _Date = new Date();
//		String _FileName = _AccountBookName + String.valueOf(_Date.getYear()) + String.valueOf(_Date.getMonth()) + String.valueOf(_Date.getDay()) + ".xls";
		String _FileName = String.valueOf(_Date.getYear()) + String.valueOf(_Date.getMonth()) + String.valueOf(_Date.getDay()) + ".xls";
		File _FileDir = new File("/sdcard/GoDutch/Export/");
		if (!_FileDir.exists()) {
			_FileDir.mkdirs();
		}
		File _File = new File("/sdcard/GoDutch/Export/" + _FileName);
		if(!_File.exists()) {
			_File.createNewFile();
		}
		
		WritableWorkbook wBookData;
		//����������
		wBookData = Workbook.createWorkbook(_File);
		//����������
		WritableSheet wsAccountBook = wBookData.createSheet(_AccountBookName, 0);
		
		String[] _Titles = {"���", "����", "���", "������Ϣ", "��������"};
		Label _Label;
		//��ӱ�����
		for (int i = 0; i < _Titles.length; i++) {
			_Label = new Label(i, 0, _Titles[i]);
			wsAccountBook.addCell(_Label);
		}
		
		/*
		 * �����
		 */
		List<ModelStatistics> _ListModelStatisticsTotal =  GetPayoutUserID(" And AccountBookID = " + p_AccountBookID);
		
		for(int i = 0;i < _ListModelStatisticsTotal.size(); i++) {
			ModelStatistics _ModelStatistics = _ListModelStatisticsTotal.get(i);
			
			//��ӱ����
			Number _IdCell = new Number(0, i+1, i+1);
			wsAccountBook.addCell(_IdCell);
			
			//�������
			Label _lblName = new Label(1, i+1, _ModelStatistics.PayerUserID);
			wsAccountBook.addCell(_lblName);
			
			//��ʽ�����������ʾ
			NumberFormat nfMoney = new NumberFormat("#.##");
			WritableCellFormat wcfFormat = new WritableCellFormat(nfMoney);
			
			//��ӽ��
			Number _CostCell = new Number(2, i+1, _ModelStatistics.Cost.doubleValue(), wcfFormat);
			wsAccountBook.addCell(_CostCell);
			
			//���������Ϣ
			String _Info = "";
			if(_ModelStatistics.getPayoutType().equals("����")) {
				_Info = _ModelStatistics.PayerUserID + "�������� " + _ModelStatistics.Cost.toString() + "Ԫ";
			} else if(_ModelStatistics.getPayoutType().equals("����")) {
				if(_ModelStatistics.PayerUserID.equals(_ModelStatistics.ConsumerUserID)) {
					_Info = _ModelStatistics.PayerUserID + "�������� " + _ModelStatistics.Cost.toString() + "Ԫ";
				} else {
					_Info = _ModelStatistics.ConsumerUserID + "Ӧ֧����" + _ModelStatistics.PayerUserID + _ModelStatistics.Cost + "Ԫ";
				}
			} 
			Label _lblInfo = new Label(3, i+1, _Info);
			wsAccountBook.addCell(_lblInfo);
			
			//�����������
			Label _lblPayoutType = new Label(4, i+1, _ModelStatistics.getPayoutType());
			wsAccountBook.addCell(_lblPayoutType);
		}
		
		wBookData.write();
		wBookData.close();
		_Result = "�����Ѿ�������λ���ڣ�/sdcard/GoDutch/Export/" + _FileName;
		return _Result;
	}
	
	public class ModelStatistics
	{
		public String PayerUserID;
		public String ConsumerUserID;
		private String m_PayoutType;
		public BigDecimal Cost;
		
		public String getPayoutType() {
			return m_PayoutType;
		}
		
		public void setPayoutType(String p_Value) {
			m_PayoutType = p_Value;
		}
	}
}
