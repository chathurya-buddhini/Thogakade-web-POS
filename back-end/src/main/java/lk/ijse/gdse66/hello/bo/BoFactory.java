package lk.ijse.gdse66.hello.bo;


import lk.ijse.gdse66.hello.bo.custom.impl.CustomerBoImpl;
import lk.ijse.gdse66.hello.bo.custom.impl.ItemBoImpl;
import lk.ijse.gdse66.hello.bo.custom.impl.OrderDetailBoImpl;
import lk.ijse.gdse66.hello.bo.custom.impl.PurchaseOrderBoImpl;

public class BoFactory {
    private static BoFactory boFactory;

    private BoFactory() {

    }

    public static BoFactory getBoFactory() {
        return (boFactory == null) ? boFactory = new BoFactory() : boFactory;
    }

    public enum BOTypes {
        CUSTOMER, ITEM, PURCHASE_ORDER,Detail_BO
    }

    public <T extends SuperBo> T getBO(BOTypes boTypes) {
        switch (boTypes) {
            case CUSTOMER:
                return (T) new CustomerBoImpl();
            case ITEM:
                return (T) new ItemBoImpl();
            case PURCHASE_ORDER:
                return (T) new PurchaseOrderBoImpl();
            case Detail_BO:
                return (T) new OrderDetailBoImpl();
            default:
                return null;
        }
    }
}
