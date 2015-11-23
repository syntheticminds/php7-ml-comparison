import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Benchmark {
	protected ArrayList<double[]> attributes;
	protected ArrayList<String> classes;
	
	public int run()
	{
		try {
			this.load();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		
		int index = 0;
		int total = 0;
		long start_time = System.currentTimeMillis();
		
		while (System.currentTimeMillis() < start_time + 30000) {
			if (index >= attributes.size()) {
				index = 0;
			}
			
			String answer = this.classify(index);

			total++;
			index++;
		}

		return total;
	}
	
	protected void load() throws IOException {
		BufferedReader file = null;
	    String line = null;

	    this.attributes = new ArrayList<double[]>();
	    this.classes = new ArrayList<String>();
		
	    try {
	    	file = new BufferedReader(new FileReader("../iris.csv"));
	    	
	    	while ((line = file.readLine()) != null) {
				String[] values = line.split(",");
				double[] row = {Double.parseDouble(values[0]), Double.parseDouble(values[1]), Double.parseDouble(values[2]), Double.parseDouble(values[3])};
				
				this.attributes.add(row);
				this.classes.add(values[4]);
	  	    }
	    } finally {
	        if (file != null) {
	        	file.close();
	        }
	    }
	}
	
	protected String classify(int index) {
		
		int closest_neighbour_index = -1;
	    double closest_neighbour_distance = Double.POSITIVE_INFINITY;
	    
	    for(int i = 0; i < this.attributes.size(); i++) {
	    	if (i == index) {
	            continue;
	        }

		    double total = 0;
		    for (int j = 0; j < this.attributes.get(i).length; j++) {
		    	total += Math.pow(this.attributes.get(index)[j] - this.attributes.get(i)[j], 2);
		    }
		    double distance = Math.sqrt(total);
	    	
		    if (distance < closest_neighbour_distance) {
				closest_neighbour_index = i;
			    closest_neighbour_distance = distance;
		    }
	    }
	    
	    return this.classes.get(closest_neighbour_index);
	}
}
