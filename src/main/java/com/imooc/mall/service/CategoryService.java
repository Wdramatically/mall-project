package com.imooc.mall.service;

import com.imooc.mall.model.request.AddCategoryReq;
import com.imooc.mall.model.request.UpdateCategoryReq;

/**
 *  分类目录
 */
public interface CategoryService {

    void addCategory(AddCategoryReq addCategoryReq);

    void updateCategory(UpdateCategoryReq updateCategoryReq);
}
