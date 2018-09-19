package org.loushang.framework.dict.dao;

import java.util.List;
import java.util.Map;

import org.loushang.framework.dict.data.Dict;

public interface DictMapper {

	void addDict(Dict dict);

	void removeDict(String id);
	
	void batchRemoveDict(String[] ids);

	void updateDict(Dict dict);

	Dict getDictById(String id);

	List<Dict> listDict(Map<String, Object> param);
}
