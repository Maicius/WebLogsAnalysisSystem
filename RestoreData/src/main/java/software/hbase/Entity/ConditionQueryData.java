package software.hbase.Entity;

import java.util.ArrayList;
import java.util.List;

public class ConditionQueryData {
    private String dateBegin;
    private List<String> courtList = new ArrayList<>();

    public String getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(String dateBegin) {
        this.dateBegin = dateBegin;
    }

    public List<String> getCourtList() {
        return courtList;
    }

    public void setCourtList(List<String> courtList) {
        this.courtList = courtList;
    }
}
