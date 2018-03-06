package beans.helper;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import beans.domain.Colonia;
import beans.service.ColoniaService;


@RequestScoped
@ManagedBean
//@Named
public class ColoniaHelper {

	@Inject
	//@ManagedProperty(value = "#{coloniaService}")
	ColoniaService coloniaService;
	
	Logger log = LogManager.getRootLogger();
	
	public List<Colonia> getColonias() {
        List<Colonia> colonias = coloniaService.findAll();
        log.info("Recuperamos el listado de personas del EJB");
        return colonias;
    }

    public long getColoniaIdPorNombre(String nombreColonia) {
        long coloniaId = 0;
        List<Colonia> colonias = getColonias();//Lista de colonias
        for (Colonia colonia : colonias) {
            if (colonia.getNombreColonia().equals(nombreColonia)) {
                coloniaId = colonia.getColoniaId();
                break;
            }
        }
        return coloniaId;
    }

    public long getColoniaIdPorCP(long codigoPostal) {
        long coloniaId = 0;
        List<Colonia> colonias = getColonias();
        for (Colonia colonia : colonias) {
            if (colonia.getCodigoPostal() == codigoPostal) {
                coloniaId = colonia.getColoniaId();
                break;
            }
        }
        return coloniaId;
    }

    public List<SelectItem> getSelectItems() {
        List<SelectItem> selectItems;
        selectItems = new ArrayList<>();
        List<Colonia> colonias = getColonias();
        for (Colonia colonia : colonias) {
            SelectItem selectItem = new SelectItem(colonia.getColoniaId(),
                    colonia.getNombreColonia());
            selectItems.add(selectItem);
        }
        return selectItems;
    }
	
}
