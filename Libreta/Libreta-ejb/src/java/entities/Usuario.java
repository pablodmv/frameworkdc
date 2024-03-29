/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import java.util.Date;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.JoinTable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


/**
 *
 * @author pablo
 */
@Entity
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length=80, nullable=false)
    private String nombre;

    @Column(length=80, nullable=false)
    private String apellido;

    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaNacimiento;

    //@JoinColumn(name="idCredencial")
    
//    @OneToOne(cascade=CascadeType.PERSIST)
//    @JoinColumn(name="idCredencial")
    @OneToOne(cascade=CascadeType.ALL)
    private Credenciales credencial;

//    @JoinColumn
//    @OneToMany
    
    @OneToMany(cascade=CascadeType.ALL)
    @JoinTable(name="USERCONTACT")
   private List<Contacto> contactos;

    public Usuario() {
        this.contactos=new ArrayList<Contacto>();
    }



     public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFechaNacimiento() {
      
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            return df.format(fechaNacimiento);
      
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Credenciales getCredencial() {
        return credencial;
    }

    public void setCredencial(Credenciales credencial) {
        this.credencial = credencial;
    }

    public List<Contacto> getContactos() {
        return contactos;
    }

    public void setContactos(Contacto contact) {
        this.contactos.add(contact);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Usuario[id=" + id + "]";
    }

}
