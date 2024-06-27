package vg.civcraft.mc.civmodcore.pdc.extensions;

import java.util.Map;
import javax.annotation.Nonnull;
import net.minecraft.nbt.Tag;
import org.bukkit.craftbukkit.v1_20_R3.persistence.CraftPersistentDataContainer;
import org.bukkit.persistence.PersistentDataContainer;

/**
 * Set of extension methods for {@link PersistentDataContainer}.
 */
public final class PersistentDataContainerExtensions {

    /**
     * @param self The PersistentDataContainer to get the internal NBT of.
     * @return Returns the PDC's inner-map.
     */
    @Nonnull
    public static Map<String, Tag> getRaw(@Nonnull final PersistentDataContainer self) {
        return ((CraftPersistentDataContainer) self).getRaw();
    }

    /**
     * @param self The PersistentDataContainer to get the size of.
     * @return Returns the PDC's size.
     */
    public static int size(@Nonnull final PersistentDataContainer self) {
        return getRaw(self).size();
    }

}
