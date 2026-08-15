package com.vmp.service.impl;

import com.vmp.entity.constants.Constants;
import com.vmp.entity.dto.MenuSortDto;
import com.vmp.entity.enums.DragMenuPositionEnum;
import com.vmp.entity.enums.PageSize;
import com.vmp.entity.enums.ResponseCodeEnum;
import com.vmp.entity.enums.VerifyRegexEnum;
import com.vmp.entity.po.SysMenuInfo;
import com.vmp.entity.query.SimplePage;
import com.vmp.entity.query.SysMenuInfoQuery;
import com.vmp.entity.vo.PaginationResultVO;
import com.vmp.exception.BusinessException;
import com.vmp.mappers.SysMenuInfoMapper;
import com.vmp.service.SysMenuInfoService;
import com.vmp.utils.StringTools;
import com.vmp.utils.VerifyUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


/**
 * 系统菜单目录 业务接口实现
 */
@Service("sysMenuInfoService")
public class SysMenuInfoServiceImpl implements SysMenuInfoService {

    @Resource
    private SysMenuInfoMapper<SysMenuInfo, SysMenuInfoQuery> sysMenuInfoMapper;

    /**
     * 根据条件查询列表
     */
    @Override
    public List<SysMenuInfo> findListByParam(SysMenuInfoQuery param) {
        return this.sysMenuInfoMapper.selectList(param);
    }

    /**
     * 根据条件查询列表
     */
    @Override
    public Integer findCountByParam(SysMenuInfoQuery param) {
        return this.sysMenuInfoMapper.selectCount(param);
    }

    /**
     * 分页查询方法
     */
    @Override
    public PaginationResultVO<SysMenuInfo> findListByPage(SysMenuInfoQuery param) {
        int count = this.findCountByParam(param);
        int pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();

        SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
        param.setSimplePage(page);
        List<SysMenuInfo> list = this.findListByParam(param);
        PaginationResultVO<SysMenuInfo> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
        return result;
    }

    /**
     * 新增
     */
    @Override
    public Integer add(SysMenuInfo bean) {
        return this.sysMenuInfoMapper.insert(bean);
    }

    /**
     * 批量新增
     */
    @Override
    public Integer addBatch(List<SysMenuInfo> listBean) {
        if (listBean == null || listBean.isEmpty()) {
            return 0;
        }
        return this.sysMenuInfoMapper.insertBatch(listBean);
    }

    /**
     * 批量新增或者修改
     */
    @Override
    public Integer addOrUpdateBatch(List<SysMenuInfo> listBean) {
        if (listBean == null || listBean.isEmpty()) {
            return 0;
        }
        return this.sysMenuInfoMapper.insertOrUpdateBatch(listBean);
    }

    /**
     * 多条件更新
     */
    @Override
    public Integer updateByParam(SysMenuInfo bean, SysMenuInfoQuery param) {
        StringTools.checkParam(param);
        return this.sysMenuInfoMapper.updateByParam(bean, param);
    }

    /**
     * 多条件删除
     */
    @Override
    public Integer deleteByParam(SysMenuInfoQuery param) {
        StringTools.checkParam(param);
        return this.sysMenuInfoMapper.deleteByParam(param);
    }

    /**
     * 根据MenuId获取对象
     */
    @Override
    public SysMenuInfo getSysMenuInfoByMenuId(String menuId) {
        return this.sysMenuInfoMapper.selectByMenuId(menuId);
    }

    /**
     * 根据MenuId修改
     */
    @Override
    public Integer updateSysMenuInfoByMenuId(SysMenuInfo bean, String menuId) {
        return this.sysMenuInfoMapper.updateByMenuId(bean, menuId);
    }

    /**
     * 根据MenuId删除
     */
    @Override
    public Integer deleteSysMenuInfoByMenuId(String menuId) {
        return this.sysMenuInfoMapper.deleteByMenuId(menuId);
    }

    /**
     * 根据Title获取对象
     */
    @Override
    public SysMenuInfo getSysMenuInfoByTitle(String title) {
        return this.sysMenuInfoMapper.selectByTitle(title);
    }

