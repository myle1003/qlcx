package com.qlcx.system.service.impl;

import java.util.List;
import com.qlcx.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qlcx.system.mapper.CustomerAccountMapper;
import com.qlcx.system.domain.CustomerAccount;
import com.qlcx.system.service.ICustomerAccountService;
import com.qlcx.common.core.text.Convert;

/**
 * Customer accountService业务层处理
 * 
 * @author qlcx
 * @date 2020-09-18
 */
@Service
public class CustomerAccountServiceImpl implements ICustomerAccountService 
{
    @Autowired
    private CustomerAccountMapper customerAccountMapper;

    /**
     * 查询Customer account
     * 
     * @param id Customer accountID
     * @return Customer account
     */
    @Override
    public CustomerAccount selectCustomerAccountById(Long id)
    {
        return customerAccountMapper.selectCustomerAccountById(id);
    }

    /**
     * 查询Customer accountDanh sách
     * 
     * @param customerAccount Customer account
     * @return Customer account
     */
    @Override
    public List<CustomerAccount> selectCustomerAccountList(CustomerAccount customerAccount)
    {
        return customerAccountMapper.selectCustomerAccountList(customerAccount);
    }

    /**
     * Thêm vàoCustomer account
     * 
     * @param customerAccount Customer account
     * @return 结果
     */
    @Override
    public int insertCustomerAccount(CustomerAccount customerAccount)
    {
        customerAccount.setCreateTime(DateUtils.getNowDate());
        return customerAccountMapper.insertCustomerAccount(customerAccount);
    }

    /**
     *sửa đổiCustomer account
     * 
     * @param customerAccount Customer account
     * @return 结果
     */
    @Override
    public int updateCustomerAccount(CustomerAccount customerAccount)
    {
        customerAccount.setUpdateTime(DateUtils.getNowDate());
        return customerAccountMapper.updateCustomerAccount(customerAccount);
    }

    /**
     * XóaCustomer account对象
     * 
     * @param ids 需要Xóa的数据ID
     * @return 结果
     */
    @Override
    public int deleteCustomerAccountByIds(String ids)
    {
        return customerAccountMapper.deleteCustomerAccountByIds(Convert.toStrArray(ids));
    }

    /**
     * XóaCustomer account信息
     * 
     * @param id Customer accountID
     * @return 结果
     */
    @Override
    public int deleteCustomerAccountById(Long id)
    {
        return customerAccountMapper.deleteCustomerAccountById(id);
    }
}
