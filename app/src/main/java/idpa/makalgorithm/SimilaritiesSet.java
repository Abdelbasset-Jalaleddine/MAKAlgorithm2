package idpa.makalgorithm;

import java.util.*;

public class SimilaritiesSet {

   static String firstSeq;
   static String secondSeq;

    public static Set<Character> set1 = new HashSet<Character>();
    public static Set<Character> set2 = new HashSet<Character>();

    public static Map<Character, Double> map1 = new HashMap<Character, Double>();
    public static Map <Character, Double> map2 = new HashMap<Character, Double>();

    public static char[] rna = {'A', 'C', 'G', 'U'};

    public static String set(){
        for (int i = 0; i<firstSeq.length(); i++) {
            Character ch = firstSeq.charAt(i);
            set1.add(ch);
        }

        for (int i = 0; i<secondSeq.length(); i++) {
            set2.add(secondSeq.charAt(i));
        }

        return "Set:\nSet 1: " + set1.toString() + "\nSet 2: " + set2.toString() + "\n";

    }

    public static String multiSet() {
        Double val;
        Character character;

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

        for (int i = 0; i<secondSeq.length(); i++) {

            character = secondSeq.charAt(i);


            if (character == 'R') {

                map2.put('G', map2.get('G') + 0.5);
                map2.put('A', map2.get('A') + 0.5);
            }

            else if (character == 'M') {

                map2.put('C', map2.get('C') + 0.5);
                map2.put('A', map2.get('A') + 0.5);
            }

            else if (character == 'S') {

                map2.put('G', map2.get('G') + 0.5);
                map2.put('C', map2.get('C') + 0.5);
            }

            else if (character == 'V') {

                map2.put('G', map2.get('G') + 1.0/3.0);
                map2.put('A', map2.get('A') + 1.0/3.0);
                map2.put('C', map2.get('C') + 1.0/3.0);
            }

            else if (character == 'N') {
                map2.put('G', map2.get('G') + 0.25);
                map2.put('A', map2.get('A') + 0.25);
                map2.put('C', map2.get('C') + 0.25);
                map2.put('U', map2.get('U') + 0.25);
            }

            else {
                val = map2.get(character);
                if (val == null)val = 0.0;
                map2.put(character, val + 1);
            }
        }

        return "MultiSet\nSet 1: " + map1.toString() + "\nSet 2: " + map2.toString() + "\n";

    }

    public static double intersectionSim() {
        Set<Character> intersection = new HashSet<Character>(set1);
        intersection.retainAll(set2);
        return intersection.size();
    }

    public static double jaccardSim() {
        double similarityJaccard;

        Set<Character> intersection = new HashSet<Character>(set1);
        Set<Character> union = new HashSet<Character>(set1);
        intersection.retainAll(set2);
        union.addAll(set2);
        similarityJaccard = (double) intersection.size() / union.size();
        return similarityJaccard;
    }

    public static double diceSim() {
        double similarityDice;

        Set<Character> intersection = new HashSet<Character>(set1);
        intersection.retainAll(set2);

        similarityDice = 2.0 * intersection.size() / (set1.size() + set2.size());

        return similarityDice;
    }

    public static double multiIntersectionSim() {
        double multiIntersecSim = 0;
        for (int i = 0; i < rna.length; i++) {
            if(map1.get(rna[i]) == null)map1.put(rna[i], 0.0);
            if(map2.get(rna[i]) == null)map2.put(rna[i], 0.0);

            multiIntersecSim += Math.min(map1.get(rna[i]), map2.get(rna[i]));

        }

        return multiIntersecSim;
    }

    public static double multiJaccardSim() {

        double intersec = multiIntersectionSim();
        double multiJacSim = 0;
        double union = 0;

        for (int i = 0; i< rna.length; i++) {
            if(map1.get(rna[i]) == null)map1.put(rna[i], 0.0);
            if(map2.get(rna[i]) == null)map2.put(rna[i], 0.0);

            union += map1.get(rna[i]) + map2.get(rna[i]);
        }

        multiJacSim = intersec / (union - intersec);


        return multiJacSim;
    }

    public static double multiDiceSim() {
        double numerator = 2 * multiIntersectionSim();
        double multiDiceSim = 0;
        double sum = 0;

        for (int i = 0; i< rna.length; i++) {
            if(map1.get(rna[i]) == null)map1.put(rna[i], 0.0);
            if(map2.get(rna[i]) == null)map2.put(rna[i], 0.0);

            sum += map1.get(rna[i]) + map2.get(rna[i]);
        }

        multiDiceSim = numerator / sum;


        return multiDiceSim;
    }

    public static void removeNulls() {
        for (int i = 0; i < rna.length; i++) {
            if(map1.get(rna[i]) == null)map1.put(rna[i], 0.0);
            if(map2.get(rna[i]) == null)map2.put(rna[i], 0.0);
        }
    }

}
