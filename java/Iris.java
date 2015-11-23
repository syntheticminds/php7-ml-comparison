import java.text.NumberFormat;

public class Iris {
	public static void main(String[] args) {
		System.out.println("Java " + System.getProperty("java.vm.name"));
		
		Benchmark benchmark = new Benchmark();
    	int total = benchmark.run();

		System.out.print(NumberFormat.getIntegerInstance().format(total) + " instances classified in 30 seconds");
		System.out.println(" (" + NumberFormat.getIntegerInstance().format(total / 30) + " per second)");
	}
}
