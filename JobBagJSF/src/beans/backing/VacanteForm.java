package beans.backing;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIInput;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import beans.model.Candidato;

@RequestScoped
@ManagedBean
//@Named
public class VacanteForm {

    Logger log = LogManager.getRootLogger();

    //@Inject
    @ManagedProperty(value = "#{candidato}")
    private Candidato candidato;

    public VacanteForm() {
        log.info("Creando objeto VacanteForm");
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    //Metodo de flujo de control
    public String enviar() {

        System.out.println("enviar() Nombre=" + this.candidato.getNombre());
        System.out.println("enviar() Apellido=" + this.candidato.getApellido());
        System.out.println("enviar() Sueldo deseado" + this.candidato.getSueldoDeseado());

        if (this.candidato.getNombre().equals("Juan")) {

            if (this.candidato.getApellido().equals("Perez")) {
                String msg = "Gracias, pero Juan Perez ya trabaja con nosotros.";
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
                FacesContext facesContext = FacesContext.getCurrentInstance();
                String clientId = null; //Este es un mensaje global
                facesContext.addMessage(clientId, facesMessage);
                return "index";
            }
            return "exito";//exito.xhtml
        } else {
            return "fallo"; //fallo.xhtml
        }
    }
    
    //Metodo de tipo Value Change Listener
    public void codigoPostalListener(ValueChangeEvent valueChangeEvent) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        UIViewRoot uiViewRoot = facesContext.getViewRoot();
        String newCodigoPostal = (String) valueChangeEvent.getNewValue();
        if ("03810".equals(newCodigoPostal)) {
            log.info("Modificamos los valores de colonia y ciudad dinamicamente con ValueChangeListener");
            //Utilizamos el nombre del form de index.xhtml para encontrar el componente
            UIInput coloniaInputText = (UIInput) uiViewRoot.findComponent("vacanteForm:colonia");
            String colonia = "Napoles";
            coloniaInputText.setValue(colonia);
            coloniaInputText.setSubmittedValue(colonia);

            UIInput ciudadInputText = (UIInput) uiViewRoot.findComponent("vacanteForm:ciudad");
            String ciudad = "Ciudad de Mexico";
            ciudadInputText.setValue(ciudad);
            ciudadInputText.setSubmittedValue(ciudad);

            facesContext.renderResponse();
        }
    }

}
