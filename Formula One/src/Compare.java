import java.util.Comparator;

class Compare implements Comparator<Formula1Driver> {



@Override
public int compare(Formula1Driver o1, Formula1Driver o2) {
        if (o2.getPoints()==o1.getPosition()) {
            if (o2.getFirstPositionCount() > o1.getFirstPositionCount()) {
                return 1;
            }else{
                return -1;
            }
        }
    else if (o2.getPoints() > o1.getPoints() ) {
        return 1;
    }else {
    return -1;
        }
}

}





