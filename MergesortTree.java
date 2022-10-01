/*
    created by: nitin23329
    on Date: 29/09/21
*/
package helperFunctionsAndClasses;

import java.util.ArrayList;

public class MergesortTree {


    static class MergeSortTree {
        ArrayList<Integer>[] segTree;
        public MergeSortTree(int[] arr, int n){
            segTree = new ArrayList[4*n];
            for(int i=0;i<segTree.length;i++)
                segTree[i] = new ArrayList<>();
            build(0,n-1,0,arr);
        }
        private void merge(ArrayList<Integer> left,ArrayList<Integer> right,ArrayList<Integer> parent){
            int i=0,j=0;
            int n = left.size(),m=right.size();
            while(i<n && j<m){
                if(left.get(i) <= right.get(j))
                    parent.add(left.get(i++));
                else parent.add(right.get(j++));
            }
            while(i<n)parent.add(left.get(i++));
            while(j<m)parent.add(right.get(j++));
        }
        private void build(int stL,int stR,int stIndex,int[] arr){
            if(stL == stR){ //leaf node
                segTree[stIndex].add(arr[stL]);
                return;
            }
            int mid = (stR - stL)/2 + stL;
            build(stL,mid,2*stIndex+1,arr);
            build(mid+1,stR,2*stIndex+2,arr);
            merge(segTree[2*stIndex+1],segTree[2*stIndex+2],segTree[stIndex]);
        }
        private int getMinCount(int stL,int stR,int qL,int qR,int val,int stIndex){
            if(qL>stR || qR<stL)return 0;
            if(qL<=stL && qR>=stR){
                int l = 0;
                int r = segTree[stIndex].size()-1;
                int count = -1;
                while(l<=r){
                    int mid = (r-l)/2 + l;
                    if(segTree[stIndex].get(mid) < val){
                        count = mid;
                        l = mid+1;
                    }else r = mid-1;
                }
                return count+1;
            }
            int stMid = (stR - stL)/2 + stL;
            return getMinCount(stL,stMid,qL,qR,val,2*stIndex+1)
                    + getMinCount(stMid+1,stR,qL,qR,val,2*stIndex+2);
        }
        private int getMaxCount(int stL,int stR,int qL,int qR,int val,int stIndex){
            if(qL>stR || qR<stL)return 0;
            if(qL<=stL && qR>=stR){
                int l = 0;
                int r = segTree[stIndex].size()-1;
                int count = segTree[stIndex].size();
                while(l<=r){
                    int mid = (r-l)/2 + l;
                    if(segTree[stIndex].get(mid) > val){
                        count = mid;
                        r = mid-1;
                    }else l = mid+1;
                }
                return segTree[stIndex].size() - count;
            }
            int stMid = (stR - stL)/2 + stL;
            return getMaxCount(stL,stMid,qL,qR,val,2*stIndex+1)
                    + getMaxCount(stMid+1,stR,qL,qR,val,2*stIndex+2);
        }
    }




}
