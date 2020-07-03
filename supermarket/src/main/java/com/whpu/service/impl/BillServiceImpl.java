package com.whpu.service.impl;

import com.whpu.mapper.BillMapper;
import com.whpu.pojo.Bill;
import com.whpu.pojo.Provider;
import com.whpu.pojo.Result;
import com.whpu.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: (Bill)账单业务层实现类
 * @author 10542
 * @date: 2020/6/30/22:46
 */
@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillMapper billMapper;

    @Override
    public List<Result> findall(String userCode) {
        return billMapper.findall(userCode);
    }

    @Override
    public Bill findByProductName(String productName) {
        return billMapper.findByProductName(productName);
    }

    @Override
    public Result findById(Integer id) {
        return billMapper.findById(id);
    }

    /**
     * 用于商品名称的模糊查询
     * @param condition
     * @return
     */
    @Override
    public List<Result> findlikeProductName(String condition) {
        return billMapper.findlikeProductName(condition);
    }

    /**
     * 用于供应商名称的模糊查询
     * @param condition
     * @return
     */
    @Override
    public List<Result> findlikeProName(String condition) {
        return billMapper.findlikeProName(condition);
    }

    @Override
    public List<Result> findallPro() {
        return billMapper.findallPro();
    }

    @Override
    public void addBill(Result result) {
        billMapper.add(result);
    }

    @Override
    public void update(Result result) {
        billMapper.update(result);
    }

    @Override
    public void delete(Integer id) {
        billMapper.delete(id);
    }

    @Override
    public List<Result> findLike(String condition1, String condition2, String condition3) {
        return billMapper.findLike(condition1,condition2,condition3);
    }
}
