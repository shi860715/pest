package com.isoftstone.mapper.main;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.isoftstone.entity.model.RemoteMonitore;
import com.isoftstone.entity.pojo.RemoteMonitorePojo;
import com.isoftstone.util.MyMapper;

public interface RemoteMonitoreMapper extends MyMapper<RemoteMonitore> {

	/**
     * 
     * 分页查询远程监控设备信息
     *
     * @author feilua
     * @param page
     *            分页模型
     * @return 用户分页列表
     * @since 207年11月06日
     */
    public List<RemoteMonitorePojo> findPage(Map<String,Object> map);
    
    /**
     * 批量删除远程监控设备信息
     * @param list
     * @return
     */
    public int delRemoteMonitore(@Param(value="list") List<Long> list);
    
    /**
     * 添加远程监控设备信息
     * @param list
     * @return
     */
    public int addRemoteMonitore(RemoteMonitore remoteMonitore);
    
    /**
     * 编辑远程诊断信息表
     * @param remoteDiagnosis
     * @return
     */
    public int editRemoteMonitore(RemoteMonitore remoteMonitore);
    
    /**
     * 
     * 通过ID查询远程监控设备视频流信息
     *
     * @author jnjua
     * @param remoteMonitore
     *            监控设备模型
     * @return 监控设备列表
     * @since 2017年12月27日
     */
    public RemoteMonitorePojo findHdById(Long id);
}
