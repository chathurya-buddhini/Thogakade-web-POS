package lk.ijse.gdse66.hello.dao;


import lk.ijse.gdse66.hello.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getDAOFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        CUSTOMER,ITEM,ORDER,ORDER_DETAILS,QUERY

    }

    public <T extends SuperDAO> T getDAO(DAOTypes res) {
        switch (res) {
            case CUSTOMER:
                return (T) new CustomerDaoImpl();
            case ITEM:
                return (T) new ItemDaoImpl();
            case ORDER:
                return (T) new OrderDaoImpl();
            case ORDER_DETAILS:
                return (T) new OrderDetailDaoImpl();
            case QUERY:
                return (T) new QueryDaoImpl();

            default:
                return null;
        }
    }


}
