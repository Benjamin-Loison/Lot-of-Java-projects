package fr.taltisconverter.benjaminloison.common;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;

public class MapUtil
{
    private static final double MC_MIN = 0;
    static final double MC_MAX = 255;
    private static final double NOT_INIT = -98765;
    public static int MC_WATER = -1;
    static double maxHeight = NOT_INIT;
    static double biomeTileSize = NOT_INIT, quotient = NOT_INIT;
    static double subChunkGroundSize = NOT_INIT;
    static WorldServer w = MinecraftServer.getServer().worldServers[0];

    static double value(String key)
    {
        double value = -1;
        File dataFile = new File(AltisConverterMod.mapFolderStr + "data.txt");
        try
        {
            Scanner scan = new Scanner(dataFile);
            while(scan.hasNextLine())
            {
                String line = scan.nextLine();
                if(line.startsWith(key + "="))
                {
                    value = Double.parseDouble(line.split("=")[1]);
                    break;
                }
            }
            scan.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return value;
    }

    public static int chunkSize()
    {
        return (int)value("chunkGroundSize");
    }

    public static double seaHeight()
    {
        return value("seaHeight");
    }

    public static int mcHeight(double height)
    {
        return (int)((height - minHeight()) * quotient);
    }

    public static double minHeight()
    {
        return value("seaBottom");
    }

    public static double biomeTileSize()
    {
        return biomeTileSize;
    }

    public static double subChunkGroundSize()
    {
        return subChunkGroundSize;
    }

    public static double maxHeight()
    {
        if(maxHeight != NOT_INIT)
            return maxHeight;
        File groundFolder = new File(AltisConverterMod.mapFolderStr + "Ground" + File.separator);
        File[] files = groundFolder.listFiles();
        for(int i = 0; i < files.length; i++)
        {
            File file = files[i];
            String fName = file.getName();
            // System.out.println("maxHeight: working on: " + fName);
            if(fName.endsWith(".height"))
            {
                try
                {
                    Scanner scanner = new Scanner(file);
                    while(scanner.hasNextLine())
                    {
                        String line = scanner.nextLine();
                        String[] parts = line.split(" ");
                        for(int partsIndex = 0; partsIndex < parts.length; partsIndex++)
                        {
                            String part = parts[partsIndex];
                            if(part.equals("N"))
                                continue;
                            else
                            {
                                Double altitude = Double.parseDouble(part);
                                if(altitude > maxHeight)
                                {
                                    maxHeight = altitude;
                                }
                            }
                        }
                    }
                    scanner.close();
                }
                catch(Exception exception)
                {
                    exception.printStackTrace();
                }
            }
        }
        return maxHeight;
    }

    public static void setBlock(int x, int y, int z, double size, String block) // can't forge setblock or minecraft setblock in unloaded chunk (even if generated)
    {
        // System.out.println("Working on: " + x + " " + y + " " + z);
        // String cmd0 = "//pos1 " + x + "," + y + "," + z, cmd1 = "//pos2 " + (x + size) + "," + y + "," + (z + size), cmd2 = "//set 2";
        // Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(x + " " + y + " " + z));
        // Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Executing: " + cmd0));
        // Minecraft.getMinecraft().thePlayer.sendChatMessage(cmd0);
        // Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Executing: " + cmd1));
        // Minecraft.getMinecraft().thePlayer.sendChatMessage(cmd1);
        // Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Executing: " + cmd2));
        // Minecraft.getMinecraft().thePlayer.sendChatMessage(cmd2);
        // Player p = (Player)Minecraft.getMinecraft().thePlayer;

        /*
         * Vector p1 = new Vector(-106, 110, 456);
         * Vector p2 = new Vector(-88, 95, 474);
         * CuboidRegion cube = new CuboidRegion(p1, p2);
         * cube.setWorld(Minecraft.getMinecraft().theWorld);
         * EditSession session = new EditSession(world, cube.getArea());
         * try {
         * session.setBlock(cube, BaseBlock.); // <----- CAN ONLY PUT 1 BLOCK
         * }
         * catch(Exception e)
         * {
         * e.printStackTrace();
         * }
         * /
         **/
        for(int xIndex = x; xIndex < x + size; xIndex++)
        {
            for(int zIndex = z; zIndex < z + size; zIndex++)
            {
                // int differenceX = Math.max(y[0], y[1]) - Math.min(y[0], y[1]);
                // int yFnl = y[0] + 1;// Math.min(y[0], y[1]) + differenceX * ((xIndex - x) / (size - 1));
                // final int xFnl = xIndex, zFnl = zIndex;
                /*
                 * try
                 * {
                 * int nb = Bukkit.getScheduler().getPendingTasks().size();
                 * while(nb > 2500)
                 * {
                 * System.out.println("Bukkit: " + nb);
                 * Thread.sleep(1);
                 * }
                 * }
                 * catch(Exception e)
                 * {
                 * e.printStackTrace();
                 * }
                 */
                /*
                 * Bukkit.getScheduler().runTaskLater(AltisConverter.plugin, new Runnable()
                 * {
                 * @Override
                 * public void run()
                 * {
                 */
                /*
                 * AltisConverter.getWorldEdit();
                 * Vector p1 = new Vector(-106, 110, 456);
                 * Vector p2 = new Vector(-88, 95, 474);
                 * CuboidRegion cube = new CuboidRegion(p1, p2);
                 * LocalWorld w = BukkitUtil.getLocalWorld(w);
                 * cube.setWorld(w);
                 * EditSession session = new EditSession(w, cube.getArea());
                 * try
                 * {
                 * session.setBlock(p1, new BaseBlock(1)); // <----- CAN ONLY PUT 1 BLOCK
                 * }
                 * catch(Exception e)
                 * {
                 * e.printStackTrace();
                 * }
                 */
                /*
                 * w.getBlockAt(xFnl, yFnl - 1, zFnl).setType(Material.STONE);
                 */ switch(block)
                {
                    case "0":
                        w.setBlock(xIndex, y, zIndex, Blocks.dirt);
                        break;
                    case "1":
                        w.setBlock(xIndex, y, zIndex, Blocks.mycelium);
                        break;
                    case "2":
                        w.setBlock(xIndex, y, zIndex, Blocks.grass);
                        break;
                    case "3":
                        w.setBlock(xIndex, y, zIndex, Blocks.double_stone_slab);
                        break;
                    case "4":
                        w.setBlock(xIndex, y, zIndex, Blocks.clay);
                        break;
                    case "5":
                        w.setBlock(xIndex, y - 1, zIndex, Blocks.stone);
                        w.setBlock(xIndex, y, zIndex, Blocks.sand);
                        break;
                    case "6":
                        w.setBlock(xIndex, y, zIndex, Blocks.mossy_cobblestone);
                        break;
                    case "7":
                        w.setBlock(xIndex, y, zIndex, Blocks.sandstone);
                        break;
                    case "8":
                        w.setBlock(xIndex, y, zIndex, Blocks.hay_block);
                        break;
                    case "9":
                        w.setBlock(xIndex, y, zIndex, Blocks.cobblestone);
                        break;
                    case "A":
                        w.setBlock(xIndex, y, zIndex, Blocks.stained_hardened_clay, 13, 1);
                        break;
                    case "B":
                        w.setBlock(xIndex, y - 1, zIndex, Blocks.stone);
                        w.setBlock(xIndex, y, zIndex, Blocks.gravel);
                        break;
                    case "C":
                        w.setBlock(xIndex, y, zIndex, Blocks.dirt, 2, 1);
                        break;
                    case "D":
                        w.setBlock(xIndex, y, zIndex, Blocks.stained_hardened_clay, 1, 1);
                        break;
                    case "E":
                        w.setBlock(xIndex, y - 1, zIndex, Blocks.stone);
                        w.setBlock(xIndex, y, zIndex, Blocks.gravel);
                        break;
                    case "F":
                        w.setBlock(xIndex, y, zIndex, Blocks.stained_hardened_clay, 12, 1);
                        break;
                    case "G":
                        w.setBlock(xIndex, y, zIndex, Blocks.end_stone);
                        break;
                    case "H":
                        w.setBlock(xIndex, y - 1, zIndex, Blocks.stone);
                        w.setBlock(xIndex, y, zIndex, Blocks.sand, 1, 1);
                        break;
                    case "I":
                        w.setBlock(xIndex, y, zIndex, Blocks.stained_hardened_clay, 5, 1);
                        break;
                }
                // for(int yt = yFnl + 1; yt <= MC_WATER; yt++)
                // w.setBlock(xIndex, yt, zIndex, Blocks.water);
                // w.setBlock(xIndex, MC_WATER, zIndex, Blocks.water); // lags
                // System.out.println("helo");
                //
                // }
                // }, 0);
            }
        }
        /*
         * try
         * {
         * // Thread.sleep(1);
         * }
         * catch(Exception e)
         * {
         * e.printStackTrace();
         * }
         */
    }

    public static int getHeight(String part)
    {
        if(part.equals("N"))
            return (int)MC_MIN;
        return mcHeight(Double.parseDouble(part));
    }

    public static double euclidianReminder(double first, double second)
    {
        while(first >= second)
        {
            first -= second;
        }
        return first;
    }

    static int threadRunning = 0;
    static int zIndex = 0;

    public static void importMap()
    {
        File groundFolder = new File(AltisConverterMod.mapFolderStr + "Ground" + File.separator);
        File[] files = groundFolder.listFiles();
        int filesLength = files.length;
        for(int i = 0; i < filesLength; i++)
        {
            File file = files[i];
            try
            {
                /*
                 * Thread th = new Thread()
                 * {
                 * public void run()
                 * {
                 * threadRunning++;
                 */
                String fName = file.getName();

                String fNameWithoutExt = fName.split("\\.")[0];
                File biomeFile = new File(AltisConverterMod.mapFolderStr + "Biomes" + File.separator + fNameWithoutExt + ".biomes");
                ArrayList<ArrayList<String>> biomesLines = new ArrayList<ArrayList<String>>();
                boolean biomesExist = biomeFile.exists();
                // System.out.println("B: " + biomesExist);
                if(biomesExist)
                {
                    Scanner scan = new Scanner(biomeFile);
                    while(scan.hasNextLine())
                    {
                        ArrayList<String> lineArray = new ArrayList<String>();
                        String line = scan.nextLine();
                        for(int lineIndex = 0; lineIndex < line.length(); lineIndex++)
                        {
                            lineArray.add(line.substring(lineIndex, lineIndex + 1));
                        }
                        // System.out.println("L: " + lineArray.size());
                        biomesLines.add(lineArray);
                    }
                    // System.out.println("Bio: " + biomesLines.size());
                    scan.close();
                }

                // System.out.println("importMap: height: working on: " + fName);
                String[] fNameParts = fNameWithoutExt.split(" ");
                int xChk = chunkSize() * Integer.parseInt(fNameParts[0]);
                int zChk = chunkSize() * Integer.parseInt(fNameParts[1]);
                if(fName.endsWith(".height"))
                {
                    System.out.println(i + " / " + filesLength);
                    // Bukkit.broadcastMessage("Working on: " + fName);
                    Scanner scanner = new Scanner(file);
                    // final Scanner nextScanner = new Scanner(file);
                    zIndex = 0;
                    // boolean noBreak = true;
                    // nextScanner.nextLine();
                    while(scanner.hasNextLine() /* && noBreak && nextScanner.hasNextLine() */)
                    {
                        /*
                         * Thread th = new Thread()
                         * {
                         * public void run()
                         * {
                         */
                        // System.out.println("Working on z: " + zIndex);
                        String line = scanner.nextLine();
                        // nextScanner.nextLine();
                        // String nextLine = nextScanner.nextLine();
                        String[] parts = line.split(" ");
                        int partsLength = parts.length;
                        for(int partsIndex = 0; partsIndex < partsLength - 1; partsIndex++)
                        {
                            // System.out.println("Working on x: " + partsIndex);
                            String part = parts[partsIndex];
                            /*
                             * int heights[] = new int[4];
                             * heights[0] = getHeight(part);
                             * heights[1] = getHeight(parts[partsIndex + 1]);
                             * String[] nextParts = nextLine.split(" ");
                             * heights[2] = getHeight(nextParts[partsIndex]);
                             * heights[3] = getHeight(nextParts[partsIndex + 1]);
                             */
                            double littleX = partsIndex * subChunkGroundSize, littleZ = zIndex * subChunkGroundSize;
                            double x = xChk + littleX, z = zChk + littleZ;
                            String biome = "6";
                            if(biomesExist)
                            {
                                double tmpX = (littleX - euclidianReminder(littleX, biomeTileSize)) / biomeTileSize, tmpZ = (littleZ - euclidianReminder(littleZ, biomeTileSize)) / biomeTileSize;
                                // System.out.println("X: " + tmpX + " " + littleX + " " + biomeTileSize() + " " + biomesLines.size() + " " + biomesLines.get(newX).size());
                                // System.out.println("Z: " + tmpZ + " " + littleZ + " " + biomeTileSize() + " " + biomesLines.size() + " " + biomesLines.get(newZ).size());
                                biome = biomesLines.get((int)tmpX).get((int)tmpZ);
                            }
                            setBlock((int)x, getHeight(part) + 1, (int)z, subChunkGroundSize, biome);
                        }
                        zIndex++;
                        // System.out.println("Finished working on z: " + zIndex);
                        /*
                         * }
                         * };
                         * th.start();
                         */
                    }
                    scanner.close();
                }
                /*
                 * threadRunning--;
                 * }
                 * };
                 * th.start();
                 * while(threadRunning > 0)
                 */
                // break;
                // Thread.sleep(1000);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}