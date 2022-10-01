/*
    created by: nitin23329
    on Date: 17/08/22
*/
package helperFunctionsAndClasses.SegmentTree;
// question: https://cses.fi/problemset/task/1651/
public class RangeUpdatePointGet {
    static class SegmentTree{
        long [] segTree;
        long [] updates;
        public SegmentTree(int [] arr,int n){
            segTree = new long[4*n];
            updates=  new long[4*n];
            build(0,n-1,arr,0);
        }
        private  void  build(int stL, int stR, int[] arr,int stI){
            if(stL == stR){
                segTree[stI] = arr[stL];
                updates[stI] = 0;
                return;
            }
            int mid = (stL + stR) / 2;
            build(stL,mid,arr,2*stI+1);;
            build(mid+1,stR,arr,2*stI+2);
//            arr[stI] = arr[2*stI + 1] + arr[2*stI + 2];
        }
        public void pushDownUpdate(int stI,boolean isLeaf){
            if(isLeaf){
                segTree[stI] += updates[stI];
            }
            else if(updates[stI] != 0 ){
                updates[2*stI +1] += updates[stI];
                updates[2*stI +2] += updates[stI];

            }
            updates[stI] = 0;
        }
        public void updateRange(int stL, int stR, int stI, int qL, int qR, int x) {
            if(qL > stR || qR < stL)return;     // out of bound
            pushDownUpdate(stI,stL == stR);  //push down any past updates
            if(qL <= stL && qR >= stR){ // node is fully overlap
                updates[stI] += x;
            }else{  // partially overlap
                int mid = (stL + stR)  /2;
                updateRange(stL,mid,2*stI + 1,qL,qR,x);
                updateRange(mid+1,stR,2*stI + 2, qL,qR,x);
            }
        }
        public long get(int stL, int stR, int stI, int ind){
            if(ind > stR || ind < stL)return 0 ;     // out of bound
            pushDownUpdate(stI,stL == stR);  //push down any past updates
            if(stL == stR)return segTree[stI];
            else {
                int mid = (stL + stR) / 2;
                return get(stL,mid,2*stI+1,ind) + get(mid+1,stR,2*stI + 2,ind);
            }
        }

    }
}
