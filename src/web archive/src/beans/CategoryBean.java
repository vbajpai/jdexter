package beans;

import java.util.ArrayList;

public class CategoryBean{

    private String categoryname;
    private ArrayList<CategoryBean> child;

    public ArrayList<CategoryBean> getChild() {
        return child;
    }

    public void setChild(ArrayList<CategoryBean> child) {
        this.child = child;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

}