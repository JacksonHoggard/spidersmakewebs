package io.github.jacksonhoggard.spidersmakewebs;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import java.util.Random;

public class MakeWebGoal extends Goal {

    private Spider spider;
    private int radius = 10;
    private int webCD = 12000;
    private int[][] pattern = {
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0 },
            { 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0 },
            { 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 0, 0 },
            { 0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0 },
            { 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0 },
            { 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0 },
            { 0, 1, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0 },
            { 1, 1, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0 },
            { 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0 },
            { 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0 },
            { 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1 },
            { 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0 },
            { 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0 },
            { 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0 },
            { 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0 },
            { 0, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0 },
            { 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0 },
            { 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 }
    };

    public MakeWebGoal(Spider spider) {
        this.spider = spider;
    }

    @Override
    public boolean canUse() {
        webCD--;
        return webCD <= 0;
    }

    @Override
    public void start() {
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        int ran = new Random().nextInt(2);

        switch(ran) {
            case 0:
                for(int x = spider.blockPosition().getX() - radius, i = 0; x < spider.blockPosition().getX() + radius; x++, i++) {
                    for(int z = spider.blockPosition().getZ() - radius, j = 0; z < spider.blockPosition().getZ() + radius; z++, j++) {
                        Block block = spider.level.getBlockState(new BlockPos(x, spider.blockPosition().getY(), z)).getBlock();
                        if(pattern[i][j] == 1 && block.equals(Blocks.AIR)) {
                            spider.level.setBlockAndUpdate(new BlockPos(x, spider.blockPosition().getY(), z), Blocks.COBWEB.defaultBlockState());
                        }
                    }
                }
                break;
            case 1:
                for(int x = spider.blockPosition().getX() - radius, i = 0; x < spider.blockPosition().getX() + radius; x++, i++) {
                    for(int y = spider.blockPosition().getY(), j = 0; y < spider.blockPosition().getY() + (radius * 2); y++, j++) {
                        Block block = spider.level.getBlockState(new BlockPos(x, y, spider.blockPosition().getZ())).getBlock();
                        if(pattern[i][j] == 1 && block.equals(Blocks.AIR)) {
                            spider.level.setBlockAndUpdate(new BlockPos(x, y, spider.blockPosition().getZ()), Blocks.COBWEB.defaultBlockState());
                        }
                    }
                }
                break;
        }
    }

    @Override
    public void stop() {
        webCD = 12000;
    }
}
