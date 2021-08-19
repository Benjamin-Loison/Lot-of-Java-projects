package fr.annihilation.benjaminloison.api;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;

import com.sk89q.jnbt.CompoundTag;

public class SignAPI
{
    int x, y, z;
    String[] ligns;

    public SignAPI(int x, int y, int z, String[] ligns)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.ligns = ligns;
    }

    public void setSign(World w)
    {
        Location l = new Location(w, x, y, z);
        boolean onGround = false;
        if(w.getBlockAt(new Location(w, x, y - 1, z)).getType() != Material.AIR)
            onGround = true;
        if(!onGround)
        {
            Sign s = (Sign)l.getBlock().getState();
            for(int n = 0; n < 4; n++)
                s.setLine(n, ligns[n]);
            org.bukkit.material.Sign matSign = new org.bukkit.material.Sign(Material.WALL_SIGN);
            matSign.setFacingDirection(direction(w, onGround));
            s.setData(matSign);
            s.update();
        }
        else
        {}
    }

    BlockFace direction(World w, boolean onGround)
    {
        short walls = 0;
        boolean west = false, east = false, north = false, south = false;
        if(w.getBlockAt(new Location(w, x - 1, y, z)).getType() != Material.AIR)
        {
            west = true;
            walls++;
        }
        if(w.getBlockAt(new Location(w, x + 1, y, z)).getType() != Material.AIR)
        {
            east = true;
            walls++;
        }
        if(w.getBlockAt(new Location(w, x, y, z - 1)).getType() != Material.AIR)
        {
            north = true;
            walls++;
        }
        if(w.getBlockAt(new Location(w, x, y, z + 1)).getType() != Material.AIR)
        {
            south = true;
            walls++;
        }
        if(walls == 4)
            walls = 0;
        if(!onGround)
            return directionWall(walls, south, north, east, west);
        return BlockFace.NORTH;
    }

    BlockFace directionWall(short walls, boolean south, boolean north, boolean east, boolean west)
    {
        if(walls == 1)
            if(south)
                return BlockFace.NORTH;
            else if(north)
                return BlockFace.SOUTH;
            else if(west)
                return BlockFace.EAST;
            else
                return BlockFace.WEST;
        else if(walls == 2)
            if(west && east)
                return BlockFace.NORTH;
            else if(north && south)
                return BlockFace.WEST;
            else if(west && north)
                return BlockFace.SOUTH;
            else if(west && south)
                return BlockFace.NORTH;
            else if(east && north)
                return BlockFace.SOUTH;
            else
                return BlockFace.NORTH;
        else if(south && east && west)
            return BlockFace.NORTH;
        else if(north && east && west)
            return BlockFace.SOUTH;
        else if(north && south && west)
            return BlockFace.EAST;
        else
            return BlockFace.WEST;
    }

    public static SignAPI[] readSigns(File file)
    {
        MCNBTDataAPI data = new MCNBTDataAPI(file);
        ArrayList<CompoundTag> list = data.ArrayListContentsOfListTag(data.getListTags("TileEntities"));
        ArrayList<SignAPI> signs = new ArrayList<SignAPI>();
        for(int s = 0; s < list.size(); s++)
            if(data.valueKeyInCompoundTag(list.get(s), "id").replace("TAG_String(\"id\"): ", "").equals("Sign"))
            {
                String[] ligns = new String[4];
                for(int l = 0; l < ligns.length; l++)
                    ligns[l] = data.valueKeyInCompoundTag(list.get(s), "Text" + (l + 1)).replace("TAG_String(\"Text" + (l + 1) + "\"): ", "");
                signs.add(new SignAPI(Integer.parseInt(data.valueKeyInCompoundTag(list.get(s), "x").replace("TAG_Int(\"x\"): ", "")), Integer.parseInt(data.valueKeyInCompoundTag(list.get(s), "y").replace("TAG_Int(\"y\"): ", "")), Integer.parseInt(data.valueKeyInCompoundTag(list.get(s), "z").replace("TAG_Int(\"z\"): ", "")), ligns));
            }
        return FileAPI.signsListFromArrayList(signs);
    }
}