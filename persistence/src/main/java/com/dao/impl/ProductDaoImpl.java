package com.dao.impl;

import com.dao.ProductDao;
import com.entity.Product;
import com.vo.PaginationResult;
import com.vo.ProductInfo;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 * Class DAO for Product.
 */
@Repository
public class ProductDaoImpl implements ProductDao {

    private static final Logger log = Logger.getLogger(ProductDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Product findProduct(String code) {
        log.info("findProduct :" + code);
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        Criteria crit = session.createCriteria(Product.class);
        crit.add(Restrictions.eq("code", code));
        return (Product) crit.uniqueResult();
    }

    @Override
    public ProductInfo findProductInfo(String code) {
        log.info("findProductInfo :" + code);
        Product product = this.findProduct(code);
        if (product == null) {
            return null;
        }
        return new ProductInfo(product.getCode(), product.getName(), product.getPrice());
    }

    @Override
    public void save(Product product) {
        log.info("save :");
        sessionFactory.getCurrentSession().saveOrUpdate(product);
    }

    @Override
    public PaginationResult<ProductInfo> queryProducts(int page, int maxResult, int maxNavigationPage, String likeName) {
        log.info("queryProducts :");
        String sql = "Select new " + ProductInfo.class.getName()
                + "(p.code, p.name, p.price) " + " from "
                + Product.class.getName() + " p ";
        if (likeName != null && likeName.length() > 0) {
            sql += " Where lower(p.name) like :likeName ";
        }
        sql += " order by p.createDate desc ";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(sql);
        if (likeName != null && likeName.length() > 0) {
            query.setParameter("likeName", "%" + likeName.toLowerCase() + "%");
        }
        return new PaginationResult<ProductInfo>(query, page, maxResult, maxNavigationPage);
    }

    @Override
    public PaginationResult<ProductInfo> queryProducts(int page, int maxResult, int maxNavigationPage) {
        log.info("queryProducts");
        return queryProducts(page, maxResult, maxNavigationPage, null);
    }
}
