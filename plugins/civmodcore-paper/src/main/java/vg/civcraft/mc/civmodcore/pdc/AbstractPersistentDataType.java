package vg.civcraft.mc.civmodcore.pdc;

import java.util.Objects;
import java.util.function.Function;
import javax.annotation.Nonnull;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractPersistentDataType<P, C> implements PersistentDataType<P, C> {

    protected final Class<P> primitiveClass;
    protected final Class<C> complexClass;

    public AbstractPersistentDataType(@Nonnull final Class<P> primitiveClass,
                                      @Nonnull final Class<C> complexClass) {
        this.primitiveClass = Objects.requireNonNull(primitiveClass);
        this.complexClass = Objects.requireNonNull(complexClass);
    }

    /**
     * {@inheritDoc}
     */
    @Nonnull
    @Override
    public final Class<P> getPrimitiveType() {
        return this.primitiveClass;
    }

    /**
     * {@inheritDoc}
     */
    @Nonnull
    @Override
    public final Class<C> getComplexType() {
        return this.complexClass;
    }

    /**
     * {@inheritDoc}
     */
    @Nonnull
    @Override
    public abstract P toPrimitive(@Nonnull C instance,
                                  @Nonnull PersistentDataAdapterContext adapter);

    /**
     * {@inheritDoc}
     */
    @Nonnull
    @Override
    public abstract C fromPrimitive(@Nonnull P raw,
                                    @Nonnull PersistentDataAdapterContext adapter);

    /**
     * Convenience method to create a {@link PersistentDataType} for a flatly-valued type, like a String or an integer
     * (as opposed to a record or other type of object that warrants needing the {@link PersistentDataAdapterContext}).
     */
    public static <P, C> @NotNull AbstractPersistentDataType<P, C> flatValued(
        final @NotNull Class<P> primitiveClass,
        final @NotNull Class<C> complexClass,
        final @NotNull Function<@NotNull C, @NotNull P> encoder,
        final @NotNull Function<@NotNull P, @NotNull C> decoder
    ) {
        Objects.requireNonNull(encoder);
        Objects.requireNonNull(decoder);
        return new AbstractPersistentDataType<>(primitiveClass, complexClass) {
            @Override
            public @NotNull P toPrimitive(
                final @NotNull C value,
                final @NotNull PersistentDataAdapterContext adapter
            ) {
                return encoder.apply(value);
            }
            @Override
            public @NotNull C fromPrimitive(
                final @NotNull P raw,
                final @NotNull PersistentDataAdapterContext adapter
            ) {
                return decoder.apply(raw);
            }
        };
    }
}
