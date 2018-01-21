package th.net.cat.epis.dto;

import java.io.Serializable;

/**
 * Created by imake on 26/02/2017
 */
public class OrderRepeat implements Serializable{
    private static final long serialVersionUID = -4643023563665968064L;
    private String orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
