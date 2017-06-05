package com.dao.impl;

import com.dao.OrderDao;
import com.entity.Order;
import com.entity.OrderDetail;
import com.vo.OrderDetailInfo;
import com.vo.OrderInfo;
import com.vo.PaginationResult;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Class DAO for orders.
 */
@Repository
public class OrderDaoImpl implements OrderDao {

    private static final Logger log = Logger.getLogger(OrderDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public int getMaxOrderNum() {
        log.info("getMaxOrderNum :");
        String sql = "Select max(o.orderNum) from " + Order.class.getName() + " o ";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(sql);
        Integer value = (Integer) query.uniqueResult();
        if (value == null) {
            return 0;
        }
        return value;
    }

    @Override
    public void saveOrder(Order order) {
        log.info("saveOrder :");
        Session session = sessionFactory.getCurrentSession();
        session.persist(order);
    }

    @Override
    public void saveOrderDetail(OrderDetail detail) {
        log.info("saveOrderDetail :");
        Session session = sessionFactory.getCurrentSession();
        session.persist(detail);
    }

    @Override
    public PaginationResult<OrderInfo> listOrderInfo(int page, int maxResult, int maxNavigationPage) {
        log.info("listOrderInfo :");
        String sql = "Select new " + OrderInfo.class.getName()
                + "(ord.id, ord.orderDate, ord.orderNum, ord.amount, "
                + " ord.customerName, ord.customerAddress, ord.customerEmail, ord.customerPhone) " + " from "
                + Order.class.getName() + " ord "
                + " order by ord.orderNum desc";
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery(sql);
        return new PaginationResult<OrderInfo>(query, page, maxResult, maxNavigationPage);
    }

    public Order findOrder(String orderId) {
        log.info("findOrder :" + orderId);
        Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(Order.class);
        crit.add(Restrictions.eq("id", orderId));
        return (Order) crit.uniqueResult();
    }

    @Override
    public OrderInfo getOrderInfo(String orderId) {
        log.info("getOrderInfo :" + orderId);
        Order order = this.findOrder(orderId);
        if (order == null) {
            return null;
        }
        return new OrderInfo(order.getId(), order.getOrderDate(),
                order.getOrderNum(), order.getAmount(), order.getCustomerName(),
                order.getCustomerAddress(), order.getCustomerEmail(), order.getCustomerPhone());
    }

    @Override
    public List<OrderDetailInfo> listOrderDetailInfos(String orderId) {
        log.info("listOrderDetailInfos :" + orderId);
        String sql = "Select new " + OrderDetailInfo.class.getName()
                + "(d.id, d.product.code, d.product.name , d.quanity,d.price,d.amount) "
                + " from " + OrderDetail.class.getName() + " d "
                + " where d.order.id = :orderId ";
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery(sql);
        query.setParameter("orderId", orderId);
        return query.list();
    }
}
