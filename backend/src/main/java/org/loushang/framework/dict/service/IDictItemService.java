package org.loushang.framework.dict.service;

import java.util.Map;

import org.loushang.framework.base.PageResult;
import org.loushang.framework.dict.data.DictItem;

public interface IDictItemService {

	String addItem(DictItem item);

	void removeItem(String dictCode, String itemCode);

	void batchRemoveItem(String dictCode, String[] itemCode);

	void updateItem(DictItem item);

	DictItem getItem(String dictCode, String itemCode);

	PageResult<DictItem> listItems(Map<String, Object> param);
}
