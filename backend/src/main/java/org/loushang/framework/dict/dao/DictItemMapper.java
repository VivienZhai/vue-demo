package org.loushang.framework.dict.dao;

import java.util.List;
import java.util.Map;

import org.loushang.framework.dict.data.DictItem;

public interface DictItemMapper {

	void addItem(DictItem item);

	void removeItem(String dictCode, String itemCode);
	
	void batchRemoveItem(String dictCode, String[] itemCode);

	void updateItem(DictItem item);

	DictItem getItem(String dictCode, String itemCode);

	List<DictItem> listItems(Map<String, Object> param);
}
