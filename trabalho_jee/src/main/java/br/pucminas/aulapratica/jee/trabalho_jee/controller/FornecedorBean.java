package br.pucminas.aulapratica.jee.trabalho_jee.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.pucminas.aulapratica.jee.trabalho_jee.business.FornecedorBusiness;
import br.pucminas.aulapratica.jee.trabalho_jee.exception.CnpjJaExistenteException;
import br.pucminas.aulapratica.jee.trabalho_jee.resource.FornecedorResource;

@Model
public class FornecedorBean {

	@EJB
	private FornecedorBusiness fornecedorBusiness;

	private FornecedorResource fornecedorResource = new FornecedorResource();
	private List<FornecedorResource> fornecedores = new ArrayList<>();

	public List<FornecedorResource> getFornecedores() {
		return fornecedores;
	}

	public FornecedorResource getFornecedorResource() {
		return fornecedorResource;
	}

	public void setFornecedorResource(FornecedorResource fornecedorResource) {
		this.fornecedorResource = fornecedorResource;
	}

	public String listarFornecedores() {
		fornecedores = fornecedorBusiness.listarFornecedores();
		return "fornecedorListagem";

	}
	
	public String fornecedorCadastro() {
		return "fornecedorCadastro";
	}

	public void salvarFornecedor() {
		try {
			fornecedorBusiness.salvarFornecedor(fornecedorResource);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Fornecedor inserido com sucesso!", "");
			FacesContext.getCurrentInstance().addMessage("formFornecedor:messages", message);
		} catch (CnpjJaExistenteException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
			FacesContext.getCurrentInstance().addMessage("formFornecedor:messages", message);
		}
	}

}
