/*
 * Doeu escrever isso. 
 * Ainda não tá 100% enterprise.
 *
 * Pra rodar:
 * $ javac edurenesto.java
 * $ java edurenesto
 */

import java.lang.reflect.*;

public class edurenesto {
    public static void main(String[] args) {
        new HelioWorldExecutor().run();
    }
}

class HelioWorldException extends Exception {
    public static final long serialVersionUID = 102931092301923L;

    public HelioWorldException(String err) {
        super(err);
    }
}

class HelioWorldExecutor {
    private HelioWorldProvider provider;

    public HelioWorldExecutor() {
        HelioWorldFactory fac = new HelioWorldFactory();

        try {
            provider = fac.build(HelioWorldFactory.HelioWorldType.REFLECT);
        } catch(HelioWorldException exp) {
            System.err.println("Erro! " + exp.getMessage());
        }
    }

    public void run() {
        try {
            provider.doHelioWorld();
        } catch(HelioWorldException exp) {
            System.err.println("Erro! " + exp.getMessage());
        }
    }
}

class HelioWorldFactory {
    public enum HelioWorldType {
        NATIVE,
        REFLECT
    }

    public HelioWorldFactory() {

    }

    public HelioWorldProvider build(HelioWorldType type) throws HelioWorldException{
        HelioWorldProvider provider = null;

        switch(type) {
            case NATIVE:
                provider = new NativeHelioWorld();
                break;
            case REFLECT:
                provider = new ReflectHelioWorld();
                break;
            default:
                throw new HelioWorldException("lol");
        }

        return provider;
    }
}

interface HelioWorldProvider {
    public void doHelioWorld() throws HelioWorldException;
}

class NativeHelioWorld implements HelioWorldProvider {
    public NativeHelioWorld() {

    }

    public void doHelioWorld() {
        System.out.println("Helio world!");
    }
}

class ReflectHelioWorld implements HelioWorldProvider {
    private Method print;
    private Object obj;

    public ReflectHelioWorld() throws HelioWorldException {
        try {
            Class c = java.io.PrintStream.class;
            print = c.getMethod("println", String.class);

            Class sys = System.class;
            Field out = sys.getField("out");
            obj = out.get(null);
        } catch(Exception e) {
            throw new HelioWorldException(e.getMessage());
        } 
    }
    
    public void doHelioWorld() throws HelioWorldException {
        try {
            print.invoke(obj, "Helio world!"); 
        } catch(Exception e){
            throw new HelioWorldException(e.getMessage());
        }
    }
}
