class LFUCache {
    HashMap<Integer,DoublyLinkedList> freqMap;
    HashMap<Integer,DLLNode> cache;
    int capacity;
    int size;
    int minFrequency;
    public LFUCache(int capacity) {
        this.capacity = capacity;
        size = 0;
        minFrequency = 0;
        cache = new HashMap<>();
        freqMap = new HashMap<>();
    }
    
    public int get(int key) {
        if(cache.containsKey(key)){
            DLLNode curNode = cache.get(key);
            updateNode(curNode);
            return curNode.value;
        }else{
            return -1;
        }
    }
    
    public void put(int key, int value) {
        if(capacity == 0)
            return;
        
        if(cache.containsKey(key)){
            DLLNode curNode = cache.get(key);
            curNode.value = value;
            updateNode(curNode);
        }else{
            size++;
            if(size > capacity){
                DoublyLinkedList minFreqList = freqMap.get(minFrequency);
                cache.remove(minFreqList.tail.prev.key);
                minFreqList.remove(minFreqList.tail.prev);
                size--;
            }
            minFrequency = 1;
            DLLNode newNode = new DLLNode(key,value);
        
            DoublyLinkedList newFreqList = freqMap.getOrDefault(1,new DoublyLinkedList());
            newFreqList.add(newNode);
            freqMap.put(1,newFreqList);
            cache.put(key,newNode);
        }
    }
    
    public void updateNode(DLLNode node){
        int nodeFreq = node.frequency;
        DoublyLinkedList freqList = freqMap.get(nodeFreq);
        freqList.remove(node);
        
        if(minFrequency == nodeFreq && freqList.listSize == 0){
            minFrequency++; 
        }
        
        node.frequency++;
        DoublyLinkedList newList = freqMap.getOrDefault(node.frequency,new DoublyLinkedList());
        newList.add(node);
        freqMap.put(node.frequency,newList);
    }
}

class DLLNode{
    int key;
    int value;
    int frequency;
    DLLNode prev, next;
    DLLNode(int key, int value){
        this.key = key;
        this.value = value;
        frequency = 1;
    }
}

class DoublyLinkedList{
    DLLNode head;
    DLLNode tail;
    int listSize;
    
    DoublyLinkedList(){
        listSize = 0;
        head = new DLLNode(0,0);
        tail = new DLLNode(0,0);
        head.next = tail;
        tail.prev = head;
    }
    
    public void remove(DLLNode node){
        DLLNode prevNode = node.prev;
        DLLNode nextNode = node.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
        listSize--;
    }
    
    public void add(DLLNode node){
        DLLNode nextToHead = head.next;
        head.next = node;
        node.next = nextToHead;
        node.prev = head;
        nextToHead.prev = node;
        listSize++;
    }
}
