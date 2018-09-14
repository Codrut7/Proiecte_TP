import org.junit.jupiter.api.Test;

class MyClassTest {
    @Test
    public void testAdunare() {
        Monom a = new Monom(2,1);
        Monom b = new Monom(0,1);
        Monom c = new Monom(2,1);
        Monom d = new Monom(0,5);
        Polinom x = new Polinom();
        Polinom y = new Polinom();
        x.addMonom(a);
        x.addMonom(b);
        y.addMonom(c);
        y.addMonom(d);
        Polinom rezultat = x.adunare(y);
        Monom e = new Monom(2,2);
        Monom f = new Monom(0,6);
        Polinom corect = new Polinom();
        corect.addMonom(e);
        corect.addMonom(f);
        assert(rezultat.equals(corect));
        Polinom test1 = new Polinom();
        Polinom test2 = new Polinom();
        Monom monom1 = new Monom(0,1);
        Monom monom2 = new Monom(0,0);
        test1.addMonom(monom1);
        test2.addMonom(monom2);
        test1.adunare(test2);
        Monom corect1 = new Monom(0,1);
        Polinom rezultat1 = new Polinom();
        rezultat1.addMonom(corect1);
        assert(rezultat1.equals(test1));

    }
    @Test
    public void testScadere(){

    }
    @Test
    public void testInmultire(){

    }
    @Test
    public void testImpartire(){

    }
    @Test
    public void testDerivare(){

    }
    @Test
    public void testIntegrare(){

    }
}