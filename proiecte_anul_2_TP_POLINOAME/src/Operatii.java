public interface Operatii {
    /* Interfata specifica operatiile ce trebuie suportate de orioe polinom */

    Polinom adunare(Polinom b);
    Polinom scadere(Polinom b);
    Polinom derivare();
    Polinom integrare();
    Polinom inmultire(Polinom a);
    Polinom impartire(Polinom b);
}
