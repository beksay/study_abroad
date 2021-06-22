package org.infosystema.iselect.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 * @author Kuttubek Aidaraliev
 */
public abstract class Conversational implements Serializable {

    private static final long serialVersionUID = 282062606859800901L;

    @Inject
    private Conversation conversation;
    private Object data;

    @PostConstruct
    public void initialize() {
        if (conversation.isTransient()) {
            conversation.begin();
            conversation.setTimeout(1200000);
        }
        if (FacesContext.getCurrentInstance().getExternalContext().getFlash().get("filtersData") != null)
            data = FacesContext.getCurrentInstance().getExternalContext().getFlash().get("filtersData");
    }

    public void closeConversation() {
        //System.out.println("closeConversation = " + FacesContext.getCurrentInstance().getExternalContext().getFlash().get("filtersData"));
        if (!conversation.isTransient()) conversation.end();
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("filtersData", data);
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public String getId() {
        return conversation.getId();
    }
}
