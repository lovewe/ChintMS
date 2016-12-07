package com.mozi.chintms.transactionManage.dao;

import java.util.List;

import com.mozi.chintms.accountManage.model.SubAccountInfo;
import com.mozi.chintms.accountManage.model.SubAccountSeqInfo;
import com.mozi.chintms.accountManage.model.TransferInfo;
import com.mozi.chintms.safeManage.model.SafeUserInfo;

/**
 * 个人账户交易
 * @author wx
 *
 */
public interface TransactionDao {
	
    /**
     * 获取用户账户余额
     * @return
     */
    public SubAccountInfo  getAmountByID(String userId);
	/**
	 * 转账
	 * 个人账户变更
     * @param 转账信息 
     */
	public int insertSubAccountSeq(SubAccountSeqInfo info);
    /**
     * 更新个人账户信息
     * @param info
     * @return
     */
    public int  updateAccounts(SubAccountSeqInfo info);
}
