//class impementing the Serializable interface
public class Persistence implements Serializable {
    
    //privately declared variable
    private String name;
    
    //default no argument constructor
    public Persistence() {}
    
    //default getters and setters
    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}