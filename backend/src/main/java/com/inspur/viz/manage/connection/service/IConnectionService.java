package com.inspur.viz.manage.connection.service;

import java.util.List;
import java.util.Map;

import org.loushang.framework.base.PageResult;

import com.inspur.viz.model.DataConnection;

public interface IConnectionService {

	PageResult<DataConnection> pagingListConnection(Map<String, Object> param);

	List<DataConnection> listConnection(Map<String, Object> param);

	DataConnection getConnectionById(String id);

	String insertConnection(DataConnection dc);

	void updateConnection(DataConnection dc);

	void removeConnection(String id);
}
