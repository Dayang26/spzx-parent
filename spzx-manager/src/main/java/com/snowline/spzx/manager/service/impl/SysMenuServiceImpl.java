package com.snowline.spzx.manager.service.impl;

import com.snowline.spzx.common.exception.SnowLineException;
import com.snowline.spzx.manager.mapper.SysMenuMapper;
import com.snowline.spzx.manager.mapper.SysRoleMenuMapper;
import com.snowline.spzx.manager.service.SysMenuService;
import com.snowline.spzx.manager.utils.MenuHelper;
import com.snowline.spzx.model.entity.system.SysMenu;
import com.snowline.spzx.model.entity.system.SysUser;
import com.snowline.spzx.model.vo.common.ResultCodeEnum;
import com.snowline.spzx.model.vo.system.SysMenuVo;
import com.snowline.spzx.utils.AuthContextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Snow
 * @create 2024-01-16 18:12
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {


    public int index = 0;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;


    //菜单列表
    @Override
    public List<SysMenu> findNodes() {
        //  查询所有菜单，返回list集合
        List<SysMenu> menusList = sysMenuMapper.findAll();
        if (CollectionUtils.isEmpty(menusList)) {
            return null;
        }
        // 调用工具类的方法，把list集合封装成要求的格式
        return MenuHelper.buildTree(menusList);
    }

    //菜单添加
    @Override
    public void save(SysMenu sysMenu) {
        sysMenuMapper.save(sysMenu);

        //把父菜单isHalf设置成为半开状态1
        this.updateSysRoleMenu(sysMenu);
    }


    //把父菜单isHalf设置成为半开状态1
    private void updateSysRoleMenu(SysMenu sysMenu) {
        //获取当前添加菜单的父菜单
        SysMenu parentMenu = sysMenuMapper.getParentMenu(sysMenu.getParentId());
        if (parentMenu != null) {
            // 把isHalf值改为1
            sysRoleMenuMapper.updateSysRoleMenuIsHalf(parentMenu.getId());

            //递归调用
            updateSysRoleMenu(parentMenu);
        }

    }

    @Override
    public void update(SysMenu sysMenu) {
        sysMenuMapper.update(sysMenu);
    }

    //根据菜单id删除
    @Override
    public void removeById(Long id) {
        //根据当前菜单id查询，是否包含子菜单
        int count = sysMenuMapper.selectCountById(id);

        //如果count>0 包含子菜单 不能删除
        if (count > 0) {
            throw new SnowLineException(ResultCodeEnum.NODE_ERROR);
        }

        //count = 0 直接删除

        sysMenuMapper.removeById(id);
    }


    //查询用户可以操作的菜单
    @Override
    public List<SysMenuVo> findMenusByUserId() {
        //获取当前用户id
        SysUser sysUser = AuthContextUtils.get();
        Long id = sysUser.getId();

        //根据id查询可以操作的菜单
        List<SysMenu> sysMenuList = sysMenuMapper.findMenusByUserId(id);

        //构建树形数据
        List<SysMenu> sysMenuTreeList = MenuHelper.buildTree(sysMenuList);

        //封装数据格式,并且返回
        return this.buildMenus(sysMenuTreeList);
    }

    // 将List<SysMenu>对象转换成List<SysMenuVo>对象
    private List<SysMenuVo> buildMenus(List<SysMenu> menus) {
        List<SysMenuVo> sysMenuVoList = new LinkedList<SysMenuVo>();
        for (SysMenu sysMenu : menus) {
            SysMenuVo sysMenuVo = new SysMenuVo();
            sysMenuVo.setTitle(sysMenu.getTitle());
            sysMenuVo.setName(sysMenu.getComponent());
            List<SysMenu> children = sysMenu.getChildren();
            if (!CollectionUtils.isEmpty(children)) {
                sysMenuVo.setChildren(buildMenus(children));
            }
            sysMenuVoList.add(sysMenuVo);
        }
        return sysMenuVoList;
    }
}
