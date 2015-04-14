/*
 * MedCEP - A powerful tool for mimport javax.persistence.*;
 * import javax.persistence.Entity;
 * 
 * import org.hibernate.annotations.*;
 * import org.openxava.annotations.*;
 * e software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/lgpl.html>.
 */
package org.medcep.model.processo;

import javax.persistence.*;

import org.openxava.annotations.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Views({
	@View(members = "nome; descricao"),
	@View(name = "Simple", members = "nome")
})
@Tabs({
	@Tab(properties = "nome, descricao", defaultOrder = "${nome} asc")
})
public class Procedimento
{

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    @Hidden
    private Integer id;

    public Integer getId()
    {
	return id;
    }

    public void setId(Integer id)
    {
	this.id = id;
    }

    @Column(length = 500, unique = true)
    @Required
    private String nome;

    @Stereotype("TEXT_AREA")
    @Column(columnDefinition = "TEXT")
    private String descricao;

    public String getNome()
    {
	return nome;
    }

    public void setNome(String nome)
    {
	this.nome = nome;
    }

    public String getDescricao()
    {
	return descricao;
    }

    public void setDescricao(String descricao)
    {
	this.descricao = descricao;
    }

}
