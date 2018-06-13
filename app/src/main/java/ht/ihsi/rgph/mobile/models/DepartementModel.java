package ht.ihsi.rgph.mobile.models;

/**
 * Created by ajordany on 3/21/2016.
 */
public class DepartementModel extends BaseModel {

    private String DeptId;
    private String DeptNom;

    public DepartementModel() {
    }

    //region DepartementModel getters and setters

    public String getDeptId() {
        return DeptId;
    }

    public void setDeptId(String deptId) {
        DeptId = deptId;
    }

    public String getDeptNom() {
        return DeptNom;
    }

    public void setDeptNom(String deptNom) {
        DeptNom = deptNom;
    }

    //endregion
}
