package com.epam.jwd.core_final.domain;

/**
 * Expected fields:
 * <p>
 * id {@link Long} - entity id
 * name {@link String} - entity name
 */
public abstract class AbstractBaseEntity implements BaseEntity {

    private static Long idCounter = 0L;

    @Override
    public Long getId() {
        //todo
        return idCounter++;
    }

    @Override
    public String getName() {
        // todo
        return null;
    }
}
