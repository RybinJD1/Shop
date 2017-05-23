package com.services.servicesImpl;

import com.dao.ProductDao;
import com.entity.Product;
import com.model.PaginationResult;
import com.model.ProductInfo;
import com.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

    @Autowired
    @Qualifier("productDaoImpl")
    private ProductDao productDao;

    @Override
    public Product findProduct(String code) {
        return productDao.findProduct(code);
    }

    @Override
    public ProductInfo findProductInfo(String code) {
        return productDao.findProductInfo(code);
    }

    @Override
    public PaginationResult<ProductInfo> queryProducts(int page, int maxResult, int maxNavigationPage) {
        return productDao.queryProducts(page, maxResult, maxNavigationPage);
    }

    @Override
    public PaginationResult<ProductInfo> queryProducts(int page, int maxResult, int maxNavigationPage, String likeName) {
        return productDao.queryProducts(page, maxResult, maxNavigationPage, likeName);
    }

    @Override
    public void save(ProductInfo productInfo) {
        productDao.save(productInfo);
    }
}
