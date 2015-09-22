import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class company extends HashMap<String, List<Long>>  {
	public void put(String key,long location)
	{
		List<Long> curr=get(key);
		if(curr==null){
			curr=new ArrayList<Long>();
			super.put(key, curr);
		}
		curr.add(location);
	}
}

class Flt extends HashMap<Float, List<Long>>  {
	public void put(Float key,long location)
	{
		List<Long> curr=get(key);
		if(curr==null){
			curr=new ArrayList<Long>();
			super.put(key, curr);
		}
		curr.add(location);
	}
}

class Numb extends HashMap<Integer, List<Long>>  {
	public void put(int key,long location)
	{
		List<Long> curr=get(key);
		if(curr==null){
			curr=new ArrayList<Long>();
			super.put(key, curr);
		}
		curr.add(location);
	}
}