package fegyverek;

import fegyverek.Fegyok;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


@Entity
@NamedQueries({
        @NamedQuery(name = "Weapons.OsszesWeapons", query = "SELECT b FROM Weapons b"),
        @NamedQuery(name = "Weapons.getWeaponsById", query = "SELECT b FROM Weapons b WHERE b.id = :id")
        })
public class Weapons extends Fegyok implements Serializable {

    private String tar;

    public String getTar() {
        return tar;
    }

    public void setTar(String tar) {
        this.tar = tar;
    }       
       

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof Weapons)) {
            return false;
        }
        Weapons other = (Weapons) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fegyverek.Weapons[ id=" + id + " ]";
    }
    
}
