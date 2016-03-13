package sexy.fkaefer.midgard;

import android.support.v4.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.firebase.client.Firebase;

import java.util.HashMap;
import java.util.Map;

import sexy.fkaefer.midgard.data.Chars;

public class NewChar extends Fragment {
    View mFrag;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFrag = inflater.inflate(R.layout.activity_new_char, container, false);
        Button fab = (Button) mFrag.findViewById(R.id.create_Char);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewChar();
                Snackbar.make(view, "Char wurde erstellt", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        return mFrag;
    }

    private void createNewChar(){
        Chars.CharItem tempChar = new Chars.CharItem();
        tempChar.setName(((AutoCompleteTextView) mFrag.findViewById(R.id.in_name)).getText().toString());
        tempChar.setRasse(((AutoCompleteTextView) mFrag.findViewById(R.id.in_rasse)).getText().toString());
        tempChar.setGrad(Integer.parseInt(((AutoCompleteTextView) mFrag.findViewById(R.id.in_grad)).getText().toString()));
        tempChar.setGG(Integer.parseInt(((AutoCompleteTextView) mFrag.findViewById(R.id.in_gg)).getText().toString()));
        tempChar.setSG(Integer.parseInt(((AutoCompleteTextView) mFrag.findViewById(R.id.in_sg)).getText().toString()));
        tempChar.setSt(Integer.parseInt(((AutoCompleteTextView) mFrag.findViewById(R.id.in_st)).getText().toString()));
        tempChar.setGs(Integer.parseInt(((AutoCompleteTextView) mFrag.findViewById(R.id.in_gs)).getText().toString()));
        tempChar.setGw(Integer.parseInt(((AutoCompleteTextView) mFrag.findViewById(R.id.in_gw)).getText().toString()));
        tempChar.setKo(Integer.parseInt(((AutoCompleteTextView) mFrag.findViewById(R.id.in_ko)).getText().toString()));
        tempChar.setIn(Integer.parseInt(((AutoCompleteTextView) mFrag.findViewById(R.id.in_in)).getText().toString()));
        tempChar.setZt(Integer.parseInt(((AutoCompleteTextView) mFrag.findViewById(R.id.in_zt)).getText().toString()));
        tempChar.setAu(Integer.parseInt(((AutoCompleteTextView) mFrag.findViewById(R.id.in_au)).getText().toString()));
        tempChar.setpA(Integer.parseInt(((AutoCompleteTextView) mFrag.findViewById(R.id.in_pa)).getText().toString()));
        tempChar.setWk(Integer.parseInt(((AutoCompleteTextView) mFrag.findViewById(R.id.in_wk)).getText().toString()));
        tempChar.setB(Integer.parseInt(((AutoCompleteTextView) mFrag.findViewById(R.id.in_b)).getText().toString()));
        tempChar.setLP(Integer.parseInt(((AutoCompleteTextView) mFrag.findViewById(R.id.in_lp)).getText().toString()));
        tempChar.setAP(Integer.parseInt(((AutoCompleteTextView) mFrag.findViewById(R.id.in_ap)).getText().toString()));
        tempChar.setAw(Integer.parseInt(((AutoCompleteTextView) mFrag.findViewById(R.id.in_aw)).getText().toString()));
        tempChar.setResG(Integer.parseInt(((AutoCompleteTextView) mFrag.findViewById(R.id.in_resg)).getText().toString()));
        tempChar.setResK(Integer.parseInt(((AutoCompleteTextView) mFrag.findViewById(R.id.in_resk)).getText().toString()));
        /*Map<String, String> tempChar = new HashMap<String, String>();
        tempChar.put("name", ((AutoCompleteTextView) mFrag.findViewById(R.id.in_name)).getText().toString());
        tempChar.put("rasse", ((AutoCompleteTextView) mFrag.findViewById(R.id.in_rasse)).getText().toString());
        tempChar.put("grad", ((AutoCompleteTextView) mFrag.findViewById(R.id.in_grad)).getText().toString());
        tempChar.put("gg" ,((AutoCompleteTextView) mFrag.findViewById(R.id.in_gg)).getText().toString());
        tempChar.put("sg",((AutoCompleteTextView) mFrag.findViewById(R.id.in_sg)).getText().toString());
        tempChar.put("st", ((AutoCompleteTextView) mFrag.findViewById(R.id.in_st)).getText().toString());
        tempChar.put("gs", ((AutoCompleteTextView) mFrag.findViewById(R.id.in_gs)).getText().toString());
        tempChar.put("gw", ((AutoCompleteTextView) mFrag.findViewById(R.id.in_gw)).getText().toString());
        tempChar.put("ko",((AutoCompleteTextView) mFrag.findViewById(R.id.in_ko)).getText().toString());
        tempChar.put("in", ((AutoCompleteTextView) mFrag.findViewById(R.id.in_in)).getText().toString());
        tempChar.put("zt", ((AutoCompleteTextView) mFrag.findViewById(R.id.in_zt)).getText().toString());
        tempChar.put("au", ((AutoCompleteTextView) mFrag.findViewById(R.id.in_au)).getText().toString());
        tempChar.put("pa", ((AutoCompleteTextView) mFrag.findViewById(R.id.in_pa)).getText().toString());
        tempChar.put("wk", ((AutoCompleteTextView) mFrag.findViewById(R.id.in_wk)).getText().toString());
        tempChar.put("b", ((AutoCompleteTextView) mFrag.findViewById(R.id.in_b)).getText().toString());
        tempChar.put("lp", ((AutoCompleteTextView) mFrag.findViewById(R.id.in_lp)).getText().toString());
        tempChar.put("ap", ((AutoCompleteTextView) mFrag.findViewById(R.id.in_ap)).getText().toString());
        tempChar.put("aw", ((AutoCompleteTextView) mFrag.findViewById(R.id.in_aw)).getText().toString());
        tempChar.put("resg", ((AutoCompleteTextView) mFrag.findViewById(R.id.in_resg)).getText().toString());
        tempChar.put("resk", ((AutoCompleteTextView) mFrag.findViewById(R.id.in_resk)).getText().toString());*/
        Firebase newRef = Application.mainRef.child("chars").push();
        newRef.setValue(tempChar);
        String postedId = newRef.getKey();
        Application.mainRef.child("battles/battle1").child(postedId).setValue(true);
    }
}
