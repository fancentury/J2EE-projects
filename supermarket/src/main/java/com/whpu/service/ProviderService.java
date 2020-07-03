package com.whpu.service;

import com.whpu.pojo.Provider;

import java.util.List;

/**
 * @ClassName ProviderService
 * @Description: 供应商服务层接口
 * @author: RiceBall
 * @date: 2020/6/28 22:37
 */
public interface ProviderService {
    List<Provider> findAll();

    Provider findByProName(String proName);

    void saveProvider(Provider provider);

    Provider findById(Integer id);

    void updateProvider(Provider provider);

    void delProvider(Integer id);

    List<Provider> findLike(String condition);
}
