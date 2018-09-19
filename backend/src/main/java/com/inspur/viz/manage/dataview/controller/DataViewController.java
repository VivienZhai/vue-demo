package com.inspur.viz.manage.dataview.controller;

import java.util.HashMap;
import java.util.Map;

import org.loushang.framework.base.PageResult;
import org.loushang.framework.base.RestResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inspur.viz.model.DataView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "数据视图")
@RestController
@RequestMapping("/manage/views")
public class DataViewController {

	@ApiOperation(value = "获取数据视图列表", notes = "获取所有数据视图 ,支持分页查询")
	@GetMapping
	public PageResult<DataView> listDataViews(@RequestParam(required = false) String fuzzyName,
			@RequestParam(defaultValue = "0") int start, @RequestParam(defaultValue = "-1") int limit) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("fuzzyName", fuzzyName);
		param.put("start", start);
		param.put("limit", limit);

		return new PageResult<DataView>(null, 0);
	}

	@ApiOperation(value = "根据ID获取数据源详情")
	@GetMapping(value = "/{id}")
	public RestResult<DataView> getDataViewById(@PathVariable("id") String id) {
		return new RestResult<DataView>(null);
	}

	@ApiOperation(value = "新增数据视图,返回新增数据源ID")
	@PostMapping
	public String addDataView(@RequestBody DataView dv) {

		return "";
	}

	@ApiOperation(value = "更新数据视图")
	@PutMapping("/{id}")
	public RestResult<String> updateDataView(@PathVariable("id") String id, @RequestBody DataView dc) {
		return new RestResult<String>();
	}

	@ApiOperation(value = "根据ID删除数据视图")
	@DeleteMapping("/{id}")
	public RestResult<String> deleteDataView(@PathVariable("id") String id) {
		return new RestResult<String>();
	}
}
