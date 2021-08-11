package com.imooc.mall.service.impl;

import com.imooc.mall.exception.ImoocMallException;
import com.imooc.mall.exception.ImoocMallExceptionEnum;
import com.imooc.mall.model.dao.CategoryMapper;
import com.imooc.mall.model.pojo.Category;
import com.imooc.mall.model.request.AddCategoryReq;
import com.imooc.mall.model.request.UpdateCategoryReq;
import com.imooc.mall.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void addCategory(AddCategoryReq addCategoryReq) {
        Category category = new Category();
        BeanUtils.copyProperties(addCategoryReq, category);
        Category oldCategory = categoryMapper.selectByName(addCategoryReq.getName());
        if (oldCategory != null) {
            throw new ImoocMallException(ImoocMallExceptionEnum.PARAM_NAME_EXIST);
        }
        //选择性插入
        int count = categoryMapper.insertSelective(category);
        if (count <= 0){
            throw new ImoocMallException(ImoocMallExceptionEnum.CREATE_FAILED);
        }
    }

    @Override
    public void updateCategory(UpdateCategoryReq updateCategoryReq) {
        Category category = new Category();
        BeanUtils.copyProperties(updateCategoryReq, category);
        if (updateCategoryReq.getName() != null){
            Category oldCategory = categoryMapper.selectByName(updateCategoryReq.getName());
            //如果根据name查到了分类，但不是同一个就重名
            if (oldCategory != null && oldCategory.getId().equals(updateCategoryReq.getId())) {
                throw new ImoocMallException(ImoocMallExceptionEnum.NAME_EXIST);
            }
        }
        //选择性插入
        int count = categoryMapper.updateByPrimaryKeySelective(category);
        if (count <= 0){
            throw new ImoocMallException(ImoocMallExceptionEnum.UPDATE_FAIL);
        }
    }
}
