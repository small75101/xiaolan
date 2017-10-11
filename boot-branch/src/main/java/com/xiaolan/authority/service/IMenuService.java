package com.xiaolan.authority.service;


import com.xiaolan.authority.domain.Menu;
import com.xiaolan.authority.domain.dto.MenuDto;
import com.xiaolan.common.service.IBaseService;

import java.util.Collection;
import java.util.List;


public interface IMenuService extends IBaseService<Menu, Integer> {

    /**
     * 查询显示排序
     *
     * @return
     * @throws Exception
     */
    List<Menu> listOfSort();

    /**
     * 格式化菜单
     *
     * @param menus
     * @return
     */
    Collection<MenuDto> parseMenus(List<Menu> menus);
}
