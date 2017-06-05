package com.services.impl;

import com.dao.ProductDao;
import com.entity.Product;
import com.services.ProductService;
import com.services.exceptions.ServiceException;
import com.vo.PaginationResult;
import com.vo.ProductInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private static final Logger log = Logger.getLogger(ProductServiceImpl.class);


    @Autowired
    @Qualifier("productDaoImpl")
    private ProductDao productDao;

    @Override
    public Product findProduct(String code) throws ServiceException {
        return productDao.findProduct(code);
    }

    @Override
    public ProductInfo findProductInfo(String code) throws ServiceException {
        return productDao.findProductInfo(code);
    }

    @Override
    public PaginationResult<ProductInfo> queryProducts(int page, int maxResult, int maxNavigationPage) throws ServiceException {
        return productDao.queryProducts(page, maxResult, maxNavigationPage);
    }

    @Override
    public PaginationResult<ProductInfo> queryProducts(int page, int maxResult, int maxNavigationPage, String likeName) throws ServiceException {
        return productDao.queryProducts(page, maxResult, maxNavigationPage, likeName);
    }

    @Override
    public void save(ProductInfo productInfo) throws ServiceException {
        String code = productInfo.getCode();
        Product product = null;
        if (code != null) {
            product = this.findProduct(code);
        }
        if (product == null) {
            product = new Product();
            product.setCreateDate(new Date());
        }
        product.setCode(code);
        product.setName(productInfo.getName());
        product.setPrice(productInfo.getPrice());
        if (productInfo.getFileData() != null) {
            byte[] image = productInfo.getFileData().getBytes();
            if (image != null && image.length > 0) {
                product.setImage(image);
            }
        }
        productDao.save(product);
    }

}
