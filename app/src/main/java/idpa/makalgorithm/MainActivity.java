package idpa.makalgorithm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.switchmaterial.SwitchMaterial;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.stream.Format;
import org.simpleframework.xml.stream.HyphenStyle;
import org.simpleframework.xml.stream.Style;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    public static String firstSeq;
    public static String secondSeq;

    @BindView(R.id.firstString)
    EditText firstString;
    @BindView(R.id.secondString)
    EditText secondString;
    @BindView(R.id.opened_set)
    ConstraintLayout opened_set;
    @BindView(R.id.set_textView)
    TextView set_textView;
    @BindView(R.id.vector_textView)
    TextView vector_textView;
    @BindView(R.id.opened_vector)
    ConstraintLayout opened_vector;
    @BindView(R.id.main_constraint)
    ConstraintLayout main_constraint;
    @BindView(R.id.runButton)
    TextView runButton;
    @BindView(R.id.opened_edit)
    ConstraintLayout opened_edit;
    @BindView(R.id.edit_textView)
    TextView edit_textView;
    @BindView(R.id.switchMode)
    SwitchCompat switchMode;
    @BindView(R.id.row_search)
    TableRow row_search;

    @BindView(R.id.editRadio)
    RadioButton editRadio;
    @BindView(R.id.similarityRadio)
    RadioButton similarityRadio;

    @BindView(R.id.setRadio)
    RadioButton setRadio;
    @BindView(R.id.setMultiRadio)
    RadioButton setMultiRadio;
    @BindView(R.id.jaccardRadio)
    RadioButton jaccardRadio;
    @BindView(R.id.jaccardMultiRadio)
    RadioButton jaccardMultiRadio;
    @BindView(R.id.diceRadio)
    RadioButton diceRadio;
    @BindView(R.id.diceMultiRadio)
    RadioButton diceMultiRadio;
    @BindView(R.id.intersectionRadio)
    RadioButton intersectionRadio;
    @BindView(R.id.intersectionMultiRadio)
    RadioButton intersectionMultiRadio;

    @BindView(R.id.IDFRadio)
    RadioButton IDFRadio;
    @BindView(R.id.TDFRadio)
    RadioButton TDFRadio;
    @BindView(R.id.IDFTDFRadio)
    RadioButton IDFTDFRadio;

    @BindView(R.id.cosineVectorRadio)
    RadioButton cosineVectorRadio;
    @BindView(R.id.pearsonVectorRadio)
    RadioButton pearsonVectorRadio;
    @BindView(R.id.euclidianVectorRadio)
    RadioButton euclidianVectorRadio;
    @BindView(R.id.manhattanVectorRadio)
    RadioButton manhattanVectorRadio;
    @BindView(R.id.tanimotoVectorRadio)
    RadioButton tanimotoVectorRadio;
    @BindView(R.id.diceVectorRadio)
    RadioButton diceVectorRadio;

    @BindView(R.id.searchEditText)
    EditText searchEditText;

    public static double[][] arr;
    public static int insertWeight = 1;
    public static int deleteWeight = 1;
    public static int updateWeight = 1;
    public static double ins, del, upd;
    MainObject abed;

    StringBuilder sb;
    static String S1;
    static String S2;

    public static char[] rna = {'A', 'C', 'G', 'U'};


    public static HashMap<Character, Double> tm1 = new HashMap<Character, Double>();
    public static HashMap<Character, Double> tm2 = new HashMap<Character, Double>();


    public static ArrayList<HashMap<Character, Double>> tm = new ArrayList<>();

    HashMap<String, Double> similarities;

    public static ArrayList<String> maw = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        sb = new StringBuilder();
        addRNAs();
        switchMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switchMode.isChecked()){
                    Toast.makeText(MainActivity.this, "Started Search Mode", Toast.LENGTH_SHORT).show();
                    switchMode.setText("Search Mode");
                    ConstraintSet constraintSet = new ConstraintSet();
                    constraintSet.clone(main_constraint);
                    constraintSet.connect(R.id.edit_textView,ConstraintSet.TOP,R.id.row_search,ConstraintSet.BOTTOM,40);
                    constraintSet.connect(R.id.opened_edit,ConstraintSet.TOP,R.id.row_search,ConstraintSet.BOTTOM,40);
                    constraintSet.applyTo(main_constraint);
                    firstString.setVisibility(View.GONE);
                    secondString.setVisibility(View.GONE);
                    row_search.setVisibility(View.VISIBLE);

                    editRadio.setChecked(false);
                    similarityRadio.setChecked(false);

                    setRadio.setChecked(false);
                    setMultiRadio.setChecked(false);
                    jaccardRadio.setChecked(false);
                    jaccardMultiRadio.setChecked(false);
                    diceRadio.setChecked(false);
                    diceMultiRadio.setChecked(false);
                    intersectionRadio.setChecked(false);
                    intersectionMultiRadio.setChecked(false);

                    IDFRadio.setChecked(false);
                    TDFRadio.setChecked(false);
                    IDFTDFRadio.setChecked(false);


                    cosineVectorRadio.setChecked(false);
                    pearsonVectorRadio.setChecked(false);
                    euclidianVectorRadio.setChecked(false);
                    manhattanVectorRadio.setChecked(false);
                    tanimotoVectorRadio.setChecked(false);
                    diceVectorRadio.setChecked(false);
                    runButton.setText("Search");
                }
                else{
                    switchMode.setText("Compare Mode");
                    Toast.makeText(MainActivity.this, "Started Compare Mode", Toast.LENGTH_SHORT).show();
                    ConstraintSet constraintSet = new ConstraintSet();
                    constraintSet.clone(main_constraint);
                    constraintSet.connect(R.id.edit_textView,ConstraintSet.TOP,R.id.secondString,ConstraintSet.BOTTOM,40);
                    constraintSet.connect(R.id.opened_edit,ConstraintSet.TOP,R.id.secondString,ConstraintSet.BOTTOM,40);
                    constraintSet.applyTo(main_constraint);
                    firstString.setVisibility(View.VISIBLE);
                    secondString.setVisibility(View.VISIBLE);
                    row_search.setVisibility(View.GONE);
                    runButton.setText("Run");

                }
            }
        });
        IDFRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IDFRadio.isChecked()){
                    TDFRadio.setChecked(false);
                    IDFTDFRadio.setChecked(false);
                }
            }
        });
        TDFRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TDFRadio.isChecked()){
                    IDFRadio.setChecked(false);
                    IDFTDFRadio.setChecked(false);
                }
            }
        });
        IDFTDFRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IDFTDFRadio.isChecked()){
                    IDFRadio.setChecked(false);
                    TDFRadio.setChecked(false);
                }
            }
        });

        try {
            marshalSummarized("abed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addRNAs() {
        abed = new MainObject("UUAGAGAGGAAGAGACGAAGCUUCAGC");
        abed.addUpdate("UUAGAGAGGAAGAGACGAAGCUUCAGC");
        abed.addUpdate("UCAGAAGAGAUUCAGGAUCGCUAGAGAGG");
        abed.addUpdate("UGGAGCUCAUAAGUAACAUGUGUCUAGA");
        abed.addUpdate("AAUUAACCAAUUACUUGAGGAGCAAGG");
        abed.addUpdate("UAAGAGAGUGUGAAGAAGGGUGACGACC");
        abed.addUpdate("UGUGAAAUUCCUCAGACAAAAAAGGUAUCC");
        abed.addUpdate("UGAGAGUCAGAUGUAGAGGAAGCCAAAG");
        abed.addUpdate("CUGGAAAUCUGUGAGUAUCUGGUGAAUU");
        abed.addUpdate("UAUUACUGGGAACCUGAGAUGUUGGAAAAUC");
        abed.addUpdate("UGAGGAUAGGAAAGCACAAGGUGGU");
        abed.addUpdate("UUGGCUUAGGUGCUUCUGCAGUAGAUGUGG");
        abed.addUpdate("UAAUAUCAGGACUGACAAUGUCAAGGAUGU");
        abed.addUpdate("AAUCAAGAUUGACAAUGUUAUGGAUGUGGC");
        abed.addUpdate("UGAGAGUCAGAUGUGGAGGAAGCCAAAGA");
        abed.addUpdate("GAAGUCAGGAUGGCUGUUUUCUCUUGUG");
        abed.addUpdate("GAGGCAUUGAGAGGACAAAAGG");
        abed.addUpdate("CAGUUGUGCCUGAUAGGAGG");
        abed.addUpdate("GAUGAAGUACAAGGGACAGAGA");
        abed.addUpdate("UACUGUAGUUGCGGUUCUGGGCAC");
        abed.addUpdate("UGUAUCCUAGGAUGGUGCGGGUUGC");
        abed.addUpdate("UAGGACCAGUCUCAAGUGAGAUGCAGGAAGC");
        abed.addUpdate("UAGAGACCCCAGAGUAAUCUAAGAC");
        abed.addUpdate("UAUCAUGCAAGAGGUCUAGUGAGGAGGAC");
        abed.addUpdate("UGGAACAAAGGGUCUAAUGACAAGUGUCAUUG");
        abed.addUpdate("UAGGAAGGAUGCAAAUAUCUGUCAGCAGC");
        abed.addUpdate("UAGCAAUGCAUUGUCCUUUGCUUCCUGG");
        abed.addUpdate("UACAUUAGGAAAGCACCUAACAGUGUUGCGG");
        abed.addUpdate("CAGAACAGGAACUCAAACAGGGCGG");
        abed.addUpdate("UAGUAACGUGUGAAUUCUGAGUUGCAG");
        abed.addUpdate("UAGAGACAGAGUCGAGACACGG");
        abed.addUpdate("UGCAACAGAAGUACAGGAAGAUAAUGGG");
        abed.addUpdate("UUGUUUAUGCGGGUUAAAGCUAUUGACAA");
        abed.addUpdate("UGGACUAUGGUACGGGCUAUGGUAU");
        abed.addUpdate("UACAGUUUAGGGAAGAAAGGGUUGA");
        abed.addUpdate("UAGAAGGAAAUGCAGAUGAGGCAGAGGGG");
        abed.addUpdate("UGGUGAUCUGAGAAUAGGGACCGGGGUCC");
        abed.addUpdate("CAGGAGUAAAGAGAGCAAGA");
        abed.addUpdate("UGAGACAGUGGUAGGCAGGGCAGG");
        abed.addUpdate("UCAUCAAAGGGGCGGUUUUGAAGCUCUUC");
        abed.addUpdate("UAGCUGUUCUUGCUAAGUGAGGAUAUGGU");
        abed.addUpdate("UUGAGUUCAGUGAUUCAUCACUGUGCGGC");
        abed.addUpdate("UAGAGAGGGGGAGUUUCUGGGGUGUA");
        abed.addUpdate("UAAGGUCUCUGGAUAAAAAGCAUGAGCGGG");
        abed.addUpdate("UCUGUCACUUUUUAGGUAGAGAGGCCAAAA");
        abed.addUpdate("UGAGCAAGUCGAUUCACAGGAUCCCCAUGC");
        abed.addUpdate("UUACAGAGUUUGGACUUUAAUCUG");
        abed.addUpdate("UUAGAGCUAGCGCUUUUCUUUGACGAGGGUU");
        abed.addUpdate("UAGCAAUGUUGGGUUCAAAGGAGCUGG");
        abed.addUpdate("CGGGAAUCCUAGUGUGCUGGG");
        abed.addUpdate("UGGGAGUGAGCAGAUGCGGCAGGACCUAG");
        abed.addUpdate("UGAGCAGAUGCGGCAGAACCUAGGUUU");
        abed.addUpdate("UUAAAAAUGGAGUUGGCAGGUGAGUU");
        abed.addUpdate("UGAGAGUUCUCGUGAAGGUUUGCAGA");
        abed.addUpdate("UAGCAUGGAUGGUGAGCUAGGAAAGC");
        abed.addUpdate("UGGAUGGUGAGCUAGGAAAGCUACCUGGG");
        abed.addUpdate("UGGGAUUAAAGGUGUGUACUAAGUAAAGG");
        abed.addUpdate("UAAUGAUGAUGAGAGGUUAAGUGUGC");
        abed.addUpdate("UAGAAGUUCACUUCUGAGGAGGCUCAAU");
        abed.addUpdate("AGGGUAAUCACAAGGUAUGAAGCGGG");
        abed.addUpdate("UGCUGAUGGAGUCUAUUAGGAGAAAUUGC");
        abed.addUpdate("UUAGCUUCAGAGGUUCUUCACAUGC");
        abed.addUpdate("UAGAAUGGGUUGGGAAAAGUCACACAUGAAC");
        abed.addUpdate("UACAGUUUAGGAAAAGGCAAAGUCG");
        abed.addUpdate("GAGAAGAACGGCUGCCCCUGUGGGUGCGGG");
        abed.addUpdate("UGACCUAUGGGAACCUCGUGUUCUUCUCGG");
        abed.addUpdate("UGAUCUCAGUUCCUCUGUGAACUACGAUGC");
        abed.addUpdate("UGGAGUCAGGAAUAGGCCCGUC");
        abed.addUpdate("GUUCCUUUUGUAAAGUGAUAGAUGCGAGCAU");
        abed.addUpdate("UCGGAAGACAGAGACGAUGCAUAGGGGCCC");
        abed.addUpdate("UAAUGUGCUGUAUCCCAAACGAGGGCAGGU");
        abed.addUpdate("UAGAAUCACCAUGUGGCGAUGUGAC");
        abed.addUpdate("GGCAAGAAAAUGGGAGCUGCAGGA");
        abed.addUpdate("UGGAGUUACAGACCCUUGAUAUGGGUAGUG");
        abed.addUpdate("UAAGAGUCUGUCAAGGGGAACUCCGG");
        abed.addUpdate("UGGACUAUGUCGAGUUAGGGUGGAAAGGGG");
        abed.addUpdate("AGGAUGAGUAGGGAUUGGUCACUUAAG");
        abed.addUpdate("UGAAGGAUGAGUAGGGAUUUGGUCAC");
        abed.addUpdate("UGGAGUCGAAUGCAGGGUUGAUUCAGAGC");
        abed.addUpdate("UGGAGUCGAAUGCAGGGUUGAUCCAGA");
        abed.addUpdate("UGGAGUCGAAUGUAGGGUUGAUCCAGC");
        abed.addUpdate("CUUAUGGAGUCGAAUGUAGGGUUGAUCCAG");
        abed.addUpdate("UCUUAUGGAGUCGAAUGCAGGGUUGAUC");
        abed.addUpdate("UCUUAUGGAGUCGAAUGCAGGG");
        abed.addUpdate("UUCACAGCAGUUGGGAUGGUGAGGA");
        abed.addUpdate("UGCCUUGCAGCCUGAUCUUAUGGAAC");
        abed.addUpdate("UAAAGGCCAACGAGAUGACCAUGC");
        abed.addUpdate("UCAUUACAAAUGAGGAACUAGGCUCAGG");
        abed.addUpdate("GAGGAAGCACGUGAAGGGAAGAAAAC");
        abed.addUpdate("GCCUAAGAAGCUGGUGAUGUCCAG");
        abed.addUpdate("UAAGUGACAGAGGUAGGAUGGAAGAG");
        abed.addUpdate("UACUAGGCAAGGGAUCAGUGUUGAGCCG");
        abed.addUpdate("AUCUGGGGUAGUAAGAG");
        abed.addUpdate("UAAAGGCCCAUGAUGAGCAGAGGAAUU");
        abed.addUpdate("CACUCUGGAAGGAAGAAAGCUCGGGGU");
        abed.addUpdate("AACAAUGAGCAGGGGCGAUAACUAAAAG");
        abed.addUpdate("UUGGAAGUCGUGAGUUUGGAUGUG");
        abed.addUpdate("UUGGAAGUCGUGAGUUUGGAUGUGGGGAUA");
        abed.addUpdate("UGAUGACUAAGGAUAUGGAUAGGUUUCAAU");
        abed.addUpdate("UAAGGAUAGGUGAUACAGUGUUGGAAACAC");
        abed.addUpdate("CUAGAUGCAAUGAGUAAAGCAUAGUCU");
        abed.addUpdate("UAAGAAAAUGGAGGGUCUCAAGAAAC");
        abed.addUpdate("UAGUGAAUUCCUAAGGAUGAGAUCCCAAAA");
        abed.addUpdate("UGAAAGAGGAGUUGAUGCUUGGGC");
        abed.addUpdate("UGAAAGAGGAGUUGAUGCUUUGGGCA");
        abed.addUpdate("UAGCUGUUUUUACAGGGUUGGAUAGG");
        abed.addUpdate("UAAGGCUGGCUUGAAGGCAUGCCUU");
        abed.addUpdate("UGUCUGUCUGGCAAACCUGAACAUGC");
        abed.addUpdate("UAAAGAGACUGAGGUGAGAUGG");
        abed.addUpdate("UAGAGCAGUGGUUCAGUCAAGCAG");
        abed.addUpdate("UAUCCAUCCCUCGUCAUGUGCUUGAAG");
        abed.addUpdate("UAGAGAGUUAGGGUGGGAGCAUGAGUU");
        abed.addUpdate("GACGUAAUAGAUGUGAGCACGUGACUGCUGU");
        abed.addUpdate("UGAGAACAUGUGUUCAAAAACUGUGUAGG");
        abed.addUpdate("AAUGAGAGCUUUGAGAUGUGGUCCCC");
        abed.addUpdate("UUGGAGGUUAUGAGAGCUUUGAGAUGUGG");
        abed.addUpdate("UUGGAGGUUAUGAGAGCUUUGAGAUG");
        abed.addUpdate("UCCCAGUCCUCGGUCAUUAAACAAGGAAGG");
        abed.addUpdate("UACUUAGCCACAGACACCUUAUCUGCUGAG");
        abed.addUpdate("GGAGAGAGACAUCAUUUAUCCGG");
        abed.addUpdate("UGAGCAGGAUGUGAGGGUGGUGGACGGGAG");
        abed.addUpdate("UGAGCAGAAUGUGAGGGUGGUGGAUGG");
        abed.addUpdate("UGGGUGAGCGGGAGAAGCAAGGAGCAG");
        abed.addUpdate("UGGAAACCAUGGGUGAGCGGGAGA");
        abed.addUpdate("UUGAUCCGGAUGGAAACCAUGGGUGAGCGG");
        abed.addUpdate("UCCUGUCACAACGUUUUAUAUAAAUGGGA");
        abed.addUpdate("UGAAGGAAAACGGUACGGUGCUUCCUGC");
        abed.addUpdate("UGGUAGGUUCAGAGCUCACAUGAGCAUGG");
        abed.addUpdate("UGAGCAUGGUAUCAUGGAGUUCCCCAGG");
        abed.addUpdate("UAGAGAGGGUAGCGAUUGCCAAGC");
        abed.addUpdate("GGAGUUCAAGGAUAAACUAGAAAUG");
        abed.addUpdate("UGGUCUGAACCUAUUUUGCGUUGAGCCGG");
        abed.addUpdate("UUACAGAUAUGAAAGCUUGAGGUUGUGAG");
        abed.addUpdate("UACAGAUAUGAAAGCUUGAGGUUGUGAGU");
        abed.addUpdate("UUGAGGUUGUGAGUAG");
        abed.addUpdate("UGUAUGUAGGAAGAAUGUUCUGGGUGGAGG");
        abed.addUpdate("UGCUUAUGAUGGCAUUGGAGAGGG");
        abed.addUpdate("UCAGUGUACUGACAUUAGAGGCUGCC");
        abed.addUpdate("UUGGGAUGUGAAUUUACUGCACUUCAAGG");
        abed.addUpdate("UUGAGCUUCAGAUGUAGGAAUUUGUAGACGU");
        abed.addUpdate("UAUAAGGAUUCAGUUUAGCUAGGAAUUAGG");
        abed.addUpdate("UAGUAAAGGAAGUGAGAAAAG");
        abed.addUpdate("UGAAGGGGUUUCUGUGAGUGUGUGU");
        abed.addUpdate("UAGCAAGAAACGGGGAGCAGUGUUAAUAACC");
        abed.addUpdate("UAGAAGACCGAGAUGUGCCCGAC");
        abed.addUpdate("UGCCAUGGUGACAGAUAUGAAAAGGAUGGG");
        abed.addUpdate("UGAGAUAAGUCCUAAGAAUGUGGU");
        abed.addUpdate("AGCUUCUGCAGUACAGGAUGUGUGUCAGG");
        abed.addUpdate("UCUGAGAAAUUCCUGGAUGGAU");
        abed.addUpdate("UGCACAAAAGGACCGUAUUAGCUAAUCUGC");
        abed.addUpdate("UGAUCUUUUCACUGGGUAUGUUGG");
        abed.addUpdate("AUUGAUAAAGAGGAUAAAGACUAGGAC");
        abed.addUpdate("UAUCAGGAGUGACAGACGGAUGGACAGA");
        abed.addUpdate("UGUUUUACAAGGUUGAGGAGAUAUGUC");
        abed.addUpdate("UGAUAGAGGUUUCCUGGAUGCCUCUG");
        abed.addUpdate("UGUGAGAGGGUCGUGGCUGAAUCCCA");
        abed.addUpdate("UUUACUACUCCCCUCGGCCCUGAACUGGGACC");
        abed.addUpdate("UGGGAAUUUGAGUAAGACUUGAGAAAAAA");
        abed.addUpdate("UUGGGUAUGAGGUGAGGUAAAGGAGAAG");
        abed.addUpdate("AAUUGGGUAUGAGGUGAGGUAAAGGAGAAG");
        abed.addUpdate("UGUGAAACACAACGUAGGAGGUCUCUC");
        abed.addUpdate("AACACAGCAAAGGAUGAUCAAGAG");
        abed.addUpdate("CUUUGAGAUGCUGAUAUUCU");
        abed.addUpdate("UAAGCCACUUUGGGAGUCUUUGGAUG");
        abed.addUpdate("UGUUCAGCAGAGAUUCUCUCUGUCGAGG");
        abed.addUpdate("UGCUUAUCUUCAUUCGAUCCUGGUGAGC");
        abed.addUpdate("UGGCAUUUGUUUGAUGUUUGUGAGUUGGG");
        abed.addUpdate("UGAGUCUGGAAUUGUGGAUGGAAGCU");
        abed.addUpdate("AAUUGUGAGACAAAUCUGGUGUGGAAA");
        abed.addUpdate("UGAGAGGGUCAGAGCUGCUGAACAGG");
        abed.addUpdate("UCUAAUAAUGGAUGUGGUUGCUGG");
        abed.addUpdate("UAGCAGUGAGGUCAAGGAAGAGGAAGC");
        abed.addUpdate("UGAAGAGUAAGGAUGUUGGUGUGU");
        abed.addUpdate("UAAGAGAUUACAGGAUGUCGUAUUG");
        abed.addUpdate("UUCAACUAGGAUGUGGUGGGAACUUGAAAC");

    }
    private void addArrayListRNAs() {
        maw.add("UUAGAGAGGAAGAGACGAAGCUUCAGC");
        maw.add("UCAGAAGAGAUUCAGGAUCGCUAGAGAGG");
        maw.add("UGGAGCUCAUAAGUAACAUGUGUCUAGA");
        maw.add("AAUUAACCAAUUACUUGAGGAGCAAGG");
        maw.add("UAAGAGAGUGUGAAGAAGGGUGACGACC");
        maw.add("UGUGAAAUUCCUCAGACAAAAAAGGUAUCC");
        maw.add("UGAGAGUCAGAUGUAGAGGAAGCCAAAG");
        maw.add("CUGGAAAUCUGUGAGUAUCUGGUGAAUU");
        maw.add("UAUUACUGGGAACCUGAGAUGUUGGAAAAUC");
        maw.add("UGAGGAUAGGAAAGCACAAGGUGGU");
        maw.add("UUGGCUUAGGUGCUUCUGCAGUAGAUGUGG");
        maw.add("UAAUAUCAGGACUGACAAUGUCAAGGAUGU");
        maw.add("AAUCAAGAUUGACAAUGUUAUGGAUGUGGC");
        maw.add("UGAGAGUCAGAUGUGGAGGAAGCCAAAGA");
        maw.add("GAAGUCAGGAUGGCUGUUUUCUCUUGUG");
        maw.add("GAGGCAUUGAGAGGACAAAAGG");
        maw.add("CAGUUGUGCCUGAUAGGAGG");
        maw.add("GAUGAAGUACAAGGGACAGAGA");
        maw.add("UACUGUAGUUGCGGUUCUGGGCAC");
        maw.add("UGUAUCCUAGGAUGGUGCGGGUUGC");
        maw.add("UAGGACCAGUCUCAAGUGAGAUGCAGGAAGC");
        maw.add("UAGAGACCCCAGAGUAAUCUAAGAC");
        maw.add("UAUCAUGCAAGAGGUCUAGUGAGGAGGAC");
        maw.add("UGGAACAAAGGGUCUAAUGACAAGUGUCAUUG");
        maw.add("UAGGAAGGAUGCAAAUAUCUGUCAGCAGC");
        maw.add("UAGCAAUGCAUUGUCCUUUGCUUCCUGG");
        maw.add("UACAUUAGGAAAGCACCUAACAGUGUUGCGG");
        maw.add("CAGAACAGGAACUCAAACAGGGCGG");
        maw.add("UAGUAACGUGUGAAUUCUGAGUUGCAG");
        maw.add("UAGAGACAGAGUCGAGACACGG");
        maw.add("UGCAACAGAAGUACAGGAAGAUAAUGGG");
        maw.add("UUGUUUAUGCGGGUUAAAGCUAUUGACAA");
        maw.add("UGGACUAUGGUACGGGCUAUGGUAU");
        maw.add("UACAGUUUAGGGAAGAAAGGGUUGA");
        maw.add("UAGAAGGAAAUGCAGAUGAGGCAGAGGGG");
        maw.add("UGGUGAUCUGAGAAUAGGGACCGGGGUCC");
        maw.add("CAGGAGUAAAGAGAGCAAGA");
        maw.add("UGAGACAGUGGUAGGCAGGGCAGG");
        maw.add("UCAUCAAAGGGGCGGUUUUGAAGCUCUUC");
        maw.add("UAGCUGUUCUUGCUAAGUGAGGAUAUGGU");
        maw.add("UUGAGUUCAGUGAUUCAUCACUGUGCGGC");
        maw.add("UAGAGAGGGGGAGUUUCUGGGGUGUA");
        maw.add("UAAGGUCUCUGGAUAAAAAGCAUGAGCGGG");
        maw.add("UCUGUCACUUUUUAGGUAGAGAGGCCAAAA");
        maw.add("UGAGCAAGUCGAUUCACAGGAUCCCCAUGC");
        maw.add("UUACAGAGUUUGGACUUUAAUCUG");
        maw.add("UUAGAGCUAGCGCUUUUCUUUGACGAGGGUU");
        maw.add("UAGCAAUGUUGGGUUCAAAGGAGCUGG");
        maw.add("CGGGAAUCCUAGUGUGCUGGG");
        maw.add("UGGGAGUGAGCAGAUGCGGCAGGACCUAG");
        maw.add("UGAGCAGAUGCGGCAGAACCUAGGUUU");
        maw.add("UUAAAAAUGGAGUUGGCAGGUGAGUU");
        maw.add("UGAGAGUUCUCGUGAAGGUUUGCAGA");
        maw.add("UAGCAUGGAUGGUGAGCUAGGAAAGC");
        maw.add("UGGAUGGUGAGCUAGGAAAGCUACCUGGG");
        maw.add("UGGGAUUAAAGGUGUGUACUAAGUAAAGG");
        maw.add("UAAUGAUGAUGAGAGGUUAAGUGUGC");
        maw.add("UAGAAGUUCACUUCUGAGGAGGCUCAAU");
        maw.add("AGGGUAAUCACAAGGUAUGAAGCGGG");
        maw.add("UGCUGAUGGAGUCUAUUAGGAGAAAUUGC");
        maw.add("UUAGCUUCAGAGGUUCUUCACAUGC");
        maw.add("UAGAAUGGGUUGGGAAAAGUCACACAUGAAC");
        maw.add("UACAGUUUAGGAAAAGGCAAAGUCG");
        maw.add("GAGAAGAACGGCUGCCCCUGUGGGUGCGGG");
        maw.add("UGACCUAUGGGAACCUCGUGUUCUUCUCGG");
        maw.add("UGAUCUCAGUUCCUCUGUGAACUACGAUGC");
        maw.add("UGGAGUCAGGAAUAGGCCCGUC");
        maw.add("GUUCCUUUUGUAAAGUGAUAGAUGCGAGCAU");
        maw.add("UCGGAAGACAGAGACGAUGCAUAGGGGCCC");
        maw.add("UAAUGUGCUGUAUCCCAAACGAGGGCAGGU");
        maw.add("UAGAAUCACCAUGUGGCGAUGUGAC");
        maw.add("GGCAAGAAAAUGGGAGCUGCAGGA");
        maw.add("UGGAGUUACAGACCCUUGAUAUGGGUAGUG");
        maw.add("UAAGAGUCUGUCAAGGGGAACUCCGG");
        maw.add("UGGACUAUGUCGAGUUAGGGUGGAAAGGGG");
        maw.add("AGGAUGAGUAGGGAUUGGUCACUUAAG");
        maw.add("UGAAGGAUGAGUAGGGAUUUGGUCAC");
        maw.add("UGGAGUCGAAUGCAGGGUUGAUUCAGAGC");
        maw.add("UGGAGUCGAAUGCAGGGUUGAUCCAGA");
        maw.add("UGGAGUCGAAUGUAGGGUUGAUCCAGC");
        maw.add("CUUAUGGAGUCGAAUGUAGGGUUGAUCCAG");
        maw.add("UCUUAUGGAGUCGAAUGCAGGGUUGAUC");
        maw.add("UCUUAUGGAGUCGAAUGCAGGG");
        maw.add("UUCACAGCAGUUGGGAUGGUGAGGA");
        maw.add("UGCCUUGCAGCCUGAUCUUAUGGAAC");
        maw.add("UAAAGGCCAACGAGAUGACCAUGC");
        maw.add("UCAUUACAAAUGAGGAACUAGGCUCAGG");
        maw.add("GAGGAAGCACGUGAAGGGAAGAAAAC");
        maw.add("GCCUAAGAAGCUGGUGAUGUCCAG");
        maw.add("UAAGUGACAGAGGUAGGAUGGAAGAG");
        maw.add("UACUAGGCAAGGGAUCAGUGUUGAGCCG");
        maw.add("AUCUGGGGUAGUAAGAG");
        maw.add("UAAAGGCCCAUGAUGAGCAGAGGAAUU");
        maw.add("CACUCUGGAAGGAAGAAAGCUCGGGGU");
        maw.add("AACAAUGAGCAGGGGCGAUAACUAAAAG");
        maw.add("UUGGAAGUCGUGAGUUUGGAUGUG");
        maw.add("UUGGAAGUCGUGAGUUUGGAUGUGGGGAUA");
        maw.add("UGAUGACUAAGGAUAUGGAUAGGUUUCAAU");
        maw.add("UAAGGAUAGGUGAUACAGUGUUGGAAACAC");
        maw.add("CUAGAUGCAAUGAGUAAAGCAUAGUCU");
        maw.add("UAAGAAAAUGGAGGGUCUCAAGAAAC");
        maw.add("UAGUGAAUUCCUAAGGAUGAGAUCCCAAAA");
        maw.add("UGAAAGAGGAGUUGAUGCUUGGGC");
        maw.add("UGAAAGAGGAGUUGAUGCUUUGGGCA");
        maw.add("UAGCUGUUUUUACAGGGUUGGAUAGG");
        maw.add("UAAGGCUGGCUUGAAGGCAUGCCUU");
        maw.add("UGUCUGUCUGGCAAACCUGAACAUGC");
        maw.add("UAAAGAGACUGAGGUGAGAUGG");
        maw.add("UAGAGCAGUGGUUCAGUCAAGCAG");
        maw.add("UAUCCAUCCCUCGUCAUGUGCUUGAAG");
        maw.add("UAGAGAGUUAGGGUGGGAGCAUGAGUU");
        maw.add("GACGUAAUAGAUGUGAGCACGUGACUGCUGU");
        maw.add("UGAGAACAUGUGUUCAAAAACUGUGUAGG");
        maw.add("AAUGAGAGCUUUGAGAUGUGGUCCCC");
        maw.add("UUGGAGGUUAUGAGAGCUUUGAGAUGUGG");
        maw.add("UUGGAGGUUAUGAGAGCUUUGAGAUG");
        maw.add("UCCCAGUCCUCGGUCAUUAAACAAGGAAGG");
        maw.add("UACUUAGCCACAGACACCUUAUCUGCUGAG");
        maw.add("GGAGAGAGACAUCAUUUAUCCGG");
        maw.add("UGAGCAGGAUGUGAGGGUGGUGGACGGGAG");
        maw.add("UGAGCAGAAUGUGAGGGUGGUGGAUGG");
        maw.add("UGGGUGAGCGGGAGAAGCAAGGAGCAG");
        maw.add("UGGAAACCAUGGGUGAGCGGGAGA");
        maw.add("UUGAUCCGGAUGGAAACCAUGGGUGAGCGG");
        maw.add("UCCUGUCACAACGUUUUAUAUAAAUGGGA");
        maw.add("UGAAGGAAAACGGUACGGUGCUUCCUGC");
        maw.add("UGGUAGGUUCAGAGCUCACAUGAGCAUGG");
        maw.add("UGAGCAUGGUAUCAUGGAGUUCCCCAGG");
        maw.add("UAGAGAGGGUAGCGAUUGCCAAGC");
        maw.add("GGAGUUCAAGGAUAAACUAGAAAUG");
        maw.add("UGGUCUGAACCUAUUUUGCGUUGAGCCGG");
        maw.add("UUACAGAUAUGAAAGCUUGAGGUUGUGAG");
        maw.add("UACAGAUAUGAAAGCUUGAGGUUGUGAGU");
        maw.add("UUGAGGUUGUGAGUAG");
        maw.add("UGUAUGUAGGAAGAAUGUUCUGGGUGGAGG");
        maw.add("UGCUUAUGAUGGCAUUGGAGAGGG");
        maw.add("UCAGUGUACUGACAUUAGAGGCUGCC");
        maw.add("UUGGGAUGUGAAUUUACUGCACUUCAAGG");
        maw.add("UUGAGCUUCAGAUGUAGGAAUUUGUAGACGU");
        maw.add("UAUAAGGAUUCAGUUUAGCUAGGAAUUAGG");
        maw.add("UAGUAAAGGAAGUGAGAAAAG");
        maw.add("UGAAGGGGUUUCUGUGAGUGUGUGU");
        maw.add("UAGCAAGAAACGGGGAGCAGUGUUAAUAACC");
        maw.add("UAGAAGACCGAGAUGUGCCCGAC");
        maw.add("UGCCAUGGUGACAGAUAUGAAAAGGAUGGG");
        maw.add("UGAGAUAAGUCCUAAGAAUGUGGU");
        maw.add("AGCUUCUGCAGUACAGGAUGUGUGUCAGG");
        maw.add("UCUGAGAAAUUCCUGGAUGGAU");
        maw.add("UGCACAAAAGGACCGUAUUAGCUAAUCUGC");
        maw.add("UGAUCUUUUCACUGGGUAUGUUGG");
        maw.add("AUUGAUAAAGAGGAUAAAGACUAGGAC");
        maw.add("UAUCAGGAGUGACAGACGGAUGGACAGA");
        maw.add("UGUUUUACAAGGUUGAGGAGAUAUGUC");
        maw.add("UGAUAGAGGUUUCCUGGAUGCCUCUG");
        maw.add("UGUGAGAGGGUCGUGGCUGAAUCCCA");
        maw.add("UUUACUACUCCCCUCGGCCCUGAACUGGGACC");
        maw.add("UGGGAAUUUGAGUAAGACUUGAGAAAAAA");
        maw.add("UUGGGUAUGAGGUGAGGUAAAGGAGAAG");
        maw.add("AAUUGGGUAUGAGGUGAGGUAAAGGAGAAG");
        maw.add("UGUGAAACACAACGUAGGAGGUCUCUC");
        maw.add("AACACAGCAAAGGAUGAUCAAGAG");
        maw.add("CUUUGAGAUGCUGAUAUUCU");
        maw.add("UAAGCCACUUUGGGAGUCUUUGGAUG");
        maw.add("UGUUCAGCAGAGAUUCUCUCUGUCGAGG");
        maw.add("UGCUUAUCUUCAUUCGAUCCUGGUGAGC");
        maw.add("UGGCAUUUGUUUGAUGUUUGUGAGUUGGG");
        maw.add("UGAGUCUGGAAUUGUGGAUGGAAGCU");
        maw.add("AAUUGUGAGACAAAUCUGGUGUGGAAA");
        maw.add("UGAGAGGGUCAGAGCUGCUGAACAGG");
        maw.add("UCUAAUAAUGGAUGUGGUUGCUGG");
        maw.add("UAGCAGUGAGGUCAAGGAAGAGGAAGC");
        maw.add("UGAAGAGUAAGGAUGUUGGUGUGU");
        maw.add("UAAGAGAUUACAGGAUGUCGUAUUG");
        maw.add("UUCAACUAGGAUGUGGUGGGAACUUGAAAC");

    }

    private void marshalSummarized(String nameXML) throws Exception {

        File result = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+ "/Summarized-"+ nameXML + ".xml");

        Style style = new HyphenStyle();
        Format format = new Format(style);
        Serializer serializer = new Persister(format);

        serializer.write(abed, result);

        Toast.makeText(getApplicationContext(), ""+result, Toast.LENGTH_SHORT).show();

    }

    public void runEverything(View view) {

        if(runButton.getText().toString().equalsIgnoreCase("run")){
        sb = new StringBuilder();
        firstSeq = firstString.getText().toString();

        secondSeq = secondString.getText().toString();

        SimilaritiesSet.firstSeq = firstSeq;
        SimilaritiesSet.secondSeq = secondSeq;

        if (setRadio.isChecked()) {
            sb.append(SimilaritiesSet.set());
        }

        if (setMultiRadio.isChecked()) {
            sb.append(SimilaritiesSet.multiSet());
        }
        if (jaccardRadio.isChecked()) {
            sb.append("Jaccard: " + SimilaritiesSet.jaccardSim() + "\n");

        }
        if (jaccardMultiRadio.isChecked()) {
            sb.append("MultiJaccard: " + SimilaritiesSet.multiJaccardSim() + "\n");
        }
        if (diceRadio.isChecked()) {
            sb.append("Dice: " + SimilaritiesSet.diceSim() + "\n");
        }
        if (diceMultiRadio.isChecked()) {
            sb.append("MultiDice: " + SimilaritiesSet.multiDiceSim() + "\n");
        }
        if (setMultiRadio.isChecked()) {
            sb.append("Intersection: " + SimilaritiesSet.intersectionSim() + "\n");
        }
        if (setMultiRadio.isChecked()) {
            sb.append("MultiIntersection: " + SimilaritiesSet.multiIntersectionSim() + "\n");
        }
        if (editRadio.isChecked() && similarityRadio.isChecked()) {
            double distance = getDistance(firstSeq.toUpperCase(), secondSeq.toUpperCase());
            sb.append("EditDistance: " + distance + "\n");
            sb.append("Similarity: " + 1 / (distance + 1) + "\n");
        }


        sb.append("\nVector Based\n");


        S1 = firstSeq;
        S2 = secondSeq;

        if (IDFTDFRadio.isChecked()) {
            //TF-IDF
            tm1 = VectorBased.getTF_IDFMap(firstSeq, secondSeq).get(0);
            tm2 = VectorBased.getTF_IDFMap(firstSeq, secondSeq).get(1);
            sb.append("Vector 1: " + tm1 + "\n");
            sb.append("Vector 2: " + tm2 + "\n");

        }

        if (IDFRadio.isChecked()) {
            //IDF
            tm1 = VectorBased.getIDFCompare(firstSeq, secondSeq).get(0);
            tm2 = VectorBased.getIDFCompare(firstSeq, secondSeq).get(1);
            sb.append("Vector 1: " + tm1 + "\n");
            sb.append("Vector 2: " + tm2 + "\n");

        }
        if (TDFRadio.isChecked()) {
            //TF
            tm1 = VectorBased.getTFMap(firstSeq);
            tm2 = VectorBased.getTFMap(secondSeq);
            sb.append("Vector 1: " + tm1 + "\n");
            sb.append("Vector 2: " + tm2 + "\n");

        }
        if (cosineVectorRadio.isChecked()) {

            sb.append("Cosine: " + computeCosineSimilarity(tm1, tm2) + "\n");

        }
        if (pearsonVectorRadio.isChecked()) {
            computePearsonCoefficient(tm1, tm2);
            sb.append("Pearson: " + computePearsonCoefficient(tm1, tm2) + "\n");

        }
        if (euclidianVectorRadio.isChecked()) {
            sb.append("Euclidian: " + computeEuclidianDistance(tm1, tm2) + "\n");

        }
        if (manhattanVectorRadio.isChecked()) {
            computeManhattanDistance(tm1, tm2);
            sb.append("Manhattan: " + computeManhattanDistance(tm1, tm2) + "\n");

        }
        if (tanimotoVectorRadio.isChecked()) {
            computeTanimotoDistance(tm1, tm2);
            sb.append("Tanimoto: " + computeTanimotoDistance(tm1, tm2) + "\n");

        }
        if (diceVectorRadio.isChecked()) {
            computeDiceDistance(tm1, tm2);
            sb.append("Dice: " + computeDiceDistance(tm1, tm2) + "\n");

        }

        String abed = sb.toString();


        Intent intent = new Intent(this, ResultsActivty.class);
        intent.putExtra("values", abed);
        startActivity(intent);
    }
        else{
            firstSeq = searchEditText.getText().toString();

            similarities = new HashMap<String, Double>();

            SimilaritiesSet.firstSeq = searchEditText.getText().toString();

            if (setRadio.isChecked()) {
                if (jaccardRadio.isChecked()) {
                    for(int i=0;i<abed.sizeUpdate();i++){
                        SimilaritiesSet.secondSeq = abed.getUpdate(i);
                        SimilaritiesSet.set();
                        similarities.put(abed.getUpdate(i),SimilaritiesSet.jaccardSim()
                        );
                    }

                }
                if (jaccardMultiRadio.isChecked()) {
                    for(int i=0;i<abed.sizeUpdate();i++){
                        SimilaritiesSet.secondSeq = abed.getUpdate(i);
                        SimilaritiesSet.set();
                        similarities.put(abed.getUpdate(i),SimilaritiesSet.multiJaccardSim()
                        );
                    }
                }
                if (diceRadio.isChecked()) {
                    for(int i=0;i<abed.sizeUpdate();i++){
                        SimilaritiesSet.secondSeq = abed.getUpdate(i);
                        SimilaritiesSet.set();
                        similarities.put(abed.getUpdate(i),SimilaritiesSet.diceSim()
                        );
                    }
                }
                if (diceMultiRadio.isChecked()) {
                    for(int i=0;i<abed.sizeUpdate();i++){
                        SimilaritiesSet.secondSeq = abed.getUpdate(i);
                        SimilaritiesSet.set();
                        similarities.put(abed.getUpdate(i),SimilaritiesSet.multiDiceSim()
                        );
                    }
                }
                if (intersectionRadio.isChecked()) {
                    for(int i=0;i<abed.sizeUpdate();i++){
                        SimilaritiesSet.secondSeq = abed.getUpdate(i);
                        SimilaritiesSet.set();
                        similarities.put(abed.getUpdate(i),SimilaritiesSet.intersectionSim()
                        );
                    }
                }
                if (intersectionMultiRadio.isChecked()) {
                    for(int i=0;i<abed.sizeUpdate();i++){
                        SimilaritiesSet.secondSeq = abed.getUpdate(i);
                        SimilaritiesSet.set();
                        similarities.put(abed.getUpdate(i),SimilaritiesSet.multiIntersectionSim()
                        );
                    }
                }
            }

            if (setMultiRadio.isChecked()) {
                if (jaccardRadio.isChecked()) {
                    for(int i=0;i<abed.sizeUpdate();i++){
                        SimilaritiesSet.secondSeq = abed.getUpdate(i);
                        SimilaritiesSet.multiSet();
                        similarities.put(abed.getUpdate(i),SimilaritiesSet.jaccardSim()
                        );
                    }

                }
                if (jaccardMultiRadio.isChecked()) {
                    for(int i=0;i<abed.sizeUpdate();i++){
                        SimilaritiesSet.secondSeq = abed.getUpdate(i);
                        SimilaritiesSet.multiSet();
                        similarities.put(abed.getUpdate(i),SimilaritiesSet.multiJaccardSim()
                        );
                    }
                }
                if (diceRadio.isChecked()) {
                    for(int i=0;i<abed.sizeUpdate();i++){
                        SimilaritiesSet.secondSeq = abed.getUpdate(i);
                        SimilaritiesSet.multiSet();
                        similarities.put(abed.getUpdate(i),SimilaritiesSet.diceSim()
                        );
                    }
                }
                if (diceMultiRadio.isChecked()) {
                    for(int i=0;i<abed.sizeUpdate();i++){
                        SimilaritiesSet.secondSeq = abed.getUpdate(i);
                        SimilaritiesSet.multiSet();
                        similarities.put(abed.getUpdate(i),SimilaritiesSet.multiDiceSim()
                        );
                    }
                }
                if (intersectionRadio.isChecked()) {
                    for(int i=0;i<abed.sizeUpdate();i++){
                        SimilaritiesSet.secondSeq = abed.getUpdate(i);
                        SimilaritiesSet.multiSet();
                        similarities.put(abed.getUpdate(i),SimilaritiesSet.intersectionSim()
                        );
                    }
                }
                if (intersectionMultiRadio.isChecked()) {
                    for(int i=0;i<abed.sizeUpdate();i++){
                        SimilaritiesSet.secondSeq = abed.getUpdate(i);
                        SimilaritiesSet.multiSet();
                        similarities.put(abed.getUpdate(i),SimilaritiesSet.multiIntersectionSim()
                        );
                    }
                }
            }

            if (editRadio.isChecked() && similarityRadio.isChecked()) {
                for(int i=0;i<abed.sizeUpdate();i++){
                    SimilaritiesSet.secondSeq = abed.getUpdate(i);
                    double distance = getDistance(firstSeq, abed.getUpdate(i));
                    similarities.put(abed.getUpdate(i),1 / (distance + 1)
                    );
                }

            }

            S1 = firstSeq;
            S2 = secondSeq;

            if (IDFRadio.isChecked()) {
                //TF-IDF
                tm = VectorBased.getIDFSearch(firstSeq, maw);
                if (cosineVectorRadio.isChecked()) {
                    for(int i=1;i<abed.sizeUpdate();i++){

                        similarities.put(abed.getUpdate(i),computeCosineSimilarity(tm.get(0),tm.get(i))
                        );
                    }

                }
                if (pearsonVectorRadio.isChecked()) {
                    for(int i=1;i<abed.sizeUpdate();i++){
                        similarities.put(abed.getUpdate(i),computePearsonCoefficient(tm.get(0),tm.get(i))
                        );
                    }
                }
                if (euclidianVectorRadio.isChecked()) {
                    for(int i=1;i<abed.sizeUpdate();i++){

                        similarities.put(abed.getUpdate(i),computeEuclidianDistance(tm.get(0),tm.get(i))
                        );
                    }
                }
                if (manhattanVectorRadio.isChecked()) {
                    for(int i=1;i<abed.sizeUpdate();i++){

                        similarities.put(abed.getUpdate(i),computeManhattanDistance(tm.get(0),tm.get(i))
                        );
                    }
                }
                if (tanimotoVectorRadio.isChecked()) {
                    for(int i=1;i<abed.sizeUpdate();i++){

                        similarities.put(abed.getUpdate(i),computeTanimotoDistance(tm.get(0),tm.get(i))
                        );
                    }
                }
                if (diceVectorRadio.isChecked()) {
                    for(int i=1;i<abed.sizeUpdate();i++){

                        similarities.put(abed.getUpdate(i),computeDiceDistance(tm.get(0),tm.get(i))
                        );
                    }
                }



            }

            if (IDFTDFRadio.isChecked()) {
                //IDF
                tm1 = VectorBased.getIDFCompare(firstSeq, secondSeq).get(0);
                tm2 = VectorBased.getIDFCompare(firstSeq, secondSeq).get(1);
                sb.append("Vector 1: " + tm1 + "\n");
                sb.append("Vector 2: " + tm2 + "\n");

            }
            if (TDFRadio.isChecked()) {
                //TF
                tm1 = VectorBased.getTFMap(firstSeq);
                tm2 = VectorBased.getTFMap(secondSeq);
                if (cosineVectorRadio.isChecked()) {
                    for(int i=0;i<abed.sizeUpdate();i++){
                        tm1 = VectorBased.getTFMap(firstSeq);
                        tm2 = VectorBased.getTFMap(abed.getUpdate(i));
                        similarities.put(abed.getUpdate(i),computeCosineSimilarity(tm1,tm2)
                        );
                    }

                }
                if (pearsonVectorRadio.isChecked()) {
                    for(int i=0;i<abed.sizeUpdate();i++){
                        tm1 = VectorBased.getTFMap(firstSeq);
                        tm2 = VectorBased.getTFMap(abed.getUpdate(i));
                        similarities.put(abed.getUpdate(i),computePearsonCoefficient(tm1,tm2)
                        );
                    }
                }
                if (euclidianVectorRadio.isChecked()) {
                    for(int i=0;i<abed.sizeUpdate();i++){
                        tm1 = VectorBased.getTFMap(firstSeq);
                        tm2 = VectorBased.getTFMap(abed.getUpdate(i));
                        similarities.put(abed.getUpdate(i),computeEuclidianDistance(tm1,tm2)
                        );
                    }
                }
                if (manhattanVectorRadio.isChecked()) {
                    for(int i=0;i<abed.sizeUpdate();i++){
                        tm1 = VectorBased.getTFMap(firstSeq);
                        tm2 = VectorBased.getTFMap(abed.getUpdate(i));
                        similarities.put(abed.getUpdate(i),computeManhattanDistance(tm1,tm2)
                        );
                    }
                }
                if (tanimotoVectorRadio.isChecked()) {
                    for(int i=0;i<abed.sizeUpdate();i++){
                        tm1 = VectorBased.getTFMap(firstSeq);
                        tm2 = VectorBased.getTFMap(abed.getUpdate(i));
                        similarities.put(abed.getUpdate(i),computeTanimotoDistance(tm1,tm2)
                        );
                    }
                }
                if (diceVectorRadio.isChecked()) {
                    for(int i=0;i<abed.sizeUpdate();i++){
                        tm1 = VectorBased.getTFMap(firstSeq);
                        tm2 = VectorBased.getTFMap(abed.getUpdate(i));
                        similarities.put(abed.getUpdate(i),computeDiceDistance(tm1,tm2)
                        );
                    }
                }
            }
            if (cosineVectorRadio.isChecked()) {

                sb.append("Cosine: " + computeCosineSimilarity(tm1, tm2) + "\n");

            }
            if (pearsonVectorRadio.isChecked()) {
                computePearsonCoefficient(tm1, tm2);
                sb.append("Pearson: " + computePearsonCoefficient(tm1, tm2) + "\n");

            }
            if (euclidianVectorRadio.isChecked()) {
                sb.append("Euclidian: " + computeEuclidianDistance(tm1, tm2) + "\n");

            }
            if (manhattanVectorRadio.isChecked()) {
                computeManhattanDistance(tm1, tm2);
                sb.append("Manhattan: " + computeManhattanDistance(tm1, tm2) + "\n");

            }
            if (tanimotoVectorRadio.isChecked()) {
                computeTanimotoDistance(tm1, tm2);
                sb.append("Tanimoto: " + computeTanimotoDistance(tm1, tm2) + "\n");

            }
            if (diceVectorRadio.isChecked()) {
                computeDiceDistance(tm1, tm2);
                sb.append("Dice: " + computeDiceDistance(tm1, tm2) + "\n");

            }
           similarities =  sortByValue(similarities);
            Intent intent = new Intent(this, SearchActivity.class);
            intent.putExtra("values", similarities);
            startActivity(intent);
        }
    }
    public static HashMap<String, Double> sortByValue(HashMap<String, Double> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Double> > list =
                new LinkedList<Map.Entry<String, Double> >(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Double> >() {
            public int compare(Map.Entry<String, Double> o1,
                               Map.Entry<String, Double> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<String, Double> temp = new LinkedHashMap<String, Double>();
        for (Map.Entry<String, Double> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    public void open_set(View view) {
        opened_set.setVisibility(View.VISIBLE);
        set_textView.setVisibility(View.GONE);

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(main_constraint);
        constraintSet.connect(R.id.vector_textView,ConstraintSet.TOP,R.id.opened_set,ConstraintSet.BOTTOM,30);
        constraintSet.connect(R.id.opened_vector,ConstraintSet.TOP,R.id.opened_set,ConstraintSet.BOTTOM,30);

        constraintSet.applyTo(main_constraint);
    }

    public void open_vector(View view) {
        opened_vector.setVisibility(View.VISIBLE);
        vector_textView.setVisibility(View.GONE);

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(main_constraint);
        constraintSet.connect(R.id.runButton,ConstraintSet.TOP,R.id.opened_vector,ConstraintSet.BOTTOM,40);

        constraintSet.applyTo(main_constraint);

    }

    public void close_vector(View view) {
        vector_textView.setVisibility(View.VISIBLE);
        opened_vector.setVisibility(View.GONE);

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(main_constraint);
        constraintSet.connect(R.id.runButton,ConstraintSet.TOP,R.id.vector_textView,ConstraintSet.BOTTOM,40);

        constraintSet.applyTo(main_constraint);
    }

    public void close_set(View view) {
        set_textView.setVisibility(View.VISIBLE);
        opened_set.setVisibility(View.GONE);

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(main_constraint);
        constraintSet.connect(R.id.vector_textView,ConstraintSet.TOP,R.id.set_textView,ConstraintSet.BOTTOM,30);
        constraintSet.connect(R.id.opened_vector,ConstraintSet.TOP,R.id.set_textView,ConstraintSet.BOTTOM,30);

        constraintSet.applyTo(main_constraint);
    }

    public void search(View view) {
        Intent intent = new Intent(this,SearchActivity.class);
        startActivity(intent);
    }
    public static double getDistance(String str1, String str2) {

        //Getting the length of each string
        int l1 = str1.length();
        int l2 = str2.length();

        //If one of the strings is empty, return the length of the other
        if (l1 == 0) return l2;
        if (l2 == 0) return l1;

        //Creating the "table"
        arr = new double[l1 + 1][l2 + 1];

        for (int i = 0; i <= l1; i++) arr[i][0] = i*insertWeight; //Insertion at row 0
        for (int i = 0; i <= l2; i++) arr[0][i] = i*deleteWeight; //Deletion at column 0

        //Operations
        for (int i = 1; i <= l1; i++) {
            char ch1 = str1.charAt(i - 1);

            for (int j = 1; j <= l2; j++) {
                char ch2 = str2.charAt(j - 1);

                switch (ch1) {
                    case 'R':
                        if (ch2 == 'G' || ch2 == 'A' || ch2 == 'R') {
                            calculate(0.5, i, j); // Expected distance of 0.5
                        } else if (ch2 == 'M' || ch2 == 'S' || ch2 == 'N') {
                            calculate(0.75, i, j); // Expected distance of 0.75
                        } else if (ch2 == 'V') {
                            calculate(0.67, i, j);// Expected distance of 2/3
                        } else calculate(1, i, j); // Default case of 1
                        break;

                    case 'M':
                        if (ch2 == 'C' || ch2 == 'A' || ch2 == 'm' || ch2 == 'M') {
                            calculate(0.5, i, j);// Expected distance of 0.5
                        } else if (ch2 == 'r' || ch2 == 'R' || ch2 == 's' || ch2 == 'S' || ch2 == 'n' || ch2 == 'N')
                            calculate(0.75, i, j);// Expected distance of 0.75

                        else if (ch2 == 'v' || ch2 == 'V') {
                            calculate(0.67, i, j);// Expected distance of 2/3
                        } else calculate(1, i, j);// Default case of 1
                        break;

                    case 'S':
                        if (ch2 == 'G' || ch2 == 'S' || ch2 == 'C') {
                            calculate(0.5, i, j);// Expected distance of 0.5
                        } else if (ch2 == 'R' || ch2 == 'M' || ch2 == 'N')
                            calculate(0.75, i, j);// Expected distance of 0.75
                        else if (ch2 == 'V') {
                            calculate(0.67, i, j);// Expected distance of 2/3
                        } else calculate(1, i, j);// Default case of 1

                        break;

                    case 'V':
                        if (ch2 == 'S' || ch2 == 'R' || ch2 == 'M' || ch2 == 'G' || ch2 == 'A' || ch2 == 'N' || ch2 == 'C') {
                            calculate(0.67, i, j); // Expected distance of 2/3
                        } else if (ch2 == 'V') {
                            calculate(0.75, i, j); // Expected distance of 0.75
                        } else {
                            calculate(1, i, j); // Default case of 1
                        }
                        break;

                    case 'N':
                        if (ch2 == 'G' || ch2 == 'A' || ch2 == 'S' || ch2 == 'C' || ch2 == 'N' || ch2 == 'U' || ch2 == 'R' ||
                                ch2 == 'M' || ch2 == 'V') {
                            calculate(0.75, i, j);// Expected distance of 0.75
                        } else {
                            calculate(1, i, j);// Default case of 1
                        }
                        break;

                    default: // If none of the characters above are mentioned, go through the normal case

                        switch (ch2) {
                            case 'R':
                                if (ch1 == 'G' || ch1 == 'A' || ch1 == 'R') {
                                    calculate(0.5, i, j); // Expected distance of 0.5
                                } else if (ch1 == 'M' || ch1 == 'S' || ch1 == 'N') {
                                    calculate(0.75, i, j); // Expected distance of 0.75
                                } else if (ch1 == 'V') {
                                    calculate(0.67, i, j);// Expected distance of 2/3
                                } else calculate(1, i, j); // Default case of 1
                                break;

                            case 'M':
                                if (ch1 == 'C' || ch1 == 'A' || ch1 == 'm' || ch1 == 'M') {
                                    calculate(0.5, i, j);// Expected distance of 0.5
                                } else if (ch1 == 'r' || ch1 == 'R' || ch1 == 's' || ch2 == 'S' || ch1 == 'n' || ch1 == 'N')
                                    calculate(0.75, i, j);// Expected distance of 0.75

                                else if (ch1 == 'v' || ch1 == 'V') {
                                    calculate(0.67, i, j);// Expected distance of 2/3
                                } else calculate(1, i, j);// Default case of 1
                                break;

                            case 'S':
                                if (ch1 == 'G' || ch1 == 'S' || ch1 == 'C') {
                                    calculate(0.5, i, j);// Expected distance of 0.5
                                } else if (ch1	 == 'R' || ch1 == 'M' || ch1 == 'N')
                                    calculate(0.75, i, j);// Expected distance of 0.75
                                else if (ch2 == 'V') {
                                    calculate(0.67, i, j);// Expected distance of 2/3
                                } else calculate(1, i, j);// Default case of 1

                                break;

                            case 'V':
                                if (ch1 == 'S' || ch1 == 'R' || ch1 == 'M' || ch1 == 'G' || ch1 == 'A' || ch1 == 'N' || ch1 == 'C') {
                                    calculate(0.67, i, j); // Expected distance of 2/3
                                } else if (ch1 == 'V') {
                                    calculate(0.75, i, j); // Expected distance of 0.75
                                } else {
                                    calculate(1, i, j); // Default case of 1
                                }
                                break;

                            case 'N':
                                if (ch1 == 'G' || ch1 == 'A' || ch1 == 'S' || ch1 == 'C' || ch1 == 'N' || ch1 == 'U' || ch1 == 'R' ||
                                        ch1 == 'M' || ch1 == 'V') {
                                    calculate(0.75, i, j);// Expected distance of 0.75
                                } else {
                                    calculate(1, i, j);// Default case of 1
                                }
                                break;

                            default:
                                if (ch1 == ch2)
                                    calculate(0, i, j);
                                else calculate(1, i, j);
                        }
                }
            }
        }

        // converting each row to a string
        // and then printing in a separate line
        for (double[] row : arr) System.out.println(Arrays.toString(row));
        return arr[l1][l2];

    }

    public static void calculate(double weight, int i, int j) {
        //In order to avoid decimals in the array, we work with the LCM of 3 and 4, which is 12
        // 12 -> full weight

        if(weight == 0){
            ins = (arr[i][j-1] + insertWeight * 12);
            del = (arr[i-1][j] + deleteWeight * 12);
            upd = (arr[i - 1][j - 1]);
        }
        else {
            ins = (arr[i - 1][j] + insertWeight * weight);
            del = (arr[i][j - 1] + deleteWeight * weight);
            upd = (arr[i - 1][j - 1] + weight * updateWeight);
        }


        //Insert
        if (ins < del && ins < upd) {
            arr[i][j] = ins;
        }

        //Delete
        else if (del < upd) {
            arr[i][j] = del;
        }

        //Update
        else {
            arr[i][j] = upd;
        }
    }

    public void open_edit(View view) {
        opened_edit.setVisibility(View.VISIBLE);
        edit_textView.setVisibility(View.GONE);

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(main_constraint);
        constraintSet.connect(R.id.opened_set,ConstraintSet.TOP,R.id.opened_edit,ConstraintSet.BOTTOM,30);
        constraintSet.connect(R.id.set_textView,ConstraintSet.TOP,R.id.opened_edit,ConstraintSet.BOTTOM,30);

        constraintSet.applyTo(main_constraint);
    }

    public void close_edit(View view) {
        edit_textView.setVisibility(View.VISIBLE);
        opened_edit.setVisibility(View.GONE);

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(main_constraint);
        constraintSet.connect(R.id.opened_set,ConstraintSet.TOP,R.id.edit_textView,ConstraintSet.BOTTOM,30);
        constraintSet.connect(R.id.set_textView,ConstraintSet.TOP,R.id.edit_textView,ConstraintSet.BOTTOM,30);

        constraintSet.applyTo(main_constraint);
    }


    public static double[] convertToArray(HashMap<Character, Double> tm12){


        double [] array = new double[tm12.size()];
        Object[] words = tm12.keySet().toArray();

        for (int i = 0 ; i<tm12.size(); i++){
            array [i] = Double.parseDouble(String.valueOf(tm12.get(words[i])));
        }
        return array;
    }

    public static double computeCosineSimilarity(HashMap<Character, Double> tm1, HashMap<Character, Double> tm2){

        double[] A = convertToArray(tm1);
        double[] B = convertToArray(tm2);


        double sim;
        double sum = 0;
        double denom;
        double [] comps = new double[A.length];
        for (int i = 0 ; i<A.length;i++){
            comps[i]= A[i]*B[i];
            sum += comps[i];
        }

//        System.out.println("Comps:");
//        for (int i : comps)
//            System.out.print (i+ " ");

        double a=0,b = 0;
        for (double value : A) {
            a += Math.pow(value, 2);
        }

        double sqrt1 = Math.sqrt(a);
        for (double v : B) {
            b += Math.pow(v, 2);
        }

        double sqrt2 = Math.sqrt(b);

        denom = sqrt1 * sqrt2;
        sim = sum/denom;

        //System.out.println("\nNumerator: " + sum +"\nDenominator: " + denom +"\nSimilarity: " + sim);
        return sim;
    }

    public static double computePearsonCoefficient (HashMap<Character, Double> tm1, HashMap<Character, Double> tm2){

        double[] A = convertToArray(tm1);
        double[] B = convertToArray(tm2);
        double avgA = average(A);
        double avgB = average(B);

        double [] newArray = new double[A.length];
        double numerator=0;
        double denom,denom1=0,denom2=0;
        double similarity ;
        for (int i =0 ; i<A.length;i++)
        {
            newArray [i] = (A[i]-avgA)*(B[i]-avgB);
            numerator += newArray[i];
            denom1 += Math.pow(A[i]-avgA,2);
            denom2 += Math.pow(B[i]-avgB,2);
        }
        denom = Math.sqrt(denom1*denom2);
//        System.out.println("Average1: "+avgA +"\nAverage2: "+avgB);
//        System.out.println("Numerator: " + numerator);
//        System.out.println("Denominator: " + Math.sqrt(denom));
//        System.out.println("\nNew array: ");
//        for (double i : newArray )
//            System.out.print(i +" ");
        similarity = numerator / denom;
        return similarity;
    }

    public static double average(double[] a) {
        double average;
        double sum = 0 ;
        for (double v : a) {
            sum += v;
        }

        average = sum/ a.length;

        return average;
    }

    public static double computeEuclidianDistance (HashMap<Character, Double> tm1, HashMap<Character, Double> tm2){

        double[] A = convertToArray(tm1);
        double[] B = convertToArray(tm2);
        double similarity;
        double euclid=0;
        for (int i = 0 ; i < A.length;i++){
            euclid += Math.pow(A[i]-B[i],2);
        }
        //System.out.print("Euclid: " + euclid);
        similarity = 1/ (1+ Math.sqrt(euclid));
        return similarity;
    }

    public static double computeManhattanDistance (HashMap<Character, Double> tm1, HashMap<Character, Double> tm2){

        double[] A = convertToArray(tm1);
        double[] B = convertToArray(tm2);
        double similarity;
        double manhattan = 0 ;
        for (int i = 0 ; i < A.length;i++){

            manhattan += Math.abs(A[i]-B[i]);
        }
//        System.out.print("Manhattan: " + manhattan);
        similarity = 1/ (1+ manhattan);
        return similarity;
    }

    public static double computeTanimotoDistance  (HashMap<Character, Double> tm1, HashMap<Character, Double> tm2){

        double[] A = convertToArray(tm1);
        double[] B = convertToArray(tm2);
        double scalar = computeScalarProduct(A,B);
        double moduleA = computeModule(A);
        double moduleB = computeModule(B);
        double similarity = scalar / (Math.pow(moduleA,2) + Math.pow(moduleB,2) - scalar);

//        System.out.println ("Scalar " + scalar);
//        System.out.println("Module A " + moduleA);
//        System.out.println("Module B " + moduleB);

        // double distance = 1 - similarity;
        return similarity;
    }

    public static double computeScalarProduct (double [] A, double[]B) {
        double scalar = 0;
        for (int i = 0; i < A.length; i++) {
            scalar += A[i] * B[i];
        }
        return scalar;
    }

    public static double computeModule (double [] A){

        double moduleA = 0 ;
        for (double v : A) {
            moduleA += Math.pow(v, 2);
        }

        return Math.sqrt(moduleA);
    }

    public static double computeDiceDistance (HashMap<Character, Double> tm1, HashMap<Character, Double> tm2){

        double[] A = convertToArray(tm1);
        double[] B = convertToArray(tm2);
        double moduleA = computeModule(A);
        double moduleB = computeModule(B);
        double scalar = computeScalarProduct(A,B);

        double similarity = (2*scalar)/(Math.pow(moduleA,2) + Math.pow(moduleB,2));

        // double distance = 1 - similarity;

        return similarity;

    }


    //To form the vectors (Term Frequencies)
    public static HashMap<Character, Double> getTFMap(String firstSeq) {
        Double val;
        Character character;

        HashMap<Character,Double> map1 = new HashMap<>();
        for (int i = 0; i < rna.length; i++) {
            if(map1.get(rna[i]) == null)map1.put(rna[i], 0.0);

        }
        for (int i = 0; i<firstSeq.length(); i++) {

            character = firstSeq.charAt(i);


            if (character == 'R') {

                map1.put('G', map1.get('G') + 0.5);
                map1.put('A', map1.get('A') + 0.5);
            }

            else if (character == 'M') {

                map1.put('C', map1.get('C') + 0.5);
                map1.put('A', map1.get('A') + 0.5);
            }

            else if (character == 'S') {

                map1.put('G', map1.get('G') + 0.5);
                map1.put('C', map1.get('C') + 0.5);
            }

            else if (character == 'V') {

                map1.put('G', map1.get('G') + 1.0/3.0);
                map1.put('A', map1.get('A') + 1.0/3.0);
                map1.put('C', map1.get('C') + 1.0/3.0);
            }

            else if (character == 'N') {
                map1.put('G', map1.get('G') + 0.25);
                map1.put('A', map1.get('A') + 0.25);
                map1.put('C', map1.get('C') + 0.25);
                map1.put('U', map1.get('U') + 0.25);
            }

            else {
                val = map1.get(character);
                if (val == null)val = 0.0;
                map1.put(character, val + 1);
            }
        }

        return map1;

    }


    public static ArrayList<HashMap<Character, Double>> getIDFCompare (String firstSeq, String secondSeq){

        Character character;

        HashMap<Character,Double> DF = new HashMap<>();
        HashMap<Character,Double> IDF = new HashMap<>();

        HashMap<Character,Double> secIDF = new HashMap<>();
        DF.put('A',0.0);
        DF.put('G',0.0);
        DF.put('U',0.0);
        DF.put('C',0.0);

        secIDF.put('A',1.0);
        secIDF.put('G',1.0);
        secIDF.put('U',1.0);
        secIDF.put('C',1.0);



        for (int i = 0; i<firstSeq.length(); i++) {
            character = firstSeq.charAt(i);
            if (character == 'R') {
                if (secondSeq.indexOf('A')!=-1)DF.put('A', DF.get('A') + 0.5);	else secIDF.put('A', 0.0);
                if (secondSeq.indexOf('G')!=-1)	DF.put('G',DF.get('G') + 0.5 );	else secIDF.put('G', 0.0);
            }

            else if (character == 'M') {

                if (secondSeq.indexOf('A')!=-1)DF.put('A', DF.get('A') + 0.5);	else secIDF.put('A', 0.0);
                if (secondSeq.indexOf('C')!=-1)	DF.put('C',DF.get('C') + 0.5 );	else secIDF.put('C', 0.0);
            }

            else if (character == 'S') {

                if (secondSeq.indexOf('A')!=-1)DF.put('G', DF.get('G') + 0.5);	else secIDF.put('G', 0.0);
                if (secondSeq.indexOf('C')!=-1)	DF.put('C',DF.get('C') + 0.5 );	else secIDF.put('C', 0.0);
            }

            else if (character == 'V') {

                if (secondSeq.indexOf('A')!=-1)DF.put('A', DF.get('A') + 1.0/3.0);	else secIDF.put('A', 0.0);
                if (secondSeq.indexOf('C')!=-1)	DF.put('C',DF.get('C') + 1.0/3.0 ); else secIDF.put('C', 0.0);
                if (secondSeq.indexOf('G')!=-1)	DF.put('G',DF.get('G') + 1.0/3.0 );		else secIDF.put('G', 0.0);
            }

            else if (character == 'N') {
                if (secondSeq.indexOf('A')!=-1) DF.put('A', DF.get('A') + 0.25); else secIDF.put('A', 0.0);
                if (secondSeq.indexOf('G')!=-1)	DF.put('G', DF.get('G') + 0.25 ); else secIDF.put('G', 0.0);
                if (secondSeq.indexOf('U')!=-1) DF.put('U', DF.get('U') + 0.25); else secIDF.put('U', 0.0);
                if (secondSeq.indexOf('C')!=-1)	DF.put('C', DF.get('C') + 0.25 ); else secIDF.put('C', 0.0);
            }

            else {
                if(secondSeq.indexOf(character) != -1)DF.put(character, 2.0);
                else {
                    secIDF.put(character, 0.0);
                    DF.put(character, 1.0);
                }
            }

        }

        System.out.println(DF);

        IDF.put('A',Math.log10(2/DF.get('A')));
        IDF.put('G',Math.log10(2/DF.get('G')));
        IDF.put('U',Math.log10(2/DF.get('U')));
        IDF.put('C',Math.log10(2/DF.get('C')));

        secIDF.put('A',secIDF.get('A') * Math.log10(2/DF.get('A')));
        secIDF.put('G',secIDF.get('G') * Math.log10(2/DF.get('G')));
        secIDF.put('U',secIDF.get('U') * Math.log10(2/DF.get('U')));
        secIDF.put('C',secIDF.get('C') * Math.log10(2/DF.get('C')));



        ArrayList<HashMap<Character, Double>> arrays = new ArrayList<HashMap<Character, Double>>();

        arrays.add(IDF);
        arrays.add(secIDF);

        return arrays;

    }

    /*public static void getIDFSearch(String firstSeq) {
    	double idf = 0;
    	int df = 0;

    	for (int i = 0; i <N; i++) {

    	}
    }
    */

    public static ArrayList<HashMap<Character, Double>> getTF_IDFMap (String firstSeq, String secondSeq){

        HashMap<Character, Double> TF_Map1 = getTFMap(firstSeq);
        HashMap<Character, Double> TF_Map2 = getTFMap(secondSeq);

        ArrayList<HashMap<Character, Double>> ar = getIDFCompare(firstSeq, secondSeq);
        ArrayList<HashMap<Character, Double>> TF_IDF_ar = new ArrayList <>();

        HashMap<Character, Double> IDF_Map1 = ar.get(0);
        HashMap<Character, Double> IDF_Map2 = ar.get(1);


        HashMap<Character, Double> TF_IDF_Map1 = new HashMap<>();
        HashMap<Character, Double> TF_IDF_Map2 = new HashMap<>();

        for (int i = 0; i < rna.length; i++) {
            TF_IDF_Map1.put(rna[i],(TF_Map1.get(rna[i]) * IDF_Map1.get(rna[i])));
            TF_IDF_Map2.put(rna[i],(TF_Map2.get(rna[i]) * IDF_Map2.get(rna[i])));
        }

        TF_IDF_ar.add(TF_IDF_Map1);
        TF_IDF_ar.add(TF_IDF_Map2);

        return TF_IDF_ar;

    }
}