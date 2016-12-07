package com.mozi.chintms.accountManage.dao;

import java.util.List;

import com.mozi.chintms.accountManage.model.QRCodeInfo;
import com.mozi.chintms.accountManage.model.SubAccountInfo;
import com.mozi.chintms.accountManage.model.SubAccountSeqForListInfo;
import com.mozi.chintms.accountManage.model.SubAccountSeqInfo;
import com.mozi.chintms.accountManage.model.TransferBillInfo;
import com.mozi.chintms.accountManage.model.TransferInfo;
import com.mozi.chintms.accountManage.model.UserInfoByPhoneInfo;

public interface AccountDao {
	/**
	 * 转账
	 * 
     * @param 转账信息 
     */
	public int insertSubAccountSeq(SubAccountSeqInfo info);
	
	/**
	 * 记录转账交易流水
	 * @param info
	 * @return
	 */
	public int insertTransferBILL(TransferInfo info);
    
    /**
     * 获取用户账户余额
     * @return
     */
    public SubAccountInfo  getAmountByID(String userId);
    
    /**
     * 转账操作
     * 个人用户余额变更
     * @param info
     * @return
     */
    public int transferAccounts(SubAccountInfo info);
    
    /**
     * 流水记录
     * @param info
     * @return
     */
    public List<SubAccountSeqForListInfo> getSubAccountSeqByUser(SubAccountSeqForListInfo info);
    
    /**
     * 根据用户ID获取概要信息
     * @param personID
     * @return
     */
    public UserInfoByPhoneInfo getUserInfoByPhone(String phoneNum);
    
    /**
     * 根据qrcode获取用户概要信息
     * @param personID
     * @return
     */
    public UserInfoByPhoneInfo getUserInfoByQRCode(String qrCode);
    
    /**
     * 更新个人账户信息
     * @param info
     * @return
     */
    public int  updateAccounts(SubAccountSeqInfo info);
    
    /**
     * 用户交易流水
     * @param userID
     * @return
     */
    public List<TransferBillInfo> selectTransferBILL(String userID);
    /**
     * 生成校验码
     * @param info
     * @return
     */
    public int generateQRCode(QRCodeInfo info);
    /**
     * 获取校验码
     * @param personID
     * @return
     */
    public QRCodeInfo getQRCode(QRCodeInfo info);
    /**
     * 更新校验码
     * @param info
     * @return
     */
    public int updateQRCode(QRCodeInfo info);
    /**
     * 更新QRCode 使用状态
     * @param info
     * @return
     */
    public int updateQRCodeStatus(QRCodeInfo info);
}
