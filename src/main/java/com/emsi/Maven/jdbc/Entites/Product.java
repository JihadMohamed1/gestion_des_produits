package com.emsi.Maven.jdbc.Entites;

import java.io.Serializable;

public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nom;
	private double prix;
	private String description;
	private int quantite;
	private Categorie categorie;
	
	public Product() {
		super();
	}
	public Product( String nom, double prix, String description, int quantite, Categorie categorie) {
		super();
		this.nom = nom;
		this.prix = prix;
		this.description = description;
		this.quantite = quantite;
		this.categorie = categorie;
	}
	
	public Product(Integer id, String nom, double prix, String description, int quantite, Categorie categorie) {
		super();
		this.id = id;
		this.nom = nom;
		this.prix = prix;
		this.description = description;
		this.quantite = quantite;
		this.categorie = categorie;
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
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public Categorie getCategorie() {
		return categorie;
	}
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
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
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return nom + "," + prix + "," + description + ","+ quantite + "," + categorie.toString() ;
	}

}
