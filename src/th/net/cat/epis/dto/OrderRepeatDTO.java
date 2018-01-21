package th.net.cat.epis.dto;

/**
 * Created by imake on 26/02/2017
 */
public class OrderRepeatDTO extends CommonStatus<OrderRepeat> {
    private th.net.cat.epis.dto.OrderRepeat orderRepeat;

    public OrderRepeat getOrderRepeat() {
        return orderRepeat;
    }

    public void setOrderRepeat(OrderRepeat orderRepeat) {
        this.orderRepeat = orderRepeat;
    }
}
