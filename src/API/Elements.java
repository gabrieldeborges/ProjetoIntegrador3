package api;

public class Elements {

	 private DistanceObj distance;

	    private Duration duration;

	    private String status;

	    public void setDistance(DistanceObj distance){
	        this.distance = distance;
	    }
	    public DistanceObj getDistance(){
	        return this.distance;
	    }
	    public void setDuration(Duration duration){
	        this.duration = duration;
	    }
	    public Duration getDuration(){
	        return this.duration;
	    }
	    public void setStatus(String status){
	        this.status = status;
	    }
	    public String getStatus(){
	        return this.status;
	    }
	
}
