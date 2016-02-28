package sexy.fkaefer.midgard.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class Chars {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<CharItem> ITEMS = new ArrayList<CharItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, CharItem> ITEM_MAP = new HashMap<String, CharItem>();

    public static void addItem(CharItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }


    /**
     * A dummy item representing a piece of content.
     */
    public static class CharItem {
        public String id;
        public String Name;
        public String Rasse;
        public String Typ;
        public int AP;
        public int Au;
        public int Aw;
        public int B;
        public int Grad;
        public int Gs;
        public int Gw;
        public int In;
        public int Ko;
        public int LP;
        public int ResG;
        public int ResK;
        public int St;
        public int Wk;
        public int Z;
        public int Zt;
        public int pA;
        public int tempGw;

        public int getGG() {
            return GG;
        }

        public void setGG(int GG) {
            this.GG = GG;
        }

        public int GG;

        public int getSG() {
            return SG;
        }

        public void setSG(int SG) {
            this.SG = SG;
        }

        public int SG;

        public CharItem(){
        }

        public CharItem(String id, String Name, String Rasse, String Typ, int AP, int Au, int Aw, int B, int Grad, int Gs, int Gw, int In, int Ko, int LP, int ResG, int ResK, int St, int Wk, int Z, int Zt, int pA) {
            this.id = id;
            this.Name = Name;
            this.Rasse = Rasse;
            this.Typ = Typ;
            this.AP = AP;
            this.Au = Au;
            this.Aw = Aw;
            this.B = B;
            this.Grad = Grad;
            this.Gs = Gs;
            this.Gw = Gw;
            this.In = In;
            this.Ko = Ko;
            this.LP = LP;
            this.ResG = ResG;
            this.ResK = ResK;
            this.St = St;
            this.Wk = Wk;
            this.Z = Z;
            this.Zt = Zt;
            this.pA = pA;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getRasse() {
            return Rasse;
        }

        public void setRasse(String rasse) {
            Rasse = rasse;
        }

        public String getTyp() {
            return Typ;
        }

        public void setTyp(String typ) {
            Typ = typ;
        }

        public int getAP() {
            return AP;
        }

        public void setAP(int AP) {
            this.AP = AP;
        }

        public int getAu() {
            return Au;
        }

        public void setAu(int au) {
            Au = au;
        }

        public int getAw() {
            return Aw;
        }

        public void setAw(int aw) {
            Aw = aw;
        }

        public int getB() {
            return B;
        }

        public void setB(int b) {
            B = b;
        }

        public int getGrad() {
            return Grad;
        }

        public void setGrad(int grad) {
            Grad = grad;
        }

        public int getGs() {
            return Gs;
        }

        public void setGs(int gs) {
            Gs = gs;
        }

        public int getGw() {
            return Gw;
        }

        public void setGw(int gw) {
            Gw = gw;
        }

        public int getIn() {
            return In;
        }

        public void setIn(int in) {
            In = in;
        }

        public int getKo() {
            return Ko;
        }

        public void setKo(int ko) {
            Ko = ko;
        }

        public int getLP() {
            return LP;
        }

        public void setLP(int LP) {
            this.LP = LP;
        }

        public int getResG() {
            return ResG;
        }

        public void setResG(int resG) {
            ResG = resG;
        }

        public int getResK() {
            return ResK;
        }

        public void setResK(int resK) {
            ResK = resK;
        }

        public int getSt() {
            return St;
        }

        public void setSt(int st) {
            St = st;
        }

        public int getWk() {
            return Wk;
        }

        public void setWk(int wk) {
            Wk = wk;
        }

        public int getZ() {
            return Z;
        }

        public void setZ(int z) {
            Z = z;
        }

        public int getZt() {
            return Zt;
        }

        public void setZt(int zt) {
            Zt = zt;
        }

        public int getpA() {
            return pA;
        }

        public void setpA(int pA) {
            this.pA = pA;
        }


        public int getTempGw() {
            return tempGw;
        }

        public void setTempGw(int tempGw) {
            this.tempGw = tempGw;
        }

        @Override
        public String toString() {
            return Name;
        }
    }
}