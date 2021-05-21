package idpa.makalgorithm;

import java.util.*;

public class VectorBased {

    static String S1;
    static String S2;
    public static char[] rna = {'A', 'C', 'G', 'U'};

    public static HashMap<Character, Double> tm1 = new HashMap<Character, Double>();
    public static HashMap<Character, Double> tm2 = new HashMap<Character, Double>();


    public static void main(String[]args){





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




    public static ArrayList<HashMap<Character, Double>> getIDFSearch (String firstSeq,ArrayList<String> maw){

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
        for (int i = 0; i<firstSeq.length(); i++){
            character = firstSeq.charAt(i);
            DF.put(character, 1.0);
        }
        for(int i = 0; i<maw.size();i++){
            String secondSeq = maw.get(i);
            for (int j = 0; j<firstSeq.length(); j++) {
                character = firstSeq.charAt(j);
                if (character == 'R') {
                    if (secondSeq.indexOf('A')!=-1)DF.put('A', DF.get('A') + 0.5);
                    if (secondSeq.indexOf('G')!=-1)	DF.put('G',DF.get('G') + 0.5 );
                }

                else if (character == 'M') {

                    if (secondSeq.indexOf('A')!=-1)DF.put('A', DF.get('A') + 0.5);
                    if (secondSeq.indexOf('C')!=-1)	DF.put('C',DF.get('C') + 0.5 );
                }

                else if (character == 'S') {

                    if (secondSeq.indexOf('A')!=-1)DF.put('G', DF.get('G') + 0.5);
                    if (secondSeq.indexOf('C')!=-1)	DF.put('C',DF.get('C') + 0.5 );
                }

                else if (character == 'V') {

                    if (secondSeq.indexOf('A')!=-1)DF.put('A', DF.get('A') + 1.0/3.0);
                    if (secondSeq.indexOf('C')!=-1)	DF.put('C',DF.get('C') + 1.0/3.0 );
                    if (secondSeq.indexOf('G')!=-1)	DF.put('G',DF.get('G') + 1.0/3.0 );
                }

                else if (character == 'N') {
                    if (secondSeq.indexOf('A')!=-1) DF.put('A', DF.get('A') + 0.25);
                    if (secondSeq.indexOf('G')!=-1)	DF.put('G', DF.get('G') + 0.25 );
                    if (secondSeq.indexOf('U')!=-1) DF.put('U', DF.get('U') + 0.25);
                    if (secondSeq.indexOf('C')!=-1)	DF.put('C', DF.get('C') + 0.25 );
                }

                else {
                    if(secondSeq.indexOf(character) != -1)DF.put(character, DF.get(character) + 1.0);
                }

            }
        }



        System.out.println(DF);

        IDF.put('A',Math.log10(maw.size()/DF.get('A')));
        IDF.put('G',Math.log10(maw.size()/DF.get('G')));
        IDF.put('U',Math.log10(maw.size()/DF.get('U')));
        IDF.put('C',Math.log10(maw.size()/DF.get('C')));
        ArrayList<HashMap<Character, Double>> arrays = new ArrayList<HashMap<Character, Double>>();
        arrays.add(IDF);


        for(int i = 0; i <maw.size(); i++){
            String meow = maw.get(i);
            System.out.println(meow);
            HashMap<Character, Double> tempHash = new HashMap<>();
            for (int j = 0; j<meow.length(); j++){
                character = firstSeq.charAt(j);
                tempHash.put(character, IDF.get(character));
            }
            for(int j = 0; j< rna.length; j++)if (tempHash.get(rna[j]) == null)tempHash.put(rna[j],0.0);
            System.out.println(tempHash);
            arrays.add(tempHash);
        }








        return arrays;

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
