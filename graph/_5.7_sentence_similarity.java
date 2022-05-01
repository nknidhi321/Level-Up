// https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/graphs/sentence_similarity_official/ojquestion#
// Simple DSU.

// You just have to take Map instead of array for both parent and height because the input word for DSU might not exist in the par and height array,
// i.e after performing DSU, You will be getting words from sentences, which does not exist in par and height, so go for map.

// Note : Here you cannot manage par and rank in array like we did in "similar string group" question thought the input here and there was String[] of array.
// Here, It could have been tackled in array itself, it it was only till pairs array, but the sentence input might contain words which does not exist in pairs array
// So, here you would face problem, if you would have taken array of par and size because coreresponding par and size does not exist for the new word,
// but this could be handled easily in map by putting the newly word in the par and rank map.

// If the input for DSU is String, think of Map for par and rank, but if all input is sured to exist in the array, then no need of Map, can be tackled in array itself. 

import java.io.*;
import java.util.*;

public class Main {

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());

    String[] sentence1 = br.readLine().split(" ");
    String[] sentence2 = br.readLine().split(" ");

    int m = Integer.parseInt(br.readLine());

    String[][] pairs = new String[m][2];
    for (int i = 0; i < m; i++) {
      pairs[i] = br.readLine().split(" ");
    }
    System.out.println(areSentencesSimilarTwo(sentence1, sentence2, pairs));
  }

  
  //=================================================================================================================
  public static Map<String, String> parMap;
  public static Map<String, Integer> heightMap;

  public static boolean areSentencesSimilarTwo(String[] Sentence1, String[] Sentence2, String[][] pairs) {
      if(Sentence1.length != Sentence2.length) return false;
      
      parMap = new HashMap<>();
      heightMap = new HashMap<>();
      
      for(String[] pair : pairs) {
          String first = pair[0];
          String sec = pair[1];
          
          // Add pair elements in both the maps first
          if(!parMap.containsKey(first)) {
            parMap.put(first, first);
            heightMap.put(first, 1);
          }
          if(!parMap.containsKey(sec)) {
            parMap.put(sec, sec);
            heightMap.put(sec, 1);
          }
          
          // Then make them both belong to the same set
          union(first, sec);
      }
      
      for(int i = 0; i < Sentence1.length; i++) {
          String word1 = Sentence1[i];
          String word2 = Sentence2[i];
      
          // Sentence word which was not there in pairs
          // Add them in both the maps first
          if(!parMap.containsKey(word1)) {
            parMap.put(word1, word1);
            heightMap.put(word1, 1);
          }
          if(!parMap.containsKey(word2)) {
            parMap.put(word2, word2);
            heightMap.put(word2, 1);
          }
          
          // If they do not belong to the same set
          // They cannot be similar
          String p1 = find(word1);
          String p2 = find(word2);
          if(!p1.equals(p2)) return false;      
      }
      return true;
  }
  
  public static String find(String x) {
      if(parMap.get(x).equals(x)) return x;
      
      String p = find(parMap.get(x));
      parMap.put(x, p);
      return p;
  }
  
  public static void union(String x, String y) {
      String lx = find(x);
      String ly = find(y);
      if(!lx.equals(ly)) {
          if(heightMap.get(lx) > heightMap.get(ly)) {
              parMap.put(ly, lx);
          }
          else if(heightMap.get(ly) > heightMap.get(lx)) {
              parMap.put(lx, ly);
          }
          else {
              parMap.put(lx, ly);
              int h = heightMap.get(ly);
              heightMap.put(ly, ++h);
          }
      }
  }
  //=================================================================================================================
  
}
