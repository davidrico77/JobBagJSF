package beans.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import beans.domain.Colonia;

@Stateless
public class ColoniaService {

	@PersistenceContext
    EntityManager em;

    @SuppressWarnings("unchecked")
	public List<Colonia> findAll() {
        return em.createNamedQuery("Colonia.findAll").getResultList();
    }

}
