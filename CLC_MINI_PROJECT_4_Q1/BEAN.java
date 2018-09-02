package CLC_4_QUESTION1;

import javax.faces.bean.ManagedBean;

@ManagedBean (name = "managedBean", eager = true)

public class BEAN {

	private String message;
	
	public BEAN(){
		setMessage("Hello World");
	}
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}