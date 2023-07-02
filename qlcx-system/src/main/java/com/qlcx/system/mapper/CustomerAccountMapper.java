package com.qlcx.system.mapper;

import java.util.List;
import com.qlcx.system.domain.CustomerAccount;

/**
 * Customer accountMapper接口
 * 
 * @author qlcx
 * @date 2020-09-18
 */
public interface CustomerAccountMapper 
{
    /**
     * 查询Customer account
     * 
     * @param id Customer accountID
     * @return Customer account
     */
    public CustomerAccount selectCustomerAccountById(Long id);

    /**
     * 查询Customer accountDanh sách
     * 
     * @param customerAccount Customer account
     * @return Customer account集合
     */
    public List<CustomerAccount> selectCustomerAccountList(CustomerAccount customerAccount);

    /**
     * Thêm vàoCustomer account
     * 
     * @param customerAccount Customer account
     * @return 结果
     */
    public int insertCustomerAccount(CustomerAccount customerAccount);

    /**
     *sửa đổiCustomer account
     * 
     * @param customerAccount Customer account
     * @return 结果
     */
    public int updateCustomerAccount(CustomerAccount customerAccount);

    /**
     * XóaCustomer account
     * 
     * @param id Customer accountID
     * @return 结果
     */
    public int deleteCustomerAccountById(Long id);

    /**
     * 批量XóaCustomer account
     * 
     * @param ids 需要Xóa的数据ID
     * @return 结果
     */
    public int deleteCustomerAccountByIds(String[] ids);
}
