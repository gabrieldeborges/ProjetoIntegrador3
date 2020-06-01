package api;

import java.util.List;

public class root {

	
	private List<String> destination_addresses;

    private List<String> origin_addresses;

    private List<rows> rows;

    private String status;

    public void setDestination_addresses(List<String> destination_addresses){
        this.destination_addresses = destination_addresses;
    }
    public List<String> getDestination_addresses(){
        return this.destination_addresses;
    }
    public void setOrigin_addresses(List<String> origin_addresses){
        this.origin_addresses = origin_addresses;
    }
    public List<String> getOrigin_addresses(){
        return this.origin_addresses;
    }
    public void setRows(List<rows> rows){
        this.rows = rows;
    }
    public List<rows> getRows(){
        return this.rows;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return this.status;
    }

	
	
}
