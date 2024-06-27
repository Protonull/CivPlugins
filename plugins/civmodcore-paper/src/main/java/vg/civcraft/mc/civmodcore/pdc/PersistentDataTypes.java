package vg.civcraft.mc.civmodcore.pdc;

import javax.annotation.Nonnull;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;

public final class PersistentDataTypes {

    public static final String DECODER_ERROR = "Was unable to decode that %s! [%s]";

    /**
     * Converts Components to Strings and vice versa.
     */
    public static final PersistentDataType<String, Component> COMPONENT = new AbstractPersistentDataType<>(String.class, Component.class) {
        @Nonnull
        @Override
        public String toPrimitive(@Nonnull final Component component,
                                  @Nonnull final PersistentDataAdapterContext adapter) {
            return GsonComponentSerializer.gson().serialize(component);
        }

        @Nonnull
        @Override
        public Component fromPrimitive(@Nonnull final String raw,
                                       @Nonnull final PersistentDataAdapterContext adapter) {
            return GsonComponentSerializer.gson().deserialize(raw);
        }
    };

}
