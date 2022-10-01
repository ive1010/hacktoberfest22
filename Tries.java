/*
    created by: nitin23329
    on Date: 26/07/22
*/
package helperFunctionsAndClasses;

public class Tries {
    static int SIZE = 26;
    static Trie root = new Trie();
    static class Trie{
        boolean isEndOfWord;
        Trie[] child;
        public Trie(){
            isEndOfWord= false;
            child =  new Trie[SIZE];
        }
        public void insert(char[] str,Trie root){
            for(char c : str) {
                if(root.child[c-'a'] == null)root.child[c-'a'] = new Trie();
                root = root.child[c-'a'];
            }
            root.isEndOfWord = true;
        }
        public boolean search(char[] str, Trie root){
            for(char c : str) {
                if(root.child[c-'a'] == null)return false;
                root = root.child[c-'a'];
            }
           return root.isEndOfWord;
        }
    }
}
