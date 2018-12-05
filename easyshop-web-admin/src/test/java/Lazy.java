import org.junit.Test;

import java.util.Calendar;

public class Lazy {
    private static Lazy lazy;
    public Lazy(){}
    public  static synchronized Lazy GetInstance(){
        if(lazy==null){
            lazy=new Lazy();
        }
        return lazy;
    }

    public static void a(String[] args) {
        Myclone my=new Myclone();
        Object o;
        my.setI(10);
        String a="123";
        Calendar instance = Calendar.getInstance();
        System.out.println(instance.get(Calendar.YEAR));
        System.out.println(instance.get(Calendar.MONTH));
        System.out.println(instance.get(Calendar.DAY_OF_MONTH));
        System.out.println(instance.get(Calendar.DAY_OF_WEEK));
        System.out.println(instance.get(Calendar.MINUTE));
        System.out.println(instance.get(Calendar.SECOND));
        System.out.println(a.intern());
        try {
            Myclone clone = (Myclone)my.clone();
            System.out.println(clone.getI());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test(){
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        System.out.println(timeInMillis);
        System.out.println(System.currentTimeMillis());
        //System.out.println(Clock.systemDefaultZone().millis());
}
}

class Hunger{
    private static Hunger hunger=new Hunger();
    private Hunger(){}
    public static Hunger getInstance(){
        return hunger;
    }
}
class Myclone implements Cloneable{
    public Integer i;

    public Integer getI() {
        return i;
    }

    public void setI(Integer i) {
        this.i = i;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

