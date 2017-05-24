package com.services.servicesImpl;

import com.dao.ProductDao;
import com.dao.exceptions.DaoException;
import com.entity.Product;
import com.model.PaginationResult;
import com.model.ProductInfo;
import com.services.ProductService;
import com.services.exceptions.ServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        try {
            return productDao.findProductInfo(code);
        } catch (DaoException e) {
            log.info("findProductInfo" + e);
            throw new ServiceException(e.getKey(), e);
        }
    }

    @Override
    public PaginationResult<ProductInfo> queryProducts(int page, int maxResult, int maxNavigationPage) throws ServiceException {
        try {
            return productDao.queryProducts(page, maxResult, maxNavigationPage);
        } catch (DaoException e) {
            log.info("queryProducts" + e);
            throw new ServiceException(e.getKey(), e);
        }
    }

    @Override
    public PaginationResult<ProductInfo> queryProducts(int page, int maxResult, int maxNavigationPage, String likeName) throws ServiceException {
        try {
            return productDao.queryProducts(page, maxResult, maxNavigationPage, likeName);
        } catch (DaoException e) {
            log.info("queryProducts" + e);
            throw new ServiceException(e.getKey(), e);
        }
    }

    @Override
    public void save(ProductInfo productInfo) throws ServiceException {
        try {
            productDao.save(productInfo);
        } catch (DaoException e) {
            log.info("save" + e);
            throw new ServiceException(e.getKey(), e);
        }
    }
}
