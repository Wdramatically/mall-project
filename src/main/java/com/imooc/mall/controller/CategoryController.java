package com.imooc.mall.controller;

import com.imooc.mall.common.ApiRestResponse;
import com.imooc.mall.common.Constants;
import com.imooc.mall.exception.ImoocMallExceptionEnum;
import com.imooc.mall.model.pojo.User;
import com.imooc.mall.model.request.AddCategoryReq;
import com.imooc.mall.service.CategoryService;
import com.imooc.mall.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@ApiOperation("商品分类")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @ApiOperation("添加商品分类")
    @PostMapping("/admin/category/add")
    @ResponseBody
    public ApiRestResponse addCategory(HttpSession session, @Valid @RequestBody AddCategoryReq addCategoryReq) {
        User user = (User) session.getAttribute(Constants.IMOOC_MALL_USER);
        if (user == null){
            return ApiRestResponse.error(ImoocMallExceptionEnum.NEED_LOGIN);
        }
        boolean isAdminRole = userService.isAdminRole(user);
        if (isAdminRole) {
            categoryService.addCategory(addCategoryReq);
        }else {
            return ApiRestResponse.error(ImoocMallExceptionEnum.NEED_ADMIN);
        }
        return ApiRestResponse.success();
    }
}
