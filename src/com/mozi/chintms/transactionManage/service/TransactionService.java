package com.mozi.chintms.transactionManage.service;

import java.util.Map;

import com.mozi.chintms.accountManage.model.SubAccountInfo;
import com.mozi.chintms.accountManage.model.TransferInfo;
import com.mozi.chintms.transactionManage.model.TransactionInfo;



/**
 * 账户交易
 * @author wx
 *
 */
public interface TransactionService {

    /**
     * 转账 个人账户变更
     * @return
     */
    public Map<Integer,String>  transferAccounts(TransactionInfo info);
    
    /**
     * 用户验证
     * @param info
     * @return
     */
    public Map<Integer, String> userVerificati(TransactionInfo info) ;
	/**
	 * 转出
	 * 
     * @param out
     *  :转出账号
     * @param money
     *  :转账金额
     */
    public int outMoney(TransactionInfo tInfo,SubAccountInfo sInfo);
}
