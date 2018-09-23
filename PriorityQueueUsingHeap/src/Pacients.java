public class Pacients implements Comparable<Pacients> {
    private int id;
    private String name;
    private String CNP;
    private int priority;

    public Pacients(int id, String name, String CNP, int priority) {
        this.id = id;
        this.name = name;
        this.CNP = CNP;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public int compareTo(Pacients o) {
        Pacients x = (Pacients) o;
        if(this.priority<x.priority){
            return -1;
        }else{
            return 1;
        }
    }
}
