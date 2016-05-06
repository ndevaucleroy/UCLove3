package lsinf1225.uclove;
import java.lang.*;

/**
 * classe permettant la creation dun objet favorite qui donne les preference dun user
 * Created by cariamole on 02.05.16.
 */
public class Favorite{
    private boolean[] hair;
    private boolean[] eyes;
    private boolean place;
    private int min;
    private int max;
    public Favorite(boolean[] hair,boolean[] eyes,boolean place,int min, int max){
        this.hair=hair;
        this.eyes=eyes;
        this.place=place;
        this.min=min;
        this.max=max;
    }

    public Favorite() {
    }
    //cree un favorite a laide dun entier
    public Favorite(int i) {
        Favorite fav;
        fav = intToFav(i);
        this.hair=fav.getHair();
        this.eyes=fav.getEyes();
        this.min=fav.getMin();
        this.max=fav.getMax();
        this.place=fav.getPlace();

    }

    public void setHair(boolean[] b){
        hair=b;
    }
    public void setEyes(boolean[] b){
        eyes=b;
    }
    public void setPlace(boolean b){
        place=b;
    }
    public void setMax(int i){
        max=i;
    }
    public void setMin(int i){
        min=i;
    }
    public boolean[] getHair(){
        return hair;
    }
    public boolean[] getEyes(){
        return eyes;
    }
    public boolean getPlace(){
        return place;
    }
    public int getMax(){
        return max;
    }
    public int getMin(){
        return min;
    }
    //retourne un favorite a laide dun entier a laide de decalage de bit et de la valeur booleenne que chaque bit possede
    public Favorite intToFav(int n){
        int maxI=n>>>21;
        int minI=(n<<11)>>>21;
        int placeI=(n<<22)>>>31;
        int eyesI=(n<<23)>>>28;
        int hairI=(n<<27)>>>27;

        boolean [] hair=new boolean[5];
        for(int i=0; i<hair.length;i++){
            if(((hairI<<(31-i))>>>31)==1){
                hair[i]=true;
            }
            else{
                hair[i]=false;
            }
        }
        boolean[] eyes=new boolean[4];
        for(int i=0; i<eyes.length;i++){
            if(((eyesI<<(31-i))>>>31)==1){
                eyes[i]=true;
            }
            else{
                eyes[i]=false;
            }
        }
        boolean place;
        if(((placeI<<(31))>>>31)==1){
            place=true;
        }
        else{
            place=false;
        }
        Favorite fav=new Favorite(hair, eyes, place, minI,maxI);
        return fav;
    }
    //prends un Favorite et retourne l entier correspondant pour pouvoir le rajouter a la table person
    public int favToInt(){
        int code=0;
        boolean[] hair=this.getHair();
        boolean[] eyes=this.getEyes();
        boolean place=this.getPlace();
        int max=this.getMax();
        int min=this.getMin();
        for(int i=0;i<hair.length;i++){
            if(hair[i])
                code+=Math.pow(2,i);
        }
        for(int i=0;i<eyes.length;i++){
            if(eyes[i])
                code+=Math.pow(2,(i+5));
        }
        if(place)
            code+=Math.pow(2,9);

        code+=(min<<10)+(max<<21);
        return code;//casse-dedi MichMich
    }

    public String toString(){
        boolean[] bh=this.getHair();
        boolean[] be=this.getEyes();
        boolean bp=this.getPlace();
        int ima=this.getMax();
        int imi=this.getMin();
        String s="";
        for(int i=0; i<bh.length;i++){
            s=s+" "+bh[i];
        }
        for(int i=0; i<be.length;i++){
            s=s+" "+be[i];
        }
        s=s+" "+bp;
        s=s+" "+imi;
        s=s+" "+ima;
        return s;
    }
}
