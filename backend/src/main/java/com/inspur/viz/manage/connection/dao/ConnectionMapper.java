package com.inspur.viz.manage.connection.dao;

import java.util.List;
import java.util.Map;

import com.inspur.viz.model.DataConnection;

public interface ConnectionMapper {

	List<DataConnection> listDataConnection(Map<String, Object> param);

	DataConnection getDataConnection(String id);

	void updateConnection(DataConnection conn);

	void removeConnection(String id);
	
	void addConnection(DataConnection conn);
}
