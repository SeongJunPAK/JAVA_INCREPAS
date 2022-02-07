package dao;

import java.util.List;

import vo.ProductVo;

public interface ProductDao {

	List<ProductVo> selectList(String category);
	ProductVo 		selectOne(int p_idx);
	int				insert(ProductVo vo);
}
