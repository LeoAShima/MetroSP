package net.fabricmc.example;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemMap extends Item {
    
    private BlockMap block;

    public ItemMap(Settings settings, BlockMap blockmap) {
        super(settings);
        this.block = blockmap;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext ctx) {
        World worldIn = ctx.getWorld();
        BlockPos pos = ctx.getBlockPos();
        BlockState bs = worldIn.getBlockState(pos);
        PlayerEntity pe = ctx.getPlayer();
        ItemStack is = ctx.getStack();

        if (!bs.getMaterial().isReplaceable()) {
            pos = pos.offset(ctx.getSide());
        }

        if (!pe.canModifyWorld()) {
            return ActionResult.FAIL;
        }

        if (!this.block.canBePlacedAt(worldIn, pos, bs, pe)) {
            return ActionResult.FAIL;
        }

        this.block.place(worldIn, pos, bs, pe);
        is.decrement(1);
        return ActionResult.SUCCESS;


    }

}