package brown.simulations;

import java.util.LinkedList;
import java.util.List;

public class LemonadeSimulation extends AbsUserSimulation {

  public LemonadeSimulation(List<String> agentClass, String inputJSON,
      String outFile, boolean writeToFile) {
    super(agentClass, inputJSON, outFile, writeToFile);
  }

  public void run() throws InterruptedException {

    ServerRunnable sr = new ServerRunnable();
    AgentRunnable ar = new AgentRunnable(agentClass.get(0), "solo");

    Thread st = new Thread(sr);
    Thread at = new Thread(ar);
    Thread atTwo = new Thread(ar);
    Thread atThree = new Thread(ar);

    st.start();
    if (agentClass != null) {
      at.start();
      atTwo.start();
      atThree.start();
    }

    while (true) {
      if (!st.isAlive()) {
        at.interrupt();
        atTwo.interrupt();
        atThree.interrupt();
        break;
      }
      Thread.sleep(1000);
    }
  }

  public static void main(String[] args) throws InterruptedException {
    List<String> agentList = new LinkedList<String>();
    agentList.add("brown.user.agent.library.BasicLemonadeAgent");
    LemonadeSimulation basicSim = new LemonadeSimulation(agentList,
        "input_configs/lemonade_game.json", "outfile", false);
    basicSim.run();
  }

}