    /**
     * 根据Title修改
     */
    @Override
    public Integer updateSysMenuInfoByTitle(SysMenuInfo bean, String title) {
        return this.sysMenuInfoMapper.updateByTitle(bean, title);
    }

    /**
     * 根据Title删除
     */
    @Override
    public Integer deleteSysMenuInfoByTitle(String title) {
        return this.sysMenuInfoMapper.deleteByTitle(title);
    }

    /**
     * 新增菜单页面
     *
     * @param userId
     * @param menuInfo
     */
    @Override
    public void newMenu(String userId, SysMenuInfo menuInfo) {
        if (!VerifyUtils.verify(VerifyRegexEnum.MENU_PATH, menuInfo.getPath())) {
            throw new BusinessException(ResponseCodeEnum.CODE_600);
        }
        menuInfo.setMenuId(StringTools.getRandomNumber(Constants.LENGTH_20));
        SysMenuInfoQuery countQuery = new SysMenuInfoQuery();
        countQuery.setPid(menuInfo.getPid());
        menuInfo.setOrderNum(findCountByParam(countQuery) + 1);
        menuInfo.setCreateBy(userId);
        menuInfo.setUpdateBy(userId);
        Date curdate = new Date();
        menuInfo.setCreateTime(curdate);
        menuInfo.setUpdateTime(curdate);
        add(menuInfo);
    }

    /**
     * 修改菜单页面
     *
     * @param userId
     * @param menuInfo
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateMenu(String userId, SysMenuInfo menuInfo) {
        if (!VerifyUtils.verify(VerifyRegexEnum.MENU_PATH, menuInfo.getPath())) {
            throw new BusinessException(ResponseCodeEnum.CODE_600);
        }
        menuInfo.setUpdateBy(userId);
        Date curdate = new Date();
        menuInfo.setUpdateTime(curdate);
        updateSysMenuInfoByMenuId(menuInfo, menuInfo.getMenuId());
    }

    /**
     * 重排序菜单
     *
     * @param userId
     * @param sortDto
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void dragSortMenu(String userId, MenuSortDto sortDto) {
        if (!DragMenuPositionEnum.BEFORE.getPosition().equals(sortDto.getPosition()) && !DragMenuPositionEnum.AFTER.getPosition().equals(sortDto.getPosition())) {
            throw new BusinessException(ResponseCodeEnum.CODE_600);
        }
        // 当前菜单
        SysMenuInfo currentMenu = this.sysMenuInfoMapper.selectByMenuId(sortDto.getMenuId());
        // 目标菜单
        SysMenuInfo targetMenu = this.sysMenuInfoMapper.selectByMenuId(sortDto.getTargetMenuId());

        // 判断是否同一父级下
        if (!currentMenu.getPid().equals(targetMenu.getPid())) {
            throw new BusinessException("不能跨父级菜单拖拽");
        }

        // 获取同一父级下的所有菜单(按order_num升序排序)
        SysMenuInfoQuery query = new SysMenuInfoQuery();
        query.setPid(currentMenu.getPid());
        query.setOrderBy("order_num asc");
        List<SysMenuInfo> menuInfoList = this.sysMenuInfoMapper.selectList(query);

        // 计算order_num
        int currentIndex = menuInfoList.indexOf(currentMenu);
        int targetIndex = menuInfoList.indexOf(targetMenu);

        // 重新插入当前菜单
        menuInfoList.remove(currentIndex);
        int insertIndex = DragMenuPositionEnum.BEFORE.getPosition().equals(sortDto.getPosition()) ? targetIndex : targetIndex + 1;
        if (currentIndex < targetIndex) {
            insertIndex = insertIndex - 1;
        }
        menuInfoList.add(insertIndex, currentMenu);

        // 批量更新order_num
        for (int i = 0; i < menuInfoList.size(); i++) {
            SysMenuInfo menu = menuInfoList.get(i);
            menu.setOrderNum(i + 1);
            menu.setUpdateTime(new Date());
            menu.setUpdateBy(userId);
            this.sysMenuInfoMapper.updateByMenuId(menu, menu.getMenuId());
        }
    }
}