package org.kgsd.esbase.controller;

import com.google.gson.Gson;
import com.google.inject.Inject;
import org.kgsd.esbase.exception.ManagerException;
import org.kgsd.esbase.model.IndexEntry;
import org.kgsd.esbase.service.ManagerService;
import ro.pippo.controller.Controller;
import ro.pippo.controller.DELETE;
import ro.pippo.controller.GET;
import ro.pippo.controller.POST;
import ro.pippo.core.HttpConstants;

import java.util.List;

import static org.kgsd.esbase.common.Constants.INDEX;

public class AdminController extends Controller {
    private ManagerService managerService;

    @Inject
    public AdminController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @POST("/api/admin/index/{index}")
    public void createIndex() {
        String index = getRequest().getParameter(INDEX).toString();
        try {
            managerService.createIndex(index);
            getResponse().status(HttpConstants.StatusCode.OK);
            getResponse().send("Created index successfully!!");
        } catch (ManagerException e) {
            getResponse().status(HttpConstants.StatusCode.INTERNAL_ERROR);
            getResponse().send("Create index failed!!");
        }
    }

    @DELETE("/api/admin/index/{index}")
    public void deleteIndex() {
        String index = getRequest().getParameter(INDEX).toString();
        try {
            managerService.deleteIndex(index);
            getResponse().status(HttpConstants.StatusCode.OK);
            getResponse().send("Deleted index successfully");
        } catch (ManagerException e) {
            getResponse().status(HttpConstants.StatusCode.INTERNAL_ERROR);
            getResponse().send("Delete index failed!!");
        }
    }

    @GET("/api/admin/indices")
    public void getAllIndices() {
        try {
            List<IndexEntry> indexEntries = managerService.getAllIndices();
            getResponse().status(HttpConstants.StatusCode.OK);
            getResponse().json().send(new Gson().toJson(indexEntries));
        } catch (ManagerException e) {
            getResponse().send(HttpConstants.StatusCode.INTERNAL_ERROR);
            getResponse().send("List indices failed!!");
        }
    }

}
