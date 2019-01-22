package lk.ijsc.dep.fx.Util;

import lk.ijsc.dep.fx.Model.Customer;
import lk.ijsc.dep.fx.Model.Items;
import lk.ijsc.dep.fx.Model.Order;
import lk.ijsc.dep.fx.Model.OrderDetail;

import java.time.LocalDate;
import java.util.ArrayList;

public class ManageOrders {
    private static ArrayList<Order> ordersDB = new ArrayList<>();

    public static ArrayList<Order> getOrdersDB(){
        return ordersDB;
    }

    public static void setOrdersDB(ArrayList<Order> orders){
        ordersDB  = orders;
    }

    // Dummy

    static{
        ArrayList<OrderDetail> orderDetails = new ArrayList<>();
        orderDetails.add(new OrderDetail("I001","Mouse",10,250));
        orderDetails.add(new OrderDetail("I002","Keyboard",10,350));
        ordersDB.add(new Order("1", LocalDate.now(),"C001",orderDetails));
    }

    public static String generateOrderId(){
        return ordersDB.size() + 1 + "";
    }

    public static void createOrder(Order order){
        ordersDB.add(order);
        for (OrderDetail orderDetail : order.getOrderDetails()) {
            Items item = ManageItems.findItem(orderDetail.getCode());
            item.setQtyOnHand(item.getQtyOnHand() - orderDetail.getQty());
        }
    }

    public static Order findOrder(String orderId){
        for (Order order : ordersDB) {
            if (order.getId().equals(orderId)){
                return order;
            }
        }
        return null;
    }

    }