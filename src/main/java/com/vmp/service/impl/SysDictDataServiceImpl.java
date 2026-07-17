package com.vmp.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.vmp.entity.constants.Constants;
import com.vmp.entity.enums.DictStatusEnum;
import org.springframework.stereotype.Service;

import com.vmp.entity.enums.PageSize;
import com.vmp.entity.query.SysDictDataQuery;
import com.vmp.entity.po.SysDictData;
import com.vmp.entity.vo.PaginationResultVO;
import com.vmp.entity.query.SimplePage;
import com.vmp.mappers.SysDictDataMapper;
import com.vmp.service.SysDictDataService;
import com.vmp.utils.StringTools;


/**
 * 系统字典管理 业务接口实现
 */
@Service("sysDictDataService")
public class SysDictDataServiceImpl implements SysDictDataService {

	@Resource
	private SysDictDataMapper<SysDictData, SysDictDataQuery> sysDictDataMapper;

	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<SysDictData> findListByParam(SysDictDataQuery param) {
		return this.sysDictDataMapper.selectList(param);
	}

	/**
	 * 根据条件查询列表
	 */
	@Override
	public Integer findCountByParam(SysDictDataQuery param) {
		return this.sysDictDataMapper.selectCount(param);
	}

	/**
	 * 分页查询方法
	 */
	@Override
	public PaginationResultVO<SysDictData> findListByPage(SysDictDataQuery param) {
		int count = this.findCountByParam(param);
		int pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();

		SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
		param.setSimplePage(page);
		List<SysDictData> list = this.findListByParam(param);
		PaginationResultVO<SysDictData> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	}

	/**
	 * 新增
	 */
	@Override
	public Integer add(SysDictData bean) {
		return this.sysDictDataMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<SysDictData> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.sysDictDataMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增或者修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<SysDictData> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.sysDictDataMapper.insertOrUpdateBatch(listBean);
	}

	/**
	 * 多条件更新
	 */
	@Override
	public Integer updateByParam(SysDictData bean, SysDictDataQuery param) {
		StringTools.checkParam(param);
		return this.sysDictDataMapper.updateByParam(bean, param);
	}

	/**
	 * 多条件删除
	 */
	@Override
	public Integer deleteByParam(SysDictDataQuery param) {
		StringTools.checkParam(param);
		return this.sysDictDataMapper.deleteByParam(param);
	}

	/**
	 * 根据DictId获取对象
	 */
	@Override
	public SysDictData getSysDictDataByDictId(String dictId) {
		return this.sysDictDataMapper.selectByDictId(dictId);
	}

	/**
	 * 根据DictId修改
	 */
	@Override
	public Integer updateSysDictDataByDictId(SysDictData bean, String dictId) {
		return this.sysDictDataMapper.updateByDictId(bean, dictId);
	}

	/**
	 * 根据DictId删除
	 */
	@Override
	public Integer deleteSysDictDataByDictId(String dictId) {
		return this.sysDictDataMapper.deleteByDictId(dictId);
	}

	/**
	 * 根据DictCode获取对象
	 */
	@Override
	public SysDictData getSysDictDataByDictCode(String dictCode) {
		return this.sysDictDataMapper.selectByDictCode(dictCode);
	}

	/**
	 * 根据DictCode修改
	 */
	@Override
	public Integer updateSysDictDataByDictCode(SysDictData bean, String dictCode) {
		return this.sysDictDataMapper.updateByDictCode(bean, dictCode);
	}

	/**
	 * 根据DictCode删除
	 */
	@Override
	public Integer deleteSysDictDataByDictCode(String dictCode) {
		return this.sysDictDataMapper.deleteByDictCode(dictCode);
	}

	/**
	 * 根据PidAndDictName获取对象
	 */
	@Override
	public SysDictData getSysDictDataByPidAndDictName(String pid, String dictName) {
		return this.sysDictDataMapper.selectByPidAndDictName(pid, dictName);
	}

	/**
	 * 根据PidAndDictName修改
	 */
	@Override
	public Integer updateSysDictDataByPidAndDictName(SysDictData bean, String pid, String dictName) {
		return this.sysDictDataMapper.updateByPidAndDictName(bean, pid, dictName);
	}

	/**
	 * 根据PidAndDictName删除
	 */
	@Override
	public Integer deleteSysDictDataByPidAndDictName(String pid, String dictName) {
		return this.sysDictDataMapper.deleteByPidAndDictName(pid, dictName);
	}

	/**
	 * 新增字典数据
	 *
	 * @param dictData
	 */
	@Override
	public void newDictData(SysDictData dictData) {
		dictData.setDictId(StringTools.getRandomString(Constants.LENGTH_20));
		dictData.setStatus(DictStatusEnum.ENABLE.getStatus());
		Date curDate = new Date();
		dictData.setCreateTime(curDate);
		add(dictData);
	}
}