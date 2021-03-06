package org.openxava.mestrado.model.MedicaoDeSoftware.AnaliseDeMedicao;

import java.util.*;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import org.openxava.annotations.*;

//@Entity
/*@Views({
	@View (members="nome, versao, data; planoDeMedicaoDaOrganizacao; projeto; descricao; recursoHumano; objetivos { objetivoEstrategico; objetivoDeSoftware; objetivoDeMedicao; necessidadeDeInformacao; } MedidasDoPlano { medidaPlanoDeMedicao }"),
	@View(name="Simple", members="nome")
})*/
/*@Tabs({
	@Tab(properties="nome, versao")	
})*/
public class Conclusao {
 
	@Id @GeneratedValue(generator="system-uuid") @Hidden
	@GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id; 
	
	@Stereotype("TEXT_AREA") 
	@Column(columnDefinition="TEXT")
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	 
	//private Collection<CriterioDeDecisao > criterioDeDecisao ;
	 
	//private Collection<AnaliseDeMedicao> analiseDeMedicao;
	
	
}
 
