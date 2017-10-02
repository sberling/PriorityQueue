import java.util.ArrayList;
import java.util.Collections;

public class PriorityQueue implements PQ{
	private ArrayList<Tuple> heap;//holds all the data
	private int itemsAdded; //incrementing ID number

	public PriorityQueue(){
		heap = new ArrayList<Tuple>();
		itemsAdded = 0;
	}

	public void insert(Object object, int score){
		itemsAdded++;
		Tuple element = new Tuple(object, score, itemsAdded);
		heap.add(element);
		swim(heap.size()-1);
	}
	
	
	public Object remove(){
		if(!heap.isEmpty()){
			//lastMinuteSwap(0, heap.get(0)); //in case one of the children or grandchildren has the same score and is older
			Collections.swap(heap, 0, heap.size()-1);
			Object removed = heap.remove(heap.size()-1).object;
			if(!heap.isEmpty()){
				sink(0); //sink the new Head to its right place
			}
			return removed;
		}
		return null;
	}

	public int getSize(){
		return heap.size();
	}

	public void clear(){
		itemsAdded = 0;
		heap = new ArrayList<Tuple>();
	}

	/**
	 * Sinks an element to its rightful place
	 * @param i index of element to sink
	 */
	private void sink(int i){
		int score = heap.get(i).score;
		Tuple sinker = heap.get(i);

		switch(getNumChildren(i)){
		case 2:

			Tuple left = heap.get(2*i+1);
			Tuple right = heap.get(2*i+2);
			
			/*
			 * See if a child is better, then swap if it is
			 */
			if(compare(sinker, left, right) == left){ 
				Collections.swap(heap, i, 2*i+1);
				sink(2*i+1);
			}
			else if(compare(sinker, left, right) == right){
				Collections.swap(heap, i, 2*i+2);
				sink(2*i+2);
			}
			
			break;
		case 1:
			Tuple child = heap.get(2*i+1);
			if(compare(sinker, child) == child){ //check if parent is less than child
				Collections.swap(heap, i, 2*i+1); //swap
				sink(2*i+1); //recurse
			}
			break;
		default://no children to swap with! so it doesn't need to sink further
			break;
		}
	}

	/**
	 * Swims the element at the given index 
	 * @param swimmerIndex index of element to swim
	 */
	private void swim(int swimmerIndex){
		if(swimmerIndex!=0){
			int parentIndex = (int) (.5*(swimmerIndex-1)); 
			Tuple swimmer = heap.get(swimmerIndex);
			Tuple parent = heap.get(parentIndex);

			if(compare(parent, swimmer) == swimmer){
				Collections.swap(heap, swimmerIndex, parentIndex); //if child is bigger than parent, swap them
				swim(parentIndex);
			} //else child is in correct place
		}
	}

	/**
	 * Gets the number of children of a node in the heap at index i
	 * @param i index to check
	 * @return number of children
	 */
	private int getNumChildren(int i){
		if(heap.size() > (2*i+2)){//definitely has two children
			return 2;
		}
		else if(heap.size() == (2*i+2)){//has only one child
			return 1;
		}
		else{
			return 0;
		}
	}

	public String toString(){
		String out = "";
		int i = 1;
		while(i <= heap.size()){
			out += i + ": " + heap.get(i-1).object + "-" + heap.get(i-1).score + "\n";
			i++;
		}
		return out;
	}

	private Tuple compare(Tuple x, Tuple y){
		if(x.score > y.score){
			return x;
		}
		else if(y.score > x.score){
			return y;
		}
		else{ //==
			if(x.id < y.id){
				return x;
			}
			else{
				return y;
			}
		}
	}
	
	private Tuple compare(Tuple x, Tuple y, Tuple z){
		return compare(compare(x,y),z);
	}
}

class Tuple {
	protected Object object;
	protected int score;
	protected long id; //to compare how old objects are
	protected Tuple(Object object, int score, int id){
		this.object = object;
		this.score = score;
		this.id =  id;
	}
}