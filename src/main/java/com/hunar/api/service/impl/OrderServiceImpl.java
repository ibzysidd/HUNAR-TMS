package com.hunar.api.service.impl;

import com.hunar.api.bean.CustomerBean;
import com.hunar.api.bean.InvoiceBean;
import com.hunar.api.bean.OrderBean;
import com.hunar.api.constant.Constants;
import com.hunar.api.entity.Address;
import com.hunar.api.entity.CompanyEntity;
import com.hunar.api.entity.CustomerEntity;
import com.hunar.api.entity.Order;
import com.hunar.api.exceptionHandling.util.Errors;
import com.hunar.api.exceptionHandling.util.FmkException;
import com.hunar.api.repository.AddressRepository;
import com.hunar.api.repository.CompanyRepository;
import com.hunar.api.repository.CustomerRepository;
import com.hunar.api.repository.OrderRepository;
import com.hunar.api.service.CustomerService;
import com.hunar.api.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.Local;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class OrderServiceImpl implements OrderService {

    public static Logger logger = LogManager.getLogger(OrderServiceImpl.class);

    private static final String PREFIX = "ORD-";
    private static final String DATE_FORMAT = "yyyyMMdd";
    private final AtomicInteger counter = new AtomicInteger(1);  // Atomic counter for uniqueness
    private String currentDate = getCurrentDate();

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    CustomerRepository customerRepository;

//    @Value("${upload.dir}")
    private String uploadDir;

    @Override
    public OrderBean createOrder(OrderBean orderBean) throws FmkException, IOException {
        logger.info("Creating new order: " + orderBean.toString());
        Order orderEntity = new Order();
        BeanUtils.copyProperties(orderBean, orderEntity);
        orderEntity.setOrderNo(generateOrderNumber());
        orderEntity.setIdAddress(orderBean.getIdAddress());
        Address address = checkByAddressId(orderBean.getIdAddress());
        CustomerEntity customer = checkByCustomerId(orderBean.getIdCustomer());
        orderEntity.setCustomer(customer);
        orderEntity.setAddress(address);
        orderEntity.setIdCustomer(orderBean.getIdCustomer());

        // If there is an image file, store it
//        MultipartFile imageFile = orderBean.getImage();
//        if (imageFile != null && !imageFile.isEmpty()) {
//            String imagePath = storeImage(imageFile);
//            orderEntity.setImage(imagePath.getBytes()); // Store file path in the DB (if using file system)
//        }

        orderEntity.setOrderStatus(Constants.IN_PROGRESS);
       orderEntity= orderRepository.save(orderEntity);
        logger.info("Created new order successfully: " + orderBean.toString());
        OrderBean orderBean1 = new OrderBean();
        BeanUtils.copyProperties(orderEntity,orderBean1);
        return  orderBean1;
    }

//    private String storeImage(MultipartFile file) throws IOException {
//        // Create the directory if it doesn't exist
//        File directory = new File(uploadDir);
//        if (!directory.exists()) {
//            directory.mkdirs();
//        }
//
//        // Generate file name and path
//        String fileName = file.getOriginalFilename();
//        Path filePath = Paths.get(uploadDir, fileName);
//
//        // Copy the image to the directory
//        Files.copy(file.getInputStream(), filePath);
//
//        // Return the file path (this can be stored in the database)
//        return filePath.toString();
//    }


    private Address checkByAddressId(int idAddress) throws FmkException {
        Optional<Address> address = addressRepository.findById(idAddress);
        if (!address.isPresent()) {
            throw new FmkException("A1002","Invalid Address Id: "+String.valueOf(idAddress));
        }
        return address.get();
    }

    private CustomerEntity checkByCustomerId(int idCustomer) throws FmkException {
        Optional<CustomerEntity> customer = customerRepository.findById(idCustomer);
        if (!customer.isPresent()) {
            throw new FmkException("C1001", "Invalid customer Id: "+String.valueOf(idCustomer));
        }
        return customer.get();
    }

    private String generateOrderNumber() {
        String todayDate = getCurrentDate();

        // Reset the counter if the date has changed
        if (!todayDate.equals(currentDate)) {
            counter.set(1); // reset counter for new day
            currentDate = todayDate;
        }

        // Format the order number
        String orderNumber = String.format("%s%s-%04d", PREFIX, todayDate, counter.getAndIncrement());
        return orderNumber;
    }

    // Helper method to get current date in the desired format
    private String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(new Date()); // Use current date
    }

    @Override
    public OrderBean updateOrder(OrderBean orderBean) throws FmkException {
        if (orderBean != null && orderBean.getOrderId() != 0) {
            logger.info("Updating order: " + orderBean.getOrderId());
            Optional<Order> orderEntity = orderRepository.findById(orderBean.getOrderId());
            if (!orderEntity.isPresent()) {
                logger.info("Order does not exists with OrderName: " + orderBean.getOrderId());
                throw new FmkException("O1001","Order does not exists with Order Id: " + String.valueOf(orderBean.getOrderId()));
            }
            orderEntity.get().setOrderId(orderBean.getOrderId());
            orderEntity.get().setAmount(orderBean.getAmount());
            orderEntity.get().setBookingDate(orderBean.getBookingDate());
            orderEntity.get().setComments(orderBean.getComments());
            orderEntity.get().setDeliveryDate(orderBean.getDeliveryDate());
            orderEntity.get().setQuantity(orderBean.getQuantity());
            orderEntity.get().setWaist(orderBean.getWaist());
            orderEntity.get().setType(orderBean.getType());
            orderEntity.get().setShoulders(orderBean.getShoulders());
            orderEntity.get().setArm_hole(orderBean.getArm_hole());
            orderEntity.get().setBelly(orderBean.getBelly());
            orderEntity.get().setBiceps(orderBean.getBiceps());
            orderEntity.get().setBust(orderBean.getBust());
            orderEntity.get().setCalfs(orderBean.getCalfs());
            orderEntity.get().setDart_point(orderBean.getDart_point());
            orderEntity.get().setHip(orderBean.getHip());
            orderEntity.get().setKnees(orderBean.getKnees());
            orderEntity.get().setLength_of_pant(orderBean.getLength_of_pant());
            orderEntity.get().setLength_of_shirt(orderBean.getLength_of_shirt());
            orderEntity.get().setRound_bottom(orderBean.getRound_bottom());
            orderEntity.get().setSleeves_length_width(orderBean.getSleeves_length_width());
            orderEntity.get().setThighs(orderBean.getThighs());
            orderEntity.get().setUpper_bust(orderBean.getUpper_bust());
            orderEntity.get().setWaist_length(orderBean.getWaist_length());
            orderEntity.get().setWaist_of_pants(orderBean.getWaist_of_pants());
            orderEntity.get().setIdCustomer(orderBean.getIdCustomer());
            orderEntity.get().setIdAddress(orderBean.getIdAddress());
            orderEntity.get().setOrderStatus(Constants.CREATED);
           Order order = orderRepository.save(orderEntity.get());
            logger.info("Updated order successfully: " + order.getOrderId());
            OrderBean orderBean1 = new OrderBean();
            BeanUtils.copyProperties(order,orderBean1);
            return orderBean1;
        }
        return null;
    }


    @Override
    public List<OrderBean> getListOfAllOrders() throws FmkException {
        List<OrderBean> listOfAllOrdersBean = null;
        List<Order> listOfAllOrdersEntity = (List<Order>) orderRepository.findAll();
        if (!listOfAllOrdersEntity.isEmpty()) {
            listOfAllOrdersBean = new ArrayList<>();
            for (Order orderEntity : listOfAllOrdersEntity) {
                OrderBean orderBean = new OrderBean();
                BeanUtils.copyProperties(orderEntity, orderBean);
                listOfAllOrdersBean.add(orderBean);
            }
            return listOfAllOrdersBean;
        }
        else {
            return new ArrayList<>();
        }

    }

    @Override
    public OrderBean getOrderById(int idOrder) throws FmkException {
        if (idOrder <= 0) {
            throw new FmkException("O1001", "Order does not exists with Order Id: " + String.valueOf(idOrder));
        }
        Optional<Order> orderEntity = orderRepository.findById(idOrder);
        if (!orderEntity.isPresent()) {
            throw new FmkException("O1001","Order does not exists with Order Id: " + String.valueOf(idOrder));
        } else {
            OrderBean orderBean = new OrderBean();
            BeanUtils.copyProperties(orderEntity, orderBean);
            return orderBean;
        }
    }

    @Override
    public String deleteOrderById(int idOrder) throws FmkException {
        if (idOrder == 0) {
            logger.info("Invalid order ID: " + idOrder);
            throw new FmkException("O1002", "Invalid order ID: " + String.valueOf(idOrder));
        }
        orderRepository.deleteById(idOrder);
        return "Order deleted succesffuly";
    }

    @Override
    public List<CustomerBean> findBookingDate(LocalDate date) throws FmkException {
//        List<CustomerBean> customerBeans = null;
//        List<OrderBean> orderBeans = null;
//        List<CustomerEntity> customerEntities = null;
//        if (date == null) {
//            return customerService.getListOfAllCustomers();
//        } else {
//            customerBeans = new ArrayList<>();
//            Set<Integer> ids = new HashSet<>();
//            List<Order> orders = orderRepository.findbyBookingDate(date);
//            if (!orders.isEmpty()) {
//                for (Order order : orders) {
//                    ids.add(order.getIdCustomer());
//                  }
//                for (Integer id : ids){
//                    CustomerEntity customerEntity = customerRepository.findById(id).get();
//                    if (customerEntity!= null){
//                        customerEntities= new ArrayList<>();
//
//                    }
//                }
//                }
//            }

        return new ArrayList<>();
    }

    @Override
    public List<OrderBean> getListOfAllOrdersByCustomerId(int custId) throws FmkException {
        List<OrderBean> listOfAllOrdersBean = null;
        List<Order> listOfAllOrdersEntity = (List<Order>) orderRepository.findAllByIdCustomer(custId);
        if (!listOfAllOrdersEntity.isEmpty()) {
            listOfAllOrdersBean = new ArrayList<>();
            for (Order orderEntity : listOfAllOrdersEntity) {
                OrderBean orderBean = new OrderBean();
                BeanUtils.copyProperties(orderEntity, orderBean);
                listOfAllOrdersBean.add(orderBean);
            }
            return listOfAllOrdersBean;
        }
       return new ArrayList<>();
    }

    @Autowired
    CompanyRepository companyRepository;

    @Override
    public InvoiceBean getInvoiceDetails(String orderNo) {
        if (orderNo != null){
            Order order = orderRepository.findByOrderNo(orderNo);
            if (order != null){
                CustomerEntity customerEntity = customerRepository.findById(order.getIdCustomer()).get();
                if (customerEntity!=null){
                    CompanyEntity companyEntity = companyRepository.findAll().get(0);
                    InvoiceBean invoiceBean = new InvoiceBean();
                    invoiceBean.setOrderNo(orderNo);
                    invoiceBean.setItem(order.getType());
                    invoiceBean.setTodaysDate(LocalDate.now());
                    invoiceBean.setAmount(String.valueOf(order.getAmount()));
                    invoiceBean.setTotalamt(String.valueOf(order.getTotalAmt()));
                    invoiceBean.setQty(String.valueOf(order.getQuantity()));
                    invoiceBean.setSerialNo(1);
                    invoiceBean.setCustomerName(customerEntity.getCustomerName());
                    invoiceBean.setCompanyAddress(companyEntity.getCompanyAddress());
                    invoiceBean.setCompanyMblNo(companyEntity.getCompanyMblNo());
                    invoiceBean.setCompanyName(companyEntity.getCompanyName());
                    invoiceBean.setCity(companyEntity.getCity());
                    invoiceBean.setPincode(companyEntity.getPincode());
                    invoiceBean.setBookingDate(order.getBookingDate());
                    return invoiceBean;

                }
         }
        }
        return null;
    }

    @Override
    public OrderBean orderDelivered(int idOrder) {
        Optional<Order> order = orderRepository.findById(idOrder);
        if (order.isPresent()){
            order.get().setOrderStatus(Constants.DELIVERED);
           Order order1 = orderRepository.save(order.get());
           OrderBean orderBean = new OrderBean();
           BeanUtils.copyProperties(order1, orderBean);
           return  orderBean;
        }
        return  null;
    }

    @Override
    public OrderBean orderCompleted(int idOrder) {
        Optional<Order> order = orderRepository.findById(idOrder);
        if (order.isPresent()){
            order.get().setOrderStatus(Constants.COMPLETED);
            Order order1 = orderRepository.save(order.get());
            OrderBean orderBean = new OrderBean();
            BeanUtils.copyProperties(order1, orderBean);
            return  orderBean;
        }
        return  null;
    }
}
