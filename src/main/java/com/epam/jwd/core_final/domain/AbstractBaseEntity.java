package com.epam.jwd.core_final.domain;

/**
 * Expected fields:
 * <p>
 * id {@link Long} - entity id
 * name {@link String} - entity name
 */
public abstract class AbstractBaseEntity implements BaseEntity {

    private static Long idCounter = 0L;

    public static Long reserveID(){
        return  idCounter++;
    }

    @Override
    public Long getId() {
        return null;
    }

    @Override
    public String getName() {
        // todo
        return null;
    }
}
