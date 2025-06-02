package me.jacksonhoggard.spidersmakewebs;

import com.mojang.logging.LogUtils;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Spider;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(Spidersmakewebs.MOD_ID)
public class Spidersmakewebs {
    public static final String MOD_ID = "spidersmakewebs";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Spidersmakewebs(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();
        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("Initializing SpidersMakeWebs");
    }

    @SubscribeEvent
    public void EntityJoined(EntityJoinLevelEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Spider spider) {
            ((Spider) event.getEntity()).goalSelector.addGoal(2, new MakeWebGoal(spider));
            ((Spider) event.getEntity()).goalSelector.addGoal(7, new PlaceWebRandomGoal(spider));
        }
    }
}
