package com.services.servicesImpl;

import com.dao.OrderDao;
import com.dao.exceptions.DaoException;
import com.model.CartInfo;
import com.model.OrderDetailInfo;
import com.model.OrderInfo;
import com.model.PaginationResult;
import com.services.OrderService;
import com.services.exceptions.ServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private static final Logger log = Logger.getLogger(ProductServiceImpl.class);

    @Autowired
    @Qualifier("orderDaoImpl")
    private OrderDao orderDao;

    @Override
    public void saveOrder(CartInfo cartInfo) throws ServiceException {
        try {
            orderDao.saveOrder(cartInfo);
        } catch (DaoException e) {
            log.info("saveOrder" + e);
            throw new ServiceException(e.getKey(), e);
        }
    }

    @Override
    public PaginationResult<OrderInfo> listOrderInfo(int page, int maxResult, int maxNavigationPage) throws ServiceException {
        try {
            return orderDao.listOrderInfo(page, maxResult, maxNavigationPage);
        } catch (DaoException e) {
            log.info("listOrderInfo" + e);
            throw new ServiceException(e.getKey(), e);
        }
    }

    @Override
    public OrderInfo getOrderInfo(String orderId) throws ServiceException {
        try {
            return orderDao.getOrderInfo(orderId);
        } catch (DaoException e) {
            log.info("getOrderInfo" + e);
            throw new ServiceException(e.getKey(), e);
        }
    }

    @Override
    public List<OrderDetailInfo> listOrderDetailInfos(String orderId) throws ServiceException {
        try {
            return orderDao.listOrderDetailInfos(orderId);
        } catch (DaoException e) {
            log.info("listOrderDetailInfos" + e);
            throw new ServiceException(e.getKey(), e);
        }
    }
}
