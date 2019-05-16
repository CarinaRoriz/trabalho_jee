package br.pucminas.aulapratica.jee.trabalho_jee.business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.pucminas.aulapratica.jee.trabalho_jee.entity.FornecedorEntity;
import br.pucminas.aulapratica.jee.trabalho_jee.exception.CnpjJaExistenteException;
import br.pucminas.aulapratica.jee.trabalho_jee.exception.CpfJaExistenteException;
import br.pucminas.aulapratica.jee.trabalho_jee.repository.FornecedorRepository;
import br.pucminas.aulapratica.jee.trabalho_jee.resource.FornecedorResource;

@Stateless
public class FornecedorBusiness {

	@Inject
	private FornecedorRepository fornecedorRepository;
	
	public void salvarFornecedor(FornecedorResource fornecedorResource) throws CpfJaExistenteException{
		FornecedorEntity fornecedor = convertToEntity(fornecedorResource);
		
		List<FornecedorEntity> listaFornecedorsMesmoCnpj = fornecedorRepository.buscarFornecedorPorCnpj(fornecedorResource.getCnpj());
		if(listaFornecedorsMesmoCnpj != null && listaFornecedorsMesmoCnpj.size() > 0){
			throw new CnpjJaExistenteException();
		}
		
		fornecedorRepository.salvar(fornecedor);
	}
	
	public List<FornecedorResource> listarFornecedores(){
		List<FornecedorResource> listaFornecedors = new ArrayList<>();
		List<FornecedorEntity> todosFornecedors = fornecedorRepository.getTodosFornecedores();
		for (FornecedorEntity fornecedorEntity : todosFornecedors) {
			FornecedorResource resource = new FornecedorResource();
			resource = convertToResource(fornecedorEntity);
			listaFornecedors.add(resource);
		}
		return listaFornecedors;
	}
	
	private FornecedorEntity convertToEntity(FornecedorResource resource){
		FornecedorEntity entity = new FornecedorEntity();
		entity.setCnpj(resource.getCnpj());
		entity.setEndereco(resource.getEndereco());
		entity.setInscricaoMunicipal(resource.getInscricaoMunicipal());
		entity.setEmail(resource.getEmail());
		entity.setRazaoSocial(resource.getRazaoSocial());
		entity.setId(resource.getId());
		return entity;
	}
	
	private FornecedorResource convertToResource(FornecedorEntity entity){
		FornecedorResource resource = new FornecedorResource();
		resource.setCnpj(entity.getCnpj());
		resource.setEndereco(entity.getEndereco());
		resource.setInscricaoMunicipal(entity.getInscricaoMunicipal());
		resource.setEmail(entity.getEmail());
		resource.setRazaoSocial(entity.getRazaoSocial());
		resource.setId(entity.getId());
		return resource;
	}
}
