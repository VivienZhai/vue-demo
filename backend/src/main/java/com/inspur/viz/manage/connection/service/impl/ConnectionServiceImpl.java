package com.inspur.viz.manage.connection.service.impl;

import java.util.List;
import java.util.Map;

import org.loushang.framework.base.PageResult;
import org.loushang.framework.util.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.inspur.viz.manage.connection.dao.ConnectionMapper;
import com.inspur.viz.manage.connection.service.IConnectionService;
import com.inspur.viz.model.DataConnection;

@Service("connectionService")
public class ConnectionServiceImpl implements IConnectionService {

	@Autowired
	private ConnectionMapper connectionMapper;

	@Override
	public PageResult<DataConnection> pagingListConnection(Map<String, Object> param) {
		// param需要中包括pageNum和pageSize
		PageHelper.startPage(param);
		List<DataConnection> list = connectionMapper.listDataConnection(param);
		PageInfo<DataConnection> pageInfo = new PageInfo<DataConnection>(list);
		return new PageResult<DataConnection>(list, pageInfo.getTotal());
	}

	@Override
	public List<DataConnection> listConnection(Map<String, Object> param) {
		return connectionMapper.listDataConnection(param);
	}

	@Override
	public DataConnection getConnectionById(String id) {
		return connectionMapper.getDataConnection(id);
	}

	@Override
	public String insertConnection(DataConnection dc) {
		String id = UUIDGenerator.getUUID();
		dc.setId(id);
		connectionMapper.addConnection(dc);
		return id;
	}

	@Override
	public void updateConnection(DataConnection dc) {
		connectionMapper.updateConnection(dc);
	}

	@Override
	public void removeConnection(String id) {
		connectionMapper.removeConnection(id);
	}
}
