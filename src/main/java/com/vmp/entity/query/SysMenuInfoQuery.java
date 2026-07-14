package com.vmp.entity.query;

import java.util.Date;
import java.util.List;


/**
 * 系统菜单目录参数
 */
public class SysMenuInfoQuery extends BaseParam {


	/**
	 * 菜单id
	 */
	private String menuId;

	private String menuIdFuzzy;

	/**
	 * 父级菜单id
	 */
	private String pid;

	private String pidFuzzy;

	/**
	 * 菜单标题
	 */
	private String title;

	private String titleFuzzy;

	/**
	 * url路径
	 */
	private String path;

	private String pathFuzzy;

	/**
	 * 创建人id
	 */
	private String createBy;

	private String createByFuzzy;

	/**
	 * 修改人id
	 */
	private String updateBy;

	private String updateByFuzzy;

	/**
	 * 创建时间
	 */
	private String createTime;

	private String createTimeStart;

	private String createTimeEnd;

	/**
	 * 修改时间
	 */
	private String updateTime;

	private String updateTimeStart;

	private String updateTimeEnd;

	/**
	 * 可提供menuId列表查询菜单
	 */
	private List<String> menuIdList;


	public void setMenuId(String menuId){
		this.menuId = menuId;
	}

	public String getMenuId(){
		return this.menuId;
	}

	public void setMenuIdFuzzy(String menuIdFuzzy){
		this.menuIdFuzzy = menuIdFuzzy;
	}

	public String getMenuIdFuzzy(){
		return this.menuIdFuzzy;
	}

	public void setPid(String pid){
		this.pid = pid;
	}

	public String getPid(){
		return this.pid;
	}

	public void setPidFuzzy(String pidFuzzy){
		this.pidFuzzy = pidFuzzy;
	}

	public String getPidFuzzy(){
		return this.pidFuzzy;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return this.title;
	}

	public void setTitleFuzzy(String titleFuzzy){
		this.titleFuzzy = titleFuzzy;
	}

	public String getTitleFuzzy(){
		return this.titleFuzzy;
	}

	public void setPath(String path){
		this.path = path;
	}

	public String getPath(){
		return this.path;
	}

	public void setPathFuzzy(String pathFuzzy){
		this.pathFuzzy = pathFuzzy;
	}

	public String getPathFuzzy(){
		return this.pathFuzzy;
	}

	public void setCreateBy(String createBy){
		this.createBy = createBy;
	}

	public String getCreateBy(){
		return this.createBy;
	}

	public void setCreateByFuzzy(String createByFuzzy){
		this.createByFuzzy = createByFuzzy;
	}

	public String getCreateByFuzzy(){
		return this.createByFuzzy;
	}

	public void setUpdateBy(String updateBy){
		this.updateBy = updateBy;
	}

	public String getUpdateBy(){
		return this.updateBy;
	}

	public void setUpdateByFuzzy(String updateByFuzzy){
		this.updateByFuzzy = updateByFuzzy;
	}

	public String getUpdateByFuzzy(){
		return this.updateByFuzzy;
	}

	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	public String getCreateTime(){
		return this.createTime;
	}

	public void setCreateTimeStart(String createTimeStart){
		this.createTimeStart = createTimeStart;
	}

	public String getCreateTimeStart(){
		return this.createTimeStart;
	}
	public void setCreateTimeEnd(String createTimeEnd){
		this.createTimeEnd = createTimeEnd;
	}

	public String getCreateTimeEnd(){
		return this.createTimeEnd;
	}

	public void setUpdateTime(String updateTime){
		this.updateTime = updateTime;
	}

	public String getUpdateTime(){
		return this.updateTime;
	}

	public void setUpdateTimeStart(String updateTimeStart){
		this.updateTimeStart = updateTimeStart;
	}

	public String getUpdateTimeStart(){
		return this.updateTimeStart;
	}
	public void setUpdateTimeEnd(String updateTimeEnd){
		this.updateTimeEnd = updateTimeEnd;
	}

	public String getUpdateTimeEnd(){
		return this.updateTimeEnd;
	}

	public List<String> getMenuIdList() {
		return menuIdList;
	}

	public void setMenuIdList(List<String> menuIdList) {
		this.menuIdList = menuIdList;
	}
}
