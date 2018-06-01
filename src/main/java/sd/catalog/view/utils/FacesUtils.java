package sd.catalog.view.utils;

import org.omnifaces.util.Ajax;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesUtils {

    public static void addMessageSaveSuccess() {
        addInfoMessage("Saved!");
    }

    public static void addMessageSuccess(String message) {
        addInfoMessage(message);
    }

    public static void addInfoMessage(String message) {
        FacesMessage infoMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, message, null);
        FacesContext.getCurrentInstance().addMessage(message, infoMessage);
    }

    public static void reloadPage() {
        Ajax.oncomplete("location.reload()");
    }
}
