/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.utils;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@ViewScoped
public class FileUploadView implements Serializable {

    private static final long serialVersionUID = 4352236420460919694L;

    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage("Ok", "Fichero " + event.getFile().getFileName() + " subido correctamente.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
