import java.util.ArrayList;

class SecondaryVersion implements PQ{
	private ArrayList<Tuple> list;

	SecondaryVersion(){
		list = new ArrayList<Tuple>(); 
	}
	public void insert(Object object, int score){
		Tuple element = new Tuple(object, score, 0);
		if(list.isEmpty()){
			list.add(element);
		}
		else{
			for(int i = 0; i < list.size(); i++){
				if(score > list.get(i).score){
					list.add(i, element);
					break;
				}
				else if(i == list.size()-1){
					list.add(element);
					break;
				}
			}
		}
	}
	public Object remove(){
		if(!list.isEmpty()){
			return list.remove(0).object;
		}
		else{
			return null;
		}
	}
	public int getSize(){
		return list.size();
	}

	public void clear(){
		list = new ArrayList<Tuple>();
	}
}
//class Tuple {
//	protected Object object;
//	protected int score;
//	protected Tuple(Object object, int score){
//		this.object = object;
//		this.score = score;
//	}
//}
