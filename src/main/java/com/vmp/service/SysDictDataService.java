package com.vmp.service;

import java.util.List;

import com.vmp.entity.query.SysDictDataQuery;
import com.vmp.entity.po.SysDictData;
import com.vmp.entity.vo.PaginationResultVO;


/**
 * 系统字典管理 业务接口
 */
public interface SysDictDataService {

	/**
	 * 根据条件查询列表
	 */
	List<SysDictData> findListByParam(SysDictDataQuery param);

	/**
	 * 根据条件查询列表
	 */
	Integer findCountByParam(SysDictDataQuery param);

	/**
	 * 分页查询
	 */
	PaginationResultVO<SysDictData> findListByPage(SysDictDataQuery param);

	/**
	 * 新增
	 */
	Integer add(SysDictData bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<SysDictData> listBean);

	/**
	 * 批量新增/修改
	 */
	Integer addOrUpdateBatch(List<SysDictData> listBean);

	/**
	 * 多条件更新
	 */
	Integer updateByParam(SysDictData bean,SysDictDataQuery param);

	/**
	 * 多条件删除
	 */
	Integer deleteByParam(SysDictDataQuery param);

	/**
	 * 根据DictId查询对象
	 */
	SysDictData getSysDictDataByDictId(String dictId);


	/**
	 * 根据DictId修改
	 */
	Integer updateSysDictDataByDictId(SysDictData bean,String dictId);


	/**
	 * 根据DictId删除
	 */
	Integer deleteSysDictDataByDictId(String dictId);


	/**
	 * 根据DictCode查询对象
	 */
	SysDictData getSysDictDataByDictCode(String dictCode);


	/**
	 * 根据DictCode修改
	 */
	Integer updateSysDictDataByDictCode(SysDictData bean,String dictCode);


	/**
	 * 根据DictCode删除
	 */
	Integer deleteSysDictDataByDictCode(String dictCode);

	/**
	 * 根据PidAndDictName查询对象
	 */
	SysDictData getSysDictDataByPidAndDictName(String pid,String dictName);


	/**
	 * 根据PidAndDictName修改
	 */
	Integer updateSysDictDataByPidAndDictName(SysDictData bean,String pid,String dictName);


	/**
	 * 根据PidAndDictName删除
	 */
	Integer deleteSysDictDataByPidAndDictName(String pid,String dictName);

	/**
	 * 新增字典数据
	 * @param dictData
	 */
	void newDictData(SysDictData dictData);

}