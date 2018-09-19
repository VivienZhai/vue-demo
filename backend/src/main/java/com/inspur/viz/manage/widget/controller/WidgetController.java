package com.inspur.viz.manage.widget.controller;

import java.util.HashMap;
import java.util.Map;

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

import com.github.pagehelper.Page;
import com.inspur.viz.model.Widget;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "")
@RestController
@RequestMapping("/manage/widgets")
public class WidgetController {

	@ApiOperation(value = "获取图表组件列表", notes = "获取所有图表组件 ,支持分页查询")
	@GetMapping
	public Page<Widget> listWidgets(@RequestParam(required = false) String fuzzyName,
			@RequestParam(defaultValue = "0") int start, @RequestParam(defaultValue = "-1") int limit) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("fuzzyName", fuzzyName);
		param.put("start", start);
		param.put("limit", limit);

		return new Page<Widget>();
	}

	@ApiOperation(value = "根据ID获取图表组件详情")
	@GetMapping(value = "/{id}")
	public RestResult<Widget> getWidgetById(@PathVariable("id") String id) {
		return new RestResult<Widget>(null);
	}

	@ApiOperation(value = "新增图表组件,返回新增图表组件ID")
	@PostMapping
	public String addWidget(@RequestBody Widget widget) {

		return "";
	}

	@ApiOperation(value = "更新图表组件")
	@PutMapping("/{id}")
	public RestResult<String> updateWidget(@PathVariable("id") String id, @RequestBody Widget widget) {
		return new RestResult<String>();
	}

	@ApiOperation(value = "根据ID删除图表组件")
	@DeleteMapping("/{id}")
	public RestResult<String> deleteWidget(@PathVariable("id") String id) {
		return new RestResult<String>();
	}
}
