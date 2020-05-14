package fr.watchdogs.benjaminloison.api;

public class ClientStats
{
    public int level, movementSpeed;
    public float saturation, temperature;
    public boolean isPoisoned;
    private static ClientStats instance = new ClientStats();

    public static ClientStats getInstance()
    {
        return instance;
    }
}