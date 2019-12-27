package og;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

public class test {
    public static void main(String[] args) {
        Penguin a = new Penguin(1919,"A",Gender.FEMALE,Fish.SILVERFISH);
        Penguin b = new Penguin(1929,"B",Gender.FEMALE,Fish.SILVERFISH);
        Penguin c = new Penguin(1939,"C",Gender.FEMALE,Fish.SILVERFISH);
        Penguin d = new Penguin(1949,"D",Gender.MALE,Fish.SARDELLE);
//        System.out.println(a.equals(b));
//        System.out.println("Hash:" + a.hashCode());
//        System.out.println("Hash:" + b.hashCode());
        HashSet<Penguin> h1 = new HashSet<>();
        HashSet<Penguin> h2 = new HashSet<>();

        h1.add(a);
        h2.add(b);
        h1.add(c);
        h2.add(d);

        PenguinColony pc1 = new PenguinColony(h1);
        PenguinColony pc2 = new PenguinColony(h2);
//        System.out.println(pc1.getPenguins());
//        System.out.println(pc2.getPenguins());
//        pc1.uniteColonies(pc2);
//        System.out.println(pc1.getPenguins());
//        System.out.println(pc2.getPenguins());

        LinkedList<Penguin> penguins = new LinkedList<>();
        penguins.add(b);
        penguins.add(d);
        if(pc1.findFirstFriend(penguins)!= null){
            System.out.println(pc1.findFirstFriend(penguins).getName());
        }else{
            System.out.println("null");
        }
    }
}
