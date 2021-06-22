package org.infosystema.study_abroad.beans;

public class ApplicationCheck {

    private String detail;
    private Integer count;

    public ApplicationCheck(String detail) {
        this.detail = detail;
    }

    public ApplicationCheck(Integer count) {
        this.count = count;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "ApplicationCheck [detail=" + detail + "]";
    }

}
