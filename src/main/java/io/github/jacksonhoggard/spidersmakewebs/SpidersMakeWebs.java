package io.github.jacksonhoggard.spidersmakewebs;

import com.mojang.logging.LogUtils;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.CaveSpider;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.stream.Collectors;

@Mod("spidersmakewebs")
public class SpidersMakeWebs
{
    public static final Logger LOGGER = LogUtils.getLogger();

    public SpidersMakeWebs()
    {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void EntityJoined(EntityJoinWorldEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Spider spider) {
            ((Spider) event.getEntity()).goalSelector.addGoal(2, new MakeWebGoal(spider));
            ((Spider) event.getEntity()).goalSelector.addGoal(7, new PlaceWebRandomGoal(spider));
        }
    }

}
