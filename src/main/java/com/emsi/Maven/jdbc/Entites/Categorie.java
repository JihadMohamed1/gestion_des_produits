package com.emsi.Maven.jdbc.Entites;

import java.io.Serializable;

public class Categorie implements Serializable  {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nom;
	
	public Categorie() {
		super();
	}
	public Categorie(String nom) {
		super();
		this.nom = nom;
	}
	public Categorie(Integer id,String nom) {
		super();
		this.id=id;
		this.nom = nom;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categorie other = (Categorie) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return   id + "," + nom ;
	}
	

}
