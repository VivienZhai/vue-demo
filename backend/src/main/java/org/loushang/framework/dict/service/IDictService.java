package org.loushang.framework.dict.service;

import java.util.Map;

import org.loushang.framework.base.PageResult;
import org.loushang.framework.dict.data.Dict;

public interface IDictService {
	String addDict(Dict dict);

	void removeDict(String id);

	void batchRemoveDict(String[] ids);

	void updateDict(Dict dict);

	Dict getDictById(String id);

	PageResult<Dict> pageListDict(Map<String, Object> param);
}
