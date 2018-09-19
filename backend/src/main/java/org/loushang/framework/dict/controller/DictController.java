package org.loushang.framework.dict.controller;

import java.util.HashMap;
import java.util.Map;

import org.loushang.framework.base.Constants;
import org.loushang.framework.base.PageResult;
import org.loushang.framework.base.RestResult;
import org.loushang.framework.dict.data.Dict;
import org.loushang.framework.dict.data.DictItem;
import org.loushang.framework.dict.service.IDictItemService;
import org.loushang.framework.dict.service.IDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "字典服务")
@RestController
@RequestMapping("/dict")
public class DictController {

	@Autowired
	@Qualifier("dictService")
	private IDictService dictService;

	@Autowired
	@Qualifier("dictItemService")
	private IDictItemService dictItemService;

	@ApiOperation(value = "获取字典列表[分页]")
	@GetMapping
	public PageResult<Dict> pageListDict(
			@ApiParam(value = "根据名称模糊查询", required = false) @RequestParam(required = false) String name,
			@ApiParam(value = "当前页数", required = true) @RequestParam int pageNum,
			@ApiParam(value = "每页显示记录数", required = true) @RequestParam int pageSize) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("name", name);
		param.put("pageNum", pageNum);
		param.put("pageSize", pageSize);

		return dictService.pageListDict(param);
	}

	@ApiOperation(value = "新增字典")
	@PostMapping
	public RestResult<Boolean> addDict(@RequestBody Dict dict) {
		dictService.addDict(dict);
		return RestResult.success();
	}

	@ApiOperation(value = "主键唯一性校验")
	@PostMapping("/{id}")
	public RestResult<Boolean> checkUnique(@PathVariable("id") String id) {
		Dict dict = dictService.getDictById(id);
		return new RestResult<Boolean>(dict == null);
	}

	@ApiOperation(value = "更新字典")
	@PutMapping("/{id}")
	public RestResult<Boolean> updateDict(@PathVariable("id") String id, @RequestBody Dict dict) {
		dict.setCode(id);
		dictService.updateDict(dict);
		return RestResult.success();
	}

	@ApiOperation(value = "根据字典ID获取字典内容")
	@GetMapping("/{id}")
	public RestResult<Dict> getDictById(@PathVariable("id") String id) {
		Dict dict = dictService.getDictById(id);
		return new RestResult<Dict>(dict);
	}

	@ApiOperation(value = "根据字典ID删除字典内容")
	@DeleteMapping("/{id}")
	public RestResult<Boolean> removeDict(@PathVariable("id") String id) {
		dictService.removeDict(id);
		return RestResult.success();
	}

	@ApiOperation(value = "批量删除字典")
	@DeleteMapping
	public RestResult<Boolean> batchRemoveDict(@RequestParam("keys") String[] keys) {
		dictService.batchRemoveDict(keys);
		return RestResult.success();
	}

	@ApiOperation(value = "根据字典ID获取字典项")
	@GetMapping("/{dict}/items")
	public PageResult<DictItem> listDictItem(@PathVariable("dict") String dict,
			@RequestParam(value = "parentCode", defaultValue = "") String parentCode,
			@RequestParam(value = "pageSize", defaultValue = "0") int pageSize,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("dictCode", dict);
		param.put("parentCode", parentCode);
		param.put(Constants.PAGE_SIZE, pageSize);
		param.put(Constants.PAGE_NUM, pageNum);
		return dictItemService.listItems(param);
	}

	@ApiOperation(value = "添加字典项")
	@PostMapping("/{dict}/items")
	public RestResult<Boolean> addDictItem(@PathVariable("dict") String dict, @RequestBody DictItem item) {
		dictItemService.addItem(item);
		return RestResult.success();
	}

	@ApiOperation(value = "检查字典项ID是否唯一")
	@PostMapping("/{dict}/items/{item}")
	public RestResult<Boolean> checkItemUnique(@PathVariable("dict") String dict, @PathVariable("item") String item) {
		DictItem dictItem = dictItemService.getItem(dict, item);
		return new RestResult<Boolean>(null == dictItem);
	}

	@ApiOperation(value = "批量删除字典项")
	@DeleteMapping("/{dict}/items")
	public RestResult<Boolean> batchRemoveItem(@PathVariable("dict") String dict, @RequestParam("keys") String[] keys) {
		dictItemService.batchRemoveItem(dict, keys);
		return RestResult.success();
	}

	@ApiOperation(value = "删除字典项")
	@DeleteMapping("/{dict}/items/{item}")
	public RestResult<Boolean> removeItem(@PathVariable("dict") String dict, @PathVariable("item") String item) {
		dictItemService.removeItem(dict, item);
		return RestResult.success();
	}

	@ApiOperation(value = "更新字典项")
	@PutMapping("/{dict}/items/{item}")
	public RestResult<Boolean> updateItem(@PathVariable("dict") String dict, @PathVariable("item") String item,
			@RequestBody DictItem dictItem) {
		dictItemService.updateItem(dictItem);
		return RestResult.success();
	}

}
