package org.loushang.framework.dict.service.impl;

import java.util.List;
import java.util.Map;

import org.loushang.framework.base.PageResult;
import org.loushang.framework.dict.dao.DictItemMapper;
import org.loushang.framework.dict.data.DictItem;
import org.loushang.framework.dict.service.IDictItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("dictItemService")
public class DictItemServiceImpl implements IDictItemService {

	@Autowired
	private DictItemMapper dictItemMapper;

	@Override
	public String addItem(DictItem item) {
		dictItemMapper.addItem(item);
		return item.getCode();
	}

	@Override
	public void updateItem(DictItem item) {
		dictItemMapper.updateItem(item);
	}

	@Override
	public PageResult<DictItem> listItems(Map<String, Object> param) {
		PageHelper.startPage(param);
		List<DictItem> list = dictItemMapper.listItems(param);
		PageInfo<DictItem> pageInfo = new PageInfo<DictItem>(list);
		return new PageResult<DictItem>(list, pageInfo.getTotal());
	}

	@Override
	public void removeItem(String dictCode, String itemCode) {
		dictItemMapper.removeItem(dictCode, itemCode);
	}
	
	@Override
	public void batchRemoveItem(String dictCode, String[] itemCodes) {
		dictItemMapper.batchRemoveItem(dictCode, itemCodes);
	}

	@Override
	public DictItem getItem(String dictCode, String itemCode) {
		return dictItemMapper.getItem(dictCode, itemCode);
	}

}
