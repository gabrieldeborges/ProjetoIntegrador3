
package Objetos;

import java.time.LocalDate;


public class Operacao {
    
    public int iduser;
    public LocalDate data;
    public int idEquip;

    public Operacao() {
    }

    public Operacao(int idUser, LocalDate data, int idEquip) {
        this.idEquip = idEquip;
        this.data = data;
        this.iduser = idUser;
    }
    
    
}
