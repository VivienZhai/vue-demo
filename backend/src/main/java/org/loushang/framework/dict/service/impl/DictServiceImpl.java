package org.loushang.framework.dict.service.impl;

import java.util.List;
import java.util.Map;

import org.loushang.framework.base.PageResult;
import org.loushang.framework.dict.dao.DictMapper;
import org.loushang.framework.dict.data.Dict;
import org.loushang.framework.dict.service.IDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("dictService")
public class DictServiceImpl implements IDictService {

	@Autowired
	private DictMapper dictMapper;

	@Override
	public String addDict(Dict dict) {
		dictMapper.addDict(dict);
		return dict.getCode();
	}

	@Override
	public void removeDict(String id) {
		dictMapper.removeDict(id);
	}
	
	@Override
	public void batchRemoveDict(String[] ids) {
		dictMapper.batchRemoveDict(ids);
	}

	@Override
	public void updateDict(Dict dict) {
		dictMapper.updateDict(dict);
	}

	@Override
	public Dict getDictById(String id) {
		return dictMapper.getDictById(id);
	}

	@Override
	public PageResult<Dict> pageListDict(Map<String, Object> param) {
		PageHelper.startPage(param);
		List<Dict> list = dictMapper.listDict(param);
		PageInfo<Dict> pageInfo = new PageInfo<Dict>(list);

		return new PageResult<Dict>(list, pageInfo.getTotal());
	}

}
