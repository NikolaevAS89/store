package ru.timestop.objects;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Simple Product
 * @author NikolaevAS
 *
 */
@Entity
@Table(name="Prod")
@NamedQueries({
@NamedQuery(name = "Prod.getAll", query = "select p from Prod p"),
@NamedQuery(name = "Prod.findProducts", query = "select p  "
		                                      + "from Prod p inner join p._category c "
		                                      + "where upper(p._name) like upper(:prod_name) "
		                                      + "  and upper(c._name) like upper(:cat_name) "
		                                      + "  and p._price between :min_price "
		                                      + "                   and :max_price "
		                                      + "order by c._name, p._name")
})
public class Prod implements Serializable{
	private static final long serialVersionUID = -7884498378960092700L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int _id; //
	@Column(name="name", unique = true)
	private String _name; //
	@Column(name="price", columnDefinition="Decimal(10,2) default '0.00'")
	private Double _price; //	
	@ManyToOne
	@JoinColumn(name = "cat_id")
	private Category _category; //
	
	public Prod(){}
	
	public Prod(Category category, String name, double price){
		this.setCat(category);
		this.setName(name);
		this.setPrice(price);
	}
	
	public int getId() {
		return _id;
	}
	public void setId(int id) {
		this._id = id;
	}
	public Category getCat() {
		return _category;
	}
	public void setCat(Category category) {
		this._category = category;
	}
	public String getName() {
		return _name;
	}
	public void setName(String name) {
		this._name = name;
	}
	public double getPrice() {
		return _price;
	}
	public void setPrice(double price) {
		this._price = price;
	}	
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder(150);
		sb.append(Prod.class.getName());
		sb.append("{ 'id':'");
		sb.append(_id);
		sb.append("','name':'");
		sb.append(_name);
		sb.append("','price':'");
		sb.append(_price);
		sb.append("','cat':'");
		sb.append(_category.toString());
		sb.append("'}");
		return sb.toString();
	}
}
