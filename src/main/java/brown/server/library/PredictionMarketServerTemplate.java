package brown.server.library;

public class PredictionMarketServerTemplate {
	static private int seconds = 5;
	static private int nSims = 50;
	static private int init_delay = 5;
	static private int lag = 25;
  
	public static void main(String[] args) throws InterruptedException {
		PredictionMarketServer server = new PredictionMarketServer(seconds, nSims, init_delay, lag);
		server.runAll();
	}
}