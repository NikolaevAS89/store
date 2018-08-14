package ru.timestop.objects;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Categories of Product({@link Prod})
 *
 * @author NikolaevAS
 */
@Entity
@Table(name = "Cat")
@NamedQuery(name = "Cat.getAll", query = "select count(c) as prod_count, c._id as id, c._name as name from Cat as c group by c._id, c._name")
public class Cat implements Serializable {
    private static final long serialVersionUID = -7884498378960092700L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int _id; //
    @Column(name = "name")
    private String _name; //
    @OneToMany(mappedBy = "_cat")
    private Set<Prod> prods;

    public Cat() {
    }

    public Cat(String name) {
        setName(name);
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        _id = id;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(100);
        sb.append(Cat.class.getName());
        sb.append("{ 'id':'");
        sb.append(_id);
        sb.append("','name':'");
        sb.append(_name);
        sb.append("'}");
        return sb.toString();
    }
}