package org.zpx.sponge.oreannounce;

import ninja.leaping.configurate.objectmapping.ObjectMapper;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import ninja.leaping.configurate.objectmapping.Setting;


public class Config {

    public final static ObjectMapper<Config> MAPPER;

    static  {
        try {
            MAPPER = ObjectMapper.forClass(Config.class);
        } catch (ObjectMappingException e) {
            throw new ExceptionInInitializerError("Couldn't initialize ObjectMapper for Config");
        }
    }
    @Setting(value = "Username_Color", comment = "Witch color should the username have?")
    public String USERNAME_COLOR = "&7";

    @Setting(value = "Gold_Announce", comment = "Should gold be announced?")
    public boolean GOLD_ANNOUNCE = true;
    @Setting(value = "Gold_Message", comment = "Witch message should be announced?")
    public String GOLD_MESSAGE = "&fhat ein wenig &6Golderz &fgefunden!";

    @Setting(value = "Iron_Announce", comment = "Should iron be announced?")
    public boolean IRON_ANNOUNCE = true;
    @Setting(value = "Iron_Message", comment = "Witch message should be announced?")
    public String IRON_MESSAGE = "&fhat &eEisenerz &fentdeckt!";

    @Setting(value = "Coal_Announce", comment = "Should coal be announced?")
    public boolean COAL_ANNOUNCE = true;
    @Setting(value = "Coal_Message", comment = "Witch message should be announced?")
    public String COAL_MESSAGE = "&fhat ein &8Kohleflös &fentdeckt!";

    @Setting(value = "Lapis_Announce", comment = "Should lapis lazuli be announced?")
    public boolean LAPIS_ANNOUNCE = true;
    @Setting(value = "Lapis_Message", comment = "Witch message should be announced?")
    public String LAPIS_MESSAGE = "&fhat etwas &1Lapis Lazuli &fausgebuddelt!";

    @Setting(value = "Diamond_Announce", comment = "Should diamond be announced?")
    public boolean DIAMOND_ANNOUNCE = true;
    @Setting(value = "Diamond_Message", comment = "Witch message should be announced?")
    public String DIAMOND_MESSAGE = "&fhat &bDiamanten &fgefunden!";

    @Setting(value = "Redstone_Announce", comment = "Should redstone be announced?")
    public boolean REDSTONE_ANNOUNCE = true;
    @Setting(value = "Redstone_Message", comment = "Witch message should be announced?")
    public String REDSTONE_MESSAGE = "&fhat &4Redstone &fentdeckt!";

    @Setting(value = "Emerald_Announce", comment = "Should emerald be announced?")
    public boolean EMERALD_ANNOUNCE = true;
    @Setting(value = "Emerald_Message", comment = "Witch message should be announced?")
    public String EMERALD_MESSAGE = "&fhat einen &2Smaragd &fgefunden!";

    @Setting(value = "Quartz_Announce", comment = "Should quartz be announced?")
    public boolean QUARTZ_ANNOUNCE = true;
    @Setting(value = "Quarz_Message", comment = "Witch message should be announced?")
    public String QUARTZ_MESSAGE = "&fhat &cNetherquartz &fgefarmt!";
}