package org.pahappa.systems.views;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;

@ManagedBean(name = "navigationController", eager = true)
@RequestScoped
public class NavigationController implements Serializable {
    public String home() {
        return "index.xhtml";
    }

    public String dashboard() {
        return "dashboard.xhtml";
    }

    public String create() {
        return "report.xhtml";
    }

    public String view() {
        return "viewIncident.xhtml";
    }

    public String edit() {
        return "update.xhtml";
    }

//    public String delete() {
//        return "pages/";
//    }
}
