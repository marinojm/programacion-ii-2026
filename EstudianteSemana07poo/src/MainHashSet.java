import java.util.HashSet;
public class MainHashSet {
    public static void main(String[]args) {
        HashSet<String> correos = new HashSet<>();

        correos.add("iusdnfisnisv.edu.gt");
        correos.add("iusdnfi.edu.gt");
        correos.add("iusdnfisnisv.edu.gt");

        for(String correo: correos){
            System.out.println(correo);
        }
    }
}
