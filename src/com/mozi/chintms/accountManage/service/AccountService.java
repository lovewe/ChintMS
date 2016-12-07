package com.mozi.chintms.accountManage.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.mozi.chintms.accountManage.model.QRCodeInfo;
import com.mozi.chintms.accountManage.model.SubAccountInfo;
import com.mozi.chintms.accountManage.model.SubAccountSeqForListInfo;
import com.mozi.chintms.accountManage.model.SubAccountSeqInfo;
import com.mozi.chintms.accountManage.model.TransferBillInfo;
import com.mozi.chintms.accountManage.model.TransferInfo;
import com.mozi.chintms.accountManage.model.UserInfoByPhoneInfo;

/**
 * 转账
 * @author wx
 *
 */
public interface AccountService {

	/**
	 * 转出
	 * 
     * @param out
     *  :转出账号
     * @param money
     *  :转账金额
     */
    public int outMoney(TransferInfo tInfo,SubAccountInfo sInfo);
 
    /**
     * 转入
     * 
     * @param in
     * :转入账号
     * @param money
     * :转账金额
     */
    public int inMoney(TransferInfo info,SubAccountInfo sInfo);
    
    /**
     * 转账 个人账户变更
     * @return
     */
    public Map<Integer,String>  transferAccounts(TransferInfo info);
    
    /**
     * 获取用户账户余额
     * @return
     */
    public double  getAmountByID(String userId);
    /**
     * 流水记录
     * @param info
     * @return
     */
    public Map<String, List<SubAccountSeqForListInfo>> getSubAccountSeqByUser(SubAccountSeqForListInfo info);
    
    /**
     * 根据用户ID获取概要信息
     * @param personID
     * @return
     */
    public UserInfoByPhoneInfo getUserInfoByPhone(String personID);
    
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
    public int  updateAccounts(SubAccountInfo info);
    /**
     * 用户验证
     * @param info
     * @return
     */
    public Map<Integer, String> userVerificati(TransferInfo info) ;
    
    /**
     * 添加缓存
     * @param 
     */
    public void SetCache(QRCodeInfo info);
    /**
     * 获取缓存
     * @param info
     * @return
     */
    public Object GetCache(QRCodeInfo info);
    /**
     * 移除缓存
     * @param info
     * @return
     */
    public void RemoveCache(QRCodeInfo info);
    /**
     * 用户交易流水
     * @param userID
     * @return
     */
    public List<TransferBillInfo> selectTransferBILL(String userID);
    
    /**
     * 生成长期校验码
     * @param info
     * @return
     */
    public String generateQRCode(QRCodeInfo info);
    /**
     * 获取长期校验码
     * @param personID
     * @return
     */
    public QRCodeInfo getQRCode(QRCodeInfo info);
    /**
     * 更新QRCode 使用状态
     * @param info
     * @return
     */
    public int updateQRCodeStatus(QRCodeInfo info);
}
