
public interface PQ {
	public void insert(Object object, int score);
	public Object remove();
	public int getSize();
	public void clear();
}
