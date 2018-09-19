package com.inspur.viz.manage.connection.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.loushang.framework.base.PageResult;
import org.loushang.framework.base.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inspur.viz.manage.connection.service.IConnectionService;
import com.inspur.viz.model.DataConnection;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "数据源连接")
@RestController
@RequestMapping("/manage/connections")
public class ConnectionController {

	@Autowired
	private IConnectionService connectionService;

	@ApiOperation(value = "获取数据源连接列表[分页]")
	@GetMapping
	public PageResult<DataConnection> pagingListConnections(
			@ApiParam(value = "根据名称模糊查询", required = false) @RequestParam(required = false) String fuzzyName,
			@ApiParam(value = "当前页数", required = true) @RequestParam int pageNum,
			@ApiParam(value = "每页显示记录数", required = true) @RequestParam int pageSize) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("fuzzyName", fuzzyName);
		param.put("pageNum", pageNum);
		param.put("pageSize", pageSize);

		return connectionService.pagingListConnection(param);
	}

	@ApiOperation(value = "获取数据源连接列表[不分页]")
	@GetMapping(params = { "pagination=false" })
	public PageResult<DataConnection> listConnections(
			@ApiParam(value = "根据名称模糊查询", required = false) @RequestParam(required = false) String fuzzyName) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("fuzzyName", fuzzyName);
		List<DataConnection> list = connectionService.listConnection(param);
		return new PageResult<DataConnection>(list, list.size());
	}

	@ApiOperation(value = "根据ID获取数据源详情")
	@GetMapping(value = "/{id}")
	public RestResult<DataConnection> getConnectionById(@PathVariable("id") String id) {
		DataConnection dc = connectionService.getConnectionById(id);
		return new RestResult<DataConnection>(dc);
	}

	@ApiOperation(value = "新增数据源连接,返回新增数据源ID")
	@PostMapping
	public RestResult<String> addConnection(@RequestBody DataConnection dc) {
		String id = connectionService.insertConnection(dc);
		return new RestResult<String>(id);
	}

	@ApiOperation(value = "更新数据源连接")
	@PutMapping("/{id}")
	public RestResult<String> updateConnection(@PathVariable("id") String id, @RequestBody DataConnection dc) {
		dc.setId(id);
		connectionService.updateConnection(dc);
		return new RestResult<String>();
	}

	@ApiOperation(value = "根据ID删除数据源连接")
	@DeleteMapping("/{id}")
	public RestResult<String> removeConnection(@PathVariable("id") String id) {
		connectionService.removeConnection(id);
		return new RestResult<String>();
	}
}
