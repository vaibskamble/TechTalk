package clinic.programming.training;
import java.util.List;
import java.util.ArrayList;
public class Application {
    
	public void greet()
	{
		List<String> greets=new ArrayList<String>();
		greets.add("hello");
		
	for(String greet:greets){
			System.out.println("Greeting:"+greet);
		}
	}
    public Application() {
        System.out.println ("Inside Application");
    }

    // method main(): ALWAYS the APPLICATION entry point
    public static void main (String[] args) {
    	System.out.println ("Starting Application");
		Application app = new Application();
		app.greet();
		
    }
}