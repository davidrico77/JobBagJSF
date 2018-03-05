package beans.helper;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

import beans.domain.Colonia;


@RequestScoped
@ManagedBean
//@Named
public class ColoniaHelper {

	public List<Colonia> getColonias() {
        List<Colonia> colonias = new ArrayList<>();
        long coloniaId = 1;
        Colonia colonia = new Colonia(coloniaId++, "Napoles", 3810);//no anteponer el cero, sino es un valor octal
        colonias.add(colonia);
        colonia = new Colonia(coloniaId++, "Polanco", 11530);
        colonias.add(colonia);
        colonia = new Colonia(coloniaId++, "Del Valle Centro", 3100);
        colonias.add(colonia);
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
