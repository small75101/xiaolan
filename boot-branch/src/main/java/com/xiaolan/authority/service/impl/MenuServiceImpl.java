package com.xiaolan.authority.service.impl;

import com.xiaolan.authority.domain.Menu;
import com.xiaolan.authority.domain.dto.MenuDto;
import com.xiaolan.authority.repository.IMenuRepository;
import com.xiaolan.authority.service.IMenuService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class MenuServiceImpl implements IMenuService {

    private IMenuRepository menuRepository;

    @Override
    public Menu save(Menu menu) {
        return menuRepository.save(menu);
    }

    @Override
    public void delete(Integer id) {
        menuRepository.delete(id);
    }

    @Override
    public Menu update(Menu menu) {
        return menuRepository.save(menu);
    }

    @Override
    public Menu findOne(Integer id) {
        return menuRepository.findOne(id);
    }


    @Override
    public List<Menu> findAll(Menu menu) {
        if (menu == null)
            menu = new Menu();
        ExampleMatcher matcher = ExampleMatcher.matching()//
                .withMatcher("name", GenericPropertyMatchers.contains())//
                .withMatcher("pname", GenericPropertyMatchers.contains())//
                ;
        return menuRepository.findAll(Example.of(menu, matcher));
    }

    public List<Menu> listOfSort() {
        Sort sort = new Sort(Sort.Direction.ASC, "displayno");
        return menuRepository.findAll(sort);
    }

    @Override
    public Collection<MenuDto> parseMenus(List<Menu> menus) {
        Map<Integer, MenuDto> map = new HashMap<Integer, MenuDto>();
        for (Menu menu : menus) {
            if (menu.getType() == 1 && (menu.getPid() == null || menu.getPid() == 0)) {//一级菜单
                map.put(menu.getId(), new MenuDto(menu));
            } else if (menu.getType() == 2) {//二级菜单
                map.get(menu.getPid()).putMenuDto(new MenuDto(menu));
            }
        }
        return map.values();
    }
}
