import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.TimerTask;

class AliveChecker extends TimerTask
{
    public void run()
    {
        new Thread(() ->
        {
            Iterator<String> it = ServeurAuth.aliveList.keySet().iterator();
            while(it.hasNext())
            {
                String key = it.next();
                int value = ServeurAuth.aliveList.get(key);
                if(value > ServeurAuth.TIME_OUT_ALIVE)
                {
                    it.remove();
                    ServeurAuth.zombiesList.remove(key);
                    ServeurAuth.writeZB(key + " removed to the zombie list !");
                    if(ServeurAuth.count)
                        ServeurAuth.writeZB("Zombies list: " + ServeurAuth.zombiesList.size());
                    try
                    {
                        String fPath = ServeurAuth.zombiesPath + key + ".txt";
                        File fZ = new File(fPath);
                        FileWriter fw = new FileWriter(fZ);
                        fw.write("0");
                        fw.close();
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                else
                {
                    ServeurAuth.aliveList.put(key, value + 1);
                }
            }
        }).start();
    }
}