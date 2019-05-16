package br.pucminas.aulapratica.jee.trabalho_jee.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.pucminas.aulapratica.jee.trabalho_jee.entity.FornecedorEntity;

public class FornecedorRepository {

	@PersistenceContext
	private EntityManager em;

	public EntityManager getEm() {
		return em;
	}

	public FornecedorEntity salvar(FornecedorEntity fornecedor) {
		em.persist(fornecedor);
		return fornecedor;
	}

	public List<FornecedorEntity> getTodosFornecedores() {
		List<FornecedorEntity> resultado = em.createQuery("SELECT f FROM FornecedorEntity f", FornecedorEntity.class)
				.getResultList();
		return resultado;
	}

	public List<FornecedorEntity> buscarFornecedorPorCnpj(String cnpj) {
		return em.createQuery("SELECT f FROM FornecedorEntity f WHERE f.cnpj = :cnpj", FornecedorEntity.class)
				.setParameter("cnpj", cnpj).getResultList();
	}

}
