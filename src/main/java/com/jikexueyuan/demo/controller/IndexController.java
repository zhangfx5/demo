package com.jikexueyuan.demo.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jikexueyuan.demo.common.PageParam;
import com.jikexueyuan.demo.service.TableIpService;

@Controller
public class IndexController {

    @Resource
    TableIpService service;

    @RequestMapping(value = "index")
    public String helloWorld(HttpServletRequest request) {
        String currPageStr = request.getParameter("page");
        int currPage = 1;
        try {
            currPage = Integer.parseInt(currPageStr);
        } catch (Exception e) {
        }

        // 获取总记录数
        int rowCount = service.getRowCount();
        PageParam pageParam = new PageParam();
        pageParam.setRowCount(rowCount);
        if (pageParam.getTotalPage() < currPage) {
            currPage = pageParam.getTotalPage();
        }
        pageParam.setCurrPage(currPage);
        pageParam = service.getIpListByPage(pageParam);

        request.setAttribute("pageParam", pageParam);

        return "index";
    }

}
