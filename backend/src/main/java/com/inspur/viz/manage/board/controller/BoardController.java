package com.inspur.viz.manage.board.controller;

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

import com.inspur.viz.model.DashBoard;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "仪表盘")
@RestController
@RequestMapping("/manage/dashboards")
public class BoardController {

	@ApiOperation(value = "获取仪表盘列表", notes = "获取所有仪表盘 ,支持分页查询")
	@GetMapping
	public PageResult<DashBoard> listDashBoards(@RequestParam(required = false) String fuzzyName,
			@RequestParam(defaultValue = "0") int start, @RequestParam(defaultValue = "-1") int limit) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("fuzzyName", fuzzyName);
		param.put("start", start);
		param.put("limit", limit);

		return new PageResult<DashBoard>(null);
	}

	@ApiOperation(value = "根据ID获取仪表盘详情")
	@GetMapping(value = "/{id}")
	public RestResult<DashBoard> getDashBoardById(@PathVariable("id") String id) {
		return new RestResult<DashBoard>(null);
	}

	@ApiOperation(value = "新增仪表盘,返回新增仪表盘ID")
	@PostMapping
	public String addBoard(@RequestBody DashBoard board) {

		return "";
	}

	@ApiOperation(value = "更新仪表盘")
	@PutMapping("/{id}")
	public RestResult<String> updateBoard(@PathVariable("id") String id, @RequestBody DashBoard board) {
		return new RestResult<String>();
	}

	@ApiOperation(value = "根据ID删除仪表盘")
	@DeleteMapping("/{id}")
	public RestResult<String> deleteBoard(@PathVariable("id") String id) {
		return new RestResult<String>();
	}
}
