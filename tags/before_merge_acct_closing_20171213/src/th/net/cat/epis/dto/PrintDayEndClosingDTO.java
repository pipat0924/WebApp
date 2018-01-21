package th.net.cat.epis.dto;

import th.net.cat.epis.entity.EmpClosing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nastanda on 6/2/2017 AD.
 */
public class PrintDayEndClosingDTO {
    private List<EmpClosing> empClosings = new ArrayList<>();

    public List<EmpClosing> getEmpClosings() {
        return empClosings;
    }

    public void setEmpClosings(List<EmpClosing> empClosings) {
        this.empClosings = empClosings;
    }
}
