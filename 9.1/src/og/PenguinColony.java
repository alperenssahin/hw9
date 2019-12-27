package og;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

public class PenguinColony {

    private HashSet<Penguin> penguins;

    public PenguinColony(HashSet<Penguin> penguins) {
        this.penguins = penguins;
    }

    public HashSet<Penguin> getPenguins() {
        return penguins;
    }

    public void setPenguins(HashSet<Penguin> penguins) {
        this.penguins = penguins;
    }

    public void uniteColonies(PenguinColony otherColony) {
        HashSet<Penguin> tmp = this.getPenguins();
        HashSet<Penguin> parameter = otherColony.getPenguins();
        while (parameter.size() != 0){
            Penguin pTmp = parameter.iterator().next();
            tmp.add(pTmp);
            parameter.remove(pTmp);
        }
        this.setPenguins(tmp);
        otherColony.setPenguins(parameter);
    }

    public PenguinColony splitColony(Predicate<? super Penguin> pred) {
        // TODO
        HashSet<Penguin> nhs = new HashSet<>();
        HashSet<Penguin> tmp = getPenguins();
        Iterator<Penguin> pIterator = tmp.iterator();
        while (pIterator.hasNext()){
            Penguin pTmp = pIterator.next();
            if(pred.test(pTmp)){
                nhs.add(pTmp);
                tmp.remove(pTmp);
            }
        }
        setPenguins(tmp);
        return new PenguinColony(nhs);
    }

    public Penguin findFirstFriend(LinkedList<Penguin> penguinFriends) {
        // TODO
        if(penguinFriends.size() > 0){
            HashSet<Penguin> hsp = getPenguins();
            Iterator<Penguin> piterator = penguinFriends.iterator();
            while (piterator.hasNext()){
                Penguin tmp = piterator.next();
                if(hsp.contains(tmp)){
                    return tmp;
                }
            }
            return null;
        }
        else{
            return null;
        }
    }

    public boolean canFeedPenguinsWithProperty(Predicate<? super Penguin> pred, Set<Fish> fishes) {
        // TODO
        boolean conn = true;
        Iterator<Fish> fishIterator = fishes.iterator();
        while (fishIterator.hasNext()){
            Fish a = fishIterator.next();
            HashSet<Penguin> tmp = getPenguins();
            Iterator<Penguin> penguinIterator = tmp.iterator();
            while (penguinIterator.hasNext()){
                Penguin pTmp = penguinIterator.next();
                if(pred.test(pTmp)){
                    conn = false;
                    break;
                }
            }
        }
        return conn;
    }

    public int computeSum(Function<? super Penguin, Integer> fun) {
        // TODO
        int sum = 0;
        HashSet<Penguin> tmp = getPenguins();
        Iterator<Penguin> penguinIterator = tmp.iterator();
        while (penguinIterator.hasNext()){
            Penguin pTmp = penguinIterator.next();
            sum += fun.apply(pTmp);
        }
        return sum;
    }

}
