package com.controller;

import com.vo.OrderDetailInfo;
import com.vo.OrderInfo;
import com.vo.PaginationResult;
import com.vo.ProductInfo;
import com.services.OrderService;
import com.services.ProductService;
import com.services.exceptions.ServiceException;
import com.validators.ProductInfoValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Transactional
@EnableWebMvc
public class AdminController {

    private static final Logger log = Logger.getLogger(AdminController.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductInfoValidator productInfoValidator;

    @Autowired
    private ResourceBundleMessageSource messageSource;

    @InitBinder
    public void myInitBinder(WebDataBinder dataBinder) {
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        System.out.println("Target=" + target);
        if (target.getClass() == ProductInfo.class) {
            dataBinder.setValidator(productInfoValidator);
            dataBinder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
        }
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login(Model model) {
        return "login";
    }

    @RequestMapping(value = {"/accountInfo"}, method = RequestMethod.GET)
    public String accountInfo(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(userDetails.getPassword());
        System.out.println(userDetails.getUsername());
        System.out.println(userDetails.isEnabled());
        model.addAttribute("userDetails", userDetails);
        return "accountInfo";
    }

    @RequestMapping(value = {"/orderList"}, method = RequestMethod.GET)
    public String orderList(Model model, ModelMap modelMap,
                            @RequestParam(value = "page", defaultValue = "1") String pageStr) {
        int page = 1;
        try {
            page = Integer.parseInt(pageStr);
        } catch (Exception e) {
        }
        final int MAX_RESULT = 5;
        final int MAX_NAVIGATION_PAGE = 10;
        PaginationResult<OrderInfo> paginationResult
                = null;
        try {
            paginationResult = orderService.listOrderInfo(page, MAX_RESULT, MAX_NAVIGATION_PAGE);
        } catch (ServiceException e) {
            log.info("listOrderInfo :" + e);
            modelMap.addAttribute("msg", "error.method.listOrderInfo");
            return "/403";
        }
        model.addAttribute("paginationResult", paginationResult);
        return "orderList";
    }

    @RequestMapping(value = {"/product"}, method = RequestMethod.GET)
    public String product(Model model, ModelMap modelMap, @RequestParam(value = "code", defaultValue = "") String code) {
        ProductInfo productInfo = null;
        if (code != null && code.length() > 0) {
            try {
                productInfo = productService.findProductInfo(code);
            } catch (ServiceException e) {
                log.info("findProductInfo :" + e);
                modelMap.addAttribute("msg", "error.method.findProductInfo");
                return "/403";
            }
        }
        if (productInfo == null) {
            productInfo = new ProductInfo();
            productInfo.setNewProduct(true);
        }
        model.addAttribute("productForm", productInfo);
        return "product";
    }

    @RequestMapping(value = {"/product"}, method = RequestMethod.POST)
    @Transactional(propagation = Propagation.NEVER)
    public String productSave(Model model,
                              @ModelAttribute("productForm") @Validated ProductInfo productInfo,
                              BindingResult result,
                              final RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "product";
        }
        try {
            productService.save(productInfo);
        } catch (Exception e) {
            String message = e.getMessage();
            model.addAttribute("message", message);
            return "product";
        }
        return "redirect:/productList";
    }

    @RequestMapping(value = {"/order"}, method = RequestMethod.GET)
    public String orderView(Model model, ModelMap modelMap, @RequestParam("orderId") String orderId) {
        OrderInfo orderInfo = null;
        if (orderId != null) {
            try {
                orderInfo = this.orderService.getOrderInfo(orderId);
            } catch (ServiceException e) {
                log.info("getOrderInfo :" + e);
                modelMap.addAttribute("msg", "error.method.getOrderInfo");
                return "/403";
            }
        }
        if (orderInfo == null) {
            return "redirect:/orderList";
        }
        List<OrderDetailInfo> details = null;
        try {
            details = this.orderService.listOrderDetailInfos(orderId);
        } catch (ServiceException e) {
            log.info("listOrderDetailInfos :" + e);
            modelMap.addAttribute("msg", "error.method.listOrderDetailInfos");
            return "/403";
        }
        orderInfo.setDetails(details);
        model.addAttribute("orderInfo", orderInfo);
        return "order";
    }
}
