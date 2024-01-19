package com.snowline.spzx.manager.utils;

import com.snowline.spzx.model.entity.system.SysMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Snow
 * @create 2024-01-16 19:13
 * <p>
 * 封装树状菜单数据
 */
public class MenuHelper {


    //通过递归的方式实现封装的过程
    public static List<SysMenu> buildTree(List<SysMenu> sysMenuList) {
        //sysMenuList 所有菜单集合

        //创建List集合，用于封装最终的数据
        List<SysMenu> trees = new ArrayList<>();
        //遍历所有菜单集合
        for (SysMenu sysMenu : sysMenuList) {
            //找到递归的入口
            //条件:parent_id =0
            if (sysMenu.getParentId() == 0) {

                //根据第一层，找下一层数据，使用递归来完成
                //写方法试下你找下层的过程，，方法里面传递两个参数
                //第一个参数；当前第一层菜单  第二个参数:所有菜单集合
                trees.add(findChildren(sysMenu, sysMenuList));

            }
        }

        return trees;
    }

    //递归查找下层菜单
    private static SysMenu findChildren(SysMenu sysMenu, List<SysMenu> sysMenuList) {
        //  给children 初始化
        sysMenu.setChildren(new ArrayList<>());
        //递归查询
        //sysMenu的id等于sysMenuList的parent_id相同 就是下层菜单
        for (SysMenu it : sysMenuList) {
            //判断
            if (sysMenu.getId().longValue() == it.getParentId().longValue()) {
                //  it就是下层数据，需要进行封装
                sysMenu.getChildren().add(findChildren(it,sysMenuList));
            }
        }

        return sysMenu;

    }



}
